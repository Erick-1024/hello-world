package com.cana.bankgate.server.lock;

import java.io.Serializable;

public class LockRequest implements Serializable{
	
	private static final long serialVersionUID = -1328854604140644906L;

	private String requestId;
	
	private long startTime;
	
	// 超时时间，单位：秒
	private int timeout;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	

}
