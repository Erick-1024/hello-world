/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexAuditRule implements Serializable {
    /**
     *批次号
     */
    private Integer batchNo;

    /**
     *申请企业的地址
     */
    private String applycustomerAddress;

    /**
     *与韵达合作年限(2年为标准)
     */
    private Integer cooperationPeriod;

    /**
     *揽派件增长率(5%为标准)
     */
    private BigDecimal receiveSendGrowthRate;

    /**
     *负面信息搜索（1;有   0:无）
     */
    private Boolean negativeInfomationSearch;

    /**
     *是否有法院被执行人信息
     */
    private Boolean isCourtExecuteIndividualInfo;

    /**
     *企业网点位置(1:市区or集镇   0:村镇)
     */
    private Boolean stationAddress;

    /**
     *保证金金额（1:>=0   0:<0）
     */
    private Boolean depositAmount;

    /**
     *是否与韵达签署特许经营(加盟)合同
     */
    private Boolean isRanchiseContract;

    /**
     *年检记录是否合格
     */
    private Boolean isQualifiedInspectionRecord;

    private static final long serialVersionUID = 1L;

    /**
     *批次号
     */
    public Integer getBatchNo() {
        return batchNo;
    }

    /**
     *批次号
     */
    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }

    /**
     *申请企业的地址
     */
    public String getApplycustomerAddress() {
        return applycustomerAddress;
    }

    /**
     *申请企业的地址
     */
    public void setApplycustomerAddress(String applycustomerAddress) {
        this.applycustomerAddress = applycustomerAddress == null ? null : applycustomerAddress.trim();
    }

    /**
     *与韵达合作年限(2年为标准)
     */
    public Integer getCooperationPeriod() {
        return cooperationPeriod;
    }

    /**
     *与韵达合作年限(2年为标准)
     */
    public void setCooperationPeriod(Integer cooperationPeriod) {
        this.cooperationPeriod = cooperationPeriod;
    }

    /**
     *揽派件增长率(5%为标准)
     */
    public BigDecimal getReceiveSendGrowthRate() {
        return receiveSendGrowthRate;
    }

    /**
     *揽派件增长率(5%为标准)
     */
    public void setReceiveSendGrowthRate(BigDecimal receiveSendGrowthRate) {
        this.receiveSendGrowthRate = receiveSendGrowthRate;
    }

    /**
     *负面信息搜索（1;有   0:无）
     */
    public Boolean getNegativeInfomationSearch() {
        return negativeInfomationSearch;
    }

    /**
     *负面信息搜索（1;有   0:无）
     */
    public void setNegativeInfomationSearch(Boolean negativeInfomationSearch) {
        this.negativeInfomationSearch = negativeInfomationSearch;
    }

    /**
     *是否有法院被执行人信息
     */
    public Boolean getIsCourtExecuteIndividualInfo() {
        return isCourtExecuteIndividualInfo;
    }

    /**
     *是否有法院被执行人信息
     */
    public void setIsCourtExecuteIndividualInfo(Boolean isCourtExecuteIndividualInfo) {
        this.isCourtExecuteIndividualInfo = isCourtExecuteIndividualInfo;
    }

    /**
     *企业网点位置(1:市区or集镇   0:村镇)
     */
    public Boolean getStationAddress() {
        return stationAddress;
    }

    /**
     *企业网点位置(1:市区or集镇   0:村镇)
     */
    public void setStationAddress(Boolean stationAddress) {
        this.stationAddress = stationAddress;
    }

    /**
     *保证金金额（1:>=0   0:<0）
     */
    public Boolean getDepositAmount() {
        return depositAmount;
    }

    /**
     *保证金金额（1:>=0   0:<0）
     */
    public void setDepositAmount(Boolean depositAmount) {
        this.depositAmount = depositAmount;
    }

    /**
     *是否与韵达签署特许经营(加盟)合同
     */
    public Boolean getIsRanchiseContract() {
        return isRanchiseContract;
    }

    /**
     *是否与韵达签署特许经营(加盟)合同
     */
    public void setIsRanchiseContract(Boolean isRanchiseContract) {
        this.isRanchiseContract = isRanchiseContract;
    }

    /**
     *年检记录是否合格
     */
    public Boolean getIsQualifiedInspectionRecord() {
        return isQualifiedInspectionRecord;
    }

    /**
     *年检记录是否合格
     */
    public void setIsQualifiedInspectionRecord(Boolean isQualifiedInspectionRecord) {
        this.isQualifiedInspectionRecord = isQualifiedInspectionRecord;
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
        YundaexAuditRule other = (YundaexAuditRule) that;
        return (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getApplycustomerAddress() == null ? other.getApplycustomerAddress() == null : this.getApplycustomerAddress().equals(other.getApplycustomerAddress()))
            && (this.getCooperationPeriod() == null ? other.getCooperationPeriod() == null : this.getCooperationPeriod().equals(other.getCooperationPeriod()))
            && (this.getReceiveSendGrowthRate() == null ? other.getReceiveSendGrowthRate() == null : this.getReceiveSendGrowthRate().equals(other.getReceiveSendGrowthRate()))
            && (this.getNegativeInfomationSearch() == null ? other.getNegativeInfomationSearch() == null : this.getNegativeInfomationSearch().equals(other.getNegativeInfomationSearch()))
            && (this.getIsCourtExecuteIndividualInfo() == null ? other.getIsCourtExecuteIndividualInfo() == null : this.getIsCourtExecuteIndividualInfo().equals(other.getIsCourtExecuteIndividualInfo()))
            && (this.getStationAddress() == null ? other.getStationAddress() == null : this.getStationAddress().equals(other.getStationAddress()))
            && (this.getDepositAmount() == null ? other.getDepositAmount() == null : this.getDepositAmount().equals(other.getDepositAmount()))
            && (this.getIsRanchiseContract() == null ? other.getIsRanchiseContract() == null : this.getIsRanchiseContract().equals(other.getIsRanchiseContract()))
            && (this.getIsQualifiedInspectionRecord() == null ? other.getIsQualifiedInspectionRecord() == null : this.getIsQualifiedInspectionRecord().equals(other.getIsQualifiedInspectionRecord()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getApplycustomerAddress() == null) ? 0 : getApplycustomerAddress().hashCode());
        result = prime * result + ((getCooperationPeriod() == null) ? 0 : getCooperationPeriod().hashCode());
        result = prime * result + ((getReceiveSendGrowthRate() == null) ? 0 : getReceiveSendGrowthRate().hashCode());
        result = prime * result + ((getNegativeInfomationSearch() == null) ? 0 : getNegativeInfomationSearch().hashCode());
        result = prime * result + ((getIsCourtExecuteIndividualInfo() == null) ? 0 : getIsCourtExecuteIndividualInfo().hashCode());
        result = prime * result + ((getStationAddress() == null) ? 0 : getStationAddress().hashCode());
        result = prime * result + ((getDepositAmount() == null) ? 0 : getDepositAmount().hashCode());
        result = prime * result + ((getIsRanchiseContract() == null) ? 0 : getIsRanchiseContract().hashCode());
        result = prime * result + ((getIsQualifiedInspectionRecord() == null) ? 0 : getIsQualifiedInspectionRecord().hashCode());
        return result;
    }
}