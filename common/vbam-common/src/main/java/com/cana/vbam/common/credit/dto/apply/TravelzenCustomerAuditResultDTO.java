/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class TravelzenCustomerAuditResultDTO implements Serializable {

	protected static final long serialVersionUID = -1116973902311218517L;

	protected String auditorId;                               //审核人员工ID
	protected String manualAuditRemarks;                      // 人工审核时的备注
	protected String legalPerson;								// 法人
	protected boolean checkCompanyInfo;                       //工商信息真实性
    protected boolean checkOrganizationCode;                  //组织机构代码
    protected boolean checkBusinessLicenceCode;               //营业执照
    protected boolean checkTaxRegistrationCertificateCode;    //税务登记证
    protected boolean checkOthers;                            //审核其他是否通过
    
	protected String customerApplyId; // 客户授信申请ID
	protected String enterpriseExecutionMoney;// 法院被执行（企业）金额，0.00元
	protected Integer enterpriseExecutionTimes;// 执行次数（企业）
	protected String individualExecutionMoney;// 法院被执行（个人）金额，0.00元
	protected Integer individualExecutionTimes;// 执行次数（个人）
	protected String negativeNetwork;// 是否存在网络负面信息
//	protected List<CustomerApplyAttachmentDTO> customerApplyAttachmentDTOs;//附件信息
//	protected String auditLimit;//额度
	private String applicantType; // 申请人类型
	
	//-----------------------------------------------------
	protected Integer consistencyCheck;// 一致性检查
	protected Integer creditLimitRuleBatchNo;// 额度模型批次号
	
	/**
	 * 设置：组织机构代码证、营业执照证、税务登记证、、
	 * @param consistencyCheck
	 */
	public void setConsistencyCheck(Integer consistencyCheck){
		this.checkCompanyInfo                       = (consistencyCheck & 1) > 0;
        this.checkOrganizationCode                  = (consistencyCheck & 2) > 0;
        this.checkBusinessLicenceCode               = (consistencyCheck & 4) > 0;
        this.checkTaxRegistrationCertificateCode    = (consistencyCheck & 8) > 0;
        this.checkOthers                            = (consistencyCheck & 16) > 0;
//        this.checkTrustAttachment					= (consistencyCheck & 32) > 0;
//        this.checkSaleAttachment                    = (consistencyCheck & 64) > 0;
//        this.checkSupplyAttachment                  = (consistencyCheck & 128) > 0;
//        this.checkCooperationAttachment 			 = (consistencyCheck & 256) > 0;
        this.consistencyCheck = getConsistencyCheck();
	}
	
	public Integer getConsistencyCheck(){
		 int status = 0;
		    status += this.checkCompanyInfo                     ? 1 : 0;
	        status += this.checkOrganizationCode                ? 2 : 0;
	        status += this.checkBusinessLicenceCode             ? 4 : 0;
	        status += this.checkTaxRegistrationCertificateCode  ? 8 : 0;
	        status += this.checkOthers                          ? 16 : 0;
//	        status += this.checkTrustAttachment					? 32 : 0;
//	        status += this.checkSaleAttachment                  ? 64 : 0;
//	        status += this.checkSupplyAttachment                ? 128 : 0;
//	        status += this.checkCooperationAttachment			? 256 : 0;
	        return status;
	}
	
	public boolean isCheckCompanyInfo() {
		return checkCompanyInfo;
	}

	public void setCheckCompanyInfo(boolean checkCompanyInfo) {
		this.checkCompanyInfo = checkCompanyInfo;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getManualAuditRemarks() {
		return manualAuditRemarks;
	}

	public void setManualAuditRemarks(String manualAuditRemarks) {
		this.manualAuditRemarks = manualAuditRemarks;
	}

	public boolean isCheckOrganizationCode() {
		return checkOrganizationCode;
	}

	public void setCheckOrganizationCode(boolean checkOrganizationCode) {
		this.checkOrganizationCode = checkOrganizationCode;
	}

	public boolean isCheckBusinessLicenceCode() {
		return checkBusinessLicenceCode;
	}

	public void setCheckBusinessLicenceCode(boolean checkBusinessLicenceCode) {
		this.checkBusinessLicenceCode = checkBusinessLicenceCode;
	}

	public boolean isCheckTaxRegistrationCertificateCode() {
		return checkTaxRegistrationCertificateCode;
	}

	public void setCheckTaxRegistrationCertificateCode(boolean checkTaxRegistrationCertificateCode) {
		this.checkTaxRegistrationCertificateCode = checkTaxRegistrationCertificateCode;
	}

	public boolean isCheckOthers() {
		return checkOthers;
	}

	public void setCheckOthers(boolean checkOthers) {
		this.checkOthers = checkOthers;
	}

	public String getCustomerApplyId() {
		return customerApplyId;
	}

	public void setCustomerApplyId(String customerApplyId) {
		this.customerApplyId = customerApplyId;
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

	public Integer getCreditLimitRuleBatchNo() {
		return creditLimitRuleBatchNo;
	}

	public void setCreditLimitRuleBatchNo(Integer creditLimitRuleBatchNo) {
		this.creditLimitRuleBatchNo = creditLimitRuleBatchNo;
	}

	public String getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

}
