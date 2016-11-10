/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepaymentExtensionProductDetail implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *汇总id
     */
    private String summaryId;

    /**
     *
     */
    private String repaymentPlanId;

    /**
     *
     */
    private String loanInfoId;

    /**
     *新增展期费用
     */
    private Long extensionCharge;

    /**
     *应还本金
     */
    private Long accountPrincipal;

    /**
     *应还利息
     */
    private Long accountInterest;

    /**
     *应还服务费
     */
    private Long accountServiceCharge;

    /**
     *展期率
     */
    private BigDecimal extensionRatio;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *罚息生成日期
     */
    private String date;

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
     *
     */
    public String getRepaymentPlanId() {
        return repaymentPlanId;
    }

    /**
     *
     */
    public void setRepaymentPlanId(String repaymentPlanId) {
        this.repaymentPlanId = repaymentPlanId == null ? null : repaymentPlanId.trim();
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
     *新增展期费用
     */
    public Long getExtensionCharge() {
        return extensionCharge;
    }

    /**
     *新增展期费用
     */
    public void setExtensionCharge(Long extensionCharge) {
        this.extensionCharge = extensionCharge;
    }

    /**
     *应还本金
     */
    public Long getAccountPrincipal() {
        return accountPrincipal;
    }

    /**
     *应还本金
     */
    public void setAccountPrincipal(Long accountPrincipal) {
        this.accountPrincipal = accountPrincipal;
    }

    /**
     *应还利息
     */
    public Long getAccountInterest() {
        return accountInterest;
    }

    /**
     *应还利息
     */
    public void setAccountInterest(Long accountInterest) {
        this.accountInterest = accountInterest;
    }

    /**
     *应还服务费
     */
    public Long getAccountServiceCharge() {
        return accountServiceCharge;
    }

    /**
     *应还服务费
     */
    public void setAccountServiceCharge(Long accountServiceCharge) {
        this.accountServiceCharge = accountServiceCharge;
    }

    /**
     *展期率
     */
    public BigDecimal getExtensionRatio() {
        return extensionRatio;
    }

    /**
     *展期率
     */
    public void setExtensionRatio(BigDecimal extensionRatio) {
        this.extensionRatio = extensionRatio;
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
     *罚息生成日期
     */
    public String getDate() {
        return date;
    }

    /**
     *罚息生成日期
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
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
        RepaymentExtensionProductDetail other = (RepaymentExtensionProductDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSummaryId() == null ? other.getSummaryId() == null : this.getSummaryId().equals(other.getSummaryId()))
            && (this.getRepaymentPlanId() == null ? other.getRepaymentPlanId() == null : this.getRepaymentPlanId().equals(other.getRepaymentPlanId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getExtensionCharge() == null ? other.getExtensionCharge() == null : this.getExtensionCharge().equals(other.getExtensionCharge()))
            && (this.getAccountPrincipal() == null ? other.getAccountPrincipal() == null : this.getAccountPrincipal().equals(other.getAccountPrincipal()))
            && (this.getAccountInterest() == null ? other.getAccountInterest() == null : this.getAccountInterest().equals(other.getAccountInterest()))
            && (this.getAccountServiceCharge() == null ? other.getAccountServiceCharge() == null : this.getAccountServiceCharge().equals(other.getAccountServiceCharge()))
            && (this.getExtensionRatio() == null ? other.getExtensionRatio() == null : this.getExtensionRatio().equals(other.getExtensionRatio()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSummaryId() == null) ? 0 : getSummaryId().hashCode());
        result = prime * result + ((getRepaymentPlanId() == null) ? 0 : getRepaymentPlanId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getExtensionCharge() == null) ? 0 : getExtensionCharge().hashCode());
        result = prime * result + ((getAccountPrincipal() == null) ? 0 : getAccountPrincipal().hashCode());
        result = prime * result + ((getAccountInterest() == null) ? 0 : getAccountInterest().hashCode());
        result = prime * result + ((getAccountServiceCharge() == null) ? 0 : getAccountServiceCharge().hashCode());
        result = prime * result + ((getExtensionRatio() == null) ? 0 : getExtensionRatio().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        return result;
    }
}