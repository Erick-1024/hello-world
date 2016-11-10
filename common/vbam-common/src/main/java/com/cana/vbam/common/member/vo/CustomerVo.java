package com.cana.vbam.common.member.vo;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.UserType;

/**
 * 客户信息
 * @author XuMeng
 *
 */
public class CustomerVo implements Serializable {
	private static final long serialVersionUID = 914854713965044679L;

	private String customerId;	//客户ID
	private UserType userType;	//客户类型
	private String customerName;	//企业名称
	private String businessLicenceCode;	//营业执照号码
	private String organizationCode;	//组织机构代码
	private String taxRegistrationCertificateCode;	//税务登记证号码

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBusinessLicenceCode() {
		return businessLicenceCode;
	}
	public void setBusinessLicenceCode(String businessLicenceCode) {
		this.businessLicenceCode = businessLicenceCode;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getTaxRegistrationCertificateCode() {
		return taxRegistrationCertificateCode;
	}
	public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
		this.taxRegistrationCertificateCode = taxRegistrationCertificateCode;
	}
	
}
