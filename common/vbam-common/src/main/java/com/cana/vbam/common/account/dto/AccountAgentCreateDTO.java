/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.member.enums.user.UserType;

public class AccountAgentCreateDTO implements Serializable {
	private static final long serialVersionUID = 3716437858809042522L;

	private String userType; //客户类型
	private String companyName; //企业名称
    private String agentCompanyId;   //代理企业id
    private AccountType accountType; //开户类型
    private UserType supervisorType;//监管账户所属用户类型
    private String supervisorAccountNo;//当监管账户为非创建时录入的已存在的帐号
    private boolean isDefaultRepayment;//是否把监管账户设置为默认还款账户
    private Integer accountNumber; //申请账户数量，若buyerNames值不为空，则账户数量以buyerNames为准
    private List<String> buyerNames;   //买方企业名称列表
    private String contactName;    //联系人
    private String contactJobTitle; //联系人职称
    private String contactTel; //联系人电话
    private String contactMail;    //邮箱
    private String contactIdentityCardFrontMediaId;    //联系人身份证正面Id
    private String contactIdentityCardBackMediaId; //联系人身份证背面Id
    private String authorizationLetterId;  //企业授权书
    private String organizationCode;   //组织机构代码
    private String organizationCodeCertificateMediaId; //组织机构代码证文件Id
    private String businessLicenceCode;    //营业执照号码
    private String businessLicenceMediaId; //营业执照扫描件Id
    private String legalPersonIdentityCardFrontMediaId;    //企业法人身份证正面id
    private String legalPersonIdentityCardBackMediaId; //企业法人身份证背面id
    private String taxRegistrationCertificateCode; //税务登记证号码
    private String taxRegistrationCertificateMediaId;  //税务登记证id

    public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getAgentCompanyId() {
        return agentCompanyId;
    }
    public void setAgentCompanyId(String agentCompanyId) {
        this.agentCompanyId = agentCompanyId;
    }
    public Integer getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
    public List<String> getBuyerNames() {
        return buyerNames;
    }
    public void setBuyerNames(List<String> buyerNames) {
        this.buyerNames = buyerNames;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactJobTitle() {
        return contactJobTitle;
    }
    public void setContactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle;
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
    public String getContactIdentityCardFrontMediaId() {
        return contactIdentityCardFrontMediaId;
    }
    public void setContactIdentityCardFrontMediaId(
            String contactIdentityCardFrontMediaId) {
        this.contactIdentityCardFrontMediaId = contactIdentityCardFrontMediaId;
    }
    public String getContactIdentityCardBackMediaId() {
        return contactIdentityCardBackMediaId;
    }
    public void setContactIdentityCardBackMediaId(
            String contactIdentityCardBackMediaId) {
        this.contactIdentityCardBackMediaId = contactIdentityCardBackMediaId;
    }
    public String getAuthorizationLetterId() {
        return authorizationLetterId;
    }
    public void setAuthorizationLetterId(String authorizationLetterId) {
        this.authorizationLetterId = authorizationLetterId;
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
    public void setOrganizationCodeCertificateMediaId(
            String organizationCodeCertificateMediaId) {
        this.organizationCodeCertificateMediaId = organizationCodeCertificateMediaId;
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
    public String getLegalPersonIdentityCardFrontMediaId() {
        return legalPersonIdentityCardFrontMediaId;
    }
    public void setLegalPersonIdentityCardFrontMediaId(
            String legalPersonIdentityCardFrontMediaId) {
        this.legalPersonIdentityCardFrontMediaId = legalPersonIdentityCardFrontMediaId;
    }
    public String getLegalPersonIdentityCardBackMediaId() {
        return legalPersonIdentityCardBackMediaId;
    }
    public void setLegalPersonIdentityCardBackMediaId(
            String legalPersonIdentityCardBackMediaId) {
        this.legalPersonIdentityCardBackMediaId = legalPersonIdentityCardBackMediaId;
    }
    public String getTaxRegistrationCertificateCode() {
        return taxRegistrationCertificateCode;
    }
    public void setTaxRegistrationCertificateCode(
            String taxRegistrationCertificateCode) {
        this.taxRegistrationCertificateCode = taxRegistrationCertificateCode;
    }
    public String getTaxRegistrationCertificateMediaId() {
        return taxRegistrationCertificateMediaId;
    }
    public void setTaxRegistrationCertificateMediaId(
            String taxRegistrationCertificateMediaId) {
        this.taxRegistrationCertificateMediaId = taxRegistrationCertificateMediaId;
    }
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public UserType getSupervisorType() {
		return supervisorType;
	}
	public void setSupervisorType(UserType supervisorType) {
		this.supervisorType = supervisorType;
	}
	public String getSupervisorAccountNo() {
		return supervisorAccountNo;
	}
	public void setSupervisorAccountNo(String supervisorAccountNo) {
		this.supervisorAccountNo = supervisorAccountNo;
	}
	public boolean getIsDefaultRepayment() {
		return isDefaultRepayment;
	}
	public void setIsDefaultRepayment(boolean isDefaultRepayment) {
		this.isDefaultRepayment = isDefaultRepayment;
	}
}