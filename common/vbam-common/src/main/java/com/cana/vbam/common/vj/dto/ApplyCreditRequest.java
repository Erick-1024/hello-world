package com.cana.vbam.common.vj.dto;

public class ApplyCreditRequest extends CustomerData{
	
	private static final long serialVersionUID = 839413576576077870L;

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
