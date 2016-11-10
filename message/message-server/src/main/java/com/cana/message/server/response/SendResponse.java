package com.cana.message.server.response;


public class SendResponse {
	
	private boolean success;
	
	private String failReason;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public static SendResponse success() {
		SendResponse result = new SendResponse();
		result.setSuccess(Boolean.TRUE);
		return result;
	}
	
	public static SendResponse fail(String message) {
		SendResponse result = new SendResponse();
		result.setSuccess(Boolean.FALSE);
		result.setFailReason(message);
		return result;
	}
	
}
