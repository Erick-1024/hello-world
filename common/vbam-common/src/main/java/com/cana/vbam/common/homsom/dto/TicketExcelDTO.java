package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class TicketExcelDTO implements Serializable{

	/**
	 *票号
	 */
	private String ticketNo;

	/**
	 *代理商名称
	 */
	private String agentName;
	
	/**
	 *订单号
	 */
	private String orderId;

    /**
     *代理商购票金额
     */
    private String amount;

    /**
     *出票日期，格式:yyyy-MM-dd
     */
    private String issueDate;

    /**
     *航班号
     */
    private String airline;

    /**
     *航程
     */
    private String itinerary;

    /**
     *起飞日期和时间
     */
    private String departureDateTime;

    /**
     *乘客姓名
     */
    private String passengerName;

    private String stateDesc;
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4284764688321736096L;

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public String getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
	
}
