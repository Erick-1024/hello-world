package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

/**
 * 还款金额
 * @author renshui
 *
 */
public class RepaymentAmount implements Serializable{

	private static final long serialVersionUID = -7821278305363651822L;

	/**
     *应还本金
     */
    private long accountPrincipal;

    /**
     *应还利息
     */
    private long accountInterest;

    /**
     *应还服务费
     */
    private long accountServiceCharge;
    
    /**
     *应还展期费用
     */
    private long accountExtensionCharge;
    
    /**
     *逾期本金
     */
    private long overduePrincipal;

    /**
     *逾期利息
     */
    private long overdueInterest;

    /**
     *逾期服务费
     */
    private long overdueServiceCharge;

    /**
     *逾期本金罚息
     */
    private long overduePrincipalPenalty;

    /**
     *逾期利息罚息
     */
    private long overdueInterestPenalty;

    /**
     *逾期服务费罚息
     */
    private long overdueServiceChargePenalty;

    /**
     *其他罚息
     */
    private long otherPenalty;
    
    /**
     *已还正常本金
     */
    private long paidNormalPrincipal;

    /**
     *已还逾期本金
     */
    private long paidOverduePrincipal;

    /**
     *已还正常利息
     */
    private long paidNormalInterest;

    /**
     *已还逾期利息
     */
    private long paidOverdueInterest;

    /**
     *已还正常服务费
     */
    private long paidNormalServiceCharge;

    /**
     *已还逾期服务费
     */
    private long paidOverdueServiceCharge;

    /**
     *已还提前还款手续费
     */
    private long paidEarlyRepaymentCharge;

    /**
     *已还展期费用
     */
    private long paidExtensionCharge;

    /**
     *已还逾期本金罚息
     */
    private long paidOverduePrincipalPenalty;

    /**
     *已还逾期利息罚息
     */
    private long paidOverdueInterestPenalty;

    /**
     *已还逾期服务费罚息
     */
    private long paidOverdueServiceChargePenalty;

    /**
     *已还其他罚息
     */
    private long paidOtherPenalty;
    
    

	
	/**
	 * 返回所有金额的汇总
	 * @return
	 */
	public long unpaidTotal(){
		return accountPrincipal + accountInterest + accountServiceCharge + accountExtensionCharge 
				+ overduePrincipal + overdueInterest + overdueServiceCharge 
				+ overduePrincipalPenalty + overdueInterestPenalty + overdueServiceChargePenalty + otherPenalty;
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


	public long getAccountExtensionCharge() {
		return accountExtensionCharge;
	}


	public void setAccountExtensionCharge(long accountExtensionCharge) {
		this.accountExtensionCharge = accountExtensionCharge;
	}


	public long getOverduePrincipal() {
		return overduePrincipal;
	}


	public void setOverduePrincipal(long overduePrincipal) {
		this.overduePrincipal = overduePrincipal;
	}


	public long getOverdueInterest() {
		return overdueInterest;
	}


	public void setOverdueInterest(long overdueInterest) {
		this.overdueInterest = overdueInterest;
	}


	public long getOverdueServiceCharge() {
		return overdueServiceCharge;
	}


	public void setOverdueServiceCharge(long overdueServiceCharge) {
		this.overdueServiceCharge = overdueServiceCharge;
	}


	public long getOverduePrincipalPenalty() {
		return overduePrincipalPenalty;
	}


	public void setOverduePrincipalPenalty(long overduePrincipalPenalty) {
		this.overduePrincipalPenalty = overduePrincipalPenalty;
	}


	public long getOverdueInterestPenalty() {
		return overdueInterestPenalty;
	}


	public void setOverdueInterestPenalty(long overdueInterestPenalty) {
		this.overdueInterestPenalty = overdueInterestPenalty;
	}


	public long getOverdueServiceChargePenalty() {
		return overdueServiceChargePenalty;
	}


	public void setOverdueServiceChargePenalty(long overdueServiceChargePenalty) {
		this.overdueServiceChargePenalty = overdueServiceChargePenalty;
	}


	public long getOtherPenalty() {
		return otherPenalty;
	}


	public void setOtherPenalty(long otherPenalty) {
		this.otherPenalty = otherPenalty;
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
