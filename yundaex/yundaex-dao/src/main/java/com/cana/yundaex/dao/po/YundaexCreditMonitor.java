/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class YundaexCreditMonitor implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *站点编号
     */
    private String stationNo;

    /**
     *站点负责人/公司名称
     */
    private String stationMgr;

    /**
     *站点名称
     */
    private String stationName;

    /**
     *Cana用户的ID
     */
    private String memberId;

    /**
     *揽派件增长率
     */
    private BigDecimal recandsendGrowthRate;

    /**
     *日资金需求（单位：分）
     */
    private Long dayRequirements;

    /**
     *韵达评分
     */
    private BigDecimal yundaexGrade;

    /**
     *上个月韵达评分
     */
    private BigDecimal lastYundaexGrade;

    /**
     *保证金账户余额（单位：分）
     */
    private Long bailBalance;

    /**
     *净现金流（单位：分）
     */
    private Long netCashflow;

    /**
     *最大授信金额（单位：分）
     */
    private Long creditLimit;

    /**
     *上个月最大授信金额（单位：分）
     */
    private Long lastCreditLimit;

    /**
     *逾期次数
     */
    private Integer overdues;

    /**
     *利率(每个还款方式都有json字符串)
     */
    private String interestRate;

    /**
     *月份（yyyy-MM）
     */
    private String month;

    /**
     *审核状态
     */
    private String auditStatus;

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
     *站点编号
     */
    public String getStationNo() {
        return stationNo;
    }

    /**
     *站点编号
     */
    public void setStationNo(String stationNo) {
        this.stationNo = stationNo == null ? null : stationNo.trim();
    }

    /**
     *站点负责人/公司名称
     */
    public String getStationMgr() {
        return stationMgr;
    }

    /**
     *站点负责人/公司名称
     */
    public void setStationMgr(String stationMgr) {
        this.stationMgr = stationMgr == null ? null : stationMgr.trim();
    }

    /**
     *站点名称
     */
    public String getStationName() {
        return stationName;
    }

    /**
     *站点名称
     */
    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    /**
     *Cana用户的ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     *Cana用户的ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     *揽派件增长率
     */
    public BigDecimal getRecandsendGrowthRate() {
        return recandsendGrowthRate;
    }

    /**
     *揽派件增长率
     */
    public void setRecandsendGrowthRate(BigDecimal recandsendGrowthRate) {
        this.recandsendGrowthRate = recandsendGrowthRate;
    }

    /**
     *日资金需求（单位：分）
     */
    public Long getDayRequirements() {
        return dayRequirements;
    }

    /**
     *日资金需求（单位：分）
     */
    public void setDayRequirements(Long dayRequirements) {
        this.dayRequirements = dayRequirements;
    }

    /**
     *韵达评分
     */
    public BigDecimal getYundaexGrade() {
        return yundaexGrade;
    }

    /**
     *韵达评分
     */
    public void setYundaexGrade(BigDecimal yundaexGrade) {
        this.yundaexGrade = yundaexGrade;
    }

    /**
     *上个月韵达评分
     */
    public BigDecimal getLastYundaexGrade() {
        return lastYundaexGrade;
    }

    /**
     *上个月韵达评分
     */
    public void setLastYundaexGrade(BigDecimal lastYundaexGrade) {
        this.lastYundaexGrade = lastYundaexGrade;
    }

    /**
     *保证金账户余额（单位：分）
     */
    public Long getBailBalance() {
        return bailBalance;
    }

    /**
     *保证金账户余额（单位：分）
     */
    public void setBailBalance(Long bailBalance) {
        this.bailBalance = bailBalance;
    }

    /**
     *净现金流（单位：分）
     */
    public Long getNetCashflow() {
        return netCashflow;
    }

    /**
     *净现金流（单位：分）
     */
    public void setNetCashflow(Long netCashflow) {
        this.netCashflow = netCashflow;
    }

    /**
     *最大授信金额（单位：分）
     */
    public Long getCreditLimit() {
        return creditLimit;
    }

    /**
     *最大授信金额（单位：分）
     */
    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     *上个月最大授信金额（单位：分）
     */
    public Long getLastCreditLimit() {
        return lastCreditLimit;
    }

    /**
     *上个月最大授信金额（单位：分）
     */
    public void setLastCreditLimit(Long lastCreditLimit) {
        this.lastCreditLimit = lastCreditLimit;
    }

    /**
     *逾期次数
     */
    public Integer getOverdues() {
        return overdues;
    }

    /**
     *逾期次数
     */
    public void setOverdues(Integer overdues) {
        this.overdues = overdues;
    }

    /**
     *利率(每个还款方式都有json字符串)
     */
    public String getInterestRate() {
        return interestRate;
    }

    /**
     *利率(每个还款方式都有json字符串)
     */
    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate == null ? null : interestRate.trim();
    }

    /**
     *月份（yyyy-MM）
     */
    public String getMonth() {
        return month;
    }

    /**
     *月份（yyyy-MM）
     */
    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    /**
     *审核状态
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     *审核状态
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
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
        YundaexCreditMonitor other = (YundaexCreditMonitor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStationNo() == null ? other.getStationNo() == null : this.getStationNo().equals(other.getStationNo()))
            && (this.getStationMgr() == null ? other.getStationMgr() == null : this.getStationMgr().equals(other.getStationMgr()))
            && (this.getStationName() == null ? other.getStationName() == null : this.getStationName().equals(other.getStationName()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getRecandsendGrowthRate() == null ? other.getRecandsendGrowthRate() == null : this.getRecandsendGrowthRate().equals(other.getRecandsendGrowthRate()))
            && (this.getDayRequirements() == null ? other.getDayRequirements() == null : this.getDayRequirements().equals(other.getDayRequirements()))
            && (this.getYundaexGrade() == null ? other.getYundaexGrade() == null : this.getYundaexGrade().equals(other.getYundaexGrade()))
            && (this.getLastYundaexGrade() == null ? other.getLastYundaexGrade() == null : this.getLastYundaexGrade().equals(other.getLastYundaexGrade()))
            && (this.getBailBalance() == null ? other.getBailBalance() == null : this.getBailBalance().equals(other.getBailBalance()))
            && (this.getNetCashflow() == null ? other.getNetCashflow() == null : this.getNetCashflow().equals(other.getNetCashflow()))
            && (this.getCreditLimit() == null ? other.getCreditLimit() == null : this.getCreditLimit().equals(other.getCreditLimit()))
            && (this.getLastCreditLimit() == null ? other.getLastCreditLimit() == null : this.getLastCreditLimit().equals(other.getLastCreditLimit()))
            && (this.getOverdues() == null ? other.getOverdues() == null : this.getOverdues().equals(other.getOverdues()))
            && (this.getInterestRate() == null ? other.getInterestRate() == null : this.getInterestRate().equals(other.getInterestRate()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getAuditStatus() == null ? other.getAuditStatus() == null : this.getAuditStatus().equals(other.getAuditStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStationNo() == null) ? 0 : getStationNo().hashCode());
        result = prime * result + ((getStationMgr() == null) ? 0 : getStationMgr().hashCode());
        result = prime * result + ((getStationName() == null) ? 0 : getStationName().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getRecandsendGrowthRate() == null) ? 0 : getRecandsendGrowthRate().hashCode());
        result = prime * result + ((getDayRequirements() == null) ? 0 : getDayRequirements().hashCode());
        result = prime * result + ((getYundaexGrade() == null) ? 0 : getYundaexGrade().hashCode());
        result = prime * result + ((getLastYundaexGrade() == null) ? 0 : getLastYundaexGrade().hashCode());
        result = prime * result + ((getBailBalance() == null) ? 0 : getBailBalance().hashCode());
        result = prime * result + ((getNetCashflow() == null) ? 0 : getNetCashflow().hashCode());
        result = prime * result + ((getCreditLimit() == null) ? 0 : getCreditLimit().hashCode());
        result = prime * result + ((getLastCreditLimit() == null) ? 0 : getLastCreditLimit().hashCode());
        result = prime * result + ((getOverdues() == null) ? 0 : getOverdues().hashCode());
        result = prime * result + ((getInterestRate() == null) ? 0 : getInterestRate().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getAuditStatus() == null) ? 0 : getAuditStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}