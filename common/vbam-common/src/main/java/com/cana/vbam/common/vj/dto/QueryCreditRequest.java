package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class QueryCreditRequest implements Serializable{
	
	private static final long serialVersionUID = 3563133350544689249L;

	private String customerName;
	
	private String identityCardNo;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}


}
