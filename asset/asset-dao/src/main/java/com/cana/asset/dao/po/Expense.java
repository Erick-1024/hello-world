/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    /**
     *
     */
    private String id;

    /**
     *关联id
     */
    private String refid;

    /**
     *关联类型(CREDIT, FACTOR_BUSINESS, INVOICE, LOAN)
     */
    private String reftype;

    /**
     *费用科目
     */
    private String expenseSubject;

    /**
     *金额
     */
    private Long amount;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *序号
     */
    private Integer sequence;

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
     *关联id
     */
    public String getRefid() {
        return refid;
    }

    /**
     *关联id
     */
    public void setRefid(String refid) {
        this.refid = refid == null ? null : refid.trim();
    }

    /**
     *关联类型(CREDIT, FACTOR_BUSINESS, INVOICE, LOAN)
     */
    public String getReftype() {
        return reftype;
    }

    /**
     *关联类型(CREDIT, FACTOR_BUSINESS, INVOICE, LOAN)
     */
    public void setReftype(String reftype) {
        this.reftype = reftype == null ? null : reftype.trim();
    }

    /**
     *费用科目
     */
    public String getExpenseSubject() {
        return expenseSubject;
    }

    /**
     *费用科目
     */
    public void setExpenseSubject(String expenseSubject) {
        this.expenseSubject = expenseSubject == null ? null : expenseSubject.trim();
    }

    /**
     *金额
     */
    public Long getAmount() {
        return amount;
    }

    /**
     *金额
     */
    public void setAmount(Long amount) {
        this.amount = amount;
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

    /**
     *序号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *序号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
        Expense other = (Expense) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRefid() == null ? other.getRefid() == null : this.getRefid().equals(other.getRefid()))
            && (this.getReftype() == null ? other.getReftype() == null : this.getReftype().equals(other.getReftype()))
            && (this.getExpenseSubject() == null ? other.getExpenseSubject() == null : this.getExpenseSubject().equals(other.getExpenseSubject()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getSequence() == null ? other.getSequence() == null : this.getSequence().equals(other.getSequence()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRefid() == null) ? 0 : getRefid().hashCode());
        result = prime * result + ((getReftype() == null) ? 0 : getReftype().hashCode());
        result = prime * result + ((getExpenseSubject() == null) ? 0 : getExpenseSubject().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getSequence() == null) ? 0 : getSequence().hashCode());
        return result;
    }
}