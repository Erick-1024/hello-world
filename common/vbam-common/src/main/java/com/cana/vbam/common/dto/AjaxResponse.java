package com.cana.vbam.common.dto;

import java.io.Serializable;
import com.cana.vbam.common.enums.AjaxResponseStatus;

@Deprecated
/**
 * 替代返回包装类
 * List结果集返回使用ListResult<T>
 * 非List结果返回使用ObjectResult<T>
 * 
 * */
public class AjaxResponse implements Serializable{
	
	private AjaxResponseStatus status;//ajax请求返回状态，是否处理成功
	
	private String message;//ajax请求返回message
	/**
	 * 
	 */
	private static final long serialVersionUID = -4934576355999549522L;
	
	/**
	 * 总数
	 */
	private int totalNum;
	
	/**
	 * 请求返回结果
	 */
	private Object data;

	public int getTotalNum() {
		return totalNum;
	}

	public Object getData() {
		return data;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public AjaxResponseStatus getStatus() {
		return status;
	}

	public void setStatus(AjaxResponseStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static AjaxResponse success(String message,Object data){
		AjaxResponse ajax = new AjaxResponse();
		ajax.setStatus(AjaxResponseStatus.SUCCESS);
		ajax.setMessage(message);
		ajax.setData(data);
		return ajax;
	}
	
	public static AjaxResponse success(String message){
		return success(message,null);
	}
	
	public static AjaxResponse success(Object data){
		return success(null,data);
	}
	
	public static AjaxResponse success(){
		return success(null,null);
	}
	
	public static AjaxResponse fail(String message){
		AjaxResponse ajax = new AjaxResponse();
		ajax.setStatus(AjaxResponseStatus.FAILED);
		ajax.setMessage(message);
		return ajax;
	}
}
