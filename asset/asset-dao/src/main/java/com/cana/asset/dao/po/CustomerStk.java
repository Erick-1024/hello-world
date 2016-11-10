/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustomerStk implements Serializable {
    /**
     *股东id 
     */
    private String id;

    /**
     *客户ｉｄ
     */
    private String customerId;

    /**
     *股东类型
     */
    private String stkType;

    /**
     *股东名称
     */
    private String stkName;

    /**
     *出资额（万元)
     */
    private BigDecimal stkPayamt;

    /**
     *出资比例
     */
    private BigDecimal stkPcnt;

    /**
     *出资方式
     */
    private String stkPayamtType;

    /**
     *是否到位
     */
    private String stkStatus;

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
     *股东id 
     */
    public String getId() {
        return id;
    }

    /**
     *股东id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *客户ｉｄ
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *客户ｉｄ
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *股东类型
     */
    public String getStkType() {
        return stkType;
    }

    /**
     *股东类型
     */
    public void setStkType(String stkType) {
        this.stkType = stkType == null ? null : stkType.trim();
    }

    /**
     *股东名称
     */
    public String getStkName() {
        return stkName;
    }

    /**
     *股东名称
     */
    public void setStkName(String stkName) {
        this.stkName = stkName == null ? null : stkName.trim();
    }

    /**
     *出资额（万元)
     */
    public BigDecimal getStkPayamt() {
        return stkPayamt;
    }

    /**
     *出资额（万元)
     */
    public void setStkPayamt(BigDecimal stkPayamt) {
        this.stkPayamt = stkPayamt;
    }

    /**
     *出资比例
     */
    public BigDecimal getStkPcnt() {
        return stkPcnt;
    }

    /**
     *出资比例
     */
    public void setStkPcnt(BigDecimal stkPcnt) {
        this.stkPcnt = stkPcnt;
    }

    /**
     *出资方式
     */
    public String getStkPayamtType() {
        return stkPayamtType;
    }

    /**
     *出资方式
     */
    public void setStkPayamtType(String stkPayamtType) {
        this.stkPayamtType = stkPayamtType == null ? null : stkPayamtType.trim();
    }

    /**
     *是否到位
     */
    public String getStkStatus() {
        return stkStatus;
    }

    /**
     *是否到位
     */
    public void setStkStatus(String stkStatus) {
        this.stkStatus = stkStatus == null ? null : stkStatus.trim();
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
        CustomerStk other = (CustomerStk) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getStkType() == null ? other.getStkType() == null : this.getStkType().equals(other.getStkType()))
            && (this.getStkName() == null ? other.getStkName() == null : this.getStkName().equals(other.getStkName()))
            && (this.getStkPayamt() == null ? other.getStkPayamt() == null : this.getStkPayamt().equals(other.getStkPayamt()))
            && (this.getStkPcnt() == null ? other.getStkPcnt() == null : this.getStkPcnt().equals(other.getStkPcnt()))
            && (this.getStkPayamtType() == null ? other.getStkPayamtType() == null : this.getStkPayamtType().equals(other.getStkPayamtType()))
            && (this.getStkStatus() == null ? other.getStkStatus() == null : this.getStkStatus().equals(other.getStkStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getStkType() == null) ? 0 : getStkType().hashCode());
        result = prime * result + ((getStkName() == null) ? 0 : getStkName().hashCode());
        result = prime * result + ((getStkPayamt() == null) ? 0 : getStkPayamt().hashCode());
        result = prime * result + ((getStkPcnt() == null) ? 0 : getStkPcnt().hashCode());
        result = prime * result + ((getStkPayamtType() == null) ? 0 : getStkPayamtType().hashCode());
        result = prime * result + ((getStkStatus() == null) ? 0 : getStkStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}