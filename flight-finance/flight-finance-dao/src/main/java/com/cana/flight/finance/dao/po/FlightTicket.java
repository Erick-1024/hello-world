/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.flight.finance.dao.po;

import java.io.Serializable;
import java.util.Date;

public class FlightTicket implements Serializable {
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
     *单位：分。对于正常和改签票来说，代表最新的总税款，不是差额。对于退票来说，代表退的总税款。
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

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *该客票是否有价值（未被改签、未被退票、还有航班未起飞）
     */
    private Boolean isValuable;

    /**
     *导致没有价值的原因。退票：REFUND、改签：ENDORSE、航班全部起飞：TAKE_OFF。优先级：退票>改签>航班全部起飞
     */
    private String noValueCause;

    /**
     *未起飞的航班数量
     */
    private Integer noTakeOffNumber;

    private static final long serialVersionUID = 1L;

    /**
     *记录ID（保证全局唯一）。主键
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     *记录ID（保证全局唯一）。主键
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    /**
     *采购商ID，唯一
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *采购商ID，唯一
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     *true:国内，false:国际
     */
    public Boolean getIsDomestic() {
        return isDomestic;
    }

    /**
     *true:国内，false:国际
     */
    public void setIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }

    /**
     *订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *订单类型（normal:正常订单，refund:退票，endorse:改签）
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     *订单类型（normal:正常订单，refund:退票，endorse:改签）
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     *是否是调帐单（true:调帐单，false:非调帐单）
     */
    public Boolean getIsAdjust() {
        return isAdjust;
    }

    /**
     *是否是调帐单（true:调帐单，false:非调帐单）
     */
    public void setIsAdjust(Boolean isAdjust) {
        this.isAdjust = isAdjust;
    }

    /**
     *完成出票日期（格式：2016-01-18 07:50:00, 对于正常票来说指的是完成出票时间，对于退票来说指的是完成退票时间，对于改签来说指的是完成改签时间，对于调账来说指的是完成调账时间）
     */
    public String getCompleteIssueTime() {
        return completeIssueTime;
    }

    /**
     *完成出票日期（格式：2016-01-18 07:50:00, 对于正常票来说指的是完成出票时间，对于退票来说指的是完成退票时间，对于改签来说指的是完成改签时间，对于调账来说指的是完成调账时间）
     */
    public void setCompleteIssueTime(String completeIssueTime) {
        this.completeIssueTime = completeIssueTime == null ? null : completeIssueTime.trim();
    }

    /**
     *票号
     */
    public String getTicketNo() {
        return ticketNo;
    }

    /**
     *票号
     */
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo == null ? null : ticketNo.trim();
    }

    /**
     *票序号
     */
    public String getConjunctionTicketSeqNo() {
        return conjunctionTicketSeqNo;
    }

    /**
     *票序号
     */
    public void setConjunctionTicketSeqNo(String conjunctionTicketSeqNo) {
        this.conjunctionTicketSeqNo = conjunctionTicketSeqNo == null ? null : conjunctionTicketSeqNo.trim();
    }

    /**
     *供应商名称（格式：不夜城）
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     *供应商名称（格式：不夜城）
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     *付款方式（credit:信用，other:其他）
     */
    public String getPayType() {
        return payType;
    }

    /**
     *付款方式（credit:信用，other:其他）
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     *应收款。单位：分。 对于正常单来说，代表客票金额；对于退票来说，代表退票金额；对于改签来说，代表改签总费用；对于调账来说，代表调账产生的差额
     */
    public Long getTotalAmount() {
        return totalAmount;
    }

    /**
     *应收款。单位：分。 对于正常单来说，代表客票金额；对于退票来说，代表退票金额；对于改签来说，代表改签总费用；对于调账来说，代表调账产生的差额
     */
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     *票款。单位：分。 对于正常和改签票来说，代表最新的票面价，不是差额。对于退票来说，代表退的票面价。
     */
    public Long getTicketPrice() {
        return ticketPrice;
    }

    /**
     *票款。单位：分。 对于正常和改签票来说，代表最新的票面价，不是差额。对于退票来说，代表退的票面价。
     */
    public void setTicketPrice(Long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     *单位：分。对于正常和改签票来说，代表最新的总税款，不是差额。对于退票来说，代表退的总税款。
     */
    public Long getTotalTax() {
        return totalTax;
    }

    /**
     *单位：分。对于正常和改签票来说，代表最新的总税款，不是差额。对于退票来说，代表退的总税款。
     */
    public void setTotalTax(Long totalTax) {
        this.totalTax = totalTax;
    }

    /**
     *燃油。单位：分。 对于正常和改签票来说，代表最新的燃油，不是差额。对于退票来说，代表退的燃油。
     */
    public Long getFuelTax() {
        return fuelTax;
    }

    /**
     *燃油。单位：分。 对于正常和改签票来说，代表最新的燃油，不是差额。对于退票来说，代表退的燃油。
     */
    public void setFuelTax(Long fuelTax) {
        this.fuelTax = fuelTax;
    }

    /**
     *基建。单位：分。 对于正常和改签票来说，代表最新的基建，不是差额。对于退票来说，代表退的基建。
     */
    public Long getConstructionFee() {
        return constructionFee;
    }

    /**
     *基建。单位：分。 对于正常和改签票来说，代表最新的基建，不是差额。对于退票来说，代表退的基建。
     */
    public void setConstructionFee(Long constructionFee) {
        this.constructionFee = constructionFee;
    }

    /**
     *PNR
     */
    public String getCrsPnr() {
        return crsPnr;
    }

    /**
     *PNR
     */
    public void setCrsPnr(String crsPnr) {
        this.crsPnr = crsPnr == null ? null : crsPnr.trim();
    }

    /**
     *OFFIC号
     */
    public String getTicketOfficeNo() {
        return ticketOfficeNo;
    }

    /**
     *OFFIC号
     */
    public void setTicketOfficeNo(String ticketOfficeNo) {
        this.ticketOfficeNo = ticketOfficeNo == null ? null : ticketOfficeNo.trim();
    }

    /**
     *航班号（格式：CA1858）。多个用英文逗号分隔
     */
    public String getAirline() {
        return airline;
    }

    /**
     *航班号（格式：CA1858）。多个用英文逗号分隔
     */
    public void setAirline(String airline) {
        this.airline = airline == null ? null : airline.trim();
    }

    /**
     *航程（例：PEKSHA，如有多段用“,”分隔）
     */
    public String getItinerary() {
        return itinerary;
    }

    /**
     *航程（例：PEKSHA，如有多段用“,”分隔）
     */
    public void setItinerary(String itinerary) {
        this.itinerary = itinerary == null ? null : itinerary.trim();
    }

    /**
     *所有航班日期（格式：yyyy-MM-dd HH:mm，如果多段用“,”分隔）
     */
    public String getDepartureDateTime() {
        return departureDateTime;
    }

    /**
     *所有航班日期（格式：yyyy-MM-dd HH:mm，如果多段用“,”分隔）
     */
    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime == null ? null : departureDateTime.trim();
    }

    /**
     *航位（多个用“,”分隔）
     */
    public String getCabinCode() {
        return cabinCode;
    }

    /**
     *航位（多个用“,”分隔）
     */
    public void setCabinCode(String cabinCode) {
        this.cabinCode = cabinCode == null ? null : cabinCode.trim();
    }

    /**
     *乘机人（如果用拼音表示，字之间用“/”分隔）
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     *乘机人（如果用拼音表示，字之间用“/”分隔）
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
    }

    /**
     *旅客类型（ADT:成人/INF:婴儿/CHD:儿童）
     */
    public String getPassengerType() {
        return passengerType;
    }

    /**
     *旅客类型（ADT:成人/INF:婴儿/CHD:儿童）
     */
    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType == null ? null : passengerType.trim();
    }

    /**
     *证件类型。国内： NI("身份证"), PP("护照"), ID("其他证件") , PP("护照")
     */
    public String getDoucmentType() {
        return doucmentType;
    }

    /**
     *证件类型。国内： NI("身份证"), PP("护照"), ID("其他证件") , PP("护照")
     */
    public void setDoucmentType(String doucmentType) {
        this.doucmentType = doucmentType == null ? null : doucmentType.trim();
    }

    /**
     *证件号码
     */
    public String getDocumentNo() {
        return documentNo;
    }

    /**
     *证件号码
     */
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo == null ? null : documentNo.trim();
    }

    /**
     *乘客手机号
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *乘客手机号
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    /**
     *原记录Id。客票被改签或退票，改签客票或退票记录中要包含原客票id。例如：正常票（记录号：R1）被改签了，改签票(记录号：R2)的origRecordId的值为R1，如果该改签票又被退掉了，退票记录的origRecordId的值为R2
     */
    public String getOrigRecordId() {
        return origRecordId;
    }

    /**
     *原记录Id。客票被改签或退票，改签客票或退票记录中要包含原客票id。例如：正常票（记录号：R1）被改签了，改签票(记录号：R2)的origRecordId的值为R1，如果该改签票又被退掉了，退票记录的origRecordId的值为R2
     */
    public void setOrigRecordId(String origRecordId) {
        this.origRecordId = origRecordId == null ? null : origRecordId.trim();
    }

    /**
     *创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *该客票是否有价值（未被改签、未被退票、还有航班未起飞）
     */
    public Boolean getIsValuable() {
        return isValuable;
    }

    /**
     *该客票是否有价值（未被改签、未被退票、还有航班未起飞）
     */
    public void setIsValuable(Boolean isValuable) {
        this.isValuable = isValuable;
    }

    /**
     *导致没有价值的原因。退票：REFUND、改签：ENDORSE、航班全部起飞：TAKE_OFF。优先级：退票>改签>航班全部起飞
     */
    public String getNoValueCause() {
        return noValueCause;
    }

    /**
     *导致没有价值的原因。退票：REFUND、改签：ENDORSE、航班全部起飞：TAKE_OFF。优先级：退票>改签>航班全部起飞
     */
    public void setNoValueCause(String noValueCause) {
        this.noValueCause = noValueCause == null ? null : noValueCause.trim();
    }

    /**
     *未起飞的航班数量
     */
    public Integer getNoTakeOffNumber() {
        return noTakeOffNumber;
    }

    /**
     *未起飞的航班数量
     */
    public void setNoTakeOffNumber(Integer noTakeOffNumber) {
        this.noTakeOffNumber = noTakeOffNumber;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FlightTicket other = (FlightTicket) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getIsDomestic() == null ? other.getIsDomestic() == null : this.getIsDomestic().equals(other.getIsDomestic()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getIsAdjust() == null ? other.getIsAdjust() == null : this.getIsAdjust().equals(other.getIsAdjust()))
            && (this.getCompleteIssueTime() == null ? other.getCompleteIssueTime() == null : this.getCompleteIssueTime().equals(other.getCompleteIssueTime()))
            && (this.getTicketNo() == null ? other.getTicketNo() == null : this.getTicketNo().equals(other.getTicketNo()))
            && (this.getConjunctionTicketSeqNo() == null ? other.getConjunctionTicketSeqNo() == null : this.getConjunctionTicketSeqNo().equals(other.getConjunctionTicketSeqNo()))
            && (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getTicketPrice() == null ? other.getTicketPrice() == null : this.getTicketPrice().equals(other.getTicketPrice()))
            && (this.getTotalTax() == null ? other.getTotalTax() == null : this.getTotalTax().equals(other.getTotalTax()))
            && (this.getFuelTax() == null ? other.getFuelTax() == null : this.getFuelTax().equals(other.getFuelTax()))
            && (this.getConstructionFee() == null ? other.getConstructionFee() == null : this.getConstructionFee().equals(other.getConstructionFee()))
            && (this.getCrsPnr() == null ? other.getCrsPnr() == null : this.getCrsPnr().equals(other.getCrsPnr()))
            && (this.getTicketOfficeNo() == null ? other.getTicketOfficeNo() == null : this.getTicketOfficeNo().equals(other.getTicketOfficeNo()))
            && (this.getAirline() == null ? other.getAirline() == null : this.getAirline().equals(other.getAirline()))
            && (this.getItinerary() == null ? other.getItinerary() == null : this.getItinerary().equals(other.getItinerary()))
            && (this.getDepartureDateTime() == null ? other.getDepartureDateTime() == null : this.getDepartureDateTime().equals(other.getDepartureDateTime()))
            && (this.getCabinCode() == null ? other.getCabinCode() == null : this.getCabinCode().equals(other.getCabinCode()))
            && (this.getPassengerName() == null ? other.getPassengerName() == null : this.getPassengerName().equals(other.getPassengerName()))
            && (this.getPassengerType() == null ? other.getPassengerType() == null : this.getPassengerType().equals(other.getPassengerType()))
            && (this.getDoucmentType() == null ? other.getDoucmentType() == null : this.getDoucmentType().equals(other.getDoucmentType()))
            && (this.getDocumentNo() == null ? other.getDocumentNo() == null : this.getDocumentNo().equals(other.getDocumentNo()))
            && (this.getMobileNo() == null ? other.getMobileNo() == null : this.getMobileNo().equals(other.getMobileNo()))
            && (this.getOrigRecordId() == null ? other.getOrigRecordId() == null : this.getOrigRecordId().equals(other.getOrigRecordId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsValuable() == null ? other.getIsValuable() == null : this.getIsValuable().equals(other.getIsValuable()))
            && (this.getNoValueCause() == null ? other.getNoValueCause() == null : this.getNoValueCause().equals(other.getNoValueCause()))
            && (this.getNoTakeOffNumber() == null ? other.getNoTakeOffNumber() == null : this.getNoTakeOffNumber().equals(other.getNoTakeOffNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getIsDomestic() == null) ? 0 : getIsDomestic().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getIsAdjust() == null) ? 0 : getIsAdjust().hashCode());
        result = prime * result + ((getCompleteIssueTime() == null) ? 0 : getCompleteIssueTime().hashCode());
        result = prime * result + ((getTicketNo() == null) ? 0 : getTicketNo().hashCode());
        result = prime * result + ((getConjunctionTicketSeqNo() == null) ? 0 : getConjunctionTicketSeqNo().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getTicketPrice() == null) ? 0 : getTicketPrice().hashCode());
        result = prime * result + ((getTotalTax() == null) ? 0 : getTotalTax().hashCode());
        result = prime * result + ((getFuelTax() == null) ? 0 : getFuelTax().hashCode());
        result = prime * result + ((getConstructionFee() == null) ? 0 : getConstructionFee().hashCode());
        result = prime * result + ((getCrsPnr() == null) ? 0 : getCrsPnr().hashCode());
        result = prime * result + ((getTicketOfficeNo() == null) ? 0 : getTicketOfficeNo().hashCode());
        result = prime * result + ((getAirline() == null) ? 0 : getAirline().hashCode());
        result = prime * result + ((getItinerary() == null) ? 0 : getItinerary().hashCode());
        result = prime * result + ((getDepartureDateTime() == null) ? 0 : getDepartureDateTime().hashCode());
        result = prime * result + ((getCabinCode() == null) ? 0 : getCabinCode().hashCode());
        result = prime * result + ((getPassengerName() == null) ? 0 : getPassengerName().hashCode());
        result = prime * result + ((getPassengerType() == null) ? 0 : getPassengerType().hashCode());
        result = prime * result + ((getDoucmentType() == null) ? 0 : getDoucmentType().hashCode());
        result = prime * result + ((getDocumentNo() == null) ? 0 : getDocumentNo().hashCode());
        result = prime * result + ((getMobileNo() == null) ? 0 : getMobileNo().hashCode());
        result = prime * result + ((getOrigRecordId() == null) ? 0 : getOrigRecordId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsValuable() == null) ? 0 : getIsValuable().hashCode());
        result = prime * result + ((getNoValueCause() == null) ? 0 : getNoValueCause().hashCode());
        result = prime * result + ((getNoTakeOffNumber() == null) ? 0 : getNoTakeOffNumber().hashCode());
        return result;
    }
}