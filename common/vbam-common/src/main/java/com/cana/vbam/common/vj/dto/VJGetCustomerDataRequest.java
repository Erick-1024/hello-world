package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJGetCustomerDataRequest implements Serializable{
	
	private static final long serialVersionUID = 8689824415192872048L;

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
