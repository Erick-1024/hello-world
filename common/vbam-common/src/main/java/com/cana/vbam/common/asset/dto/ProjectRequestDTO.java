package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ @author jiangzhou.Ren
 * @time 2016年5月17日上午11:10:09
 */
public class ProjectRequestDTO implements Serializable{
	
	private static final long serialVersionUID = 9042881011689512869L;
	private List<ProjectFactorDTO> projectFactors;
	private List<ProjectDocumentDTO> projectDocuments;
	private Boolean havePermissionModifyDocument;	//当前用户是否有权限修改合同文档

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
	private String type;

	/**
	 * 项目状态：立项、进行中、暂停、结束
	 */
	private String status;

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
	private String coreIndustry;

	/**
	 * 经济类型
	 */
	private String coreEconomicCategory;

	/**
	 * 核心企业银行账号
	 */
	private String coreAccountNo;

	/**
	 * 融资申请人
	 */
	private String financeApplicant;

	/**
	 * 单笔贷款下限，单位元
	 */
	private String singleLoanLimitLower;

	/**
	 * 单笔贷款上限，单位元
	 */
	private String singleLoanLimitUpper;

	/**
	 * 利率单位，see InterestRateUnit
	 */
	private String interestRateUnit;

	/**
	 * 利率区间下限
	 */
	private String interestRateLower;

	/**
	 * 利率区间上限
	 */
	private String interestRateUpper;

	/**
	 * 期限单位，see DateUnit
	 */
	private String loanPeriodUnit;

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
	private String earlyRepaymentChargeRatio;

	/**
	 * 展期天数
	 */
	private Integer extensionDays;

	/**
	 * see ChargeMethod，展期收息方式，加固定值、按比例上浮
	 */
	private String extensionRatioMethod;

	/**
	 * 展期上浮利率值 或者是 展期上浮比例
	 */
	private String extensionRatio;

	/**
	 * see ChargeMethod，罚息收息方式，加固定值、按比例上浮
	 */
	private String penaltyRateMethod;

	/**
	 * 逾期上浮利率值 或者是 逾期上浮比例
	 */
	private String penaltyRate;

	/**
	 * 扣款时点，格式为HH:mm
	 */
	private String deductionTime;

	/**
	 * 扣款规则，see DeductionRule
	 */
	private String deductionRule;

	private String productIntroduction;	//产品介绍
	
	private String productTypeDesc;	//产品类型描述

