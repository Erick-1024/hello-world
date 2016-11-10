package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJDownloadBankBillResponse implements Serializable{
	
	private static final long serialVersionUID = -635137286751861990L;

	private String retCode;
	
	private String retMsg;
	
	private VJDownloadBankBillResponseData data;

	public VJDownloadBankBillResponseData getData() {
		return data;
	}

	public void setData(VJDownloadBankBillResponseData data) {
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
