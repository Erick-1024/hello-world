package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

/**
 * 预还款结果(合并)
 * 
 * @author sugar
 *
 */
public class PrepareRepaymentMergeResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 归还本金
	 */
	private String paidPrincipal;

	/**
	 * 归还利息
	 */
	private String paidInterest;

	/**
	 * 归还逾期额
	 */
	private String paidOverdueAmount;
	
	/**
	 * 归还服务费
	 */
	private String paidServiceCharge;
	
	public String getPaidPrincipal() {
		return paidPrincipal;
	}

	public void setPaidPrincipal(String paidPrincipal) {
		this.paidPrincipal = paidPrincipal;
	}

	public String getPaidInterest() {
		return paidInterest;
	}

	public void setPaidInterest(String paidInterest) {
		this.paidInterest = paidInterest;
	}

	public String getPaidOverdueAmount() {
		return paidOverdueAmount;
	}

	public void setPaidOverdueAmount(String paidOverdueAmount) {
		this.paidOverdueAmount = paidOverdueAmount;
	}

	public String getPaidServiceCharge() {
		return paidServiceCharge;
	}

	public void setPaidServiceCharge(String paidServiceCharge) {
		this.paidServiceCharge = paidServiceCharge;
	}
}
