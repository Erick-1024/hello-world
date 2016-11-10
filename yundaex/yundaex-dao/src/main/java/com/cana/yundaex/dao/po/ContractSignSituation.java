/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ContractSignSituation implements Serializable {
    /**
     *主键　（使用企业用户ｉｄ）
     */
    private String id;

    /**
     *网点客户名称
     */
    private String stationName;

    /**
     *签约状态（机器码）
     */
    private Integer signSituation;

    /**
     *完成时间
     */
    private Date signCompleteTime;

    /**
     *协议编号
     */
    private String protocolNo;

    /**
     *
     */
    private String payAccountName;

    /**
     *放款人账号
     */
    private String payAccountNo;

    /**
     *放款人银行地址
     */
    private String payAccountBank;

    /**
     *
     */
    private String payLianHangNo;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *融资合同签署状态（无，未签署，已签署）
     */
    private String financeContractSignState;

    /**
     *授权合同签署状态（无，未签署，已签署）
     */
    private String creditContractSignState;

    /**
     *放款人姓名
     */
    private String dutyContractSignState;

    private static final long serialVersionUID = 1L;

    /**
     *主键　（使用企业用户ｉｄ）
     */
    public String getId() {
        return id;
    }

    /**
     *主键　（使用企业用户ｉｄ）
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *网点客户名称
     */
    public String getStationName() {
        return stationName;
    }

    /**
     *网点客户名称
     */
    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    /**
     *签约状态（机器码）
     */
    public Integer getSignSituation() {
        return signSituation;
    }

    /**
     *签约状态（机器码）
     */
    public void setSignSituation(Integer signSituation) {
        this.signSituation = signSituation;
    }

    /**
     *完成时间
     */
    public Date getSignCompleteTime() {
        return signCompleteTime;
    }

    /**
     *完成时间
     */
    public void setSignCompleteTime(Date signCompleteTime) {
        this.signCompleteTime = signCompleteTime;
    }

    /**
     *协议编号
     */
    public String getProtocolNo() {
        return protocolNo;
    }

    /**
     *协议编号
     */
    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo == null ? null : protocolNo.trim();
    }

    /**
     *
     */
    public String getPayAccountName() {
        return payAccountName;
    }

    /**
     *
     */
    public void setPayAccountName(String payAccountName) {
        this.payAccountName = payAccountName == null ? null : payAccountName.trim();
    }

    /**
     *放款人账号
     */
    public String getPayAccountNo() {
        return payAccountNo;
    }

    /**
     *放款人账号
     */
    public void setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo == null ? null : payAccountNo.trim();
    }

    /**
     *放款人银行地址
     */
    public String getPayAccountBank() {
        return payAccountBank;
    }

    /**
     *放款人银行地址
     */
    public void setPayAccountBank(String payAccountBank) {
        this.payAccountBank = payAccountBank == null ? null : payAccountBank.trim();
    }

    /**
     *
     */
    public String getPayLianHangNo() {
        return payLianHangNo;
    }

    /**
     *
     */
    public void setPayLianHangNo(String payLianHangNo) {
        this.payLianHangNo = payLianHangNo == null ? null : payLianHangNo.trim();
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
     *融资合同签署状态（无，未签署，已签署）
     */
    public String getFinanceContractSignState() {
        return financeContractSignState;
    }

    /**
     *融资合同签署状态（无，未签署，已签署）
     */
    public void setFinanceContractSignState(String financeContractSignState) {
        this.financeContractSignState = financeContractSignState == null ? null : financeContractSignState.trim();
    }

    /**
     *授权合同签署状态（无，未签署，已签署）
     */
    public String getCreditContractSignState() {
        return creditContractSignState;
    }

    /**
     *授权合同签署状态（无，未签署，已签署）
     */
    public void setCreditContractSignState(String creditContractSignState) {
        this.creditContractSignState = creditContractSignState == null ? null : creditContractSignState.trim();
    }

    /**
     *放款人姓名
     */
    public String getDutyContractSignState() {
        return dutyContractSignState;
    }

    /**
     *放款人姓名
     */
    public void setDutyContractSignState(String dutyContractSignState) {
        this.dutyContractSignState = dutyContractSignState == null ? null : dutyContractSignState.trim();
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
        ContractSignSituation other = (ContractSignSituation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStationName() == null ? other.getStationName() == null : this.getStationName().equals(other.getStationName()))
            && (this.getSignSituation() == null ? other.getSignSituation() == null : this.getSignSituation().equals(other.getSignSituation()))
            && (this.getSignCompleteTime() == null ? other.getSignCompleteTime() == null : this.getSignCompleteTime().equals(other.getSignCompleteTime()))
            && (this.getProtocolNo() == null ? other.getProtocolNo() == null : this.getProtocolNo().equals(other.getProtocolNo()))
            && (this.getPayAccountName() == null ? other.getPayAccountName() == null : this.getPayAccountName().equals(other.getPayAccountName()))
            && (this.getPayAccountNo() == null ? other.getPayAccountNo() == null : this.getPayAccountNo().equals(other.getPayAccountNo()))
            && (this.getPayAccountBank() == null ? other.getPayAccountBank() == null : this.getPayAccountBank().equals(other.getPayAccountBank()))
            && (this.getPayLianHangNo() == null ? other.getPayLianHangNo() == null : this.getPayLianHangNo().equals(other.getPayLianHangNo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getFinanceContractSignState() == null ? other.getFinanceContractSignState() == null : this.getFinanceContractSignState().equals(other.getFinanceContractSignState()))
            && (this.getCreditContractSignState() == null ? other.getCreditContractSignState() == null : this.getCreditContractSignState().equals(other.getCreditContractSignState()))
            && (this.getDutyContractSignState() == null ? other.getDutyContractSignState() == null : this.getDutyContractSignState().equals(other.getDutyContractSignState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStationName() == null) ? 0 : getStationName().hashCode());
        result = prime * result + ((getSignSituation() == null) ? 0 : getSignSituation().hashCode());
        result = prime * result + ((getSignCompleteTime() == null) ? 0 : getSignCompleteTime().hashCode());
        result = prime * result + ((getProtocolNo() == null) ? 0 : getProtocolNo().hashCode());
        result = prime * result + ((getPayAccountName() == null) ? 0 : getPayAccountName().hashCode());
        result = prime * result + ((getPayAccountNo() == null) ? 0 : getPayAccountNo().hashCode());
        result = prime * result + ((getPayAccountBank() == null) ? 0 : getPayAccountBank().hashCode());
        result = prime * result + ((getPayLianHangNo() == null) ? 0 : getPayLianHangNo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getFinanceContractSignState() == null) ? 0 : getFinanceContractSignState().hashCode());
        result = prime * result + ((getCreditContractSignState() == null) ? 0 : getCreditContractSignState().hashCode());
        result = prime * result + ((getDutyContractSignState() == null) ? 0 : getDutyContractSignState().hashCode());
        return result;
    }
}