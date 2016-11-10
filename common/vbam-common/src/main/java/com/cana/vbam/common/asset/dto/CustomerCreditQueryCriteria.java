package com.cana.vbam.common.asset.dto;

import java.util.List;

import com.cana.vbam.common.dto.Pagination;

/**
 * @author hu
 *
 */
public class CustomerCreditQueryCriteria extends Pagination {

	private static final long serialVersionUID = 5399296296651855756L;

	private String customerName;
	
	private boolean isApplyCredit;
	
	private String userId;

	private String factorId;
	
	private List<String> customerIds;
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public boolean isApplyCredit() {
		return isApplyCredit;
	}

	public void setIsApplyCredit(boolean isApplyCredit) {
		this.isApplyCredit = isApplyCredit;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public List<String> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}
}
