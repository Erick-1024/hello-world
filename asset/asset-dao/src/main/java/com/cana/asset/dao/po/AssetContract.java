/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class AssetContract implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *产品ID
     */
    private String productId;

    /**
     *用户ID
     */
    private String memberId;

    /**
     *保理商ID
     */
    private String factorId;

    /**
     *媒体服务器文件ID
     */
    private String mediaId;

    /**
     *文件名（不带后缀）
     */
    private String fileName;

    /**
     *文件后缀
     */
    private String fileSuffix;

    /**
     *监管账号，即融资商进行还款的账号
     */
    private String accountNo;

    /**
     *监管关系ID
     */
    private String accountSupervisionId;

    /**
     *合同生效日
     */
    private String effectiveDate;

    /**
     *合同到期日
     */
    private String dueDate;

    /**
     *
     */
    private Boolean deleted;

    /**
     *创建时间
     */
    private Date createTime;

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
     *产品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     *产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     *用户ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     *用户ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     *保理商ID
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *保理商ID
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *媒体服务器文件ID
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     *媒体服务器文件ID
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    /**
     *文件名（不带后缀）
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *文件名（不带后缀）
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     *文件后缀
     */
    public String getFileSuffix() {
        return fileSuffix;
    }

    /**
     *文件后缀
     */
    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix == null ? null : fileSuffix.trim();
    }

    /**
     *监管账号，即融资商进行还款的账号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *监管账号，即融资商进行还款的账号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *监管关系ID
     */
    public String getAccountSupervisionId() {
        return accountSupervisionId;
    }

    /**
     *监管关系ID
     */
    public void setAccountSupervisionId(String accountSupervisionId) {
        this.accountSupervisionId = accountSupervisionId == null ? null : accountSupervisionId.trim();
    }

    /**
     *合同生效日
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     *合同生效日
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : effectiveDate.trim();
    }

    /**
     *合同到期日
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     *合同到期日
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    /**
     *
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     *
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        AssetContract other = (AssetContract) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getMediaId() == null ? other.getMediaId() == null : this.getMediaId().equals(other.getMediaId()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFileSuffix() == null ? other.getFileSuffix() == null : this.getFileSuffix().equals(other.getFileSuffix()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getAccountSupervisionId() == null ? other.getAccountSupervisionId() == null : this.getAccountSupervisionId().equals(other.getAccountSupervisionId()))
            && (this.getEffectiveDate() == null ? other.getEffectiveDate() == null : this.getEffectiveDate().equals(other.getEffectiveDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getMediaId() == null) ? 0 : getMediaId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileSuffix() == null) ? 0 : getFileSuffix().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getAccountSupervisionId() == null) ? 0 : getAccountSupervisionId().hashCode());
        result = prime * result + ((getEffectiveDate() == null) ? 0 : getEffectiveDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}