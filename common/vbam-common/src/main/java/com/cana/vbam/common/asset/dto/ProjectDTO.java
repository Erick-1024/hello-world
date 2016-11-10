/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.asset.enums.ProjectRepaymentMethodsEnum;
import com.cana.vbam.common.asset.enums.ProjectStatusEnum;
import com.cana.vbam.common.asset.enums.ProjectTypeEnum;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;

/**
 * @author jiangzhou.Ren
 * @time 2016年5月17日下午4:40:52
 */
public class ProjectDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5392869747731312171L;

	// 资金方
	private List<ProjectFactorDTO> projectFactors;

	// 合同
	private List<ProjectDocumentDTO> projectDocument;

	/**
	 * 产品描述
	 */
	private String productIntroduction;

	/**
	 * 产品类型描述
	 */
	private String productTypeDesc;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 项目名称
	 */
	private String name;

	/**
	 * 项目类型，平台、保理、租赁、小贷
	 */
	private ProjectTypeEnum type;

	/**
	 * 项目状态：立项、进行中、暂停、结束
	 */
	private ProjectStatusEnum status;

	/**
	 * 核心企业ID
	 */
	private String coreCompanyId;

	/**
	 * 核心企业名称
	 */
	private String coreCompanyName;

	/**
	 * 核心企业组织机构代码
	 */
	private String coreOrganizationCode;

	/**
	 * 核心企业营业执照号码
	 */
	private String coreBusinessLicenceCode;

	/**
	 * 核心企业税务登记证号吗
	 */
	private String coreTaxRegistrationCertificateCode;

	/**
	 * 行业
	 */
	private IndustryTypeEnum coreIndustry;

	/**
	 * 经济类型
	 */
	private EconomicCategoryEnum coreEconomicCategory;

	/**
	 * 核心企业银行账号
	 */
	private String coreAccountNo;

	/**
	 * 融资申请人
	 */
	private String financeApplicant;

	/**
	 * 单笔贷款下限
	 */
	private Long singleLoanLimitLower;

	// 单笔贷款上限页面展示需要转换
	private String singleLoanLimitLowerAdd;

	/**
	 * 单笔贷款上限
	 */
	private Long singleLoanLimitUpper;

	// 单笔贷款上限页面展示需要转换
	private String singleLoanLimitUpperAdd;

	/**
	 * 利率单位，see InterestRateUnit
	 */
	private InterestRateUnit interestRateUnit;

	/**
	 * 利率区间下限
	 */
	private BigDecimal interestRateLower;

	// 此字段是为了页面数据转换
	private String interestRateLowerAdd;

	/**
	 * 利率区间上限
	 */
	private BigDecimal interestRateUpper;

	// 此字段是为了页面数据转换
	private String interestRateUpperAdd;

	/**
	 * 期限单位，see DateUnit
	 */
	private DateUnit loanPeriodUnit;

	/**
	 * 期限下限
	 */
	private Integer loanPeriodLower;

	/**
	 * 期限上限
	 */
	private Integer loanPeriodUpper;

	/**
	 * see RepaymentType，允许的还本付息方式，多个值之间使用英文逗号分隔
	 */
	private String repaymentMethods;

	/**
	 * 提前还款手续费率
	 */
	private BigDecimal earlyRepaymentChargeRatio;
	// 此字段是为了页面数据转换
	private String earlyRepaymentChargeRatioAdd;

	/**
	 * 展期天数
	 */
	private Integer extensionDays;

	/**
	 * see ChargeMethod，展期收息方式，加固定值、按比例上浮
	 */
	private ChargeMethod extensionRatioMethod;

	/**
	 * 展期上浮利率值 或者是 展期上浮比例
	 */
	private BigDecimal extensionRatio;

	// 此字段是为了页面数据转换
	private String extensionRatioAdd;

	/**
	 * see ChargeMethod，罚息收息方式，加固定值、按比例上浮
	 */
	private ChargeMethod penaltyRateMethod;

	// private String penaltyRateMethod;

	/**
	 * 逾期上浮利率值 或者是 逾期上浮比例
	 */
	private String penaltyRateAdd;

	private BigDecimal penaltyRate;

	/**
	 * 扣款时点，格式为HH:mm
	 */
	private String deductionTime;

	/**
	 * 扣款规则，see DeductionRule
	 */
	private DeductionRule deductionRule;

	/*
	 * 还款方式 see ProjectRepaymentMethodsEnum
	 */
	private List<ProjectRepaymentMethodsEnum> repaymentMethodList;

	/*
	 * 付款方式复选框标记
	 */
	private List<ProjectRepaymentMethodsEnum> repaymentMethodTypeList;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建用户ID
	 */
	private String createUserId;

	/**
	 * 创建客户ID
	 */
	private String createCustomerId;

	private boolean useHolidayPolicy;

	public List<ProjectFactorDTO> getProjectFactors() {
		return projectFactors;
	}

	public void setProjectFactors(List<ProjectFactorDTO> projectFactors) {
		this.projectFactors = projectFactors;
	}

	public List<ProjectDocumentDTO> getProjectDocument() {
		return projectDocument;
	}

	public void setProjectDocument(List<ProjectDocumentDTO> projectDocument) {
		this.projectDocument = projectDocument;
	}

	/**
	 * 主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * 项目名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 项目名称
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public IndustryTypeEnum getCoreIndustry() {
		return coreIndustry;
	}

	public void setCoreIndustry(IndustryTypeEnum coreIndustry) {
		this.coreIndustry = coreIndustry;
	}

	public ProjectTypeEnum getType() {
		return type;
	}

	public void setType(ProjectTypeEnum type) {
		this.type = type;
	}

	/**
	 * 核心企业ID
	 */
	public String getCoreCompanyId() {
		return coreCompanyId;
	}

	/**
	 * 核心企业ID
	 */
	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId == null ? null : coreCompanyId.trim();
	}

	/**
	 * 核心企业名称
	 */
	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	/**
	 * 核心企业名称
	 */
	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName == null ? null : coreCompanyName.trim();
	}

	/**
	 * 核心企业组织机构代码
	 */
	public String getCoreOrganizationCode() {
		return coreOrganizationCode;
	}

	/**
	 * 核心企业组织机构代码
	 */
	public void setCoreOrganizationCode(String coreOrganizationCode) {
		this.coreOrganizationCode = coreOrganizationCode == null ? null : coreOrganizationCode.trim();
	}

	/**
	 * 核心企业营业执照号码
	 */
	public String getCoreBusinessLicenceCode() {
		return coreBusinessLicenceCode;
	}

	/**
	 * 核心企业营业执照号码
	 */
	public void setCoreBusinessLicenceCode(String coreBusinessLicenceCode) {
		this.coreBusinessLicenceCode = coreBusinessLicenceCode == null ? null : coreBusinessLicenceCode.trim();
	}

	/**
	 * 核心企业税务登记证号吗
	 */
	public String getCoreTaxRegistrationCertificateCode() {
		return coreTaxRegistrationCertificateCode;
	}

	/**
	 * 核心企业税务登记证号吗
	 */
	public void setCoreTaxRegistrationCertificateCode(String coreTaxRegistrationCertificateCode) {
		this.coreTaxRegistrationCertificateCode = coreTaxRegistrationCertificateCode == null ? null
				: coreTaxRegistrationCertificateCode.trim();
	}

	public EconomicCategoryEnum getCoreEconomicCategory() {
		return coreEconomicCategory;
	}

	public void setCoreEconomicCategory(EconomicCategoryEnum coreEconomicCategory) {
		this.coreEconomicCategory = coreEconomicCategory;
	}

	/**
	 * 核心企业银行账号
	 */
	public String getCoreAccountNo() {
		return coreAccountNo;
	}

	/**
	 * 核心企业银行账号
	 */
	public void setCoreAccountNo(String coreAccountNo) {
		this.coreAccountNo = coreAccountNo == null ? null : coreAccountNo.trim();
	}

	/**
	 * 融资申请人
	 */
	public String getFinanceApplicant() {
		return financeApplicant;
	}

	/**
	 * 融资申请人
	 */
	public void setFinanceApplicant(String financeApplicant) {
		this.financeApplicant = financeApplicant == null ? null : financeApplicant.trim();
	}

	/**
	 * 单笔贷款下限
	 */
	public Long getSingleLoanLimitLower() {
		return singleLoanLimitLower;
	}

	/**
	 * 单笔贷款下限
	 */
	public void setSingleLoanLimitLower(Long singleLoanLimitLower) {
		this.singleLoanLimitLower = singleLoanLimitLower;
	}

	/**
	 * 单笔贷款上限
	 */
	public Long getSingleLoanLimitUpper() {
		return singleLoanLimitUpper;
	}

	/**
	 * 单笔贷款上限
	 */
	public void setSingleLoanLimitUpper(Long singleLoanLimitUpper) {
		this.singleLoanLimitUpper = singleLoanLimitUpper;
	}

	/**
	 * 利率单位，see InterestRateUnit
	 */

	/**
	 * 利率区间下限
	 */
	public BigDecimal getInterestRateLower() {
		return interestRateLower;
	}

	/**
	 * 利率区间下限
	 */
	public void setInterestRateLower(BigDecimal interestRateLower) {
		this.interestRateLower = interestRateLower;
	}

	/**
	 * 利率区间上限
	 */
	public BigDecimal getInterestRateUpper() {
		return interestRateUpper;
	}

	/**
	 * 利率区间上限
	 */
	public void setInterestRateUpper(BigDecimal interestRateUpper) {
		this.interestRateUpper = interestRateUpper;
	}

	/**
	 * 期限单位，see DateUnit
	 */

	/**
	 * 期限下限
	 */
	public Integer getLoanPeriodLower() {
		return loanPeriodLower;
	}

	/**
	 * 期限下限
	 */
	public void setLoanPeriodLower(Integer loanPeriodLower) {
		this.loanPeriodLower = loanPeriodLower;
	}

	/**
	 * 期限上限
	 */
	public Integer getLoanPeriodUpper() {
		return loanPeriodUpper;
	}

	/**
	 * 期限上限
	 */
	public void setLoanPeriodUpper(Integer loanPeriodUpper) {
		this.loanPeriodUpper = loanPeriodUpper;
	}

	/**
	 * see RepaymentType，允许的还本付息方式，多个值之间使用英文逗号分隔
	 */
	/**
	 * 提前还款手续费率
	 */

	/**
	 * 展期天数
	 */
	public Integer getExtensionDays() {
		return extensionDays;
	}

	/**
	 * 展期天数
	 */
	public void setExtensionDays(Integer extensionDays) {
		this.extensionDays = extensionDays;
	}

	/**
	 * 扣款时点，格式为HH:mm
	 */
	public String getDeductionTime() {
		return deductionTime;
	}

	public String getPenaltyRateAdd() {
		return penaltyRateAdd;
	}

	public void setPenaltyRateAdd(String penaltyRateAdd) {
		this.penaltyRateAdd = penaltyRateAdd;
	}

	public BigDecimal getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(BigDecimal penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	/**
	 * 扣款时点，格式为HH:mm
	 */
	public void setDeductionTime(String deductionTime) {
		this.deductionTime = deductionTime == null ? null : deductionTime.trim();
	}

	/**
	 * 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 创建用户ID
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * 创建用户ID
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId == null ? null : createUserId.trim();
	}

	/**
	 * 创建客户ID
	 */
	public String getCreateCustomerId() {
		return createCustomerId;
	}

	/**
	 * 创建客户ID
	 */
	public void setCreateCustomerId(String createCustomerId) {
		this.createCustomerId = createCustomerId == null ? null : createCustomerId.trim();
	}

	public String getProductIntroduction() {
		return productIntroduction;
	}

	public void setProductIntroduction(String productIntroduction) {
		this.productIntroduction = productIntroduction;
	}

	public String getProductTypeDesc() {
		return productTypeDesc;
	}

	public void setProductTypeDesc(String productTypeDesc) {
		this.productTypeDesc = productTypeDesc;
	}

	public String getSingleLoanLimitLowerAdd() {
		return singleLoanLimitLowerAdd;
	}

	public void setSingleLoanLimitLowerAdd(String singleLoanLimitLowerAdd) {
		this.singleLoanLimitLowerAdd = singleLoanLimitLowerAdd;
	}

	public String getSingleLoanLimitUpperAdd() {
		return singleLoanLimitUpperAdd;
	}

	public void setSingleLoanLimitUpperAdd(String singleLoanLimitUpperAdd) {
		this.singleLoanLimitUpperAdd = singleLoanLimitUpperAdd;
	}

	public BigDecimal getEarlyRepaymentChargeRatio() {
		return earlyRepaymentChargeRatio;
	}

	public void setEarlyRepaymentChargeRatio(BigDecimal earlyRepaymentChargeRatio) {
		this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
	}

	public String getExtensionRatioAdd() {
		return extensionRatioAdd;
	}

	public void setExtensionRatioAdd(String extensionRatioAdd) {
		this.extensionRatioAdd = extensionRatioAdd;
	}

	public String getEarlyRepaymentChargeRatioAdd() {
		return earlyRepaymentChargeRatioAdd;
	}

	public void setEarlyRepaymentChargeRatioAdd(String earlyRepaymentChargeRatioAdd) {
		this.earlyRepaymentChargeRatioAdd = earlyRepaymentChargeRatioAdd;
	}

	public BigDecimal getExtensionRatio() {
		return extensionRatio;
	}

	public void setExtensionRatio(BigDecimal extensionRatio) {
		this.extensionRatio = extensionRatio;
	}

	public String getInterestRateLowerAdd() {
		return interestRateLowerAdd;
	}

	public void setInterestRateLowerAdd(String interestRateLowerAdd) {
		this.interestRateLowerAdd = interestRateLowerAdd;
	}

	public String getInterestRateUpperAdd() {
		return interestRateUpperAdd;
	}

	public void setInterestRateUpperAdd(String interestRateUpperAdd) {
		this.interestRateUpperAdd = interestRateUpperAdd;
	}

	public InterestRateUnit getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(InterestRateUnit interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public DateUnit getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(DateUnit loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public String getRepaymentMethods() {
		return repaymentMethods;
	}

	public void setRepaymentMethods(String repaymentMethods) {
		this.repaymentMethods = repaymentMethods;
	}

	public ChargeMethod getExtensionRatioMethod() {
		return extensionRatioMethod;
	}

	public void setExtensionRatioMethod(ChargeMethod extensionRatioMethod) {
		this.extensionRatioMethod = extensionRatioMethod;
	}

	public ChargeMethod getPenaltyRateMethod() {
		return penaltyRateMethod;
	}

	public void setPenaltyRateMethod(ChargeMethod penaltyRateMethod) {
		this.penaltyRateMethod = penaltyRateMethod;
	}

	public DeductionRule getDeductionRule() {
		return deductionRule;
	}

	public void setDeductionRule(DeductionRule deductionRule) {
		this.deductionRule = deductionRule;
	}

	public ProjectStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProjectStatusEnum status) {
		this.status = status;
	}

	public List<ProjectRepaymentMethodsEnum> getRepaymentMethodList() {
		return repaymentMethodList;
	}

	public void setRepaymentMethodList(List<ProjectRepaymentMethodsEnum> repaymentMethodList) {
		this.repaymentMethodList = repaymentMethodList;
	}

	public List<ProjectRepaymentMethodsEnum> getRepaymentMethodTypeList() {
		return repaymentMethodTypeList;
	}

	public void setRepaymentMethodTypeList(List<ProjectRepaymentMethodsEnum> repaymentMethodTypeList) {
		this.repaymentMethodTypeList = repaymentMethodTypeList;
	}

	public boolean isUseHolidayPolicy() {
		return useHolidayPolicy;
	}

	public void setUseHolidayPolicy(boolean useHolidayPolicy) {
		this.useHolidayPolicy = useHolidayPolicy;
	}

}