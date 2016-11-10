package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;

public class CreditUsedLimitInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String creditLimitId;
	
	private String memberId;
	
	private String outCustomerId;
	
	private String companyName;
	
	private String outCustomerName;
	
	private Long usedLimit;

	public String getCreditLimitId() {
		return creditLimitId;
	}

	public void setCreditLimitId(String creditLimitId) {
		this.creditLimitId = creditLimitId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public Long getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(Long usedLimit) {
		this.usedLimit = usedLimit;
	}
	
}