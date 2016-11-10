package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class TicketDTO implements Serializable {

	private static final long serialVersionUID = -530506549278053578L;

    /**
     *代理商名称
     */
    private String agentName;

    /**
     *订单号
     */
    private String orderId;

    /**
     *票号
     */
    private String ticketNo;

    /**
     *代理商购票金额
     */
    private Long amount;

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

    /**
     *客票状态: 待处理、作废、已处理（即生成了应收账款，但依然存在三个状态：没有配置、额度不足、已生成放款）
     */
    private String state;

    private String stateDesc;
    
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

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

}
