package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.PaymentType;

public class SubmitSettlementRequestDTO implements Serializable{
	
	private static final long serialVersionUID = 4506648570410552200L;

	/**
	 * 核销交易对手还款表Id
	 */
	private String settlementCounterpartyId;
	
	/**
	 * 回购交易对手还款表Id
	 */
	private String buybackCounterpartyId;
	
	/**
	 * 交易对手Id
	 */
	private String counterpartyId;
	
	/**
	 * 取消选中的客票集合
	 */
	private List<String> unselectedTicketNoList;

	/**
	 * 选中的客票集合
	 */
	private List<String> selectedTicketNoList;
	
	/**
	 * 是否选中所有
	 */
	private boolean chooseAll;
	
	/**
	 * 实际核销总金额
	 */
	private String actualSettlementAmount;
	
	/**
	 * 放款总金额
	 */
	private String loanAmount;
	
	/**
	 * 还款类型
	 */
	private PaymentType paymentType;
	
	/**
	 * 渠道
	 */
	private Channel channel;

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

	public List<String> getUnselectedTicketNoList() {
		return unselectedTicketNoList;
	}

	public void setUnselectedTicketNoList(List<String> unselectedTicketNoList) {
		this.unselectedTicketNoList = unselectedTicketNoList;
	}

	public String getActualSettlementAmount() {
		return actualSettlementAmount;
	}

	public void setActualSettlementAmount(String actualSettlementAmount) {
		this.actualSettlementAmount = actualSettlementAmount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public List<String> getSelectedTicketNoList() {
		return selectedTicketNoList;
	}

	public void setSelectedTicketNoList(List<String> selectedTicketNoList) {
		this.selectedTicketNoList = selectedTicketNoList;
	}

	public boolean isChooseAll() {
		return chooseAll;
	}

	public void setChooseAll(boolean chooseAll) {
		this.chooseAll = chooseAll;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}
}
