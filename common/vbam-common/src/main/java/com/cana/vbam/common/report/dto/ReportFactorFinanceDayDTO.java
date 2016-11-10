package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class ReportFactorFinanceDayDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1823578195081245950L;

    /**
     *报表日期（格式：yyyy-MM-dd）
     */
    private String reportDate;
    
    /**
     * 查询选项
     */
    private String businessProduct;

    /**
     *融资余额（每一个放款信息变化后的融资余额）
     */
    private String financeBalance;

    /**
     *放款金额（新增放款信息的放款金额）
     */
    private String financeAmount;

    /**
     *应还本金
     */
    private String accountPrincipal;
    
    /**
     *应还正常利息
     */
    private String accountInterest;

    /**
     *应还正常服务费
     */
    private String accountServiceCharge;

    /**
     *应还正常展期费
     */
    private String accountExtensionCharge;

    /**
     *应还固定费用
     */
    private String accountExpense;
    
    /**
     *应还逾期本金
     */
    private String accountOverduePrincipal;

    /**
     *应还逾期利息
     */
    private String accountOverdueInterest;

    /**
     *应还逾期服务费
     */
    private String accountOverdueServiceCharge;

    /**
     *应还逾期展期费
     */
    private String accountOverdueExtensionCharge;
    
    /**
     *应还逾期本金罚息
     */
    private String accountOverduePrincipalPenalty;

    /**
     *应还逾期利息罚息
     */
    private String accountOverdueInterestPenalty;

    /**
     *应还逾期服务费罚息
     */
    private String accountOverdueServiceChargePenalty;

    /**
     *应还其他罚息
     */
    private String accountOtherPenalty;
    
    /**
     *应还费用（包括利息，服务费，展期费，罚息等）
     */
    private String accountCharge;

    /**
     *应还逾期金额（逾期的金额）
     */
    private String accountOverdue;

    /**
     *已还本金
     */
    private String paidPrincipal;
    
    /**
     *已还正常利息
     */
    private String paidInterest;

    /**
     *已还正常服务费
     */
    private String paidServiceCharge;

    /**
     *已还正常展期费
     */
    private String paidExtensionCharge;

    /**
     *已还固定费用
     */
    private String paidExpense;

    /**
     *提前还款手续费
     */
    private String paidEarlyRepaymentCharge;
    
    /**
     *已还逾期本金
     */
    private String paidOverduePrincipal;

    /**
     *已还逾期利息
     */
    private String paidOverdueInterest;

    /**
     *已还逾期服务费
     */
    private String paidOverdueServiceCharge;

    /**
     *已还逾期展期费
     */
    private String paidOverdueExtensionCharge;

    /**
     *已还逾期本金罚息
     */
    private String paidOverduePrincipalPenalty;

    /**
     *已还逾期利息罚息
     */
    private String paidOverdueInterestPenalty;

    /**
     *已还逾期服务费罚息
     */
    private String paidOverdueServiceChargePenalty;

    /**
     *已还其他罚息
     */
    private String paidOtherPenalty;

    /**
     *已还费用（包括利息，服务费，展期费，罚息等）
     */
    private String paidCharge;
    
    /**
     *已还逾期金额（逾期的金额）
     */
    private String paidOverdue;

    /**
     *当日调账本金
     */
    private String adjustPrincipal;

    /**
     *调账利息
     */
    private String adjustInterest;

    /**
     *调账服务费
     */
    private String adjustServiceCharge;

    /**
     *调账展期
     */
    private String adjustExtension;

    /**
     *调账逾期利息
     */
    private String adjustOverdueInterest;

    /**
     *调账逾期服务费
     */
    private String adjustOverdueServiceCharge;

    /**
     *调账罚息
     */
    private String adjustPenalty;

    /**
     *调账固定费用
     */
    private String adjustExpense;

    /**
     *当日调账费用
     */
    private String adjustAmount;

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(String financeBalance) {
		this.financeBalance = financeBalance;
	}

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

	public String getAccountPrincipal() {
		return accountPrincipal;
	}

	public void setAccountPrincipal(String accountPrincipal) {
		this.accountPrincipal = accountPrincipal;
	}

	public String getAccountCharge() {
		return accountCharge;
	}

	public void setAccountCharge(String accountCharge) {
		this.accountCharge = accountCharge;
	}

	public String getAccountExpense() {
		return accountExpense;
	}

	public void setAccountExpense(String accountExpense) {
		this.accountExpense = accountExpense;
	}

	public String getAccountOverdue() {
		return accountOverdue;
	}

	public void setAccountOverdue(String accountOverdue) {
		this.accountOverdue = accountOverdue;
	}

	public String getPaidPrincipal() {
		return paidPrincipal;
	}

	public void setPaidPrincipal(String paidPrincipal) {
		this.paidPrincipal = paidPrincipal;
	}

	public String getPaidCharge() {
		return paidCharge;
	}

	public void setPaidCharge(String paidCharge) {
		this.paidCharge = paidCharge;
	}

	public String getPaidExpense() {
		return paidExpense;
	}

	public void setPaidExpense(String paidExpense) {
		this.paidExpense = paidExpense;
	}

	public String getPaidOverdue() {
		return paidOverdue;
	}

	public void setPaidOverdue(String paidOverdue) {
		this.paidOverdue = paidOverdue;
	}

	public String getAdjustPrincipal() {
		return adjustPrincipal;
	}

	public void setAdjustPrincipal(String adjustPrincipal) {
		this.adjustPrincipal = adjustPrincipal;
	}

	public String getAdjustAmount() {
		return adjustAmount;
	}

	public void setAdjustAmount(String adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	public String getAccountOverduePrincipal() {
		return accountOverduePrincipal;
	}

	public void setAccountOverduePrincipal(String accountOverduePrincipal) {
		this.accountOverduePrincipal = accountOverduePrincipal;
	}

	public String getAccountOverdueInterest() {
		return accountOverdueInterest;
	}

	public void setAccountOverdueInterest(String accountOverdueInterest) {
		this.accountOverdueInterest = accountOverdueInterest;
	}

	public String getAccountOverdueServiceCharge() {
		return accountOverdueServiceCharge;
	}

	public void setAccountOverdueServiceCharge(String accountOverdueServiceCharge) {
		this.accountOverdueServiceCharge = accountOverdueServiceCharge;
	}

	public String getAccountOverdueExtensionCharge() {
		return accountOverdueExtensionCharge;
	}

	public void setAccountOverdueExtensionCharge(
			String accountOverdueExtensionCharge) {
		this.accountOverdueExtensionCharge = accountOverdueExtensionCharge;
	}

	public String getAccountInterest() {
		return accountInterest;
	}

	public void setAccountInterest(String accountInterest) {
		this.accountInterest = accountInterest;
	}

	public String getAccountServiceCharge() {
		return accountServiceCharge;
	}

	public void setAccountServiceCharge(String accountServiceCharge) {
		this.accountServiceCharge = accountServiceCharge;
	}

	public String getAccountExtensionCharge() {
		return accountExtensionCharge;
	}

	public void setAccountExtensionCharge(String accountExtensionCharge) {
		this.accountExtensionCharge = accountExtensionCharge;
	}

	public String getAccountOverduePrincipalPenalty() {
		return accountOverduePrincipalPenalty;
	}

	public void setAccountOverduePrincipalPenalty(
			String accountOverduePrincipalPenalty) {
		this.accountOverduePrincipalPenalty = accountOverduePrincipalPenalty;
	}

	public String getAccountOverdueInterestPenalty() {
		return accountOverdueInterestPenalty;
	}

	public void setAccountOverdueInterestPenalty(
			String accountOverdueInterestPenalty) {
		this.accountOverdueInterestPenalty = accountOverdueInterestPenalty;
	}

	public String getAccountOverdueServiceChargePenalty() {
		return accountOverdueServiceChargePenalty;
	}

	public void setAccountOverdueServiceChargePenalty(
			String accountOverdueServiceChargePenalty) {
		this.accountOverdueServiceChargePenalty = accountOverdueServiceChargePenalty;
	}

	public String getAccountOtherPenalty() {
		return accountOtherPenalty;
	}

	public void setAccountOtherPenalty(String accountOtherPenalty) {
		this.accountOtherPenalty = accountOtherPenalty;
	}

	public String getPaidInterest() {
		return paidInterest;
	}

	public void setPaidInterest(String paidInterest) {
		this.paidInterest = paidInterest;
	}

	public String getPaidServiceCharge() {
		return paidServiceCharge;
	}

	public void setPaidServiceCharge(String paidServiceCharge) {
		this.paidServiceCharge = paidServiceCharge;
	}

	public String getPaidExtensionCharge() {
		return paidExtensionCharge;
	}

	public void setPaidExtensionCharge(String paidExtensionCharge) {
		this.paidExtensionCharge = paidExtensionCharge;
	}

	public String getPaidEarlyRepaymentCharge() {
		return paidEarlyRepaymentCharge;
	}

	public void setPaidEarlyRepaymentCharge(String paidEarlyRepaymentCharge) {
		this.paidEarlyRepaymentCharge = paidEarlyRepaymentCharge;
	}

	public String getPaidOverduePrincipalPenalty() {
		return paidOverduePrincipalPenalty;
	}

	public void setPaidOverduePrincipalPenalty(String paidOverduePrincipalPenalty) {
		this.paidOverduePrincipalPenalty = paidOverduePrincipalPenalty;
	}

	public String getPaidOverdueInterestPenalty() {
		return paidOverdueInterestPenalty;
	}

	public void setPaidOverdueInterestPenalty(String paidOverdueInterestPenalty) {
		this.paidOverdueInterestPenalty = paidOverdueInterestPenalty;
	}

	public String getPaidOverdueServiceChargePenalty() {
		return paidOverdueServiceChargePenalty;
	}

	public void setPaidOverdueServiceChargePenalty(
			String paidOverdueServiceChargePenalty) {
		this.paidOverdueServiceChargePenalty = paidOverdueServiceChargePenalty;
	}

	public String getPaidOtherPenalty() {
		return paidOtherPenalty;
	}

	public void setPaidOtherPenalty(String paidOtherPenalty) {
		this.paidOtherPenalty = paidOtherPenalty;
	}

	public String getPaidOverduePrincipal() {
		return paidOverduePrincipal;
	}

	public void setPaidOverduePrincipal(String paidOverduePrincipal) {
		this.paidOverduePrincipal = paidOverduePrincipal;
	}

	public String getPaidOverdueInterest() {
		return paidOverdueInterest;
	}

	public void setPaidOverdueInterest(String paidOverdueInterest) {
		this.paidOverdueInterest = paidOverdueInterest;
	}

	public String getPaidOverdueServiceCharge() {
		return paidOverdueServiceCharge;
	}

	public void setPaidOverdueServiceCharge(String paidOverdueServiceCharge) {
		this.paidOverdueServiceCharge = paidOverdueServiceCharge;
	}

	public String getPaidOverdueExtensionCharge() {
		return paidOverdueExtensionCharge;
	}

	public void setPaidOverdueExtensionCharge(String paidOverdueExtensionCharge) {
		this.paidOverdueExtensionCharge = paidOverdueExtensionCharge;
	}

	public String getAdjustInterest() {
		return adjustInterest;
	}

	public void setAdjustInterest(String adjustInterest) {
		this.adjustInterest = adjustInterest;
	}

	public String getAdjustServiceCharge() {
		return adjustServiceCharge;
	}

	public void setAdjustServiceCharge(String adjustServiceCharge) {
		this.adjustServiceCharge = adjustServiceCharge;
	}

	public String getAdjustExtension() {
		return adjustExtension;
	}

	public void setAdjustExtension(String adjustExtension) {
		this.adjustExtension = adjustExtension;
	}

	public String getAdjustOverdueInterest() {
		return adjustOverdueInterest;
	}

	public void setAdjustOverdueInterest(String adjustOverdueInterest) {
		this.adjustOverdueInterest = adjustOverdueInterest;
	}

	public String getAdjustOverdueServiceCharge() {
		return adjustOverdueServiceCharge;
	}

	public void setAdjustOverdueServiceCharge(String adjustOverdueServiceCharge) {
		this.adjustOverdueServiceCharge = adjustOverdueServiceCharge;
	}

	public String getAdjustPenalty() {
		return adjustPenalty;
	}

	public void setAdjustPenalty(String adjustPenalty) {
		this.adjustPenalty = adjustPenalty;
	}

	public String getAdjustExpense() {
		return adjustExpense;
	}

	public void setAdjustExpense(String adjustExpense) {
		this.adjustExpense = adjustExpense;
	}
	
}