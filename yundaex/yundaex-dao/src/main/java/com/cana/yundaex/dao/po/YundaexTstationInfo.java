/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class YundaexTstationInfo implements Serializable {
    /**
     *主键。记录ID。唯一。
     */
    private Integer id;

    /**
     *网点id，唯一
     */
    private String stationNo;

    /**
     *统计年月，yyyy-MM
     */
    private String statmonth;

    /**
     *揽件总数
     */
    private Integer receiveTotal;

    /**
     *日平均揽件数
     */
    private BigDecimal avgReceiveNum;

    /**
     *已签收总数（揽件）
     */
    private Integer receiveSumSigned;

    /**
     *未签收总数（揽件）
     */
    private Integer receiveSumUnsign;

    /**
     *派件总数
     */
    private Integer sendTotal;

    /**
     *日平均到件数
     */
    private BigDecimal avgSendNum;

    /**
     *已签收总数（派件）
     */
    private Integer sendSumSigned;

    /**
     *未签收总数（派件）
     */
    private Integer sendSumUnsign;

    /**
     *揽件派件月差额
     */
    private Integer recSendDif;

    /**
     *揽派比
     */
    private BigDecimal recSendRatio;

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
     *主键。记录ID。唯一。
     */
    public Integer getId() {
        return id;
    }

    /**
     *主键。记录ID。唯一。
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *网点id，唯一
     */
    public String getStationNo() {
        return stationNo;
    }

    /**
     *网点id，唯一
     */
    public void setStationNo(String stationNo) {
        this.stationNo = stationNo == null ? null : stationNo.trim();
    }

    /**
     *统计年月，yyyy-MM
     */
    public String getStatmonth() {
        return statmonth;
    }

    /**
     *统计年月，yyyy-MM
     */
    public void setStatmonth(String statmonth) {
        this.statmonth = statmonth == null ? null : statmonth.trim();
    }

    /**
     *揽件总数
     */
    public Integer getReceiveTotal() {
        return receiveTotal;
    }

    /**
     *揽件总数
     */
    public void setReceiveTotal(Integer receiveTotal) {
        this.receiveTotal = receiveTotal;
    }

    /**
     *日平均揽件数
     */
    public BigDecimal getAvgReceiveNum() {
        return avgReceiveNum;
    }

    /**
     *日平均揽件数
     */
    public void setAvgReceiveNum(BigDecimal avgReceiveNum) {
        this.avgReceiveNum = avgReceiveNum;
    }

    /**
     *已签收总数（揽件）
     */
    public Integer getReceiveSumSigned() {
        return receiveSumSigned;
    }

    /**
     *已签收总数（揽件）
     */
    public void setReceiveSumSigned(Integer receiveSumSigned) {
        this.receiveSumSigned = receiveSumSigned;
    }

    /**
     *未签收总数（揽件）
     */
    public Integer getReceiveSumUnsign() {
        return receiveSumUnsign;
    }

    /**
     *未签收总数（揽件）
     */
    public void setReceiveSumUnsign(Integer receiveSumUnsign) {
        this.receiveSumUnsign = receiveSumUnsign;
    }

    /**
     *派件总数
     */
    public Integer getSendTotal() {
        return sendTotal;
    }

    /**
     *派件总数
     */
    public void setSendTotal(Integer sendTotal) {
        this.sendTotal = sendTotal;
    }

    /**
     *日平均到件数
     */
    public BigDecimal getAvgSendNum() {
        return avgSendNum;
    }

    /**
     *日平均到件数
     */
    public void setAvgSendNum(BigDecimal avgSendNum) {
        this.avgSendNum = avgSendNum;
    }

    /**
     *已签收总数（派件）
     */
    public Integer getSendSumSigned() {
        return sendSumSigned;
    }

    /**
     *已签收总数（派件）
     */
    public void setSendSumSigned(Integer sendSumSigned) {
        this.sendSumSigned = sendSumSigned;
    }

    /**
     *未签收总数（派件）
     */
    public Integer getSendSumUnsign() {
        return sendSumUnsign;
    }

    /**
     *未签收总数（派件）
     */
    public void setSendSumUnsign(Integer sendSumUnsign) {
        this.sendSumUnsign = sendSumUnsign;
    }

    /**
     *揽件派件月差额
     */
    public Integer getRecSendDif() {
        return recSendDif;
    }

    /**
     *揽件派件月差额
     */
    public void setRecSendDif(Integer recSendDif) {
        this.recSendDif = recSendDif;
    }

    /**
     *揽派比
     */
    public BigDecimal getRecSendRatio() {
        return recSendRatio;
    }

    /**
     *揽派比
     */
    public void setRecSendRatio(BigDecimal recSendRatio) {
        this.recSendRatio = recSendRatio;
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
        YundaexTstationInfo other = (YundaexTstationInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStationNo() == null ? other.getStationNo() == null : this.getStationNo().equals(other.getStationNo()))
            && (this.getStatmonth() == null ? other.getStatmonth() == null : this.getStatmonth().equals(other.getStatmonth()))
            && (this.getReceiveTotal() == null ? other.getReceiveTotal() == null : this.getReceiveTotal().equals(other.getReceiveTotal()))
            && (this.getAvgReceiveNum() == null ? other.getAvgReceiveNum() == null : this.getAvgReceiveNum().equals(other.getAvgReceiveNum()))
            && (this.getReceiveSumSigned() == null ? other.getReceiveSumSigned() == null : this.getReceiveSumSigned().equals(other.getReceiveSumSigned()))
            && (this.getReceiveSumUnsign() == null ? other.getReceiveSumUnsign() == null : this.getReceiveSumUnsign().equals(other.getReceiveSumUnsign()))
            && (this.getSendTotal() == null ? other.getSendTotal() == null : this.getSendTotal().equals(other.getSendTotal()))
            && (this.getAvgSendNum() == null ? other.getAvgSendNum() == null : this.getAvgSendNum().equals(other.getAvgSendNum()))
            && (this.getSendSumSigned() == null ? other.getSendSumSigned() == null : this.getSendSumSigned().equals(other.getSendSumSigned()))
            && (this.getSendSumUnsign() == null ? other.getSendSumUnsign() == null : this.getSendSumUnsign().equals(other.getSendSumUnsign()))
            && (this.getRecSendDif() == null ? other.getRecSendDif() == null : this.getRecSendDif().equals(other.getRecSendDif()))
            && (this.getRecSendRatio() == null ? other.getRecSendRatio() == null : this.getRecSendRatio().equals(other.getRecSendRatio()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStationNo() == null) ? 0 : getStationNo().hashCode());
        result = prime * result + ((getStatmonth() == null) ? 0 : getStatmonth().hashCode());
        result = prime * result + ((getReceiveTotal() == null) ? 0 : getReceiveTotal().hashCode());
        result = prime * result + ((getAvgReceiveNum() == null) ? 0 : getAvgReceiveNum().hashCode());
        result = prime * result + ((getReceiveSumSigned() == null) ? 0 : getReceiveSumSigned().hashCode());
        result = prime * result + ((getReceiveSumUnsign() == null) ? 0 : getReceiveSumUnsign().hashCode());
        result = prime * result + ((getSendTotal() == null) ? 0 : getSendTotal().hashCode());
        result = prime * result + ((getAvgSendNum() == null) ? 0 : getAvgSendNum().hashCode());
        result = prime * result + ((getSendSumSigned() == null) ? 0 : getSendSumSigned().hashCode());
        result = prime * result + ((getSendSumUnsign() == null) ? 0 : getSendSumUnsign().hashCode());
        result = prime * result + ((getRecSendDif() == null) ? 0 : getRecSendDif().hashCode());
        result = prime * result + ((getRecSendRatio() == null) ? 0 : getRecSendRatio().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}