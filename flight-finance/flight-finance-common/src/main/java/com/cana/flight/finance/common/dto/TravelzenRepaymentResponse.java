package com.cana.flight.finance.common.dto;

import java.util.List;

public class TravelzenRepaymentResponse extends TravelzenBaseResponse {

	private static final long serialVersionUID = 1L;
	
	private List<RepaymentDTO> repaymentData;

	public List<RepaymentDTO> getRepaymentData() {
		return repaymentData;
	}

	public void setRepaymentData(List<RepaymentDTO> repaymentData) {
		this.repaymentData = repaymentData;
	}

}
