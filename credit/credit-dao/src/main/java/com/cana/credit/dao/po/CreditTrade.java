/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.credit.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CreditTrade implements Serializable {
    /**
     *主键、Cana平台的交易流水号
     */
    private String id;

    /**
     *外部唯一交易编号
     */
    private String outTradeNo;

    /**
     *外部客户ID
     */
    private String outCustomerId;

    /**
     *外部交易时间
     */
    private Date outTradeTime;

    /**
     *外部客户名称
     */
    private String outCustomerName;

    /**
     *外部原交易号（退款时必须）
     */
    private String outOriginTradeNo;

    /**
     *融资商客户id
     */
    private String finaceCustomerId;

    /**
     *融资商客户名称
     */
    private String finaceCustomerName;

    /**
     *机构
     */
    private String institution;

    /**
     *交易请求发起方传送的签名
     */
    private String sign;

    /**
     *交易金额，精确到分。正数
     */
    private Long fee;

    /**
     *订单信息
     */
    private String orderInfo;

    /**
     *交易状态异步通知地址
     */
    private String notifyUrl;

    /**
     *交易类型。PAYMENT: 支付；REFUND：退款
     */
    private String tradeType;

    /**
     *交易状态。"SUCCESS"：交易成功；"HANDLING"：处理中；"FAIL"：交易失败
     */
    private String tradeStatus;

    /**
     *汇总ID，即放款ID（支付单时为本单对应的放款ID、退款单时为原单的放款ID）
     */
    private String summaryId;

    /**
     *交易开始时间
     */
    private Date tradeStartTime;

    /**
     *交易结束时间
     */
    private Date tradeEndTime;

    /**
     *原支付单交易完成时间，退款单必须
     */
    private Date originTradeEndTime;

    private static final long serialVersionUID = 1L;

    /**
     *主键、Cana平台的交易流水号
     */
    public String getId() {
        return id;
    }

    /**
     *主键、Cana平台的交易流水号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *外部唯一交易编号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     *外部唯一交易编号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     *外部客户ID
     */
    public String getOutCustomerId() {
        return outCustomerId;
    }

    /**
     *外部客户ID
     */
    public void setOutCustomerId(String outCustomerId) {
        this.outCustomerId = outCustomerId == null ? null : outCustomerId.trim();
    }

    /**
     *外部交易时间
     */
    public Date getOutTradeTime() {
        return outTradeTime;
    }

    /**
     *外部交易时间
     */
    public void setOutTradeTime(Date outTradeTime) {
        this.outTradeTime = outTradeTime;
    }

    /**
     *外部客户名称
     */
    public String getOutCustomerName() {
        return outCustomerName;
    }

    /**
     *外部客户名称
     */
    public void setOutCustomerName(String outCustomerName) {
        this.outCustomerName = outCustomerName == null ? null : outCustomerName.trim();
    }

    /**
     *外部原交易号（退款时必须）
     */
    public String getOutOriginTradeNo() {
        return outOriginTradeNo;
    }

    /**
     *外部原交易号（退款时必须）
     */
    public void setOutOriginTradeNo(String outOriginTradeNo) {
        this.outOriginTradeNo = outOriginTradeNo == null ? null : outOriginTradeNo.trim();
    }

    /**
     *融资商客户id
     */
    public String getFinaceCustomerId() {
        return finaceCustomerId;
    }

    /**
     *融资商客户id
     */
    public void setFinaceCustomerId(String finaceCustomerId) {
        this.finaceCustomerId = finaceCustomerId == null ? null : finaceCustomerId.trim();
    }

    /**
     *融资商客户名称
     */
    public String getFinaceCustomerName() {
        return finaceCustomerName;
    }

    /**
     *融资商客户名称
     */
    public void setFinaceCustomerName(String finaceCustomerName) {
        this.finaceCustomerName = finaceCustomerName == null ? null : finaceCustomerName.trim();
    }

    /**
     *机构
     */
    public String getInstitution() {
        return institution;
    }

    /**
     *机构
     */
    public void setInstitution(String institution) {
        this.institution = institution == null ? null : institution.trim();
    }

    /**
     *交易请求发起方传送的签名
     */
    public String getSign() {
        return sign;
    }

    /**
     *交易请求发起方传送的签名
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    /**
     *交易金额，精确到分。正数
     */
    public Long getFee() {
        return fee;
    }

    /**
     *交易金额，精确到分。正数
     */
    public void setFee(Long fee) {
        this.fee = fee;
    }

    /**
     *订单信息
     */
    public String getOrderInfo() {
        return orderInfo;
    }

    /**
     *订单信息
     */
    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo == null ? null : orderInfo.trim();
    }

    /**
     *交易状态异步通知地址
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     *交易状态异步通知地址
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    /**
     *交易类型。PAYMENT: 支付；REFUND：退款
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     *交易类型。PAYMENT: 支付；REFUND：退款
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     *交易状态。"SUCCESS"：交易成功；"HANDLING"：处理中；"FAIL"：交易失败
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     *交易状态。"SUCCESS"：交易成功；"HANDLING"：处理中；"FAIL"：交易失败
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    /**
     *汇总ID，即放款ID（支付单时为本单对应的放款ID、退款单时为原单的放款ID）
     */
    public String getSummaryId() {
        return summaryId;
    }

    /**
     *汇总ID，即放款ID（支付单时为本单对应的放款ID、退款单时为原单的放款ID）
     */
    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId == null ? null : summaryId.trim();
    }

    /**
     *交易开始时间
     */
    public Date getTradeStartTime() {
        return tradeStartTime;
    }

    /**
     *交易开始时间
     */
    public void setTradeStartTime(Date tradeStartTime) {
        this.tradeStartTime = tradeStartTime;
    }

    /**
     *交易结束时间
     */
    public Date getTradeEndTime() {
        return tradeEndTime;
    }

    /**
     *交易结束时间
     */
    public void setTradeEndTime(Date tradeEndTime) {
        this.tradeEndTime = tradeEndTime;
    }

    /**
     *原支付单交易完成时间，退款单必须
     */
    public Date getOriginTradeEndTime() {
        return originTradeEndTime;
    }

    /**
     *原支付单交易完成时间，退款单必须
     */
    public void setOriginTradeEndTime(Date originTradeEndTime) {
        this.originTradeEndTime = originTradeEndTime;
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
        CreditTrade other = (CreditTrade) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getOutCustomerId() == null ? other.getOutCustomerId() == null : this.getOutCustomerId().equals(other.getOutCustomerId()))
            && (this.getOutTradeTime() == null ? other.getOutTradeTime() == null : this.getOutTradeTime().equals(other.getOutTradeTime()))
            && (this.getOutCustomerName() == null ? other.getOutCustomerName() == null : this.getOutCustomerName().equals(other.getOutCustomerName()))
            && (this.getOutOriginTradeNo() == null ? other.getOutOriginTradeNo() == null : this.getOutOriginTradeNo().equals(other.getOutOriginTradeNo()))
            && (this.getFinaceCustomerId() == null ? other.getFinaceCustomerId() == null : this.getFinaceCustomerId().equals(other.getFinaceCustomerId()))
            && (this.getFinaceCustomerName() == null ? other.getFinaceCustomerName() == null : this.getFinaceCustomerName().equals(other.getFinaceCustomerName()))
            && (this.getInstitution() == null ? other.getInstitution() == null : this.getInstitution().equals(other.getInstitution()))
            && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getOrderInfo() == null ? other.getOrderInfo() == null : this.getOrderInfo().equals(other.getOrderInfo()))
            && (this.getNotifyUrl() == null ? other.getNotifyUrl() == null : this.getNotifyUrl().equals(other.getNotifyUrl()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getTradeStatus() == null ? other.getTradeStatus() == null : this.getTradeStatus().equals(other.getTradeStatus()))
            && (this.getSummaryId() == null ? other.getSummaryId() == null : this.getSummaryId().equals(other.getSummaryId()))
            && (this.getTradeStartTime() == null ? other.getTradeStartTime() == null : this.getTradeStartTime().equals(other.getTradeStartTime()))
            && (this.getTradeEndTime() == null ? other.getTradeEndTime() == null : this.getTradeEndTime().equals(other.getTradeEndTime()))
            && (this.getOriginTradeEndTime() == null ? other.getOriginTradeEndTime() == null : this.getOriginTradeEndTime().equals(other.getOriginTradeEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getOutCustomerId() == null) ? 0 : getOutCustomerId().hashCode());
        result = prime * result + ((getOutTradeTime() == null) ? 0 : getOutTradeTime().hashCode());
        result = prime * result + ((getOutCustomerName() == null) ? 0 : getOutCustomerName().hashCode());
        result = prime * result + ((getOutOriginTradeNo() == null) ? 0 : getOutOriginTradeNo().hashCode());
        result = prime * result + ((getFinaceCustomerId() == null) ? 0 : getFinaceCustomerId().hashCode());
        result = prime * result + ((getFinaceCustomerName() == null) ? 0 : getFinaceCustomerName().hashCode());
        result = prime * result + ((getInstitution() == null) ? 0 : getInstitution().hashCode());
        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getOrderInfo() == null) ? 0 : getOrderInfo().hashCode());
        result = prime * result + ((getNotifyUrl() == null) ? 0 : getNotifyUrl().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getTradeStatus() == null) ? 0 : getTradeStatus().hashCode());
        result = prime * result + ((getSummaryId() == null) ? 0 : getSummaryId().hashCode());
        result = prime * result + ((getTradeStartTime() == null) ? 0 : getTradeStartTime().hashCode());
        result = prime * result + ((getTradeEndTime() == null) ? 0 : getTradeEndTime().hashCode());
        result = prime * result + ((getOriginTradeEndTime() == null) ? 0 : getOriginTradeEndTime().hashCode());
        return result;
    }
}