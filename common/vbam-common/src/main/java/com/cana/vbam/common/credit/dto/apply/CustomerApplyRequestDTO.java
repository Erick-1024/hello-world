package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;

public class CustomerApplyRequestDTO implements Serializable {

	/**
	 * 真旅的客户（采购商）Id
	 */
	private String customerId;

	/**
	 * 申请时间
	 */
	private String applyTime;

	/**
	 * 真旅采购商名称
	 */
	private String customerName;

	/**
	 * 申请公司的全称(个人用户为该用户的姓名)
	 */
	private String companyName;

	/**
	 * 实际控制人
	 */
	private String realControlPerson;

	/**
	 * 申请额度
	 */
	private Long applyCreditLimit;

	/**
	 * 申请类型（"REPAYMENT"：订单回款，"FINANCING"：订单融资）
	 */
	private String applyType;

	/**
	 * 组织机构证件号码（个人用户为该用户的身份证号码）
	 */
	private String organizationNo;

	/**
	 * 组织机构证件Code（个人用户为用户的身份证正面照）
	 */
	private String organizationCode;

	/**
	 * 营业执照证件号码（个人用户为该用户的身份证号码）
	 */
	private String businessLicenceNo;

	/**
	 * 营业执照证件Code（个人用户为用户的身份证反面照）
	 */
	private String businessLicenceCode;

	/**
	 * 税务登记证件号码（个人用户为该用户的身份证号码）
	 */
	private String taxRegistrationCertificateNo;

	/**
	 * 税务登记证件Code（个人用户为用户的手持身份证照）
	 */
	private String taxRegistrationCertificateCode;
    
    /**
     *实际控制人身份证号码
     */
    private String realControlPersonId;

    /**
     *实际控制人手持身份证正面照
     */
    private String realControlPersonIdHandheldFrontCode;

    /**
     *法人身份证号码
     */
    private String legalPersonId;

    /**
     *法人手持身份证正面照
     */
    private String legalPersonIdHandheldFrontCode;

	/**
	 * 联系人姓名
	 */
	private String contactName;

	/**
	 * 手机号码
	 */
	private String phoneNumber;

	/**
	 * 电子邮件
	 */
	private String email;

	/**
	 * 下游客户类型（"ENTERPRISE"：企业，"INDIVIDUAL"：个人，"BOTH"：两者都有）
	 */
	private String downstreamCustomerType;

	/**
	 * 下游回款账期（如不填则为无账期，固定账期格式：f5,f18（每月5号和18号） e10(每10天为一个还款周期)）
	 */
	private String downstreamRepaymentAccountPeriod;

	/**
	 * 申请人类型，详见ApplyApplicantType，当没有值时表示不确定
	 */
	private String applicantType;
	
	/**
     *审核结果URL
     */
    private String auditNotifyUrl;

    /**
     *额度生效通知
     */
    private String limitNotifyUrl;

	private static final long serialVersionUID = 1L;
	
	/**
	 * 企业性质（"SOE"：国有，"COOPERATIVE"：合作，"JOINT"：合资，"SOLE"：独资，"COLLECTIVE"：集体，
	 * "PRIAVTE"：私营，"INDIVIDUAL"：个体工商户，"CUSTOMS"：报关，"OTHER"：其他）
	 */
	@Deprecated
	private String companyNature;

	/**
	 * 公司是否上市
	 */
	@Deprecated
	private Boolean isListing;

	/**
	 * 年销售额，这里指去年的年销售额
	 */
	@Deprecated
	private Long annualSales;

	/**
	 * 主要合作产品
	 */
	@Deprecated
	private String majorCooperativeProducts;

	/**
	 * 是否存在淡旺季
	 */
	@Deprecated
	private Boolean existPeakSlackPeriod;

	/**
	 * 旺季时间（月份范围，如35，多段用","分隔）
	 */
	@Deprecated
	private String peakPeriod;

	/**
	 * 淡季时间（月份范围，如35，多段用","分隔）
	 */
	@Deprecated
	private String slackPeriod;

	/**
	 * 经营模式（"SELF"：自营，"TITULAR"：挂靠）
	 */
	@Deprecated
	private String businessModel;

	/**
	 * 被挂靠企业名称(企业全称)
	 */
	@Deprecated
	private String subordinateCompanyName;

	/**
	 * 资质信息（"DOMESTIC"：国内，"INTERNATIONAL"：国际，"OTHER"：其他）
	 */
	@Deprecated
	private String qualifications;

	/**
	 * 资质信息备注（当资质为"OTHER"时必填）
	 */
	@Deprecated
	private String qualificationsRemarks;

