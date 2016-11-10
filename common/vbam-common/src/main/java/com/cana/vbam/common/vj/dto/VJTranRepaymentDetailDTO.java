package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

/**
 * 交易明细－－主动还款明细详情
 * @author sugar
 *
 */
public class VJTranRepaymentDetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *CANA端生成的交易流水
	 */
	private String canaTranSeq;

	/**
	 * 操作时间
	 */
	private String operatingTime;
	
	/**
	 * 金额明细
	 */
	private String amountDetails;

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}

	public String getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getAmountDetails() {
		return amountDetails;
	}

	public void setAmountDetails(String amountDetails) {
		this.amountDetails = amountDetails;
	}

}