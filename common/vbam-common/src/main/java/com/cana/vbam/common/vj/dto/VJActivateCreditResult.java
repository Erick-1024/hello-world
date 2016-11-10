package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJActivateCreditResult implements Serializable{
	
	private static final long serialVersionUID = 8340553283527972627L;

	private String canaCustomerId;
	
	private long creditLimit;
	
	private long availableCreditLimit;
	
	private long usedCreditLimit;

	public String getCanaCustomerId() {
		return canaCustomerId;
	}

	public void setCanaCustomerId(String canaCustomerId) {
		this.canaCustomerId = canaCustomerId;
	}

	public long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public long getAvailableCreditLimit() {
		return availableCreditLimit;
	}

	public void setAvailableCreditLimit(long availableCreditLimit) {
		this.availableCreditLimit = availableCreditLimit;
	}

	public long getUsedCreditLimit() {
		return usedCreditLimit;
	}

	public void setUsedCreditLimit(long usedCreditLimit) {
		this.usedCreditLimit = usedCreditLimit;
	}

}
