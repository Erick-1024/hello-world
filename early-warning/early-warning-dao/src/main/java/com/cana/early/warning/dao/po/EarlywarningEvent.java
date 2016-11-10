/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.early.warning.dao.po;

import java.io.Serializable;
import java.util.Date;

public class EarlywarningEvent implements Serializable {
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
     *预警种类
     */
    private String type;

    /**
     *预警种类下面的子类型
     */
    private String subType;

    /**
     *该事件的预警等级
     */
    private String level;

    /**
     *录入人的用户id
     */
    private String entryUserId;

    /**
     *录入人的真实姓名
     */
    private String entryRealName;

    /**
     *录入事件的审核通过时间
     */
    private Date entryReviewTime;

    /**
     *事件解除人的用户id
     */
    private String cancelUserId;

    /**
     *事件解除人的真实姓名
     */
    private String cancelRealName;

    /**
     *解除事件的审核通过时间
     */
    private Date cancelReviewTime;

    /**
     *发生时间
     */
    private Date occurTime;

    /**
     *金额。如：法院判决金额
     */
    private Long amount;

    /**
     *事件描述
     */
    private String represent;

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
     *预警种类
     */
    public String getType() {
        return type;
    }

    /**
     *预警种类
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *预警种类下面的子类型
     */
    public String getSubType() {
        return subType;
    }

    /**
     *预警种类下面的子类型
     */
    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    /**
     *该事件的预警等级
     */
    public String getLevel() {
        return level;
    }

    /**
     *该事件的预警等级
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     *录入人的用户id
     */
    public String getEntryUserId() {
        return entryUserId;
    }

    /**
     *录入人的用户id
     */
    public void setEntryUserId(String entryUserId) {
        this.entryUserId = entryUserId == null ? null : entryUserId.trim();
    }

    /**
     *录入人的真实姓名
     */
    public String getEntryRealName() {
        return entryRealName;
    }

    /**
     *录入人的真实姓名
     */
    public void setEntryRealName(String entryRealName) {
        this.entryRealName = entryRealName == null ? null : entryRealName.trim();
    }

    /**
     *录入事件的审核通过时间
     */
    public Date getEntryReviewTime() {
        return entryReviewTime;
    }

    /**
     *录入事件的审核通过时间
     */
    public void setEntryReviewTime(Date entryReviewTime) {
        this.entryReviewTime = entryReviewTime;
    }

    /**
     *事件解除人的用户id
     */
    public String getCancelUserId() {
        return cancelUserId;
    }

    /**
     *事件解除人的用户id
     */
    public void setCancelUserId(String cancelUserId) {
        this.cancelUserId = cancelUserId == null ? null : cancelUserId.trim();
    }

    /**
     *事件解除人的真实姓名
     */
    public String getCancelRealName() {
        return cancelRealName;
    }

    /**
     *事件解除人的真实姓名
     */
    public void setCancelRealName(String cancelRealName) {
        this.cancelRealName = cancelRealName == null ? null : cancelRealName.trim();
    }

    /**
     *解除事件的审核通过时间
     */
    public Date getCancelReviewTime() {
        return cancelReviewTime;
    }

    /**
     *解除事件的审核通过时间
     */
    public void setCancelReviewTime(Date cancelReviewTime) {
        this.cancelReviewTime = cancelReviewTime;
    }

    /**
     *发生时间
     */
    public Date getOccurTime() {
        return occurTime;
    }

    /**
     *发生时间
     */
    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    /**
     *金额。如：法院判决金额
     */
    public Long getAmount() {
        return amount;
    }

    /**
     *金额。如：法院判决金额
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     *事件描述
     */
    public String getRepresent() {
        return represent;
    }

    /**
     *事件描述
     */
    public void setRepresent(String represent) {
        this.represent = represent == null ? null : represent.trim();
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
        EarlywarningEvent other = (EarlywarningEvent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getFinanceId() == null ? other.getFinanceId() == null : this.getFinanceId().equals(other.getFinanceId()))
            && (this.getFinanceCompany() == null ? other.getFinanceCompany() == null : this.getFinanceCompany().equals(other.getFinanceCompany()))
            && (this.getOutCustomerId() == null ? other.getOutCustomerId() == null : this.getOutCustomerId().equals(other.getOutCustomerId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getSubType() == null ? other.getSubType() == null : this.getSubType().equals(other.getSubType()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getEntryUserId() == null ? other.getEntryUserId() == null : this.getEntryUserId().equals(other.getEntryUserId()))
            && (this.getEntryRealName() == null ? other.getEntryRealName() == null : this.getEntryRealName().equals(other.getEntryRealName()))
            && (this.getEntryReviewTime() == null ? other.getEntryReviewTime() == null : this.getEntryReviewTime().equals(other.getEntryReviewTime()))
            && (this.getCancelUserId() == null ? other.getCancelUserId() == null : this.getCancelUserId().equals(other.getCancelUserId()))
            && (this.getCancelRealName() == null ? other.getCancelRealName() == null : this.getCancelRealName().equals(other.getCancelRealName()))
            && (this.getCancelReviewTime() == null ? other.getCancelReviewTime() == null : this.getCancelReviewTime().equals(other.getCancelReviewTime()))
            && (this.getOccurTime() == null ? other.getOccurTime() == null : this.getOccurTime().equals(other.getOccurTime()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getRepresent() == null ? other.getRepresent() == null : this.getRepresent().equals(other.getRepresent()))
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
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getSubType() == null) ? 0 : getSubType().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getEntryUserId() == null) ? 0 : getEntryUserId().hashCode());
        result = prime * result + ((getEntryRealName() == null) ? 0 : getEntryRealName().hashCode());
        result = prime * result + ((getEntryReviewTime() == null) ? 0 : getEntryReviewTime().hashCode());
        result = prime * result + ((getCancelUserId() == null) ? 0 : getCancelUserId().hashCode());
        result = prime * result + ((getCancelRealName() == null) ? 0 : getCancelRealName().hashCode());
        result = prime * result + ((getCancelReviewTime() == null) ? 0 : getCancelReviewTime().hashCode());
        result = prime * result + ((getOccurTime() == null) ? 0 : getOccurTime().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getRepresent() == null) ? 0 : getRepresent().hashCode());
        result = prime * result + ((getExtraData() == null) ? 0 : getExtraData().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}