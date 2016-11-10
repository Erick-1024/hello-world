package com.travelzen.framework.core.exception;

/**
 * 抛出此异常的方法可以重试
 * 
 * @author renshui
 *
 */
public class CanRetryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private boolean nowarning = false; // 是否报警，为true是表示不报警

	public static CanRetryException instance(String retMsg) {
		return new CanRetryException(retMsg, null);
	}

	public static CanRetryException instance(String retMsg, boolean nowarning) {
		return new CanRetryException(retMsg, null, nowarning);
	}

	public CanRetryException(String retMsg, Throwable thr) {
		super(retMsg, thr);
	}

	public CanRetryException(String retMsg, Throwable thr, boolean nowarning) {
		super(retMsg, thr);
		this.nowarning = nowarning;
	}

	public boolean isNowarning() {
		return nowarning;
	}

	public boolean isWarning() {
		return !nowarning;
	}

	public void setNowarning(boolean nowarning) {
		this.nowarning = nowarning;
	}

}
