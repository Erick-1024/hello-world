package com.cana.repayment.scheduler.retry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.travelzen.framework.retry.dict.RetryTaskType;
import com.travelzen.framework.retry.handler.IRetryTaskHandler;
import com.travelzen.framework.retry.handler.IRetryTaskHandlerFactory;

@Component
public class RetryTaskHandlerFactory implements IRetryTaskHandlerFactory {

	private static Map<RetryTaskType, IRetryTaskHandler> cache = new HashMap<>();
	
	@Override
	public IRetryTaskHandler getHandler(String taskType) {
		taskType = StringUtils.trimToEmpty(taskType);
		if (StringUtils.isBlank(taskType))
			return null;
		RetryTaskType type = RetryTaskType.valueOf(taskType);
		IRetryTaskHandler handler = cache.get(type);
		if (handler != null)
			return handler;
		switch (type) {
		case REPAYMENT_SUCCESS_NOTIFY:
			handler = new RepaymentSuccessNotifyRetryTaskHandler();
			cache.put(type, handler);
			break;
		case ADJUST_SUCCESS_NOTIFY:
			handler = new AdjustSuccessNotifyRetryTaskHandler();
			cache.put(type, handler);
			break;
		case REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY:
			handler = new RepaymentSuccessSmsMailNotifyRetryTaskHandler();
			cache.put(type, handler);
			break;
		case SYNC_VJ_DEDUCT_STATE:
			handler = new SyncVJDeductStateRetryTaskHandler();
			cache.put(type, handler);
			break;
		case TZ_ACCOUNT_REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY:
			handler = new TzAccountRepaymentSuccessSmsMailNotifyHandler();
			cache.put(type, handler);
			break;	
		
		default:
			break;
		}
		return handler;
	}

	@Override
	public List<RetryTaskType> canHandleTaskTypes() {
		return Arrays.asList(RetryTaskType.CREDIT_LIMIT_RECOVERY, 
				             RetryTaskType.REPAYMENT_SUCCESS_NOTIFY,
				             RetryTaskType.ADJUST_SUCCESS_NOTIFY,
				             RetryTaskType.REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY,
				             RetryTaskType.SYNC_VJ_DEDUCT_STATE,
				             RetryTaskType.TZ_ACCOUNT_REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY);
	}

}
