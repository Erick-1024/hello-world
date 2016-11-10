package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cana.vbam.common.dto.CompanyInfoAuditDetail;
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;

public class CustomerDetailDTO extends CompanyInfoAuditDetail implements Serializable{

	private static final long serialVersionUID = -2073362540688279075L;

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
     *角色id
     */
    private List<RoleDTO> roleDTOList;

    private Set<UserGuideStatus> guideStatus;
    
    private String certSubjectDn;
    
    /**
     *个人用户的身份证
     */
    private String identityCardNo;
    private String legalPerson; // 法人

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBusinessLicenceCode() {
		return businessLicenceCode;
	}

	public void setBusinessLicenceCode(String businessLicenceCode) {
		this.businessLicenceCode = businessLicenceCode;
	}

	public String getBusinessLicenceMediaId() {
		return businessLicenceMediaId;
	}

	public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
		this.businessLicenceMediaId = businessLicenceMediaId;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationCodeCertificateMediaId() {
		return organizationCodeCertificateMediaId;
	}

	public void setOrganizationCodeCertificateMediaId(String organizationCodeCertificateMediaId) {
		this.organizationCodeCertificateMediaId = organizationCodeCertificateMediaId;
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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getAgentCompany() {
		return agentCompany;
	}

	public void setAgentCompany(String agentCompany) {
		this.agentCompany = agentCompany;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public boolean isAuditResult() {
		return auditResult;
	}

	public void setAuditResult(boolean auditResult) {
		this.auditResult = auditResult;
	}

	public List<RoleDTO> getRoleDTOList() {
		return roleDTOList;
	}

	public void setRoleDTOList(List<RoleDTO> roleDTOList) {
		this.roleDTOList = roleDTOList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDetailDTO other = (CustomerDetailDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCertSubjectDn() {
		return certSubjectDn;
	}

	public void setCertSubjectDn(String certSubjectDn) {
		this.certSubjectDn = certSubjectDn;
	}

	public Set<UserGuideStatus> getGuideStatus() {
		return guideStatus;
	}

	public void setGuideStatus(Set<UserGuideStatus> guideStatus) {
		this.guideStatus = guideStatus;
	}
	
	public void addGuideStatus(UserGuideStatus guideStatus) {
		if(this.guideStatus == null){
			this.guideStatus = new HashSet<UserGuideStatus>();
		}
		this.guideStatus.add(guideStatus);
	}

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
}
