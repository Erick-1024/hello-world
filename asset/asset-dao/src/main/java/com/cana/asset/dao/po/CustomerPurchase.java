/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustomerPurchase implements Serializable {
    /**
     *采购ｉｄ
     */
    private String id;

    /**
     *关联客户ｉｄ
     */
    private String customerId;

    /**
     *供应商名称
     */
    private String supplierName;

    /**
     *上年采购量（万元）
     */
    private BigDecimal purchaseLastYear;

    /**
     *占总采购量%
     */
    private BigDecimal percent;

    /**
     *结算方式
     */
    private String settleType;

    /**
     *应付账款余额（万元）
     */
    private BigDecimal accountPayableBalance;

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
     *采购ｉｄ
     */
    public String getId() {
        return id;
    }

    /**
     *采购ｉｄ
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *关联客户ｉｄ
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *关联客户ｉｄ
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *供应商名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     *供应商名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     *上年采购量（万元）
     */
    public BigDecimal getPurchaseLastYear() {
        return purchaseLastYear;
    }

    /**
     *上年采购量（万元）
     */
    public void setPurchaseLastYear(BigDecimal purchaseLastYear) {
        this.purchaseLastYear = purchaseLastYear;
    }

    /**
     *占总采购量%
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     *占总采购量%
     */
    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    /**
     *结算方式
     */
    public String getSettleType() {
        return settleType;
    }

    /**
     *结算方式
     */
    public void setSettleType(String settleType) {
        this.settleType = settleType == null ? null : settleType.trim();
    }

    /**
     *应付账款余额（万元）
     */
    public BigDecimal getAccountPayableBalance() {
        return accountPayableBalance;
    }

    /**
     *应付账款余额（万元）
     */
    public void setAccountPayableBalance(BigDecimal accountPayableBalance) {
        this.accountPayableBalance = accountPayableBalance;
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
        CustomerPurchase other = (CustomerPurchase) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
            && (this.getPurchaseLastYear() == null ? other.getPurchaseLastYear() == null : this.getPurchaseLastYear().equals(other.getPurchaseLastYear()))
            && (this.getPercent() == null ? other.getPercent() == null : this.getPercent().equals(other.getPercent()))
            && (this.getSettleType() == null ? other.getSettleType() == null : this.getSettleType().equals(other.getSettleType()))
            && (this.getAccountPayableBalance() == null ? other.getAccountPayableBalance() == null : this.getAccountPayableBalance().equals(other.getAccountPayableBalance()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getPurchaseLastYear() == null) ? 0 : getPurchaseLastYear().hashCode());
        result = prime * result + ((getPercent() == null) ? 0 : getPercent().hashCode());
        result = prime * result + ((getSettleType() == null) ? 0 : getSettleType().hashCode());
        result = prime * result + ((getAccountPayableBalance() == null) ? 0 : getAccountPayableBalance().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}