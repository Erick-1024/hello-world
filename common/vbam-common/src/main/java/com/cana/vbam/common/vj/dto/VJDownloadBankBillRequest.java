package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJDownloadBankBillRequest implements Serializable{
	
	private static final long serialVersionUID = 4232012140018136668L;

	private String bankCheckDate;

	public String getBankCheckDate() {
		return bankCheckDate;
	}

	public void setBankCheckDate(String bankCheckDate) {
		this.bankCheckDate = bankCheckDate;
	}

}
