package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class LoanAuditDTO implements Serializable {

	private static final long serialVersionUID = 3303070341818234237L;

	/**
     *交易对手名称
     */
    private String counterpartyName;
	
	/**
     *日期
     */
    private String date;
	
	/**
     *申请金额
     */
    private Long applyAmount;

    /**
     *实际放款金额
     */
    private Long loanAmount;

    /**
     *放款状态
     */
    private String loanState;
    
    private String loanStateDesc;

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(Long applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanState() {
		return loanState;
	}

	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}

	public String getLoanStateDesc() {
		return loanStateDesc;
	}

	public void setLoanStateDesc(String loanStateDesc) {
		this.loanStateDesc = loanStateDesc;
	}
	
}
