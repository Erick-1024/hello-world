package com.cana.credit.scheduler.retry;

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
		case AUDIT_RESULT:
			handler = new AuditResultRetryTaskHandler();
			cache.put(type, handler);
			break;
		case CREDIT_LIMIT_EFFECT:
			handler = new CreditLimitEffectRetryTaskHandler();
			cache.put(type, handler);
			break;
		case CREDIT_TRADE_RESULT:
			handler = new CreditTradeResultRetryTaskHandler();
			cache.put(type, handler);
			break;
		case CREDIT_CREATE_USER:
			handler = new CreditCreateUserTaskHandler();
			cache.put(type, handler);
			break;
		case CREDIT_UPDATE_USER_ROLE:
			handler = new UpdateUserRoleTaskHandler();
			cache.put(type, handler);
			break;
		case REFUND2CUSTOMER_SUCCESS_NOTIFY:
			handler = new Refund2CustomerSuccessRetryTaskHandler();
			cache.put(type, handler);
			break;
		default:
			break;
		}
		return handler;
	}

	@Override
	public List<RetryTaskType> canHandleTaskTypes() {
		return Arrays.asList(RetryTaskType.AUDIT_RESULT, 
				             RetryTaskType.CREDIT_LIMIT_EFFECT,
				             RetryTaskType.CREDIT_TRADE_RESULT,
				             RetryTaskType.CREDIT_CREATE_USER,
				             RetryTaskType.CREDIT_UPDATE_USER_ROLE,
				             RetryTaskType.REFUND2CUSTOMER_SUCCESS_NOTIFY);
	}

}
