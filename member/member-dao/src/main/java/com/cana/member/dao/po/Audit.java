/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.member.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Audit implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *审核人id
     */
    private String auditorId;

    /**
     *其他审核意见
     */
    private String auditMessage;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *审核状态，二进制（第一位：公司名称核对结果，第二位：营业执照核对结果，第三位：组织机构核对结果，第四位：审核结果）
     */
    private Integer auditStatus;

    /**
     *被审批者
     */
    private String customerId;

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
     *审核人id
     */
    public String getAuditorId() {
        return auditorId;
    }

    /**
     *审核人id
     */
    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId == null ? null : auditorId.trim();
    }

    /**
     *其他审核意见
     */
    public String getAuditMessage() {
        return auditMessage;
    }

    /**
     *其他审核意见
     */
    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage == null ? null : auditMessage.trim();
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
     *审核状态，二进制（第一位：公司名称核对结果，第二位：营业执照核对结果，第三位：组织机构核对结果，第四位：审核结果）
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     *审核状态，二进制（第一位：公司名称核对结果，第二位：营业执照核对结果，第三位：组织机构核对结果，第四位：审核结果）
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     *被审批者
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *被审批者
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
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
        Audit other = (Audit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAuditorId() == null ? other.getAuditorId() == null : this.getAuditorId().equals(other.getAuditorId()))
            && (this.getAuditMessage() == null ? other.getAuditMessage() == null : this.getAuditMessage().equals(other.getAuditMessage()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getAuditStatus() == null ? other.getAuditStatus() == null : this.getAuditStatus().equals(other.getAuditStatus()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAuditorId() == null) ? 0 : getAuditorId().hashCode());
        result = prime * result + ((getAuditMessage() == null) ? 0 : getAuditMessage().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAuditStatus() == null) ? 0 : getAuditStatus().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        return result;
    }
}