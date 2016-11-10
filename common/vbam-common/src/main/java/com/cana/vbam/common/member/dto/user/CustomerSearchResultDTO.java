package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.member.enums.user.AccountActivateStatus;
import com.cana.vbam.common.member.enums.user.AccountAuditResult;
import com.cana.vbam.common.member.enums.user.AccountAuditStatus;
import com.cana.vbam.common.member.enums.user.UserType;

public class CustomerSearchResultDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7117700907842922619L;
	/**
    *主键
    */
    
	private String id;
	/**
	*用户名
	*/
	private String username;
	
    /**
    *公司名称
    */
    private String companyName;
    
    /**
     *申请企业
     */
    private String agentCompany;
    
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
    *注册时间
    */
    private Date createTime;
    
    /**
     *账号审核状态
     */
    private AccountAuditStatus accountAuditStatus;
    
    /**
     *账号审核结果
     */
    private AccountAuditResult accountAuditResult;
    
    /**
     *账号激活状态
     */
    private AccountActivateStatus accountActivateStatus;
    
    /**
    *组织机构代码
    */
    private String organizationCode;
    
    /**
    *营业执照号码
    */
    private String businessLicenceCode;
    
    /**
     *税务登记证号码
     */
     private String taxRegistrationCertificateCode;
    
    /**
     *用户类型（保理商，融资商）
     */
    private UserType userType;
    
    /**
     *审核人
     */
    private String auditor;

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAgentCompany() {
		return agentCompany;
	}

	public void setAgentCompany(String agentCompany) {
		this.agentCompany = agentCompany;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public AccountAuditStatus getAccountAuditStatus() {
		return accountAuditStatus;
	}

	public AccountAuditResult getAccountAuditResult() {
		return accountAuditResult;
	}

	public AccountActivateStatus getAccountActivateStatus() {
		return accountActivateStatus;
	}

	public void setAccountAuditStatus(AccountAuditStatus accountAuditStatus) {
		this.accountAuditStatus = accountAuditStatus;
	}

	public void setAccountAuditResult(AccountAuditResult accountAuditResult) {
		this.accountAuditResult = accountAuditResult;
	}

	public void setAccountActivateStatus(AccountActivateStatus accountActivateStatus) {
		this.accountActivateStatus = accountActivateStatus;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getBusinessLicenceCode() {
		return businessLicenceCode;
	}

	public void setBusinessLicenceCode(String businessLicenceCode) {
		this.businessLicenceCode = businessLicenceCode;
	}

	public String getTaxRegistrationCertificateCode() {
		return taxRegistrationCertificateCode;
	}

	public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
		this.taxRegistrationCertificateCode = taxRegistrationCertificateCode;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

}
