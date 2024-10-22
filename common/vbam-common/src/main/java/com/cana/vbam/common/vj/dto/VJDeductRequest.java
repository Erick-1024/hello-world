package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJDeductRequest implements Serializable{
	
	private static final long serialVersionUID = 2868156500467683951L;

	private String canaTranSeq;
	
	private String customerName;
	
	private String identityCardNo;
	
	private String planId;
	
	private long amount;
	
	private boolean allowPartialDeduct = false;
	
	private boolean overdue;

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}

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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public boolean isAllowPartialDeduct() {
		return allowPartialDeduct;
	}

	public void setAllowPartialDeduct(boolean allowPartialDeduct) {
		this.allowPartialDeduct = allowPartialDeduct;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public boolean isOverdue() {
		return overdue;
	}

	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}

}
