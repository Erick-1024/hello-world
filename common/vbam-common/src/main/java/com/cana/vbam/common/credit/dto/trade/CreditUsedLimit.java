package com.cana.vbam.common.credit.dto.trade;

import java.io.Serializable;

public class CreditUsedLimit implements Serializable{

	private static final long serialVersionUID = 1L;

	private String memberId;
	
	private String outCustomerId;
	
	private Long usedLimit;

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

	public Long getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(Long usedLimit) {
		this.usedLimit = usedLimit;
	}
	
}
