	package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.Map;

import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;

public class CustomerReviewDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1781583377839841986L;

	/**
    *主键
    */
    private String id;
    
	/**
    *用户名
    */
    private String username;
    
    /**
     *用户类型
     */
    private UserType userType;
    
    /**
    *公司名称
    */
    private String companyName;
    
    /**
    *营业执照号码
    */
    private String businessLicenceCode;
    
    /**
    *营业执照扫描件Id
    */
    private String businessLicenceMediaId;
    
    /**
    *组织机构代码
    */
    private String organizationCode;
    
    /**
    *组织机构代码证文件Id
    */
    private String organizationCodeCertificateMediaId;
    
    /**
     *联系人身份证正面Id
     */
    private String contactIdentityCardFrontMediaId;

    /**
     *联系人身份证背面Id
     */
    private String contactIdentityCardBackMediaId;

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
    *职称
    */
    private String jobTitle;
    
    /**
    *申请企业
    */
    private String agentCompany;
    
    /**
    *用户状态
     */
    private UserStatus userStatus;
    
    /**
     *审核结果
     */
    private boolean auditResult;
    
    /**
     *公司名称一致性
     */
    private boolean checkCompanyName;
    
    /**
     *组织机构代码证一致性
     */
    private boolean checkOrganizationCode;
    
    /**
     *营业执照一致性
     */
    private boolean checkBusinessLicenceCode;
    
    /**
     *税务登记证一致性
     */
    private boolean checkTaxRegistrationCertificateCode;
    
    /**
     *法人身份证一致性
     */
    private boolean checkLegalPersonIdentityCard;
    
    /**
     *其他一致性
     */
    private boolean checkOthers;
    
    /**
     *其他信息
     */
    private String auditMessage;

    /**
     *用户所属角色<roleId, roleName>
     */
    private Map<String, String> roleNames;

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public UserType getUserType() {
		return userType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getBusinessLicenceCode() {
		return businessLicenceCode;
	}

	public String getBusinessLicenceMediaId() {
		return businessLicenceMediaId;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getOrganizationCodeCertificateMediaId() {
		return organizationCodeCertificateMediaId;
	}

	public String getContactIdentityCardFrontMediaId() {
		return contactIdentityCardFrontMediaId;
	}

	public String getContactIdentityCardBackMediaId() {
		return contactIdentityCardBackMediaId;
	}

	public String getLegalPersonIdentityCardFrontMediaId() {
		return legalPersonIdentityCardFrontMediaId;
	}

	public String getLegalPersonIdentityCardBackMediaId() {
		return legalPersonIdentityCardBackMediaId;
	}

	public String getTaxRegistrationCertificateCode() {
		return taxRegistrationCertificateCode;
	}

	public String getTaxRegistrationCertificateMediaId() {
		return taxRegistrationCertificateMediaId;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public String getContactMail() {
		return contactMail;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getAgentCompany() {
		return agentCompany;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public boolean isAuditResult() {
		return auditResult;
	}

	public boolean isCheckCompanyName() {
		return checkCompanyName;
	}
	
	public boolean isCheckOrganizationCode() {
		return checkOrganizationCode;
	}

	public boolean isCheckBusinessLicenceCode() {
		return checkBusinessLicenceCode;
	}

	public boolean isCheckTaxRegistrationCertificateCode() {
		return checkTaxRegistrationCertificateCode;
	}

	public boolean isCheckLegalPersonIdentityCard() {
		return checkLegalPersonIdentityCard;
	}

	public void setCheckTaxRegistrationCertificateCode(boolean checkTaxRegistrationCertificateCode) {
		this.checkTaxRegistrationCertificateCode = checkTaxRegistrationCertificateCode;
	}

	public void setCheckLegalPersonIdentityCard(boolean checkLegalPersonIdentityCard) {
		this.checkLegalPersonIdentityCard = checkLegalPersonIdentityCard;
	}

	public boolean isCheckOthers() {
		return checkOthers;
	}

	public String getAuditMessage() {
		return auditMessage;
	}

	public Map<String, String> getRoleNames() {
		return roleNames;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setBusinessLicenceCode(String businessLicenceCode) {
		this.businessLicenceCode = businessLicenceCode;
	}

	public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
		this.businessLicenceMediaId = businessLicenceMediaId;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public void setOrganizationCodeCertificateMediaId(String organizationCodeCertificateMediaId) {
		this.organizationCodeCertificateMediaId = organizationCodeCertificateMediaId;
	}

	public void setContactIdentityCardFrontMediaId(String contactIdentityCardFrontMediaId) {
		this.contactIdentityCardFrontMediaId = contactIdentityCardFrontMediaId;
	}

	public void setContactIdentityCardBackMediaId(String contactIdentityCardBackMediaId) {
		this.contactIdentityCardBackMediaId = contactIdentityCardBackMediaId;
	}

	public void setLegalPersonIdentityCardFrontMediaId(String legalPersonIdentityCardFrontMediaId) {
		this.legalPersonIdentityCardFrontMediaId = legalPersonIdentityCardFrontMediaId;
	}

	public void setLegalPersonIdentityCardBackMediaId(String legalPersonIdentityCardBackMediaId) {
		this.legalPersonIdentityCardBackMediaId = legalPersonIdentityCardBackMediaId;
	}

	public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
		this.taxRegistrationCertificateCode = taxRegistrationCertificateCode;
	}

	public void setTaxRegistrationCertificateMediaId(String taxRegistrationCertificateMediaId) {
		this.taxRegistrationCertificateMediaId = taxRegistrationCertificateMediaId;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setAgentCompany(String agentCompany) {
		this.agentCompany = agentCompany;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public void setAuditResult(boolean auditResult) {
		this.auditResult = auditResult;
	}

	public void setCheckCompanyName(boolean checkCompanyName) {
		this.checkCompanyName = checkCompanyName;
	}
	
	public void setCheckOrganizationCode(boolean checkOrganizationCode) {
		this.checkOrganizationCode = checkOrganizationCode;
	}

	public void setCheckBusinessLicenceCode(boolean checkBusinessLicenceCode) {
		this.checkBusinessLicenceCode = checkBusinessLicenceCode;
	}

	public void setCheckOthers(boolean checkOthers) {
		this.checkOthers = checkOthers;
	}

	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}

	public void setRoleNames(Map<String, String> roleNames) {
		this.roleNames = roleNames;
	}
    
}
