package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class SettlementSummaryDTO implements Serializable{

	private static final long serialVersionUID = -497130853527348251L;

	/**
	 * 核销/回购日期
	 */
	private String date;
	
	/**
	 * 应核销总金额
	 */
	private String settleAmount;
	
	/**
	 * 实际核销总金额
	 */
	private String actualSettleAmount;
	
	/**
	 * 应退还总金额
	 */
	private String refundAmount;
	
	/**
	 * 应回购总金额
	 */
	private String buybackAmount;
	
	/**
	 * 已回购总金额
	 */
	private String actualBuybackAmount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}

	public String getActualSettleAmount() {
		return actualSettleAmount;
	}

	public void setActualSettleAmount(String actualSettleAmount) {
		this.actualSettleAmount = actualSettleAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getBuybackAmount() {
		return buybackAmount;
	}

	public void setBuybackAmount(String buybackAmount) {
		this.buybackAmount = buybackAmount;
	}

	public String getActualBuybackAmount() {
		return actualBuybackAmount;
	}

	public void setActualBuybackAmount(String actualBuybackAmount) {
		this.actualBuybackAmount = actualBuybackAmount;
	}
}
