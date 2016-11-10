/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ReportAccountFundCount implements Serializable {
    /**
     *资金统计日报表ID，以企业为统计单位
     */
    private Integer id;

    /**
     *客户ID，在CANA的公司ID
     */
    private String customerId;

    /**
     *日报表日期（格式：yyyy-MM-dd，不存在当天报表，当天报表在0点查询统计生成）
     */
    private String reportDate;

    /**
     *入金笔数
     */
    private Long depositCount;

    /**
     *转入笔数
     */
    private Long transferInCount;

    /**
     *转出笔数
     */
    private Long transferOutCount;

    /**
     *提现笔数
     */
    private Long withdrawCount;

    /**
     *冻结笔数
     */
    private Long freezeCount;

    /**
     *解冻笔数
     */
    private Long unfreezeCount;

    /**
     *退款笔数，退款行为由平台交易记录判定
     */
    private Long refundCount;

    /**
     *自己的监管状态账户的入金笔数
     */
    private Long supervisionDepositCount;

    /**
     *自己的监管状态账户的转入笔数
     */
    private Long supervisionTransferInCount;

    /**
     *自己的监管状态账户的转出笔数
     */
    private Long supervisionTransferOutCount;

    /**
     *自己的监管状态账户的提现笔数
     */
    private Long supervisionWithdrawCount;

    /**
     *自己的监管状态账户的冻结笔数
     */
    private Long supervisionFreezeCount;

    /**
     *自己的监管状态账户的解冻笔数
     */
    private Long supervisionUnfreezeCount;

    /**
     *自己的监管状态账户的退款笔数，退款行为由平台交易记录判定
     */
    private Long supervisionRefundCount;

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
     *资金统计日报表ID，以企业为统计单位
     */
    public Integer getId() {
        return id;
    }

    /**
     *资金统计日报表ID，以企业为统计单位
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
     *日报表日期（格式：yyyy-MM-dd，不存在当天报表，当天报表在0点查询统计生成）
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     *日报表日期（格式：yyyy-MM-dd，不存在当天报表，当天报表在0点查询统计生成）
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate == null ? null : reportDate.trim();
    }

    /**
     *入金笔数
     */
    public Long getDepositCount() {
        return depositCount;
    }

    /**
     *入金笔数
     */
    public void setDepositCount(Long depositCount) {
        this.depositCount = depositCount;
    }

    /**
     *转入笔数
     */
    public Long getTransferInCount() {
        return transferInCount;
    }

    /**
     *转入笔数
     */
    public void setTransferInCount(Long transferInCount) {
        this.transferInCount = transferInCount;
    }

    /**
     *转出笔数
     */
    public Long getTransferOutCount() {
        return transferOutCount;
    }

    /**
     *转出笔数
     */
    public void setTransferOutCount(Long transferOutCount) {
        this.transferOutCount = transferOutCount;
    }

    /**
     *提现笔数
     */
    public Long getWithdrawCount() {
        return withdrawCount;
    }

    /**
     *提现笔数
     */
    public void setWithdrawCount(Long withdrawCount) {
        this.withdrawCount = withdrawCount;
    }

    /**
     *冻结笔数
     */
    public Long getFreezeCount() {
        return freezeCount;
    }

    /**
     *冻结笔数
     */
    public void setFreezeCount(Long freezeCount) {
        this.freezeCount = freezeCount;
    }

    /**
     *解冻笔数
     */
    public Long getUnfreezeCount() {
        return unfreezeCount;
    }

    /**
     *解冻笔数
     */
    public void setUnfreezeCount(Long unfreezeCount) {
        this.unfreezeCount = unfreezeCount;
    }

    /**
     *退款笔数，退款行为由平台交易记录判定
     */
    public Long getRefundCount() {
        return refundCount;
    }

    /**
     *退款笔数，退款行为由平台交易记录判定
     */
    public void setRefundCount(Long refundCount) {
        this.refundCount = refundCount;
    }

    /**
     *自己的监管状态账户的入金笔数
     */
    public Long getSupervisionDepositCount() {
        return supervisionDepositCount;
    }

    /**
     *自己的监管状态账户的入金笔数
     */
    public void setSupervisionDepositCount(Long supervisionDepositCount) {
        this.supervisionDepositCount = supervisionDepositCount;
    }

    /**
     *自己的监管状态账户的转入笔数
     */
    public Long getSupervisionTransferInCount() {
        return supervisionTransferInCount;
    }

    /**
     *自己的监管状态账户的转入笔数
     */
    public void setSupervisionTransferInCount(Long supervisionTransferInCount) {
        this.supervisionTransferInCount = supervisionTransferInCount;
    }

    /**
     *自己的监管状态账户的转出笔数
     */
    public Long getSupervisionTransferOutCount() {
        return supervisionTransferOutCount;
    }

    /**
     *自己的监管状态账户的转出笔数
     */
    public void setSupervisionTransferOutCount(Long supervisionTransferOutCount) {
        this.supervisionTransferOutCount = supervisionTransferOutCount;
    }

    /**
     *自己的监管状态账户的提现笔数
     */
    public Long getSupervisionWithdrawCount() {
        return supervisionWithdrawCount;
    }

    /**
     *自己的监管状态账户的提现笔数
     */
    public void setSupervisionWithdrawCount(Long supervisionWithdrawCount) {
        this.supervisionWithdrawCount = supervisionWithdrawCount;
    }

    /**
     *自己的监管状态账户的冻结笔数
     */
    public Long getSupervisionFreezeCount() {
        return supervisionFreezeCount;
    }

    /**
     *自己的监管状态账户的冻结笔数
     */
    public void setSupervisionFreezeCount(Long supervisionFreezeCount) {
        this.supervisionFreezeCount = supervisionFreezeCount;
    }

    /**
     *自己的监管状态账户的解冻笔数
     */
    public Long getSupervisionUnfreezeCount() {
        return supervisionUnfreezeCount;
    }

    /**
     *自己的监管状态账户的解冻笔数
     */
    public void setSupervisionUnfreezeCount(Long supervisionUnfreezeCount) {
        this.supervisionUnfreezeCount = supervisionUnfreezeCount;
    }

    /**
     *自己的监管状态账户的退款笔数，退款行为由平台交易记录判定
     */
    public Long getSupervisionRefundCount() {
        return supervisionRefundCount;
    }

    /**
     *自己的监管状态账户的退款笔数，退款行为由平台交易记录判定
     */
    public void setSupervisionRefundCount(Long supervisionRefundCount) {
        this.supervisionRefundCount = supervisionRefundCount;
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
        ReportAccountFundCount other = (ReportAccountFundCount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getReportDate() == null ? other.getReportDate() == null : this.getReportDate().equals(other.getReportDate()))
            && (this.getDepositCount() == null ? other.getDepositCount() == null : this.getDepositCount().equals(other.getDepositCount()))
            && (this.getTransferInCount() == null ? other.getTransferInCount() == null : this.getTransferInCount().equals(other.getTransferInCount()))
            && (this.getTransferOutCount() == null ? other.getTransferOutCount() == null : this.getTransferOutCount().equals(other.getTransferOutCount()))
            && (this.getWithdrawCount() == null ? other.getWithdrawCount() == null : this.getWithdrawCount().equals(other.getWithdrawCount()))
            && (this.getFreezeCount() == null ? other.getFreezeCount() == null : this.getFreezeCount().equals(other.getFreezeCount()))
            && (this.getUnfreezeCount() == null ? other.getUnfreezeCount() == null : this.getUnfreezeCount().equals(other.getUnfreezeCount()))
            && (this.getRefundCount() == null ? other.getRefundCount() == null : this.getRefundCount().equals(other.getRefundCount()))
            && (this.getSupervisionDepositCount() == null ? other.getSupervisionDepositCount() == null : this.getSupervisionDepositCount().equals(other.getSupervisionDepositCount()))
            && (this.getSupervisionTransferInCount() == null ? other.getSupervisionTransferInCount() == null : this.getSupervisionTransferInCount().equals(other.getSupervisionTransferInCount()))
            && (this.getSupervisionTransferOutCount() == null ? other.getSupervisionTransferOutCount() == null : this.getSupervisionTransferOutCount().equals(other.getSupervisionTransferOutCount()))
            && (this.getSupervisionWithdrawCount() == null ? other.getSupervisionWithdrawCount() == null : this.getSupervisionWithdrawCount().equals(other.getSupervisionWithdrawCount()))
            && (this.getSupervisionFreezeCount() == null ? other.getSupervisionFreezeCount() == null : this.getSupervisionFreezeCount().equals(other.getSupervisionFreezeCount()))
            && (this.getSupervisionUnfreezeCount() == null ? other.getSupervisionUnfreezeCount() == null : this.getSupervisionUnfreezeCount().equals(other.getSupervisionUnfreezeCount()))
            && (this.getSupervisionRefundCount() == null ? other.getSupervisionRefundCount() == null : this.getSupervisionRefundCount().equals(other.getSupervisionRefundCount()))
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
        result = prime * result + ((getDepositCount() == null) ? 0 : getDepositCount().hashCode());
        result = prime * result + ((getTransferInCount() == null) ? 0 : getTransferInCount().hashCode());
        result = prime * result + ((getTransferOutCount() == null) ? 0 : getTransferOutCount().hashCode());
        result = prime * result + ((getWithdrawCount() == null) ? 0 : getWithdrawCount().hashCode());
        result = prime * result + ((getFreezeCount() == null) ? 0 : getFreezeCount().hashCode());
        result = prime * result + ((getUnfreezeCount() == null) ? 0 : getUnfreezeCount().hashCode());
        result = prime * result + ((getRefundCount() == null) ? 0 : getRefundCount().hashCode());
        result = prime * result + ((getSupervisionDepositCount() == null) ? 0 : getSupervisionDepositCount().hashCode());
        result = prime * result + ((getSupervisionTransferInCount() == null) ? 0 : getSupervisionTransferInCount().hashCode());
        result = prime * result + ((getSupervisionTransferOutCount() == null) ? 0 : getSupervisionTransferOutCount().hashCode());
        result = prime * result + ((getSupervisionWithdrawCount() == null) ? 0 : getSupervisionWithdrawCount().hashCode());
        result = prime * result + ((getSupervisionFreezeCount() == null) ? 0 : getSupervisionFreezeCount().hashCode());
        result = prime * result + ((getSupervisionUnfreezeCount() == null) ? 0 : getSupervisionUnfreezeCount().hashCode());
        result = prime * result + ((getSupervisionRefundCount() == null) ? 0 : getSupervisionRefundCount().hashCode());
        result = prime * result + ((getFundReportState() == null) ? 0 : getFundReportState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}