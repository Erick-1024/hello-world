/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ProjectFactor implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *关联的项目ID
     */
    private String projectId;

    /**
     *资金方企业ID
     */
    private String companyId;

    /**
     *资金方企业名称
     */
    private String companyName;

    /**
     *企业组织机构代码
     */
    private String organizationCode;

    /**
     *企业营业执照号码
     */
    private String businessLicenceCode;

    /**
     *企业税务登记证号吗
     */
    private String taxRegistrationCertificateCode;

    /**
     *企业银行账号
     */
    private String accountNo;

    /**
     *合作状态，正常、暂停，@see ProjectFactorStatusEnum
     */
    private String status;

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
     *资金方企业ID
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     *资金方企业ID
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     *资金方企业名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *资金方企业名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     *企业组织机构代码
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     *企业组织机构代码
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    /**
     *企业营业执照号码
     */
    public String getBusinessLicenceCode() {
        return businessLicenceCode;
    }

    /**
     *企业营业执照号码
     */
    public void setBusinessLicenceCode(String businessLicenceCode) {
        this.businessLicenceCode = businessLicenceCode == null ? null : businessLicenceCode.trim();
    }

    /**
     *企业税务登记证号吗
     */
    public String getTaxRegistrationCertificateCode() {
        return taxRegistrationCertificateCode;
    }

    /**
     *企业税务登记证号吗
     */
    public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
        this.taxRegistrationCertificateCode = taxRegistrationCertificateCode == null ? null : taxRegistrationCertificateCode.trim();
    }

    /**
     *企业银行账号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *企业银行账号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *合作状态，正常、暂停，@see ProjectFactorStatusEnum
     */
    public String getStatus() {
        return status;
    }

    /**
     *合作状态，正常、暂停，@see ProjectFactorStatusEnum
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        ProjectFactor other = (ProjectFactor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getOrganizationCode() == null ? other.getOrganizationCode() == null : this.getOrganizationCode().equals(other.getOrganizationCode()))
            && (this.getBusinessLicenceCode() == null ? other.getBusinessLicenceCode() == null : this.getBusinessLicenceCode().equals(other.getBusinessLicenceCode()))
            && (this.getTaxRegistrationCertificateCode() == null ? other.getTaxRegistrationCertificateCode() == null : this.getTaxRegistrationCertificateCode().equals(other.getTaxRegistrationCertificateCode()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getOrganizationCode() == null) ? 0 : getOrganizationCode().hashCode());
        result = prime * result + ((getBusinessLicenceCode() == null) ? 0 : getBusinessLicenceCode().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateCode() == null) ? 0 : getTaxRegistrationCertificateCode().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}