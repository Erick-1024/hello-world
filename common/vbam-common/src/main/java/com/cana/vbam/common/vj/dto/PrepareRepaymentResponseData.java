package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

/**
 * 
 * @author renshui
 *
 */
public class PrepareRepaymentResponseData implements Serializable{
	
	private static final long serialVersionUID = 5533781382439058707L;

	private long accountPrincipal;
	
	private long accountInterest;
	
	private long overduePrincipal;
	
	private long overdueInterest;
	
	private long overduePenalty;
	
	private long returnAmount;

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

	public long getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(long overduePenalty) {
		this.overduePenalty = overduePenalty;
	}

	public long getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(long returnAmount) {
		this.returnAmount = returnAmount;
	}

}
