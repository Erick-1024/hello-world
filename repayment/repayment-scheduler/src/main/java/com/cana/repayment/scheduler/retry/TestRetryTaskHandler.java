package com.cana.repayment.scheduler.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;

public class TestRetryTaskHandler extends AbstractRetryTaskHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		throw new Exception("exception count:" + task.getCurrentAttempts());
	}

	@Override
	public void onFail(RetryTask task) throws Exception {
		logger.info("最终失败了");
	}

}
