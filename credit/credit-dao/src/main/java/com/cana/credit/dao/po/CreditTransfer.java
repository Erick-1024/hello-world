/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.credit.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CreditTransfer implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *业务流水号
     */
    private String businessSeq;

    /**
     *该笔转账所对应授信交易的Cana流水号
     */
    private String creditTradeId;

    /**
     *转账状态。"SUCCESS"：转账成功；"HANDLING"：转账中；"FAIL"：转账失败
     */
    private String transferStatus;

    /**
     *转账金额。精确到分
     */
    private Long fee;

    /**
     *转账类型。LOAN：放款；REFUND2FACTOR：退款给资金方；REFUND2CUSTOMER：退款给客户
     */
    private String transferType;

    /**
     *转出账号
     */
    private String fromAccountNo;

    /**
     *转出账户名
     */
    private String fromAccountName;

    /**
     *转入账号
     */
    private String toAccountNo;

    /**
     *转入账户名
     */
    private String toAccountName;

    /**
     *操作员ID
     */
    private String operatorId;

    /**
     *转账开始时间
     */
    private Date transferStartTime;

    /**
     *转账结束时间
     */
    private Date transferEndTime;

    /**
     *历史流水号。失败时将原交易流水号追加在该字段后面，用”,“分割(退款给客户时失败时，用)
     */
    private String businessSeqHistory;

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
     *业务流水号
     */
    public String getBusinessSeq() {
        return businessSeq;
    }

    /**
     *业务流水号
     */
    public void setBusinessSeq(String businessSeq) {
        this.businessSeq = businessSeq == null ? null : businessSeq.trim();
    }

    /**
     *该笔转账所对应授信交易的Cana流水号
     */
    public String getCreditTradeId() {
        return creditTradeId;
    }

    /**
     *该笔转账所对应授信交易的Cana流水号
     */
    public void setCreditTradeId(String creditTradeId) {
        this.creditTradeId = creditTradeId == null ? null : creditTradeId.trim();
    }

    /**
     *转账状态。"SUCCESS"：转账成功；"HANDLING"：转账中；"FAIL"：转账失败
     */
    public String getTransferStatus() {
        return transferStatus;
    }

    /**
     *转账状态。"SUCCESS"：转账成功；"HANDLING"：转账中；"FAIL"：转账失败
     */
    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus == null ? null : transferStatus.trim();
    }

    /**
     *转账金额。精确到分
     */
    public Long getFee() {
        return fee;
    }

    /**
     *转账金额。精确到分
     */
    public void setFee(Long fee) {
        this.fee = fee;
    }

    /**
     *转账类型。LOAN：放款；REFUND2FACTOR：退款给资金方；REFUND2CUSTOMER：退款给客户
     */
    public String getTransferType() {
        return transferType;
    }

    /**
     *转账类型。LOAN：放款；REFUND2FACTOR：退款给资金方；REFUND2CUSTOMER：退款给客户
     */
    public void setTransferType(String transferType) {
        this.transferType = transferType == null ? null : transferType.trim();
    }

    /**
     *转出账号
     */
    public String getFromAccountNo() {
        return fromAccountNo;
    }

    /**
     *转出账号
     */
    public void setFromAccountNo(String fromAccountNo) {
        this.fromAccountNo = fromAccountNo == null ? null : fromAccountNo.trim();
    }

    /**
     *转出账户名
     */
    public String getFromAccountName() {
        return fromAccountName;
    }

    /**
     *转出账户名
     */
    public void setFromAccountName(String fromAccountName) {
        this.fromAccountName = fromAccountName == null ? null : fromAccountName.trim();
    }

    /**
     *转入账号
     */
    public String getToAccountNo() {
        return toAccountNo;
    }

    /**
     *转入账号
     */
    public void setToAccountNo(String toAccountNo) {
        this.toAccountNo = toAccountNo == null ? null : toAccountNo.trim();
    }

    /**
     *转入账户名
     */
    public String getToAccountName() {
        return toAccountName;
    }

    /**
     *转入账户名
     */
    public void setToAccountName(String toAccountName) {
        this.toAccountName = toAccountName == null ? null : toAccountName.trim();
    }

    /**
     *操作员ID
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     *操作员ID
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    /**
     *转账开始时间
     */
    public Date getTransferStartTime() {
        return transferStartTime;
    }

    /**
     *转账开始时间
     */
    public void setTransferStartTime(Date transferStartTime) {
        this.transferStartTime = transferStartTime;
    }

    /**
     *转账结束时间
     */
    public Date getTransferEndTime() {
        return transferEndTime;
    }

    /**
     *转账结束时间
     */
    public void setTransferEndTime(Date transferEndTime) {
        this.transferEndTime = transferEndTime;
    }

    /**
     *历史流水号。失败时将原交易流水号追加在该字段后面，用”,“分割(退款给客户时失败时，用)
     */
    public String getBusinessSeqHistory() {
        return businessSeqHistory;
    }

    /**
     *历史流水号。失败时将原交易流水号追加在该字段后面，用”,“分割(退款给客户时失败时，用)
     */
    public void setBusinessSeqHistory(String businessSeqHistory) {
        this.businessSeqHistory = businessSeqHistory == null ? null : businessSeqHistory.trim();
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
        CreditTransfer other = (CreditTransfer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessSeq() == null ? other.getBusinessSeq() == null : this.getBusinessSeq().equals(other.getBusinessSeq()))
            && (this.getCreditTradeId() == null ? other.getCreditTradeId() == null : this.getCreditTradeId().equals(other.getCreditTradeId()))
            && (this.getTransferStatus() == null ? other.getTransferStatus() == null : this.getTransferStatus().equals(other.getTransferStatus()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getTransferType() == null ? other.getTransferType() == null : this.getTransferType().equals(other.getTransferType()))
            && (this.getFromAccountNo() == null ? other.getFromAccountNo() == null : this.getFromAccountNo().equals(other.getFromAccountNo()))
            && (this.getFromAccountName() == null ? other.getFromAccountName() == null : this.getFromAccountName().equals(other.getFromAccountName()))
            && (this.getToAccountNo() == null ? other.getToAccountNo() == null : this.getToAccountNo().equals(other.getToAccountNo()))
            && (this.getToAccountName() == null ? other.getToAccountName() == null : this.getToAccountName().equals(other.getToAccountName()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getTransferStartTime() == null ? other.getTransferStartTime() == null : this.getTransferStartTime().equals(other.getTransferStartTime()))
            && (this.getTransferEndTime() == null ? other.getTransferEndTime() == null : this.getTransferEndTime().equals(other.getTransferEndTime()))
            && (this.getBusinessSeqHistory() == null ? other.getBusinessSeqHistory() == null : this.getBusinessSeqHistory().equals(other.getBusinessSeqHistory()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessSeq() == null) ? 0 : getBusinessSeq().hashCode());
        result = prime * result + ((getCreditTradeId() == null) ? 0 : getCreditTradeId().hashCode());
        result = prime * result + ((getTransferStatus() == null) ? 0 : getTransferStatus().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getTransferType() == null) ? 0 : getTransferType().hashCode());
        result = prime * result + ((getFromAccountNo() == null) ? 0 : getFromAccountNo().hashCode());
        result = prime * result + ((getFromAccountName() == null) ? 0 : getFromAccountName().hashCode());
        result = prime * result + ((getToAccountNo() == null) ? 0 : getToAccountNo().hashCode());
        result = prime * result + ((getToAccountName() == null) ? 0 : getToAccountName().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getTransferStartTime() == null) ? 0 : getTransferStartTime().hashCode());
        result = prime * result + ((getTransferEndTime() == null) ? 0 : getTransferEndTime().hashCode());
        result = prime * result + ((getBusinessSeqHistory() == null) ? 0 : getBusinessSeqHistory().hashCode());
        return result;
    }
}