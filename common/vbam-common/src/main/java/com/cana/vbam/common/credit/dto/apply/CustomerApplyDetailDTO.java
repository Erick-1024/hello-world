package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;
import java.util.List;

public class CustomerApplyDetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

		/**
	    *主键
	    */
	    private String id;
	    
	    private Integer canaId;

	    private String tzCustomerId;
	    
	    /**
	    *申请时间
	    */
	    private String applyDate;

	    /**
	    *申请公司的全称
	    */
	    private String companyName;

	    /**
	    *实际控制人
	    */
	    private String realControlPerson;

	    /**
	    *申请额度
	    */
	    private String applyCreditLimit;

	    /**
	    *申请类型（"REPAYMENT"：订单回款，"FINANCING"：订单融资）
	    */
	    private String applyType;
	    
	    private String applyTypeDesc;

	    /**
	    *联系人姓名
	    */
	    private String contactName;

	    /**
	    *手机号码
	    */
	    private String phoneNumber;

	    /**
	    *电子邮件
	    */
	    private String email;

	    /**
	    *下游客户类型（"ENTERPRISE"：企业，"INDIVIDUAL"：个人，"BOTH"：两者都有）
	    */
	    private String downstreamCustomerType;

	    private String downstreamCustomerTypeDesc;
	    
	    /**
	    *下游回款账期（如不填则为无账期，固定账期格式：f5,f18（每月5号和18号）  e10(每10天为一个还款周期)）
	    */
	    private String downstreamRepaymentAccountPeriod;
	    
	    /**
	     *法院被执行（企业）金额，精确到分
	     */
	    private String enterpriseExecutionMoney;

	    /**
	     *执行次数（企业）
	     */
	    private Integer enterpriseExecutionTimes;

	    /**
	     *法院被执行（个人）金额，精确到分
	     */
	    private String individualExecutionMoney;

	    /**
	     *执行次数（个人）
	     */
	    private Integer individualExecutionTimes;

	    /**
	     *是否存在网络负面信息
	     */
	    private String negativeNetwork;
	    
	    /**
	     *工商信息
	     */
	    private String businessInfo;

	    // 申请人类型
	    private String applicantType;
	    private String applicantTypeDesc;
	    
	    /**
	     *组织机构证件号码
	     */
	    private String organizationNo;

	    /**
	     *组织机构证件Id
	     */
	    private String organizationMediaId;

	    /**
	     *营业执照证件号码
	     */
	    private String businessLicenceNo;

	    /**
	     *营业执照证件Id
	     */
	    private String businessLicenceMediaId;

	    /**
	     *税务登记证件号码
	     */
	    private String taxRegistrationCertificateNo;

	    /**
	     *税务登记证件Id
	     */
	    private String taxRegistrationCertificateMediaId;
	    
	    /**
	     *人工审核时的备注
	     */
	    private String manualAuditRemarks;

	    /**
	     *是否通过人工审核（"WAIT"：未审核，"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
	     */
	    private String accessManualState;
	    
	    private String accessManualStateDesc;
	    
	    /**
	     *是否在白名单内
	     */
	    private Boolean inWhitelist;

	    /**
	     *销售数据(近12个月)
	     */
	    private List<CustomerSaleDataDTO> saleDatas;
	    
	    /**
	     *是否通过准入规则（"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
	     */
	    private String accessAutomaticState;
	    
	    private String accessAutomaticStateDesc;
	    
	    /**
	     *准入规则跑的数据（合作月份，逾期。。）
	     */
	    private AutomaticAuditDataDTO automaticAuditData;
	    
	    /**
	     *准入审核备注，准入审核不通过时填写不通过内容。
	     */
	    private List<String> automaticAuditRemarkList;
	    
	    /**
	     * 法人
	     */
	    private String legalPerson;
	    
	    /**
	     *实际控制人身份证号码
	     */
	    private String realControlPersonId;

	    /**
	     *实际控制人手持身份证正面照
	     */
	    private String realControlPersonIdHandheldFrontMediaId;

	    /**
	     *法人身份证号码
	     */
	    private String legalPersonId;

	    /**
	     *法人手持身份证正面照
	     */
	    private String legalPersonIdHandheldFrontMediaId;

	    /**
	     *法人是否与实际控制人一致
	     */
	    private Boolean samePersonOfLegalAndRealControl;
	    
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getCanaId() {
			return canaId;
		}

		public void setCanaId(Integer canaId) {
			this.canaId = canaId;
		}

		public String getApplyDate() {
			return applyDate;
		}

		public void setApplyDate(String applyDate) {
			this.applyDate = applyDate;
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

		public String getApplyCreditLimit() {
			return applyCreditLimit;
		}

		public void setApplyCreditLimit(String applyCreditLimit) {
			this.applyCreditLimit = applyCreditLimit;
		}

		public String getApplyType() {
			return applyType;
		}

		public void setApplyType(String applyType) {
			this.applyType = applyType;
		}

		public String getApplyTypeDesc() {
			return applyTypeDesc;
		}

		public void setApplyTypeDesc(String applyTypeDesc) {
			this.applyTypeDesc = applyTypeDesc;
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

		public String getDownstreamCustomerTypeDesc() {
			return downstreamCustomerTypeDesc;
		}

		public void setDownstreamCustomerTypeDesc(String downstreamCustomerTypeDesc) {
			this.downstreamCustomerTypeDesc = downstreamCustomerTypeDesc;
		}

		public String getDownstreamRepaymentAccountPeriod() {
			return downstreamRepaymentAccountPeriod;
		}

		public void setDownstreamRepaymentAccountPeriod(String downstreamRepaymentAccountPeriod) {
			this.downstreamRepaymentAccountPeriod = downstreamRepaymentAccountPeriod;
		}

		public String getEnterpriseExecutionMoney() {
			return enterpriseExecutionMoney;
		}

		public void setEnterpriseExecutionMoney(String enterpriseExecutionMoney) {
			this.enterpriseExecutionMoney = enterpriseExecutionMoney;
		}

		public Integer getEnterpriseExecutionTimes() {
			return enterpriseExecutionTimes;
		}

		public void setEnterpriseExecutionTimes(Integer enterpriseExecutionTimes) {
			this.enterpriseExecutionTimes = enterpriseExecutionTimes;
		}

		public String getIndividualExecutionMoney() {
			return individualExecutionMoney;
		}

		public void setIndividualExecutionMoney(String individualExecutionMoney) {
			this.individualExecutionMoney = individualExecutionMoney;
		}

		public Integer getIndividualExecutionTimes() {
			return individualExecutionTimes;
		}

		public void setIndividualExecutionTimes(Integer individualExecutionTimes) {
			this.individualExecutionTimes = individualExecutionTimes;
		}

		public String getNegativeNetwork() {
			return negativeNetwork;
		}

		public void setNegativeNetwork(String negativeNetwork) {
			this.negativeNetwork = negativeNetwork;
		}

		public String getBusinessInfo() {
			return businessInfo;
		}

		public void setBusinessInfo(String businessInfo) {
			this.businessInfo = businessInfo;
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

		public String getManualAuditRemarks() {
			return manualAuditRemarks;
		}

		public void setManualAuditRemarks(String manualAuditRemarks) {
			this.manualAuditRemarks = manualAuditRemarks;
		}

		public String getAccessManualState() {
			return accessManualState;
		}

		public void setAccessManualState(String accessManualState) {
			this.accessManualState = accessManualState;
		}

		public String getAccessManualStateDesc() {
			return accessManualStateDesc;
		}

		public void setAccessManualStateDesc(String accessManualStateDesc) {
			this.accessManualStateDesc = accessManualStateDesc;
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

		public Boolean getInWhitelist() {
			return inWhitelist;
		}

		public void setInWhitelist(Boolean inWhitelist) {
			this.inWhitelist = inWhitelist;
		}

		public String getApplicantTypeDesc() {
			return applicantTypeDesc;
		}

		public void setApplicantTypeDesc(String applicantTypeDesc) {
			this.applicantTypeDesc = applicantTypeDesc;
		}

		public String getApplicantType() {
			return applicantType;
		}

		public void setApplicantType(String applicantType) {
			this.applicantType = applicantType;
		}

		public String getRealControlPersonId() {
			return realControlPersonId;
		}

		public void setRealControlPersonId(String realControlPersonId) {
			this.realControlPersonId = realControlPersonId;
		}

		public String getRealControlPersonIdHandheldFrontMediaId() {
			return realControlPersonIdHandheldFrontMediaId;
		}

		public void setRealControlPersonIdHandheldFrontMediaId(String realControlPersonIdHandheldFrontMediaId) {
			this.realControlPersonIdHandheldFrontMediaId = realControlPersonIdHandheldFrontMediaId;
		}

		public String getLegalPerson() {
			return legalPerson;
		}

		public void setLegalPerson(String legalPerson) {
			this.legalPerson = legalPerson;
		}

		public String getLegalPersonId() {
			return legalPersonId;
		}

		public void setLegalPersonId(String legalPersonId) {
			this.legalPersonId = legalPersonId;
		}

		public String getLegalPersonIdHandheldFrontMediaId() {
			return legalPersonIdHandheldFrontMediaId;
		}

		public void setLegalPersonIdHandheldFrontMediaId(String legalPersonIdHandheldFrontMediaId) {
			this.legalPersonIdHandheldFrontMediaId = legalPersonIdHandheldFrontMediaId;
		}

		public Boolean getSamePersonOfLegalAndRealControl() {
			return samePersonOfLegalAndRealControl;
		}

		public void setSamePersonOfLegalAndRealControl(Boolean samePersonOfLegalAndRealControl) {
			this.samePersonOfLegalAndRealControl = samePersonOfLegalAndRealControl;
		}

		public List<CustomerSaleDataDTO> getSaleDatas() {
			return saleDatas;
		}

		public void setSaleDatas(List<CustomerSaleDataDTO> saleDatas) {
			this.saleDatas = saleDatas;
		}

		public String getTzCustomerId() {
			return tzCustomerId;
		}

		public void setTzCustomerId(String tzCustomerId) {
			this.tzCustomerId = tzCustomerId;
		}

		public AutomaticAuditDataDTO getAutomaticAuditData() {
			return automaticAuditData;
		}

		public void setAutomaticAuditData(AutomaticAuditDataDTO automaticAuditData) {
			this.automaticAuditData = automaticAuditData;
		}

		public String getAccessAutomaticState() {
			return accessAutomaticState;
		}

		public void setAccessAutomaticState(String accessAutomaticState) {
			this.accessAutomaticState = accessAutomaticState;
		}

		public String getAccessAutomaticStateDesc() {
			return accessAutomaticStateDesc;
		}

		public void setAccessAutomaticStateDesc(String accessAutomaticStateDesc) {
			this.accessAutomaticStateDesc = accessAutomaticStateDesc;
		}

		public List<String> getAutomaticAuditRemarkList() {
			return automaticAuditRemarkList;
		}

		public void setAutomaticAuditRemarkList(List<String> automaticAuditRemarkList) {
			this.automaticAuditRemarkList = automaticAuditRemarkList;
		}

}
