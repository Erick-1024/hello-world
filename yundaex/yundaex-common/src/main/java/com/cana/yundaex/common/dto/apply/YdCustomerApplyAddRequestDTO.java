package com.cana.yundaex.common.dto.apply;

import java.io.Serializable;
import java.math.BigDecimal;

public class YdCustomerApplyAddRequestDTO implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private String id;
	
    /**
     *站点编号
     */
    private String stationNo;
    
    /**
     *网点数量
     */
    private Integer stationAmount;
    
    /**
     * 打款账户银行名称
     */
    private String payAccountBankName;
    
    /**
     * 打款账户银行所在省份
     */
    private String bankProvince;
    
    /**
     * 打款账户所在城市
     */
    private String bankCity;

    /**
     *打款账户
     */
    private String payAccount;

    /**
     *打款账户户名
     */
    private String payAccountName;

    /**
     * 打款账户地址
     */
    private String payAccountAddress;
    
    /**
     * 联行hao
     */
    private String lianHangNo;
    
    /**
     *保证金账户余额
     */
    private BigDecimal bailBalance;
 
    /**
     *韵达评价
     */
    private String yundaexJudge;
    
    /**
     *借款人邮箱
     */
    private String custEmail;
   
    /**
     *实际控制人
     */
    private String controller;

    /**
     *实际控制人身份证号
     */
    private String controllerIdno;
    
    /**
     *实际控制人邮箱
     */
    private String controllerEmail;

    /**
     *实际控制人手机号码
     */
    private String controllerPhone;

    /**
     *实际控制人籍贯
     */
    private String controllerOrigin;
    
    /**
     *实际控制人与法人代表是否是同一人 （1：是   0：否）
     */
    private String controllerIsLegal;

    /**
     *法人
     */
    private String legalPerson;

    /**
     *法人代表邮箱
     */
    private String legalEmail;

    /**
     *法人代表手机号码
     */
    private String legalPhone;

    /**
     *开户人（法人：LEGAL     实际控制人：CONTROLLER   其他：OTHER  公司：COMPANY）
     */
    private String accountOwner;

    /**
     *开户人账户
     */
    private String accountOwnerName;

    /**
     *开户人邮箱
     */
    private String accountOwnerEmail;

    /**
     *开户人手机号码
     */
    private String accountOwnerPhone;
    
    /**
     *年检记录
     */
    private String qualifiedInspectionRecord;
    
    /**
     *申请企业与韵达签署特许经营加盟合同到期日
     */
    private String ranchiseContractDeadline;
    
    /**
     *短期借款
     */
    private BigDecimal shortLoan;

    /**
     * 借款类型
     */
    private String loanType;
    
    /**
     *借款来源
     */
    private String loanFrom;

    /**
     *代理资质
     */
    private String agentQualification;
    
    /**
     *组织机构代码号码
     */
    private String organizationNo;

    /**
     *组织机构证件ID
     */
    private String organizationMediaId;
    
    /**
     *营业执照号码
     */
    private String businessLicenceNo;

    /**
     *营业执照证件ID
     */
    private String businessLicenceMediaId;
    

    /**
     *税务登记证号码
     */
    private String taxRegistrationCertificateNo;

    /**
     *税务登记证证件ID
     */
    private String taxRegistrationCertificateMediaId;

    /**
     *法人代表身份证号码
     */
    private String legalIdno;

    /**
     * 法人身份证正面图片ID
     */
    private String legalIdnoFrontMediaId;
    
    /**
     *法人身份证反面图片Ｉｄ
     */
    private String legalIdnoBackMediaId;

    /**
     * 补充资料附件名
     */
    private String additionInformationMediaId;

    /**
     *是否有天猫、淘宝订单
     */
    private String whetherTbOrder;

    /**
     *天猫、淘宝订单占比
     */
    private String tbOrderRatio;

    /**
     *其他说明
     */
    private String otherExplain;


	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getOrganizationMediaId() {
		return organizationMediaId;
	}

	public void setOrganizationMediaId(String organizationMediaId) {
		this.organizationMediaId = organizationMediaId;
	}

	public String getBusinessLicenceNo() {
		return businessLicenceNo;
	}

	public void setBusinessLicenceNo(String businessLicenceNo) {
		this.businessLicenceNo = businessLicenceNo;
	}

	public String getBusinessLicenceMediaId() {
		return businessLicenceMediaId;
	}

	public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
		this.businessLicenceMediaId = businessLicenceMediaId;
	}

	public String getTaxRegistrationCertificateNo() {
		return taxRegistrationCertificateNo;
	}

	public void setTaxRegistrationCertificateNo(String taxRegistrationCertificateNo) {
		this.taxRegistrationCertificateNo = taxRegistrationCertificateNo;
	}

	public String getTaxRegistrationCertificateMediaId() {
		return taxRegistrationCertificateMediaId;
	}

	public void setTaxRegistrationCertificateMediaId(String taxRegistrationCertificateMediaId) {
		this.taxRegistrationCertificateMediaId = taxRegistrationCertificateMediaId;
	}

	public BigDecimal getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(BigDecimal bailBalance) {
		this.bailBalance = bailBalance;
	}

	public String getRanchiseContractDeadline() {
		return ranchiseContractDeadline;
	}

	public void setRanchiseContractDeadline(String ranchiseContractDeadline) {
		this.ranchiseContractDeadline = ranchiseContractDeadline;
	}

	public BigDecimal getShortLoan() {
		return shortLoan;
	}

	public void setShortLoan(BigDecimal shortLoan) {
		this.shortLoan = shortLoan;
	}

	public String getQualifiedInspectionRecord() {
		return qualifiedInspectionRecord;
	}

	public void setQualifiedInspectionRecord(String qualifiedInspectionRecord) {
		this.qualifiedInspectionRecord = qualifiedInspectionRecord;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPayAccountName() {
		return payAccountName;
	}

	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getControllerEmail() {
		return controllerEmail;
	}

	public void setControllerEmail(String controllerEmail) {
		this.controllerEmail = controllerEmail;
	}

	public String getControllerPhone() {
		return controllerPhone;
	}

	public void setControllerPhone(String controllerPhone) {
		this.controllerPhone = controllerPhone;
	}

	public String getControllerIsLegal() {
		return controllerIsLegal;
	}

	public void setControllerIsLegal(String controllerIsLegal) {
		this.controllerIsLegal = controllerIsLegal;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalEmail() {
		return legalEmail;
	}

	public void setLegalEmail(String legalEmail) {
		this.legalEmail = legalEmail;
	}

	public String getLegalPhone() {
		return legalPhone;
	}

	public void setLegalPhone(String legalPhone) {
		this.legalPhone = legalPhone;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getAccountOwnerName() {
		return accountOwnerName;
	}

	public void setAccountOwnerName(String accountOwnerName) {
		this.accountOwnerName = accountOwnerName;
	}

	public String getAccountOwnerEmail() {
		return accountOwnerEmail;
	}

	public void setAccountOwnerEmail(String accountOwnerEmail) {
		this.accountOwnerEmail = accountOwnerEmail;
	}

	public String getAccountOwnerPhone() {
		return accountOwnerPhone;
	}

	public void setAccountOwnerPhone(String accountOwnerPhone) {
		this.accountOwnerPhone = accountOwnerPhone;
	}

	public String getLegalIdno() {
		return legalIdno;
	}

	public void setLegalIdno(String legalIdno) {
		this.legalIdno = legalIdno;
	}

	public String getLegalIdnoFrontMediaId() {
		return legalIdnoFrontMediaId;
	}

	public void setLegalIdnoFrontMediaId(String legalIdnoFrontMediaId) {
		this.legalIdnoFrontMediaId = legalIdnoFrontMediaId;
	}

	public String getLegalIdnoBackMediaId() {
		return legalIdnoBackMediaId;
	}

	public void setLegalIdnoBackMediaId(String legalIdnoBackMediaId) {
		this.legalIdnoBackMediaId = legalIdnoBackMediaId;
	}

	public String getAdditionInformationMediaId() {
		return additionInformationMediaId;
	}

	public void setAdditionInformationMediaId(String additionInformationMediaId) {
		this.additionInformationMediaId = additionInformationMediaId;
	}

	public String getWhetherTbOrder() {
		return whetherTbOrder;
	}

	public void setWhetherTbOrder(String whetherTbOrder) {
		this.whetherTbOrder = whetherTbOrder;
	}

	public String getTbOrderRatio() {
		return tbOrderRatio;
	}

	public void setTbOrderRatio(String tbOrderRatio) {
		this.tbOrderRatio = tbOrderRatio;
	}

	public String getOtherExplain() {
		return otherExplain;
	}

	public void setOtherExplain(String otherExplain) {
		this.otherExplain = otherExplain;
	}

	public String getLianHangNo() {
		return lianHangNo;
	}

	public void setLianHangNo(String lianHangNo) {
		this.lianHangNo = lianHangNo;
	}

	public String getPayAccountAddress() {
		return payAccountAddress;
	}

	public void setPayAccountAddress(String payAccountAddress) {
		this.payAccountAddress = payAccountAddress;
	}

	public String getLoanFrom() {
		return loanFrom;
	}

	public void setLoanFrom(String loanFrom) {
		this.loanFrom = loanFrom;
	}

	public String getAgentQualification() {
		return agentQualification;
	}

	public void setAgentQualification(String agentQualification) {
		this.agentQualification = agentQualification;
	}

	public String getControllerIdno() {
		return controllerIdno;
	}

	public void setControllerIdno(String controllerIdno) {
		this.controllerIdno = controllerIdno;
	}

	public Integer getStationAmount() {
		return stationAmount;
	}

	public void setStationAmount(Integer stationAmount) {
		this.stationAmount = stationAmount;
	}

	public String getPayAccountBankName() {
		return payAccountBankName;
	}

	public void setPayAccountBankName(String payAccountBankName) {
		this.payAccountBankName = payAccountBankName;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYundaexJudge() {
		return yundaexJudge;
	}

	public void setYundaexJudge(String yundaexJudge) {
		this.yundaexJudge = yundaexJudge;
	}

	public String getControllerOrigin() {
		return controllerOrigin;
	}

	public void setControllerOrigin(String controllerOrigin) {
		this.controllerOrigin = controllerOrigin;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	
    
}
