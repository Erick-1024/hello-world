package com.travelzen.framework.retry.handler;

import java.util.List;

import com.travelzen.framework.retry.dict.RetryTaskType;

public interface IRetryTaskHandlerFactory {
	
	/**
	 * 根据任务类型获取任务处理器
	 * @param taskType
	 * @return
	 */
	public IRetryTaskHandler getHandler(String taskType);
	
	/**
	 * 可以处理的重试任务类型
	 * @return
	 */
	public List<RetryTaskType> canHandleTaskTypes();
}
