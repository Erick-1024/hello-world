/**
* Copyright (c) 2015, travelzen and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.UserType;

public class CustomerRegisterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6876332971826463607L;
	
	/**
	*用户名
	*/
	private String username;
	
	/**
	*用户类型（保理商，融资商）
	*/
	private UserType userType;
	
	/**
	 *联系人
	 */
	private String contactName;
	
	/**
	 *职称
	 */
	private String jobTitle;
	
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
	*税务登记证号码
	*/
	private String taxRegistrationCertificateCode;
	
	/**
	*税务登记证id
	*/
	private String taxRegistrationCertificateMediaId;
	
	/**
	*企业法人身份证正面id
	*/
	private String legalPersonIdentityCardFrontMediaId;
	
	/**
	*企业法人身份证背面id
	*/
	private String legalPersonIdentityCardBackMediaId;

	public String getUsername() {
		return username;
	}

	public UserType getUserType() {
		return userType;
	}

	public String getContactName() {
		return contactName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getContactTel() {
		return contactTel;
	}

	public String getContactMail() {
		return contactMail;
	}

	public String getContactIdentityCardFrontMediaId() {
		return contactIdentityCardFrontMediaId;
	}

	public String getContactIdentityCardBackMediaId() {
		return contactIdentityCardBackMediaId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getOrganizationCodeCertificateMediaId() {
		return organizationCodeCertificateMediaId;
	}

	public String getBusinessLicenceCode() {
		return businessLicenceCode;
	}

	public String getBusinessLicenceMediaId() {
		return businessLicenceMediaId;
	}

	public String getTaxRegistrationCertificateCode() {
		return taxRegistrationCertificateCode;
	}

	public String getTaxRegistrationCertificateMediaId() {
		return taxRegistrationCertificateMediaId;
	}

	public String getLegalPersonIdentityCardFrontMediaId() {
		return legalPersonIdentityCardFrontMediaId;
	}

	public String getLegalPersonIdentityCardBackMediaId() {
		return legalPersonIdentityCardBackMediaId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public void setContactIdentityCardFrontMediaId(String contactIdentityCardFrontMediaId) {
		this.contactIdentityCardFrontMediaId = contactIdentityCardFrontMediaId;
	}

	public void setContactIdentityCardBackMediaId(String contactIdentityCardBackMediaId) {
		this.contactIdentityCardBackMediaId = contactIdentityCardBackMediaId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public void setOrganizationCodeCertificateMediaId(String organizationCodeCertificateMediaId) {
		this.organizationCodeCertificateMediaId = organizationCodeCertificateMediaId;
	}

	public void setBusinessLicenceCode(String businessLicenceCode) {
		this.businessLicenceCode = businessLicenceCode;
	}

	public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
		this.businessLicenceMediaId = businessLicenceMediaId;
	}

	public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
		this.taxRegistrationCertificateCode = taxRegistrationCertificateCode;
	}

	public void setTaxRegistrationCertificateMediaId(String taxRegistrationCertificateMediaId) {
		this.taxRegistrationCertificateMediaId = taxRegistrationCertificateMediaId;
	}

	public void setLegalPersonIdentityCardFrontMediaId(String legalPersonIdentityCardFrontMediaId) {
		this.legalPersonIdentityCardFrontMediaId = legalPersonIdentityCardFrontMediaId;
	}

	public void setLegalPersonIdentityCardBackMediaId(String legalPersonIdentityCardBackMediaId) {
		this.legalPersonIdentityCardBackMediaId = legalPersonIdentityCardBackMediaId;
	}

}