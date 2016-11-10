package com.cana.repayment.dao.po.manual;

import java.io.Serializable;

public class ActiveRepaymentExpense implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3000445438902660763L;

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
	private long repaymentAmount;
	
	/**
	 * 已还金额
	 */
	private long paidAmount;
	
	/**
	 * 结清状态
	 */
	private String settleStatus;

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

	public long getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(long repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public long getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(long paidAmount) {
		this.paidAmount = paidAmount;
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
