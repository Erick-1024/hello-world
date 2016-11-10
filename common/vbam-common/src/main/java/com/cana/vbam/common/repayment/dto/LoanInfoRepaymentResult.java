package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

/**
 * 放款信息还款结果
 * @author renshui
 *
 */
public class LoanInfoRepaymentResult implements Serializable{
	
	private static final long serialVersionUID = 17685203680676463L;

	/**
	 * 放款信息id
	 */
	private String loanInfoId;
	
	/**
	 * 实际总的还款金额
	 */
	private long actualRepaymentTotalAmount;

	/**
	 * 本次还款本金
	 */
	private long actualRepaymentPrincipal;
	
	/**
	 * 还款汇总记录id
	 */
	private String repaymentSummaryRecordId;

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getRepaymentSummaryRecordId() {
		return repaymentSummaryRecordId;
	}

	public void setRepaymentSummaryRecordId(String repaymentSummaryRecordId) {
		this.repaymentSummaryRecordId = repaymentSummaryRecordId;
	}

	public long getActualRepaymentTotalAmount() {
		return actualRepaymentTotalAmount;
	}

	public void setActualRepaymentTotalAmount(long actualRepaymentTotalAmount) {
		this.actualRepaymentTotalAmount = actualRepaymentTotalAmount;
	}

	public long getActualRepaymentPrincipal() {
		return actualRepaymentPrincipal;
	}

	public void setActualRepaymentPrincipal(long actualRepaymentPrincipal) {
		this.actualRepaymentPrincipal = actualRepaymentPrincipal;
	}

}
