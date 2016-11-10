package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

import com.cana.vbam.common.utils.MoneyArithUtil;

public class AccountRepaymentPlanRedisDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6946438797007727669L;
	
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 放款编号
	 */
	private String loanNo;

	/**
	 * 期数
	 */
	private String repaymentPeriod;
	
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
	 * 应还利息
	 */
	private String accountInterest;

	/**
	 * 应还服务费
	 */
	private String accountServiceCharge;
	
	/**
	 * 逾期本金
	 */
	private String overduePrincipal;

	/**
	 * 逾期利息
	 */
	private String overdueInterest;

	/**
	 * 逾期服务费
	 */
	private String overdueServiceCharge;
	
	/**
	 * 展期费用
	 */
	private String extensionCharge;

	/**
	 * 逾期管理费
	 */
	private String overdueManagerFee;
	
	/**
	 * 应还总金额
	 */
	private String accountTotalAmount;
	
	/**
	 * 已还提前还款手续费
	 */
	private String paidEarlyRepaymentCharge;
	
	/**
	 * 结清状态（已结清、未结清、部分结清）
	 */
	private String settleStatus;

	/**
	 * 期状态（-1 往期，0,1当期，2 未来期）
	 */
	private String periodStatus;
	
	/**
	 * 用于页面显示用的结清状态
	 */
	private String settleStatusForPage;
	
	/**
	 * 应还本金New
	 */
	private String accountPrincipalNew;

	/**
	 * 应还利息New
	 */
	private String accountInterestNew;

	/**
	 * 应还服务费New
	 */
	private String accountServiceChargeNew;
	
	/**
	 * 逾期本金New
	 */
	private String overduePrincipalNew;

	/**
	 * 逾期利息New
	 */
	private String overdueInterestNew;

	/**
	 * 逾期服务费New
	 */
	private String overdueServiceChargeNew;
	
	/**
	 * 展期费用New
	 */
	private String extensionChargeNew;

	/**
	 * 逾期管理费New
	 */
	private String overdueManagerFeeNew;
	
	/**
	 * 已还提前还款手续费New
	 */
	private String paidEarlyRepaymentChargeNew;
	
	/**
	 * 应还总金额New
	 */
	private String accountTotalAmountNew;
	
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

	public String getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(String repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
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

	public String getExtensionCharge() {
		return extensionCharge;
	}

	public void setExtensionCharge(String extensionCharge) {
		this.extensionCharge = extensionCharge;
	}

	public String getOverdueManagerFee() {
		return overdueManagerFee;
	}

	public void setOverdueManagerFee(String overdueManagerFee) {
		this.overdueManagerFee = overdueManagerFee;
	}

	public String getAccountTotalAmount() {
		return accountTotalAmount;
	}

	public void setAccountTotalAmount(String accountTotalAmount) {
		this.accountTotalAmount = accountTotalAmount;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getPaidEarlyRepaymentCharge() {
		return paidEarlyRepaymentCharge;
	}

	public void setPaidEarlyRepaymentCharge(String paidEarlyRepaymentCharge) {
		this.paidEarlyRepaymentCharge = paidEarlyRepaymentCharge;
	}

	public String getSettleStatusForPage() {
		return settleStatusForPage;
	}

	public void setSettleStatusForPage(String settleStatusForPage) {
		this.settleStatusForPage = settleStatusForPage;
	}

	public String getAccountPrincipalNew() {
		return accountPrincipalNew;
	}

	public void setAccountPrincipalNew(String accountPrincipalNew) {
		this.accountPrincipalNew = accountPrincipalNew;
	}

	public String getAccountInterestNew() {
		return accountInterestNew;
	}

	public void setAccountInterestNew(String accountInterestNew) {
		this.accountInterestNew = accountInterestNew;
	}

	public String getAccountServiceChargeNew() {
		return accountServiceChargeNew;
	}

	public void setAccountServiceChargeNew(String accountServiceChargeNew) {
		this.accountServiceChargeNew = accountServiceChargeNew;
	}

	public String getOverduePrincipalNew() {
		return overduePrincipalNew;
	}

	public void setOverduePrincipalNew(String overduePrincipalNew) {
		this.overduePrincipalNew = overduePrincipalNew;
	}

	public String getOverdueInterestNew() {
		return overdueInterestNew;
	}

	public void setOverdueInterestNew(String overdueInterestNew) {
		this.overdueInterestNew = overdueInterestNew;
	}

	public String getOverdueServiceChargeNew() {
		return overdueServiceChargeNew;
	}

	public void setOverdueServiceChargeNew(String overdueServiceChargeNew) {
		this.overdueServiceChargeNew = overdueServiceChargeNew;
	}

	public String getExtensionChargeNew() {
		return extensionChargeNew;
	}

	public void setExtensionChargeNew(String extensionChargeNew) {
		this.extensionChargeNew = extensionChargeNew;
	}

	public String getOverdueManagerFeeNew() {
		return overdueManagerFeeNew;
	}

	public void setOverdueManagerFeeNew(String overdueManagerFeeNew) {
		this.overdueManagerFeeNew = overdueManagerFeeNew;
	}

	public String getPaidEarlyRepaymentChargeNew() {
		return paidEarlyRepaymentChargeNew;
	}

	public void setPaidEarlyRepaymentChargeNew(String paidEarlyRepaymentChargeNew) {
		this.paidEarlyRepaymentChargeNew = paidEarlyRepaymentChargeNew;
	}

	public String getAccountTotalAmountNew() {
		return accountTotalAmountNew;
	}

	public void setAccountTotalAmountNew(String accountTotalAmountNew) {
		this.accountTotalAmountNew = accountTotalAmountNew;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AccountRepaymentPlanRedisDTO other = (AccountRepaymentPlanRedisDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getPeriodStatus() {
		return periodStatus;
	}

	public void setPeriodStatus(String periodStatus) {
		this.periodStatus = periodStatus;
	}
	
	/**
	 * 计算还款总金额
	 * @param plan
	 * @return
	 */
	public void computeTotalAmountCharge(){
		
		long amount = 0;
		amount += MoneyArithUtil.convertStringToMoney(accountPrincipal);
		amount += MoneyArithUtil.convertStringToMoney(accountInterest);
		amount += MoneyArithUtil.convertStringToMoney(accountServiceCharge);
		amount += MoneyArithUtil.convertStringToMoney(overduePrincipal);
		amount += MoneyArithUtil.convertStringToMoney(extensionCharge);
		amount += MoneyArithUtil.convertStringToMoney(overdueInterest);
		amount += MoneyArithUtil.convertStringToMoney(overdueServiceCharge);
//		amount += MoneyUtil.convertStringToMoney(paidEarlyRepaymentCharge);
		amount += MoneyArithUtil.convertStringToMoney(overdueManagerFee);
		
		accountTotalAmount = MoneyArithUtil.convertMoneyToString(amount);
	}

}
