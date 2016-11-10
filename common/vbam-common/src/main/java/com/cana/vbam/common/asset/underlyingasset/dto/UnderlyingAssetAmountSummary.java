package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;

/**
 * 基础资产的应还、实还金额信息
 * 
 * @author XuMeng
 *
 */
public class UnderlyingAssetAmountSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id; // 目标ID，可能为专项计划ID或基础资产ID
	private long accountInterest; // 应还利息
	private long accountOverdue; // 应还逾期
	private long accountPrincipal; // 应还本金
	private long paidInterest; // 已还利息
	private long paidOverdue; // 已还逾期
	private long paidPrincipal; // 已还本金

	// 以下是由上述字段计算出来的汇总金额信息
	private long accountIncome; // 应还收入 = accountInterest + accountOverdue
	private long accountAmount; // 应还总额 = accountIncome + accountPrincipal
	private long paidIncome; // 已还收入 = paidInterest + paidOverdue
	private long paidAmount; // 已还总额 = paidIncome + paidPrincipal
	private long unpaidAmount; // 未偿总额 = max(accountAmount - paidAmount, 0)

	/**
	 * 计算汇总金额
	 */
	public UnderlyingAssetAmountSummary calcSummaryAmount() {
		accountIncome = accountInterest + accountOverdue;
		accountAmount = accountIncome + accountPrincipal;
		paidIncome = paidInterest + paidOverdue;
		paidAmount = paidIncome + paidPrincipal;
		unpaidAmount = accountAmount - paidAmount;
		unpaidAmount = unpaidAmount < 0 ? 0 : unpaidAmount;
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getAccountIncome() {
		return accountIncome;
	}

	public void setAccountIncome(long accountIncome) {
		this.accountIncome = accountIncome;
	}

	public long getAccountPrincipal() {
		return accountPrincipal;
	}

	public void setAccountPrincipal(long accountPrincipal) {
		this.accountPrincipal = accountPrincipal;
	}

	public long getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(long accountAmount) {
		this.accountAmount = accountAmount;
	}

	public long getPaidIncome() {
		return paidIncome;
	}

	public void setPaidIncome(long paidIncome) {
		this.paidIncome = paidIncome;
	}

	public long getPaidPrincipal() {
		return paidPrincipal;
	}

	public void setPaidPrincipal(long paidPrincipal) {
		this.paidPrincipal = paidPrincipal;
	}

	public long getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(long paidAmount) {
		this.paidAmount = paidAmount;
	}

	public long getUnpaidAmount() {
		return unpaidAmount;
	}

	public void setUnpaidAmount(long unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}

	public long getAccountInterest() {
		return accountInterest;
	}

	public void setAccountInterest(long accountInterest) {
		this.accountInterest = accountInterest;
	}

	public long getAccountOverdue() {
		return accountOverdue;
	}

	public void setAccountOverdue(long accountOverdue) {
		this.accountOverdue = accountOverdue;
	}

	public long getPaidInterest() {
		return paidInterest;
	}

	public void setPaidInterest(long paidInterest) {
		this.paidInterest = paidInterest;
	}

	public long getPaidOverdue() {
		return paidOverdue;
	}

	public void setPaidOverdue(long paidOverdue) {
		this.paidOverdue = paidOverdue;
	}

}
