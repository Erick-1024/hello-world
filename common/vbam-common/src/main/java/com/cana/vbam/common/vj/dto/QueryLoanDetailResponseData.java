package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class QueryLoanDetailResponseData implements Serializable {

	private static final long serialVersionUID = -6986214440131633263L;

	private LoanInformation loan;

	public LoanInformation getLoanInformation() {
		return loan;
	}

	public void setLoanInformation(LoanInformation loan) {
		this.loan = loan;
	}
	
}
