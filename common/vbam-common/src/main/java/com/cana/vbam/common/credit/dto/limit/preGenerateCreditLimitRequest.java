package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;

public class preGenerateCreditLimitRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerId;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
