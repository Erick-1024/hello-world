package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class QueryRepaymentSummaryResponseDTO implements Serializable{

	private static final long serialVersionUID = -4325018752585789356L;

	private RepaymentAmount repaymentAmount;

	public RepaymentAmount getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(RepaymentAmount repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	
}
