/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.member.dao.po;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *主账号Id
     */
    private String masterId;

    /**
     *用户名
     */
    private String username;

    /**
     *密码
     */
    private String password;

    /**
     *公司名称
     */
    private String companyName;

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
     *联系人
     */
    private String contactName;

    /**
     *联系人电话
     */
    private String contactTel;

    /**
     *邮箱
     */
    private String contactMail;

    /**
     *用户类型（保理商，融资商，CANA）普通员工没有此值
     */
    private String userType;

    /**
     *用户状态
     */
    private String userStatus;

    /**
     *职称
     */
    private String jobTitle;

    /**
     *用户所属角色
     */
    private String roleId;

    /**
     *随机字符串，用于记住登录功能
     */
    private String token;

    /**
     *是否已登陆
     */
    private Boolean signedin;

    /**
     *最后登录时间
     */
    private Date signinTime;

    /**
     *最后登陆Ip
     */
    private String signinIp;

    /**
     *注册时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;

    /**
     *审核通过时间（废弃）
     */
    private Date auditTime;

    /**
     *激活安全码
     */
    private String securityCode;

    /**
     *安全码到期时间
     */
    private Date securityCodeExpirationTime;

    /**
     *申请企业
     */
    private String agentCompany;

    /**
     *联系人身份证正面Id
     */
    private String contactIdentityCardFrontMediaId;

    /**
     *联系人身份证反面Id
     */
    private String contactIdentityCardBackMediaId;

    /**
     *法人
     */
    private String legalPerson;

    /**
     *企业法人身份证正面id
     */
    private String legalPersonIdentityCardFrontMediaId;

    /**
     *企业法人身份证反面id
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
     *
     */
    private String jobNo;

    /**
     *
     */
    private String realName;

    /**
     *支付密码
     */
    private String payPassword;

    /**
     *保理商引导状态
     */
    private String guideStatus;

    /**
     *员工电话
     */
    private String employeeTel;

    /**
     *员工邮件
     */
    private String employeeMail;

    /**
     *员工职称
     */
    private String employeeJobTitle;

    /**
     *ca证书dn
     */
    private String certSubjectDn;

    /**
     *个人用户的身份证
     */
    private String identityCardNo;

    /**
     *个性权限
     */
    private String permissions;

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
     *主账号Id
     */
    public String getMasterId() {
        return masterId;
    }

    /**
     *主账号Id
     */
    public void setMasterId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
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
     *密码
     */
    public String getPassword() {
        return password;
    }

    /**
     *密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     *公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
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
     *用户类型（保理商，融资商，CANA）普通员工没有此值
     */
    public String getUserType() {
        return userType;
    }

    /**
     *用户类型（保理商，融资商，CANA）普通员工没有此值
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     *用户状态
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     *用户状态
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    /**
     *职称
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     *职称
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle == null ? null : jobTitle.trim();
    }

    /**
     *用户所属角色
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     *用户所属角色
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     *随机字符串，用于记住登录功能
     */
    public String getToken() {
        return token;
    }

    /**
     *随机字符串，用于记住登录功能
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     *是否已登陆
     */
    public Boolean getSignedin() {
        return signedin;
    }

    /**
     *是否已登陆
     */
    public void setSignedin(Boolean signedin) {
        this.signedin = signedin;
    }

    /**
     *最后登录时间
     */
    public Date getSigninTime() {
        return signinTime;
    }

    /**
     *最后登录时间
     */
    public void setSigninTime(Date signinTime) {
        this.signinTime = signinTime;
    }

    /**
     *最后登陆Ip
     */
    public String getSigninIp() {
        return signinIp;
    }

    /**
     *最后登陆Ip
     */
    public void setSigninIp(String signinIp) {
        this.signinIp = signinIp == null ? null : signinIp.trim();
    }

    /**
     *注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *更新时间
     */
    public Date getUpateTime() {
        return upateTime;
    }

    /**
     *更新时间
     */
    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
    }

    /**
     *审核通过时间（废弃）
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     *审核通过时间（废弃）
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     *激活安全码
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     *激活安全码
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode == null ? null : securityCode.trim();
    }

    /**
     *安全码到期时间
     */
    public Date getSecurityCodeExpirationTime() {
        return securityCodeExpirationTime;
    }

    /**
     *安全码到期时间
     */
    public void setSecurityCodeExpirationTime(Date securityCodeExpirationTime) {
        this.securityCodeExpirationTime = securityCodeExpirationTime;
    }

    /**
     *申请企业
     */
    public String getAgentCompany() {
        return agentCompany;
    }

    /**
     *申请企业
     */
    public void setAgentCompany(String agentCompany) {
        this.agentCompany = agentCompany == null ? null : agentCompany.trim();
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
     *联系人身份证反面Id
     */
    public String getContactIdentityCardBackMediaId() {
        return contactIdentityCardBackMediaId;
    }

    /**
     *联系人身份证反面Id
     */
    public void setContactIdentityCardBackMediaId(String contactIdentityCardBackMediaId) {
        this.contactIdentityCardBackMediaId = contactIdentityCardBackMediaId == null ? null : contactIdentityCardBackMediaId.trim();
    }

    /**
     *法人
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     *法人
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
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
     *企业法人身份证反面id
     */
    public String getLegalPersonIdentityCardBackMediaId() {
        return legalPersonIdentityCardBackMediaId;
    }

    /**
     *企业法人身份证反面id
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
     *
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     *
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo == null ? null : jobNo.trim();
    }

    /**
     *
     */
    public String getRealName() {
        return realName;
    }

    /**
     *
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     *支付密码
     */
    public String getPayPassword() {
        return payPassword;
    }

    /**
     *支付密码
     */
    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    /**
     *保理商引导状态
     */
    public String getGuideStatus() {
        return guideStatus;
    }

    /**
     *保理商引导状态
     */
    public void setGuideStatus(String guideStatus) {
        this.guideStatus = guideStatus == null ? null : guideStatus.trim();
    }

    /**
     *员工电话
     */
    public String getEmployeeTel() {
        return employeeTel;
    }

    /**
     *员工电话
     */
    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel == null ? null : employeeTel.trim();
    }

    /**
     *员工邮件
     */
    public String getEmployeeMail() {
        return employeeMail;
    }

    /**
     *员工邮件
     */
    public void setEmployeeMail(String employeeMail) {
        this.employeeMail = employeeMail == null ? null : employeeMail.trim();
    }

    /**
     *员工职称
     */
    public String getEmployeeJobTitle() {
        return employeeJobTitle;
    }

    /**
     *员工职称
     */
    public void setEmployeeJobTitle(String employeeJobTitle) {
        this.employeeJobTitle = employeeJobTitle == null ? null : employeeJobTitle.trim();
    }

    /**
     *ca证书dn
     */
    public String getCertSubjectDn() {
        return certSubjectDn;
    }

    /**
     *ca证书dn
     */
    public void setCertSubjectDn(String certSubjectDn) {
        this.certSubjectDn = certSubjectDn == null ? null : certSubjectDn.trim();
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
     *个性权限
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     *个性权限
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMasterId() == null ? other.getMasterId() == null : this.getMasterId().equals(other.getMasterId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getOrganizationCode() == null ? other.getOrganizationCode() == null : this.getOrganizationCode().equals(other.getOrganizationCode()))
            && (this.getOrganizationCodeCertificateMediaId() == null ? other.getOrganizationCodeCertificateMediaId() == null : this.getOrganizationCodeCertificateMediaId().equals(other.getOrganizationCodeCertificateMediaId()))
            && (this.getBusinessLicenceCode() == null ? other.getBusinessLicenceCode() == null : this.getBusinessLicenceCode().equals(other.getBusinessLicenceCode()))
            && (this.getBusinessLicenceMediaId() == null ? other.getBusinessLicenceMediaId() == null : this.getBusinessLicenceMediaId().equals(other.getBusinessLicenceMediaId()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactTel() == null ? other.getContactTel() == null : this.getContactTel().equals(other.getContactTel()))
            && (this.getContactMail() == null ? other.getContactMail() == null : this.getContactMail().equals(other.getContactMail()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getJobTitle() == null ? other.getJobTitle() == null : this.getJobTitle().equals(other.getJobTitle()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getSignedin() == null ? other.getSignedin() == null : this.getSignedin().equals(other.getSignedin()))
            && (this.getSigninTime() == null ? other.getSigninTime() == null : this.getSigninTime().equals(other.getSigninTime()))
            && (this.getSigninIp() == null ? other.getSigninIp() == null : this.getSigninIp().equals(other.getSigninIp()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()))
            && (this.getAuditTime() == null ? other.getAuditTime() == null : this.getAuditTime().equals(other.getAuditTime()))
            && (this.getSecurityCode() == null ? other.getSecurityCode() == null : this.getSecurityCode().equals(other.getSecurityCode()))
            && (this.getSecurityCodeExpirationTime() == null ? other.getSecurityCodeExpirationTime() == null : this.getSecurityCodeExpirationTime().equals(other.getSecurityCodeExpirationTime()))
            && (this.getAgentCompany() == null ? other.getAgentCompany() == null : this.getAgentCompany().equals(other.getAgentCompany()))
            && (this.getContactIdentityCardFrontMediaId() == null ? other.getContactIdentityCardFrontMediaId() == null : this.getContactIdentityCardFrontMediaId().equals(other.getContactIdentityCardFrontMediaId()))
            && (this.getContactIdentityCardBackMediaId() == null ? other.getContactIdentityCardBackMediaId() == null : this.getContactIdentityCardBackMediaId().equals(other.getContactIdentityCardBackMediaId()))
            && (this.getLegalPerson() == null ? other.getLegalPerson() == null : this.getLegalPerson().equals(other.getLegalPerson()))
            && (this.getLegalPersonIdentityCardFrontMediaId() == null ? other.getLegalPersonIdentityCardFrontMediaId() == null : this.getLegalPersonIdentityCardFrontMediaId().equals(other.getLegalPersonIdentityCardFrontMediaId()))
            && (this.getLegalPersonIdentityCardBackMediaId() == null ? other.getLegalPersonIdentityCardBackMediaId() == null : this.getLegalPersonIdentityCardBackMediaId().equals(other.getLegalPersonIdentityCardBackMediaId()))
            && (this.getTaxRegistrationCertificateCode() == null ? other.getTaxRegistrationCertificateCode() == null : this.getTaxRegistrationCertificateCode().equals(other.getTaxRegistrationCertificateCode()))
            && (this.getTaxRegistrationCertificateMediaId() == null ? other.getTaxRegistrationCertificateMediaId() == null : this.getTaxRegistrationCertificateMediaId().equals(other.getTaxRegistrationCertificateMediaId()))
            && (this.getJobNo() == null ? other.getJobNo() == null : this.getJobNo().equals(other.getJobNo()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getPayPassword() == null ? other.getPayPassword() == null : this.getPayPassword().equals(other.getPayPassword()))
            && (this.getGuideStatus() == null ? other.getGuideStatus() == null : this.getGuideStatus().equals(other.getGuideStatus()))
            && (this.getEmployeeTel() == null ? other.getEmployeeTel() == null : this.getEmployeeTel().equals(other.getEmployeeTel()))
            && (this.getEmployeeMail() == null ? other.getEmployeeMail() == null : this.getEmployeeMail().equals(other.getEmployeeMail()))
            && (this.getEmployeeJobTitle() == null ? other.getEmployeeJobTitle() == null : this.getEmployeeJobTitle().equals(other.getEmployeeJobTitle()))
            && (this.getCertSubjectDn() == null ? other.getCertSubjectDn() == null : this.getCertSubjectDn().equals(other.getCertSubjectDn()))
            && (this.getIdentityCardNo() == null ? other.getIdentityCardNo() == null : this.getIdentityCardNo().equals(other.getIdentityCardNo()))
            && (this.getPermissions() == null ? other.getPermissions() == null : this.getPermissions().equals(other.getPermissions()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMasterId() == null) ? 0 : getMasterId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getOrganizationCode() == null) ? 0 : getOrganizationCode().hashCode());
        result = prime * result + ((getOrganizationCodeCertificateMediaId() == null) ? 0 : getOrganizationCodeCertificateMediaId().hashCode());
        result = prime * result + ((getBusinessLicenceCode() == null) ? 0 : getBusinessLicenceCode().hashCode());
        result = prime * result + ((getBusinessLicenceMediaId() == null) ? 0 : getBusinessLicenceMediaId().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactTel() == null) ? 0 : getContactTel().hashCode());
        result = prime * result + ((getContactMail() == null) ? 0 : getContactMail().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getJobTitle() == null) ? 0 : getJobTitle().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getSignedin() == null) ? 0 : getSignedin().hashCode());
        result = prime * result + ((getSigninTime() == null) ? 0 : getSigninTime().hashCode());
        result = prime * result + ((getSigninIp() == null) ? 0 : getSigninIp().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        result = prime * result + ((getAuditTime() == null) ? 0 : getAuditTime().hashCode());
        result = prime * result + ((getSecurityCode() == null) ? 0 : getSecurityCode().hashCode());
        result = prime * result + ((getSecurityCodeExpirationTime() == null) ? 0 : getSecurityCodeExpirationTime().hashCode());
        result = prime * result + ((getAgentCompany() == null) ? 0 : getAgentCompany().hashCode());
        result = prime * result + ((getContactIdentityCardFrontMediaId() == null) ? 0 : getContactIdentityCardFrontMediaId().hashCode());
        result = prime * result + ((getContactIdentityCardBackMediaId() == null) ? 0 : getContactIdentityCardBackMediaId().hashCode());
        result = prime * result + ((getLegalPerson() == null) ? 0 : getLegalPerson().hashCode());
        result = prime * result + ((getLegalPersonIdentityCardFrontMediaId() == null) ? 0 : getLegalPersonIdentityCardFrontMediaId().hashCode());
        result = prime * result + ((getLegalPersonIdentityCardBackMediaId() == null) ? 0 : getLegalPersonIdentityCardBackMediaId().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateCode() == null) ? 0 : getTaxRegistrationCertificateCode().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateMediaId() == null) ? 0 : getTaxRegistrationCertificateMediaId().hashCode());
        result = prime * result + ((getJobNo() == null) ? 0 : getJobNo().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getPayPassword() == null) ? 0 : getPayPassword().hashCode());
        result = prime * result + ((getGuideStatus() == null) ? 0 : getGuideStatus().hashCode());
        result = prime * result + ((getEmployeeTel() == null) ? 0 : getEmployeeTel().hashCode());
        result = prime * result + ((getEmployeeMail() == null) ? 0 : getEmployeeMail().hashCode());
        result = prime * result + ((getEmployeeJobTitle() == null) ? 0 : getEmployeeJobTitle().hashCode());
        result = prime * result + ((getCertSubjectDn() == null) ? 0 : getCertSubjectDn().hashCode());
        result = prime * result + ((getIdentityCardNo() == null) ? 0 : getIdentityCardNo().hashCode());
        result = prime * result + ((getPermissions() == null) ? 0 : getPermissions().hashCode());
        return result;
    }
}