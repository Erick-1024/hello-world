package com.cana.yundaex.common.dto;

import java.io.Serializable;

public class YundaexReturnData<T> implements Serializable {

	private static final long serialVersionUID = -3658730873899758255L;
	
	private String errorCode;

	private String msg;

	private T data;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
