/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class ProjectFactorDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7608051877877565592L;

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
     *合作状态，正常、暂停
     */
    private String status;
    
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
     *合作状态，正常、暂停
     */
    public String getStatus() {
        return status;
    }

    /**
     *合作状态，正常、暂停
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}