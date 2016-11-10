package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class QueryTranStateResponseData implements Serializable {

	private static final long serialVersionUID = -3223650485889179096L;

	private LoanInformation loan;

	public LoanInformation getLoan() {
		return loan;
	}

	public void setLoan(LoanInformation loan) {
		this.loan = loan;
	}
	
}
