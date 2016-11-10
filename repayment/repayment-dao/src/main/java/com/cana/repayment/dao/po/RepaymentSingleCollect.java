/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.util.Date;

public class RepaymentSingleCollect implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *放款信息Id
     */
    private String loanInfoId;

    /**
     *还款类型（账扣、手动、线下）
     */
    private String repaymentType;

    /**
     *还款凭证
     */
    private String repaymentCertificate;

    /**
     *还款总金额
     */
    private Long repaymentTotalAmount;

    /**
     *还款日期
     */
    private String repaymentDate;

    /**
     *交易状态
     */
    private String tradeStatus;

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
     *还款类型（账扣、手动、线下）
     */
    public String getRepaymentType() {
        return repaymentType;
    }

    /**
     *还款类型（账扣、手动、线下）
     */
    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType == null ? null : repaymentType.trim();
    }

    /**
     *还款凭证
     */
    public String getRepaymentCertificate() {
        return repaymentCertificate;
    }

    /**
     *还款凭证
     */
    public void setRepaymentCertificate(String repaymentCertificate) {
        this.repaymentCertificate = repaymentCertificate == null ? null : repaymentCertificate.trim();
    }

    /**
     *还款总金额
     */
    public Long getRepaymentTotalAmount() {
        return repaymentTotalAmount;
    }

    /**
     *还款总金额
     */
    public void setRepaymentTotalAmount(Long repaymentTotalAmount) {
        this.repaymentTotalAmount = repaymentTotalAmount;
    }

    /**
     *还款日期
     */
    public String getRepaymentDate() {
        return repaymentDate;
    }

    /**
     *还款日期
     */
    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate == null ? null : repaymentDate.trim();
    }

    /**
     *交易状态
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     *交易状态
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
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
        RepaymentSingleCollect other = (RepaymentSingleCollect) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getRepaymentType() == null ? other.getRepaymentType() == null : this.getRepaymentType().equals(other.getRepaymentType()))
            && (this.getRepaymentCertificate() == null ? other.getRepaymentCertificate() == null : this.getRepaymentCertificate().equals(other.getRepaymentCertificate()))
            && (this.getRepaymentTotalAmount() == null ? other.getRepaymentTotalAmount() == null : this.getRepaymentTotalAmount().equals(other.getRepaymentTotalAmount()))
            && (this.getRepaymentDate() == null ? other.getRepaymentDate() == null : this.getRepaymentDate().equals(other.getRepaymentDate()))
            && (this.getTradeStatus() == null ? other.getTradeStatus() == null : this.getTradeStatus().equals(other.getTradeStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getRepaymentType() == null) ? 0 : getRepaymentType().hashCode());
        result = prime * result + ((getRepaymentCertificate() == null) ? 0 : getRepaymentCertificate().hashCode());
        result = prime * result + ((getRepaymentTotalAmount() == null) ? 0 : getRepaymentTotalAmount().hashCode());
        result = prime * result + ((getRepaymentDate() == null) ? 0 : getRepaymentDate().hashCode());
        result = prime * result + ((getTradeStatus() == null) ? 0 : getTradeStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}