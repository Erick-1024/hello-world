package com.cana.vbam.common.dto;

import java.io.Serializable;

import com.travelzen.framework.core.common.ReturnCode;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ReturnCode retCode;
	
	private String retMsg;
	
	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	@Override
	public String toString() {
		return "BaseResponse [retCode=" + retCode + ", retMsg=" + retMsg + "]";
	}

	public ReturnCode getRetCode() {
		return retCode;
	}

	public void setRetCode(ReturnCode retCode) {
		this.retCode = retCode;
	}
	
}
