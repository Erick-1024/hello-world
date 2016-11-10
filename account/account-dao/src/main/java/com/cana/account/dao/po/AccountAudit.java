/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.account.dao.po;

import java.io.Serializable;
import java.util.Date;

public class AccountAudit implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *开户申请id
    */
    private String accountApplyId;

    /**
    *审核人id
    */
    private String auditorId;

    /**
    *审核状态，二进制
    */
    private Integer auditStatus;

    /**
    *其他审核意见
    */
    private String auditMessage;

    /**
    *审核分配的用户角色
    */
    private String roleId;

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
    *开户申请id
    */
    public String getAccountApplyId() {
        return accountApplyId;
    }

    /**
    *开户申请id
    */
    public void setAccountApplyId(String accountApplyId) {
        this.accountApplyId = accountApplyId == null ? null : accountApplyId.trim();
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
    *审核状态，二进制
    */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
    *审核状态，二进制
    */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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
    *审核分配的用户角色
    */
    public String getRoleId() {
        return roleId;
    }

    /**
    *审核分配的用户角色
    */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
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
        AccountAudit other = (AccountAudit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountApplyId() == null ? other.getAccountApplyId() == null : this.getAccountApplyId().equals(other.getAccountApplyId()))
            && (this.getAuditorId() == null ? other.getAuditorId() == null : this.getAuditorId().equals(other.getAuditorId()))
            && (this.getAuditStatus() == null ? other.getAuditStatus() == null : this.getAuditStatus().equals(other.getAuditStatus()))
            && (this.getAuditMessage() == null ? other.getAuditMessage() == null : this.getAuditMessage().equals(other.getAuditMessage()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccountApplyId() == null) ? 0 : getAccountApplyId().hashCode());
        result = prime * result + ((getAuditorId() == null) ? 0 : getAuditorId().hashCode());
        result = prime * result + ((getAuditStatus() == null) ? 0 : getAuditStatus().hashCode());
        result = prime * result + ((getAuditMessage() == null) ? 0 : getAuditMessage().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}