package com.cana.vbam.common.crawler.dto;

import java.io.Serializable;

import com.travelzen.framework.core.common.ReturnCode;

/**
 * 查询失信执行响应
 * 
 * @author renshui
 *
 */
public class GetShixinCourtResponse implements Serializable {

	
	private static final long serialVersionUID = 4603572667482321429L;
	// 返回码
	private ReturnCode retCode;
	// 返回消息
	private String retMsg;
	// 失信记录数
	private boolean onBlackList;
	
	public ReturnCode getRetCode() {
		return retCode;
	}
	public void setRetCode(ReturnCode retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public boolean isOnBlackList() {
		return onBlackList;
	}
	public void setOnBlackList(boolean onBlackList) {
		this.onBlackList = onBlackList;
	}

}
