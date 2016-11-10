/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ReportFundMonthly implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *余额月份
     */
    private String month;

    /**
     *保理商Id
     */
    private String factorId;

    /**
     *保理商名称
     */
    private String factorName;

    /**
     *融资客户名称
     */
    private String financeName;

    /**
     *账户名称
     */
    private String accountName;

    /**
     *银行账号
     */
    private String accountNo;

    /**
     *账户类型
     */
    private String accountType;

    /**
     *监管类型
     */
    private String supervisionStatus;

    /**
     *回款账户
     */
    private Boolean isTransferInAccount;

    /**
     *账户状态
     */
    private String accountStatus;

    /**
     *当月账户余额
     */
    private Long balance;

    /**
     *获取结果
     */
    private String result;

    /**
     *是否是主账户
     */
    private Boolean isMainAccount;

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
     *余额月份
     */
    public String getMonth() {
        return month;
    }

    /**
     *余额月份
     */
    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    /**
     *保理商Id
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *保理商Id
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *保理商名称
     */
    public String getFactorName() {
        return factorName;
    }

    /**
     *保理商名称
     */
    public void setFactorName(String factorName) {
        this.factorName = factorName == null ? null : factorName.trim();
    }

    /**
     *融资客户名称
     */
    public String getFinanceName() {
        return financeName;
    }

    /**
     *融资客户名称
     */
    public void setFinanceName(String financeName) {
        this.financeName = financeName == null ? null : financeName.trim();
    }

    /**
     *账户名称
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     *账户名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     *银行账号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *银行账号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *账户类型
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     *账户类型
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    /**
     *监管类型
     */
    public String getSupervisionStatus() {
        return supervisionStatus;
    }

    /**
     *监管类型
     */
    public void setSupervisionStatus(String supervisionStatus) {
        this.supervisionStatus = supervisionStatus == null ? null : supervisionStatus.trim();
    }

    /**
     *回款账户
     */
    public Boolean getIsTransferInAccount() {
        return isTransferInAccount;
    }

    /**
     *回款账户
     */
    public void setIsTransferInAccount(Boolean isTransferInAccount) {
        this.isTransferInAccount = isTransferInAccount;
    }

    /**
     *账户状态
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     *账户状态
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    /**
     *当月账户余额
     */
    public Long getBalance() {
        return balance;
    }

    /**
     *当月账户余额
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }

    /**
     *获取结果
     */
    public String getResult() {
        return result;
    }

    /**
     *获取结果
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    /**
     *是否是主账户
     */
    public Boolean getIsMainAccount() {
        return isMainAccount;
    }

    /**
     *是否是主账户
     */
    public void setIsMainAccount(Boolean isMainAccount) {
        this.isMainAccount = isMainAccount;
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
        ReportFundMonthly other = (ReportFundMonthly) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getFactorName() == null ? other.getFactorName() == null : this.getFactorName().equals(other.getFactorName()))
            && (this.getFinanceName() == null ? other.getFinanceName() == null : this.getFinanceName().equals(other.getFinanceName()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getAccountType() == null ? other.getAccountType() == null : this.getAccountType().equals(other.getAccountType()))
            && (this.getSupervisionStatus() == null ? other.getSupervisionStatus() == null : this.getSupervisionStatus().equals(other.getSupervisionStatus()))
            && (this.getIsTransferInAccount() == null ? other.getIsTransferInAccount() == null : this.getIsTransferInAccount().equals(other.getIsTransferInAccount()))
            && (this.getAccountStatus() == null ? other.getAccountStatus() == null : this.getAccountStatus().equals(other.getAccountStatus()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getIsMainAccount() == null ? other.getIsMainAccount() == null : this.getIsMainAccount().equals(other.getIsMainAccount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getFactorName() == null) ? 0 : getFactorName().hashCode());
        result = prime * result + ((getFinanceName() == null) ? 0 : getFinanceName().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getAccountType() == null) ? 0 : getAccountType().hashCode());
        result = prime * result + ((getSupervisionStatus() == null) ? 0 : getSupervisionStatus().hashCode());
        result = prime * result + ((getIsTransferInAccount() == null) ? 0 : getIsTransferInAccount().hashCode());
        result = prime * result + ((getAccountStatus() == null) ? 0 : getAccountStatus().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getIsMainAccount() == null) ? 0 : getIsMainAccount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}