/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.account.dao.po;

import java.io.Serializable;
import java.util.Date;

public class AccountTradeApply implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *银行账户id，或者是新建监管和解除监管时的监管账户Id
     */
    private String accountId;

    /**
     *交易账户，发起该交易的账户
     */
    private String accountNo;

    /**
     *账户名称
     */
    private String accountName;

    /**
     *申请类型，转账、提现、新建监管、解除监管
     */
    private String tradeType;

    /**
     *交易状态（待审核、审核失败、审核成功）
     */
    private String status;

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
     *联行号
     */
    private String lianHangNo;

    /**
     *提现银行地址
     */
    private String withdrawBankAddress;

    /**
     *交易金额，分为单位，正为收入，负为支出
     */
    private Long amount;

    /**
     *交易手续费
     */
    private Long charge;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *申请客户ID
     */
    private String applyCompanyId;

    /**
     *申请客户名称
     */
    private String applyCompanyName;

    /**
     *需要对此交易做审核的客户id
     */
    private String auditCompanyId;

    /**
     *审核员工id
     */
    private String auditUserId;

    /**
     *审核意见
     */
    private String auditMessage;

    /**
     *审核时间
     */
    private Date auditTime;

    /**
     *解除监管时是否移除所有监管关系
     */
    private Boolean isRemoveAllSupervision;

    /**
     *监管关系关联的专用账户Id列表，使用英文分号分隔；
     */
    private String specialAccountIds;

    /**
     *解除监管时需要解除的专用账户id列表，使用英文分号分隔；
     */
    private String removeSpecialAccountIds;

    /**
     *是否是默认还款账户
     */
    private Boolean isDefaultRepayment;

    /**
     *交易备注
     */
    private String remark;

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
     *银行账户id，或者是新建监管和解除监管时的监管账户Id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *银行账户id，或者是新建监管和解除监管时的监管账户Id
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
     *申请类型，转账、提现、新建监管、解除监管
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     *申请类型，转账、提现、新建监管、解除监管
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     *交易状态（待审核、审核失败、审核成功）
     */
    public String getStatus() {
        return status;
    }

    /**
     *交易状态（待审核、审核失败、审核成功）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     *联行号
     */
    public String getLianHangNo() {
        return lianHangNo;
    }

    /**
     *联行号
     */
    public void setLianHangNo(String lianHangNo) {
        this.lianHangNo = lianHangNo == null ? null : lianHangNo.trim();
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
     *交易手续费
     */
    public Long getCharge() {
        return charge;
    }

    /**
     *交易手续费
     */
    public void setCharge(Long charge) {
        this.charge = charge;
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
     *申请客户ID
     */
    public String getApplyCompanyId() {
        return applyCompanyId;
    }

    /**
     *申请客户ID
     */
    public void setApplyCompanyId(String applyCompanyId) {
        this.applyCompanyId = applyCompanyId == null ? null : applyCompanyId.trim();
    }

    /**
     *申请客户名称
     */
    public String getApplyCompanyName() {
        return applyCompanyName;
    }

    /**
     *申请客户名称
     */
    public void setApplyCompanyName(String applyCompanyName) {
        this.applyCompanyName = applyCompanyName == null ? null : applyCompanyName.trim();
    }

    /**
     *需要对此交易做审核的客户id
     */
    public String getAuditCompanyId() {
        return auditCompanyId;
    }

    /**
     *需要对此交易做审核的客户id
     */
    public void setAuditCompanyId(String auditCompanyId) {
        this.auditCompanyId = auditCompanyId == null ? null : auditCompanyId.trim();
    }

    /**
     *审核员工id
     */
    public String getAuditUserId() {
        return auditUserId;
    }

    /**
     *审核员工id
     */
    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId == null ? null : auditUserId.trim();
    }

    /**
     *审核意见
     */
    public String getAuditMessage() {
        return auditMessage;
    }

    /**
     *审核意见
     */
    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage == null ? null : auditMessage.trim();
    }

    /**
     *审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     *审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     *解除监管时是否移除所有监管关系
     */
    public Boolean getIsRemoveAllSupervision() {
        return isRemoveAllSupervision;
    }

    /**
     *解除监管时是否移除所有监管关系
     */
    public void setIsRemoveAllSupervision(Boolean isRemoveAllSupervision) {
        this.isRemoveAllSupervision = isRemoveAllSupervision;
    }

    /**
     *监管关系关联的专用账户Id列表，使用英文分号分隔；
     */
    public String getSpecialAccountIds() {
        return specialAccountIds;
    }

    /**
     *监管关系关联的专用账户Id列表，使用英文分号分隔；
     */
    public void setSpecialAccountIds(String specialAccountIds) {
        this.specialAccountIds = specialAccountIds == null ? null : specialAccountIds.trim();
    }

    /**
     *解除监管时需要解除的专用账户id列表，使用英文分号分隔；
     */
    public String getRemoveSpecialAccountIds() {
        return removeSpecialAccountIds;
    }

    /**
     *解除监管时需要解除的专用账户id列表，使用英文分号分隔；
     */
    public void setRemoveSpecialAccountIds(String removeSpecialAccountIds) {
        this.removeSpecialAccountIds = removeSpecialAccountIds == null ? null : removeSpecialAccountIds.trim();
    }

    /**
     *是否是默认还款账户
     */
    public Boolean getIsDefaultRepayment() {
        return isDefaultRepayment;
    }

    /**
     *是否是默认还款账户
     */
    public void setIsDefaultRepayment(Boolean isDefaultRepayment) {
        this.isDefaultRepayment = isDefaultRepayment;
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
        AccountTradeApply other = (AccountTradeApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOppositeAccountId() == null ? other.getOppositeAccountId() == null : this.getOppositeAccountId().equals(other.getOppositeAccountId()))
            && (this.getOppositeAccountNo() == null ? other.getOppositeAccountNo() == null : this.getOppositeAccountNo().equals(other.getOppositeAccountNo()))
            && (this.getOppositeAccountName() == null ? other.getOppositeAccountName() == null : this.getOppositeAccountName().equals(other.getOppositeAccountName()))
            && (this.getWithdrawBank() == null ? other.getWithdrawBank() == null : this.getWithdrawBank().equals(other.getWithdrawBank()))
            && (this.getLianHangNo() == null ? other.getLianHangNo() == null : this.getLianHangNo().equals(other.getLianHangNo()))
            && (this.getWithdrawBankAddress() == null ? other.getWithdrawBankAddress() == null : this.getWithdrawBankAddress().equals(other.getWithdrawBankAddress()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCharge() == null ? other.getCharge() == null : this.getCharge().equals(other.getCharge()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getApplyCompanyId() == null ? other.getApplyCompanyId() == null : this.getApplyCompanyId().equals(other.getApplyCompanyId()))
            && (this.getApplyCompanyName() == null ? other.getApplyCompanyName() == null : this.getApplyCompanyName().equals(other.getApplyCompanyName()))
            && (this.getAuditCompanyId() == null ? other.getAuditCompanyId() == null : this.getAuditCompanyId().equals(other.getAuditCompanyId()))
            && (this.getAuditUserId() == null ? other.getAuditUserId() == null : this.getAuditUserId().equals(other.getAuditUserId()))
            && (this.getAuditMessage() == null ? other.getAuditMessage() == null : this.getAuditMessage().equals(other.getAuditMessage()))
            && (this.getAuditTime() == null ? other.getAuditTime() == null : this.getAuditTime().equals(other.getAuditTime()))
            && (this.getIsRemoveAllSupervision() == null ? other.getIsRemoveAllSupervision() == null : this.getIsRemoveAllSupervision().equals(other.getIsRemoveAllSupervision()))
            && (this.getSpecialAccountIds() == null ? other.getSpecialAccountIds() == null : this.getSpecialAccountIds().equals(other.getSpecialAccountIds()))
            && (this.getRemoveSpecialAccountIds() == null ? other.getRemoveSpecialAccountIds() == null : this.getRemoveSpecialAccountIds().equals(other.getRemoveSpecialAccountIds()))
            && (this.getIsDefaultRepayment() == null ? other.getIsDefaultRepayment() == null : this.getIsDefaultRepayment().equals(other.getIsDefaultRepayment()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOppositeAccountId() == null) ? 0 : getOppositeAccountId().hashCode());
        result = prime * result + ((getOppositeAccountNo() == null) ? 0 : getOppositeAccountNo().hashCode());
        result = prime * result + ((getOppositeAccountName() == null) ? 0 : getOppositeAccountName().hashCode());
        result = prime * result + ((getWithdrawBank() == null) ? 0 : getWithdrawBank().hashCode());
        result = prime * result + ((getLianHangNo() == null) ? 0 : getLianHangNo().hashCode());
        result = prime * result + ((getWithdrawBankAddress() == null) ? 0 : getWithdrawBankAddress().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCharge() == null) ? 0 : getCharge().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getApplyCompanyId() == null) ? 0 : getApplyCompanyId().hashCode());
        result = prime * result + ((getApplyCompanyName() == null) ? 0 : getApplyCompanyName().hashCode());
        result = prime * result + ((getAuditCompanyId() == null) ? 0 : getAuditCompanyId().hashCode());
        result = prime * result + ((getAuditUserId() == null) ? 0 : getAuditUserId().hashCode());
        result = prime * result + ((getAuditMessage() == null) ? 0 : getAuditMessage().hashCode());
        result = prime * result + ((getAuditTime() == null) ? 0 : getAuditTime().hashCode());
        result = prime * result + ((getIsRemoveAllSupervision() == null) ? 0 : getIsRemoveAllSupervision().hashCode());
        result = prime * result + ((getSpecialAccountIds() == null) ? 0 : getSpecialAccountIds().hashCode());
        result = prime * result + ((getRemoveSpecialAccountIds() == null) ? 0 : getRemoveSpecialAccountIds().hashCode());
        result = prime * result + ((getIsDefaultRepayment() == null) ? 0 : getIsDefaultRepayment().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}