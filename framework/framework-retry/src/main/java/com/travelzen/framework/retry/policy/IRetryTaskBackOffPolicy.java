package com.travelzen.framework.retry.policy;

import java.util.Date;

import com.travelzen.framework.retry.dao.po.RetryTask;


/**
 * 重试任务回退策略
 * @author renshui
 *
 */
public interface IRetryTaskBackOffPolicy {
	/**
	 * 重试时间是否已到
	 * @param lastRunTime
	 * @param currentAttempts
	 * @param task
	 * @return
	 */
	public boolean isTimeUp(Date lastRunTime, Long currentAttempts, RetryTask task);
}
