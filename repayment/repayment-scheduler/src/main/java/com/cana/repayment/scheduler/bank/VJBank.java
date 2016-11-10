package com.cana.repayment.scheduler.bank;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.remoting.TimeoutException;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.vj.dto.DeductRequest;
import com.cana.vbam.common.vj.dto.DeductResponse;
import com.cana.vbam.common.vj.dto.SyncVJDeductStateRetryTaskData;
import com.cana.vbam.common.vj.enums.TranState;
import com.cana.vj.api.IVJApi;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;
import com.travelzen.framework.retry.dict.RetryTaskState;
import com.travelzen.framework.retry.dict.RetryTaskType;
import com.travelzen.framework.retry.policy.RetryTaskBackOffPolicy;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class VJBank implements IBank{
	
	private IVJApi vjApi = SpringApplicationContext.getApplicationContext().getBean(IVJApi.class);
	
	private SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	private RetryTaskMapper retryTaskMapper = SpringApplicationContext.getApplicationContext().getBean(RetryTaskMapper.class);
	
	private UserMapper userMapper = SpringApplicationContext.getApplicationContext().getBean(UserMapper.class);
	
	private IVbamCommonService commonService = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 无法获取账户余额，返回最大值
	 */
	@Override
	public long getAccountBalance(String accountNo) throws Exception {
		return Long.MAX_VALUE;
	}

	@Override
	public long deductAmount(long amount, RepaymentLoanInfoBO loanInfoBO, RepaymentDailyBatchTaskItemBO taskItemBO)
			throws Exception {

		List<RepaymentPlanBO> repaymentPlanBOs = loanInfoBO.lazyLoadPlans();
		
		// 创建自动扣款交易
		DeductResponse deductResponse = null;
		DeductRequest request = new DeductRequest();
		request.setCanaTranSeq(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_CANA_TRAN_SEQ, 4));
		request.setCustomerName(loanInfoBO.getFinanceCompany());
		request.setIdentityCardNo(userMapper.selectByPrimaryKey(loanInfoBO.getFinanceId()).getIdentityCardNo());
		request.setLoanId(loanInfoBO.getId());
		request.setPlanId(repaymentPlanBOs.get(0).getId());
		request.setAmount(amount);
		request.setAllowPartialDeduct(false);
		request.setOverdue(repaymentPlanBOs.get(0).inOverdueState(commonService.getCurrentDate()));
		logger.info("账扣请求:" + new Gson().toJson(request));
		try{
			deductResponse = vjApi.deductAmount(request);
		}catch(Exception e){
			logger.error("", e);
			if (ExceptionUtils.indexOfThrowable(e, TimeoutException.class) != -1){
				insertSyncVJDeductStateRetryTask(request, taskItemBO);
				throw new Exception("扣款状态未明");
			}
		}
		logger.info("账扣响应:" + new Gson().toJson(deductResponse));

		if (deductResultUnknown(deductResponse)) {
			insertSyncVJDeductStateRetryTask(request, taskItemBO);
			throw new Exception("扣款状态未明");
		}else if(deductResultSuccess(deductResponse)){
			logger.info("扣款成功");
			return amount;
		}else{
			logger.info("扣款失败");
			return 0L;
		}

	}
	
	private void insertSyncVJDeductStateRetryTask(DeductRequest request, RepaymentDailyBatchTaskItemBO taskItemBO){
		
		SyncVJDeductStateRetryTaskData data = new SyncVJDeductStateRetryTaskData();
		data.setCanaTranSeq(request.getCanaTranSeq());
		data.setLoanInfoId(request.getLoanId());
		data.setTaskItemId(taskItemBO.getId());
		data.setAmount(request.getAmount());
		
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.SYNC_VJ_DEDUCT_STATE.name());
		task.setTaskId(request.getCanaTranSeq());
		task.setState(RetryTaskState.not_begin.name());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setData(new Gson().toJson(data));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);	
	}

	/**
	 * 返回扣款结果是否成功
	 * @param deductResponse
	 * @return
	 */
	private boolean deductResultSuccess(DeductResponse deductResponse) {
		return deductResponse.getRetCode() == ReturnCode.SUCCESS && deductResponse.getState() == TranState.SUCCESS;
	}

	/**
	 * 扣款结果未知
	 * @param deductResponse
	 * @return
	 */
	private boolean deductResultUnknown(DeductResponse deductResponse) {
		return deductResponse == null || deductResponse.getRetCode() == ReturnCode.TIMEOUT
				|| (deductResponse.getRetCode() == ReturnCode.SUCCESS && deductResponse.getState() == TranState.UNKNOWN);
	}

}
