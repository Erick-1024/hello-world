/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class AssetInvoiceBasicInfo implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *保理商id
     */
    private String factorId;

    /**
     *保理商名称
     */
    private String factorName;

    /**
     *客户Id
     */
    private String customerId;

    /**
     *客户名称
     */
    private String customerName;

    /**
     *业务产品
     */
    private String businessProduct;

    /**
     *业务合同号
     */
    private String businessContractNo;

    /**
     *币种
     */
    private String currency;

    /**
     *单证笔数
     */
    private Integer countInvoice;

    /**
     *账款总余额
     */
    private Long sumInvoiceAmt;

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
     *客户Id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *客户Id
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
     *业务产品
     */
    public String getBusinessProduct() {
        return businessProduct;
    }

    /**
     *业务产品
     */
    public void setBusinessProduct(String businessProduct) {
        this.businessProduct = businessProduct == null ? null : businessProduct.trim();
    }

    /**
     *业务合同号
     */
    public String getBusinessContractNo() {
        return businessContractNo;
    }

    /**
     *业务合同号
     */
    public void setBusinessContractNo(String businessContractNo) {
        this.businessContractNo = businessContractNo == null ? null : businessContractNo.trim();
    }

    /**
     *币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *币种
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     *单证笔数
     */
    public Integer getCountInvoice() {
        return countInvoice;
    }

    /**
     *单证笔数
     */
    public void setCountInvoice(Integer countInvoice) {
        this.countInvoice = countInvoice;
    }

    /**
     *账款总余额
     */
    public Long getSumInvoiceAmt() {
        return sumInvoiceAmt;
    }

    /**
     *账款总余额
     */
    public void setSumInvoiceAmt(Long sumInvoiceAmt) {
        this.sumInvoiceAmt = sumInvoiceAmt;
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
        AssetInvoiceBasicInfo other = (AssetInvoiceBasicInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getFactorName() == null ? other.getFactorName() == null : this.getFactorName().equals(other.getFactorName()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getBusinessProduct() == null ? other.getBusinessProduct() == null : this.getBusinessProduct().equals(other.getBusinessProduct()))
            && (this.getBusinessContractNo() == null ? other.getBusinessContractNo() == null : this.getBusinessContractNo().equals(other.getBusinessContractNo()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getCountInvoice() == null ? other.getCountInvoice() == null : this.getCountInvoice().equals(other.getCountInvoice()))
            && (this.getSumInvoiceAmt() == null ? other.getSumInvoiceAmt() == null : this.getSumInvoiceAmt().equals(other.getSumInvoiceAmt()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getFactorName() == null) ? 0 : getFactorName().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getBusinessProduct() == null) ? 0 : getBusinessProduct().hashCode());
        result = prime * result + ((getBusinessContractNo() == null) ? 0 : getBusinessContractNo().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getCountInvoice() == null) ? 0 : getCountInvoice().hashCode());
        result = prime * result + ((getSumInvoiceAmt() == null) ? 0 : getSumInvoiceAmt().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}