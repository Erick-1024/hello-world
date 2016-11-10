/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ReportBankAccountTradeFlow implements Serializable {
    /**
     *银行账户交易流水表ID，该表保存从银行拉取的交易流水，辅助报表数据生成和对账
     */
    private Integer id;

    /**
     *CANA平台企业ID，冗余该字段，方便报表统计
     */
    private String customerId;

    /**
     *主账号
     */
    private String mainAccountNo;

    /**
     *对应主账号的银行登录用户名
     */
    private String bankUserName;

    /**
     *附属账号,非入金记录时需要保存附属账号
     */
    private String accountNo;

    /**
     *附属账号监管状态：AccountSupervisionStatus
     */
    private String supervisionStatus;

    /**
     *交易类型：入金、转账、提现（出金）、退款、调账、冻结、解冻，其中退款、调账为平台所有，对于银行业务都为转账
     */
    private String tradeType;

    /**
     *交易日期，format:20160101
     */
    private String tradeDate;

    /**
     *交易时间，format:102332
     */
    private String tradeTime;

    /**
     *交易状态，交易状态用作业务筛选条件
     */
    private String tradeState;

    /**
     *交易金额
     */
    private Long tradeAmount;

    /**
     *交易手续费，目前只有提现有手续费
     */
    private Long tradeFee;

    /**
     *交易对方账号
     */
    private String oppositeAccountNo;

    /**
     *交易对方账户名
     */
    private String oppositeAccountName;

    /**
     *现转标志，标志是现金交易还是转账
     */
    private String cashTransferfFlag;

    /**
     *账户余额，当附属账号为空时，若有值，表示主账号余额
     */
    private Long accountBalance;

    /**
     *流水插入表的时间，区别于交易时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     *银行账户交易流水表ID，该表保存从银行拉取的交易流水，辅助报表数据生成和对账
     */
    public Integer getId() {
        return id;
    }

    /**
     *银行账户交易流水表ID，该表保存从银行拉取的交易流水，辅助报表数据生成和对账
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *CANA平台企业ID，冗余该字段，方便报表统计
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *CANA平台企业ID，冗余该字段，方便报表统计
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *主账号
     */
    public String getMainAccountNo() {
        return mainAccountNo;
    }

    /**
     *主账号
     */
    public void setMainAccountNo(String mainAccountNo) {
        this.mainAccountNo = mainAccountNo == null ? null : mainAccountNo.trim();
    }

    /**
     *对应主账号的银行登录用户名
     */
    public String getBankUserName() {
        return bankUserName;
    }

    /**
     *对应主账号的银行登录用户名
     */
    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    /**
     *附属账号,非入金记录时需要保存附属账号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *附属账号,非入金记录时需要保存附属账号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *附属账号监管状态：AccountSupervisionStatus
     */
    public String getSupervisionStatus() {
        return supervisionStatus;
    }

    /**
     *附属账号监管状态：AccountSupervisionStatus
     */
    public void setSupervisionStatus(String supervisionStatus) {
        this.supervisionStatus = supervisionStatus == null ? null : supervisionStatus.trim();
    }

    /**
     *交易类型：入金、转账、提现（出金）、退款、调账、冻结、解冻，其中退款、调账为平台所有，对于银行业务都为转账
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     *交易类型：入金、转账、提现（出金）、退款、调账、冻结、解冻，其中退款、调账为平台所有，对于银行业务都为转账
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     *交易日期，format:20160101
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     *交易日期，format:20160101
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    /**
     *交易时间，format:102332
     */
    public String getTradeTime() {
        return tradeTime;
    }

    /**
     *交易时间，format:102332
     */
    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }

    /**
     *交易状态，交易状态用作业务筛选条件
     */
    public String getTradeState() {
        return tradeState;
    }

    /**
     *交易状态，交易状态用作业务筛选条件
     */
    public void setTradeState(String tradeState) {
        this.tradeState = tradeState == null ? null : tradeState.trim();
    }

    /**
     *交易金额
     */
    public Long getTradeAmount() {
        return tradeAmount;
    }

    /**
     *交易金额
     */
    public void setTradeAmount(Long tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    /**
     *交易手续费，目前只有提现有手续费
     */
    public Long getTradeFee() {
        return tradeFee;
    }

    /**
     *交易手续费，目前只有提现有手续费
     */
    public void setTradeFee(Long tradeFee) {
        this.tradeFee = tradeFee;
    }

    /**
     *交易对方账号
     */
    public String getOppositeAccountNo() {
        return oppositeAccountNo;
    }

    /**
     *交易对方账号
     */
    public void setOppositeAccountNo(String oppositeAccountNo) {
        this.oppositeAccountNo = oppositeAccountNo == null ? null : oppositeAccountNo.trim();
    }

    /**
     *交易对方账户名
     */
    public String getOppositeAccountName() {
        return oppositeAccountName;
    }

    /**
     *交易对方账户名
     */
    public void setOppositeAccountName(String oppositeAccountName) {
        this.oppositeAccountName = oppositeAccountName == null ? null : oppositeAccountName.trim();
    }

    /**
     *现转标志，标志是现金交易还是转账
     */
    public String getCashTransferfFlag() {
        return cashTransferfFlag;
    }

    /**
     *现转标志，标志是现金交易还是转账
     */
    public void setCashTransferfFlag(String cashTransferfFlag) {
        this.cashTransferfFlag = cashTransferfFlag == null ? null : cashTransferfFlag.trim();
    }

    /**
     *账户余额，当附属账号为空时，若有值，表示主账号余额
     */
    public Long getAccountBalance() {
        return accountBalance;
    }

    /**
     *账户余额，当附属账号为空时，若有值，表示主账号余额
     */
    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     *流水插入表的时间，区别于交易时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *流水插入表的时间，区别于交易时间
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
        ReportBankAccountTradeFlow other = (ReportBankAccountTradeFlow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getMainAccountNo() == null ? other.getMainAccountNo() == null : this.getMainAccountNo().equals(other.getMainAccountNo()))
            && (this.getBankUserName() == null ? other.getBankUserName() == null : this.getBankUserName().equals(other.getBankUserName()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getSupervisionStatus() == null ? other.getSupervisionStatus() == null : this.getSupervisionStatus().equals(other.getSupervisionStatus()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getTradeDate() == null ? other.getTradeDate() == null : this.getTradeDate().equals(other.getTradeDate()))
            && (this.getTradeTime() == null ? other.getTradeTime() == null : this.getTradeTime().equals(other.getTradeTime()))
            && (this.getTradeState() == null ? other.getTradeState() == null : this.getTradeState().equals(other.getTradeState()))
            && (this.getTradeAmount() == null ? other.getTradeAmount() == null : this.getTradeAmount().equals(other.getTradeAmount()))
            && (this.getTradeFee() == null ? other.getTradeFee() == null : this.getTradeFee().equals(other.getTradeFee()))
            && (this.getOppositeAccountNo() == null ? other.getOppositeAccountNo() == null : this.getOppositeAccountNo().equals(other.getOppositeAccountNo()))
            && (this.getOppositeAccountName() == null ? other.getOppositeAccountName() == null : this.getOppositeAccountName().equals(other.getOppositeAccountName()))
            && (this.getCashTransferfFlag() == null ? other.getCashTransferfFlag() == null : this.getCashTransferfFlag().equals(other.getCashTransferfFlag()))
            && (this.getAccountBalance() == null ? other.getAccountBalance() == null : this.getAccountBalance().equals(other.getAccountBalance()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getMainAccountNo() == null) ? 0 : getMainAccountNo().hashCode());
        result = prime * result + ((getBankUserName() == null) ? 0 : getBankUserName().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getSupervisionStatus() == null) ? 0 : getSupervisionStatus().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getTradeDate() == null) ? 0 : getTradeDate().hashCode());
        result = prime * result + ((getTradeTime() == null) ? 0 : getTradeTime().hashCode());
        result = prime * result + ((getTradeState() == null) ? 0 : getTradeState().hashCode());
        result = prime * result + ((getTradeAmount() == null) ? 0 : getTradeAmount().hashCode());
        result = prime * result + ((getTradeFee() == null) ? 0 : getTradeFee().hashCode());
        result = prime * result + ((getOppositeAccountNo() == null) ? 0 : getOppositeAccountNo().hashCode());
        result = prime * result + ((getOppositeAccountName() == null) ? 0 : getOppositeAccountName().hashCode());
        result = prime * result + ((getCashTransferfFlag() == null) ? 0 : getCashTransferfFlag().hashCode());
        result = prime * result + ((getAccountBalance() == null) ? 0 : getAccountBalance().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}