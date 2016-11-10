/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.early.warning.dao.po;

import java.io.Serializable;
import java.util.Date;

public class EarlywarningEventReview implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *产品id
     */
    private String productId;

    /**
     *融资客户Id
     */
    private String financeId;

    /**
     *融资客户公司名称
     */
    private String financeCompany;

    /**
     *外部平台客户ID
     */
    private String outCustomerId;

    /**
     *预警事件id
     */
    private String eventId;

    /**
     *预警种类
     */
    private String eventType;

    /**
     *预警种类下面的子类型
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
     *审核人的用户id
     */
    private String reviewerUserId;

    /**
     *审核人的真实姓名
     */
    private String reviewerRealName;

    /**
     *该事件审核完成之前的预警等级
     */
    private String prevLevel;

    /**
     *附加数据
     */
    private String extraData;

    /**
     *状态
     */
    private String state;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *主键
     */
    public String getId() {
        return id;
    }

    /**
     *主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *产品id
     */
    public String getProductId() {
        return productId;
    }

    /**
     *产品id
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     *融资客户Id
     */
    public String getFinanceId() {
        return financeId;
    }

    /**
     *融资客户Id
     */
    public void setFinanceId(String financeId) {
        this.financeId = financeId == null ? null : financeId.trim();
    }

    /**
     *融资客户公司名称
     */
    public String getFinanceCompany() {
        return financeCompany;
    }

    /**
     *融资客户公司名称
     */
    public void setFinanceCompany(String financeCompany) {
        this.financeCompany = financeCompany == null ? null : financeCompany.trim();
    }

    /**
     *外部平台客户ID
     */
    public String getOutCustomerId() {
        return outCustomerId;
    }

    /**
     *外部平台客户ID
     */
    public void setOutCustomerId(String outCustomerId) {
        this.outCustomerId = outCustomerId == null ? null : outCustomerId.trim();
    }

    /**
     *预警事件id
     */
    public String getEventId() {
        return eventId;
    }

    /**
     *预警事件id
     */
    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    /**
     *预警种类
     */
    public String getEventType() {
        return eventType;
    }

    /**
     *预警种类
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     *预警种类下面的子类型
     */
    public String getEventSubType() {
        return eventSubType;
    }

    /**
     *预警种类下面的子类型
     */
    public void setEventSubType(String eventSubType) {
        this.eventSubType = eventSubType == null ? null : eventSubType.trim();
    }

    /**
     *申请类型：新增，解除
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     *申请类型：新增，解除
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    /**
     *审核时间
     */
    public Date getReviewTime() {
        return reviewTime;
    }

    /**
     *审核时间
     */
    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    /**
     *审核人的用户id
     */
    public String getReviewerUserId() {
        return reviewerUserId;
    }

    /**
     *审核人的用户id
     */
    public void setReviewerUserId(String reviewerUserId) {
        this.reviewerUserId = reviewerUserId == null ? null : reviewerUserId.trim();
    }

    /**
     *审核人的真实姓名
     */
    public String getReviewerRealName() {
        return reviewerRealName;
    }

    /**
     *审核人的真实姓名
     */
    public void setReviewerRealName(String reviewerRealName) {
        this.reviewerRealName = reviewerRealName == null ? null : reviewerRealName.trim();
    }

    /**
     *该事件审核完成之前的预警等级
     */
    public String getPrevLevel() {
        return prevLevel;
    }

    /**
     *该事件审核完成之前的预警等级
     */
    public void setPrevLevel(String prevLevel) {
        this.prevLevel = prevLevel == null ? null : prevLevel.trim();
    }

    /**
     *附加数据
     */
    public String getExtraData() {
        return extraData;
    }

    /**
     *附加数据
     */
    public void setExtraData(String extraData) {
        this.extraData = extraData == null ? null : extraData.trim();
    }

    /**
     *状态
     */
    public String getState() {
        return state;
    }

    /**
     *状态
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
        EarlywarningEventReview other = (EarlywarningEventReview) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getFinanceId() == null ? other.getFinanceId() == null : this.getFinanceId().equals(other.getFinanceId()))
            && (this.getFinanceCompany() == null ? other.getFinanceCompany() == null : this.getFinanceCompany().equals(other.getFinanceCompany()))
            && (this.getOutCustomerId() == null ? other.getOutCustomerId() == null : this.getOutCustomerId().equals(other.getOutCustomerId()))
            && (this.getEventId() == null ? other.getEventId() == null : this.getEventId().equals(other.getEventId()))
            && (this.getEventType() == null ? other.getEventType() == null : this.getEventType().equals(other.getEventType()))
            && (this.getEventSubType() == null ? other.getEventSubType() == null : this.getEventSubType().equals(other.getEventSubType()))
            && (this.getApplyType() == null ? other.getApplyType() == null : this.getApplyType().equals(other.getApplyType()))
            && (this.getReviewTime() == null ? other.getReviewTime() == null : this.getReviewTime().equals(other.getReviewTime()))
            && (this.getReviewerUserId() == null ? other.getReviewerUserId() == null : this.getReviewerUserId().equals(other.getReviewerUserId()))
            && (this.getReviewerRealName() == null ? other.getReviewerRealName() == null : this.getReviewerRealName().equals(other.getReviewerRealName()))
            && (this.getPrevLevel() == null ? other.getPrevLevel() == null : this.getPrevLevel().equals(other.getPrevLevel()))
            && (this.getExtraData() == null ? other.getExtraData() == null : this.getExtraData().equals(other.getExtraData()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getFinanceId() == null) ? 0 : getFinanceId().hashCode());
        result = prime * result + ((getFinanceCompany() == null) ? 0 : getFinanceCompany().hashCode());
        result = prime * result + ((getOutCustomerId() == null) ? 0 : getOutCustomerId().hashCode());
        result = prime * result + ((getEventId() == null) ? 0 : getEventId().hashCode());
        result = prime * result + ((getEventType() == null) ? 0 : getEventType().hashCode());
        result = prime * result + ((getEventSubType() == null) ? 0 : getEventSubType().hashCode());
        result = prime * result + ((getApplyType() == null) ? 0 : getApplyType().hashCode());
        result = prime * result + ((getReviewTime() == null) ? 0 : getReviewTime().hashCode());
        result = prime * result + ((getReviewerUserId() == null) ? 0 : getReviewerUserId().hashCode());
        result = prime * result + ((getReviewerRealName() == null) ? 0 : getReviewerRealName().hashCode());
        result = prime * result + ((getPrevLevel() == null) ? 0 : getPrevLevel().hashCode());
        result = prime * result + ((getExtraData() == null) ? 0 : getExtraData().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}