package com.cana.flight.finance.common.dto;

import java.io.Serializable;
import java.util.Date;

public class CreditRefundDTO  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String institution;

	private String customerId;

	private String tradeNo;

	private String originTradeNo;
	
	private Long refundFee;

	private String notifyURL;

	private String sign;

	private String tradeTime;

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOriginTradeNo() {
		return originTradeNo;
	}

	public void setOriginTradeNo(String originTradeNo) {
		this.originTradeNo = originTradeNo;
	}

	public Long getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Long refundFee) {
		this.refundFee = refundFee;
	}

	public String getNotifyURL() {
		return notifyURL;
	}

	public void setNotifyURL(String notifyURL) {
		this.notifyURL = notifyURL;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	
}
