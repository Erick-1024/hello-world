package com.cana.yundaex.common.dto.limit;

import java.io.Serializable;

import com.cana.yundaex.common.dto.YundaexBaseResponse;

public class AsyncNotifyYDCustomerCreditLimitResult extends YundaexBaseResponse implements Serializable {

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
