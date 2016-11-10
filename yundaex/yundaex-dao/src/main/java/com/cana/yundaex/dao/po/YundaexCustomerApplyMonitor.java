/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.util.Date;

public class YundaexCustomerApplyMonitor implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *站点编号
     */
    private String stationNo;

    /**
     *站点数量
     */
    private Integer stationAmount;

    /**
     *站点负责人/公司名称
     */
    private String stationMgr;

    /**
     *站点名称
     */
    private String stationName;

    /**
     *加盟年限
     */
    private Long busiLimit;

    /**
     *保证金账户余额（单位：分）
     */
    private Long bailBalance;

    /**
     *短期借款（单位：分）
     */
    private Long shortLoan;

    /**
     *借款类型
     */
    private String loanType;

    /**
     *借款期限
     */
    private Integer loanLimit;

    /**
     *期限单位
     */
    private String limitUnit;

    /**
     *韵达评价
     */
    private String yundaexJudge;

    /**
     *月份（yyyy-MM）
     */
    private String month;

    /**
     *状态
     */
    private String status;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *修改时间
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
     *站点数量
     */
    public Integer getStationAmount() {
        return stationAmount;
    }

    /**
     *站点数量
     */
    public void setStationAmount(Integer stationAmount) {
        this.stationAmount = stationAmount;
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
     *加盟年限
     */
    public Long getBusiLimit() {
        return busiLimit;
    }

    /**
     *加盟年限
     */
    public void setBusiLimit(Long busiLimit) {
        this.busiLimit = busiLimit;
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
     *短期借款（单位：分）
     */
    public Long getShortLoan() {
        return shortLoan;
    }

    /**
     *短期借款（单位：分）
     */
    public void setShortLoan(Long shortLoan) {
        this.shortLoan = shortLoan;
    }

    /**
     *借款类型
     */
    public String getLoanType() {
        return loanType;
    }

    /**
     *借款类型
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType == null ? null : loanType.trim();
    }

    /**
     *借款期限
     */
    public Integer getLoanLimit() {
        return loanLimit;
    }

    /**
     *借款期限
     */
    public void setLoanLimit(Integer loanLimit) {
        this.loanLimit = loanLimit;
    }

    /**
     *期限单位
     */
    public String getLimitUnit() {
        return limitUnit;
    }

    /**
     *期限单位
     */
    public void setLimitUnit(String limitUnit) {
        this.limitUnit = limitUnit == null ? null : limitUnit.trim();
    }

    /**
     *韵达评价
     */
    public String getYundaexJudge() {
        return yundaexJudge;
    }

    /**
     *韵达评价
     */
    public void setYundaexJudge(String yundaexJudge) {
        this.yundaexJudge = yundaexJudge == null ? null : yundaexJudge.trim();
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
     *状态
     */
    public String getStatus() {
        return status;
    }

    /**
     *状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     *修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *修改时间
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
        YundaexCustomerApplyMonitor other = (YundaexCustomerApplyMonitor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStationNo() == null ? other.getStationNo() == null : this.getStationNo().equals(other.getStationNo()))
            && (this.getStationAmount() == null ? other.getStationAmount() == null : this.getStationAmount().equals(other.getStationAmount()))
            && (this.getStationMgr() == null ? other.getStationMgr() == null : this.getStationMgr().equals(other.getStationMgr()))
            && (this.getStationName() == null ? other.getStationName() == null : this.getStationName().equals(other.getStationName()))
            && (this.getBusiLimit() == null ? other.getBusiLimit() == null : this.getBusiLimit().equals(other.getBusiLimit()))
            && (this.getBailBalance() == null ? other.getBailBalance() == null : this.getBailBalance().equals(other.getBailBalance()))
            && (this.getShortLoan() == null ? other.getShortLoan() == null : this.getShortLoan().equals(other.getShortLoan()))
            && (this.getLoanType() == null ? other.getLoanType() == null : this.getLoanType().equals(other.getLoanType()))
            && (this.getLoanLimit() == null ? other.getLoanLimit() == null : this.getLoanLimit().equals(other.getLoanLimit()))
            && (this.getLimitUnit() == null ? other.getLimitUnit() == null : this.getLimitUnit().equals(other.getLimitUnit()))
            && (this.getYundaexJudge() == null ? other.getYundaexJudge() == null : this.getYundaexJudge().equals(other.getYundaexJudge()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStationNo() == null) ? 0 : getStationNo().hashCode());
        result = prime * result + ((getStationAmount() == null) ? 0 : getStationAmount().hashCode());
        result = prime * result + ((getStationMgr() == null) ? 0 : getStationMgr().hashCode());
        result = prime * result + ((getStationName() == null) ? 0 : getStationName().hashCode());
        result = prime * result + ((getBusiLimit() == null) ? 0 : getBusiLimit().hashCode());
        result = prime * result + ((getBailBalance() == null) ? 0 : getBailBalance().hashCode());
        result = prime * result + ((getShortLoan() == null) ? 0 : getShortLoan().hashCode());
        result = prime * result + ((getLoanType() == null) ? 0 : getLoanType().hashCode());
        result = prime * result + ((getLoanLimit() == null) ? 0 : getLoanLimit().hashCode());
        result = prime * result + ((getLimitUnit() == null) ? 0 : getLimitUnit().hashCode());
        result = prime * result + ((getYundaexJudge() == null) ? 0 : getYundaexJudge().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}