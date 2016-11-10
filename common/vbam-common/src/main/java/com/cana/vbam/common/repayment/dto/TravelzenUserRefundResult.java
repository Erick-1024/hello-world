package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 真旅用户退款结果
 * @author renshui
 *
 */
public class TravelzenUserRefundResult implements Serializable{

	private static final long serialVersionUID = -4140842512972806760L;

	/**
	 * 还款结束后剩余的金额
	 */
	private long remainingAmount;
	
	/**
	 * 本次还的本金总额
	 */
	private long totalPaidPrincipal;
	
	/**
	 * 受影响的放款信息还款结果
	 */
	private List<LoanInfoRepaymentResult> result;

	public long getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(long remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public List<LoanInfoRepaymentResult> getResult() {
		return result;
	}

	public void setResult(List<LoanInfoRepaymentResult> result) {
		this.result = result;
	}

	public long getTotalPaidPrincipal() {
		return totalPaidPrincipal;
	}

	public void setTotalPaidPrincipal(long totalPaidPrincipal) {
		this.totalPaidPrincipal = totalPaidPrincipal;
	}
	
}
