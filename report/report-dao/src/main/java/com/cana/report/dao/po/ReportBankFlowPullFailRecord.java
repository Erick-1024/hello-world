/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ReportBankFlowPullFailRecord implements Serializable {
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
     *交易类型：入金、转账、提现（出金）、退款、调账、冻结、解冻，其中退款、调账为平台所有，对于银行业务都为转账
     */
    private String tradeType;

    /**
     *报表日期，该条失败记录所属报表日期
     */
    private Date reportDate;

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
     *报表日期，该条失败记录所属报表日期
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     *报表日期，该条失败记录所属报表日期
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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
        ReportBankFlowPullFailRecord other = (ReportBankFlowPullFailRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getMainAccountNo() == null ? other.getMainAccountNo() == null : this.getMainAccountNo().equals(other.getMainAccountNo()))
            && (this.getBankUserName() == null ? other.getBankUserName() == null : this.getBankUserName().equals(other.getBankUserName()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getReportDate() == null ? other.getReportDate() == null : this.getReportDate().equals(other.getReportDate()))
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
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getReportDate() == null) ? 0 : getReportDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}