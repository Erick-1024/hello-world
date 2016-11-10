package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.member.enums.user.UserType;

public class CompanyInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	    
	    private List<RoleDTO> roleDTOlist;
	    
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
	       *联系人身份证正面Id
	       */
	      private String contactIdentityCardFrontMediaId;

	      /**
	       *联系人身份证背面Id
	       */
	      private String contactIdentityCardBackMediaId;
	      
	      /**
	  	 * 员工工号
	  	 */
	  	private String jobNo;

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

		public String getTaxRegistrationCertificateCode() {
			return taxRegistrationCertificateCode;
		}

		public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
			this.taxRegistrationCertificateCode = taxRegistrationCertificateCode;
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

		public List<RoleDTO> getRoleDTOlist() {
			return roleDTOlist;
		}

		public void setRoleDTOlist(List<RoleDTO> roleDTOlist) {
			this.roleDTOlist = roleDTOlist;
		}

		public String getContactIdentityCardFrontMediaId() {
			return contactIdentityCardFrontMediaId;
		}

		public void setContactIdentityCardFrontMediaId(String contactIdentityCardFrontMediaId) {
			this.contactIdentityCardFrontMediaId = contactIdentityCardFrontMediaId;
		}

		public String getContactIdentityCardBackMediaId() {
			return contactIdentityCardBackMediaId;
		}

		public void setContactIdentityCardBackMediaId(String contactIdentityCardBackMediaId) {
			this.contactIdentityCardBackMediaId = contactIdentityCardBackMediaId;
		}

		public String getJobNo() {
			return jobNo;
		}

		public void setJobNo(String jobNo) {
			this.jobNo = jobNo;
		}
	    
	      
}
