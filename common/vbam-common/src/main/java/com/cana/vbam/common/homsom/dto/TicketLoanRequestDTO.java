package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.PaymentType;
import com.cana.vbam.common.homsom.enums.SettlementState;

public class TicketLoanRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 5295919686251281566L;

	/**
	 * 渠道
	 */
	private Channel channel;
	
	/**
	 * 还款类型
	 */
	private PaymentType paymentType;
	
	/**
	 * 交易对手还款表ID
	 */
	private String settlementCounterpartyId;
	
	/**
	 * 交易对手回购表ID
	 */
	private String buybackCounterpartyId;
	
	/**
	 * 核销/回购状态
	 */
	private SettlementState state;
	
	private int page;
	
	private int pageSize;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getSettlementCounterpartyId() {
		return settlementCounterpartyId;
	}

	public void setSettlementCounterpartyId(String settlementCounterpartyId) {
		this.settlementCounterpartyId = settlementCounterpartyId;
	}

	public String getBuybackCounterpartyId() {
		return buybackCounterpartyId;
	}

	public void setBuybackCounterpartyId(String buybackCounterpartyId) {
		this.buybackCounterpartyId = buybackCounterpartyId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public SettlementState getState() {
		return state;
	}

	public void setState(SettlementState state) {
		this.state = state;
	}
}
