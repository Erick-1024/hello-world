package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class NotifyTzResultRetryTaskData<T> implements Serializable {

	private static final long serialVersionUID = 5933987239865514085L;

	private T data;
	
	private String url;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
