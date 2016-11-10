package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

/**
 * 预还款结果
 * 
 * @author XuMeng
 *
 */
public class PrepareRepaymentResult implements Serializable {

	/**
	 * 剩余金额，如果此值为负值，则表示还款金额距离最少还款金额的差值，当此值不为负值时，下列值则保存了此次还款金额的分拆情况
	 */
	private long remainingAmount;

	/**
	 * 已还正常本金
	 */
	private long paidNormalPrincipal;

	/**
	 * 已还逾期本金
	 */
	private long paidOverduePrincipal;

	/**
	 * 已还正常利息
	 */
	private long paidNormalInterest;

	/**
	 * 已还逾期利息
	 */
	private long paidOverdueInterest;

	/**
	 * 已还正常服务费
	 */
	private long paidNormalServiceCharge;

	/**
	 * 已还逾期服务费
	 */
	private long paidOverdueServiceCharge;

	/**
	 * 已还提前还款手续费
	 */
	private long paidEarlyRepaymentCharge;

	/**
	 * 已还展期费用
	 */
	private long paidExtensionCharge;

	/**
	 * 已还逾期本金罚息
	 */
	private long paidOverduePrincipalPenalty;

	/**
	 * 已还逾期利息罚息
	 */
	private long paidOverdueInterestPenalty;

	/**
	 * 已还逾期服务费罚息
	 */
	private long paidOverdueServiceChargePenalty;

	/**
	 * 已还其他罚息
	 */
	private long paidOtherPenalty;

	private static final long serialVersionUID = 1L;

	public long getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(long remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public long getPaidNormalPrincipal() {
		return paidNormalPrincipal;
	}

	public void setPaidNormalPrincipal(long paidNormalPrincipal) {
		this.paidNormalPrincipal = paidNormalPrincipal;
	}

	public long getPaidOverduePrincipal() {
		return paidOverduePrincipal;
	}

	public void setPaidOverduePrincipal(long paidOverduePrincipal) {
		this.paidOverduePrincipal = paidOverduePrincipal;
	}

	public long getPaidNormalInterest() {
		return paidNormalInterest;
	}

	public void setPaidNormalInterest(long paidNormalInterest) {
		this.paidNormalInterest = paidNormalInterest;
	}

	public long getPaidOverdueInterest() {
		return paidOverdueInterest;
	}

	public void setPaidOverdueInterest(long paidOverdueInterest) {
		this.paidOverdueInterest = paidOverdueInterest;
	}

	public long getPaidNormalServiceCharge() {
		return paidNormalServiceCharge;
	}

	public void setPaidNormalServiceCharge(long paidNormalServiceCharge) {
		this.paidNormalServiceCharge = paidNormalServiceCharge;
	}

	public long getPaidOverdueServiceCharge() {
		return paidOverdueServiceCharge;
	}

	public void setPaidOverdueServiceCharge(long paidOverdueServiceCharge) {
		this.paidOverdueServiceCharge = paidOverdueServiceCharge;
	}

	public long getPaidEarlyRepaymentCharge() {
		return paidEarlyRepaymentCharge;
	}

	public void setPaidEarlyRepaymentCharge(long paidEarlyRepaymentCharge) {
		this.paidEarlyRepaymentCharge = paidEarlyRepaymentCharge;
	}

	public long getPaidExtensionCharge() {
		return paidExtensionCharge;
	}

	public void setPaidExtensionCharge(long paidExtensionCharge) {
		this.paidExtensionCharge = paidExtensionCharge;
	}

	public long getPaidOverduePrincipalPenalty() {
		return paidOverduePrincipalPenalty;
	}

	public void setPaidOverduePrincipalPenalty(long paidOverduePrincipalPenalty) {
		this.paidOverduePrincipalPenalty = paidOverduePrincipalPenalty;
	}

	public long getPaidOverdueInterestPenalty() {
		return paidOverdueInterestPenalty;
	}

	public void setPaidOverdueInterestPenalty(long paidOverdueInterestPenalty) {
		this.paidOverdueInterestPenalty = paidOverdueInterestPenalty;
	}

	public long getPaidOverdueServiceChargePenalty() {
		return paidOverdueServiceChargePenalty;
	}

	public void setPaidOverdueServiceChargePenalty(long paidOverdueServiceChargePenalty) {
		this.paidOverdueServiceChargePenalty = paidOverdueServiceChargePenalty;
	}

	public long getPaidOtherPenalty() {
		return paidOtherPenalty;
	}

	public void setPaidOtherPenalty(long paidOtherPenalty) {
		this.paidOtherPenalty = paidOtherPenalty;
	}

}
