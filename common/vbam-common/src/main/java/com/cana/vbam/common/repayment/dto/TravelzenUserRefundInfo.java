package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class TravelzenUserRefundInfo implements Serializable{
	
	private static final long serialVersionUID = 8322818921295428171L;
	
	// 真旅客户在cana平台的用户id
	private String financeId;
	// 退款金额，单位分
	private long refundAmount;
	// cana平台产生的退款交易id
	private String txnId;
	// 外部客户id
	private String outCustomerId;
	
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public long getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(long refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getOutCustomerId() {
		return outCustomerId;
	}
	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}
	

}
