package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.PaymentType;
import com.cana.vbam.common.homsom.enums.SettlementState;

public class PaymentDataRequestDTO implements Serializable{

	private static final long serialVersionUID = 1750274634094880470L;

	/**
	 * 渠道
	 */
	private Channel channel;
	
	/**
	 * 还款类型
	 */
	private PaymentType paymentType;
	
	/**
	 * 状态
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
