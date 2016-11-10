package com.travelzen.framework.retry.handler;

import com.travelzen.framework.retry.dao.po.RetryTask;

/**
 * 重试任务处理器
 * @author renshui
 *
 */
public abstract class AbstractRetryTaskHandler implements IRetryTaskHandler{
	/**
	 * 执行重试任务
	 * @param task 任务数据
	 * @param status 处理结果， 如果调用status.fail(), 标识任务处理失败
	 * @throws Exception 任务处理过程中出现异常
	 */
	public abstract void execute(RetryTask task, final HandlerStatus status) throws Exception; 
	
	/**
	 * 重试任务最终执行失败后会调用此方法
	 * @param task
	 * @throws Exception
	 */
	public void onFail(RetryTask task) throws Exception{
		
	}
}
