package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

import com.cana.vbam.common.utils.MoneyArithUtil;

public class PaidRepaymentPlanRedisDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2023900148125294978L;

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
	 * 线下还款日
	 */
	private String offlineRepaymentDate;
	
	/**
	 * 已还本金
	 */
	private String paidNormalPrincipal;

	/**
	 * 已还利息
	 */
	private String paidNormalInterest;

	/**
	 * 已还服务费
	 */
	private String paidNormalServiceCharge;

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
	 * 已还展期费用
	 */
	private String paidExtensionCharge;

	/**
	 * 已还逾期管理费
	 */
	private String paidOverdueManagerFee;

	/**
	 * 已还提前还款手续费
	 */
	private String paidEarlyRepaymentCharge;

	/**
	 * 已还总金额
	 */
	private String paidTotalAmount;

	/**
	 * 应还本金
	 */
	private String accountPrincipal;
	
	/**
	 * 期状态 （-1是往期，0是当前，1当前，2未来期）
	 */
	private String periodStatus;
	
	/**
	 * 放款信息id
	 */
	private String loanInfoId;
	
	/**
	 * 用于页面显示用的结清状态
	 */
	private String settleStatusForPage;
	
	/**
	 * 已还本金New
	 */
	private String paidNormalPrincipalNew;

	/**
	 * 已还利息New
	 */
	private String paidNormalInterestNew;

	/**
	 * 已还服务费New
	 */
	private String paidNormalServiceChargeNew;

	/**
	 * 已还逾期本金New
	 */
	private String paidOverduePrincipalNew;

	/**
	 * 已还逾期利息New
	 */
	private String paidOverdueInterestNew;

	/**
	 * 已还逾期服务费New
	 */
	private String paidOverdueServiceChargeNew;

	/**
	 * 已还展期费用New
	 */
	private String paidExtensionChargeNew;

	/**
	 * 已还逾期管理费New
	 */
	private String paidOverdueManagerFeeNew;

	/**
	 * 已还提前还款手续费New
	 */
	private String paidEarlyRepaymentChargeNew;
	
	/**
	 * 已还总金额New
	 */
	private String paidTotalAmountNew;
	
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

	public String getOfflineRepaymentDate() {
		return offlineRepaymentDate;
	}

	public void setOfflineRepaymentDate(String offlineRepaymentDate) {
		this.offlineRepaymentDate = offlineRepaymentDate;
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

	public String getPaidOverdueManagerFee() {
		return paidOverdueManagerFee;
	}

	public void setPaidOverdueManagerFee(String paidOverdueManagerFee) {
		this.paidOverdueManagerFee = paidOverdueManagerFee;
	}

	public String getPaidEarlyRepaymentCharge() {
		return paidEarlyRepaymentCharge;
	}

	public void setPaidEarlyRepaymentCharge(String paidEarlyRepaymentCharge) {
		this.paidEarlyRepaymentCharge = paidEarlyRepaymentCharge;
	}

	public String getPaidTotalAmount() {
		return paidTotalAmount;
	}

	public void setPaidTotalAmount(String paidTotalAmount) {
		this.paidTotalAmount = paidTotalAmount;
	}

	public String getSettleStatusForPage() {
		return settleStatusForPage;
	}

	public void setSettleStatusForPage(String settleStatusForPage) {
		this.settleStatusForPage = settleStatusForPage;
	}

	public String getAccountPrincipal() {
		return accountPrincipal;
	}

	public void setAccountPrincipal(String accountPrincipal) {
		this.accountPrincipal = accountPrincipal;
	}

	public String getPaidNormalPrincipalNew() {
		return paidNormalPrincipalNew;
	}

	public void setPaidNormalPrincipalNew(String paidNormalPrincipalNew) {
		this.paidNormalPrincipalNew = paidNormalPrincipalNew;
	}

	public String getPaidNormalInterestNew() {
		return paidNormalInterestNew;
	}

	public void setPaidNormalInterestNew(String paidNormalInterestNew) {
		this.paidNormalInterestNew = paidNormalInterestNew;
	}

	public String getPaidNormalServiceChargeNew() {
		return paidNormalServiceChargeNew;
	}

	public void setPaidNormalServiceChargeNew(String paidNormalServiceChargeNew) {
		this.paidNormalServiceChargeNew = paidNormalServiceChargeNew;
	}

	public String getPaidOverduePrincipalNew() {
		return paidOverduePrincipalNew;
	}

	public void setPaidOverduePrincipalNew(String paidOverduePrincipalNew) {
		this.paidOverduePrincipalNew = paidOverduePrincipalNew;
	}

	public String getPaidOverdueInterestNew() {
		return paidOverdueInterestNew;
	}

	public void setPaidOverdueInterestNew(String paidOverdueInterestNew) {
		this.paidOverdueInterestNew = paidOverdueInterestNew;
	}

	public String getPaidOverdueServiceChargeNew() {
		return paidOverdueServiceChargeNew;
	}

	public void setPaidOverdueServiceChargeNew(String paidOverdueServiceChargeNew) {
		this.paidOverdueServiceChargeNew = paidOverdueServiceChargeNew;
	}

	public String getPaidExtensionChargeNew() {
		return paidExtensionChargeNew;
	}

	public void setPaidExtensionChargeNew(String paidExtensionChargeNew) {
		this.paidExtensionChargeNew = paidExtensionChargeNew;
	}

	public String getPaidOverdueManagerFeeNew() {
		return paidOverdueManagerFeeNew;
	}

	public void setPaidOverdueManagerFeeNew(String paidOverdueManagerFeeNew) {
		this.paidOverdueManagerFeeNew = paidOverdueManagerFeeNew;
	}

	public String getPaidEarlyRepaymentChargeNew() {
		return paidEarlyRepaymentChargeNew;
	}

	public void setPaidEarlyRepaymentChargeNew(String paidEarlyRepaymentChargeNew) {
		this.paidEarlyRepaymentChargeNew = paidEarlyRepaymentChargeNew;
	}

	public String getPaidTotalAmountNew() {
		return paidTotalAmountNew;
	}

	public void setPaidTotalAmountNew(String paidTotalAmountNew) {
		this.paidTotalAmountNew = paidTotalAmountNew;
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
		PaidRepaymentPlanRedisDTO other = (PaidRepaymentPlanRedisDTO) obj;
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
		amount += MoneyArithUtil.convertStringToMoney(paidNormalPrincipal);
		amount += MoneyArithUtil.convertStringToMoney(paidNormalInterest);
		amount += MoneyArithUtil.convertStringToMoney(paidNormalServiceCharge);
		amount += MoneyArithUtil.convertStringToMoney(paidOverduePrincipal);
		amount += MoneyArithUtil.convertStringToMoney(paidOverdueInterest);
		amount += MoneyArithUtil.convertStringToMoney(paidOverdueServiceCharge);
		amount += MoneyArithUtil.convertStringToMoney(paidExtensionCharge);
		amount += MoneyArithUtil.convertStringToMoney(paidEarlyRepaymentCharge);
		amount += MoneyArithUtil.convertStringToMoney(paidOverdueManagerFee);
		
		paidTotalAmount = MoneyArithUtil.convertMoneyToString(amount);
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
}
