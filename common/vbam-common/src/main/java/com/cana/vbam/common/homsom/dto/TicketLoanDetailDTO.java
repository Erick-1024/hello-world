package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

import com.cana.vbam.common.homsom.enums.Channel;

public class TicketLoanDetailDTO implements Serializable{
	
	private static final long serialVersionUID = 2484608978255469557L;

	/**
	 * 交易对手还款表ID
	 */
	private String settlementCounterpartyId;
	
	/**
	 * 交易对手回购表ID
	 */
	private String buybackCounterpartyId;
	
	/**
	 * 交易对手id
	 */
	private String counterpartyId;

	/**
	 * 交易对手名称
	 */
	private String counterpartyName;
	
	/**
	 * 渠道
	 */
	private Channel channel;

	/**
	 * 票号
	 */
	private String ticketNo;
	
	/**
	 * 本金
	 */
	private String loanAmount;
	
	/**
	 * 利息
	 */
	private String interestAmount;
	
	/**
	 * 期限
	 */
	private String loanPeriod;

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

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}
}
