package com.travelzen.framework.retry.template;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dao.po.RetryTaskExample;
import com.travelzen.framework.retry.dict.RetryTaskState;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.retry.handler.IRetryTaskHandler;
import com.travelzen.framework.retry.policy.IRetryTaskPolicy;
import com.travelzen.framework.retry.policy.RetryTaskPolicyFactory;

public class RetryTaskTemplate implements Runnable {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RetryTaskMapper retryTaskMapper;

	private RetryTask task;

	private IRetryTaskHandler handler;
	
	private IRetryTaskPolicy retryTaskPolicy;
	
	public RetryTaskTemplate(RetryTaskMapper retryTaskMapper, RetryTask task, IRetryTaskHandler handler){
		this.retryTaskMapper = retryTaskMapper;
		this.task= task;
		this.handler = handler;
	}

	public void execute(RetryTask task, IRetryTaskHandler handler) throws Exception {
		MDC.put("rpid", RandomStringUtils.randomAlphanumeric(10));
		logger.info("执行重试任务:" + new Gson().toJson(task));
		String oldState = task.getState();
		
		if (!setTaskStart(task)){
			logger.error("设置任务开始执行失败");
			return;
		}

		if (handler == null) {
			logger.error("未找到重试任务处理程序");
			terminateTaskAbruptly(task, "未找到重试任务处理程序");
			return;
		}
		
		retryTaskPolicy = RetryTaskPolicyFactory.getPolicy(task.getRetryPolicy());
		
		if(retryTaskPolicy == null){
			logger.error("未找到RetryTaskPolicy， 不再执行");
			terminateTaskAbruptly(task, "未找到RetryTaskPolicy， 不再执行");
			return;
		}
		
		if(task.getTaskDeadline() == null && task.getMaxAttempts() == 0){
			String errorMsg = "未指定任务的截止日期或者最大重试次数";
			logger.error(errorMsg);
			terminateTaskAbruptly(task, errorMsg);
			return;
		}
		
		if (!retryTaskPolicy.isAlive(task)) {
			logger.error("该任务已经过期或已结束， 不再执行");
			terminateTaskAbruptly(task, "该任务已经过期或已结束， 不再执行");
			return;
		}
		
		if(!retryTaskPolicy.isTimeUp(task)){
			logger.info("还未到重试时间");
			restoreState(task, oldState);
			return;
		}
		
		logger.info(String.format("开始第%s次重试", task.getCurrentAttempts() + 1));
		try {
			HandlerStatus status = new HandlerStatus();
			handler.execute(task, status);
			if (status.isFail())
				setTaskFail(task, status.getMessage());
			else setTaskSuccess(task);
		} catch (Exception e) {
			logger.error("", e);
			setTaskFail(task, e);
		} finally {
			endTaskExcution(task);
		}
	}
	
	private void restoreState(RetryTask task, String oldState) throws Exception{
		task.setState(oldState);
		Date now = new Date();
		task.setUpdateDate(now);
		retryTaskMapper.updateByPrimaryKeySelective(task);
	}

	private void endTaskExcution(RetryTask task) throws Exception {
		Date now = new Date();
		task.setEndTime(now);
		task.setCurrentAttempts(task.getCurrentAttempts() + 1);
		boolean failFinished = !retryTaskPolicy.isAlive(task);
		boolean successFinished = RetryTaskState.success.name().equals(task.getState()); 
		task.setFinished(failFinished || successFinished);
		task.setUpdateDate(now);
		retryTaskMapper.updateByPrimaryKeySelective(task);
		
		if(failFinished){ //触发失败事件
			triggerFailEvent(task);
		}
	}

	/**
	 * 设置任务执行失败
	 * @param task2
	 * @param e
	 */
	private void setTaskFail(RetryTask task, Exception e) {
		task.setState(RetryTaskState.fail.name());
		task.setMessage(trimMessage(ExceptionUtils.getStackTrace(e)));
	}
	
	private String trimMessage(String message){
		if(StringUtils.isEmpty(message))
			return message;
		final int MAX_LENGTH = 4096 / 3;
		if(message.length() > MAX_LENGTH)
			return message.substring(0, MAX_LENGTH);
		else return message;
	}

	/**
	 * 设置任务成功 
	 * @param task
	 */
	private void setTaskSuccess(RetryTask task) {
		task.setState(RetryTaskState.success.name());
		logger.info("任务执行成功，任务ID:{}", task.getTaskId());
	}

	/**
	 * 设置任务失败
	 * @param task
	 * @param message
	 */
	private void setTaskFail(RetryTask task, String message) {
		task.setState(RetryTaskState.fail.name());
		task.setMessage(trimMessage(message));
	}
	
	/**
	 * 异常终止任务执行 
	 * @param task
	 * @param string
	 * @throws Exception
	 */
	private void terminateTaskAbruptly(RetryTask task, String message) throws Exception{
		Date now = new Date();
		task.setState(RetryTaskState.fail.name());
		task.setFinished(true);
		task.setMessage(trimMessage(message));
		task.setUpdateDate(now);
		retryTaskMapper.updateByPrimaryKeySelective(task);
		// 触发失败事件
		triggerFailEvent(task);
	}

	/**
	 * 触发失败事件
	 * @param task
	 */
	private void triggerFailEvent(RetryTask task) {
		try{
			if(handler != null)
				handler.onFail(task);
		}catch(Exception e){
			logger.error("", e);
		}
	}

	/**
	 * 设置任务执行开始
	 * 
	 * @param task
	 * @return
	 */
	private boolean setTaskStart(final RetryTask task) {
		try {
			Date now = new Date();
			RetryTaskExample example = new RetryTaskExample();
			example.createCriteria().andTaskTypeEqualTo(task.getTaskType()).andTaskIdEqualTo(task.getTaskId())
					.andBusinessTransactionVersionEqualTo(task.getBusinessTransactionVersion());
			task.setState(RetryTaskState.processing.name());
			task.setStartTime(now);
			task.setBusinessTransactionVersion(DateTimeUtil.datetime14() + "-" + RandomStringUtils.randomNumeric(3));
			task.setUpdateDate(now);
			return retryTaskMapper.updateByExampleSelective(task, example) == 1;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	@Override
	public void run() {
		String rpid = RandomStringUtils.randomNumeric(10);
		MDC.put("rpid", String.format("[rpid=%s]", rpid));
		try{
			execute(task, handler);
		}catch(Exception e){
			logger.error("", e);
		}finally{
			MDC.clear();
		}
	}

}
