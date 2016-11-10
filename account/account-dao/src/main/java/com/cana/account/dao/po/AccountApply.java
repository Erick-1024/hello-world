/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.account.dao.po;

import java.io.Serializable;
import java.util.Date;

public class AccountApply implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *用户名
    */
    private String username;

    /**
    *申请状态（未审核、审核未通过、审核已通过）
    */
    private String applyStatus;

    /**
    *客户类型（因主动开户不再检查资料有效期，因此只有代开户才会有申请，所以此字段只会是融资商类型）
    */
    private String userType;

    /**
    *代开户的账户类型
    */
    private String accountType;

    /**
    *监管账户所属用户类型：保理商、融资商
    */
    private String supervisorType;

    /**
    *企业id
    */
    private String companyId;

    /**
    *企业名称
    */
    private String companyName;

    /**
    *代办企业id
    */
    private String agentCompanyId;

    /**
    *代办企业名称
    */
    private String agentCompanyName;

    /**
    *申请账户数量，若buyer_names值不为空，则账户数量以buyer_names为准
    */
    private Integer accountNumber;

    /**
    *买方企业名称列表，以英文分号分隔，例如：名称1;名称2;名称3
    */
    private String buyerNames;

    /**
    *当监管账户为录入时的账户ID
    */
    private String supervisionAccountId;

    /**
    *联系人
    */
    private String contactName;

    /**
    *职称
    */
    private String contactJobTitle;

    /**
    *联系人电话
    */
    private String contactTel;

    /**
    *邮箱
    */
    private String contactMail;

    /**
    *联系人身份证正面Id
    */
    private String contactIdentityCardFrontMediaId;

    /**
    *联系人身份证背面Id
    */
    private String contactIdentityCardBackMediaId;

    /**
    *企业授权书
    */
    private String authorizationLetterId;

    /**
    *组织机构代码
    */
    private String organizationCode;

    /**
    *组织机构代码证文件Id
    */
    private String organizationCodeCertificateMediaId;

    /**
    *营业执照号码
    */
    private String businessLicenceCode;

    /**
    *营业执照扫描件Id
    */
    private String businessLicenceMediaId;

    /**
    *企业法人身份证正面id
    */
    private String legalPersonIdentityCardFrontMediaId;

    /**
    *企业法人身份证背面id
    */
    private String legalPersonIdentityCardBackMediaId;

    /**
    *税务登记证号码
    */
    private String taxRegistrationCertificateCode;

    /**
    *税务登记证id
    */
    private String taxRegistrationCertificateMediaId;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *更新时间
    */
    private Date updateTime;

    /**
    *是否把监管账户设为默认还款账户
    */
    private Boolean isDefaultRepayment;

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
    *用户名
    */
    public String getUsername() {
        return username;
    }

    /**
    *用户名
    */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
    *申请状态（未审核、审核未通过、审核已通过）
    */
    public String getApplyStatus() {
        return applyStatus;
    }

    /**
    *申请状态（未审核、审核未通过、审核已通过）
    */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus == null ? null : applyStatus.trim();
    }

    /**
    *客户类型（因主动开户不再检查资料有效期，因此只有代开户才会有申请，所以此字段只会是融资商类型）
    */
    public String getUserType() {
        return userType;
    }

    /**
    *客户类型（因主动开户不再检查资料有效期，因此只有代开户才会有申请，所以此字段只会是融资商类型）
    */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
    *代开户的账户类型
    */
    public String getAccountType() {
        return accountType;
    }

    /**
    *代开户的账户类型
    */
    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    /**
    *监管账户所属用户类型：保理商、融资商
    */
    public String getSupervisorType() {
        return supervisorType;
    }

    /**
    *监管账户所属用户类型：保理商、融资商
    */
    public void setSupervisorType(String supervisorType) {
        this.supervisorType = supervisorType == null ? null : supervisorType.trim();
    }

    /**
    *企业id
    */
    public String getCompanyId() {
        return companyId;
    }

    /**
    *企业id
    */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
    *企业名称
    */
    public String getCompanyName() {
        return companyName;
    }

    /**
    *企业名称
    */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
    *代办企业id
    */
    public String getAgentCompanyId() {
        return agentCompanyId;
    }

    /**
    *代办企业id
    */
    public void setAgentCompanyId(String agentCompanyId) {
        this.agentCompanyId = agentCompanyId == null ? null : agentCompanyId.trim();
    }

    /**
    *代办企业名称
    */
    public String getAgentCompanyName() {
        return agentCompanyName;
    }

    /**
    *代办企业名称
    */
    public void setAgentCompanyName(String agentCompanyName) {
        this.agentCompanyName = agentCompanyName == null ? null : agentCompanyName.trim();
    }

    /**
    *申请账户数量，若buyer_names值不为空，则账户数量以buyer_names为准
    */
    public Integer getAccountNumber() {
        return accountNumber;
    }

    /**
    *申请账户数量，若buyer_names值不为空，则账户数量以buyer_names为准
    */
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
    *买方企业名称列表，以英文分号分隔，例如：名称1;名称2;名称3
    */
    public String getBuyerNames() {
        return buyerNames;
    }

    /**
    *买方企业名称列表，以英文分号分隔，例如：名称1;名称2;名称3
    */
    public void setBuyerNames(String buyerNames) {
        this.buyerNames = buyerNames == null ? null : buyerNames.trim();
    }

    /**
    *当监管账户为录入时的账户ID
    */
    public String getSupervisionAccountId() {
        return supervisionAccountId;
    }

    /**
    *当监管账户为录入时的账户ID
    */
    public void setSupervisionAccountId(String supervisionAccountId) {
        this.supervisionAccountId = supervisionAccountId == null ? null : supervisionAccountId.trim();
    }

    /**
    *联系人
    */
    public String getContactName() {
        return contactName;
    }

    /**
    *联系人
    */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
    *职称
    */
    public String getContactJobTitle() {
        return contactJobTitle;
    }

    /**
    *职称
    */
    public void setContactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle == null ? null : contactJobTitle.trim();
    }

    /**
    *联系人电话
    */
    public String getContactTel() {
        return contactTel;
    }

    /**
    *联系人电话
    */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    /**
    *邮箱
    */
    public String getContactMail() {
        return contactMail;
    }

    /**
    *邮箱
    */
    public void setContactMail(String contactMail) {
        this.contactMail = contactMail == null ? null : contactMail.trim();
    }

    /**
    *联系人身份证正面Id
    */
    public String getContactIdentityCardFrontMediaId() {
        return contactIdentityCardFrontMediaId;
    }

    /**
    *联系人身份证正面Id
    */
    public void setContactIdentityCardFrontMediaId(String contactIdentityCardFrontMediaId) {
        this.contactIdentityCardFrontMediaId = contactIdentityCardFrontMediaId == null ? null : contactIdentityCardFrontMediaId.trim();
    }

    /**
    *联系人身份证背面Id
    */
    public String getContactIdentityCardBackMediaId() {
        return contactIdentityCardBackMediaId;
    }

    /**
    *联系人身份证背面Id
    */
    public void setContactIdentityCardBackMediaId(String contactIdentityCardBackMediaId) {
        this.contactIdentityCardBackMediaId = contactIdentityCardBackMediaId == null ? null : contactIdentityCardBackMediaId.trim();
    }

    /**
    *企业授权书
    */
    public String getAuthorizationLetterId() {
        return authorizationLetterId;
    }

    /**
    *企业授权书
    */
    public void setAuthorizationLetterId(String authorizationLetterId) {
        this.authorizationLetterId = authorizationLetterId == null ? null : authorizationLetterId.trim();
    }

    /**
    *组织机构代码
    */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
    *组织机构代码
    */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    /**
    *组织机构代码证文件Id
    */
    public String getOrganizationCodeCertificateMediaId() {
        return organizationCodeCertificateMediaId;
    }

    /**
    *组织机构代码证文件Id
    */
    public void setOrganizationCodeCertificateMediaId(String organizationCodeCertificateMediaId) {
        this.organizationCodeCertificateMediaId = organizationCodeCertificateMediaId == null ? null : organizationCodeCertificateMediaId.trim();
    }

    /**
    *营业执照号码
    */
    public String getBusinessLicenceCode() {
        return businessLicenceCode;
    }

    /**
    *营业执照号码
    */
    public void setBusinessLicenceCode(String businessLicenceCode) {
        this.businessLicenceCode = businessLicenceCode == null ? null : businessLicenceCode.trim();
    }

    /**
    *营业执照扫描件Id
    */
    public String getBusinessLicenceMediaId() {
        return businessLicenceMediaId;
    }

    /**
    *营业执照扫描件Id
    */
    public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
        this.businessLicenceMediaId = businessLicenceMediaId == null ? null : businessLicenceMediaId.trim();
    }

    /**
    *企业法人身份证正面id
    */
    public String getLegalPersonIdentityCardFrontMediaId() {
        return legalPersonIdentityCardFrontMediaId;
    }

    /**
    *企业法人身份证正面id
    */
    public void setLegalPersonIdentityCardFrontMediaId(String legalPersonIdentityCardFrontMediaId) {
        this.legalPersonIdentityCardFrontMediaId = legalPersonIdentityCardFrontMediaId == null ? null : legalPersonIdentityCardFrontMediaId.trim();
    }

    /**
    *企业法人身份证背面id
    */
    public String getLegalPersonIdentityCardBackMediaId() {
        return legalPersonIdentityCardBackMediaId;
    }

    /**
    *企业法人身份证背面id
    */
    public void setLegalPersonIdentityCardBackMediaId(String legalPersonIdentityCardBackMediaId) {
        this.legalPersonIdentityCardBackMediaId = legalPersonIdentityCardBackMediaId == null ? null : legalPersonIdentityCardBackMediaId.trim();
    }

    /**
    *税务登记证号码
    */
    public String getTaxRegistrationCertificateCode() {
        return taxRegistrationCertificateCode;
    }

    /**
    *税务登记证号码
    */
    public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
        this.taxRegistrationCertificateCode = taxRegistrationCertificateCode == null ? null : taxRegistrationCertificateCode.trim();
    }

    /**
    *税务登记证id
    */
    public String getTaxRegistrationCertificateMediaId() {
        return taxRegistrationCertificateMediaId;
    }

    /**
    *税务登记证id
    */
    public void setTaxRegistrationCertificateMediaId(String taxRegistrationCertificateMediaId) {
        this.taxRegistrationCertificateMediaId = taxRegistrationCertificateMediaId == null ? null : taxRegistrationCertificateMediaId.trim();
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
    *是否把监管账户设为默认还款账户
    */
    public Boolean getIsDefaultRepayment() {
        return isDefaultRepayment;
    }

    /**
    *是否把监管账户设为默认还款账户
    */
    public void setIsDefaultRepayment(Boolean isDefaultRepayment) {
        this.isDefaultRepayment = isDefaultRepayment;
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
        AccountApply other = (AccountApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getApplyStatus() == null ? other.getApplyStatus() == null : this.getApplyStatus().equals(other.getApplyStatus()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getAccountType() == null ? other.getAccountType() == null : this.getAccountType().equals(other.getAccountType()))
            && (this.getSupervisorType() == null ? other.getSupervisorType() == null : this.getSupervisorType().equals(other.getSupervisorType()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getAgentCompanyId() == null ? other.getAgentCompanyId() == null : this.getAgentCompanyId().equals(other.getAgentCompanyId()))
            && (this.getAgentCompanyName() == null ? other.getAgentCompanyName() == null : this.getAgentCompanyName().equals(other.getAgentCompanyName()))
            && (this.getAccountNumber() == null ? other.getAccountNumber() == null : this.getAccountNumber().equals(other.getAccountNumber()))
            && (this.getBuyerNames() == null ? other.getBuyerNames() == null : this.getBuyerNames().equals(other.getBuyerNames()))
            && (this.getSupervisionAccountId() == null ? other.getSupervisionAccountId() == null : this.getSupervisionAccountId().equals(other.getSupervisionAccountId()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactJobTitle() == null ? other.getContactJobTitle() == null : this.getContactJobTitle().equals(other.getContactJobTitle()))
            && (this.getContactTel() == null ? other.getContactTel() == null : this.getContactTel().equals(other.getContactTel()))
            && (this.getContactMail() == null ? other.getContactMail() == null : this.getContactMail().equals(other.getContactMail()))
            && (this.getContactIdentityCardFrontMediaId() == null ? other.getContactIdentityCardFrontMediaId() == null : this.getContactIdentityCardFrontMediaId().equals(other.getContactIdentityCardFrontMediaId()))
            && (this.getContactIdentityCardBackMediaId() == null ? other.getContactIdentityCardBackMediaId() == null : this.getContactIdentityCardBackMediaId().equals(other.getContactIdentityCardBackMediaId()))
            && (this.getAuthorizationLetterId() == null ? other.getAuthorizationLetterId() == null : this.getAuthorizationLetterId().equals(other.getAuthorizationLetterId()))
            && (this.getOrganizationCode() == null ? other.getOrganizationCode() == null : this.getOrganizationCode().equals(other.getOrganizationCode()))
            && (this.getOrganizationCodeCertificateMediaId() == null ? other.getOrganizationCodeCertificateMediaId() == null : this.getOrganizationCodeCertificateMediaId().equals(other.getOrganizationCodeCertificateMediaId()))
            && (this.getBusinessLicenceCode() == null ? other.getBusinessLicenceCode() == null : this.getBusinessLicenceCode().equals(other.getBusinessLicenceCode()))
            && (this.getBusinessLicenceMediaId() == null ? other.getBusinessLicenceMediaId() == null : this.getBusinessLicenceMediaId().equals(other.getBusinessLicenceMediaId()))
            && (this.getLegalPersonIdentityCardFrontMediaId() == null ? other.getLegalPersonIdentityCardFrontMediaId() == null : this.getLegalPersonIdentityCardFrontMediaId().equals(other.getLegalPersonIdentityCardFrontMediaId()))
            && (this.getLegalPersonIdentityCardBackMediaId() == null ? other.getLegalPersonIdentityCardBackMediaId() == null : this.getLegalPersonIdentityCardBackMediaId().equals(other.getLegalPersonIdentityCardBackMediaId()))
            && (this.getTaxRegistrationCertificateCode() == null ? other.getTaxRegistrationCertificateCode() == null : this.getTaxRegistrationCertificateCode().equals(other.getTaxRegistrationCertificateCode()))
            && (this.getTaxRegistrationCertificateMediaId() == null ? other.getTaxRegistrationCertificateMediaId() == null : this.getTaxRegistrationCertificateMediaId().equals(other.getTaxRegistrationCertificateMediaId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDefaultRepayment() == null ? other.getIsDefaultRepayment() == null : this.getIsDefaultRepayment().equals(other.getIsDefaultRepayment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getApplyStatus() == null) ? 0 : getApplyStatus().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getAccountType() == null) ? 0 : getAccountType().hashCode());
        result = prime * result + ((getSupervisorType() == null) ? 0 : getSupervisorType().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getAgentCompanyId() == null) ? 0 : getAgentCompanyId().hashCode());
        result = prime * result + ((getAgentCompanyName() == null) ? 0 : getAgentCompanyName().hashCode());
        result = prime * result + ((getAccountNumber() == null) ? 0 : getAccountNumber().hashCode());
        result = prime * result + ((getBuyerNames() == null) ? 0 : getBuyerNames().hashCode());
        result = prime * result + ((getSupervisionAccountId() == null) ? 0 : getSupervisionAccountId().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactJobTitle() == null) ? 0 : getContactJobTitle().hashCode());
        result = prime * result + ((getContactTel() == null) ? 0 : getContactTel().hashCode());
        result = prime * result + ((getContactMail() == null) ? 0 : getContactMail().hashCode());
        result = prime * result + ((getContactIdentityCardFrontMediaId() == null) ? 0 : getContactIdentityCardFrontMediaId().hashCode());
        result = prime * result + ((getContactIdentityCardBackMediaId() == null) ? 0 : getContactIdentityCardBackMediaId().hashCode());
        result = prime * result + ((getAuthorizationLetterId() == null) ? 0 : getAuthorizationLetterId().hashCode());
        result = prime * result + ((getOrganizationCode() == null) ? 0 : getOrganizationCode().hashCode());
        result = prime * result + ((getOrganizationCodeCertificateMediaId() == null) ? 0 : getOrganizationCodeCertificateMediaId().hashCode());
        result = prime * result + ((getBusinessLicenceCode() == null) ? 0 : getBusinessLicenceCode().hashCode());
        result = prime * result + ((getBusinessLicenceMediaId() == null) ? 0 : getBusinessLicenceMediaId().hashCode());
        result = prime * result + ((getLegalPersonIdentityCardFrontMediaId() == null) ? 0 : getLegalPersonIdentityCardFrontMediaId().hashCode());
        result = prime * result + ((getLegalPersonIdentityCardBackMediaId() == null) ? 0 : getLegalPersonIdentityCardBackMediaId().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateCode() == null) ? 0 : getTaxRegistrationCertificateCode().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateMediaId() == null) ? 0 : getTaxRegistrationCertificateMediaId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDefaultRepayment() == null) ? 0 : getIsDefaultRepayment().hashCode());
        return result;
    }
}