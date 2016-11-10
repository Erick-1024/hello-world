package com.travelzen.framework.retry.handler;

/**
 * 处理结果
 * @author renshui
 *
 */
public class HandlerStatus {
	/**
	 * 任务执行是否失败
	 */
	private boolean isFail = false;
	/**
	 * 任务执行结果描述
	 */
	private String message;
	
	public boolean isFail() {
		return isFail;
	}
	
	public void fail() {
		this.isFail = true;
	}

	
	public String getMessage() {
		return message;
	}

	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
