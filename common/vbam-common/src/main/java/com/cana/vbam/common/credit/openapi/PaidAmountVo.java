package com.cana.vbam.common.credit.openapi;

import java.io.Serializable;

public class PaidAmountVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long paidNormalPrincipal;//已还正常本金
	
	private long paidNormalInterest;//已还正常利息
	
	private long paidNormalServiceCharge;//已还正常服务费
	
	private long paidExtensionCharge;//已还展期费用
	
	private long paidOverduePrincipal;//已还逾期本金
	
	private long paidOverdueInterest;//已还逾期利息
	
	private long paidOverdueServiceCharge;//已还逾期服务费
	
	private long paidOverduePrincipalPenalty;//已还逾期本金罚息
	
	private long paidOverdueInterestPenalty;//已还逾期利息罚息
	
	private long paidOverdueServiceChargePenalty;//已还逾期服务费罚息
	
	private long paidOtherPenalty;//已还其他罚息
	
	private long paidEarlyRepaymentCharge;//已还提前还款手续费

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

	public long getPaidExtensionCharge() {
		return paidExtensionCharge;
	}

	public void setPaidExtensionCharge(long paidExtensionCharge) {
		this.paidExtensionCharge = paidExtensionCharge;
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

	public long getPaidEarlyRepaymentCharge() {
		return paidEarlyRepaymentCharge;
	}

	public void setPaidEarlyRepaymentCharge(long paidEarlyRepaymentCharge) {
		this.paidEarlyRepaymentCharge = paidEarlyRepaymentCharge;
	}
	
	

}
