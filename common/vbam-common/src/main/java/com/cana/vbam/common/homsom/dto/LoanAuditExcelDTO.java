package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class LoanAuditExcelDTO implements Serializable {

	/**
	 *日期
	 */
	private String date;
	
	/**
	 *申请金额
	 */
	private String applyAmount;
	
    /**
     *实际放款金额
     */
    private String loanAmount;

    private String loanStateDesc;
	
	private static final long serialVersionUID = -4296163800823577211L;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanStateDesc() {
		return loanStateDesc;
	}

	public void setLoanStateDesc(String loanStateDesc) {
		this.loanStateDesc = loanStateDesc;
	}

}
