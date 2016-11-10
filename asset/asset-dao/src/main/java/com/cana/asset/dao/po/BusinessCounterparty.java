/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BusinessCounterparty implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *业务资料Id
     */
    private String businessInfoId;

    /**
     *交易对手Id
     */
    private String counterpartyId;

    /**
     *交易对手
     */
    private String counterparty;

    /**
     *交易对手类型
     */
    private String counterpartyType;

    /**
     *融资比例
     */
    private BigDecimal financingRatio;

    /**
     *保理类型
     */
    private String factoringType;

    /**
     *是否查询子额度
     */
    private Boolean querySubLimitFlag;

    /**
     *子额度
     */
    private Long subLimit;

    /**
     *资料顺序
     */
    private Integer sequence;

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
     *业务资料Id
     */
    public String getBusinessInfoId() {
        return businessInfoId;
    }

    /**
     *业务资料Id
     */
    public void setBusinessInfoId(String businessInfoId) {
        this.businessInfoId = businessInfoId == null ? null : businessInfoId.trim();
    }

    /**
     *交易对手Id
     */
    public String getCounterpartyId() {
        return counterpartyId;
    }

    /**
     *交易对手Id
     */
    public void setCounterpartyId(String counterpartyId) {
        this.counterpartyId = counterpartyId == null ? null : counterpartyId.trim();
    }

    /**
     *交易对手
     */
    public String getCounterparty() {
        return counterparty;
    }

    /**
     *交易对手
     */
    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty == null ? null : counterparty.trim();
    }

    /**
     *交易对手类型
     */
    public String getCounterpartyType() {
        return counterpartyType;
    }

    /**
     *交易对手类型
     */
    public void setCounterpartyType(String counterpartyType) {
        this.counterpartyType = counterpartyType == null ? null : counterpartyType.trim();
    }

    /**
     *融资比例
     */
    public BigDecimal getFinancingRatio() {
        return financingRatio;
    }

    /**
     *融资比例
     */
    public void setFinancingRatio(BigDecimal financingRatio) {
        this.financingRatio = financingRatio;
    }

    /**
     *保理类型
     */
    public String getFactoringType() {
        return factoringType;
    }

    /**
     *保理类型
     */
    public void setFactoringType(String factoringType) {
        this.factoringType = factoringType == null ? null : factoringType.trim();
    }

    /**
     *是否查询子额度
     */
    public Boolean getQuerySubLimitFlag() {
        return querySubLimitFlag;
    }

    /**
     *是否查询子额度
     */
    public void setQuerySubLimitFlag(Boolean querySubLimitFlag) {
        this.querySubLimitFlag = querySubLimitFlag;
    }

    /**
     *子额度
     */
    public Long getSubLimit() {
        return subLimit;
    }

    /**
     *子额度
     */
    public void setSubLimit(Long subLimit) {
        this.subLimit = subLimit;
    }

    /**
     *资料顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *资料顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
        BusinessCounterparty other = (BusinessCounterparty) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessInfoId() == null ? other.getBusinessInfoId() == null : this.getBusinessInfoId().equals(other.getBusinessInfoId()))
            && (this.getCounterpartyId() == null ? other.getCounterpartyId() == null : this.getCounterpartyId().equals(other.getCounterpartyId()))
            && (this.getCounterparty() == null ? other.getCounterparty() == null : this.getCounterparty().equals(other.getCounterparty()))
            && (this.getCounterpartyType() == null ? other.getCounterpartyType() == null : this.getCounterpartyType().equals(other.getCounterpartyType()))
            && (this.getFinancingRatio() == null ? other.getFinancingRatio() == null : this.getFinancingRatio().equals(other.getFinancingRatio()))
            && (this.getFactoringType() == null ? other.getFactoringType() == null : this.getFactoringType().equals(other.getFactoringType()))
            && (this.getQuerySubLimitFlag() == null ? other.getQuerySubLimitFlag() == null : this.getQuerySubLimitFlag().equals(other.getQuerySubLimitFlag()))
            && (this.getSubLimit() == null ? other.getSubLimit() == null : this.getSubLimit().equals(other.getSubLimit()))
            && (this.getSequence() == null ? other.getSequence() == null : this.getSequence().equals(other.getSequence()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessInfoId() == null) ? 0 : getBusinessInfoId().hashCode());
        result = prime * result + ((getCounterpartyId() == null) ? 0 : getCounterpartyId().hashCode());
        result = prime * result + ((getCounterparty() == null) ? 0 : getCounterparty().hashCode());
        result = prime * result + ((getCounterpartyType() == null) ? 0 : getCounterpartyType().hashCode());
        result = prime * result + ((getFinancingRatio() == null) ? 0 : getFinancingRatio().hashCode());
        result = prime * result + ((getFactoringType() == null) ? 0 : getFactoringType().hashCode());
        result = prime * result + ((getQuerySubLimitFlag() == null) ? 0 : getQuerySubLimitFlag().hashCode());
        result = prime * result + ((getSubLimit() == null) ? 0 : getSubLimit().hashCode());
        result = prime * result + ((getSequence() == null) ? 0 : getSequence().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}