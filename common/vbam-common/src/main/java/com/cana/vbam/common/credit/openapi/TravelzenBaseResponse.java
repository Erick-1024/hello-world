package com.cana.vbam.common.credit.openapi;

import java.io.Serializable;

public class TravelzenBaseResponse implements Serializable {

	private static final long serialVersionUID = 2384849780440080967L;

	private String retCode;
	
	private String retMsg;
	
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
