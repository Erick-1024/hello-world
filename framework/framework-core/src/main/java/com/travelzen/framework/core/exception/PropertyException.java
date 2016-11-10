package com.travelzen.framework.core.exception;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class PropertyException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2501930532223237138L;
	private String retMsg = "";
	private String retName = null;
	private Object[] objects;

	public static PropertyException instanceConfNotFoundException(String retMsg) {
		return new PropertyException("ConfNotFoundException", retMsg, null);
	}

	public static PropertyException instanceConfNotFoundException(String retMsg, Throwable thr) {
		return new PropertyException("ConfNotFoundException", retMsg, thr);
	}

	public static PropertyException instance(String retName, String retMsg) {
		return new PropertyException(retName, retMsg, null);
	}

	public static PropertyException instance(String retName, String retMsg, Throwable thr) {
		return new PropertyException(retName, retMsg, thr);
	}

	public PropertyException(Throwable thr, Object... objects) {
		this("error", "", thr, objects);
	}

	public PropertyException(String retName, Throwable thr, Object... objects) {
		this(retName, "", thr, objects);
	}

	public PropertyException(String retName, String retMsg, Throwable thr, Object... objects) {
		super(String.format("[retName=%s,retMsg=%s]", retName, retMsg));
		this.retName = retName;
		this.objects = objects;
		this.retMsg = retMsg;
		this.initCause(thr);
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getRetName() {
		return retName;
	}

	@Override
	public String toString() {
		return String.format("[retName=%s,retMsg=%s]", retName, retMsg);
	}

	@Override
	public String getMessage() {
		return String.format("[retName=%s,retMsg=%s, objects=%s]", this.retName, this.retMsg, Arrays.deepToString(objects));
	}

	public String getMessage(String format, String separator) {
		return String.format(format, StringUtils.join(objects, separator));
	}

	public String getMessage(String format) {
		return String.format(format, objects);
	}

	/**
	 * 从thr中提取BizException,若提取不到返回thr
	 * 
	 * @param thr
	 * @return
	 */
	public static Exception unwrap(Exception e) {
		Throwable cause = e.getCause();
		if (cause != null && cause instanceof PropertyException)
			return (Exception) cause;
		return e;
	}

	/**
	 * 获取原始的消息
	 * 
	 * @param e
	 * @return
	 */
	public static String getRawMsg(Exception e) {
		if (e == null)
			return null;
		e = unwrap(e);
		if (e instanceof PropertyException) {
			PropertyException bizException = (PropertyException) e;
			return bizException.getRetMsg();
		} else
			return e.getMessage();
	}
}
