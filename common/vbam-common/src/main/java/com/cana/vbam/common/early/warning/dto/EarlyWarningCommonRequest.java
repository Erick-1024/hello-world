package com.cana.vbam.common.early.warning.dto;

import com.cana.vbam.common.dto.Pagination;

public class EarlyWarningCommonRequest extends Pagination {

	private static final long serialVersionUID = 333824377729939987L;

	private String memberId;
	
	private String companyName;
	
	private String outCustomerId;
	
	private String outCustomerName;
	
	private String earlyWarningLevel;
	
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

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getEarlyWarningLevel() {
		return earlyWarningLevel;
	}

	public void setEarlyWarningLevel(String earlyWarningLevel) {
		this.earlyWarningLevel = earlyWarningLevel;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
