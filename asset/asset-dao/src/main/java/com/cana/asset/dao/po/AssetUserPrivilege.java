/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class AssetUserPrivilege implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *平台主账号的用户类型
     */
    private String userType;

    /**
     *平台主账号id
     */
    private String masterId;

    /**
     *企业名称
     */
    private String companyName;

    /**
     *保理商id
     */
    private String factorId;

    /**
     *保理商名称
     */
    private String factorName;

    /**
     *客户id, ALL代表该保理商项下所有的客户
     */
    private String customerId;

    /**
     *客户名称
     */
    private String customerName;

    /**
     *创建操作员id
     */
    private String creatorId;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *记录更新时间
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
     *平台主账号的用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     *平台主账号的用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     *平台主账号id
     */
    public String getMasterId() {
        return masterId;
    }

    /**
     *平台主账号id
     */
    public void setMasterId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
    }

    /**
     *企业名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *企业名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     *保理商id
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *保理商id
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *保理商名称
     */
    public String getFactorName() {
        return factorName;
    }

    /**
     *保理商名称
     */
    public void setFactorName(String factorName) {
        this.factorName = factorName == null ? null : factorName.trim();
    }

    /**
     *客户id, ALL代表该保理商项下所有的客户
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *客户id, ALL代表该保理商项下所有的客户
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
     *创建操作员id
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     *创建操作员id
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
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
     *记录更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *记录更新时间
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
        AssetUserPrivilege other = (AssetUserPrivilege) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getMasterId() == null ? other.getMasterId() == null : this.getMasterId().equals(other.getMasterId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getFactorName() == null ? other.getFactorName() == null : this.getFactorName().equals(other.getFactorName()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getMasterId() == null) ? 0 : getMasterId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getFactorName() == null) ? 0 : getFactorName().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}