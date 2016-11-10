package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

/**
 * 还款请求
 * @author tangyihong
 *
 */
public class RepaymentRequest implements Serializable{
	
	private static final long serialVersionUID = -6389908125748774373L;

	private String loanId;
	
	private long repaymentAmount;
	
	private boolean isSendMessage;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public long getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(long repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public boolean isSendMessage() {
		return isSendMessage;
	}

	public void setSendMessage(boolean isSendMessage) {
		this.isSendMessage = isSendMessage;
	}
	
	

}
