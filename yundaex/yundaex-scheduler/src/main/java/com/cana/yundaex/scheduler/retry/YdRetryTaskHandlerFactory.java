package com.cana.yundaex.scheduler.retry;

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
public class YdRetryTaskHandlerFactory implements IRetryTaskHandlerFactory {

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
		case YD_CREDIT_TRADE_RESULT:
			handler = new YdLoanResultRetryTaskHandler();
			cache.put(type, handler);
			break;
		case YD_AUDIT_RESULT:
			handler = new YdAuditResultRetryTaskHandler();
			cache.put(type,handler);
			break;
		case YD_CREDIT_CREATE_USER:
			handler = new YdCreditUserTaskHandler();
			cache.put(type, handler);
			break;
		case YD_CREDIT_UPDATE_USER_ROLE:
			handler = new YdUpdateUserRoleTaskHandler();
			cache.put(type, handler);
			break;
		case YD_STATION_PULL:
			handler = new YdStationPullHandler();
			cache.put(type, handler);
			break;
		case YD_STATION_SYN:
			handler = new YdStationSynTaskHandler();
			cache.put(type, handler);
			break;
		default:
			break;
		}
		return handler;
	}

	@Override
	public List<RetryTaskType> canHandleTaskTypes() {
		return Arrays.asList(RetryTaskType.YD_CREDIT_TRADE_RESULT,RetryTaskType.YD_AUDIT_RESULT,RetryTaskType.YD_AUDIT_RESULT,RetryTaskType.YD_CREDIT_CREATE_USER,RetryTaskType.YD_CREDIT_UPDATE_USER_ROLE,RetryTaskType.YD_STATION_PULL,RetryTaskType.YD_STATION_SYN);
	}

}
