package com.cana.repayment.scheduler.handler;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.annotation.MQConsumer;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.message.dto.RepaymentSuccessMessage;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.GsonBuilder;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;
import com.travelzen.framework.retry.dict.RetryTaskType;
import com.travelzen.framework.retry.policy.RetryTaskBackOffPolicy;

@MQConsumer(RepaymentSuccessMessage.class)
public class RepaymentSuccessHandler extends AbstractMQMessageHandler<RepaymentSuccessMessage>{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void handleMessage(RepaymentSuccessMessage message) throws Exception {
		int hour = 0;
		switch (message.getRepaymentMethod()) {
		case ACCOUNTDEDUCTION:
			RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(message.getLoanInfoId());
			if(StringUtils.isBlank(loanInfoBO.getBusinessProductId()) || (!StringUtils.equals(loanInfoBO.getBusinessProductId(), 
					Constants.TRAVELZEN_FINANCE_PRODUCT_ID)	&& !StringUtils.equals(loanInfoBO.getBusinessProductId(), Constants.YUNDAEX_FINANCE_PRODUCT_ID))){
				logger.info("暂不处理该产品的还款提醒: " + message.getBusinessProductId());
				return;
			}
			hour = vbamCommonService.getCurrentDateTime().getHourOfDay();
			RetryTask task = new RetryTask();
			task.setTaskType(RetryTaskType.REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY.name());
			task.setTaskId(message.getLoanInfoId() + UUID.randomUUID().toString());
			task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
			task.setRetryPolicy(RetryTaskPolicy.simple.name());
			task.setFixedBackoffPeriod(10L);
			task.setMaxAttempts(1L);
			task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(message));
			if(hour >= 22){
				task.setEndTime(DateTime.parse(vbamCommonService.getCurrentDate()).plusDays(1).plusHours(10).toDate());
			}
			retryTaskMapper.insertSelective(task);
			break;
			
		case REFUND:
			if(!StringUtils.equals(message.getBusinessProductId(), Constants.TRAVELZEN_FINANCE_PRODUCT_ID)){
				logger.info("暂不处理该产品的还款提醒: " + message.getBusinessProductId());
			}
			hour = vbamCommonService.getCurrentDateTime().getHourOfDay();
			task = new RetryTask();
			task.setTaskType(RetryTaskType.REFUND2CUSTOMER_SUCCESS_NOTIFY.name());
			task.setTaskId(message.getLoanInfoId() + UUID.randomUUID().toString());
			task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
			task.setRetryPolicy(RetryTaskPolicy.simple.name());
			task.setFixedBackoffPeriod(10L);
			task.setMaxAttempts(1L);
			task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(message));
			if(hour > 22){
				task.setStartTime(DateTime.parse(vbamCommonService.getCurrentDate()).plusDays(1).plusHours(10).toDate());
			}
			retryTaskMapper.insertSelective(task);
			logger.info("暂不提醒该类型还款: " + RepaymentMethod.REFUND.desc());
			break;
			
		case ACTIVE:
			hour = vbamCommonService.getCurrentDateTime().getHourOfDay();
			task = new RetryTask();
			task.setTaskType(RetryTaskType.REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY.name());
			task.setTaskId(message.getLoanInfoId() + UUID.randomUUID().toString());
			task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
			task.setRetryPolicy(RetryTaskPolicy.simple.name());
			task.setFixedBackoffPeriod(10L);
			task.setMaxAttempts(1L);
			task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(message));
			if(hour > 22){
				task.setStartTime(DateTime.parse(vbamCommonService.getCurrentDate()).plusDays(1).plusHours(10).toDate());
			}
			retryTaskMapper.insertSelective(task);
			break;
			
		case TZACCOUNT:
			hour = vbamCommonService.getCurrentDateTime().getHourOfDay();
			task = new RetryTask();
			task.setTaskType(RetryTaskType.TZ_ACCOUNT_REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY.name());
			task.setTaskId(message.getLoanInfoId() + UUID.randomUUID().toString());
			task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
			task.setRetryPolicy(RetryTaskPolicy.simple.name());
			task.setFixedBackoffPeriod(10L);
			task.setMaxAttempts(1L);
			task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(message));
			if(hour > 22){
				task.setStartTime(DateTime.parse(vbamCommonService.getCurrentDate()).plusDays(1).plusHours(10).toDate());
			}
			retryTaskMapper.insertSelective(task);
			break;
			
		default:
			break;
		}
	}
}
