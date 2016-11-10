package com.cana.vbam.common.credit.dto.limit;

import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;

public class QueryCreditLimitResponse extends TravelzenBaseResponse {

	private static final long serialVersionUID = 1L;

	private Long totalLimit;
	
	private Long unusedLimit;
	
	private String status;

	public Long getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(Long totalLimit) {
		this.totalLimit = totalLimit;
	}

	public Long getUnusedLimit() {
		return unusedLimit;
	}

	public void setUnusedLimit(Long unusedLimit) {
		this.unusedLimit = unusedLimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
