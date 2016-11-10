package com.travelzen.framework.core.exception;

/**
 * 重复执行异常
 * @author renshui
 *
 */
public class RepeatedExecuteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static RepeatedExecuteException instance(String retMsg) {
		return new RepeatedExecuteException(retMsg, null);
	}

	public RepeatedExecuteException(String retMsg, Throwable thr) {
		super(retMsg, thr);
	}

}
