package com.cana.vbam.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cana.vbam.common.enums.AjaxResponseStatus;

/**
 * 用于ajax请求，并返回list结果的包装类
 * 
 * @author dev4
 *
 * @param <T>
 */
public class ListResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8343701728567309053L;

	private int totalNum;

	private List<T> data;

	private String message;

	private AjaxResponseStatus status;

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = null == data ? new ArrayList<T>() : data;
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

	public static <T> ListResult<T> success(String message, List<T> data, int totalNum) {
		ListResult<T> result = new ListResult<>();
		result.setStatus(AjaxResponseStatus.SUCCESS);
		result.setMessage(message);
		result.setData(null == data ? new ArrayList<T>() : data);
		result.setTotalNum(totalNum);
		return result;
	}

	public static <T> ListResult<T> success(String message) {
		return success(message, null, 0);
	}

	public static <T> ListResult<T> success(List<T> data, int totalNum) {
		return success(null, data, totalNum);
	}

	public static <T> ListResult<T> success() {
		return success(null, null, 0);
	}

	public static <T> ListResult<T> fail(String message) {
		ListResult<T> result = new ListResult<>();
		result.setStatus(AjaxResponseStatus.FAILED);
		result.setMessage(message);
		result.setData(new ArrayList<T>());
		return result;
	}
}
