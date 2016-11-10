/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssetInvoiceInfo implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *账款基本信息id
     */
    private String invoiceBaseId;

    /**
     *交易对手Id
     */
    private String counterpartyId;

    /**
     *交易对手
     */
    private String counterparty;

    /**
     *单证号码
     */
    private String invoiceNo;

    /**
     *单证面额
     */
    private Long nominvoiceAmt;

    /**
     *应收金额
     */
    private Long invoiceAmt;

    /**
     *融资比例
     */
    private BigDecimal financingRatio;

    /**
     *开票日
     */
    private String invoiceDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *关联的放款信息ID
     */
    private String loanInfoId;

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
     *账款基本信息id
     */
    public String getInvoiceBaseId() {
        return invoiceBaseId;
    }

    /**
     *账款基本信息id
     */
    public void setInvoiceBaseId(String invoiceBaseId) {
        this.invoiceBaseId = invoiceBaseId == null ? null : invoiceBaseId.trim();
    }

    /**
     *交易对手Id
     */
    public String getCounterpartyId() {
        return counterpartyId;
    }

    /**
     *交易对手Id
     */
    public void setCounterpartyId(String counterpartyId) {
        this.counterpartyId = counterpartyId == null ? null : counterpartyId.trim();
    }

    /**
     *交易对手
     */
    public String getCounterparty() {
        return counterparty;
    }

    /**
     *交易对手
     */
    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty == null ? null : counterparty.trim();
    }

    /**
     *单证号码
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     *单证号码
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    /**
     *单证面额
     */
    public Long getNominvoiceAmt() {
        return nominvoiceAmt;
    }

    /**
     *单证面额
     */
    public void setNominvoiceAmt(Long nominvoiceAmt) {
        this.nominvoiceAmt = nominvoiceAmt;
    }

    /**
     *应收金额
     */
    public Long getInvoiceAmt() {
        return invoiceAmt;
    }

    /**
     *应收金额
     */
    public void setInvoiceAmt(Long invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    /**
     *融资比例
     */
    public BigDecimal getFinancingRatio() {
        return financingRatio;
    }

    /**
     *融资比例
     */
    public void setFinancingRatio(BigDecimal financingRatio) {
        this.financingRatio = financingRatio;
    }

    /**
     *开票日
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     *开票日
     */
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate == null ? null : invoiceDate.trim();
    }

    /**
     *到期日
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     *到期日
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    /**
     *关联的放款信息ID
     */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
     *关联的放款信息ID
     */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
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
        AssetInvoiceInfo other = (AssetInvoiceInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInvoiceBaseId() == null ? other.getInvoiceBaseId() == null : this.getInvoiceBaseId().equals(other.getInvoiceBaseId()))
            && (this.getCounterpartyId() == null ? other.getCounterpartyId() == null : this.getCounterpartyId().equals(other.getCounterpartyId()))
            && (this.getCounterparty() == null ? other.getCounterparty() == null : this.getCounterparty().equals(other.getCounterparty()))
            && (this.getInvoiceNo() == null ? other.getInvoiceNo() == null : this.getInvoiceNo().equals(other.getInvoiceNo()))
            && (this.getNominvoiceAmt() == null ? other.getNominvoiceAmt() == null : this.getNominvoiceAmt().equals(other.getNominvoiceAmt()))
            && (this.getInvoiceAmt() == null ? other.getInvoiceAmt() == null : this.getInvoiceAmt().equals(other.getInvoiceAmt()))
            && (this.getFinancingRatio() == null ? other.getFinancingRatio() == null : this.getFinancingRatio().equals(other.getFinancingRatio()))
            && (this.getInvoiceDate() == null ? other.getInvoiceDate() == null : this.getInvoiceDate().equals(other.getInvoiceDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInvoiceBaseId() == null) ? 0 : getInvoiceBaseId().hashCode());
        result = prime * result + ((getCounterpartyId() == null) ? 0 : getCounterpartyId().hashCode());
        result = prime * result + ((getCounterparty() == null) ? 0 : getCounterparty().hashCode());
        result = prime * result + ((getInvoiceNo() == null) ? 0 : getInvoiceNo().hashCode());
        result = prime * result + ((getNominvoiceAmt() == null) ? 0 : getNominvoiceAmt().hashCode());
        result = prime * result + ((getInvoiceAmt() == null) ? 0 : getInvoiceAmt().hashCode());
        result = prime * result + ((getFinancingRatio() == null) ? 0 : getFinancingRatio().hashCode());
        result = prime * result + ((getInvoiceDate() == null) ? 0 : getInvoiceDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}