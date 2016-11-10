/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.bankgate.server.po;

import java.util.Date;

public class BankgateTrans {
    /**
     *主键
     */
    private String id;

    /**
     *原流水记录ID，关联操作，Ex：解冻和冻结相关联
     */
    private String originId;

    /**
     *交易发起时间，业务端
     */
    private String transDate;

    /**
     *交易类型，BangateServer
     */
    private String transType;

    /**
     *业务类型，业务端.业务类型必须明确给出.Ex：在BangateServer，转账、解冻、解冻支付、冻结、支付冻结都是强制转账的交易类>型
     */
    private String businessType;

    /**
     *业务流水号，业务端
     */
    private String businessSeq;

    /**
     *网关流水号，BangateServer
     */
    private String gateSeq;

    /**
     *银行流水号，银行返回，可能没有
     */
    private String bankSeq;

    /**
     *交易金额，分为单位
     */
    private Long amount;

    /**
     *银行对帐时间
     */
    private String bankCheckDate;

    /**
     *交易状态
     */
    private String status;

    /**
     *银行主账号用户名
     */
    private String bankUserName;

    /**
     *银行主账号
     */
    private String mainAccountNo;

    /**
     *业务发起帐号，发起该交易的帐号
     */
    private String accountNo;

    /**
     *第三方帐号，Ex：对应于发起帐号，Ex:转账时的收款帐号
     */
    private String receiveAccountNo;

    /**
     *流水创建时间
     */
    private Date createTime;

    /**
     *最后一次更新该记录的时间，同一条记录因为状态变更可能会多次更新
     */
    private Date updateTime;

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
     *原流水记录ID，关联操作，Ex：解冻和冻结相关联
     */
    public String getOriginId() {
        return originId;
    }

    /**
     *原流水记录ID，关联操作，Ex：解冻和冻结相关联
     */
    public void setOriginId(String originId) {
        this.originId = originId == null ? null : originId.trim();
    }

    /**
     *交易发起时间，业务端
     */
    public String getTransDate() {
        return transDate;
    }

    /**
     *交易发起时间，业务端
     */
    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    /**
     *交易类型，BangateServer
     */
    public String getTransType() {
        return transType;
    }

    /**
     *交易类型，BangateServer
     */
    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }

    /**
     *业务类型，业务端.业务类型必须明确给出.Ex：在BangateServer，转账、解冻、解冻支付、冻结、支付冻结都是强制转账的交易类>型
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     *业务类型，业务端.业务类型必须明确给出.Ex：在BangateServer，转账、解冻、解冻支付、冻结、支付冻结都是强制转账的交易类>型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     *业务流水号，业务端
     */
    public String getBusinessSeq() {
        return businessSeq;
    }

    /**
     *业务流水号，业务端
     */
    public void setBusinessSeq(String businessSeq) {
        this.businessSeq = businessSeq == null ? null : businessSeq.trim();
    }

    /**
     *网关流水号，BangateServer
     */
    public String getGateSeq() {
        return gateSeq;
    }

    /**
     *网关流水号，BangateServer
     */
    public void setGateSeq(String gateSeq) {
        this.gateSeq = gateSeq == null ? null : gateSeq.trim();
    }

    /**
     *银行流水号，银行返回，可能没有
     */
    public String getBankSeq() {
        return bankSeq;
    }

    /**
     *银行流水号，银行返回，可能没有
     */
    public void setBankSeq(String bankSeq) {
        this.bankSeq = bankSeq == null ? null : bankSeq.trim();
    }

    /**
     *交易金额，分为单位
     */
    public Long getAmount() {
        return amount;
    }

    /**
     *交易金额，分为单位
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     *银行对帐时间
     */
    public String getBankCheckDate() {
        return bankCheckDate;
    }

    /**
     *银行对帐时间
     */
    public void setBankCheckDate(String bankCheckDate) {
        this.bankCheckDate = bankCheckDate == null ? null : bankCheckDate.trim();
    }

    /**
     *交易状态
     */
    public String getStatus() {
        return status;
    }

    /**
     *交易状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *银行主账号用户名
     */
    public String getBankUserName() {
        return bankUserName;
    }

    /**
     *银行主账号用户名
     */
    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    /**
     *银行主账号
     */
    public String getMainAccountNo() {
        return mainAccountNo;
    }

    /**
     *银行主账号
     */
    public void setMainAccountNo(String mainAccountNo) {
        this.mainAccountNo = mainAccountNo == null ? null : mainAccountNo.trim();
    }

    /**
     *业务发起帐号，发起该交易的帐号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *业务发起帐号，发起该交易的帐号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *第三方帐号，Ex：对应于发起帐号，Ex:转账时的收款帐号
     */
    public String getReceiveAccountNo() {
        return receiveAccountNo;
    }

    /**
     *第三方帐号，Ex：对应于发起帐号，Ex:转账时的收款帐号
     */
    public void setReceiveAccountNo(String receiveAccountNo) {
        this.receiveAccountNo = receiveAccountNo == null ? null : receiveAccountNo.trim();
    }

    /**
     *流水创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *流水创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *最后一次更新该记录的时间，同一条记录因为状态变更可能会多次更新
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *最后一次更新该记录的时间，同一条记录因为状态变更可能会多次更新
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
        BankgateTrans other = (BankgateTrans) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOriginId() == null ? other.getOriginId() == null : this.getOriginId().equals(other.getOriginId()))
            && (this.getTransDate() == null ? other.getTransDate() == null : this.getTransDate().equals(other.getTransDate()))
            && (this.getTransType() == null ? other.getTransType() == null : this.getTransType().equals(other.getTransType()))
            && (this.getBusinessType() == null ? other.getBusinessType() == null : this.getBusinessType().equals(other.getBusinessType()))
            && (this.getBusinessSeq() == null ? other.getBusinessSeq() == null : this.getBusinessSeq().equals(other.getBusinessSeq()))
            && (this.getGateSeq() == null ? other.getGateSeq() == null : this.getGateSeq().equals(other.getGateSeq()))
            && (this.getBankSeq() == null ? other.getBankSeq() == null : this.getBankSeq().equals(other.getBankSeq()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBankCheckDate() == null ? other.getBankCheckDate() == null : this.getBankCheckDate().equals(other.getBankCheckDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBankUserName() == null ? other.getBankUserName() == null : this.getBankUserName().equals(other.getBankUserName()))
            && (this.getMainAccountNo() == null ? other.getMainAccountNo() == null : this.getMainAccountNo().equals(other.getMainAccountNo()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getReceiveAccountNo() == null ? other.getReceiveAccountNo() == null : this.getReceiveAccountNo().equals(other.getReceiveAccountNo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOriginId() == null) ? 0 : getOriginId().hashCode());
        result = prime * result + ((getTransDate() == null) ? 0 : getTransDate().hashCode());
        result = prime * result + ((getTransType() == null) ? 0 : getTransType().hashCode());
        result = prime * result + ((getBusinessType() == null) ? 0 : getBusinessType().hashCode());
        result = prime * result + ((getBusinessSeq() == null) ? 0 : getBusinessSeq().hashCode());
        result = prime * result + ((getGateSeq() == null) ? 0 : getGateSeq().hashCode());
        result = prime * result + ((getBankSeq() == null) ? 0 : getBankSeq().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBankCheckDate() == null) ? 0 : getBankCheckDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBankUserName() == null) ? 0 : getBankUserName().hashCode());
        result = prime * result + ((getMainAccountNo() == null) ? 0 : getMainAccountNo().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getReceiveAccountNo() == null) ? 0 : getReceiveAccountNo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}