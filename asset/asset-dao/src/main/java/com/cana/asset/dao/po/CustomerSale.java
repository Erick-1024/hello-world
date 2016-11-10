/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustomerSale implements Serializable {
    /**
     *销售id
     */
    private String id;

    /**
     *关联客户ｉｄ
     */
    private String customerId;

    /**
     *客户名称
     */
    private String saleCustomerName;

    /**
     *上年销货额（万元）
     */
    private BigDecimal saleLastYear;

    /**
     * 占总销售额%
     */
    private BigDecimal percent;

    /**
     *合作年限
     */
    private BigDecimal cooperationPeriod;

    /**
     *应收账款余额（万元）
     */
    private BigDecimal accountReceivableBalance;

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
     *销售id
     */
    public String getId() {
        return id;
    }

    /**
     *销售id
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
     *客户名称
     */
    public String getSaleCustomerName() {
        return saleCustomerName;
    }

    /**
     *客户名称
     */
    public void setSaleCustomerName(String saleCustomerName) {
        this.saleCustomerName = saleCustomerName == null ? null : saleCustomerName.trim();
    }

    /**
     *上年销货额（万元）
     */
    public BigDecimal getSaleLastYear() {
        return saleLastYear;
    }

    /**
     *上年销货额（万元）
     */
    public void setSaleLastYear(BigDecimal saleLastYear) {
        this.saleLastYear = saleLastYear;
    }

    /**
     * 占总销售额%
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     * 占总销售额%
     */
    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    /**
     *合作年限
     */
    public BigDecimal getCooperationPeriod() {
        return cooperationPeriod;
    }

    /**
     *合作年限
     */
    public void setCooperationPeriod(BigDecimal cooperationPeriod) {
        this.cooperationPeriod = cooperationPeriod;
    }

    /**
     *应收账款余额（万元）
     */
    public BigDecimal getAccountReceivableBalance() {
        return accountReceivableBalance;
    }

    /**
     *应收账款余额（万元）
     */
    public void setAccountReceivableBalance(BigDecimal accountReceivableBalance) {
        this.accountReceivableBalance = accountReceivableBalance;
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
        CustomerSale other = (CustomerSale) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getSaleCustomerName() == null ? other.getSaleCustomerName() == null : this.getSaleCustomerName().equals(other.getSaleCustomerName()))
            && (this.getSaleLastYear() == null ? other.getSaleLastYear() == null : this.getSaleLastYear().equals(other.getSaleLastYear()))
            && (this.getPercent() == null ? other.getPercent() == null : this.getPercent().equals(other.getPercent()))
            && (this.getCooperationPeriod() == null ? other.getCooperationPeriod() == null : this.getCooperationPeriod().equals(other.getCooperationPeriod()))
            && (this.getAccountReceivableBalance() == null ? other.getAccountReceivableBalance() == null : this.getAccountReceivableBalance().equals(other.getAccountReceivableBalance()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getSaleCustomerName() == null) ? 0 : getSaleCustomerName().hashCode());
        result = prime * result + ((getSaleLastYear() == null) ? 0 : getSaleLastYear().hashCode());
        result = prime * result + ((getPercent() == null) ? 0 : getPercent().hashCode());
        result = prime * result + ((getCooperationPeriod() == null) ? 0 : getCooperationPeriod().hashCode());
        result = prime * result + ((getAccountReceivableBalance() == null) ? 0 : getAccountReceivableBalance().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}