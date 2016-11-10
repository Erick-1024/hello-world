/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
public class RepaymentPlanActiveRepaymentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3614884499486110257L;

	/**
    *主键（唯一判断，保理商+还款编号）
    */
    private String id;
    
    /**
	 * 放款信息Id(平台流水号)
	 */
	private String loanInfoId;
	
	/**
	 * 业务产品名称
	 */
	private String businessProduct;
	
	/**
	 * 保理商名称
	 */
	private String factorCompany;
	
	/**
	 * 融资客户名称
	 */
	private String financeCompany;
	
	/**
	 * 融资客户联系电话
	 */
	private String financeTel;
	
	/**
	 * 核心企业名称
	 */
	private String coreCompanyName;
	
	/**
	 * 放款编号
	 */
	private String loanNo;
	
	/**
	 * 期数
	 */
	private String repaymentPeriod;
	
	/**
	 * 融资金额
	 */
	private String financeAmount;
	
	/**
	 * 融资余额
	 */
	private String financeBalance;
	
	/**
	 * 起息日
	 */
	private String valueDate;
	
	/**
	 * 结息日
	 */
	private String settleInterestDate;
	
	/**
	 * 还款日
	 */
	private String repaymentDate;

	/**
	 * 应还本金
	 */
	private String accountPrincipal;
	
	/**
     * 截止到今日应还利息
     */
    private String accountInterestUntilNow;
	
	/**
	 * 应还利息
	 */
	private String accountInterest;
	
	/**
	 * 应还服务费
	 */
	private String accountServiceCharge;
	
	/**
	 * 应还展期费用
	 */
	private String accountExtensionCharge;
	
	/**
	 * 已还正常本金
	 */
	private String paidNormalPrincipal;
	
	/**
	 * 已还正常利息
	 */
	private String paidNormalInterest;
	
	/**
	 * 已还正常服务费
	 */
	private String paidNormalServiceCharge;
	
	/**
	 * 逾期本金
	 */
	private String overduePrincipal;
	
	/**
	 * 逾期利息
	 */
	private String overdueInterest;
	
	/**
	 * 逾期管理费
	 */
	private String overdueManageCharge;
	
	/**
	 * 逾期服务费
	 */
	private String overdueServiceCharge;
	
	/**
	 * 已还逾期本金
	 */
	private String paidOverduePrincipal;

	/**
	 * 已还逾期利息
	 */
	private String paidOverdueInterest;
	
	/**
	 * 已还逾期服务费
	 */
	private String paidOverdueServiceCharge;
	
	/**
	 * 已还展期
	 */
	private String paidExtensionCharge;
    
	/**
	 * 已还逾期管理费
	 */
	private String paidOverdueManageCharge;
    
	/**
     *保理商Id
     */
    private String factorId;
    
    /**
     * 应还总金额=应还本金+应还利息+应还服务费+应还展期费用+已还提前还款手续费
     */
    private String accountTotalAmount;
    
    /**
     * 是否可以还款
     */
    private boolean isRepaied;
    
    /**
     * 结清状态
     */
    private String settleStatus;
    
    /**
     * 逾期展期费用
     */
    private String overdueExtensionCharge;

    /**
     * 展期天数
     */
    private int extensionDays;

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

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

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getFactorCompany() {
		return factorCompany;
	}

	public void setFactorCompany(String factorCompany) {
		this.factorCompany = factorCompany;
	}

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(String repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

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

	public String getPaidNormalPrincipal() {
		return paidNormalPrincipal;
	}

	public void setPaidNormalPrincipal(String paidNormalPrincipal) {
		this.paidNormalPrincipal = paidNormalPrincipal;
	}

	public String getPaidNormalInterest() {
		return paidNormalInterest;
	}

	public void setPaidNormalInterest(String paidNormalInterest) {
		this.paidNormalInterest = paidNormalInterest;
	}

	public String getPaidNormalServiceCharge() {
		return paidNormalServiceCharge;
	}

	public void setPaidNormalServiceCharge(String paidNormalServiceCharge) {
		this.paidNormalServiceCharge = paidNormalServiceCharge;
	}

	public String getOverduePrincipal() {
		return overduePrincipal;
	}

	public void setOverduePrincipal(String overduePrincipal) {
		this.overduePrincipal = overduePrincipal;
	}

	public String getOverdueInterest() {
		return overdueInterest;
	}

	public void setOverdueInterest(String overdueInterest) {
		this.overdueInterest = overdueInterest;
	}

	public String getOverdueServiceCharge() {
		return overdueServiceCharge;
	}

	public void setOverdueServiceCharge(String overdueServiceCharge) {
		this.overdueServiceCharge = overdueServiceCharge;
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

	public String getPaidExtensionCharge() {
		return paidExtensionCharge;
	}

	public void setPaidExtensionCharge(String paidExtensionCharge) {
		this.paidExtensionCharge = paidExtensionCharge;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getAccountTotalAmount() {
		return accountTotalAmount;
	}

	public void setAccountTotalAmount(String accountTotalAmount) {
		this.accountTotalAmount = accountTotalAmount;
	}

	public boolean isRepaied() {
		return isRepaied;
	}

	public void setRepaied(boolean isRepaied) {
		this.isRepaied = isRepaied;
	}

	public String getOverdueExtensionCharge() {
		return overdueExtensionCharge;
	}

	public void setOverdueExtensionCharge(String overdueExtensionCharge) {
		this.overdueExtensionCharge = overdueExtensionCharge;
	}

	public String getAccountInterestUntilNow() {
		return accountInterestUntilNow;
	}

	public void setAccountInterestUntilNow(String accountInterestUntilNow) {
		this.accountInterestUntilNow = accountInterestUntilNow;
	}

	public String getOverdueManageCharge() {
		return overdueManageCharge;
	}

	public void setOverdueManageCharge(String overdueManageCharge) {
		this.overdueManageCharge = overdueManageCharge;
	}

	public String getFinanceTel() {
		return financeTel;
	}

	public void setFinanceTel(String financeTel) {
		this.financeTel = financeTel;
	}

	public int getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(int extensionDays) {
		this.extensionDays = extensionDays;
	}
	
	public String getPaidOverdueManageCharge() {
		return paidOverdueManageCharge;
	}

	public void setPaidOverdueManageCharge(String paidOverdueManageCharge) {
		this.paidOverdueManageCharge = paidOverdueManageCharge;
	}
}