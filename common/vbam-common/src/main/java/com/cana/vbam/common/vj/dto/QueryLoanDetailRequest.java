package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class QueryLoanDetailRequest implements Serializable {

	private static final long serialVersionUID = -1746678444455681888L;

	private String loanId;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	
}
