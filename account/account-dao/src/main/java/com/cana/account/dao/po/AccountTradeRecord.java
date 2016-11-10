/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.account.dao.po;

import java.io.Serializable;
import java.util.Date;

public class AccountTradeRecord implements Serializable {
    /**
     *主键，平台流水号，cana页面显示使用
     */
    private String id;

    /**
     *账户所属客户ID
     */
    private String companyId;

    /**
     *银行账户id
     */
    private String accountId;

    /**
     *交易账户，发起该交易的账户
     */
    private String accountNo;

    /**
     *账户名称，即账户所属公司名称
     */
    private String accountName;

    /**
     *当前账户所属账户类型，一般、专用
     */
    private String accountType;

    /**
     *业务类型，转账、提现，开户、解冻、解冻
     */
    private String tradeType;

    /**
     *交易操作方式：手工操作还是系统自动操作
     */
    private String operateType;

    /**
     *交易对方账户id，Ex：转账时的收款账户
     */
    private String oppositeAccountId;

    /**
     *交易对方账号
     */
    private String oppositeAccountNo;

    /**
     *交易对方账户名字
     */
    private String oppositeAccountName;

    /**
     *提现银行名称
     */
    private String withdrawBank;

    /**
     *提现银行地址
     */
    private String withdrawBankAddress;

    /**
     *账户监管关系Id
     */
    private String accountSupervisionId;

    /**
     *交易金额，分为单位，正为收入，负为支出
     */
    private Long amount;

    /**
     *交易状态（银行接口请求中、交易中、交易成功、交易失败）
     */
    private String status;

    /**
     *业务流水号，对应银行的一笔交易，不在页面上显示
     */
    private String businessSeq;

    /**
     *交易发起时间
     */
    private Date tradeStartTime;

    /**
     *交易结束时间
     */
    private Date tradeEndTime;

    /**
     *操作员工ID
     */
    private String operateUserId;

    /**
     *交易备注
     */
    private String remark;

    /**
     *用户类型
     */
    private String userType;

    private static final long serialVersionUID = 1L;

    /**
     *主键，平台流水号，cana页面显示使用
     */
    public String getId() {
        return id;
    }

    /**
     *主键，平台流水号，cana页面显示使用
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *账户所属客户ID
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     *账户所属客户ID
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     *银行账户id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *银行账户id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     *交易账户，发起该交易的账户
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *交易账户，发起该交易的账户
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *账户名称，即账户所属公司名称
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     *账户名称，即账户所属公司名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     *当前账户所属账户类型，一般、专用
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     *当前账户所属账户类型，一般、专用
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    /**
     *业务类型，转账、提现，开户、解冻、解冻
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     *业务类型，转账、提现，开户、解冻、解冻
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     *交易操作方式：手工操作还是系统自动操作
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     *交易操作方式：手工操作还是系统自动操作
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     *交易对方账户id，Ex：转账时的收款账户
     */
    public String getOppositeAccountId() {
        return oppositeAccountId;
    }

