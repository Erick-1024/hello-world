package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class ConfirmLoanResponseData implements Serializable {

	private static final long serialVersionUID = -6910832662499034191L;
	
	private LoanInformation loan;

	public LoanInformation getLoan() {
		return loan;
	}

	public void setLoan(LoanInformation loan) {
		this.loan = loan;
	}
	
}
