package com.travelzen.framework.core.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 7758476083917374792L;
	
	public ServiceException() {
	}
	
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable throwable) {
		super(throwable);
	}

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}