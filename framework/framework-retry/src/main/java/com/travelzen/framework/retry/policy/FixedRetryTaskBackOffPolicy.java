package com.travelzen.framework.retry.policy;

import java.util.Date;

import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.core.time.DateTimeUtil;


public class FixedRetryTaskBackOffPolicy implements IRetryTaskBackOffPolicy{

	@Override
	public boolean isTimeUp(Date lastRunTime, Long currentAttempts, RetryTask task) {
		Date now = new Date();
		if(lastRunTime == null)
			return true;
		return DateTimeUtil.diffInSec(lastRunTime, now) >= task.getFixedBackoffPeriod();
	}

}
