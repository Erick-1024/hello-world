package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class AsyncNotifyTzCustomerCreditLimitResult extends TravelzenBaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String customerId;
	
	private Long limit;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

}
