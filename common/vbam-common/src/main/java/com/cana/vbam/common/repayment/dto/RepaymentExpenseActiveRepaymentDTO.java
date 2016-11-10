package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class RepaymentExpenseActiveRepaymentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4488402322722398873L;

	/**
	 * 费用列表Id
	 */
	private String id;
	
	/**
	 * 放款编号
	 */
	private String loanNo;
	
	/**
	 * 放款信息Id(平台流水号)
	 */
	private String loanInfoId;
	
	/**
	 * 费用名目
	 */
	private String expenseSubject;

	/**
	 * 还款日
	 */
	private String repaymentDate;
	
	/**
	 * 应还金额
	 */
	private String repaymentAmount;
	
	/**
	 * 已还金额
	 */
	private String paidAmount;
	
	/**
	 * 结清状态
	 */
	private String settleStatus;
	
	/**
     * 是否可以还款
     */
    private boolean isRepaied;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getExpenseSubject() {
		return expenseSubject;
	}

	public void setExpenseSubject(String expenseSubject) {
		this.expenseSubject = expenseSubject;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(String repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public boolean isRepaied() {
		return isRepaied;
	}

	public void setRepaied(boolean isRepaied) {
		this.isRepaied = isRepaied;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
	
}
