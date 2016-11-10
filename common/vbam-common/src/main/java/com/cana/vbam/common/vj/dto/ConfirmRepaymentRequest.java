package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

/**
 * 确定还款请求
 * @author tangyihong
 *
 */
public class ConfirmRepaymentRequest implements Serializable{
	
	private static final long serialVersionUID = -6389908125748774373L;

	private String vjTranSeq;
	
	private String canaCustomerId;
	
	private String loanId;
	
	private long repaymentAmount;

	public String getVjTranSeq() {
		return vjTranSeq;
	}

	public void setVjTranSeq(String vjTranSeq) {
		this.vjTranSeq = vjTranSeq;
	}

	public String getCanaCustomerId() {
		return canaCustomerId;
	}

	public void setCanaCustomerId(String canaCustomerId) {
		this.canaCustomerId = canaCustomerId;
	}

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
	
	

}
