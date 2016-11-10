package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.repayment.enums.RepaymentType;

public class CounterpartyConfigDTO implements Serializable{
	
	private static final long serialVersionUID = -7881205836253478682L;

	/**
     * 渠道
     */
    private Channel channel;

    /**
     * 交易对手Id
     */
    private String counterpartyId;
	
	/**
     * 交易对手
     */
    private String counterparty;

    /**
     * 放款期限，单位天
     */
    private String loanPeriod;

    /**
     * 回购期限，单位天
     */
    private String buybackPeriod;

    /**
     * 还本付息方式
     */
    private RepaymentType repaymentMethod;
    
    /**
     * 是否在配置表中
     */
    private boolean inConfigFlag;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getBuybackPeriod() {
		return buybackPeriod;
	}

	public void setBuybackPeriod(String buybackPeriod) {
		this.buybackPeriod = buybackPeriod;
	}

	public RepaymentType getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(RepaymentType repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public boolean isInConfigFlag() {
		return inConfigFlag;
	}

	public void setInConfigFlag(boolean inConfigFlag) {
		this.inConfigFlag = inConfigFlag;
	}
}
