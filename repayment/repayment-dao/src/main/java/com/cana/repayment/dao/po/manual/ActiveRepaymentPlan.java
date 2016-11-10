package com.cana.repayment.dao.po.manual;

import java.io.Serializable;

public class ActiveRepaymentPlan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6243689674803326335L;
	
	/**
	 * 还款计划Id
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
	private int repaymentPeriod;
	
	/**
	 * 融资金额
	 */
	private long financeAmount;
	
	/**
	 * 融资余额
	 */
	private long financeBalance;
	
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
	private long accountPrincipal;
	
	/**
	 * 应还利息
	 */
	private long accountInterest;
	
	/**
	 * 应还服务费
	 */
	private long accountServiceCharge;
	
	/**
	 * 应还展期费用
	 */
	private long accountExtensionCharge;
	
	/**
	 * 已还正常本金
	 */
	private long paidNormalPrincipal;
	
	/**
	 * 已还正常利息
	 */
	private long paidNormalInterest;
	
	/**
	 * 已还正常服务费
	 */
	private long paidNormalServiceCharge;
	
	/**
	 * 逾期本金
	 */
	private long overduePrincipal;
	
	/**
	 * 逾期利息
	 */
	private long overdueInterest;
	
	/**
	 * 逾期管理费
	 */
	private long overdueManageCharge;
	
	/**
	 * 逾期服务费
	 */
	private long overdueServiceCharge;
	
	/**
	 * 已还逾期本金
	 */
	private long paidOverduePrincipal;

	/**
	 * 已还逾期利息
	 */
	private long paidOverdueInterest;
	
	/**
	 * 已还逾期服务费
	 */
	private long paidOverdueServiceCharge;
	
	/**
	 * 已还展期
	 */
	private long paidExtensionCharge;
	
	/**
	 * 已还逾期管理费
	 */
	private long paidOverdueManageCharge;

	/**
	 * 结清状态
	 */
	private String settleStatus;

	/**
     *本期展期天数，即MAX（节假日因素产生的展期天数，放款的展期天数）
     */
    private int extensionDays;
	
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

	public int getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(int repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public long getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(long financeAmount) {
		this.financeAmount = financeAmount;
	}

	public long getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(long financeBalance) {
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

	public long getOverduePrincipal() {
		return overduePrincipal;
	}

	public void setOverduePrincipal(long overduePrincipal) {
		this.overduePrincipal = overduePrincipal;
	}

	public long getOverdueServiceCharge() {
		return overdueServiceCharge;
	}

	public void setOverdueServiceCharge(long overdueServiceCharge) {
		this.overdueServiceCharge = overdueServiceCharge;
	}

	public long getAccountExtensionCharge() {
		return accountExtensionCharge;
	}

	public void setAccountExtensionCharge(long accountExtensionCharge) {
		this.accountExtensionCharge = accountExtensionCharge;
	}

	public long getPaidOverduePrincipal() {
		return paidOverduePrincipal;
	}

	public void setPaidOverduePrincipal(long paidOverduePrincipal) {
		this.paidOverduePrincipal = paidOverduePrincipal;
	}

	public long getPaidOverdueInterest() {
		return paidOverdueInterest;
	}

	public void setPaidOverdueInterest(long paidOverdueInterest) {
		this.paidOverdueInterest = paidOverdueInterest;
	}

	public long getPaidOverdueServiceCharge() {
		return paidOverdueServiceCharge;
	}

	public void setPaidOverdueServiceCharge(long paidOverdueServiceCharge) {
		this.paidOverdueServiceCharge = paidOverdueServiceCharge;
	}

	public long getPaidExtensionCharge() {
		return paidExtensionCharge;
	}

	public void setPaidExtensionCharge(long paidExtensionCharge) {
		this.paidExtensionCharge = paidExtensionCharge;
	}

	public long getAccountPrincipal() {
		return accountPrincipal;
	}

	public void setAccountPrincipal(long accountPrincipal) {
		this.accountPrincipal = accountPrincipal;
	}

	public long getAccountInterest() {
		return accountInterest;
	}

	public void setAccountInterest(long accountInterest) {
		this.accountInterest = accountInterest;
	}

	public long getAccountServiceCharge() {
		return accountServiceCharge;
	}

	public void setAccountServiceCharge(long accountServiceCharge) {
		this.accountServiceCharge = accountServiceCharge;
	}

	public long getPaidNormalPrincipal() {
		return paidNormalPrincipal;
	}

	public void setPaidNormalPrincipal(long paidNormalPrincipal) {
		this.paidNormalPrincipal = paidNormalPrincipal;
	}

	public long getPaidNormalInterest() {
		return paidNormalInterest;
	}

	public void setPaidNormalInterest(long paidNormalInterest) {
		this.paidNormalInterest = paidNormalInterest;
	}

	public long getPaidNormalServiceCharge() {
		return paidNormalServiceCharge;
	}

	public void setPaidNormalServiceCharge(long paidNormalServiceCharge) {
		this.paidNormalServiceCharge = paidNormalServiceCharge;
	}

	public long getOverdueInterest() {
		return overdueInterest;
	}

	public void setOverdueInterest(long overdueInterest) {
		this.overdueInterest = overdueInterest;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public long getOverdueManageCharge() {
		return overdueManageCharge;
	}

	public void setOverdueManageCharge(long overdueManageCharge) {
		this.overdueManageCharge = overdueManageCharge;
	}

	public int getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(int extensionDays) {
		this.extensionDays = extensionDays;
	}
	
	public long getPaidOverdueManageCharge() {
		return paidOverdueManageCharge;
	}

	public void setPaidOverdueManageCharge(long paidOverdueManageCharge) {
		this.paidOverdueManageCharge = paidOverdueManageCharge;
	}
}
