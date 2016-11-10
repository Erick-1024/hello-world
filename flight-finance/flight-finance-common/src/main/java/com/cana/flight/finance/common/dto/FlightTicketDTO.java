/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class FlightTicketDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
    *记录ID（保证全局唯一）。主键
    */
    private String recordId;

    /**
    *采购商ID，唯一
    */
    private String customerId;

    /**
    *客户名称
    */
    private String customerName;

    /**
    *true:国内，false:国际
    */
    private Boolean isDomestic;

    /**
    *订单编号
    */
    private String orderNo;

    /**
    *订单类型（normal:正常订单，refund:退票，endorse:改签）
    */
    private String orderType;

    /**
    *是否是调帐单（true:调帐单，false:非调帐单）
    */
    private Boolean isAdjust;

    /**
    *完成出票日期（格式：2016-01-18 07:50:00, 对于正常票来说指的是完成出票时间，对于退票来说指的是完成退票时间，对于改签来说指的是完成改签时间，对于调账来说指的是完成调账时间）
    */
    private String completeIssueTime;

    /**
    *票号
    */
    private String ticketNo;

    /**
    *票序号
    */
    private String conjunctionTicketSeqNo;

    /**
    *供应商名称（格式：不夜城）
    */
    private String supplierName;

    /**
    *付款方式（credit:信用，other:其他）
    */
    private String payType;

    /**
    *应收款。单位：分。 对于正常单来说，代表客票金额；对于退票来说，代表退票金额；对于改签来说，代表改签总费用；对于调账来说，代表调账产生的差额
    */
    private Long totalAmount;

    /**
    *票款。单位：分。 对于正常和改签票来说，代表最新的票面价，不是差额。对于退票来说，代表退的票面价。
    */
    private Long ticketPrice;

    /**
     * 单位：分。对于正常和改签票来说，代表最新的总税款，不是差额。对于退票来说，代表退的总税款。
     */
    private Long totalTax;
    
    /**
    *燃油。单位：分。 对于正常和改签票来说，代表最新的燃油，不是差额。对于退票来说，代表退的燃油。
    */
    private Long fuelTax;

    /**
    *基建。单位：分。 对于正常和改签票来说，代表最新的基建，不是差额。对于退票来说，代表退的基建。
    */
    private Long constructionFee;

    /**
    *PNR
    */
    private String crsPnr;

    /**
    *OFFIC号
    */
    private String ticketOfficeNo;

    /**
    *航班号（格式：CA1858）。多个用英文逗号分隔
    */
    private String airline;

    /**
    *航程（例：PEKSHA，如有多段用“,”分隔）
    */
    private String itinerary;

    /**
    *所有航班日期（格式：yyyy-MM-dd HH:mm，如果多段用“,”分隔）
    */
    private String departureDateTime;

    /**
    *航位（多个用“,”分隔）
    */
    private String cabinCode;

    /**
    *乘机人（如果用拼音表示，字之间用“/”分隔）
    */
    private String passengerName;

    /**
    *旅客类型（ADT:成人/INF:婴儿/CHD:儿童）
    */
    private String passengerType;

    /**
    *证件类型。国内： NI("身份证"), PP("护照"), ID("其他证件") , PP("护照")
    */
    private String doucmentType;

    /**
    *证件号码
    */
    private String documentNo;

    /**
    *乘客手机号
    */
    private String mobileNo;
    
    /**
    *原记录Id。客票被改签或退票，改签客票或退票记录中要包含原客票id。例如：正常票（记录号：R1）被改签了，改签票(记录号：R2)的origRecordId的值为R1，如果该改签票又被退掉了，退票记录的origRecordId的值为R2
    */
    private String origRecordId;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Boolean getIsDomestic() {
		return isDomestic;
	}

	public void setIsDomestic(Boolean isDomestic) {
		this.isDomestic = isDomestic;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Boolean getIsAdjust() {
		return isAdjust;
	}

	public void setIsAdjust(Boolean isAdjust) {
		this.isAdjust = isAdjust;
	}

	public String getCompleteIssueTime() {
		return completeIssueTime;
	}

	public void setCompleteIssueTime(String completeIssueTime) {
		this.completeIssueTime = completeIssueTime;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getConjunctionTicketSeqNo() {
		return conjunctionTicketSeqNo;
	}

	public void setConjunctionTicketSeqNo(String conjunctionTicketSeqNo) {
		this.conjunctionTicketSeqNo = conjunctionTicketSeqNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Long ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Long getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Long totalTax) {
		this.totalTax = totalTax;
	}

	public Long getFuelTax() {
		return fuelTax;
	}

	public void setFuelTax(Long fuelTax) {
		this.fuelTax = fuelTax;
	}

	public Long getConstructionFee() {
		return constructionFee;
	}

	public void setConstructionFee(Long constructionFee) {
		this.constructionFee = constructionFee;
	}

	public String getCrsPnr() {
		return crsPnr;
	}

	public void setCrsPnr(String crsPnr) {
		this.crsPnr = crsPnr;
	}

	public String getTicketOfficeNo() {
		return ticketOfficeNo;
	}

	public void setTicketOfficeNo(String ticketOfficeNo) {
		this.ticketOfficeNo = ticketOfficeNo;
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

	public String getCabinCode() {
		return cabinCode;
	}

	public void setCabinCode(String cabinCode) {
		this.cabinCode = cabinCode;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getDoucmentType() {
		return doucmentType;
	}

	public void setDoucmentType(String doucmentType) {
		this.doucmentType = doucmentType;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOrigRecordId() {
		return origRecordId;
	}

	public void setOrigRecordId(String origRecordId) {
		this.origRecordId = origRecordId;
	}

}