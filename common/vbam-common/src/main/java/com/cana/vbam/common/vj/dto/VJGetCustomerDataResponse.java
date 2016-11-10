package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJGetCustomerDataResponse implements Serializable{
	
	private static final long serialVersionUID = -4305323259367150088L;

	private String retCode;
	
	private String retMsg;
	
	private CustomerData data;

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

	public CustomerData getData() {
		return data;
	}

	public void setData(CustomerData data) {
		this.data = data;
	}

}
