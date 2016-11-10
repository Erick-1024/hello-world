package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class TravelzenBaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String retCode;//接收结果
	
	private String retMsg;//接收信息
	
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
