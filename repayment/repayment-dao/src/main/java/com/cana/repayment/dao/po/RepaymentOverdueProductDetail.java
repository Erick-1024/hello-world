/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.util.Date;

public class RepaymentOverdueProductDetail implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *汇总id
     */
    private String summaryId;

    /**
     *关联者Id（还款计划、费用）
     */
    private String relatedId;

    /**
     *关联者类型
     */
    private String relatedType;

    /**
     *
     */
    private String loanInfoId;

    /**
     *新增逾期本金
     */
    private Long overduePrincipal;

    /**
     *新增逾期利息
     */
    private Long overdueInterest;

    /**
     *新增逾期服务费
     */
    private Long overdueServiceCharge;

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
     *汇总id
     */
    public String getSummaryId() {
        return summaryId;
    }

    /**
     *汇总id
     */
    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId == null ? null : summaryId.trim();
    }

    /**
     *关联者Id（还款计划、费用）
     */
    public String getRelatedId() {
        return relatedId;
    }

    /**
     *关联者Id（还款计划、费用）
     */
    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId == null ? null : relatedId.trim();
    }

    /**
     *关联者类型
     */
    public String getRelatedType() {
        return relatedType;
    }

    /**
     *关联者类型
     */
    public void setRelatedType(String relatedType) {
        this.relatedType = relatedType == null ? null : relatedType.trim();
    }

    /**
     *
     */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
     *
     */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
    }

    /**
     *新增逾期本金
     */
    public Long getOverduePrincipal() {
        return overduePrincipal;
    }

    /**
     *新增逾期本金
     */
    public void setOverduePrincipal(Long overduePrincipal) {
        this.overduePrincipal = overduePrincipal;
    }

    /**
     *新增逾期利息
     */
    public Long getOverdueInterest() {
        return overdueInterest;
    }

    /**
     *新增逾期利息
     */
    public void setOverdueInterest(Long overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    /**
     *新增逾期服务费
     */
    public Long getOverdueServiceCharge() {
        return overdueServiceCharge;
    }

    /**
     *新增逾期服务费
     */
    public void setOverdueServiceCharge(Long overdueServiceCharge) {
        this.overdueServiceCharge = overdueServiceCharge;
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
        RepaymentOverdueProductDetail other = (RepaymentOverdueProductDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSummaryId() == null ? other.getSummaryId() == null : this.getSummaryId().equals(other.getSummaryId()))
            && (this.getRelatedId() == null ? other.getRelatedId() == null : this.getRelatedId().equals(other.getRelatedId()))
            && (this.getRelatedType() == null ? other.getRelatedType() == null : this.getRelatedType().equals(other.getRelatedType()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getOverduePrincipal() == null ? other.getOverduePrincipal() == null : this.getOverduePrincipal().equals(other.getOverduePrincipal()))
            && (this.getOverdueInterest() == null ? other.getOverdueInterest() == null : this.getOverdueInterest().equals(other.getOverdueInterest()))
            && (this.getOverdueServiceCharge() == null ? other.getOverdueServiceCharge() == null : this.getOverdueServiceCharge().equals(other.getOverdueServiceCharge()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSummaryId() == null) ? 0 : getSummaryId().hashCode());
        result = prime * result + ((getRelatedId() == null) ? 0 : getRelatedId().hashCode());
        result = prime * result + ((getRelatedType() == null) ? 0 : getRelatedType().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getOverduePrincipal() == null) ? 0 : getOverduePrincipal().hashCode());
        result = prime * result + ((getOverdueInterest() == null) ? 0 : getOverdueInterest().hashCode());
        result = prime * result + ((getOverdueServiceCharge() == null) ? 0 : getOverdueServiceCharge().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}