	/**
	 * 当地同业地位（"TOP3"：前三名，"ABOVEAVERAGE"：中等偏上，"AVERAGE"：中等，"BELOWAVERAGE"：中等偏下，
	 * "WEAKNESS"：较弱势）
	 */
	@Deprecated
	private String sameIndustryPlaceOnLocal;

	/**
	 * 同类型采购商地位（"MAJOR"：主要代理人，"GENERAL"：一般代理人，"ACTING"：临时代理人）
	 */
	@Deprecated
	private String sameTypePlace;

	/**
	 * 合作配合度（"0"：低，"1"：中，"2"：高）
	 */
	@Deprecated
	private Integer cooperationDegree;

	/**
	 * 借款期内有无中断采购的可能（"SURE"：肯定有，"MAYBE"：可能有，"NOT"：无）
	 */
	@Deprecated
	private String maybeInterruptPurchase;

	/**
	 * 借款期内有无增加采购的可能（"SURE"：肯定有，"MAYBE"：可能有，"NOT"：无）
	 */
	@Deprecated
	private String maybeIncreasePurchase;

	/**
	 * 建议额度，精确到分
	 */
	@Deprecated
	private Long proposalCreditLimit;

	/**
	 * 建议审核结果（"true"：通过，"false"：不通过）
	 */
	@Deprecated
	private Boolean proposalAuditResult;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRealControlPerson() {
		return realControlPerson;
	}

	public void setRealControlPerson(String realControlPerson) {
		this.realControlPerson = realControlPerson;
	}

	public Long getApplyCreditLimit() {
		return applyCreditLimit;
	}

