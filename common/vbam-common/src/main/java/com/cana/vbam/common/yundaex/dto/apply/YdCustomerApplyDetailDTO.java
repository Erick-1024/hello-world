package com.cana.vbam.common.yundaex.dto.apply;

import java.io.Serializable;
import java.util.Date;

/**
 * 审核列表详情页的信息
 * 
 * @author xiaoyu
 *
 */
public class YdCustomerApplyDetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 申请日期
	 */
	private Date applyDate;

	/**
	 * 站点编号
	 */
	private String stationNo;

	/**
	 * 网点数量
	 */
	private Integer stationAmount;

	/**
	 * 站点负责人/公司名称
	 */
	private String stationMgr;

	/**
	 * 站点名称
	 */
	private String stationName;

	/**
	 * 借款人姓名
	 */
	private String custName;

	/**
	 * 借款人身份证号
	 */
	private String custIdno;

	/**
	 * 借款人手机号
	 */
	private String custPhone;

	/**
	 * 借款人邮箱
	 */
	private String custEmail;

	/**
	 * 站点经营地址-省
	 */
	private String province;

	/**
	 * 站点经营地址-市
	 */
	private String city;

	/**
	 * 站点经营地址-详细地址
	 */
	private String address;

	/**
	 * 经营地址 （详细地址）
	 */
	private String detailAddress;

	/**
	 * 加盟年限
	 */
	private Long busiLimit;

	/**
	 * 区域代码
	 */
	private String regioncode;

	/**
	 * 申请额度
	 */
	private String applyCreditLimit;

	/**
	 * 资金用途
	 */
	private String fundsUse;

	/**
	 * 意向期限
	 */
	private Long loanLimit;

	/**
	 * 增信方式
	 */
	private String addCredit;

	/**
	 * 4种还款方式（ 1：1个月，随时回购 2：3个月，按月支付服务费，到期回购本金 3：6个月，类等额本金方式回购 4：6个月，类等额本息方式回购）
	 */
	private String repaymentType;

	/**
	 * 银行账户
	 */
	private String bankAccount;

	/**
	 * 银行账户户名
	 */
	private String bankAccountName;

	/**
	 * 开户行名称和地址
	 */
	private String bankAccountAddress;

	/**
	 * 组织机构代码号码
	 */
	private String organizationNo;

	/**
	 * 组织机构证件ID
	 */
	private String organizationMediaId;

	/**
	 * 营业执照号码
	 */
	private String businessLicenceNo;

	/**
	 * 营业执照证件ID
	 */
	private String businessLicenceMediaId;

	/**
	 * 税务登记证号码
	 */
	private String taxRegistrationCertificateNo;

	/**
	 * 税务登记证证件ID
	 */
	private String taxRegistrationCertificateMediaId;

	/**
	 * 保证金账户余额
	 */
	private String bailBalance;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 是否通过人工审核（"WAIT"：未审核，"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
	 */
	private String accessManualState;

	/**
	 * 人工审核时的备注
	 */
	private String manualAuditRemarks;

	/**
	 * 是否通过系统审核（"WAIT"：未审核，"ACCESS"：通过系统审核，"NOTACCESS"：不通过系统审核）
	 */
	private String accessAutomaticState;

	/**
	 * 系统审核备注，系统审核不通过时填写不通过内容。
	 */
	private String automaticAuditRemarks;

	/**
	 * 审核人ID
	 */
	private String auditorId;

	/**
	 * 一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)
	 */
	private Integer consistencyCheck;

	/**
	 * 是否有网点信息（Y:有 N:没有 W:拉取信息失败）
	 */
	private String whetherStationInfo;

	/**
	 * 拉取站点信息结果
	 */
	private String reasonW;

	/**
	 * 准入规则批次号
	 */
	private Integer automaticAuditRuleBatchNo;

	/**
	 * 人工审核规则批次号
	 */
	private Integer manualAuditRuleBatchNo;

	/**
	 * 额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）
	 */
	private String creditLimitGenerateState;

	/**
	 * 是否存在网络负面信息
	 */
	private String negativeNetwork;

	/**
	 * 申请企业与韵达签署特许经营加盟合同到期日
	 */
	private Date ranchiseContractDeadline;

	/**
	 * 短期借款
	 */
	private String shortLoan;

	/**
	 * 借款类型
	 */
	private String loanType;

	private String loanTypeDesc;

	/**
	 * 借款来源
	 */
	private String loanFrom;

	/**
	 * 代理资质
	 */
	private String agentQualification;

	/**
	 * 法院被执行人信息
	 */
	private String executeIndividualInfo;

	/**
	 * 年检记录
	 */
	private String qualifiedInspectionRecord;

	private String qualifiedInspectionRecordDesc;

	/**
	 * 打款账户
	 */
	private String payAccount;

	/**
	 * 打款账户户名
	 */
	private String payAccountName;

	/**
	 * 打款账户地址
	 */
	private String payAccountAddress;

	/**
	 * 支行联行号
	 */
	private String lianHangNo;

	/**
	 * 实际控制人
	 */
	private String controller;

	/**
	 * 实际控制人身份证号
	 */
	private String controllerIdno;

	/**
	 * 实际控制人邮箱
	 */
	private String controllerEmail;

	/**
	 * 实际控制人手机号码
	 */
	private String controllerPhone;

	/**
	 * 实际控制人籍贯
	 */
	private String controllerOrigin;

	/**
	 * 实际控制人与法人代表是否是同一人 （1：是 0：否）
	 */
	private String controllerIsLegal;

	/**
	 * 法人
	 */
	private String legalPerson;

	/**
	 * 法人代表邮箱
	 */
	private String legalEmail;

	/**
	 * 法人代表手机号码
	 */
	private String legalPhone;

	/**
	 * 开户人（法人：LEGAL 实际控制人：CONTROLLER 其他：OTHER）
	 */
	private String accountOwner;

	private String accountOwnerDesc;

	/**
	 * 开户人账户
	 */
	private String accountOwnerName;

	/**
	 * 开户人邮箱
	 */
	private String accountOwnerEmail;

	/**
	 * 开户人手机号码
	 */
	private String accountOwnerPhone;

	/**
	 * 法人代表身份证号码
	 */
	private String legalIdno;

	/**
	 * 法人代表身份证正面图片Ｉｄ
	 */
	private String legalIdnoFrontMediaId;

	/**
	 * 法人身份证反面图片Ｉｄ
	 */
	private String legalIdnoBackMediaId;

	/**
	 * 补充资料附件ID
	 */
	private String additionInformationMediaId;

	/**
	 * 是否有天猫、淘宝订单
	 */
	private String whetherTbOrder;

	/**
	 * 天猫、淘宝订单占比
	 */
	private String tbOrderRatio;

	/**
	 * 韵达说明
	 */
	private String yundaexExplain;
	/**
	 * 其他说明
	 */
	private String otherExplain;

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
	 * 韵达评价
	 */
	private String yundaexJudge;

	private String yundaexJudgeDesc;

	/**
	 * 站点地址区域 （市区 集镇 村镇）
	 */
	private String stationAddress;
	private String stationAddressDesc;

	/**
	 * 申请类型
	 */
	private String applyType;

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStationMgr() {
		return stationMgr;
	}

	public void setStationMgr(String stationMgr) {
		this.stationMgr = stationMgr;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustIdno() {
		return custIdno;
	}

	public void setCustIdno(String custIdno) {
		this.custIdno = custIdno;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Long getBusiLimit() {
		return busiLimit;
	}

	public void setBusiLimit(Long busiLimit) {
		this.busiLimit = busiLimit;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public String getApplyCreditLimit() {
		return applyCreditLimit;
	}

	public void setApplyCreditLimit(String applyCreditLimit) {
		this.applyCreditLimit = applyCreditLimit;
	}

	public String getFundsUse() {
		return fundsUse;
	}

	public void setFundsUse(String fundsUse) {
		this.fundsUse = fundsUse;
	}

	public Long getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(Long loanLimit) {
		this.loanLimit = loanLimit;
	}

	public String getAddCredit() {
		return addCredit;
	}

	public void setAddCredit(String addCredit) {
		this.addCredit = addCredit;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountAddress() {
		return bankAccountAddress;
	}

	public void setBankAccountAddress(String bankAccountAddress) {
		this.bankAccountAddress = bankAccountAddress;
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

	public String getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(String bailBalance) {
		this.bailBalance = bailBalance;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAccessManualState() {
		return accessManualState;
	}

	public void setAccessManualState(String accessManualState) {
		this.accessManualState = accessManualState;
	}

	public String getManualAuditRemarks() {
		return manualAuditRemarks;
	}

	public void setManualAuditRemarks(String manualAuditRemarks) {
		this.manualAuditRemarks = manualAuditRemarks;
	}

	public String getAccessAutomaticState() {
		return accessAutomaticState;
	}

	public void setAccessAutomaticState(String accessAutomaticState) {
		this.accessAutomaticState = accessAutomaticState;
	}

	public String getAutomaticAuditRemarks() {
		return automaticAuditRemarks;
	}

	public void setAutomaticAuditRemarks(String automaticAuditRemarks) {
		this.automaticAuditRemarks = automaticAuditRemarks;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public Integer getConsistencyCheck() {
		return consistencyCheck;
	}

	public void setConsistencyCheck(Integer consistencyCheck) {
		this.consistencyCheck = consistencyCheck;
	}

	public String getWhetherStationInfo() {
		return whetherStationInfo;
	}

	public void setWhetherStationInfo(String whetherStationInfo) {
		this.whetherStationInfo = whetherStationInfo;
	}

	public String getReasonW() {
		return reasonW;
	}

	public void setReasonW(String reasonW) {
		this.reasonW = reasonW;
	}

	public Integer getAutomaticAuditRuleBatchNo() {
		return automaticAuditRuleBatchNo;
	}

	public void setAutomaticAuditRuleBatchNo(Integer automaticAuditRuleBatchNo) {
		this.automaticAuditRuleBatchNo = automaticAuditRuleBatchNo;
	}

	public Integer getManualAuditRuleBatchNo() {
		return manualAuditRuleBatchNo;
	}

	public void setManualAuditRuleBatchNo(Integer manualAuditRuleBatchNo) {
		this.manualAuditRuleBatchNo = manualAuditRuleBatchNo;
	}

	public String getCreditLimitGenerateState() {
		return creditLimitGenerateState;
	}

	public void setCreditLimitGenerateState(String creditLimitGenerateState) {
		this.creditLimitGenerateState = creditLimitGenerateState;
	}

	public String getNegativeNetwork() {
		return negativeNetwork;
	}

	public void setNegativeNetwork(String negativeNetwork) {
		this.negativeNetwork = negativeNetwork;
	}

	public Date getRanchiseContractDeadline() {
		return ranchiseContractDeadline;
	}

	public void setRanchiseContractDeadline(Date ranchiseContractDeadline) {
		this.ranchiseContractDeadline = ranchiseContractDeadline;
	}

	public String getShortLoan() {
		return shortLoan;
	}

	public void setShortLoan(String shortLoan) {
		this.shortLoan = shortLoan;
	}

	public String getExecuteIndividualInfo() {
		return executeIndividualInfo;
	}

	public void setExecuteIndividualInfo(String executeIndividualInfo) {
		this.executeIndividualInfo = executeIndividualInfo;
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

	public String getPayAccountAddress() {
		return payAccountAddress;
	}

	public void setPayAccountAddress(String payAccountAddress) {
		this.payAccountAddress = payAccountAddress;
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

	public String getAccountOwnerDesc() {
		return accountOwnerDesc;
	}

	public void setAccountOwnerDesc(String accountOwnerDesc) {
		this.accountOwnerDesc = accountOwnerDesc;
	}

	public String getLianHangNo() {
		return lianHangNo;
	}

	public void setLianHangNo(String lianHangNo) {
		this.lianHangNo = lianHangNo;
	}

	public String getControllerIdno() {
		return controllerIdno;
	}

	public void setControllerIdno(String controllerIdno) {
		this.controllerIdno = controllerIdno;
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

	public String getControllerOrigin() {
		return controllerOrigin;
	}

	public void setControllerOrigin(String controllerOrigin) {
		this.controllerOrigin = controllerOrigin;
	}

	public String getYundaexJudge() {
		return yundaexJudge;
	}

	public void setYundaexJudge(String yundaexJudge) {
		this.yundaexJudge = yundaexJudge;
	}

	public String getYundaexExplain() {
		return yundaexExplain;
	}

	public void setYundaexExplain(String yundaexExplain) {
		this.yundaexExplain = yundaexExplain;
	}

	public String getStationAddress() {
		return stationAddress;
	}

	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}

	public String getStationAddressDesc() {
		return stationAddressDesc;
	}

	public void setStationAddressDesc(String stationAddressDesc) {
		this.stationAddressDesc = stationAddressDesc;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanTypeDesc() {
		return loanTypeDesc;
	}

	public void setLoanTypeDesc(String loanTypeDesc) {
		this.loanTypeDesc = loanTypeDesc;
	}

	public String getQualifiedInspectionRecordDesc() {
		return qualifiedInspectionRecordDesc;
	}

	public void setQualifiedInspectionRecordDesc(String qualifiedInspectionRecordDesc) {
		this.qualifiedInspectionRecordDesc = qualifiedInspectionRecordDesc;
	}

	public String getYundaexJudgeDesc() {
		return yundaexJudgeDesc;
	}

	public void setYundaexJudgeDesc(String yundaexJudgeDesc) {
		this.yundaexJudgeDesc = yundaexJudgeDesc;
	}

}
