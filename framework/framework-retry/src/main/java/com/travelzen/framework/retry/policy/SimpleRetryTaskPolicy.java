package com.travelzen.framework.retry.policy;

import java.util.Date;

import com.travelzen.framework.retry.dao.po.RetryTask;


public class SimpleRetryTaskPolicy implements IRetryTaskPolicy{

	@Override
	public boolean isTimeUp(RetryTask task) {
		IRetryTaskBackOffPolicy backOffPolicy =  RetryTaskBackOffPolicyFactory.getPolicy(task.getBackoffPolicy());
		if(backOffPolicy == null)
			return false;
		return backOffPolicy.isTimeUp(task.getEndTime(), task.getCurrentAttempts() + 1, task);
	}

	@Override
	public boolean isAlive(RetryTask task) {
		Date now = new Date();
		if(task.getTaskDeadline() != null && now.compareTo(task.getTaskDeadline()) > 0)
			return false;
		if(task.getMaxAttempts() > 0 && task.getCurrentAttempts() >= task.getMaxAttempts()) 
			return false;
		return true;
	}

}
