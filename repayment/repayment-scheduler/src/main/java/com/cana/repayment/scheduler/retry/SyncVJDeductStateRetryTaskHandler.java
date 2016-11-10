package com.cana.repayment.scheduler.retry;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.service.bo.RepaymentDailyBatchTaskBO;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.vj.dto.QueryDeductStateRequest;
import com.cana.vbam.common.vj.dto.QueryDeductStateResponse;
import com.cana.vbam.common.vj.dto.SyncVJDeductStateRetryTaskData;
import com.cana.vbam.common.vj.enums.TranState;
import com.cana.vj.api.IVJApi;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class SyncVJDeductStateRetryTaskHandler extends AbstractRetryTaskHandler{
	
	private IVbamCommonService commonService = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);
	
	private IVJApi vjApi = SpringApplicationContext.getApplicationContext().getBean(IVJApi.class);
	
	private IRepaymentTransactionService transactionService = SpringApplicationContext.getApplicationContext().getBean(IRepaymentTransactionService.class);

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		
		SyncVJDeductStateRetryTaskData data = new Gson().fromJson(task.getData(), SyncVJDeductStateRetryTaskData.class);
		RepaymentDailyBatchTaskItemBO taskItemBO = new RepaymentDailyBatchTaskItemBO(data.getTaskItemId());
		RepaymentDailyBatchTaskBO taskBO = taskItemBO.lazyLoadTaskBO();
		
		if(!commonService.getCurrentDate().equals(taskBO.getDate())){
			logger.info("跨天了，不再处理");
			return;
		}
		
		if(taskItemBO.getId().equals(taskBO.getFailTaskItemId())){
			QueryDeductStateRequest request = new QueryDeductStateRequest();
			request.setCanaTranSeq(data.getCanaTranSeq());
			logger.info("查询自动扣款状态请求:" + new Gson().toJson(request));
			QueryDeductStateResponse response = vjApi.queryDeductState(request);
			logger.info("查询自动扣款状态的响应:" + new Gson().toJson(response));
			if(response.getRetCode() == ReturnCode.SUCCESS){
				if(response.getState() == TranState.UNKNOWN){
					throw new Exception("扣款状态未明");
				}else if(response.getState() == TranState.SUCCESS){
					logger.info("查询到自动扣款成功");
					boolean defaultDeduct = Boolean.valueOf(taskItemBO.extraData("defaultDeduct"));
					String curDate10 = taskBO.getDate();
					if(defaultDeduct)
						curDate10 = DateTimeUtil.addDay10(taskBO.getDate(), -1);
					Map<Object, Object> extra = new HashMap<>();
					transactionService.updateOnDeductSuccess(data.getAmount(), taskBO, taskItemBO, curDate10, extra);
				}else{
					logger.info("查询到自动扣款失败");
					taskBO.advanceToNextTask();
				}
				
			}
		}else{
			logger.info("任务状态不对，不再重试");
			return;
		}
		
		
	}

}
