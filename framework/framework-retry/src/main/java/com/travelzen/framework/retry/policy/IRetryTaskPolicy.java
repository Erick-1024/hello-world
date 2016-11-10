package com.travelzen.framework.retry.policy;

import com.travelzen.framework.retry.dao.po.RetryTask;

public interface IRetryTaskPolicy {
	/**
	 * 是否到了重试时间
	 * @param task
	 * @return
	 */
	public boolean isTimeUp(RetryTask task);
	
	/**
	 * 当任务deadline已过或者达到最大执行次数返回false，否则返回true
	 * @param task
	 * @return
	 */
	public boolean isAlive(RetryTask task);
}
