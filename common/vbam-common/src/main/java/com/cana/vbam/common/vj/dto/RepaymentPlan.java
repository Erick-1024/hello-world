package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class RepaymentPlan implements Serializable {

	private static final long serialVersionUID = 2000817258406489445L;

	private String planId;
	
	private String repaymentDate;
	
	private Long accountPrincipal;
	
	private Long accountInterest;
	
	private Long accountInterestUntilNow;
	
	private Integer overdueDays;
	
	private Long overduePrincipal;
	
	private Long overdueInterest;
	
	private Long overduePenalty;
	
	private boolean overdue;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public Long getAccountPrincipal() {
		return accountPrincipal;
	}

	public void setAccountPrincipal(Long accountPrincipal) {
		this.accountPrincipal = accountPrincipal;
	}

	public Long getAccountInterest() {
		return accountInterest;
	}

	public void setAccountInterest(Long accountInterest) {
		this.accountInterest = accountInterest;
	}

	public Long getAccountInterestUntilNow() {
		return accountInterestUntilNow;
	}

	public void setAccountInterestUntilNow(Long accountInterestUntilNow) {
		this.accountInterestUntilNow = accountInterestUntilNow;
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public Long getOverduePrincipal() {
		return overduePrincipal;
	}

	public void setOverduePrincipal(Long overduePrincipal) {
		this.overduePrincipal = overduePrincipal;
	}

	public Long getOverdueInterest() {
		return overdueInterest;
	}

	public void setOverdueInterest(Long overdueInterest) {
		this.overdueInterest = overdueInterest;
	}

	public Long getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(Long overduePenalty) {
		this.overduePenalty = overduePenalty;
	}
	
	public boolean isOverdue() {
		return overdue;
	}

	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}
}
