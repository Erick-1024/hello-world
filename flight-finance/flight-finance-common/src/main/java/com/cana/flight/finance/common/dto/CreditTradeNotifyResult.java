package com.cana.flight.finance.common.dto;

public class CreditTradeNotifyResult extends CreditTradeResult {

	private static final long serialVersionUID = 1L;
	// 通知地址
	private String notifyUrl;

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
}
