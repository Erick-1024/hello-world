package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class SyncVJDeductStateRetryTaskData implements Serializable{
	
	private static final long serialVersionUID = 5744779151373181303L;

	private String canaTranSeq;
	
	private String taskItemId;
	
	private String loanInfoId;
	
	private long amount;

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}

	public String getTaskItemId() {
		return taskItemId;
	}

	public void setTaskItemId(String taskItemId) {
		this.taskItemId = taskItemId;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
