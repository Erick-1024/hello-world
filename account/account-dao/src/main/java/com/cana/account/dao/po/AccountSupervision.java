/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.account.dao.po;

import java.io.Serializable;
import java.util.Date;

public class AccountSupervision implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *账户id
    */
    private String accountId;

    /**
    *账户所属企业客户类型（FACTOR/FINACE）
    */
    private String userType;

    /**
    *账户所属企业id
    */
    private String companyId;

    /**
    *账户所属企业名称
    */
    private String companyName;

    /**
    *监管企业id
    */
    private String supervisorId;

    /**
    *监管企业名称
    */
    private String supervisorName;

    /**
    *监管关系开始时间
    */
    private Date supervisionStartTime;

    /**
    *监管关系解除时间
    */
    private Date supervisionEndTime;

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
    *账户id
    */
    public String getAccountId() {
        return accountId;
    }

    /**
    *账户id
    */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
    *账户所属企业客户类型（FACTOR/FINACE）
    */
    public String getUserType() {
        return userType;
    }

    /**
    *账户所属企业客户类型（FACTOR/FINACE）
    */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
    *账户所属企业id
    */
    public String getCompanyId() {
        return companyId;
    }

    /**
    *账户所属企业id
    */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
    *账户所属企业名称
    */
    public String getCompanyName() {
        return companyName;
    }

    /**
    *账户所属企业名称
    */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
    *监管企业id
    */
    public String getSupervisorId() {
        return supervisorId;
    }

    /**
    *监管企业id
    */
    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId == null ? null : supervisorId.trim();
    }

    /**
    *监管企业名称
    */
    public String getSupervisorName() {
        return supervisorName;
    }

    /**
    *监管企业名称
    */
    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName == null ? null : supervisorName.trim();
    }

    /**
    *监管关系开始时间
    */
    public Date getSupervisionStartTime() {
        return supervisionStartTime;
    }

    /**
    *监管关系开始时间
    */
    public void setSupervisionStartTime(Date supervisionStartTime) {
        this.supervisionStartTime = supervisionStartTime;
    }

    /**
    *监管关系解除时间
    */
    public Date getSupervisionEndTime() {
        return supervisionEndTime;
    }

    /**
    *监管关系解除时间
    */
    public void setSupervisionEndTime(Date supervisionEndTime) {
        this.supervisionEndTime = supervisionEndTime;
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
        AccountSupervision other = (AccountSupervision) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getSupervisorId() == null ? other.getSupervisorId() == null : this.getSupervisorId().equals(other.getSupervisorId()))
            && (this.getSupervisorName() == null ? other.getSupervisorName() == null : this.getSupervisorName().equals(other.getSupervisorName()))
            && (this.getSupervisionStartTime() == null ? other.getSupervisionStartTime() == null : this.getSupervisionStartTime().equals(other.getSupervisionStartTime()))
            && (this.getSupervisionEndTime() == null ? other.getSupervisionEndTime() == null : this.getSupervisionEndTime().equals(other.getSupervisionEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getSupervisorId() == null) ? 0 : getSupervisorId().hashCode());
        result = prime * result + ((getSupervisorName() == null) ? 0 : getSupervisorName().hashCode());
        result = prime * result + ((getSupervisionStartTime() == null) ? 0 : getSupervisionStartTime().hashCode());
        result = prime * result + ((getSupervisionEndTime() == null) ? 0 : getSupervisionEndTime().hashCode());
        return result;
    }
}