	public void setApplyCreditLimit(Long applyCreditLimit) {
		this.applyCreditLimit = applyCreditLimit;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
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

	public String getRealControlPersonId() {
		return realControlPersonId;
	}

	public void setRealControlPersonId(String realControlPersonId) {
		this.realControlPersonId = realControlPersonId;
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

	public String getDownstreamCustomerType() {
		return downstreamCustomerType;
	}

	public void setDownstreamCustomerType(String downstreamCustomerType) {
		this.downstreamCustomerType = downstreamCustomerType;
	}

	public String getDownstreamRepaymentAccountPeriod() {
		return downstreamRepaymentAccountPeriod;
	}

	public void setDownstreamRepaymentAccountPeriod(String downstreamRepaymentAccountPeriod) {
		this.downstreamRepaymentAccountPeriod = downstreamRepaymentAccountPeriod;
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

	public String getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}

	public String getAuditNotifyUrl() {
		return auditNotifyUrl;
	}

	public void setAuditNotifyUrl(String auditNotifyUrl) {
		this.auditNotifyUrl = auditNotifyUrl;
	}

	public String getLimitNotifyUrl() {
		return limitNotifyUrl;
	}

	public void setLimitNotifyUrl(String limitNotifyUrl) {
		this.limitNotifyUrl = limitNotifyUrl;
	}

	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public Boolean getIsListing() {
		return isListing;
	}

	public void setIsListing(Boolean isListing) {
		this.isListing = isListing;
	}

	public Long getAnnualSales() {
		return annualSales;
	}

	public void setAnnualSales(Long annualSales) {
		this.annualSales = annualSales;
	}

	public String getMajorCooperativeProducts() {
		return majorCooperativeProducts;
	}

	public void setMajorCooperativeProducts(String majorCooperativeProducts) {
		this.majorCooperativeProducts = majorCooperativeProducts;
	}

	public Boolean getExistPeakSlackPeriod() {
		return existPeakSlackPeriod;
	}

	public void setExistPeakSlackPeriod(Boolean existPeakSlackPeriod) {
		this.existPeakSlackPeriod = existPeakSlackPeriod;
	}

	public String getPeakPeriod() {
		return peakPeriod;
	}

	public void setPeakPeriod(String peakPeriod) {
		this.peakPeriod = peakPeriod;
	}

	public String getSlackPeriod() {
		return slackPeriod;
	}

	public void setSlackPeriod(String slackPeriod) {
		this.slackPeriod = slackPeriod;
	}

	public String getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}

	public String getSubordinateCompanyName() {
		return subordinateCompanyName;
	}

	public void setSubordinateCompanyName(String subordinateCompanyName) {
		this.subordinateCompanyName = subordinateCompanyName;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getQualificationsRemarks() {
		return qualificationsRemarks;
	}

	public void setQualificationsRemarks(String qualificationsRemarks) {
		this.qualificationsRemarks = qualificationsRemarks;
	}

	public String getSameIndustryPlaceOnLocal() {
		return sameIndustryPlaceOnLocal;
	}

	public void setSameIndustryPlaceOnLocal(String sameIndustryPlaceOnLocal) {
		this.sameIndustryPlaceOnLocal = sameIndustryPlaceOnLocal;
	}

	public String getSameTypePlace() {
		return sameTypePlace;
	}

	public void setSameTypePlace(String sameTypePlace) {
		this.sameTypePlace = sameTypePlace;
	}

	public Integer getCooperationDegree() {
		return cooperationDegree;
	}

	public void setCooperationDegree(Integer cooperationDegree) {
		this.cooperationDegree = cooperationDegree;
	}

	public String getMaybeInterruptPurchase() {
		return maybeInterruptPurchase;
	}

	public void setMaybeInterruptPurchase(String maybeInterruptPurchase) {
		this.maybeInterruptPurchase = maybeInterruptPurchase;
	}

	public String getMaybeIncreasePurchase() {
		return maybeIncreasePurchase;
	}

	public void setMaybeIncreasePurchase(String maybeIncreasePurchase) {
		this.maybeIncreasePurchase = maybeIncreasePurchase;
	}

	public Long getProposalCreditLimit() {
		return proposalCreditLimit;
	}

	public void setProposalCreditLimit(Long proposalCreditLimit) {
		this.proposalCreditLimit = proposalCreditLimit;
	}

	public Boolean getProposalAuditResult() {
		return proposalAuditResult;
	}

	public void setProposalAuditResult(Boolean proposalAuditResult) {
		this.proposalAuditResult = proposalAuditResult;
	}

	public String getLegalPersonId() {
		return legalPersonId;
	}

	public void setLegalPersonId(String legalPersonId) {
		this.legalPersonId = legalPersonId;
	}

	@Override
	public String toString() {
		return "CustomerApplyRequestDTO [customerId=" + customerId + ", applyTime=" + applyTime + ", customerName="
				+ customerName + ", companyName=" + companyName + ", realControlPerson=" + realControlPerson
				+ ", applyCreditLimit=" + applyCreditLimit + ", applyType=" + applyType + ", organizationNo="
				+ organizationNo + ", businessLicenceNo=" + businessLicenceNo + ", taxRegistrationCertificateNo="
				+ taxRegistrationCertificateNo +", realControlPersonId=" + realControlPersonId  + ", legalPersonId=" + legalPersonId + ", contactName="
				+ contactName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", downstreamCustomerType="
				+ downstreamCustomerType + ", downstreamRepaymentAccountPeriod=" + downstreamRepaymentAccountPeriod
				+ ", applicantType=" + applicantType + ", auditNotifyUrl=" + auditNotifyUrl + ", limitNotifyUrl="
				+ limitNotifyUrl + ", companyNature=" + companyNature + ", isListing=" + isListing + ", annualSales="
				+ annualSales + ", majorCooperativeProducts=" + majorCooperativeProducts + ", existPeakSlackPeriod="
				+ existPeakSlackPeriod + ", peakPeriod=" + peakPeriod + ", slackPeriod=" + slackPeriod
				+ ", businessModel=" + businessModel + ", subordinateCompanyName=" + subordinateCompanyName
				+ ", qualifications=" + qualifications + ", qualificationsRemarks=" + qualificationsRemarks
				+ ", sameIndustryPlaceOnLocal=" + sameIndustryPlaceOnLocal + ", sameTypePlace=" + sameTypePlace
				+ ", cooperationDegree=" + cooperationDegree + ", maybeInterruptPurchase=" + maybeInterruptPurchase
				+ ", maybeIncreasePurchase=" + maybeIncreasePurchase + ", proposalCreditLimit=" + proposalCreditLimit
				+ ", proposalAuditResult=" + proposalAuditResult + "]";
	}

	public String getRealControlPersonIdHandheldFrontCode() {
		return realControlPersonIdHandheldFrontCode;
	}

	public void setRealControlPersonIdHandheldFrontCode(String realControlPersonIdHandheldFrontCode) {
		this.realControlPersonIdHandheldFrontCode = realControlPersonIdHandheldFrontCode;
	}

	public String getLegalPersonIdHandheldFrontCode() {
		return legalPersonIdHandheldFrontCode;
	}

	public void setLegalPersonIdHandheldFrontCode(String legalPersonIdHandheldFrontCode) {
		this.legalPersonIdHandheldFrontCode = legalPersonIdHandheldFrontCode;
	}

}
