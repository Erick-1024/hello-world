/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class CreditPayDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String institution;

	private String customerId;

	private String tradeNo;

	private Long paymentFee;

	private String notifyURL;

	private String sign;

	private String tradeTime;

	private String customerName;

	private String orderInfo;
	
	private String activityId;

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

	public Long getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(Long paymentFee) {
		this.paymentFee = paymentFee;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

}