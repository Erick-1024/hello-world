/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class LoanInfoRedisDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4051339122236872845L;
	
	/**
	 * 主键
	 */
	private String id;

	/**
    *放款编号
    */
    private String loanNo;

    /**
    *业务合同号
    */
    private String businessContractNo;
    
    /**
     *保理商id
     */
    private String factorId;

    /**
    *保理商公司名称（列表显示用）
    */
    private String factorCompany;
    
    /**
     *融资客户id 
     */
    private String financeId;
    
    /**
    *融资客户公司名称
    */
    private String financeCompany;
    /**
     * 融资客户外部帐户名称
     */
    private String outCustomerName;

    /**
    *凭证号码
    */
    private String voucherNo;

    /**
    *币种 
    */
    private String currency;

    /**
    *业务产品
    */
    private String businessProduct;

    /**
    *应收账款金额
    */
    private String receivablesAmount;

    /**
    *应收账款余额
    */
    private String receivablesBalance;

    /**
    *融资金额
    */
    private String financeAmount;

    /**
    *融资余额 = 融资金额 - 所有计划（已还本金+已还愈期本金）
    */
    private String financeBalance;

    /**
    *利率
    */
    private String interestRate;

    /**
    *关联的账号Id
    */
    private String accountId;
    
	/**
    *账号
    */
    private String accountNo;

    /**
    *放款日
    */
    private String loanDate;

    /**
    *到期日
    */
    private String dueDate;

    /**
    *还款方式
    */
    private String repaymentMethod;

    /**
    *校验状态
    */
    private String verifyStatus;

    /**
    *检验未通过原因
    */
    private String verifyFailReason;

    /**
     * 期限单位
     */
    private String loanPeriodUnit;
    
    /**
     * 贷款期限
     */
    private String loanPeriod;
    
    /**
     * 期数
     */
    private String repaymentPeriod;
    
    /**
     * 当前监管关系Id
     */
    private String accountSupervisionId;
    
    /**
     * 利率单位
     */
    private String interestRateUnit;
    
    /**
    *结清状态（已结清、未结清）
    */
    private String settleStatus;
    
    /**
     *主动还款的状态(已还款，已完成)
     */
    private String activeRepaymentStatus;
    
    /**
     * 核心企业Id
     */
    private String coreCompanyId;
    
    /**
     * 核心企业姓名
     */
    private String coreCompanyName;
    
    /**
     * 线下还款&调账权限
     */
    private boolean accessToAdjustAndOfflineRepayment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
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

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getReceivablesAmount() {
		return receivablesAmount;
	}

	public void setReceivablesAmount(String receivablesAmount) {
		this.receivablesAmount = receivablesAmount;
	}

	public String getReceivablesBalance() {
		return receivablesBalance;
	}

	public void setReceivablesBalance(String receivablesBalance) {
		this.receivablesBalance = receivablesBalance;
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

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getVerifyFailReason() {
		return verifyFailReason;
	}

	public void setVerifyFailReason(String verifyFailReason) {
		this.verifyFailReason = verifyFailReason;
	}

	public String getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(String loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(String repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public String getAccountSupervisionId() {
		return accountSupervisionId;
	}

	public void setAccountSupervisionId(String accountSupervisionId) {
		this.accountSupervisionId = accountSupervisionId;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
	
	public String getActiveRepaymentStatus() {
	    return activeRepaymentStatus;
	}

	public void setActiveRepaymentStatus(String activeRepaymentStatus) {
	    this.activeRepaymentStatus = activeRepaymentStatus;
	}

	public String getCoreCompanyId() {
		return coreCompanyId;
	}

	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}
	
	public boolean isAccessToAdjustAndOfflineRepayment() {
		return accessToAdjustAndOfflineRepayment;
	}

	public void setAccessToAdjustAndOfflineRepayment(
			boolean accessToAdjustAndOfflineRepayment) {
		this.accessToAdjustAndOfflineRepayment = accessToAdjustAndOfflineRepayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loanNo == null) ? 0 : loanNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanInfoRedisDTO other = (LoanInfoRedisDTO) obj;
		if (loanNo == null) {
			if (other.loanNo != null)
				return false;
		} else if (!loanNo.equals(other.loanNo))
			return false;
		return true;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

}