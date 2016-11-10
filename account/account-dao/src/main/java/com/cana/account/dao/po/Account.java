/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.account.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *归集账号Id
    */
    private String accumulationId;

    /**
    *账户申请id
    */
    private String accountApplyId;

    /**
    *账户所属企业客户类型（FACTOR/FINACE/CANA）
    */
    private String userType;

    /**
    *账户所属企业id
    */
    private String companyId;

    /**
    *账户所属企业名称
    */
    private String companyName;

    /**
    *当前监管关系Id，对应account_supervision表
    */
    private String accountSupervisionId;

    /**
    *当前监管企业id
    */
    private String supervisorId;

    /**
    *当前监管企业名称
    */
    private String supervisorName;

    /**
    *银行账号类型（一般账户、专有账户）
    */
    private String accountType;

    /**
    *银行账号
    */
    private String accountNo;

    /**
    *账户状态（冻结、正常）
    */
    private String accountStatus;

    /**
    *监管状态（未监管、监管）
    */
    private String supervisionStatus;

    /**
    *归集状态（未归集、归集、被归集）
    */
    private String accumulationStatus;

    /**
    *买方企业名称
    */
    private String buyerName;

    /**
    *是否为默认还款账户
    */
    private Boolean isDefaultRepayment;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *更新时间
    */
    private Date updateTime;

    /**
    *操作客户ID
    */
    private String operateCompanyId;

    /**
    *是否为保理商的回款账户
    */
    private Boolean isTransferInAccount;

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
    *归集账号Id
    */
    public String getAccumulationId() {
        return accumulationId;
    }

    /**
    *归集账号Id
    */
    public void setAccumulationId(String accumulationId) {
        this.accumulationId = accumulationId == null ? null : accumulationId.trim();
    }

    /**
    *账户申请id
    */
    public String getAccountApplyId() {
        return accountApplyId;
    }

    /**
    *账户申请id
    */
    public void setAccountApplyId(String accountApplyId) {
        this.accountApplyId = accountApplyId == null ? null : accountApplyId.trim();
    }

    /**
    *账户所属企业客户类型（FACTOR/FINACE/CANA）
    */
    public String getUserType() {
        return userType;
    }

    /**
    *账户所属企业客户类型（FACTOR/FINACE/CANA）
    */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
    *账户所属企业id
    */
    public String getCompanyId() {
        return companyId;
    }

    /**
    *账户所属企业id
    */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
    *账户所属企业名称
    */
    public String getCompanyName() {
        return companyName;
    }

    /**
    *账户所属企业名称
    */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
    *当前监管关系Id，对应account_supervision表
    */
    public String getAccountSupervisionId() {
        return accountSupervisionId;
    }

    /**
    *当前监管关系Id，对应account_supervision表
    */
    public void setAccountSupervisionId(String accountSupervisionId) {
        this.accountSupervisionId = accountSupervisionId == null ? null : accountSupervisionId.trim();
    }

    /**
    *当前监管企业id
    */
    public String getSupervisorId() {
        return supervisorId;
    }

    /**
    *当前监管企业id
    */
    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId == null ? null : supervisorId.trim();
    }

    /**
    *当前监管企业名称
    */
    public String getSupervisorName() {
        return supervisorName;
    }

    /**
    *当前监管企业名称
    */
    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName == null ? null : supervisorName.trim();
    }

    /**
    *银行账号类型（一般账户、专有账户）
    */
    public String getAccountType() {
        return accountType;
    }

    /**
    *银行账号类型（一般账户、专有账户）
    */
    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
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
    *账户状态（冻结、正常）
    */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
    *账户状态（冻结、正常）
    */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    /**
    *监管状态（未监管、监管）
    */
    public String getSupervisionStatus() {
        return supervisionStatus;
    }

    /**
    *监管状态（未监管、监管）
    */
    public void setSupervisionStatus(String supervisionStatus) {
        this.supervisionStatus = supervisionStatus == null ? null : supervisionStatus.trim();
    }

    /**
    *归集状态（未归集、归集、被归集）
    */
    public String getAccumulationStatus() {
        return accumulationStatus;
    }

    /**
    *归集状态（未归集、归集、被归集）
    */
    public void setAccumulationStatus(String accumulationStatus) {
        this.accumulationStatus = accumulationStatus == null ? null : accumulationStatus.trim();
    }

    /**
    *买方企业名称
    */
    public String getBuyerName() {
        return buyerName;
    }

    /**
    *买方企业名称
    */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    /**
    *是否为默认还款账户
    */
    public Boolean getIsDefaultRepayment() {
        return isDefaultRepayment;
    }

    /**
    *是否为默认还款账户
    */
    public void setIsDefaultRepayment(Boolean isDefaultRepayment) {
        this.isDefaultRepayment = isDefaultRepayment;
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
    *操作客户ID
    */
    public String getOperateCompanyId() {
        return operateCompanyId;
    }

    /**
    *操作客户ID
    */
    public void setOperateCompanyId(String operateCompanyId) {
        this.operateCompanyId = operateCompanyId == null ? null : operateCompanyId.trim();
    }

    /**
    *是否为保理商的回款账户
    */
    public Boolean getIsTransferInAccount() {
        return isTransferInAccount;
    }

    /**
    *是否为保理商的回款账户
    */
    public void setIsTransferInAccount(Boolean isTransferInAccount) {
        this.isTransferInAccount = isTransferInAccount;
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
        Account other = (Account) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccumulationId() == null ? other.getAccumulationId() == null : this.getAccumulationId().equals(other.getAccumulationId()))
            && (this.getAccountApplyId() == null ? other.getAccountApplyId() == null : this.getAccountApplyId().equals(other.getAccountApplyId()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getAccountSupervisionId() == null ? other.getAccountSupervisionId() == null : this.getAccountSupervisionId().equals(other.getAccountSupervisionId()))
            && (this.getSupervisorId() == null ? other.getSupervisorId() == null : this.getSupervisorId().equals(other.getSupervisorId()))
            && (this.getSupervisorName() == null ? other.getSupervisorName() == null : this.getSupervisorName().equals(other.getSupervisorName()))
            && (this.getAccountType() == null ? other.getAccountType() == null : this.getAccountType().equals(other.getAccountType()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getAccountStatus() == null ? other.getAccountStatus() == null : this.getAccountStatus().equals(other.getAccountStatus()))
            && (this.getSupervisionStatus() == null ? other.getSupervisionStatus() == null : this.getSupervisionStatus().equals(other.getSupervisionStatus()))
            && (this.getAccumulationStatus() == null ? other.getAccumulationStatus() == null : this.getAccumulationStatus().equals(other.getAccumulationStatus()))
            && (this.getBuyerName() == null ? other.getBuyerName() == null : this.getBuyerName().equals(other.getBuyerName()))
            && (this.getIsDefaultRepayment() == null ? other.getIsDefaultRepayment() == null : this.getIsDefaultRepayment().equals(other.getIsDefaultRepayment()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getOperateCompanyId() == null ? other.getOperateCompanyId() == null : this.getOperateCompanyId().equals(other.getOperateCompanyId()))
            && (this.getIsTransferInAccount() == null ? other.getIsTransferInAccount() == null : this.getIsTransferInAccount().equals(other.getIsTransferInAccount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccumulationId() == null) ? 0 : getAccumulationId().hashCode());
        result = prime * result + ((getAccountApplyId() == null) ? 0 : getAccountApplyId().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getAccountSupervisionId() == null) ? 0 : getAccountSupervisionId().hashCode());
        result = prime * result + ((getSupervisorId() == null) ? 0 : getSupervisorId().hashCode());
        result = prime * result + ((getSupervisorName() == null) ? 0 : getSupervisorName().hashCode());
        result = prime * result + ((getAccountType() == null) ? 0 : getAccountType().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getAccountStatus() == null) ? 0 : getAccountStatus().hashCode());
        result = prime * result + ((getSupervisionStatus() == null) ? 0 : getSupervisionStatus().hashCode());
        result = prime * result + ((getAccumulationStatus() == null) ? 0 : getAccumulationStatus().hashCode());
        result = prime * result + ((getBuyerName() == null) ? 0 : getBuyerName().hashCode());
        result = prime * result + ((getIsDefaultRepayment() == null) ? 0 : getIsDefaultRepayment().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getOperateCompanyId() == null) ? 0 : getOperateCompanyId().hashCode());
        result = prime * result + ((getIsTransferInAccount() == null) ? 0 : getIsTransferInAccount().hashCode());
        return result;
    }
}