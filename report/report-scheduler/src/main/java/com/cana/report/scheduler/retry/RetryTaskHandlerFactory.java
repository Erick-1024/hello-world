package com.cana.report.scheduler.retry;

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
		case ACCOUT_BALANCE_GET:
			handler = new AccountBalanceGetRetryTaskHandler();
			cache.put(type, handler);
			break;
		default:
			break;
		}
		return handler;
	}

	@Override
	public List<RetryTaskType> canHandleTaskTypes() {
		return Arrays.asList(RetryTaskType.ACCOUT_BALANCE_GET);
	}

}
