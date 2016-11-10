/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.credit.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WhiteCustomer implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *Cana的真旅网客户ID
     */
    private Integer tzShortId;

    /**
     *采购商ID。唯一
     */
    private String tzCustomerId;

    /**
     *客户名称
     */
    private String customerName;

    /**
     *与客户来源公司的合作月份
     */
    private Integer cooperationPeriod;

    /**
     *订单采购增长率
     */
    private BigDecimal purchaseOrderGrowthRate;

    /**
     *逾期率
     */
    private BigDecimal overdueRate;

    /**
     *逾期次数
     */
    private Integer overdueTimes;

    /**
     *规则批次号
     */
    private Integer ruleBatchNo;

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
     *Cana的真旅网客户ID
     */
    public Integer getTzShortId() {
        return tzShortId;
    }

    /**
     *Cana的真旅网客户ID
     */
    public void setTzShortId(Integer tzShortId) {
        this.tzShortId = tzShortId;
    }

    /**
     *采购商ID。唯一
     */
    public String getTzCustomerId() {
        return tzCustomerId;
    }

    /**
     *采购商ID。唯一
     */
    public void setTzCustomerId(String tzCustomerId) {
        this.tzCustomerId = tzCustomerId == null ? null : tzCustomerId.trim();
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
     *与客户来源公司的合作月份
     */
    public Integer getCooperationPeriod() {
        return cooperationPeriod;
    }

    /**
     *与客户来源公司的合作月份
     */
    public void setCooperationPeriod(Integer cooperationPeriod) {
        this.cooperationPeriod = cooperationPeriod;
    }

    /**
     *订单采购增长率
     */
    public BigDecimal getPurchaseOrderGrowthRate() {
        return purchaseOrderGrowthRate;
    }

    /**
     *订单采购增长率
     */
    public void setPurchaseOrderGrowthRate(BigDecimal purchaseOrderGrowthRate) {
        this.purchaseOrderGrowthRate = purchaseOrderGrowthRate;
    }

    /**
     *逾期率
     */
    public BigDecimal getOverdueRate() {
        return overdueRate;
    }

    /**
     *逾期率
     */
    public void setOverdueRate(BigDecimal overdueRate) {
        this.overdueRate = overdueRate;
    }

    /**
     *逾期次数
     */
    public Integer getOverdueTimes() {
        return overdueTimes;
    }

    /**
     *逾期次数
     */
    public void setOverdueTimes(Integer overdueTimes) {
        this.overdueTimes = overdueTimes;
    }

    /**
     *规则批次号
     */
    public Integer getRuleBatchNo() {
        return ruleBatchNo;
    }

    /**
     *规则批次号
     */
    public void setRuleBatchNo(Integer ruleBatchNo) {
        this.ruleBatchNo = ruleBatchNo;
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
        WhiteCustomer other = (WhiteCustomer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTzShortId() == null ? other.getTzShortId() == null : this.getTzShortId().equals(other.getTzShortId()))
            && (this.getTzCustomerId() == null ? other.getTzCustomerId() == null : this.getTzCustomerId().equals(other.getTzCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCooperationPeriod() == null ? other.getCooperationPeriod() == null : this.getCooperationPeriod().equals(other.getCooperationPeriod()))
            && (this.getPurchaseOrderGrowthRate() == null ? other.getPurchaseOrderGrowthRate() == null : this.getPurchaseOrderGrowthRate().equals(other.getPurchaseOrderGrowthRate()))
            && (this.getOverdueRate() == null ? other.getOverdueRate() == null : this.getOverdueRate().equals(other.getOverdueRate()))
            && (this.getOverdueTimes() == null ? other.getOverdueTimes() == null : this.getOverdueTimes().equals(other.getOverdueTimes()))
            && (this.getRuleBatchNo() == null ? other.getRuleBatchNo() == null : this.getRuleBatchNo().equals(other.getRuleBatchNo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTzShortId() == null) ? 0 : getTzShortId().hashCode());
        result = prime * result + ((getTzCustomerId() == null) ? 0 : getTzCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCooperationPeriod() == null) ? 0 : getCooperationPeriod().hashCode());
        result = prime * result + ((getPurchaseOrderGrowthRate() == null) ? 0 : getPurchaseOrderGrowthRate().hashCode());
        result = prime * result + ((getOverdueRate() == null) ? 0 : getOverdueRate().hashCode());
        result = prime * result + ((getOverdueTimes() == null) ? 0 : getOverdueTimes().hashCode());
        result = prime * result + ((getRuleBatchNo() == null) ? 0 : getRuleBatchNo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}