	private boolean useHolidayPolicy; // 是否使用节假日政策，一旦新建，不可修改

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCoreCompanyId() {
		return coreCompanyId;
	}

	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}

	public String getCoreOrganizationCode() {
		return coreOrganizationCode;
	}

	public void setCoreOrganizationCode(String coreOrganizationCode) {
		this.coreOrganizationCode = coreOrganizationCode;
	}

	public String getCoreBusinessLicenceCode() {
		return coreBusinessLicenceCode;
	}

	public void setCoreBusinessLicenceCode(String coreBusinessLicenceCode) {
		this.coreBusinessLicenceCode = coreBusinessLicenceCode;
	}

	public String getCoreTaxRegistrationCertificateCode() {
		return coreTaxRegistrationCertificateCode;
	}

	public void setCoreTaxRegistrationCertificateCode(String coreTaxRegistrationCertificateCode) {
		this.coreTaxRegistrationCertificateCode = coreTaxRegistrationCertificateCode;
	}

	public String getCoreIndustry() {
		return coreIndustry;
	}

	public void setCoreIndustry(String coreIndustry) {
		this.coreIndustry = coreIndustry;
	}

	public String getCoreEconomicCategory() {
		return coreEconomicCategory;
	}

	public void setCoreEconomicCategory(String coreEconomicCategory) {
		this.coreEconomicCategory = coreEconomicCategory;
	}

	public String getCoreAccountNo() {
		return coreAccountNo;
	}

	public void setCoreAccountNo(String coreAccountNo) {
		this.coreAccountNo = coreAccountNo;
	}

	public String getFinanceApplicant() {
		return financeApplicant;
	}

	public void setFinanceApplicant(String financeApplicant) {
		this.financeApplicant = financeApplicant;
	}

	public String getSingleLoanLimitLower() {
		return singleLoanLimitLower;
	}

	public void setSingleLoanLimitLower(String singleLoanLimitLower) {
		this.singleLoanLimitLower = singleLoanLimitLower;
	}

	public String getSingleLoanLimitUpper() {
		return singleLoanLimitUpper;
	}

	public void setSingleLoanLimitUpper(String singleLoanLimitUpper) {
		this.singleLoanLimitUpper = singleLoanLimitUpper;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getInterestRateLower() {
		return interestRateLower;
	}

	public void setInterestRateLower(String interestRateLower) {
		this.interestRateLower = interestRateLower;
	}

	public String getInterestRateUpper() {
		return interestRateUpper;
	}

	public void setInterestRateUpper(String interestRateUpper) {
		this.interestRateUpper = interestRateUpper;
	}

	public String getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(String loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public Integer getLoanPeriodLower() {
		return loanPeriodLower;
	}

	public void setLoanPeriodLower(Integer loanPeriodLower) {
		this.loanPeriodLower = loanPeriodLower;
	}

	public Integer getLoanPeriodUpper() {
		return loanPeriodUpper;
	}

	public void setLoanPeriodUpper(Integer loanPeriodUpper) {
		this.loanPeriodUpper = loanPeriodUpper;
	}

	public String getRepaymentMethods() {
		return repaymentMethods;
	}

	public void setRepaymentMethods(String repaymentMethods) {
		this.repaymentMethods = repaymentMethods;
	}

	public String getEarlyRepaymentChargeRatio() {
		return earlyRepaymentChargeRatio;
	}

	public void setEarlyRepaymentChargeRatio(String earlyRepaymentChargeRatio) {
		this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
	}

	public Integer getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(Integer extensionDays) {
		this.extensionDays = extensionDays;
	}

	public String getExtensionRatioMethod() {
		return extensionRatioMethod;
	}

	public void setExtensionRatioMethod(String extensionRatioMethod) {
		this.extensionRatioMethod = extensionRatioMethod;
	}

	public String getExtensionRatio() {
		return extensionRatio;
	}

	public void setExtensionRatio(String extensionRatio) {
		this.extensionRatio = extensionRatio;
	}

	public String getPenaltyRateMethod() {
		return penaltyRateMethod;
	}

	public void setPenaltyRateMethod(String penaltyRateMethod) {
		this.penaltyRateMethod = penaltyRateMethod;
	}

	public String getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(String penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public String getDeductionTime() {
		return deductionTime;
	}

	public void setDeductionTime(String deductionTime) {
		this.deductionTime = deductionTime;
	}

	public String getDeductionRule() {
		return deductionRule;
	}

	public void setDeductionRule(String deductionRule) {
		this.deductionRule = deductionRule;
	}

	public List<ProjectFactorDTO> getProjectFactors() {
		return projectFactors;
	}

	public void setProjectFactors(List<ProjectFactorDTO> projectFactors) {
		this.projectFactors = projectFactors;
	}

	public List<ProjectDocumentDTO> getProjectDocuments() {
		return projectDocuments;
	}

	public void setProjectDocuments(List<ProjectDocumentDTO> projectDocuments) {
		this.projectDocuments = projectDocuments;
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

	public Boolean getHavePermissionModifyDocument() {
		return havePermissionModifyDocument;
	}

	public void setHavePermissionModifyDocument(Boolean havePermissionModifyDocument) {
		this.havePermissionModifyDocument = havePermissionModifyDocument;
	}

	public boolean isUseHolidayPolicy() {
		return useHolidayPolicy;
	}

	public void setUseHolidayPolicy(boolean useHolidayPolicy) {
		this.useHolidayPolicy = useHolidayPolicy;
	}

}
