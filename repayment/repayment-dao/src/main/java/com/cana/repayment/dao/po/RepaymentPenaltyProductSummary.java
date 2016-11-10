/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.util.Date;

public class RepaymentPenaltyProductSummary implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *放款信息Id
    */
    private String loanInfoId;

    /**
    *生成日期
    */
    private String date;

    /**
    *受影响的还款计划条数
    */
    private Integer affectedPlanNum;

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
    *放款信息Id
    */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
    *放款信息Id
    */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
    }

    /**
    *生成日期
    */
    public String getDate() {
        return date;
    }

    /**
    *生成日期
    */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
    *受影响的还款计划条数
    */
    public Integer getAffectedPlanNum() {
        return affectedPlanNum;
    }

    /**
    *受影响的还款计划条数
    */
    public void setAffectedPlanNum(Integer affectedPlanNum) {
        this.affectedPlanNum = affectedPlanNum;
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
        RepaymentPenaltyProductSummary other = (RepaymentPenaltyProductSummary) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getAffectedPlanNum() == null ? other.getAffectedPlanNum() == null : this.getAffectedPlanNum().equals(other.getAffectedPlanNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getAffectedPlanNum() == null) ? 0 : getAffectedPlanNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}