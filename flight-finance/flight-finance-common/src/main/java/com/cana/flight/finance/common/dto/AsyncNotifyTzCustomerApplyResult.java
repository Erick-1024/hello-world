package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class AsyncNotifyTzCustomerApplyResult extends TravelzenBaseResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String customerId;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
