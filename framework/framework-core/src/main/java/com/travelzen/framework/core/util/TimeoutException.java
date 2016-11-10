package com.travelzen.framework.core.util;

/**
 * Thrown when a command does not complete in enough time.
 */
public class TimeoutException extends RuntimeException{

  /**
	 * 
	 */
	private static final long serialVersionUID = -1725580304148423363L;

public TimeoutException() {
  }

  public TimeoutException(String message) {
    super(message);
  }

  public TimeoutException(Throwable cause) {
    super(cause);
  }

  public TimeoutException(String message, Throwable cause) {
    super(message, cause);
  }
}