    /**
     *交易对方账户id，Ex：转账时的收款账户
     */
    public void setOppositeAccountId(String oppositeAccountId) {
        this.oppositeAccountId = oppositeAccountId == null ? null : oppositeAccountId.trim();
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
     *交易对方账户名字
     */
    public String getOppositeAccountName() {
        return oppositeAccountName;
    }

    /**
     *交易对方账户名字
     */
    public void setOppositeAccountName(String oppositeAccountName) {
        this.oppositeAccountName = oppositeAccountName == null ? null : oppositeAccountName.trim();
    }

    /**
     *提现银行名称
     */
    public String getWithdrawBank() {
        return withdrawBank;
    }

    /**
     *提现银行名称
     */
    public void setWithdrawBank(String withdrawBank) {
        this.withdrawBank = withdrawBank == null ? null : withdrawBank.trim();
    }

    /**
     *提现银行地址
     */
    public String getWithdrawBankAddress() {
        return withdrawBankAddress;
    }

    /**
     *提现银行地址
     */
    public void setWithdrawBankAddress(String withdrawBankAddress) {
        this.withdrawBankAddress = withdrawBankAddress == null ? null : withdrawBankAddress.trim();
    }

    /**
     *账户监管关系Id
     */
    public String getAccountSupervisionId() {
        return accountSupervisionId;
    }

    /**
     *账户监管关系Id
     */
    public void setAccountSupervisionId(String accountSupervisionId) {
        this.accountSupervisionId = accountSupervisionId == null ? null : accountSupervisionId.trim();
    }

    /**
     *交易金额，分为单位，正为收入，负为支出
     */
    public Long getAmount() {
        return amount;
    }

    /**
     *交易金额，分为单位，正为收入，负为支出
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     *交易状态（银行接口请求中、交易中、交易成功、交易失败）
     */
    public String getStatus() {
        return status;
    }

    /**
     *交易状态（银行接口请求中、交易中、交易成功、交易失败）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *业务流水号，对应银行的一笔交易，不在页面上显示
     */
    public String getBusinessSeq() {
        return businessSeq;
    }

    /**
     *业务流水号，对应银行的一笔交易，不在页面上显示
     */
    public void setBusinessSeq(String businessSeq) {
        this.businessSeq = businessSeq == null ? null : businessSeq.trim();
    }

    /**
     *交易发起时间
     */
    public Date getTradeStartTime() {
        return tradeStartTime;
    }

    /**
     *交易发起时间
     */
    public void setTradeStartTime(Date tradeStartTime) {
        this.tradeStartTime = tradeStartTime;
    }

    /**
     *交易结束时间
     */
    public Date getTradeEndTime() {
        return tradeEndTime;
    }

    /**
     *交易结束时间
     */
    public void setTradeEndTime(Date tradeEndTime) {
        this.tradeEndTime = tradeEndTime;
    }

    /**
     *操作员工ID
     */
    public String getOperateUserId() {
        return operateUserId;
    }

    /**
     *操作员工ID
     */
    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId == null ? null : operateUserId.trim();
    }

    /**
     *交易备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     *交易备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     *用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     *用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
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
        AccountTradeRecord other = (AccountTradeRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getAccountType() == null ? other.getAccountType() == null : this.getAccountType().equals(other.getAccountType()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
            && (this.getOppositeAccountId() == null ? other.getOppositeAccountId() == null : this.getOppositeAccountId().equals(other.getOppositeAccountId()))
            && (this.getOppositeAccountNo() == null ? other.getOppositeAccountNo() == null : this.getOppositeAccountNo().equals(other.getOppositeAccountNo()))
            && (this.getOppositeAccountName() == null ? other.getOppositeAccountName() == null : this.getOppositeAccountName().equals(other.getOppositeAccountName()))
            && (this.getWithdrawBank() == null ? other.getWithdrawBank() == null : this.getWithdrawBank().equals(other.getWithdrawBank()))
            && (this.getWithdrawBankAddress() == null ? other.getWithdrawBankAddress() == null : this.getWithdrawBankAddress().equals(other.getWithdrawBankAddress()))
            && (this.getAccountSupervisionId() == null ? other.getAccountSupervisionId() == null : this.getAccountSupervisionId().equals(other.getAccountSupervisionId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBusinessSeq() == null ? other.getBusinessSeq() == null : this.getBusinessSeq().equals(other.getBusinessSeq()))
            && (this.getTradeStartTime() == null ? other.getTradeStartTime() == null : this.getTradeStartTime().equals(other.getTradeStartTime()))
            && (this.getTradeEndTime() == null ? other.getTradeEndTime() == null : this.getTradeEndTime().equals(other.getTradeEndTime()))
            && (this.getOperateUserId() == null ? other.getOperateUserId() == null : this.getOperateUserId().equals(other.getOperateUserId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getAccountType() == null) ? 0 : getAccountType().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getOppositeAccountId() == null) ? 0 : getOppositeAccountId().hashCode());
        result = prime * result + ((getOppositeAccountNo() == null) ? 0 : getOppositeAccountNo().hashCode());
        result = prime * result + ((getOppositeAccountName() == null) ? 0 : getOppositeAccountName().hashCode());
        result = prime * result + ((getWithdrawBank() == null) ? 0 : getWithdrawBank().hashCode());
        result = prime * result + ((getWithdrawBankAddress() == null) ? 0 : getWithdrawBankAddress().hashCode());
        result = prime * result + ((getAccountSupervisionId() == null) ? 0 : getAccountSupervisionId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBusinessSeq() == null) ? 0 : getBusinessSeq().hashCode());
        result = prime * result + ((getTradeStartTime() == null) ? 0 : getTradeStartTime().hashCode());
        result = prime * result + ((getTradeEndTime() == null) ? 0 : getTradeEndTime().hashCode());
        result = prime * result + ((getOperateUserId() == null) ? 0 : getOperateUserId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        return result;
    }
}