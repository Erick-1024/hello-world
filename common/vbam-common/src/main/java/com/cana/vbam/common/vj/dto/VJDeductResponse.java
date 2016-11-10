package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJDeductResponse implements Serializable{
	
	private static final long serialVersionUID = -2472545362676520891L;
	
	private String retCode;
	
	private String retMsg;
	
	private VJDeductResponseData data;

	public VJDeductResponseData getData() {
		return data;
	}

	public void setData(VJDeductResponseData data) {
		this.data = data;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

}
