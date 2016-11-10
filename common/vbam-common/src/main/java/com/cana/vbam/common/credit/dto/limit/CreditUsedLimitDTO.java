package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;

public class CreditUsedLimitDTO implements Serializable{
	
	private static final long serialVersionUID = -4680602167013971975L;

	private String memberId;
	
	private String companyName;
	
	private String outCustomerName;
	
	private Long usedLimit;
	
	private String productId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
