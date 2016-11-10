package com.cana.vbam.common.credit.dto.trade;

import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;

public class CreditTradeResponse extends TravelzenBaseResponse {
 
	private static final long serialVersionUID = 1L;

	private String tranSeq;

	public String getTranSeq() {
		return tranSeq;
	}

	public void setTranSeq(String tranSeq) {
		this.tranSeq = tranSeq;
	}
	
}
