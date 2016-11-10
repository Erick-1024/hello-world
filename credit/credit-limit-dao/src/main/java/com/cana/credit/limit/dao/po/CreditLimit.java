/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.credit.limit.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CreditLimit implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *Cana用户的ID
     */
    private String memberId;

    /**
     *关联的项目ID
     */
    private String projectId;

    /**
     *客户名称（企业的全称）
     */
    private String companyName;

    /**
     *个人用户的身份证
     */
    private String identityCardNo;

    /**
     *详见@see CreditMode ，授信方式（SYNTHETICAL:综合授信、SINGLE:单笔授信）
     */
    private String creditMode;

    /**
     *总额度，精确到分
     */
    private Long totalLimit;

    /**
     *已使用额度，精确到分
     */
    private Long usedLimit;

    /**
     *额度生效日
     */
    private Date effectiveDate;

    /**
     *详见@see CreditLimitStatus ，额度状态（NORMAL:正常、FREEZE:冻结、TEMPORARY_FREEZE:临时冻结、DISABLED:停用、INACTIVE:未激活）
     */
    private String status;

    /**
     *外部客户ID，目前真旅项目使用
     */
    private String outCustomerId;

    /**
     *外部客户名称，目前真旅项目使用
     */
    private String outCustomerName;

    /**
     *手机号
     */
    private String mobileNo;

    /**
     *更新时间
     */
    private Date updateTime;

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
     *Cana用户的ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     *Cana用户的ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     *关联的项目ID
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     *关联的项目ID
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     *客户名称（企业的全称）
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *客户名称（企业的全称）
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     *个人用户的身份证
     */
    public String getIdentityCardNo() {
        return identityCardNo;
    }

    /**
     *个人用户的身份证
     */
    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo == null ? null : identityCardNo.trim();
    }

    /**
     *详见@see CreditMode ，授信方式（SYNTHETICAL:综合授信、SINGLE:单笔授信）
     */
    public String getCreditMode() {
        return creditMode;
    }

    /**
     *详见@see CreditMode ，授信方式（SYNTHETICAL:综合授信、SINGLE:单笔授信）
     */
    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode == null ? null : creditMode.trim();
    }

    /**
     *总额度，精确到分
     */
    public Long getTotalLimit() {
        return totalLimit;
    }

    /**
     *总额度，精确到分
     */
    public void setTotalLimit(Long totalLimit) {
        this.totalLimit = totalLimit;
    }

    /**
     *已使用额度，精确到分
     */
    public Long getUsedLimit() {
        return usedLimit;
    }

    /**
     *已使用额度，精确到分
     */
    public void setUsedLimit(Long usedLimit) {
        this.usedLimit = usedLimit;
    }

    /**
     *额度生效日
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     *额度生效日
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     *详见@see CreditLimitStatus ，额度状态（NORMAL:正常、FREEZE:冻结、TEMPORARY_FREEZE:临时冻结、DISABLED:停用、INACTIVE:未激活）
     */
    public String getStatus() {
        return status;
    }

    /**
     *详见@see CreditLimitStatus ，额度状态（NORMAL:正常、FREEZE:冻结、TEMPORARY_FREEZE:临时冻结、DISABLED:停用、INACTIVE:未激活）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *外部客户ID，目前真旅项目使用
     */
    public String getOutCustomerId() {
        return outCustomerId;
    }

    /**
     *外部客户ID，目前真旅项目使用
     */
    public void setOutCustomerId(String outCustomerId) {
        this.outCustomerId = outCustomerId == null ? null : outCustomerId.trim();
    }

    /**
     *外部客户名称，目前真旅项目使用
     */
    public String getOutCustomerName() {
        return outCustomerName;
    }

    /**
     *外部客户名称，目前真旅项目使用
     */
    public void setOutCustomerName(String outCustomerName) {
        this.outCustomerName = outCustomerName == null ? null : outCustomerName.trim();
    }

    /**
     *手机号
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *手机号
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
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
        CreditLimit other = (CreditLimit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getIdentityCardNo() == null ? other.getIdentityCardNo() == null : this.getIdentityCardNo().equals(other.getIdentityCardNo()))
            && (this.getCreditMode() == null ? other.getCreditMode() == null : this.getCreditMode().equals(other.getCreditMode()))
            && (this.getTotalLimit() == null ? other.getTotalLimit() == null : this.getTotalLimit().equals(other.getTotalLimit()))
            && (this.getUsedLimit() == null ? other.getUsedLimit() == null : this.getUsedLimit().equals(other.getUsedLimit()))
            && (this.getEffectiveDate() == null ? other.getEffectiveDate() == null : this.getEffectiveDate().equals(other.getEffectiveDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOutCustomerId() == null ? other.getOutCustomerId() == null : this.getOutCustomerId().equals(other.getOutCustomerId()))
            && (this.getOutCustomerName() == null ? other.getOutCustomerName() == null : this.getOutCustomerName().equals(other.getOutCustomerName()))
            && (this.getMobileNo() == null ? other.getMobileNo() == null : this.getMobileNo().equals(other.getMobileNo()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getIdentityCardNo() == null) ? 0 : getIdentityCardNo().hashCode());
        result = prime * result + ((getCreditMode() == null) ? 0 : getCreditMode().hashCode());
        result = prime * result + ((getTotalLimit() == null) ? 0 : getTotalLimit().hashCode());
        result = prime * result + ((getUsedLimit() == null) ? 0 : getUsedLimit().hashCode());
        result = prime * result + ((getEffectiveDate() == null) ? 0 : getEffectiveDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOutCustomerId() == null) ? 0 : getOutCustomerId().hashCode());
        result = prime * result + ((getOutCustomerName() == null) ? 0 : getOutCustomerName().hashCode());
        result = prime * result + ((getMobileNo() == null) ? 0 : getMobileNo().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}