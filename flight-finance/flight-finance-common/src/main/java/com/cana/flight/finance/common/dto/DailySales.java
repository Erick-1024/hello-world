package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class DailySales implements Serializable{

	private static final long serialVersionUID = -7563952168730360496L;

	private String customerId;
	
	private Long dailySales;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getDailySales() {
		return dailySales;
	}

	public void setDailySales(Long dailySales) {
		this.dailySales = dailySales;
	}
	
}
