/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ReportFactorFinanceCount implements Serializable {
    /**
     *
     */
    private String id;

    /**
     *报表所有者
     */
    private String ownerId;

    /**
     *报表日期
     */
    private String reportDate;

    /**
     *用户类型
     */
    private String userType;

    /**
     *业务产品Id
     */
    private String businessProductId;

    /**
     *放款笔数
     */
    private Integer loanItems;

    /**
     *逾期笔数
     */
    private Integer overdueItems;

    /**
     *展期笔数
     */
    private Integer extensionItems;

    /**
     *还款笔数
     */
    private Integer repaymentItems;

    /**
     *调账笔数
     */
    private Integer adjustItems;

    /**
     *逾期客户数
     */
    private Integer overdueCustomer;

    /**
     *报表创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public String getId() {
        return id;
    }

    /**
     *
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *报表所有者
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     *报表所有者
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    /**
     *报表日期
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     *报表日期
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate == null ? null : reportDate.trim();
    }

    /**
     *用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     *用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     *业务产品Id
     */
    public String getBusinessProductId() {
        return businessProductId;
    }

    /**
     *业务产品Id
     */
    public void setBusinessProductId(String businessProductId) {
        this.businessProductId = businessProductId == null ? null : businessProductId.trim();
    }

    /**
     *放款笔数
     */
    public Integer getLoanItems() {
        return loanItems;
    }

    /**
     *放款笔数
     */
    public void setLoanItems(Integer loanItems) {
        this.loanItems = loanItems;
    }

    /**
     *逾期笔数
     */
    public Integer getOverdueItems() {
        return overdueItems;
    }

    /**
     *逾期笔数
     */
    public void setOverdueItems(Integer overdueItems) {
        this.overdueItems = overdueItems;
    }

    /**
     *展期笔数
     */
    public Integer getExtensionItems() {
        return extensionItems;
    }

    /**
     *展期笔数
     */
    public void setExtensionItems(Integer extensionItems) {
        this.extensionItems = extensionItems;
    }

    /**
     *还款笔数
     */
    public Integer getRepaymentItems() {
        return repaymentItems;
    }

    /**
     *还款笔数
     */
    public void setRepaymentItems(Integer repaymentItems) {
        this.repaymentItems = repaymentItems;
    }

    /**
     *调账笔数
     */
    public Integer getAdjustItems() {
        return adjustItems;
    }

    /**
     *调账笔数
     */
    public void setAdjustItems(Integer adjustItems) {
        this.adjustItems = adjustItems;
    }

    /**
     *逾期客户数
     */
    public Integer getOverdueCustomer() {
        return overdueCustomer;
    }

    /**
     *逾期客户数
     */
    public void setOverdueCustomer(Integer overdueCustomer) {
        this.overdueCustomer = overdueCustomer;
    }

    /**
     *报表创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *报表创建时间
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
        ReportFactorFinanceCount other = (ReportFactorFinanceCount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getReportDate() == null ? other.getReportDate() == null : this.getReportDate().equals(other.getReportDate()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getBusinessProductId() == null ? other.getBusinessProductId() == null : this.getBusinessProductId().equals(other.getBusinessProductId()))
            && (this.getLoanItems() == null ? other.getLoanItems() == null : this.getLoanItems().equals(other.getLoanItems()))
            && (this.getOverdueItems() == null ? other.getOverdueItems() == null : this.getOverdueItems().equals(other.getOverdueItems()))
            && (this.getExtensionItems() == null ? other.getExtensionItems() == null : this.getExtensionItems().equals(other.getExtensionItems()))
            && (this.getRepaymentItems() == null ? other.getRepaymentItems() == null : this.getRepaymentItems().equals(other.getRepaymentItems()))
            && (this.getAdjustItems() == null ? other.getAdjustItems() == null : this.getAdjustItems().equals(other.getAdjustItems()))
            && (this.getOverdueCustomer() == null ? other.getOverdueCustomer() == null : this.getOverdueCustomer().equals(other.getOverdueCustomer()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getReportDate() == null) ? 0 : getReportDate().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getBusinessProductId() == null) ? 0 : getBusinessProductId().hashCode());
        result = prime * result + ((getLoanItems() == null) ? 0 : getLoanItems().hashCode());
        result = prime * result + ((getOverdueItems() == null) ? 0 : getOverdueItems().hashCode());
        result = prime * result + ((getExtensionItems() == null) ? 0 : getExtensionItems().hashCode());
        result = prime * result + ((getRepaymentItems() == null) ? 0 : getRepaymentItems().hashCode());
        result = prime * result + ((getAdjustItems() == null) ? 0 : getAdjustItems().hashCode());
        result = prime * result + ((getOverdueCustomer() == null) ? 0 : getOverdueCustomer().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}