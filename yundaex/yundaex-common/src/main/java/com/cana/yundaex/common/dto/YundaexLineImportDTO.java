package com.cana.yundaex.common.dto;

import java.io.Serializable;
import java.util.Date;

public class YundaexLineImportDTO implements Serializable {

	private static final long serialVersionUID = -7179246256892439206L;

	/**
	 * 站点编号
	 */
	private String stationNo;

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
	 * 加盟年限
	 */
	private Long busiLimit;

	/**
	 * 区域代码
	 */
	private String regioncode;

	/**
	 * 意向额度
	 */
	private Long applyCreditLimit;

	/**
	 * 意向期限
	 */
	private Long loanLimit;

	/**
	 * 增信方式
	 */
	private String addCredit;

	/**
	 * 还款方式
	 */
	private String repaymentType;

	/**
	 * 组织机构代码号码
	 */
	private String organizationNo;

	/**
	 * 组织机构代码编码
	 */
	private String organizationMediaId;

	/**
	 * 营业执照号码
	 */
	private String businessLicenceNo;

	/**
	 * 营业执照编码
	 */
	private String businessLicenceMediaId;

	/**
	 * 税务登记证号码
	 */
	private String taxRegistrationCertificateNo;

	/**
	 * 税务登记证编码
	 */
	private String taxRegistrationCertificateMediaId;

	/**
	 * 法人身份证号码
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
	 * 借款人邮箱
	 */
	private String custEmail;

	/**
	 * 是否有网点信息（Y:有 N:没有）
	 */
	private String whetherStationInfo;

	/**
	 * 拉取网点次数
	 */
	private Integer pullCount;

	/**
	 * 保证金账户余额
	 */
	private String bailBalance;

	/**
	 * 其它说明
	 */
	private String explainInfo;

	/**
	 * whether_station_info拉取信息失败的原因（仅当whether_station_info='W'是时候）
	 */
	private String reasonW;

	/**
	 * 韵达评价
	 */
	private String yundaexJudge;

	/**
	 * 打款账户
	 */
	private String payAccount;

	/**
	 * 打款账户名称
	 */
	private String payAccountName;
	
	/**
	 * 打款账户地址
	 */
	private String payAccountAddress;

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPayAccountAddress() {
		return payAccountAddress;
	}

	public void setPayAccountAddress(String payAccountAddress) {
		this.payAccountAddress = payAccountAddress;
	}

	public String getYundaexJudge() {
		return yundaexJudge;
	}

	public void setYundaexJudge(String yundaexJudge) {
		this.yundaexJudge = yundaexJudge;
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

	public Long getApplyCreditLimit() {
		return applyCreditLimit;
	}

	public void setApplyCreditLimit(Long applyCreditLimit) {
		this.applyCreditLimit = applyCreditLimit;
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

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getWhetherStationInfo() {
		return whetherStationInfo;
	}

	public void setWhetherStationInfo(String whetherStationInfo) {
		this.whetherStationInfo = whetherStationInfo;
	}

	public Integer getPullCount() {
		return pullCount;
	}

	public void setPullCount(Integer pullCount) {
		this.pullCount = pullCount;
	}

	public String getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(String bailBalance) {
		this.bailBalance = bailBalance;
	}

	public String getExplainInfo() {
		return explainInfo;
	}

	public void setExplainInfo(String explainInfo) {
		this.explainInfo = explainInfo;
	}

	public String getReasonW() {
		return reasonW;
	}

	public void setReasonW(String reasonW) {
		this.reasonW = reasonW;
	}

	public String getPayAccountName() {
		return payAccountName;
	}

	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}

}
