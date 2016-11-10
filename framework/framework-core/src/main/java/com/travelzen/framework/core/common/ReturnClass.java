package com.travelzen.framework.core.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ReturnClass {
	private ReturnCode retCode = ReturnCode.SUCCESS;
	private Object[] objects = null;

	public ReturnClass() {
	}

	public ReturnClass(ReturnCode sStatus) {
		setRetCode(sStatus);
	}

	public ReturnClass(ReturnCode sStatus, Object... objects) {
		setRetCode(sStatus, objects);
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}

	public ReturnCode getRetCode() {
		return retCode;
	}

	public void setRetCode(ReturnCode retCode) {
		this.retCode = retCode;
	}

	public void setRetCode(ReturnCode retCode, Object[] objects) {
		this.retCode = retCode;
		this.objects = objects;
	}

	public String getMessage() {
		if (null != retCode.getRetMsg())
			return String.format(retCode.getRetMsg(), objects);
		return "";
    }

	public String getMessage(String format) {
		return String.format(format, objects);
	}

	public String toString() {

		return ReflectionToStringBuilder.toString(this);

	}
}