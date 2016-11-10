package com.cana.flight.finance.common.dto;

import java.io.Serializable;
import java.util.Date;

public class CreditTradeDTO implements Serializable {

	private static final long serialVersionUID = 5645671881512049665L;

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
     *交易金额,正数
     */
    private String fee;

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
     *汇总ID，即放款编号（支付单时为本单对应的放款编号、退款单时为原单的放款编号）
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
    
    private String tradeStatusDesc;
    
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
     *交易金额。正数
     */
    public String getFee() {
        return fee;
    }

    /**
     *交易金额。正数
     */
    public void setFee(String fee) {
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
     *汇总ID，即放款编号（支付单时为本单对应的放款编号、退款单时为原单的放款编号）
     */
    public String getSummaryId() {
        return summaryId;
    }

    /**
     *汇总ID，即放款编号（支付单时为本单对应的放款编号、退款单时为原单的放款编号）
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

	public String getFinaceCustomerId() {
		return finaceCustomerId;
	}

	public void setFinaceCustomerId(String finaceCustomerId) {
		this.finaceCustomerId = finaceCustomerId;
	}

	public String getFinaceCustomerName() {
		return finaceCustomerName;
	}

	public void setFinaceCustomerName(String finaceCustomerName) {
		this.finaceCustomerName = finaceCustomerName;
	}

	public String getTradeStatusDesc() {
		return tradeStatusDesc;
	}

	public void setTradeStatusDesc(String tradeStatusDesc) {
		this.tradeStatusDesc = tradeStatusDesc;
	}

}