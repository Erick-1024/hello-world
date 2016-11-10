package com.cana.vbam.common.asset.loan.dto;

import com.cana.vbam.common.dto.Pagination;

public class AssetLoanPaidListRequest extends Pagination {

	private static final long serialVersionUID = -1741550434662742866L;
	
	private String loanId;
	
	private String userId;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
