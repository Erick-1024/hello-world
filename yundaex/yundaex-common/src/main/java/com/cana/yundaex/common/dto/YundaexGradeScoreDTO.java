package com.cana.yundaex.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexGradeScoreDTO implements Serializable{

	/**
     *主键
     */
//    private String id;

    /**
     *申请日期
     */
//    private Date applyDate;

    /**
     *网点编号
     */
    private String stationNo;
    
    /**
     * 揽派件增长率
     */
    private BigDecimal recAndSendGrowthRate;

    /**
     *网点数量
     */
    private Integer stationAmount;

    /**
     *站点负责人/公司名称
     */
//    private String stationMgr;

    /**
     *站点名称
     */
//    private String stationName;

    /**
     *借款人姓名
     */
//    private String custName;

    /**
     *借款人身份证号
     */
//    private String custIdno;

    /**
     *借款人手机号
     */
//    private String custPhone;

    /**
     *借款人邮箱
     */
//    private String custEmail;

    /**
     *站点经营地址-省
     */
    private String province;

    /**
     *站点经营地址-市
     */
    private String city;
    
    /**
     * 城市等级
     */
    private String cityLevel;

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
//    private String fundsUse;

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
//    private String bankAccount;

    /**
     *银行所在省份
     */
//    private String bankAccountName;

    /**
     *银行所在城市
     */
//    private String bankAccountAddress;

    /**
     *组织机构代码号码
     */
//    private String organizationNo;

    /**
     *组织机构证件ID
     */
//    private String organizationMediaId;

    /**
     *营业执照号码
     */
//    private String businessLicenceNo;

    /**
     *营业执照证件ID
     */
//    private String businessLicenceMediaId;

    /**
     *税务登记证号码
     */
//    private String taxRegistrationCertificateNo;

    /**
     *税务登记证证件ID
     */
//    private String taxRegistrationCertificateMediaId;

    /**
     *保证金账户余额
     */
    private Long bailBalance;

    /**
     *更新时间
     */
//    private Date updateTime;

    /**
     *创建时间
     */
//    private Date createTime;

    /**
     *是否通过人工审核（"WAIT"：未审核，"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
     */
//    private String accessManualState;

    /**
     *人工审核时的备注
     */
//    private String manualAuditRemarks;

    /**
     *是否通过系统审核（"WAIT"：未审核，"ACCESS"：通过系统审核，"NOTACCESS"：不通过系统审核）
     */
//    private String accessAutomaticState;

    /**
     *系统审核备注，系统审核不通过时填写不通过内容。
     */
//    private String automaticAuditRemarks;

    /**
     *审核人ID
     */
//    private String auditorId;

    /**
     *一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)
     */
    private Integer consistencyCheck;

    /**
     *是否有网点信息（Y:有　N:没有　W:拉取信息失败）
     */
//    private String whetherStationInfo;

    /**
     *拉取站点信息结果
     */
//    private String reasonW;

    /**
     *准入规则批次号
     */
//    private Integer automaticAuditRuleBatchNo;

    /**
     *人工审核规则批次号
     */
//    private Integer manualAuditRuleBatchNo;

    /**
     *额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）
     */
//    private String creditLimitGenerateState;

    /**
     *是否存在网络负面信息
     */
//    private String negativeNetwork;

    /**
     *申请企业与韵达签署特许经营加盟合同到期日
     */
//    private Date ranchiseContractDeadline;

    /**
     *法院被执行人信息
     */
//    private String executeIndividualInfo;

    /**
     *年检记录
     */
    private String qualifiedInspectionRecord;

    /**
     *打款账户
     */
//    private String payAccount;

    /**
     *打款账户户名
     */
//    private String payAccountName;

    /**
     *打款账户地址
     */
//    private String payAccountAddress;

    /**
     *开户行联行号
     */
//    private String lianHangNo;

    /**
     *实际控制人
     */
//    private String controller;

    /**
     *实际控制人身份证号
     */
//    private String controllerIdno;

    /**
     *实际控制人邮箱
     */
//    private String controllerEmail;

    /**
     *实际控制人手机号码
     */
//    private String controllerPhone;

    /**
     *实际控制人籍贯
     */
    private String controllerOrigin;

    /**
     *实际控制人与法人代表是否是同一人 （1：是   0：否）
     */
//    private String controllerIsLegal;

    /**
     *法人
     */
//    private String legalPerson;

    /**
     *法人代表邮箱
     */
//    private String legalEmail;

    /**
     *法人代表手机号码
     */
//    private String legalPhone;

    /**
     *开户人（法人：LEGAL     实际控制人：CONTROLLER   其他：OTHER   公司：COMPANY）
     */
//    private String accountOwner;

    /**
     *开户人账户
     */
//    private String accountOwnerName;

    /**
     *开户人邮箱
     */
//    private String accountOwnerEmail;

    /**
     *开户人手机号码
     */
//    private String accountOwnerPhone;

    /**
     *短期借款
     */
    private Long shortLoan;

    /**
     *借款类型
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
     *法人代表身份证号码
     */
//    private String legalIdno;

    /**
     *法人代表身份证正面图片Ｉｄ
     */
//    private String legalIdnoFrontMediaId;

    /**
     *法人身份证反面图片Ｉｄ
     */
//    private String legalIdnoBackMediaId;

    /**
     *补充资料附件ID
     */
//    private String additionInformationMediaId;

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
//    private String yundaexExplain;

    /**
     *其他说明
     */
//    private String otherExplain;

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
     *评级状态备注
     */
//    private String gradeStateRemarks;

    /**
     *计算额度时，存放额度信息
     */
//    private String limitInfoRemarks;

    private static final long serialVersionUID = 1L;


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


	public String getCityLevel() {
		return cityLevel;
	}

	public void setCityLevel(String cityLevel) {
		this.cityLevel = cityLevel;
	}

	public BigDecimal getRecAndSendGrowthRate() {
		return recAndSendGrowthRate;
	}

	public void setRecAndSendGrowthRate(BigDecimal recAndSendGrowthRate) {
		this.recAndSendGrowthRate = recAndSendGrowthRate;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
}
