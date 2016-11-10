package com.travelzen.tops.mediaserver.exception;


public class MediaServerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1599565643966272386L;
	
	public MediaServerException() {
		super();
	}
	
	public MediaServerException(String message) {
		super(message);
	}
	
	public MediaServerException(Throwable throwable) {
		super(throwable);
	}
	
	public MediaServerException(String message,Throwable throwable) {
		super(message, throwable);
	}

}
