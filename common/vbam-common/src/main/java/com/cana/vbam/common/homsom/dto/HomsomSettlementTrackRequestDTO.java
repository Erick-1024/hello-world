package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

import com.cana.vbam.common.homsom.enums.Channel;

/**
 * 恒顺核销履历请求DTO
 * @author jiangzhou.Ren
 * @time 2016年10月18日下午4:35:02
 */
public class HomsomSettlementTrackRequestDTO implements Serializable{

	private static final long serialVersionUID = -3433881089339169521L;
	
	/**
	 * 交易对手
	 */
	private String counterpartyName;
	
	/**
	 * 票号
	 */
	private String ticketNo;
	
	 /**
     *出票开始日期，格式:yyyy-MM-dd
     */
	private String startIssueDate;
	
	 /**
     *出票结束日期，格式:yyyy-MM-dd
     */
	private String endIssueDate;
	
	/**
     *核销起始日期，格式:yyyy-MM-dd
     */
    private String startSettleDate;
    
    /**
     *核销结束日期，格式:yyyy-MM-dd
     */
    private String endSettleDate;
    
    /**
     *回购起始日期，格式:yyyy-MM-dd
     */
    private String startBuybackDate;
    
    /**
     *回购结束日期，格式:yyyy-MM-dd
     */
    private String endBuybackDate;
    
	/**
     * 渠道
     */
    private Channel channel;
    
    private int page=1;
    
    private int pageSize=5;

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	
	public String getStartIssueDate() {
		return startIssueDate;
	}

	public void setStartIssueDate(String startIssueDate) {
		this.startIssueDate = startIssueDate;
	}

	public String getEndIssueDate() {
		return endIssueDate;
	}

	public void setEndIssueDate(String endIssueDate) {
		this.endIssueDate = endIssueDate;
	}

	public String getStartSettleDate() {
		return startSettleDate;
	}

	public void setStartSettleDate(String startSettleDate) {
		this.startSettleDate = startSettleDate;
	}

	public String getEndSettleDate() {
		return endSettleDate;
	}

	public void setEndSettleDate(String endSettleDate) {
		this.endSettleDate = endSettleDate;
	}

	public String getStartBuybackDate() {
		return startBuybackDate;
	}

	public void setStartBuybackDate(String startBuybackDate) {
		this.startBuybackDate = startBuybackDate;
	}

	public String getEndBuybackDate() {
		return endBuybackDate;
	}

	public void setEndBuybackDate(String endBuybackDate) {
		this.endBuybackDate = endBuybackDate;
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

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
