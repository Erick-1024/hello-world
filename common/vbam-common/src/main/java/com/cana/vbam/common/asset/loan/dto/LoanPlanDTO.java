package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;

public class LoanPlanDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String financeBalance; // 融资余额
	private String valueDate; // 起息日
	private String settleInterestDate; // 结息日
	private String repaymentDate; // 还款日
	private String accountPrincipal; // 应还本金
	private String accountInterest; // 应还利息
	private String accountOverdue; // 应还逾期费
	private String accountAmount; // 应还总金额，新增、修改、导入时忽略该字段
	private String lastPaidDate; // 最后入账日期
	private String settleStatus; // 结清状态，英文

	private String settleStatusDesc; // 结清状态，中文，仅显示使用，新增、修改、导入时忽略该字段
    private Long paidPrincipal; //已还本金，新增、修改、导入时忽略该字段
    private Long paidInterest; // 已还利息，新增、修改、导入时忽略该字段
    private Long paidOverdue; // 已还逾期，新增、修改、导入时忽略该字段
    private String forwardDays; // 提前天数，新增、修改、导入时忽略该字段
	private String overdueDays; // 逾期天数，新增、修改、导入时忽略该字段
	private String id; // 还款计划ID，新增、修改、导入时忽略该字段
	private boolean existPaidInfo; //是否存在还款
	
	public String getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(String financeBalance) {
		this.financeBalance = financeBalance;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getSettleInterestDate() {
		return settleInterestDate;
	}

	public void setSettleInterestDate(String settleInterestDate) {
		this.settleInterestDate = settleInterestDate;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getAccountPrincipal() {
		return accountPrincipal;
	}

	public void setAccountPrincipal(String accountPrincipal) {
		this.accountPrincipal = accountPrincipal;
	}

	public String getAccountInterest() {
		return accountInterest;
	}

	public void setAccountInterest(String accountInterest) {
		this.accountInterest = accountInterest;
	}

	public String getAccountOverdue() {
		return accountOverdue;
	}

	public void setAccountOverdue(String accountOverdue) {
		this.accountOverdue = accountOverdue;
	}

	public String getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(String accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getLastPaidDate() {
		return lastPaidDate;
	}

	public void setLastPaidDate(String lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getSettleStatusDesc() {
		return settleStatusDesc;
	}

	public void setSettleStatusDesc(String settleStatusDesc) {
		this.settleStatusDesc = settleStatusDesc;
	}

	public Long getPaidPrincipal() {
		return paidPrincipal;
	}

	public void setPaidPrincipal(Long paidPrincipal) {
		this.paidPrincipal = paidPrincipal;
	}

	public Long getPaidInterest() {
		return paidInterest;
	}

	public void setPaidInterest(Long paidInterest) {
		this.paidInterest = paidInterest;
	}

	public Long getPaidOverdue() {
		return paidOverdue;
	}

	public void setPaidOverdue(Long paidOverdue) {
		this.paidOverdue = paidOverdue;
	}

	public String getForwardDays() {
		return forwardDays;
	}

	public void setForwardDays(String forwardDays) {
		this.forwardDays = forwardDays;
	}

	public String getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(String overdueDays) {
		this.overdueDays = overdueDays;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isExistPaidInfo() {
		return existPaidInfo;
	}

	public void setExistPaidInfo(boolean existPaidInfo) {
		this.existPaidInfo = existPaidInfo;
	}

}
