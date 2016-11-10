package com.cana.yundaex.common.dto.test;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class YundaexCustomerAapplyTestDto implements Serializable{

		/**
	     *主键
	     */
	    private String id;

	    /**
	     *申请日期
	     */
	    private Date applyDate;

	    /**
	     *网点编号
	     */
	    private String stationNo;

	    /**
	     *网点数量
	     */
	    private Integer stationAmount;

	    /**
	     *站点负责人/公司名称
	     */
	    private String stationMgr;

	    /**
	     *站点名称
	     */
	    private String stationName;

	    /**
	     *借款人姓名
	     */
	    private String custName;

	    /**
	     *借款人身份证号
	     */
	    private String custIdno;

	    /**
	     *借款人手机号
	     */
	    private String custPhone;

	    /**
	     *借款人邮箱
	     */
	    private String custEmail;

	    /**
	     *站点经营地址-省
	     */
	    private String province;

	    /**
	     *站点经营地址-市
	     */
	    private String city;

	    /**
	     *站点经营地址-详细地址
	     */
	    private String address;

	    /**
	     *加盟年限
	     */
	    private Long busiLimit;

	    /**
	     *区域代码
	     */
	    private String regioncode;

	    /**
	     *申请额度
	     */
	    private Long applyCreditLimit;

	    /**
	     *资金用途
	     */
	    private String fundsUse;

	    /**
	     *意向期限
	     */
	    private String loanLimit;

	    /**
	     *增信方式
	     */
	    private String addCredit;

	    /**
	     *4种还款方式（ 1：1个月，随时回购   2：3个月，按月支付服务费，到期回购本金   3：6个月，类等额本金方式回购   4：6个月，类等额本息方式回购）
	     */
	    private String repaymentType;

	    /**
	     *银行
	     */
	    private String bankAccount;

	    /**
	     *银行所在省份
	     */
	    private String bankAccountName;

	    /**
	     *银行所在城市
	     */
	    private String bankAccountAddress;

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
	     *保证金账户余额
	     */
	    private Long bailBalance;

	    /**
	     *更新时间
	     */
	    private Date updateTime;

	    /**
	     *创建时间
	     */
	    private Date createTime;

	    /**
	     *是否通过人工审核（"WAIT"：未审核，"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
	     */
	    private String accessManualState;

	    /**
	     *人工审核时的备注
	     */
	    private String manualAuditRemarks;

	    /**
	     *是否通过系统审核（"WAIT"：未审核，"ACCESS"：通过系统审核，"NOTACCESS"：不通过系统审核）
	     */
	    private String accessAutomaticState;

	    /**
	     *系统审核备注，系统审核不通过时填写不通过内容。
	     */
	    private String automaticAuditRemarks;

	    /**
	     *审核人ID
	     */
	    private String auditorId;

	    /**
	     *一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)
	     */
	    private Integer consistencyCheck;

	    /**
	     *是否有网点信息（Y:有　N:没有　W:拉取信息失败）
	     */
	    private String whetherStationInfo;

	    /**
	     *拉取站点信息结果
	     */
	    private String reasonW;

	    /**
	     *准入规则批次号
	     */
	    private Integer automaticAuditRuleBatchNo;

	    /**
	     *人工审核规则批次号
	     */
	    private Integer manualAuditRuleBatchNo;

	    /**
	     *额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）
	     */
	    private String creditLimitGenerateState;

	    /**
	     *是否存在网络负面信息
	     */
	    private String negativeNetwork;

	    /**
	     *申请企业与韵达签署特许经营加盟合同到期日
	     */
	    private Date ranchiseContractDeadline;

	    /**
	     *法院被执行人信息
	     */
	    private String executeIndividualInfo;

	    /**
	     *年检记录
	     */
	    private String qualifiedInspectionRecord;

	    /**
	     *打款账户
	     */
	    private String payAccount;

	    /**
	     *打款账户户名
	     */
	    private String payAccountName;

	    /**
	     *打款账户地址
	     */
	    private String payAccountAddress;

	    /**
	     *开户行联行号
	     */
	    private String lianHangNo;

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
	     *开户人（法人：LEGAL     实际控制人：CONTROLLER   其他：OTHER   公司：COMPANY）
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
	     *短期借款
	     */
	    private Long shortLoan;

	    /**
	     *借款来源
	     */
	    private String loanFrom;

	    /**
	     *代理资质
	     */
	    private String agentQualification;

	    /**
	     *法人代表身份证号码
	     */
	    private String legalIdno;

	    /**
	     *法人代表身份证正面图片Ｉｄ
	     */
	    private String legalIdnoFrontMediaId;

	    /**
	     *法人身份证反面图片Ｉｄ
	     */
	    private String legalIdnoBackMediaId;

	    /**
	     *补充资料附件ID
	     */
	    private String additionInformationMediaId;

	    /**
	     *是否有天猫、淘宝订单
	     */
	    private String whetherTbOrder;

	    /**
	     *天猫、淘宝订单占比
	     */
	    private BigDecimal tbOrderRatio;

	    /**
	     *韵达说明
	     */
	    private String yundaexExplain;

	    /**
	     *其他说明
	     */
	    private String otherExplain;

	    /**
	     *打款账户银行名称
	     */
	    private String payAccountBankName;

	    /**
	     *打款账户银行所在省份
	     */
	    private String bankProvince;

	    /**
	     *打款账户所在城市
	     */
	    private String bankCity;

	    /**
	     *韵达评价
	     */
	    private String yundaexJudge;

	    /**
	     *站点地址区域 （市区   集镇   村镇）
	     */
	    private String stationAddress;

	    /**
	     *评级状态（WAIT   ACCESS  NOTACCESS）
	     */
	    private String gradeState;

	    /**
	     *评级审核备注
	     */
	    private String gradeStateRemarks;


		/*// field name
		private String fieldname;

		// field value
		private Object value;

		// field value's class type
		private Class clazz;

		private List<String> errorMsg = new ArrayList<String>();

		public String getFieldname() {
			return fieldname;
		}

		public void setFieldname(String fieldname) {
			this.fieldname = fieldname;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public Class getClazz() {
			return clazz;
		}

		public void setClazz(Class clazz) {
			this.clazz = clazz;
		}

		public List<String> getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(List<String> errorMsg) {
			this.errorMsg = errorMsg;
		}

		public YundaexCustomerAapplyTestDto() {
	             super();
	         }

		public YundaexCustomerAapplyTestDto(String fieldname, Object value, Class clazz) {
	            super();
	             this.fieldname = fieldname;
	             this.value = value;
	             this.clazz = clazz;
	         }

		private YundaexCustomerAapplyTestDto(String fieldname, List<String> errorMsg) {
	            super();
	            this.fieldname = fieldname;
	             this.errorMsg = errorMsg;
	        }*/

		private static final long serialVersionUID = 1L;
		/**
	     *主键
	     */
	    public String getId() {
	        return id;
	    }

	    /**
	     *主键
	     */
	    public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }

	    /**
	     *申请日期
	     */
	    public Date getApplyDate() {
	        return applyDate;
	    }

	    /**
	     *申请日期
	     */
	    public void setApplyDate(Date applyDate) {
	        this.applyDate = applyDate;
	    }

	    /**
	     *网点编号
	     */
	    public String getStationNo() {
	        return stationNo;
	    }

	    /**
	     *网点编号
	     */
	    public void setStationNo(String stationNo) {
	        this.stationNo = stationNo == null ? null : stationNo.trim();
	    }

	    /**
	     *网点数量
	     */
	    public Integer getStationAmount() {
	        return stationAmount;
	    }

	    /**
	     *网点数量
	     */
	    public void setStationAmount(Integer stationAmount) {
	        this.stationAmount = stationAmount;
	    }

	    /**
	     *站点负责人/公司名称
	     */
	    public String getStationMgr() {
	        return stationMgr;
	    }

	    /**
	     *站点负责人/公司名称
	     */
	    public void setStationMgr(String stationMgr) {
	        this.stationMgr = stationMgr == null ? null : stationMgr.trim();
	    }

	    /**
	     *站点名称
	     */
	    public String getStationName() {
	        return stationName;
	    }

	    /**
	     *站点名称
	     */
	    public void setStationName(String stationName) {
	        this.stationName = stationName == null ? null : stationName.trim();
	    }

	    /**
	     *借款人姓名
	     */
	    public String getCustName() {
	        return custName;
	    }

	    /**
	     *借款人姓名
	     */
	    public void setCustName(String custName) {
	        this.custName = custName == null ? null : custName.trim();
	    }

	    /**
	     *借款人身份证号
	     */
	    public String getCustIdno() {
	        return custIdno;
	    }

	    /**
	     *借款人身份证号
	     */
	    public void setCustIdno(String custIdno) {
	        this.custIdno = custIdno == null ? null : custIdno.trim();
	    }

	    /**
	     *借款人手机号
	     */
	    public String getCustPhone() {
	        return custPhone;
	    }

	    /**
	     *借款人手机号
	     */
	    public void setCustPhone(String custPhone) {
	        this.custPhone = custPhone == null ? null : custPhone.trim();
	    }

	    /**
	     *借款人邮箱
	     */
	    public String getCustEmail() {
	        return custEmail;
	    }

	    /**
	     *借款人邮箱
	     */
	    public void setCustEmail(String custEmail) {
	        this.custEmail = custEmail == null ? null : custEmail.trim();
	    }

	    /**
	     *站点经营地址-省
	     */
	    public String getProvince() {
	        return province;
	    }

	    /**
	     *站点经营地址-省
	     */
	    public void setProvince(String province) {
	        this.province = province == null ? null : province.trim();
	    }

	    /**
	     *站点经营地址-市
	     */
	    public String getCity() {
	        return city;
	    }

	    /**
	     *站点经营地址-市
	     */
	    public void setCity(String city) {
	        this.city = city == null ? null : city.trim();
	    }

	    /**
	     *站点经营地址-详细地址
	     */
	    public String getAddress() {
	        return address;
	    }

	    /**
	     *站点经营地址-详细地址
	     */
	    public void setAddress(String address) {
	        this.address = address == null ? null : address.trim();
	    }

	    /**
	     *加盟年限
	     */
	    public Long getBusiLimit() {
	        return busiLimit;
	    }

	    /**
	     *加盟年限
	     */
	    public void setBusiLimit(Long busiLimit) {
	        this.busiLimit = busiLimit;
	    }

	    /**
	     *区域代码
	     */
	    public String getRegioncode() {
	        return regioncode;
	    }

	    /**
	     *区域代码
	     */
	    public void setRegioncode(String regioncode) {
	        this.regioncode = regioncode == null ? null : regioncode.trim();
	    }

	    /**
	     *申请额度
	     */
	    public Long getApplyCreditLimit() {
	        return applyCreditLimit;
	    }

	    /**
	     *申请额度
	     */
	    public void setApplyCreditLimit(Long applyCreditLimit) {
	        this.applyCreditLimit = applyCreditLimit;
	    }

	    /**
	     *资金用途
	     */
	    public String getFundsUse() {
	        return fundsUse;
	    }

	    /**
	     *资金用途
	     */
	    public void setFundsUse(String fundsUse) {
	        this.fundsUse = fundsUse == null ? null : fundsUse.trim();
	    }

	    /**
	     *意向期限
	     */
	    public String getLoanLimit() {
	        return loanLimit;
	    }

	    /**
	     *意向期限
	     */
	    public void setLoanLimit(String loanLimit) {
	        this.loanLimit = loanLimit == null ? null : loanLimit.trim();
	    }

	    /**
	     *增信方式
	     */
	    public String getAddCredit() {
	        return addCredit;
	    }

	    /**
	     *增信方式
	     */
	    public void setAddCredit(String addCredit) {
	        this.addCredit = addCredit == null ? null : addCredit.trim();
	    }

	    /**
	     *4种还款方式（ 1：1个月，随时回购   2：3个月，按月支付服务费，到期回购本金   3：6个月，类等额本金方式回购   4：6个月，类等额本息方式回购）
	     */
	    public String getRepaymentType() {
	        return repaymentType;
	    }

	    /**
	     *4种还款方式（ 1：1个月，随时回购   2：3个月，按月支付服务费，到期回购本金   3：6个月，类等额本金方式回购   4：6个月，类等额本息方式回购）
	     */
	    public void setRepaymentType(String repaymentType) {
	        this.repaymentType = repaymentType == null ? null : repaymentType.trim();
	    }

	    /**
	     *银行
	     */
	    public String getBankAccount() {
	        return bankAccount;
	    }

	    /**
	     *银行
	     */
	    public void setBankAccount(String bankAccount) {
	        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
	    }

	    /**
	     *银行所在省份
	     */
	    public String getBankAccountName() {
	        return bankAccountName;
	    }

	    /**
	     *银行所在省份
	     */
	    public void setBankAccountName(String bankAccountName) {
	        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
	    }

	    /**
	     *银行所在城市
	     */
	    public String getBankAccountAddress() {
	        return bankAccountAddress;
	    }

	    /**
	     *银行所在城市
	     */
	    public void setBankAccountAddress(String bankAccountAddress) {
	        this.bankAccountAddress = bankAccountAddress == null ? null : bankAccountAddress.trim();
	    }

	    /**
	     *组织机构代码号码
	     */
	    public String getOrganizationNo() {
	        return organizationNo;
	    }

	    /**
	     *组织机构代码号码
	     */
	    public void setOrganizationNo(String organizationNo) {
	        this.organizationNo = organizationNo == null ? null : organizationNo.trim();
	    }

	    /**
	     *组织机构证件ID
	     */
	    public String getOrganizationMediaId() {
	        return organizationMediaId;
	    }

	    /**
	     *组织机构证件ID
	     */
	    public void setOrganizationMediaId(String organizationMediaId) {
	        this.organizationMediaId = organizationMediaId == null ? null : organizationMediaId.trim();
	    }

	    /**
	     *营业执照号码
	     */
	    public String getBusinessLicenceNo() {
	        return businessLicenceNo;
	    }

	    /**
	     *营业执照号码
	     */
	    public void setBusinessLicenceNo(String businessLicenceNo) {
	        this.businessLicenceNo = businessLicenceNo == null ? null : businessLicenceNo.trim();
	    }

	    /**
	     *营业执照证件ID
	     */
	    public String getBusinessLicenceMediaId() {
	        return businessLicenceMediaId;
	    }

	    /**
	     *营业执照证件ID
	     */
	    public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
	        this.businessLicenceMediaId = businessLicenceMediaId == null ? null : businessLicenceMediaId.trim();
	    }

	    /**
	     *税务登记证号码
	     */
	    public String getTaxRegistrationCertificateNo() {
	        return taxRegistrationCertificateNo;
	    }

	    /**
	     *税务登记证号码
	     */
	    public void setTaxRegistrationCertificateNo(String taxRegistrationCertificateNo) {
	        this.taxRegistrationCertificateNo = taxRegistrationCertificateNo == null ? null : taxRegistrationCertificateNo.trim();
	    }

	    /**
	     *税务登记证证件ID
	     */
	    public String getTaxRegistrationCertificateMediaId() {
	        return taxRegistrationCertificateMediaId;
	    }

	    /**
	     *税务登记证证件ID
	     */
	    public void setTaxRegistrationCertificateMediaId(String taxRegistrationCertificateMediaId) {
	        this.taxRegistrationCertificateMediaId = taxRegistrationCertificateMediaId == null ? null : taxRegistrationCertificateMediaId.trim();
	    }

	    /**
	     *保证金账户余额
	     */
	    public Long getBailBalance() {
	        return bailBalance;
	    }

	    /**
	     *保证金账户余额
	     */
	    public void setBailBalance(Long bailBalance) {
	        this.bailBalance = bailBalance;
	    }

	    /**
	     *更新时间
	     */
	    public Date getUpdateTime() {
	        return updateTime;
	    }

	    /**
	     *更新时间
	     */
	    public void setUpdateTime(Date updateTime) {
	        this.updateTime = updateTime;
	    }

	    /**
	     *创建时间
	     */
	    public Date getCreateTime() {
	        return createTime;
	    }

	    /**
	     *创建时间
	     */
	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    /**
	     *是否通过人工审核（"WAIT"：未审核，"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
	     */
	    public String getAccessManualState() {
	        return accessManualState;
	    }

	    /**
	     *是否通过人工审核（"WAIT"：未审核，"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
	     */
	    public void setAccessManualState(String accessManualState) {
	        this.accessManualState = accessManualState == null ? null : accessManualState.trim();
	    }

	    /**
	     *人工审核时的备注
	     */
	    public String getManualAuditRemarks() {
	        return manualAuditRemarks;
	    }

	    /**
	     *人工审核时的备注
	     */
	    public void setManualAuditRemarks(String manualAuditRemarks) {
	        this.manualAuditRemarks = manualAuditRemarks == null ? null : manualAuditRemarks.trim();
	    }

	    /**
	     *是否通过系统审核（"WAIT"：未审核，"ACCESS"：通过系统审核，"NOTACCESS"：不通过系统审核）
	     */
	    public String getAccessAutomaticState() {
	        return accessAutomaticState;
	    }

	    /**
	     *是否通过系统审核（"WAIT"：未审核，"ACCESS"：通过系统审核，"NOTACCESS"：不通过系统审核）
	     */
	    public void setAccessAutomaticState(String accessAutomaticState) {
	        this.accessAutomaticState = accessAutomaticState == null ? null : accessAutomaticState.trim();
	    }

	    /**
	     *系统审核备注，系统审核不通过时填写不通过内容。
	     */
	    public String getAutomaticAuditRemarks() {
	        return automaticAuditRemarks;
	    }

	    /**
	     *系统审核备注，系统审核不通过时填写不通过内容。
	     */
	    public void setAutomaticAuditRemarks(String automaticAuditRemarks) {
	        this.automaticAuditRemarks = automaticAuditRemarks == null ? null : automaticAuditRemarks.trim();
	    }

	    /**
	     *审核人ID
	     */
	    public String getAuditorId() {
	        return auditorId;
	    }

	    /**
	     *审核人ID
	     */
	    public void setAuditorId(String auditorId) {
	        this.auditorId = auditorId == null ? null : auditorId.trim();
	    }

	    /**
	     *一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)
	     */
	    public Integer getConsistencyCheck() {
	        return consistencyCheck;
	    }

	    /**
	     *一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)
	     */
	    public void setConsistencyCheck(Integer consistencyCheck) {
	        this.consistencyCheck = consistencyCheck;
	    }

	    /**
	     *是否有网点信息（Y:有　N:没有　W:拉取信息失败）
	     */
	    public String getWhetherStationInfo() {
	        return whetherStationInfo;
	    }

	    /**
	     *是否有网点信息（Y:有　N:没有　W:拉取信息失败）
	     */
	    public void setWhetherStationInfo(String whetherStationInfo) {
	        this.whetherStationInfo = whetherStationInfo == null ? null : whetherStationInfo.trim();
	    }

	    /**
	     *拉取站点信息结果
	     */
	    public String getReasonW() {
	        return reasonW;
	    }

	    /**
	     *拉取站点信息结果
	     */
	    public void setReasonW(String reasonW) {
	        this.reasonW = reasonW == null ? null : reasonW.trim();
	    }

	    /**
	     *准入规则批次号
	     */
	    public Integer getAutomaticAuditRuleBatchNo() {
	        return automaticAuditRuleBatchNo;
	    }

	    /**
	     *准入规则批次号
	     */
	    public void setAutomaticAuditRuleBatchNo(Integer automaticAuditRuleBatchNo) {
	        this.automaticAuditRuleBatchNo = automaticAuditRuleBatchNo;
	    }

	    /**
	     *人工审核规则批次号
	     */
	    public Integer getManualAuditRuleBatchNo() {
	        return manualAuditRuleBatchNo;
	    }

	    /**
	     *人工审核规则批次号
	     */
	    public void setManualAuditRuleBatchNo(Integer manualAuditRuleBatchNo) {
	        this.manualAuditRuleBatchNo = manualAuditRuleBatchNo;
	    }

	    /**
	     *额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）
	     */
	    public String getCreditLimitGenerateState() {
	        return creditLimitGenerateState;
	    }

	    /**
	     *额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）
	     */
	    public void setCreditLimitGenerateState(String creditLimitGenerateState) {
	        this.creditLimitGenerateState = creditLimitGenerateState == null ? null : creditLimitGenerateState.trim();
	    }

	    /**
	     *是否存在网络负面信息
	     */
	    public String getNegativeNetwork() {
	        return negativeNetwork;
	    }

	    /**
	     *是否存在网络负面信息
	     */
	    public void setNegativeNetwork(String negativeNetwork) {
	        this.negativeNetwork = negativeNetwork == null ? null : negativeNetwork.trim();
	    }

	    /**
	     *申请企业与韵达签署特许经营加盟合同到期日
	     */
	    public Date getRanchiseContractDeadline() {
	        return ranchiseContractDeadline;
	    }

	    /**
	     *申请企业与韵达签署特许经营加盟合同到期日
	     */
	    public void setRanchiseContractDeadline(Date ranchiseContractDeadline) {
	        this.ranchiseContractDeadline = ranchiseContractDeadline;
	    }

	    /**
	     *法院被执行人信息
	     */
	    public String getExecuteIndividualInfo() {
	        return executeIndividualInfo;
	    }

	    /**
	     *法院被执行人信息
	     */
	    public void setExecuteIndividualInfo(String executeIndividualInfo) {
	        this.executeIndividualInfo = executeIndividualInfo == null ? null : executeIndividualInfo.trim();
	    }

	    /**
	     *年检记录
	     */
	    public String getQualifiedInspectionRecord() {
	        return qualifiedInspectionRecord;
	    }

	    /**
	     *年检记录
	     */
	    public void setQualifiedInspectionRecord(String qualifiedInspectionRecord) {
	        this.qualifiedInspectionRecord = qualifiedInspectionRecord == null ? null : qualifiedInspectionRecord.trim();
	    }

	    /**
	     *打款账户
	     */
	    public String getPayAccount() {
	        return payAccount;
	    }

	    /**
	     *打款账户
	     */
	    public void setPayAccount(String payAccount) {
	        this.payAccount = payAccount == null ? null : payAccount.trim();
	    }

	    /**
	     *打款账户户名
	     */
	    public String getPayAccountName() {
	        return payAccountName;
	    }

	    /**
	     *打款账户户名
	     */
	    public void setPayAccountName(String payAccountName) {
	        this.payAccountName = payAccountName == null ? null : payAccountName.trim();
	    }

	    /**
	     *打款账户地址
	     */
	    public String getPayAccountAddress() {
	        return payAccountAddress;
	    }

	    /**
	     *打款账户地址
	     */
	    public void setPayAccountAddress(String payAccountAddress) {
	        this.payAccountAddress = payAccountAddress == null ? null : payAccountAddress.trim();
	    }

	    /**
	     *开户行联行号
	     */
	    public String getLianHangNo() {
	        return lianHangNo;
	    }

	    /**
	     *开户行联行号
	     */
	    public void setLianHangNo(String lianHangNo) {
	        this.lianHangNo = lianHangNo == null ? null : lianHangNo.trim();
	    }

	    /**
	     *实际控制人
	     */
	    public String getController() {
	        return controller;
	    }

	    /**
	     *实际控制人
	     */
	    public void setController(String controller) {
	        this.controller = controller == null ? null : controller.trim();
	    }

	    /**
	     *实际控制人身份证号
	     */
	    public String getControllerIdno() {
	        return controllerIdno;
	    }

	    /**
	     *实际控制人身份证号
	     */
	    public void setControllerIdno(String controllerIdno) {
	        this.controllerIdno = controllerIdno == null ? null : controllerIdno.trim();
	    }

	    /**
	     *实际控制人邮箱
	     */
	    public String getControllerEmail() {
	        return controllerEmail;
	    }

	    /**
	     *实际控制人邮箱
	     */
	    public void setControllerEmail(String controllerEmail) {
	        this.controllerEmail = controllerEmail == null ? null : controllerEmail.trim();
	    }

	    /**
	     *实际控制人手机号码
	     */
	    public String getControllerPhone() {
	        return controllerPhone;
	    }

	    /**
	     *实际控制人手机号码
	     */
	    public void setControllerPhone(String controllerPhone) {
	        this.controllerPhone = controllerPhone == null ? null : controllerPhone.trim();
	    }

	    /**
	     *实际控制人籍贯
	     */
	    public String getControllerOrigin() {
	        return controllerOrigin;
	    }

	    /**
	     *实际控制人籍贯
	     */
	    public void setControllerOrigin(String controllerOrigin) {
	        this.controllerOrigin = controllerOrigin == null ? null : controllerOrigin.trim();
	    }

	    /**
	     *实际控制人与法人代表是否是同一人 （1：是   0：否）
	     */
	    public String getControllerIsLegal() {
	        return controllerIsLegal;
	    }

	    /**
	     *实际控制人与法人代表是否是同一人 （1：是   0：否）
	     */
	    public void setControllerIsLegal(String controllerIsLegal) {
	        this.controllerIsLegal = controllerIsLegal == null ? null : controllerIsLegal.trim();
	    }

	    /**
	     *法人
	     */
	    public String getLegalPerson() {
	        return legalPerson;
	    }

	    /**
	     *法人
	     */
	    public void setLegalPerson(String legalPerson) {
	        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
	    }

	    /**
	     *法人代表邮箱
	     */
	    public String getLegalEmail() {
	        return legalEmail;
	    }

	    /**
	     *法人代表邮箱
	     */
	    public void setLegalEmail(String legalEmail) {
	        this.legalEmail = legalEmail == null ? null : legalEmail.trim();
	    }

	    /**
	     *法人代表手机号码
	     */
	    public String getLegalPhone() {
	        return legalPhone;
	    }

	    /**
	     *法人代表手机号码
	     */
	    public void setLegalPhone(String legalPhone) {
	        this.legalPhone = legalPhone == null ? null : legalPhone.trim();
	    }

	    /**
	     *开户人（法人：LEGAL     实际控制人：CONTROLLER   其他：OTHER   公司：COMPANY）
	     */
	    public String getAccountOwner() {
	        return accountOwner;
	    }

	    /**
	     *开户人（法人：LEGAL     实际控制人：CONTROLLER   其他：OTHER   公司：COMPANY）
	     */
	    public void setAccountOwner(String accountOwner) {
	        this.accountOwner = accountOwner == null ? null : accountOwner.trim();
	    }

	    /**
	     *开户人账户
	     */
	    public String getAccountOwnerName() {
	        return accountOwnerName;
	    }

	    /**
	     *开户人账户
	     */
	    public void setAccountOwnerName(String accountOwnerName) {
	        this.accountOwnerName = accountOwnerName == null ? null : accountOwnerName.trim();
	    }

	    /**
	     *开户人邮箱
	     */
	    public String getAccountOwnerEmail() {
	        return accountOwnerEmail;
	    }

	    /**
	     *开户人邮箱
	     */
	    public void setAccountOwnerEmail(String accountOwnerEmail) {
	        this.accountOwnerEmail = accountOwnerEmail == null ? null : accountOwnerEmail.trim();
	    }

	    /**
	     *开户人手机号码
	     */
	    public String getAccountOwnerPhone() {
	        return accountOwnerPhone;
	    }

	    /**
	     *开户人手机号码
	     */
	    public void setAccountOwnerPhone(String accountOwnerPhone) {
	        this.accountOwnerPhone = accountOwnerPhone == null ? null : accountOwnerPhone.trim();
	    }

	    /**
	     *短期借款
	     */
	    public Long getShortLoan() {
	        return shortLoan;
	    }

	    /**
	     *短期借款
	     */
	    public void setShortLoan(Long shortLoan) {
	        this.shortLoan = shortLoan;
	    }

	    /**
	     *借款来源
	     */
	    public String getLoanFrom() {
	        return loanFrom;
	    }

	    /**
	     *借款来源
	     */
	    public void setLoanFrom(String loanFrom) {
	        this.loanFrom = loanFrom == null ? null : loanFrom.trim();
	    }

	    /**
	     *代理资质
	     */
	    public String getAgentQualification() {
	        return agentQualification;
	    }

	    /**
	     *代理资质
	     */
	    public void setAgentQualification(String agentQualification) {
	        this.agentQualification = agentQualification == null ? null : agentQualification.trim();
	    }

	    /**
	     *法人代表身份证号码
	     */
	    public String getLegalIdno() {
	        return legalIdno;
	    }

	    /**
	     *法人代表身份证号码
	     */
	    public void setLegalIdno(String legalIdno) {
	        this.legalIdno = legalIdno == null ? null : legalIdno.trim();
	    }

	    /**
	     *法人代表身份证正面图片Ｉｄ
	     */
	    public String getLegalIdnoFrontMediaId() {
	        return legalIdnoFrontMediaId;
	    }

	    /**
	     *法人代表身份证正面图片Ｉｄ
	     */
	    public void setLegalIdnoFrontMediaId(String legalIdnoFrontMediaId) {
	        this.legalIdnoFrontMediaId = legalIdnoFrontMediaId == null ? null : legalIdnoFrontMediaId.trim();
	    }

	    /**
	     *法人身份证反面图片Ｉｄ
	     */
	    public String getLegalIdnoBackMediaId() {
	        return legalIdnoBackMediaId;
	    }

	    /**
	     *法人身份证反面图片Ｉｄ
	     */
	    public void setLegalIdnoBackMediaId(String legalIdnoBackMediaId) {
	        this.legalIdnoBackMediaId = legalIdnoBackMediaId == null ? null : legalIdnoBackMediaId.trim();
	    }

	    /**
	     *补充资料附件ID
	     */
	    public String getAdditionInformationMediaId() {
	        return additionInformationMediaId;
	    }

	    /**
	     *补充资料附件ID
	     */
	    public void setAdditionInformationMediaId(String additionInformationMediaId) {
	        this.additionInformationMediaId = additionInformationMediaId == null ? null : additionInformationMediaId.trim();
	    }

	    /**
	     *是否有天猫、淘宝订单
	     */
	    public String getWhetherTbOrder() {
	        return whetherTbOrder;
	    }

	    /**
	     *是否有天猫、淘宝订单
	     */
	    public void setWhetherTbOrder(String whetherTbOrder) {
	        this.whetherTbOrder = whetherTbOrder == null ? null : whetherTbOrder.trim();
	    }

	    /**
	     *天猫、淘宝订单占比
	     */
	    public BigDecimal getTbOrderRatio() {
	        return tbOrderRatio;
	    }

	    /**
	     *天猫、淘宝订单占比
	     */
	    public void setTbOrderRatio(BigDecimal tbOrderRatio) {
	        this.tbOrderRatio = tbOrderRatio;
	    }

	    /**
	     *韵达说明
	     */
	    public String getYundaexExplain() {
	        return yundaexExplain;
	    }

	    /**
	     *韵达说明
	     */
	    public void setYundaexExplain(String yundaexExplain) {
	        this.yundaexExplain = yundaexExplain == null ? null : yundaexExplain.trim();
	    }

	    /**
	     *其他说明
	     */
	    public String getOtherExplain() {
	        return otherExplain;
	    }

	    /**
	     *其他说明
	     */
	    public void setOtherExplain(String otherExplain) {
	        this.otherExplain = otherExplain == null ? null : otherExplain.trim();
	    }

	    /**
	     *打款账户银行名称
	     */
	    public String getPayAccountBankName() {
	        return payAccountBankName;
	    }

	    /**
	     *打款账户银行名称
	     */
	    public void setPayAccountBankName(String payAccountBankName) {
	        this.payAccountBankName = payAccountBankName == null ? null : payAccountBankName.trim();
	    }

	    /**
	     *打款账户银行所在省份
	     */
	    public String getBankProvince() {
	        return bankProvince;
	    }

	    /**
	     *打款账户银行所在省份
	     */
	    public void setBankProvince(String bankProvince) {
	        this.bankProvince = bankProvince == null ? null : bankProvince.trim();
	    }

	    /**
	     *打款账户所在城市
	     */
	    public String getBankCity() {
	        return bankCity;
	    }

	    /**
	     *打款账户所在城市
	     */
	    public void setBankCity(String bankCity) {
	        this.bankCity = bankCity == null ? null : bankCity.trim();
	    }

	    /**
	     *韵达评价
	     */
	    public String getYundaexJudge() {
	        return yundaexJudge;
	    }

	    /**
	     *韵达评价
	     */
	    public void setYundaexJudge(String yundaexJudge) {
	        this.yundaexJudge = yundaexJudge == null ? null : yundaexJudge.trim();
	    }

	    /**
	     *站点地址区域 （市区   集镇   村镇）
	     */
	    public String getStationAddress() {
	        return stationAddress;
	    }

	    /**
	     *站点地址区域 （市区   集镇   村镇）
	     */
	    public void setStationAddress(String stationAddress) {
	        this.stationAddress = stationAddress == null ? null : stationAddress.trim();
	    }

	    /**
	     *评级状态（WAIT   ACCESS  NOTACCESS）
	     */
	    public String getGradeState() {
	        return gradeState;
	    }

	    /**
	     *评级状态（WAIT   ACCESS  NOTACCESS）
	     */
	    public void setGradeState(String gradeState) {
	        this.gradeState = gradeState == null ? null : gradeState.trim();
	    }

	    /**
	     *评级审核备注
	     */
	    public String getGradeStateRemarks() {
	        return gradeStateRemarks;
	    }

	    /**
	     *评级审核备注
	     */
	    public void setGradeStateRemarks(String gradeStateRemarks) {
	        this.gradeStateRemarks = gradeStateRemarks == null ? null : gradeStateRemarks.trim();
	    }

	    @Override
	    public boolean equals(Object that) {
	        if (this == that) {
	            return true;
	        }
	        if (that == null) {
	            return false;
	        }
	        if (getClass() != that.getClass()) {
	            return false;
	        }
	        YundaexCustomerAapplyTestDto other = (YundaexCustomerAapplyTestDto) that;
	        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
	            && (this.getApplyDate() == null ? other.getApplyDate() == null : this.getApplyDate().equals(other.getApplyDate()))
	            && (this.getStationNo() == null ? other.getStationNo() == null : this.getStationNo().equals(other.getStationNo()))
	            && (this.getStationAmount() == null ? other.getStationAmount() == null : this.getStationAmount().equals(other.getStationAmount()))
	            && (this.getStationMgr() == null ? other.getStationMgr() == null : this.getStationMgr().equals(other.getStationMgr()))
	            && (this.getStationName() == null ? other.getStationName() == null : this.getStationName().equals(other.getStationName()))
	            && (this.getCustName() == null ? other.getCustName() == null : this.getCustName().equals(other.getCustName()))
	            && (this.getCustIdno() == null ? other.getCustIdno() == null : this.getCustIdno().equals(other.getCustIdno()))
	            && (this.getCustPhone() == null ? other.getCustPhone() == null : this.getCustPhone().equals(other.getCustPhone()))
	            && (this.getCustEmail() == null ? other.getCustEmail() == null : this.getCustEmail().equals(other.getCustEmail()))
	            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
	            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
	            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
	            && (this.getBusiLimit() == null ? other.getBusiLimit() == null : this.getBusiLimit().equals(other.getBusiLimit()))
	            && (this.getRegioncode() == null ? other.getRegioncode() == null : this.getRegioncode().equals(other.getRegioncode()))
	            && (this.getApplyCreditLimit() == null ? other.getApplyCreditLimit() == null : this.getApplyCreditLimit().equals(other.getApplyCreditLimit()))
	            && (this.getFundsUse() == null ? other.getFundsUse() == null : this.getFundsUse().equals(other.getFundsUse()))
	            && (this.getLoanLimit() == null ? other.getLoanLimit() == null : this.getLoanLimit().equals(other.getLoanLimit()))
	            && (this.getAddCredit() == null ? other.getAddCredit() == null : this.getAddCredit().equals(other.getAddCredit()))
	            && (this.getRepaymentType() == null ? other.getRepaymentType() == null : this.getRepaymentType().equals(other.getRepaymentType()))
	            && (this.getBankAccount() == null ? other.getBankAccount() == null : this.getBankAccount().equals(other.getBankAccount()))
	            && (this.getBankAccountName() == null ? other.getBankAccountName() == null : this.getBankAccountName().equals(other.getBankAccountName()))
	            && (this.getBankAccountAddress() == null ? other.getBankAccountAddress() == null : this.getBankAccountAddress().equals(other.getBankAccountAddress()))
	            && (this.getOrganizationNo() == null ? other.getOrganizationNo() == null : this.getOrganizationNo().equals(other.getOrganizationNo()))
	            && (this.getOrganizationMediaId() == null ? other.getOrganizationMediaId() == null : this.getOrganizationMediaId().equals(other.getOrganizationMediaId()))
	            && (this.getBusinessLicenceNo() == null ? other.getBusinessLicenceNo() == null : this.getBusinessLicenceNo().equals(other.getBusinessLicenceNo()))
	            && (this.getBusinessLicenceMediaId() == null ? other.getBusinessLicenceMediaId() == null : this.getBusinessLicenceMediaId().equals(other.getBusinessLicenceMediaId()))
	            && (this.getTaxRegistrationCertificateNo() == null ? other.getTaxRegistrationCertificateNo() == null : this.getTaxRegistrationCertificateNo().equals(other.getTaxRegistrationCertificateNo()))
	            && (this.getTaxRegistrationCertificateMediaId() == null ? other.getTaxRegistrationCertificateMediaId() == null : this.getTaxRegistrationCertificateMediaId().equals(other.getTaxRegistrationCertificateMediaId()))
	            && (this.getBailBalance() == null ? other.getBailBalance() == null : this.getBailBalance().equals(other.getBailBalance()))
	            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
	            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
	            && (this.getAccessManualState() == null ? other.getAccessManualState() == null : this.getAccessManualState().equals(other.getAccessManualState()))
	            && (this.getManualAuditRemarks() == null ? other.getManualAuditRemarks() == null : this.getManualAuditRemarks().equals(other.getManualAuditRemarks()))
	            && (this.getAccessAutomaticState() == null ? other.getAccessAutomaticState() == null : this.getAccessAutomaticState().equals(other.getAccessAutomaticState()))
	            && (this.getAutomaticAuditRemarks() == null ? other.getAutomaticAuditRemarks() == null : this.getAutomaticAuditRemarks().equals(other.getAutomaticAuditRemarks()))
	            && (this.getAuditorId() == null ? other.getAuditorId() == null : this.getAuditorId().equals(other.getAuditorId()))
	            && (this.getConsistencyCheck() == null ? other.getConsistencyCheck() == null : this.getConsistencyCheck().equals(other.getConsistencyCheck()))
	            && (this.getWhetherStationInfo() == null ? other.getWhetherStationInfo() == null : this.getWhetherStationInfo().equals(other.getWhetherStationInfo()))
	            && (this.getReasonW() == null ? other.getReasonW() == null : this.getReasonW().equals(other.getReasonW()))
	            && (this.getAutomaticAuditRuleBatchNo() == null ? other.getAutomaticAuditRuleBatchNo() == null : this.getAutomaticAuditRuleBatchNo().equals(other.getAutomaticAuditRuleBatchNo()))
	            && (this.getManualAuditRuleBatchNo() == null ? other.getManualAuditRuleBatchNo() == null : this.getManualAuditRuleBatchNo().equals(other.getManualAuditRuleBatchNo()))
	            && (this.getCreditLimitGenerateState() == null ? other.getCreditLimitGenerateState() == null : this.getCreditLimitGenerateState().equals(other.getCreditLimitGenerateState()))
	            && (this.getNegativeNetwork() == null ? other.getNegativeNetwork() == null : this.getNegativeNetwork().equals(other.getNegativeNetwork()))
	            && (this.getRanchiseContractDeadline() == null ? other.getRanchiseContractDeadline() == null : this.getRanchiseContractDeadline().equals(other.getRanchiseContractDeadline()))
	            && (this.getExecuteIndividualInfo() == null ? other.getExecuteIndividualInfo() == null : this.getExecuteIndividualInfo().equals(other.getExecuteIndividualInfo()))
	            && (this.getQualifiedInspectionRecord() == null ? other.getQualifiedInspectionRecord() == null : this.getQualifiedInspectionRecord().equals(other.getQualifiedInspectionRecord()))
	            && (this.getPayAccount() == null ? other.getPayAccount() == null : this.getPayAccount().equals(other.getPayAccount()))
	            && (this.getPayAccountName() == null ? other.getPayAccountName() == null : this.getPayAccountName().equals(other.getPayAccountName()))
	            && (this.getPayAccountAddress() == null ? other.getPayAccountAddress() == null : this.getPayAccountAddress().equals(other.getPayAccountAddress()))
	            && (this.getLianHangNo() == null ? other.getLianHangNo() == null : this.getLianHangNo().equals(other.getLianHangNo()))
	            && (this.getController() == null ? other.getController() == null : this.getController().equals(other.getController()))
	            && (this.getControllerIdno() == null ? other.getControllerIdno() == null : this.getControllerIdno().equals(other.getControllerIdno()))
	            && (this.getControllerEmail() == null ? other.getControllerEmail() == null : this.getControllerEmail().equals(other.getControllerEmail()))
	            && (this.getControllerPhone() == null ? other.getControllerPhone() == null : this.getControllerPhone().equals(other.getControllerPhone()))
	            && (this.getControllerOrigin() == null ? other.getControllerOrigin() == null : this.getControllerOrigin().equals(other.getControllerOrigin()))
	            && (this.getControllerIsLegal() == null ? other.getControllerIsLegal() == null : this.getControllerIsLegal().equals(other.getControllerIsLegal()))
	            && (this.getLegalPerson() == null ? other.getLegalPerson() == null : this.getLegalPerson().equals(other.getLegalPerson()))
	            && (this.getLegalEmail() == null ? other.getLegalEmail() == null : this.getLegalEmail().equals(other.getLegalEmail()))
	            && (this.getLegalPhone() == null ? other.getLegalPhone() == null : this.getLegalPhone().equals(other.getLegalPhone()))
	            && (this.getAccountOwner() == null ? other.getAccountOwner() == null : this.getAccountOwner().equals(other.getAccountOwner()))
	            && (this.getAccountOwnerName() == null ? other.getAccountOwnerName() == null : this.getAccountOwnerName().equals(other.getAccountOwnerName()))
	            && (this.getAccountOwnerEmail() == null ? other.getAccountOwnerEmail() == null : this.getAccountOwnerEmail().equals(other.getAccountOwnerEmail()))
	            && (this.getAccountOwnerPhone() == null ? other.getAccountOwnerPhone() == null : this.getAccountOwnerPhone().equals(other.getAccountOwnerPhone()))
	            && (this.getShortLoan() == null ? other.getShortLoan() == null : this.getShortLoan().equals(other.getShortLoan()))
	            && (this.getLoanFrom() == null ? other.getLoanFrom() == null : this.getLoanFrom().equals(other.getLoanFrom()))
	            && (this.getAgentQualification() == null ? other.getAgentQualification() == null : this.getAgentQualification().equals(other.getAgentQualification()))
	            && (this.getLegalIdno() == null ? other.getLegalIdno() == null : this.getLegalIdno().equals(other.getLegalIdno()))
	            && (this.getLegalIdnoFrontMediaId() == null ? other.getLegalIdnoFrontMediaId() == null : this.getLegalIdnoFrontMediaId().equals(other.getLegalIdnoFrontMediaId()))
	            && (this.getLegalIdnoBackMediaId() == null ? other.getLegalIdnoBackMediaId() == null : this.getLegalIdnoBackMediaId().equals(other.getLegalIdnoBackMediaId()))
	            && (this.getAdditionInformationMediaId() == null ? other.getAdditionInformationMediaId() == null : this.getAdditionInformationMediaId().equals(other.getAdditionInformationMediaId()))
	            && (this.getWhetherTbOrder() == null ? other.getWhetherTbOrder() == null : this.getWhetherTbOrder().equals(other.getWhetherTbOrder()))
	            && (this.getTbOrderRatio() == null ? other.getTbOrderRatio() == null : this.getTbOrderRatio().equals(other.getTbOrderRatio()))
	            && (this.getYundaexExplain() == null ? other.getYundaexExplain() == null : this.getYundaexExplain().equals(other.getYundaexExplain()))
	            && (this.getOtherExplain() == null ? other.getOtherExplain() == null : this.getOtherExplain().equals(other.getOtherExplain()))
	            && (this.getPayAccountBankName() == null ? other.getPayAccountBankName() == null : this.getPayAccountBankName().equals(other.getPayAccountBankName()))
	            && (this.getBankProvince() == null ? other.getBankProvince() == null : this.getBankProvince().equals(other.getBankProvince()))
	            && (this.getBankCity() == null ? other.getBankCity() == null : this.getBankCity().equals(other.getBankCity()))
	            && (this.getYundaexJudge() == null ? other.getYundaexJudge() == null : this.getYundaexJudge().equals(other.getYundaexJudge()))
	            && (this.getStationAddress() == null ? other.getStationAddress() == null : this.getStationAddress().equals(other.getStationAddress()))
	            && (this.getGradeState() == null ? other.getGradeState() == null : this.getGradeState().equals(other.getGradeState()))
	            && (this.getGradeStateRemarks() == null ? other.getGradeStateRemarks() == null : this.getGradeStateRemarks().equals(other.getGradeStateRemarks()));
	    }

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
	        result = prime * result + ((getApplyDate() == null) ? 0 : getApplyDate().hashCode());
	        result = prime * result + ((getStationNo() == null) ? 0 : getStationNo().hashCode());
	        result = prime * result + ((getStationAmount() == null) ? 0 : getStationAmount().hashCode());
	        result = prime * result + ((getStationMgr() == null) ? 0 : getStationMgr().hashCode());
	        result = prime * result + ((getStationName() == null) ? 0 : getStationName().hashCode());
	        result = prime * result + ((getCustName() == null) ? 0 : getCustName().hashCode());
	        result = prime * result + ((getCustIdno() == null) ? 0 : getCustIdno().hashCode());
	        result = prime * result + ((getCustPhone() == null) ? 0 : getCustPhone().hashCode());
	        result = prime * result + ((getCustEmail() == null) ? 0 : getCustEmail().hashCode());
	        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
	        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
	        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
	        result = prime * result + ((getBusiLimit() == null) ? 0 : getBusiLimit().hashCode());
	        result = prime * result + ((getRegioncode() == null) ? 0 : getRegioncode().hashCode());
	        result = prime * result + ((getApplyCreditLimit() == null) ? 0 : getApplyCreditLimit().hashCode());
	        result = prime * result + ((getFundsUse() == null) ? 0 : getFundsUse().hashCode());
	        result = prime * result + ((getLoanLimit() == null) ? 0 : getLoanLimit().hashCode());
	        result = prime * result + ((getAddCredit() == null) ? 0 : getAddCredit().hashCode());
	        result = prime * result + ((getRepaymentType() == null) ? 0 : getRepaymentType().hashCode());
	        result = prime * result + ((getBankAccount() == null) ? 0 : getBankAccount().hashCode());
	        result = prime * result + ((getBankAccountName() == null) ? 0 : getBankAccountName().hashCode());
	        result = prime * result + ((getBankAccountAddress() == null) ? 0 : getBankAccountAddress().hashCode());
	        result = prime * result + ((getOrganizationNo() == null) ? 0 : getOrganizationNo().hashCode());
	        result = prime * result + ((getOrganizationMediaId() == null) ? 0 : getOrganizationMediaId().hashCode());
	        result = prime * result + ((getBusinessLicenceNo() == null) ? 0 : getBusinessLicenceNo().hashCode());
	        result = prime * result + ((getBusinessLicenceMediaId() == null) ? 0 : getBusinessLicenceMediaId().hashCode());
	        result = prime * result + ((getTaxRegistrationCertificateNo() == null) ? 0 : getTaxRegistrationCertificateNo().hashCode());
	        result = prime * result + ((getTaxRegistrationCertificateMediaId() == null) ? 0 : getTaxRegistrationCertificateMediaId().hashCode());
	        result = prime * result + ((getBailBalance() == null) ? 0 : getBailBalance().hashCode());
	        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
	        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
	        result = prime * result + ((getAccessManualState() == null) ? 0 : getAccessManualState().hashCode());
	        result = prime * result + ((getManualAuditRemarks() == null) ? 0 : getManualAuditRemarks().hashCode());
	        result = prime * result + ((getAccessAutomaticState() == null) ? 0 : getAccessAutomaticState().hashCode());
	        result = prime * result + ((getAutomaticAuditRemarks() == null) ? 0 : getAutomaticAuditRemarks().hashCode());
	        result = prime * result + ((getAuditorId() == null) ? 0 : getAuditorId().hashCode());
	        result = prime * result + ((getConsistencyCheck() == null) ? 0 : getConsistencyCheck().hashCode());
	        result = prime * result + ((getWhetherStationInfo() == null) ? 0 : getWhetherStationInfo().hashCode());
	        result = prime * result + ((getReasonW() == null) ? 0 : getReasonW().hashCode());
	        result = prime * result + ((getAutomaticAuditRuleBatchNo() == null) ? 0 : getAutomaticAuditRuleBatchNo().hashCode());
	        result = prime * result + ((getManualAuditRuleBatchNo() == null) ? 0 : getManualAuditRuleBatchNo().hashCode());
	        result = prime * result + ((getCreditLimitGenerateState() == null) ? 0 : getCreditLimitGenerateState().hashCode());
	        result = prime * result + ((getNegativeNetwork() == null) ? 0 : getNegativeNetwork().hashCode());
	        result = prime * result + ((getRanchiseContractDeadline() == null) ? 0 : getRanchiseContractDeadline().hashCode());
	        result = prime * result + ((getExecuteIndividualInfo() == null) ? 0 : getExecuteIndividualInfo().hashCode());
	        result = prime * result + ((getQualifiedInspectionRecord() == null) ? 0 : getQualifiedInspectionRecord().hashCode());
	        result = prime * result + ((getPayAccount() == null) ? 0 : getPayAccount().hashCode());
	        result = prime * result + ((getPayAccountName() == null) ? 0 : getPayAccountName().hashCode());
	        result = prime * result + ((getPayAccountAddress() == null) ? 0 : getPayAccountAddress().hashCode());
	        result = prime * result + ((getLianHangNo() == null) ? 0 : getLianHangNo().hashCode());
	        result = prime * result + ((getController() == null) ? 0 : getController().hashCode());
	        result = prime * result + ((getControllerIdno() == null) ? 0 : getControllerIdno().hashCode());
	        result = prime * result + ((getControllerEmail() == null) ? 0 : getControllerEmail().hashCode());
	        result = prime * result + ((getControllerPhone() == null) ? 0 : getControllerPhone().hashCode());
	        result = prime * result + ((getControllerOrigin() == null) ? 0 : getControllerOrigin().hashCode());
	        result = prime * result + ((getControllerIsLegal() == null) ? 0 : getControllerIsLegal().hashCode());
	        result = prime * result + ((getLegalPerson() == null) ? 0 : getLegalPerson().hashCode());
	        result = prime * result + ((getLegalEmail() == null) ? 0 : getLegalEmail().hashCode());
	        result = prime * result + ((getLegalPhone() == null) ? 0 : getLegalPhone().hashCode());
	        result = prime * result + ((getAccountOwner() == null) ? 0 : getAccountOwner().hashCode());
	        result = prime * result + ((getAccountOwnerName() == null) ? 0 : getAccountOwnerName().hashCode());
	        result = prime * result + ((getAccountOwnerEmail() == null) ? 0 : getAccountOwnerEmail().hashCode());
	        result = prime * result + ((getAccountOwnerPhone() == null) ? 0 : getAccountOwnerPhone().hashCode());
	        result = prime * result + ((getShortLoan() == null) ? 0 : getShortLoan().hashCode());
	        result = prime * result + ((getLoanFrom() == null) ? 0 : getLoanFrom().hashCode());
	        result = prime * result + ((getAgentQualification() == null) ? 0 : getAgentQualification().hashCode());
	        result = prime * result + ((getLegalIdno() == null) ? 0 : getLegalIdno().hashCode());
	        result = prime * result + ((getLegalIdnoFrontMediaId() == null) ? 0 : getLegalIdnoFrontMediaId().hashCode());
	        result = prime * result + ((getLegalIdnoBackMediaId() == null) ? 0 : getLegalIdnoBackMediaId().hashCode());
	        result = prime * result + ((getAdditionInformationMediaId() == null) ? 0 : getAdditionInformationMediaId().hashCode());
	        result = prime * result + ((getWhetherTbOrder() == null) ? 0 : getWhetherTbOrder().hashCode());
	        result = prime * result + ((getTbOrderRatio() == null) ? 0 : getTbOrderRatio().hashCode());
	        result = prime * result + ((getYundaexExplain() == null) ? 0 : getYundaexExplain().hashCode());
	        result = prime * result + ((getOtherExplain() == null) ? 0 : getOtherExplain().hashCode());
	        result = prime * result + ((getPayAccountBankName() == null) ? 0 : getPayAccountBankName().hashCode());
	        result = prime * result + ((getBankProvince() == null) ? 0 : getBankProvince().hashCode());
	        result = prime * result + ((getBankCity() == null) ? 0 : getBankCity().hashCode());
	        result = prime * result + ((getYundaexJudge() == null) ? 0 : getYundaexJudge().hashCode());
	        result = prime * result + ((getStationAddress() == null) ? 0 : getStationAddress().hashCode());
	        result = prime * result + ((getGradeState() == null) ? 0 : getGradeState().hashCode());
	        result = prime * result + ((getGradeStateRemarks() == null) ? 0 : getGradeStateRemarks().hashCode());
	        return result;
	    }
	}
