/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.credit.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccessRule extends AccessRuleKey implements Serializable {
    /**
     *是否检查是否是白名单客户
     */
    private Boolean isCheckWhiteCustomer;

    /**
     *与真旅网合作月份的最小值
     */
    private Integer cooperationPeriodMin;

    /**
     *与真旅网合作月份的最小值
     */
    private Integer cooperationPeriodMax;

    /**
     *逾期率的最大值（真旅网平台）
     */
    private BigDecimal overdueRateTz;

    /**
     *逾期次数的最大值（真旅网平台）
     */
    private Integer overdueTimesTz;

    /**
     *逾期天数的最大值（真旅网平台）
     */
    private Integer overdueDaysTz;

    /**
     *逾期率的最大值（cana平台）
     */
    private BigDecimal overdueRateCana;

    /**
     *逾期次数的最大值（cana平台）
     */
    private Integer overdueTimesCana;

    /**
     *订单采购的增长率的最小值
     */
    private BigDecimal purchaseOrderGrowthRate;

    /**
     *法院被执行（企业）总金额（分）
     */
    private Long courtExecuteCompanyAmount;

    /**
     *法院被执行（企业）总次数
     */
    private Integer courtExecuteCompanyTimes;

    /**
     *法院被执行（个人）总金额（分）
     */
    private Long courtExecuteIndividualAmount;

    /**
     *法院被执行（个人）总次数
     */
    private Integer courtExecuteIndividualTimes;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *与真旅网合作月份的最大值
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *是否检查是否是白名单客户
     */
    public Boolean getIsCheckWhiteCustomer() {
        return isCheckWhiteCustomer;
    }

    /**
     *是否检查是否是白名单客户
     */
    public void setIsCheckWhiteCustomer(Boolean isCheckWhiteCustomer) {
        this.isCheckWhiteCustomer = isCheckWhiteCustomer;
    }

    /**
     *与真旅网合作月份的最小值
     */
    public Integer getCooperationPeriodMin() {
        return cooperationPeriodMin;
    }

    /**
     *与真旅网合作月份的最小值
     */
    public void setCooperationPeriodMin(Integer cooperationPeriodMin) {
        this.cooperationPeriodMin = cooperationPeriodMin;
    }

    /**
     *与真旅网合作月份的最小值
     */
    public Integer getCooperationPeriodMax() {
        return cooperationPeriodMax;
    }

    /**
     *与真旅网合作月份的最小值
     */
    public void setCooperationPeriodMax(Integer cooperationPeriodMax) {
        this.cooperationPeriodMax = cooperationPeriodMax;
    }

    /**
     *逾期率的最大值（真旅网平台）
     */
    public BigDecimal getOverdueRateTz() {
        return overdueRateTz;
    }

    /**
     *逾期率的最大值（真旅网平台）
     */
    public void setOverdueRateTz(BigDecimal overdueRateTz) {
        this.overdueRateTz = overdueRateTz;
    }

    /**
     *逾期次数的最大值（真旅网平台）
     */
    public Integer getOverdueTimesTz() {
        return overdueTimesTz;
    }

    /**
     *逾期次数的最大值（真旅网平台）
     */
    public void setOverdueTimesTz(Integer overdueTimesTz) {
        this.overdueTimesTz = overdueTimesTz;
    }

    /**
     *逾期天数的最大值（真旅网平台）
     */
    public Integer getOverdueDaysTz() {
        return overdueDaysTz;
    }

    /**
     *逾期天数的最大值（真旅网平台）
     */
    public void setOverdueDaysTz(Integer overdueDaysTz) {
        this.overdueDaysTz = overdueDaysTz;
    }

    /**
     *逾期率的最大值（cana平台）
     */
    public BigDecimal getOverdueRateCana() {
        return overdueRateCana;
    }

    /**
     *逾期率的最大值（cana平台）
     */
    public void setOverdueRateCana(BigDecimal overdueRateCana) {
        this.overdueRateCana = overdueRateCana;
    }

    /**
     *逾期次数的最大值（cana平台）
     */
    public Integer getOverdueTimesCana() {
        return overdueTimesCana;
    }

    /**
     *逾期次数的最大值（cana平台）
     */
    public void setOverdueTimesCana(Integer overdueTimesCana) {
        this.overdueTimesCana = overdueTimesCana;
    }

    /**
     *订单采购的增长率的最小值
     */
    public BigDecimal getPurchaseOrderGrowthRate() {
        return purchaseOrderGrowthRate;
    }

    /**
     *订单采购的增长率的最小值
     */
    public void setPurchaseOrderGrowthRate(BigDecimal purchaseOrderGrowthRate) {
        this.purchaseOrderGrowthRate = purchaseOrderGrowthRate;
    }

    /**
     *法院被执行（企业）总金额（分）
     */
    public Long getCourtExecuteCompanyAmount() {
        return courtExecuteCompanyAmount;
    }

    /**
     *法院被执行（企业）总金额（分）
     */
    public void setCourtExecuteCompanyAmount(Long courtExecuteCompanyAmount) {
        this.courtExecuteCompanyAmount = courtExecuteCompanyAmount;
    }

    /**
     *法院被执行（企业）总次数
     */
    public Integer getCourtExecuteCompanyTimes() {
        return courtExecuteCompanyTimes;
    }

    /**
     *法院被执行（企业）总次数
     */
    public void setCourtExecuteCompanyTimes(Integer courtExecuteCompanyTimes) {
        this.courtExecuteCompanyTimes = courtExecuteCompanyTimes;
    }

    /**
     *法院被执行（个人）总金额（分）
     */
    public Long getCourtExecuteIndividualAmount() {
        return courtExecuteIndividualAmount;
    }

    /**
     *法院被执行（个人）总金额（分）
     */
    public void setCourtExecuteIndividualAmount(Long courtExecuteIndividualAmount) {
        this.courtExecuteIndividualAmount = courtExecuteIndividualAmount;
    }

    /**
     *法院被执行（个人）总次数
     */
    public Integer getCourtExecuteIndividualTimes() {
        return courtExecuteIndividualTimes;
    }

    /**
     *法院被执行（个人）总次数
     */
    public void setCourtExecuteIndividualTimes(Integer courtExecuteIndividualTimes) {
        this.courtExecuteIndividualTimes = courtExecuteIndividualTimes;
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
     *与真旅网合作月份的最大值
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *与真旅网合作月份的最大值
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
        AccessRule other = (AccessRule) that;
        return (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getFitObject() == null ? other.getFitObject() == null : this.getFitObject().equals(other.getFitObject()))
            && (this.getIsCheckWhiteCustomer() == null ? other.getIsCheckWhiteCustomer() == null : this.getIsCheckWhiteCustomer().equals(other.getIsCheckWhiteCustomer()))
            && (this.getCooperationPeriodMin() == null ? other.getCooperationPeriodMin() == null : this.getCooperationPeriodMin().equals(other.getCooperationPeriodMin()))
            && (this.getCooperationPeriodMax() == null ? other.getCooperationPeriodMax() == null : this.getCooperationPeriodMax().equals(other.getCooperationPeriodMax()))
            && (this.getOverdueRateTz() == null ? other.getOverdueRateTz() == null : this.getOverdueRateTz().equals(other.getOverdueRateTz()))
            && (this.getOverdueTimesTz() == null ? other.getOverdueTimesTz() == null : this.getOverdueTimesTz().equals(other.getOverdueTimesTz()))
            && (this.getOverdueDaysTz() == null ? other.getOverdueDaysTz() == null : this.getOverdueDaysTz().equals(other.getOverdueDaysTz()))
            && (this.getOverdueRateCana() == null ? other.getOverdueRateCana() == null : this.getOverdueRateCana().equals(other.getOverdueRateCana()))
            && (this.getOverdueTimesCana() == null ? other.getOverdueTimesCana() == null : this.getOverdueTimesCana().equals(other.getOverdueTimesCana()))
            && (this.getPurchaseOrderGrowthRate() == null ? other.getPurchaseOrderGrowthRate() == null : this.getPurchaseOrderGrowthRate().equals(other.getPurchaseOrderGrowthRate()))
            && (this.getCourtExecuteCompanyAmount() == null ? other.getCourtExecuteCompanyAmount() == null : this.getCourtExecuteCompanyAmount().equals(other.getCourtExecuteCompanyAmount()))
            && (this.getCourtExecuteCompanyTimes() == null ? other.getCourtExecuteCompanyTimes() == null : this.getCourtExecuteCompanyTimes().equals(other.getCourtExecuteCompanyTimes()))
            && (this.getCourtExecuteIndividualAmount() == null ? other.getCourtExecuteIndividualAmount() == null : this.getCourtExecuteIndividualAmount().equals(other.getCourtExecuteIndividualAmount()))
            && (this.getCourtExecuteIndividualTimes() == null ? other.getCourtExecuteIndividualTimes() == null : this.getCourtExecuteIndividualTimes().equals(other.getCourtExecuteIndividualTimes()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getFitObject() == null) ? 0 : getFitObject().hashCode());
        result = prime * result + ((getIsCheckWhiteCustomer() == null) ? 0 : getIsCheckWhiteCustomer().hashCode());
        result = prime * result + ((getCooperationPeriodMin() == null) ? 0 : getCooperationPeriodMin().hashCode());
        result = prime * result + ((getCooperationPeriodMax() == null) ? 0 : getCooperationPeriodMax().hashCode());
        result = prime * result + ((getOverdueRateTz() == null) ? 0 : getOverdueRateTz().hashCode());
        result = prime * result + ((getOverdueTimesTz() == null) ? 0 : getOverdueTimesTz().hashCode());
        result = prime * result + ((getOverdueDaysTz() == null) ? 0 : getOverdueDaysTz().hashCode());
        result = prime * result + ((getOverdueRateCana() == null) ? 0 : getOverdueRateCana().hashCode());
        result = prime * result + ((getOverdueTimesCana() == null) ? 0 : getOverdueTimesCana().hashCode());
        result = prime * result + ((getPurchaseOrderGrowthRate() == null) ? 0 : getPurchaseOrderGrowthRate().hashCode());
        result = prime * result + ((getCourtExecuteCompanyAmount() == null) ? 0 : getCourtExecuteCompanyAmount().hashCode());
        result = prime * result + ((getCourtExecuteCompanyTimes() == null) ? 0 : getCourtExecuteCompanyTimes().hashCode());
        result = prime * result + ((getCourtExecuteIndividualAmount() == null) ? 0 : getCourtExecuteIndividualAmount().hashCode());
        result = prime * result + ((getCourtExecuteIndividualTimes() == null) ? 0 : getCourtExecuteIndividualTimes().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}