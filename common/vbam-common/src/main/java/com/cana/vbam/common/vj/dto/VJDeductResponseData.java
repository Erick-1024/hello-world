package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJDeductResponseData implements Serializable{
	
	private static final long serialVersionUID = 6045206742049678003L;

	private String vjTranSeq;
	
	private String bankCheckDate;

	public String getVjTranSeq() {
		return vjTranSeq;
	}

	public void setVjTranSeq(String vjTranSeq) {
		this.vjTranSeq = vjTranSeq;
	}

	public String getBankCheckDate() {
		return bankCheckDate;
	}

	public void setBankCheckDate(String bankCheckDate) {
		this.bankCheckDate = bankCheckDate;
	}
	
	

}
