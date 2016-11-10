package com.cana.vbam.common.dto;

import java.io.Serializable;

import com.cana.vbam.common.enums.AjaxResponseStatus;

/**
 * 用于ajax请求，返回非list的的包装类
 * @author dev4
 *
 */
public class ObjectResult<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4543583458127221425L;

	private T data;
	
	private String message;
	
	private AjaxResponseStatus status;


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AjaxResponseStatus getStatus() {
		return status;
	}

	public void setStatus(AjaxResponseStatus status) {
		this.status = status;
	}
	
	public static <T>ObjectResult<T> success(String message, T data) {
		ObjectResult<T> result = new ObjectResult<>();
		result.setStatus(AjaxResponseStatus.SUCCESS);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
	
	public static <T>ObjectResult<T> success(String message) {
		return success(message, null);
	}
	
	public static <T>ObjectResult<T> success() {
		return success(null, null);
	}
	
	public static <T>ObjectResult<T> fail(String message) {
		ObjectResult<T> result = new ObjectResult<>();
		result.setStatus(AjaxResponseStatus.FAILED);
		result.setMessage(message);
		return result;
	}
}
