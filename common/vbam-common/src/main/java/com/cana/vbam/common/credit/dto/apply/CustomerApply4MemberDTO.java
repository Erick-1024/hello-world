package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;

public class CustomerApply4MemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String companyName;
	
	private String organizationNo;
	
	private String businessLicenceNo;
	
	private String taxRegistrationCertificateNo;
	
	private String organizationMediaId;
	
	private String businessLicenceMediaId;
	
	private String taxRegistrationCertificateMediaId;
	
    private String contactName;

    private String phoneNumber;

    private String email;
    
    private String auditorId;
    
    private String userId;

    private String financeRoleId; // 如果为空，则使用融资商默认角色
    
    private boolean individual; // 当为真时，表示是真旅个人客户，并取businessLicenceNo为个人身份证号码
    
    private String legalPerson;
    
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOrganizationMediaId() {
		return organizationMediaId;
	}

	public void setOrganizationMediaId(String organizationMediaId) {
		this.organizationMediaId = organizationMediaId;
	}

	public String getBusinessLicenceMediaId() {
		return businessLicenceMediaId;
	}

	public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
		this.businessLicenceMediaId = businessLicenceMediaId;
	}

	public String getTaxRegistrationCertificateMediaId() {
		return taxRegistrationCertificateMediaId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getBusinessLicenceNo() {
		return businessLicenceNo;
	}

	public void setBusinessLicenceNo(String businessLicenceNo) {
		this.businessLicenceNo = businessLicenceNo;
	}

	public String getTaxRegistrationCertificateNo() {
		return taxRegistrationCertificateNo;
	}

	public void setTaxRegistrationCertificateNo(String taxRegistrationCertificateNo) {
		this.taxRegistrationCertificateNo = taxRegistrationCertificateNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFinanceRoleId() {
		return financeRoleId;
	}

	public void setFinanceRoleId(String financeRoleId) {
		this.financeRoleId = financeRoleId;
	}

	public boolean isIndividual() {
		return individual;
	}

	public void setIndividual(boolean individual) {
		this.individual = individual;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
}
