package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetSource;

public class AssetInPoolDTO implements Serializable {

	private static final long serialVersionUID = 6013417938871001539L;

	/**
	 * 放款编号
	 */
	private String loanNo;

	/**
	 * 业务合同号
	 */
	private String businessContractNo;

	/**
	 * 借款人名称
	 */
	private String customerName;

	/**
	 * 借款人类型
	 */
	private String customerEconomicCategory;

	/**
	 * 交易对手名称
	 */
	private String counterparty;

	/**
	 * 交易对手类型
	 */
	private String counterpartyEconomicCategory;

	/**
	 * 借款人所在地区
	 */
	private String customerCity;

	/**
	 * 借款人所属行业
	 */
	private String customerIndustry;

	/**
	 * 交易对手所在地区
	 */
	private String counterpartyCity;

	/**
	 * 交易对手所属行业
	 */
	private String counterpartyIndustry;

	/**
	 * 授信额度
	 */
	private String creditLimit;

	/**
	 * 授信可用余额
	 */
	private String creditBalance;

	/**
	 * 交易对手非承保额度
	 */
	private String counterpartyLimit;

	/**
	 * 交易对手非承保余额
	 */
	private String counterpartyBalance;

	/**
	 * 应收账款金额
	 */
	private String invoiceAmount;

	/**
	 * 应收账款余额
	 */
	private String invoiceBalance;

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
	private String loanDate;

	/**
	 * 到期日
	 */
	private String dueDate;

	/**
	 * 还款方式
	 */
	private String repaymentMethod;

	/**
	 * 利率类型
	 */
	private String interestRateUnit;

	/**
	 * 利率
	 */
	private String interestRate;

	/**
	 * 期限
	 */
	private String loanPeriod;

	/**
	 * 所属专项计划编号
	 */
	private String specialProgramId;

	/**
	 * 专项计划名称
	 */
	private String specialProgramName;

	/**
	 * 资产池状态，未入池，备选池，入池
	 */
	private UnderlyingAssetPoolStatus assetPoolStatus;

	/**
	 * 应还利息
	 */
	private String accountInterest;

	/**
	 * 应还逾期
	 */
	private String accountOverdue;

	/**
	 * 应还本金
	 */
	private String accountPrincipal;

	/**
	 * 已还利息
	 */
	private String paidInterest;

	/**
	 * 已还逾期
	 */
	private String paidOverdue;

	/**
	 * 已还本金
	 */
	private String paidPrincipal;

	/**
	 * 应还收入 = accountInterest + accountOverdue
	 */
	private String accountIncome;

	/**
	 * 应还总额 = accountIncome + accountPrincipal
	 */
	private String accountAmount;

	/**
	 * 已还收入 = paidInterest + paidOverdue
	 */
	private String paidIncome;

	/**
	 * 已还总额 = paidIncome + paidPrincipal
	 */
	private String paidAmount;

	/**
	 * 未偿总额 = max(accountAmount - paidAmount, 0)
	 */
	private String unpaidAmount;

	/**
	 * 基础资产类型
	 */
	private UnderlyingAssetSource assetSource;

	public UnderlyingAssetSource getAssetSource() {
		return assetSource;
	}

	public void setAssetSource(UnderlyingAssetSource assetSource) {
		this.assetSource = assetSource;
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

	public String getAccountPrincipal() {
		return accountPrincipal;
	}

	public void setAccountPrincipal(String accountPrincipal) {
		this.accountPrincipal = accountPrincipal;
	}

	public String getPaidInterest() {
		return paidInterest;
	}

	public void setPaidInterest(String paidInterest) {
		this.paidInterest = paidInterest;
	}

	public String getPaidOverdue() {
		return paidOverdue;
	}

	public void setPaidOverdue(String paidOverdue) {
		this.paidOverdue = paidOverdue;
	}

	public String getPaidPrincipal() {
		return paidPrincipal;
	}

	public void setPaidPrincipal(String paidPrincipal) {
		this.paidPrincipal = paidPrincipal;
	}

	public String getAccountIncome() {
		return accountIncome;
	}

	public void setAccountIncome(String accountIncome) {
		this.accountIncome = accountIncome;
	}

	public String getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(String accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getPaidIncome() {
		return paidIncome;
	}

	public void setPaidIncome(String paidIncome) {
		this.paidIncome = paidIncome;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getUnpaidAmount() {
		return unpaidAmount;
	}

	public void setUnpaidAmount(String unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(String creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getCounterpartyLimit() {
		return counterpartyLimit;
	}

	public void setCounterpartyLimit(String counterpartyLimit) {
		this.counterpartyLimit = counterpartyLimit;
	}

	public String getCounterpartyBalance() {
		return counterpartyBalance;
	}

	public void setCounterpartyBalance(String counterpartyBalance) {
		this.counterpartyBalance = counterpartyBalance;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceBalance() {
		return invoiceBalance;
	}

	public void setInvoiceBalance(String invoiceBalance) {
		this.invoiceBalance = invoiceBalance;
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

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getSpecialProgramId() {
		return specialProgramId;
	}

	public void setSpecialProgramId(String specialProgramId) {
		this.specialProgramId = specialProgramId;
	}

	public String getSpecialProgramName() {
		return specialProgramName;
	}

	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
	}

	public UnderlyingAssetPoolStatus getAssetPoolStatus() {
		return assetPoolStatus;
	}

	public void setAssetPoolStatus(UnderlyingAssetPoolStatus assetPoolStatus) {
		this.assetPoolStatus = assetPoolStatus;
	}

	public String getCustomerEconomicCategory() {
		return customerEconomicCategory;
	}

	public void setCustomerEconomicCategory(String customerEconomicCategory) {
		this.customerEconomicCategory = customerEconomicCategory;
	}

	public String getCounterpartyEconomicCategory() {
		return counterpartyEconomicCategory;
	}

	public void setCounterpartyEconomicCategory(String counterpartyEconomicCategory) {
		this.counterpartyEconomicCategory = counterpartyEconomicCategory;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerIndustry() {
		return customerIndustry;
	}

	public void setCustomerIndustry(String customerIndustry) {
		this.customerIndustry = customerIndustry;
	}

	public String getCounterpartyCity() {
		return counterpartyCity;
	}

	public void setCounterpartyCity(String counterpartyCity) {
		this.counterpartyCity = counterpartyCity;
	}

	public String getCounterpartyIndustry() {
		return counterpartyIndustry;
	}

	public void setCounterpartyIndustry(String counterpartyIndustry) {
		this.counterpartyIndustry = counterpartyIndustry;
	}

}
