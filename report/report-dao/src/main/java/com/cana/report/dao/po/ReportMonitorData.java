/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ReportMonitorData implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *平台客户ID
     */
    private String memberId;

    /**
     *
     */
    private String outCustomerId;

    /**
     *
     */
    private String productId;

    /**
     *金额数据类型（合格AR余额：QUALIFIED_AR、机票销售总金额：TICKET_ALL_SALES、机票回款总金额：产品的id+TICKET_REPAYMENT、机票已起飞金额：TICKET_TAKE_OFF_SALE）
     */
    private String type;

    /**
     *金额，精确到分
     */
    private Long amount;

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
     *
     */
    public String getOutCustomerId() {
        return outCustomerId;
    }

    /**
     *
     */
    public void setOutCustomerId(String outCustomerId) {
        this.outCustomerId = outCustomerId == null ? null : outCustomerId.trim();
    }

    /**
     *
     */
    public String getProductId() {
        return productId;
    }

    /**
     *
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     *金额数据类型（合格AR余额：QUALIFIED_AR、机票销售总金额：TICKET_ALL_SALES、机票回款总金额：产品的id+TICKET_REPAYMENT、机票已起飞金额：TICKET_TAKE_OFF_SALE）
     */
    public String getType() {
        return type;
    }

    /**
     *金额数据类型（合格AR余额：QUALIFIED_AR、机票销售总金额：TICKET_ALL_SALES、机票回款总金额：产品的id+TICKET_REPAYMENT、机票已起飞金额：TICKET_TAKE_OFF_SALE）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *金额，精确到分
     */
    public Long getAmount() {
        return amount;
    }

    /**
     *金额，精确到分
     */
    public void setAmount(Long amount) {
        this.amount = amount;
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
        ReportMonitorData other = (ReportMonitorData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getOutCustomerId() == null ? other.getOutCustomerId() == null : this.getOutCustomerId().equals(other.getOutCustomerId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
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
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}