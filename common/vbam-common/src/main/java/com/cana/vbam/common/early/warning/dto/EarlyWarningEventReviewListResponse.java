package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.util.Date;

public class EarlyWarningEventReviewListResponse implements Serializable {

	private static final long serialVersionUID = 2722376787809075222L;

	/**
     *主键
     */
    private String id;

    /**
     *融资客户Id
     */
    private String financeId;

    /**
     *融资客户公司名称
     */
    private String financeCompany;
    
    /**
     * 外部平台客户ID
     */
    private String outCustomerId;
    
    /**
     * 外部客户名称
     */
    private String outCustomerName;

    /**
     *预警事件id
     */
    private String eventId;

    /**
     *预警种类
     */
    private String eventType;
    
    /**
     * 事件种类
     */
    private String eventSubType;

    /**
     *申请类型：新增，解除
     */
    private String applyType;

    /**
     *审核时间
     */
    private Date reviewTime;

    /**
     *审核人的真实姓名
     */
    private String reviewerRealName;

    /**
     *状态
     */
    private String state;
    
    private String applyTypeDesc;
	
    private String stateDesc;
    
    private String earlyWarningLevel;
    
    private String earlyWarningLevelDesc;
    
    /**
     *申请时间
     */
    private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewerRealName() {
		return reviewerRealName;
	}

	public void setReviewerRealName(String reviewerRealName) {
		this.reviewerRealName = reviewerRealName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getApplyTypeDesc() {
		return applyTypeDesc;
	}

	public void setApplyTypeDesc(String applyTypeDesc) {
		this.applyTypeDesc = applyTypeDesc;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getEarlyWarningLevel() {
		return earlyWarningLevel;
	}

	public void setEarlyWarningLevel(String earlyWarningLevel) {
		this.earlyWarningLevel = earlyWarningLevel;
	}

	public String getEarlyWarningLevelDesc() {
		return earlyWarningLevelDesc;
	}

	public void setEarlyWarningLevelDesc(String earlyWarningLevelDesc) {
		this.earlyWarningLevelDesc = earlyWarningLevelDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEventSubType() {
		return eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}
    
}
