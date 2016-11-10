/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.marketing.dao.po;

import java.io.Serializable;
import java.util.Date;

public class MarketingActivity implements Serializable {
    /**
     *
     */
    private String id;

    /**
     *活动类型。如：借款利率打折
     */
    private String type;

    /**
     *该活动适用的产品编号，为空代表适用于所有产品
     */
    private String productId;

    /**
     *该活动适用的保理商，为空代表适用于所有保理商
     */
    private String factorId;

    /**
     *该活动适用的融资客户，为空代表适用于所有融资客户
     */
    private String financeId;

    /**
     *活动开始日期
     */
    private Date startDate;

    /**
     *活动结束日期
     */
    private Date endDate;

    /**
     *活动详情
     */
    private String detail;

    /**
     *是否还在使用
     */
    private Boolean used;

    /**
     *创建时间
     */
    private Date createTime;

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
     *活动类型。如：借款利率打折
     */
    public String getType() {
        return type;
    }

    /**
     *活动类型。如：借款利率打折
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *该活动适用的产品编号，为空代表适用于所有产品
     */
    public String getProductId() {
        return productId;
    }

    /**
     *该活动适用的产品编号，为空代表适用于所有产品
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     *该活动适用的保理商，为空代表适用于所有保理商
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *该活动适用的保理商，为空代表适用于所有保理商
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *该活动适用的融资客户，为空代表适用于所有融资客户
     */
    public String getFinanceId() {
        return financeId;
    }

    /**
     *该活动适用的融资客户，为空代表适用于所有融资客户
     */
    public void setFinanceId(String financeId) {
        this.financeId = financeId == null ? null : financeId.trim();
    }

    /**
     *活动开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     *活动开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *活动结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     *活动结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     *活动详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     *活动详情
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     *是否还在使用
     */
    public Boolean getUsed() {
        return used;
    }

    /**
     *是否还在使用
     */
    public void setUsed(Boolean used) {
        this.used = used;
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
        MarketingActivity other = (MarketingActivity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getFinanceId() == null ? other.getFinanceId() == null : this.getFinanceId().equals(other.getFinanceId()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
            && (this.getUsed() == null ? other.getUsed() == null : this.getUsed().equals(other.getUsed()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getFinanceId() == null) ? 0 : getFinanceId().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getUsed() == null) ? 0 : getUsed().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}