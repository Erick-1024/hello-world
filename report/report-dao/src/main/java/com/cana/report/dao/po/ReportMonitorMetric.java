/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReportMonitorMetric implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *平台客户ID
     */
    private String memberId;

    /**
     *外部客户ID
     */
    private String outCustomerId;

    /**
     *产品ID
     */
    private String productId;

    /**
     *指标类型（质押反担保覆盖率：COUNTER_GUARANTEE_RATE、销售变化率：SALES_CHANGE_RATE、销售回款率：SALES_REPAYMENT_RATE）
     */
    private String type;

    /**
     *指数比例
     */
    private BigDecimal proportion;

    /**
     *统计日期，格式：yyyy-MM-dd
     */
    private String date;

    /**
     *创建日期
     */
    private Date createTime;

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
     *平台客户ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     *平台客户ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     *外部客户ID
     */
    public String getOutCustomerId() {
        return outCustomerId;
    }

    /**
     *外部客户ID
     */
    public void setOutCustomerId(String outCustomerId) {
        this.outCustomerId = outCustomerId == null ? null : outCustomerId.trim();
    }

    /**
     *产品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     *产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     *指标类型（质押反担保覆盖率：COUNTER_GUARANTEE_RATE、销售变化率：SALES_CHANGE_RATE、销售回款率：SALES_REPAYMENT_RATE）
     */
    public String getType() {
        return type;
    }

    /**
     *指标类型（质押反担保覆盖率：COUNTER_GUARANTEE_RATE、销售变化率：SALES_CHANGE_RATE、销售回款率：SALES_REPAYMENT_RATE）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *指数比例
     */
    public BigDecimal getProportion() {
        return proportion;
    }

    /**
     *指数比例
     */
    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    /**
     *统计日期，格式：yyyy-MM-dd
     */
    public String getDate() {
        return date;
    }

    /**
     *统计日期，格式：yyyy-MM-dd
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
     *创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建日期
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
        ReportMonitorMetric other = (ReportMonitorMetric) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getOutCustomerId() == null ? other.getOutCustomerId() == null : this.getOutCustomerId().equals(other.getOutCustomerId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getProportion() == null ? other.getProportion() == null : this.getProportion().equals(other.getProportion()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getOutCustomerId() == null) ? 0 : getOutCustomerId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getProportion() == null) ? 0 : getProportion().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}