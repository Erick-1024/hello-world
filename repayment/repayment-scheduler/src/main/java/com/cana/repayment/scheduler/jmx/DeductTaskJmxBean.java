package com.cana.repayment.scheduler.jmx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.cana.account.api.IAccountApi;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskExample;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskBO;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.account.dto.AccountTradeRecordBasicInfo;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.repayment.enums.BatchTaskType;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

@ManagedResource(description = "账扣人工干预") 
public class DeductTaskJmxBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private RepaymentDailyBatchTaskMapper dailyBatchTaskMapper;
	@Resource
	private IAccountApi accountApi;
	@Resource
	private IRepaymentTransactionService transactionService;
	
	@ManagedOperation(description="处理扣款状态未明")
    @ManagedOperationParameters({
    @ManagedOperationParameter(name = "loanInfoId", description = "放款信息id"),
    @ManagedOperationParameter(name = "date", description = "跑批日期"),
    })
	public void handleUnknownDeductState(String loanInfoId, String date) throws Throwable{
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		
		try{
		
			logger.info("开始处理扣款状态未明的跑批任务, loanInfoId={}, date={}", loanInfoId, date);

			RepaymentDailyBatchTaskBO taskBO = getTaskBO(loanInfoId, date);

			if (taskBO == null) {
				logger.error("该跑批任务不存在");
				return;
			}

			RepaymentDailyBatchTaskItemBO taskItemBO = taskBO.currentTaskItemBO();
			
			if(taskItemBO == null){
				logger.error("跑批任务已经执行成功, 不处理");
				return;
			}
			
			if(StringUtils.isBlank(taskBO.getFailTaskItemId())){
				logger.error("跑批任务未失败，不处理");
				return;
			}
			
			if (!BatchTaskType.deduct.name().equals(taskItemBO.getTaskType())) {
				logger.error("当前失败的跑批任务不是账扣");
				return;
			}

			if (taskBO.getCanRetry()) {
				logger.error("该任务可以重试，不需要人工干预");
				return;
			}

			String businessSeq = taskItemBO.extraData("businessSeq");
			logger.info("查询交易状态请求, businessSeq={}", businessSeq);
			AccountTradeRecordBasicInfo tradeRecord = accountApi.queryTradeRecordBasicInfo(businessSeq);
			logger.info("查询交易状态响应:" + new Gson().toJson(tradeRecord));

			if (tradeRecord == null) {
				logger.error("不存在这笔交易");
				return;
			}

			if (AccountTradeStatus.TRADE_SUCCESS != tradeRecord.getStatus()) {
				logger.error("交易不成功");
				return;
			}

			long actualDeductAmount = tradeRecord.getAmount();
			boolean defaultDeduct = Boolean.valueOf(taskItemBO.extraData("defaultDeduct"));
			String curDate10 = taskBO.getDate();

			if (defaultDeduct)
				curDate10 = DateTimeUtil.addDay10(taskBO.getDate(), -1);

			Map<Object, Object> extra = new HashMap<>();
			transactionService.updateOnDeductSuccess(actualDeductAmount, taskBO, taskItemBO, curDate10, extra);

			logger.info("处理成功");

		}catch(Exception e){
			logger.error("", e);
		}
	}
	
	private RepaymentDailyBatchTaskBO getTaskBO(String loanInfoId, String date) throws Exception{
		RepaymentDailyBatchTaskExample example = new RepaymentDailyBatchTaskExample();
		example.createCriteria().andLoanInfoIdEqualTo(loanInfoId)
		                        .andDateEqualTo(date);
		List<RepaymentDailyBatchTask> taskList = dailyBatchTaskMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(taskList))
			return null;
		return new RepaymentDailyBatchTaskBO(taskList.get(0));
	}
	
}
