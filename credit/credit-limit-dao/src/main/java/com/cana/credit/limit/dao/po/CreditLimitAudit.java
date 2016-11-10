/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.credit.limit.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CreditLimitAudit implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *授信额度的ID
     */
    private String limitId;

    /**
     *详见 @see CreditLimitAuditType，有如下类型：总额度变化、额度消耗、项目中额度恢复、融资模块还款恢复
     */
    private String type;

    /**
     *变化之前的总额度
     */
    private Long prevTotalLimit;

    /**
     *总额度
     */
    private Long totalLimit;

    /**
     *变化之前的已使用额度
     */
    private Long prevUsedLimit;

    /**
     *已使用额度
     */
    private Long usedLimit;

    /**
     *当客户在融资模块进行还款时，会触发额度恢复，此时使用的是重试框架来恢复额度，而此字段是用来识别融资发来的恢复额度请求是否已经被处理过的字段，具体参见真旅项目中的实现。
     */
    private String requestId;

    /**
     *真旅项目中，引起额度变化的交易记录ID
     */
    private String tradeId;

    /**
     *放款ID，目前用在韵达项目中
     */
    private String loanId;

    /**
     *放款编号，目前用在韵达项目中
     */
    private String loanNo;

    /**
     *创建时间
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
     *授信额度的ID
     */
    public String getLimitId() {
        return limitId;
    }

    /**
     *授信额度的ID
     */
    public void setLimitId(String limitId) {
        this.limitId = limitId == null ? null : limitId.trim();
    }

    /**
     *详见 @see CreditLimitAuditType，有如下类型：总额度变化、额度消耗、项目中额度恢复、融资模块还款恢复
     */
    public String getType() {
        return type;
    }

    /**
     *详见 @see CreditLimitAuditType，有如下类型：总额度变化、额度消耗、项目中额度恢复、融资模块还款恢复
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *变化之前的总额度
     */
    public Long getPrevTotalLimit() {
        return prevTotalLimit;
    }

    /**
     *变化之前的总额度
     */
    public void setPrevTotalLimit(Long prevTotalLimit) {
        this.prevTotalLimit = prevTotalLimit;
    }

    /**
     *总额度
     */
    public Long getTotalLimit() {
        return totalLimit;
    }

    /**
     *总额度
     */
    public void setTotalLimit(Long totalLimit) {
        this.totalLimit = totalLimit;
    }

    /**
     *变化之前的已使用额度
     */
    public Long getPrevUsedLimit() {
        return prevUsedLimit;
    }

    /**
     *变化之前的已使用额度
     */
    public void setPrevUsedLimit(Long prevUsedLimit) {
        this.prevUsedLimit = prevUsedLimit;
    }

    /**
     *已使用额度
     */
    public Long getUsedLimit() {
        return usedLimit;
    }

    /**
     *已使用额度
     */
    public void setUsedLimit(Long usedLimit) {
        this.usedLimit = usedLimit;
    }

    /**
     *当客户在融资模块进行还款时，会触发额度恢复，此时使用的是重试框架来恢复额度，而此字段是用来识别融资发来的恢复额度请求是否已经被处理过的字段，具体参见真旅项目中的实现。
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     *当客户在融资模块进行还款时，会触发额度恢复，此时使用的是重试框架来恢复额度，而此字段是用来识别融资发来的恢复额度请求是否已经被处理过的字段，具体参见真旅项目中的实现。
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    /**
     *真旅项目中，引起额度变化的交易记录ID
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     *真旅项目中，引起额度变化的交易记录ID
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     *放款ID，目前用在韵达项目中
     */
    public String getLoanId() {
        return loanId;
    }

    /**
     *放款ID，目前用在韵达项目中
     */
    public void setLoanId(String loanId) {
        this.loanId = loanId == null ? null : loanId.trim();
    }

    /**
     *放款编号，目前用在韵达项目中
     */
    public String getLoanNo() {
        return loanNo;
    }

    /**
     *放款编号，目前用在韵达项目中
     */
    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
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
        CreditLimitAudit other = (CreditLimitAudit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLimitId() == null ? other.getLimitId() == null : this.getLimitId().equals(other.getLimitId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPrevTotalLimit() == null ? other.getPrevTotalLimit() == null : this.getPrevTotalLimit().equals(other.getPrevTotalLimit()))
            && (this.getTotalLimit() == null ? other.getTotalLimit() == null : this.getTotalLimit().equals(other.getTotalLimit()))
            && (this.getPrevUsedLimit() == null ? other.getPrevUsedLimit() == null : this.getPrevUsedLimit().equals(other.getPrevUsedLimit()))
            && (this.getUsedLimit() == null ? other.getUsedLimit() == null : this.getUsedLimit().equals(other.getUsedLimit()))
            && (this.getRequestId() == null ? other.getRequestId() == null : this.getRequestId().equals(other.getRequestId()))
            && (this.getTradeId() == null ? other.getTradeId() == null : this.getTradeId().equals(other.getTradeId()))
            && (this.getLoanId() == null ? other.getLoanId() == null : this.getLoanId().equals(other.getLoanId()))
            && (this.getLoanNo() == null ? other.getLoanNo() == null : this.getLoanNo().equals(other.getLoanNo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLimitId() == null) ? 0 : getLimitId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPrevTotalLimit() == null) ? 0 : getPrevTotalLimit().hashCode());
        result = prime * result + ((getTotalLimit() == null) ? 0 : getTotalLimit().hashCode());
        result = prime * result + ((getPrevUsedLimit() == null) ? 0 : getPrevUsedLimit().hashCode());
        result = prime * result + ((getUsedLimit() == null) ? 0 : getUsedLimit().hashCode());
        result = prime * result + ((getRequestId() == null) ? 0 : getRequestId().hashCode());
        result = prime * result + ((getTradeId() == null) ? 0 : getTradeId().hashCode());
        result = prime * result + ((getLoanId() == null) ? 0 : getLoanId().hashCode());
        result = prime * result + ((getLoanNo() == null) ? 0 : getLoanNo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}