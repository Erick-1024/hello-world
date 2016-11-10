package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class CreditTradeResult implements Serializable{

	private static final long serialVersionUID = 1L;

	// 外部交易编号
	private String tradeNo;
	
	// cana交易流水号
	private String tranSeq; 
	
	// 交易类型：PAYMENT;REFUND
	private String tradeType;
	
	// 交易状态
	private String tradeStatus;
	
	// 在tradeStatus为SUCCESS的情况下返回交易成功的时间，格式：2016-01-18 07:50:00
	private String tradeSuccessTime;
	
	// 将以上所有字段按出现顺序拼接在一起，使用cana rsa私钥签名
	private String sign;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTranSeq() {
		return tranSeq;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public void setTranSeq(String tranSeq) {
		this.tranSeq = tranSeq;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
