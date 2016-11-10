package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

import com.cana.vbam.common.homsom.enums.SettlementState;

public class SettlementCounterpartyDTO implements Serializable{

	private static final long serialVersionUID = -7555558038556890576L;

	/**
     * 主键
     */
    private String id;

    /**
     * 交易对手ID
     */
    private String counterpartyId;

    /**
     * 交易对手名称，即单位客户名称
     */
    private String counterpartyName;

    /**
     * 应核销总金额
     */
    private String settleAmount;

    /**
     * 实际核销总金额
     */
    private String actualSettleAmount;

    /**
     * 放款总金额
     */
    private String loanTotalAmount;

    /**
     * 应退总金额
     */
    private String refundAmount;

    /**
     * 回购总金额
     */
    private String buybackAmount;

    /**
     * 实际回购总金额
     */
    private String actualBuybackAmount;

    /**
     *核销状态: 未核销、已核销
     */
    private SettlementState state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
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

	public String getLoanTotalAmount() {
		return loanTotalAmount;
	}

	public void setLoanTotalAmount(String loanTotalAmount) {
		this.loanTotalAmount = loanTotalAmount;
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

	public SettlementState getState() {
		return state;
	}

	public void setState(SettlementState state) {
		this.state = state;
	}

	public String getActualBuybackAmount() {
		return actualBuybackAmount;
	}

	public void setActualBuybackAmount(String actualBuybackAmount) {
		this.actualBuybackAmount = actualBuybackAmount;
	}
}
