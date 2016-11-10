package com.cana.vbam.common.yundaex.dto.apply;

import java.io.Serializable;

public class YundaexCustomerAuditResultDTO implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1906000303228896236L;

	protected String auditorId;                               //审核人员工ID
	protected String manualAuditRemarks;                      // 人工审核时的备注
	protected String stationAddress;							//经营地址
	//protected boolean checkCompanyInfo;                       //工商信息真实性
    protected boolean checkOrganizationCode;                  //组织机构代码
    protected boolean checkBusinessLicenceCode;               //营业执照
    protected boolean checkTaxRegistrationCertificateCode;    //税务登记证
    protected boolean checkLegalIdno;							//法人身份证
    protected boolean checkOther;								//其他检查
    
    protected String executeIndividualInfo;    //是否有法院被执行人信息
    protected String negativeNetwork;			// 是否存在网络负面信息
    protected String customerApplyId;          // 客户授信申请ID
    
    //-----------------------------------------------------
  	protected Integer consistencyCheck;			// 一致性检查
  	protected Integer creditLimitRuleBatchNo;	// 额度模型批次号
  	
  	public Integer getConsistencyCheck() {
  		 int status = 0;
		    status += this.checkOrganizationCode                     	? 1 : 0;
	        status += this.checkBusinessLicenceCode                	  	? 2 : 0;
	        status += this.checkTaxRegistrationCertificateCode        	? 4 : 0;
	        status += this.checkLegalIdno 							 	? 8 : 0;
	        status += this.checkOther									? 16 : 0;
	        return status;
	}
	public void setConsistencyCheck(Integer consistencyCheck) {
		this.checkOrganizationCode                       	= (consistencyCheck & 1) > 0;
        this.checkBusinessLicenceCode                  		= (consistencyCheck & 2) > 0;
        this.checkTaxRegistrationCertificateCode        	= (consistencyCheck & 4) > 0;
        this.checkLegalIdno    								= (consistencyCheck & 8) > 0;
        this.checkOther    									= (consistencyCheck & 16) > 0;
        this.consistencyCheck = getConsistencyCheck();
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
	public String getStationAddress() {
		return stationAddress;
	}
	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
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
	public boolean isCheckLegalIdno() {
		return checkLegalIdno;
	}
	public void setCheckLegalIdno(boolean checkLegalIdno) {
		this.checkLegalIdno = checkLegalIdno;
	}
	
	public boolean isCheckOther() {
		return checkOther;
	}
	public void setCheckOther(boolean checkOther) {
		this.checkOther = checkOther;
	}
	public String getExecuteIndividualInfo() {
		return executeIndividualInfo;
	}
	public void setExecuteIndividualInfo(String executeIndividualInfo) {
		this.executeIndividualInfo = executeIndividualInfo;
	}
	public String getNegativeNetwork() {
		return negativeNetwork;
	}
	public void setNegativeNetwork(String negativeNetwork) {
		this.negativeNetwork = negativeNetwork;
	}
	public String getCustomerApplyId() {
		return customerApplyId;
	}
	public void setCustomerApplyId(String customerApplyId) {
		this.customerApplyId = customerApplyId;
	}
	public Integer getCreditLimitRuleBatchNo() {
		return creditLimitRuleBatchNo;
	}
	public void setCreditLimitRuleBatchNo(Integer creditLimitRuleBatchNo) {
		this.creditLimitRuleBatchNo = creditLimitRuleBatchNo;
	}
  	
  	
	
}
