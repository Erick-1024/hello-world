package com.cana.vbam.common.credit.dto.trade;

import java.io.Serializable;

import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;

public class CreditTradeStateResultDTO extends TravelzenBaseResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String tradeNo;
	
	private String tranSeq;
	
	private String tradeType;
	
	private String tradeStatus;
	
	private String tradeSuccessTime;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTranSeq() {
		return tranSeq;
	}

	public void setTranSeq(String tranSeq) {
		this.tranSeq = tranSeq;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeSuccessTime() {
		return tradeSuccessTime;
	}

	public void setTradeSuccessTime(String tradeSuccessTime) {
		this.tradeSuccessTime = tradeSuccessTime;
	}
	
}
