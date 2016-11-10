package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

/**
 * 确定还款返回的数据
 * @author tangyihong
 *
 */
public class ConfirmRepaymentResponseData implements Serializable{
	
	private static final long serialVersionUID = 5533781382439058707L;

	private LoanInformation loan;

	public LoanInformation getLoan() {
		return loan;
	}

	public void setLoan(LoanInformation loan) {
		this.loan = loan;
	}

}
