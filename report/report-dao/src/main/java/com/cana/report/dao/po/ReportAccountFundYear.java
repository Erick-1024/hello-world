/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ReportAccountFundYear implements Serializable {
    /**
     *资金年报表ID，以企业为统计单位
     */
    private Integer id;

    /**
     *客户ID，在CANA的公司ID
     */
    private String customerId;

    /**
     *年报表日期(格式：yyyy，存在当年报表，年报表是由日报表统计生成)
     */
    private String reportDate;

    /**
     *上一年账户余额为上年最后一日的账户余额
     */
    private Long lastBalance;

    /**
     *上一年自己的监管状态账户的余额，为上年最后一日自己的监管状态账户余额
     */
    private Long lastOwnSupervisionBalance;

    /**
     *上一年监管的别人账户的余额，为上年最后一日监管的别人账户的余额
     */
    private Long lastOtherSupervisionBalance;

    /**
     *本年余额为本年，最后一日或当前日的账户余额
     */
    private Long currentBalance;

    /**
     *本年自己的监管状态账户的余额，为本年最后一日自己的监管状态账户余额
     */
    private Long currentOwnSupervisionBalance;

    /**
     *本年监管的别人账户的余额，为本年最后一日监管的别人账户的余额
     */
    private Long currentOtherSupervisionBalance;

    /**
     *年入金额为当年日报表入金额累加值
     */
    private Long depositFund;

    /**
     *年转帐额为当年日报表转帐额累加值,负值转出，正值转入
     */
    private Long transferFund;

    /**
     *年提现额为当年日报表提现额累加值
     */
    private Long withdrawFund;

    /**
     *提现手续费为当年日报表提现手续费累加值
     */
    private Long withdrawFee;

    /**
     *当年冻结金额，当年所有冻结金额累加值
     */
    private Long freezeFund;

    /**
     *当年解冻金额，当年所有解冻金额累加值
     */
    private Long unfreezeFund;

    /**
     *年退款额为当年日报表退款额累加值，退款行为由平台交易记录判定
     */
    private Long refundFund;

    /**
     *自己的监管状态账户的入金金额，当年日报累加值
     */
    private Long supervisionDepositFund;

    /**
     *自己的监管状态账户的转帐金额，负值转出，正值转入，当年日报累加值
     */
    private Long supervisionTransferFund;

    /**
     *自己的监管状态账户的提现金额，当年日报累加值
     */
    private Long supervisionWithdrawFund;

    /**
     *自己的监管状态账户的提现手续费，当年日报累加值
     */
    private Long supervisionWithdrawFee;

    /**
     *自己的监管状态账户的冻结金额，当年日报累加值
     */
    private Long supervisionFreezeFund;

    /**
     *自己的监管状态账户的解冻金额，当年日报累加值
     */
    private Long supervisionUnfreezeFund;

    /**
     *自己的监管状态账户的退款金额，当年日报累加值
     */
    private Long supervisionRefundFund;

    /**
     *报表状态，标志资金报表中资金、交易、余额字段值的有效性，以便对失败的字段值进行重新拉取：FundReportState枚举
     */
    private Integer fundReportState;

    /**
     *报表创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     *资金年报表ID，以企业为统计单位
     */
    public Integer getId() {
        return id;
    }

    /**
     *资金年报表ID，以企业为统计单位
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *客户ID，在CANA的公司ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *客户ID，在CANA的公司ID
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *年报表日期(格式：yyyy，存在当年报表，年报表是由日报表统计生成)
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     *年报表日期(格式：yyyy，存在当年报表，年报表是由日报表统计生成)
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate == null ? null : reportDate.trim();
    }

    /**
     *上一年账户余额为上年最后一日的账户余额
     */
    public Long getLastBalance() {
        return lastBalance;
    }

    /**
     *上一年账户余额为上年最后一日的账户余额
     */
    public void setLastBalance(Long lastBalance) {
        this.lastBalance = lastBalance;
    }

    /**
     *上一年自己的监管状态账户的余额，为上年最后一日自己的监管状态账户余额
     */
    public Long getLastOwnSupervisionBalance() {
        return lastOwnSupervisionBalance;
    }

    /**
     *上一年自己的监管状态账户的余额，为上年最后一日自己的监管状态账户余额
     */
    public void setLastOwnSupervisionBalance(Long lastOwnSupervisionBalance) {
        this.lastOwnSupervisionBalance = lastOwnSupervisionBalance;
    }

    /**
     *上一年监管的别人账户的余额，为上年最后一日监管的别人账户的余额
     */
    public Long getLastOtherSupervisionBalance() {
        return lastOtherSupervisionBalance;
    }

    /**
     *上一年监管的别人账户的余额，为上年最后一日监管的别人账户的余额
     */
    public void setLastOtherSupervisionBalance(Long lastOtherSupervisionBalance) {
        this.lastOtherSupervisionBalance = lastOtherSupervisionBalance;
    }

    /**
     *本年余额为本年，最后一日或当前日的账户余额
     */
    public Long getCurrentBalance() {
        return currentBalance;
    }

    /**
     *本年余额为本年，最后一日或当前日的账户余额
     */
    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     *本年自己的监管状态账户的余额，为本年最后一日自己的监管状态账户余额
     */
    public Long getCurrentOwnSupervisionBalance() {
        return currentOwnSupervisionBalance;
    }

    /**
     *本年自己的监管状态账户的余额，为本年最后一日自己的监管状态账户余额
     */
    public void setCurrentOwnSupervisionBalance(Long currentOwnSupervisionBalance) {
        this.currentOwnSupervisionBalance = currentOwnSupervisionBalance;
    }

    /**
     *本年监管的别人账户的余额，为本年最后一日监管的别人账户的余额
     */
    public Long getCurrentOtherSupervisionBalance() {
        return currentOtherSupervisionBalance;
    }

    /**
     *本年监管的别人账户的余额，为本年最后一日监管的别人账户的余额
     */
    public void setCurrentOtherSupervisionBalance(Long currentOtherSupervisionBalance) {
        this.currentOtherSupervisionBalance = currentOtherSupervisionBalance;
    }

    /**
     *年入金额为当年日报表入金额累加值
     */
    public Long getDepositFund() {
        return depositFund;
    }

    /**
     *年入金额为当年日报表入金额累加值
     */
    public void setDepositFund(Long depositFund) {
        this.depositFund = depositFund;
    }

    /**
     *年转帐额为当年日报表转帐额累加值,负值转出，正值转入
     */
    public Long getTransferFund() {
        return transferFund;
    }

    /**
     *年转帐额为当年日报表转帐额累加值,负值转出，正值转入
     */
    public void setTransferFund(Long transferFund) {
        this.transferFund = transferFund;
    }

    /**
     *年提现额为当年日报表提现额累加值
     */
    public Long getWithdrawFund() {
        return withdrawFund;
    }

    /**
     *年提现额为当年日报表提现额累加值
     */
    public void setWithdrawFund(Long withdrawFund) {
        this.withdrawFund = withdrawFund;
    }

    /**
     *提现手续费为当年日报表提现手续费累加值
     */
    public Long getWithdrawFee() {
        return withdrawFee;
    }

    /**
     *提现手续费为当年日报表提现手续费累加值
     */
    public void setWithdrawFee(Long withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    /**
     *当年冻结金额，当年所有冻结金额累加值
     */
    public Long getFreezeFund() {
        return freezeFund;
    }

    /**
     *当年冻结金额，当年所有冻结金额累加值
     */
    public void setFreezeFund(Long freezeFund) {
        this.freezeFund = freezeFund;
    }

    /**
     *当年解冻金额，当年所有解冻金额累加值
     */
    public Long getUnfreezeFund() {
        return unfreezeFund;
    }

    /**
     *当年解冻金额，当年所有解冻金额累加值
     */
    public void setUnfreezeFund(Long unfreezeFund) {
        this.unfreezeFund = unfreezeFund;
    }

    /**
     *年退款额为当年日报表退款额累加值，退款行为由平台交易记录判定
     */
    public Long getRefundFund() {
        return refundFund;
    }

    /**
     *年退款额为当年日报表退款额累加值，退款行为由平台交易记录判定
     */
    public void setRefundFund(Long refundFund) {
        this.refundFund = refundFund;
    }

    /**
     *自己的监管状态账户的入金金额，当年日报累加值
     */
    public Long getSupervisionDepositFund() {
        return supervisionDepositFund;
    }

    /**
     *自己的监管状态账户的入金金额，当年日报累加值
     */
    public void setSupervisionDepositFund(Long supervisionDepositFund) {
        this.supervisionDepositFund = supervisionDepositFund;
    }

    /**
     *自己的监管状态账户的转帐金额，负值转出，正值转入，当年日报累加值
     */
    public Long getSupervisionTransferFund() {
        return supervisionTransferFund;
    }

    /**
     *自己的监管状态账户的转帐金额，负值转出，正值转入，当年日报累加值
     */
    public void setSupervisionTransferFund(Long supervisionTransferFund) {
        this.supervisionTransferFund = supervisionTransferFund;
    }

    /**
     *自己的监管状态账户的提现金额，当年日报累加值
     */
    public Long getSupervisionWithdrawFund() {
        return supervisionWithdrawFund;
    }

    /**
     *自己的监管状态账户的提现金额，当年日报累加值
     */
    public void setSupervisionWithdrawFund(Long supervisionWithdrawFund) {
        this.supervisionWithdrawFund = supervisionWithdrawFund;
    }

    /**
     *自己的监管状态账户的提现手续费，当年日报累加值
     */
    public Long getSupervisionWithdrawFee() {
        return supervisionWithdrawFee;
    }

    /**
     *自己的监管状态账户的提现手续费，当年日报累加值
     */
    public void setSupervisionWithdrawFee(Long supervisionWithdrawFee) {
        this.supervisionWithdrawFee = supervisionWithdrawFee;
    }

    /**
     *自己的监管状态账户的冻结金额，当年日报累加值
     */
    public Long getSupervisionFreezeFund() {
        return supervisionFreezeFund;
    }

    /**
     *自己的监管状态账户的冻结金额，当年日报累加值
     */
    public void setSupervisionFreezeFund(Long supervisionFreezeFund) {
        this.supervisionFreezeFund = supervisionFreezeFund;
    }

    /**
     *自己的监管状态账户的解冻金额，当年日报累加值
     */
    public Long getSupervisionUnfreezeFund() {
        return supervisionUnfreezeFund;
    }

    /**
     *自己的监管状态账户的解冻金额，当年日报累加值
     */
    public void setSupervisionUnfreezeFund(Long supervisionUnfreezeFund) {
        this.supervisionUnfreezeFund = supervisionUnfreezeFund;
    }

    /**
     *自己的监管状态账户的退款金额，当年日报累加值
     */
    public Long getSupervisionRefundFund() {
        return supervisionRefundFund;
    }

    /**
     *自己的监管状态账户的退款金额，当年日报累加值
     */
    public void setSupervisionRefundFund(Long supervisionRefundFund) {
        this.supervisionRefundFund = supervisionRefundFund;
    }

    /**
     *报表状态，标志资金报表中资金、交易、余额字段值的有效性，以便对失败的字段值进行重新拉取：FundReportState枚举
     */
    public Integer getFundReportState() {
        return fundReportState;
    }

    /**
     *报表状态，标志资金报表中资金、交易、余额字段值的有效性，以便对失败的字段值进行重新拉取：FundReportState枚举
     */
    public void setFundReportState(Integer fundReportState) {
        this.fundReportState = fundReportState;
    }

    /**
     *报表创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *报表创建时间
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
        ReportAccountFundYear other = (ReportAccountFundYear) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getReportDate() == null ? other.getReportDate() == null : this.getReportDate().equals(other.getReportDate()))
            && (this.getLastBalance() == null ? other.getLastBalance() == null : this.getLastBalance().equals(other.getLastBalance()))
            && (this.getLastOwnSupervisionBalance() == null ? other.getLastOwnSupervisionBalance() == null : this.getLastOwnSupervisionBalance().equals(other.getLastOwnSupervisionBalance()))
            && (this.getLastOtherSupervisionBalance() == null ? other.getLastOtherSupervisionBalance() == null : this.getLastOtherSupervisionBalance().equals(other.getLastOtherSupervisionBalance()))
            && (this.getCurrentBalance() == null ? other.getCurrentBalance() == null : this.getCurrentBalance().equals(other.getCurrentBalance()))
            && (this.getCurrentOwnSupervisionBalance() == null ? other.getCurrentOwnSupervisionBalance() == null : this.getCurrentOwnSupervisionBalance().equals(other.getCurrentOwnSupervisionBalance()))
            && (this.getCurrentOtherSupervisionBalance() == null ? other.getCurrentOtherSupervisionBalance() == null : this.getCurrentOtherSupervisionBalance().equals(other.getCurrentOtherSupervisionBalance()))
            && (this.getDepositFund() == null ? other.getDepositFund() == null : this.getDepositFund().equals(other.getDepositFund()))
            && (this.getTransferFund() == null ? other.getTransferFund() == null : this.getTransferFund().equals(other.getTransferFund()))
            && (this.getWithdrawFund() == null ? other.getWithdrawFund() == null : this.getWithdrawFund().equals(other.getWithdrawFund()))
            && (this.getWithdrawFee() == null ? other.getWithdrawFee() == null : this.getWithdrawFee().equals(other.getWithdrawFee()))
            && (this.getFreezeFund() == null ? other.getFreezeFund() == null : this.getFreezeFund().equals(other.getFreezeFund()))
            && (this.getUnfreezeFund() == null ? other.getUnfreezeFund() == null : this.getUnfreezeFund().equals(other.getUnfreezeFund()))
            && (this.getRefundFund() == null ? other.getRefundFund() == null : this.getRefundFund().equals(other.getRefundFund()))
            && (this.getSupervisionDepositFund() == null ? other.getSupervisionDepositFund() == null : this.getSupervisionDepositFund().equals(other.getSupervisionDepositFund()))
            && (this.getSupervisionTransferFund() == null ? other.getSupervisionTransferFund() == null : this.getSupervisionTransferFund().equals(other.getSupervisionTransferFund()))
            && (this.getSupervisionWithdrawFund() == null ? other.getSupervisionWithdrawFund() == null : this.getSupervisionWithdrawFund().equals(other.getSupervisionWithdrawFund()))
            && (this.getSupervisionWithdrawFee() == null ? other.getSupervisionWithdrawFee() == null : this.getSupervisionWithdrawFee().equals(other.getSupervisionWithdrawFee()))
            && (this.getSupervisionFreezeFund() == null ? other.getSupervisionFreezeFund() == null : this.getSupervisionFreezeFund().equals(other.getSupervisionFreezeFund()))
            && (this.getSupervisionUnfreezeFund() == null ? other.getSupervisionUnfreezeFund() == null : this.getSupervisionUnfreezeFund().equals(other.getSupervisionUnfreezeFund()))
            && (this.getSupervisionRefundFund() == null ? other.getSupervisionRefundFund() == null : this.getSupervisionRefundFund().equals(other.getSupervisionRefundFund()))
            && (this.getFundReportState() == null ? other.getFundReportState() == null : this.getFundReportState().equals(other.getFundReportState()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getReportDate() == null) ? 0 : getReportDate().hashCode());
        result = prime * result + ((getLastBalance() == null) ? 0 : getLastBalance().hashCode());
        result = prime * result + ((getLastOwnSupervisionBalance() == null) ? 0 : getLastOwnSupervisionBalance().hashCode());
        result = prime * result + ((getLastOtherSupervisionBalance() == null) ? 0 : getLastOtherSupervisionBalance().hashCode());
        result = prime * result + ((getCurrentBalance() == null) ? 0 : getCurrentBalance().hashCode());
        result = prime * result + ((getCurrentOwnSupervisionBalance() == null) ? 0 : getCurrentOwnSupervisionBalance().hashCode());
        result = prime * result + ((getCurrentOtherSupervisionBalance() == null) ? 0 : getCurrentOtherSupervisionBalance().hashCode());
        result = prime * result + ((getDepositFund() == null) ? 0 : getDepositFund().hashCode());
        result = prime * result + ((getTransferFund() == null) ? 0 : getTransferFund().hashCode());
        result = prime * result + ((getWithdrawFund() == null) ? 0 : getWithdrawFund().hashCode());
        result = prime * result + ((getWithdrawFee() == null) ? 0 : getWithdrawFee().hashCode());
        result = prime * result + ((getFreezeFund() == null) ? 0 : getFreezeFund().hashCode());
        result = prime * result + ((getUnfreezeFund() == null) ? 0 : getUnfreezeFund().hashCode());
        result = prime * result + ((getRefundFund() == null) ? 0 : getRefundFund().hashCode());
        result = prime * result + ((getSupervisionDepositFund() == null) ? 0 : getSupervisionDepositFund().hashCode());
        result = prime * result + ((getSupervisionTransferFund() == null) ? 0 : getSupervisionTransferFund().hashCode());
        result = prime * result + ((getSupervisionWithdrawFund() == null) ? 0 : getSupervisionWithdrawFund().hashCode());
        result = prime * result + ((getSupervisionWithdrawFee() == null) ? 0 : getSupervisionWithdrawFee().hashCode());
        result = prime * result + ((getSupervisionFreezeFund() == null) ? 0 : getSupervisionFreezeFund().hashCode());
        result = prime * result + ((getSupervisionUnfreezeFund() == null) ? 0 : getSupervisionUnfreezeFund().hashCode());
        result = prime * result + ((getSupervisionRefundFund() == null) ? 0 : getSupervisionRefundFund().hashCode());
        result = prime * result + ((getFundReportState() == null) ? 0 : getFundReportState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}