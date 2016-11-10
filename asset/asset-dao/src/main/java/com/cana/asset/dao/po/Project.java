/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Project implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *项目名称
     */
    private String name;

    /**
     *项目类型，平台、保理、租赁、小贷
     */
    private String type;

    /**
     *项目状态：立项、进行中、暂停、结束
     */
    private String status;

    /**
     *核心企业ID
     */
    private String coreCompanyId;

    /**
     *核心企业名称
     */
    private String coreCompanyName;

    /**
     *核心企业组织机构代码
     */
    private String coreOrganizationCode;

    /**
     *核心企业营业执照号码
     */
    private String coreBusinessLicenceCode;

    /**
     *核心企业税务登记证号吗
     */
    private String coreTaxRegistrationCertificateCode;

    /**
     *行业
     */
    private String coreIndustry;

    /**
     *经济类型
     */
    private String coreEconomicCategory;

    /**
     *核心企业银行账号
     */
    private String coreAccountNo;

    /**
     *融资申请人
     */
    private String financeApplicant;

    /**
     *单笔贷款下限
     */
    private Long singleLoanLimitLower;

    /**
     *单笔贷款上限
     */
    private Long singleLoanLimitUpper;

    /**
     *利率单位，see InterestRateUnit
     */
    private String interestRateUnit;

    /**
     *利率区间下限
     */
    private BigDecimal interestRateLower;

    /**
     *利率区间上限
     */
    private BigDecimal interestRateUpper;

    /**
     *期限单位，see DateUnit
     */
    private String loanPeriodUnit;

    /**
     *期限下限
     */
    private Integer loanPeriodLower;

    /**
     *期限上限
     */
    private Integer loanPeriodUpper;

    /**
     *see RepaymentType，允许的还本付息方式，多个值之间使用英文逗号分隔
     */
    private String repaymentMethods;

    /**
     *提前还款手续费率
     */
    private BigDecimal earlyRepaymentChargeRatio;

    /**
     *展期天数
     */
    private Integer extensionDays;

    /**
     *see ChargeMethod，展期收息方式，加固定值、按比例上浮
     */
    private String extensionRatioMethod;

    /**
     *展期上浮利率值 或者是 展期上浮比例
     */
    private BigDecimal extensionRatio;

    /**
     *是否使用节假日政策，即还款计划遇节假日时，自动计算展期
     */
    private Boolean useHolidayPolicy;

    /**
     *see ChargeMethod，罚息收息方式，加固定值、按比例上浮
     */
    private String penaltyRateMethod;

    /**
     *逾期上浮利率值 或者是 逾期上浮比例
     */
    private BigDecimal penaltyRate;

    /**
     *扣款时点，格式为HH:mm
     */
    private String deductionTime;

    /**
     *扣款规则，see DeductionRule
     */
    private String deductionRule;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *创建用户ID
     */
    private String createUserId;

    /**
     *创建客户ID
     */
    private String createCustomerId;

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
     *项目名称
     */
    public String getName() {
        return name;
    }

    /**
     *项目名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *项目类型，平台、保理、租赁、小贷
     */
    public String getType() {
        return type;
    }

    /**
     *项目类型，平台、保理、租赁、小贷
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *项目状态：立项、进行中、暂停、结束
     */
    public String getStatus() {
        return status;
    }

    /**
     *项目状态：立项、进行中、暂停、结束
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *核心企业ID
     */
    public String getCoreCompanyId() {
        return coreCompanyId;
    }

    /**
     *核心企业ID
     */
    public void setCoreCompanyId(String coreCompanyId) {
        this.coreCompanyId = coreCompanyId == null ? null : coreCompanyId.trim();
    }

    /**
     *核心企业名称
     */
    public String getCoreCompanyName() {
        return coreCompanyName;
    }

    /**
     *核心企业名称
     */
    public void setCoreCompanyName(String coreCompanyName) {
        this.coreCompanyName = coreCompanyName == null ? null : coreCompanyName.trim();
    }

    /**
     *核心企业组织机构代码
     */
    public String getCoreOrganizationCode() {
        return coreOrganizationCode;
    }

    /**
     *核心企业组织机构代码
     */
    public void setCoreOrganizationCode(String coreOrganizationCode) {
        this.coreOrganizationCode = coreOrganizationCode == null ? null : coreOrganizationCode.trim();
    }

    /**
     *核心企业营业执照号码
     */
    public String getCoreBusinessLicenceCode() {
        return coreBusinessLicenceCode;
    }

    /**
     *核心企业营业执照号码
     */
    public void setCoreBusinessLicenceCode(String coreBusinessLicenceCode) {
        this.coreBusinessLicenceCode = coreBusinessLicenceCode == null ? null : coreBusinessLicenceCode.trim();
    }

    /**
     *核心企业税务登记证号吗
     */
    public String getCoreTaxRegistrationCertificateCode() {
        return coreTaxRegistrationCertificateCode;
    }

    /**
     *核心企业税务登记证号吗
     */
    public void setCoreTaxRegistrationCertificateCode(String coreTaxRegistrationCertificateCode) {
        this.coreTaxRegistrationCertificateCode = coreTaxRegistrationCertificateCode == null ? null : coreTaxRegistrationCertificateCode.trim();
    }

    /**
     *行业
     */
    public String getCoreIndustry() {
        return coreIndustry;
    }

    /**
     *行业
     */
    public void setCoreIndustry(String coreIndustry) {
        this.coreIndustry = coreIndustry == null ? null : coreIndustry.trim();
    }

    /**
     *经济类型
     */
    public String getCoreEconomicCategory() {
        return coreEconomicCategory;
    }

    /**
     *经济类型
     */
    public void setCoreEconomicCategory(String coreEconomicCategory) {
        this.coreEconomicCategory = coreEconomicCategory == null ? null : coreEconomicCategory.trim();
    }

    /**
     *核心企业银行账号
     */
    public String getCoreAccountNo() {
        return coreAccountNo;
    }

    /**
     *核心企业银行账号
     */
    public void setCoreAccountNo(String coreAccountNo) {
        this.coreAccountNo = coreAccountNo == null ? null : coreAccountNo.trim();
    }

    /**
     *融资申请人
     */
    public String getFinanceApplicant() {
        return financeApplicant;
    }

    /**
     *融资申请人
     */
    public void setFinanceApplicant(String financeApplicant) {
        this.financeApplicant = financeApplicant == null ? null : financeApplicant.trim();
    }

    /**
     *单笔贷款下限
     */
    public Long getSingleLoanLimitLower() {
        return singleLoanLimitLower;
    }

    /**
     *单笔贷款下限
     */
    public void setSingleLoanLimitLower(Long singleLoanLimitLower) {
        this.singleLoanLimitLower = singleLoanLimitLower;
    }

    /**
     *单笔贷款上限
     */
    public Long getSingleLoanLimitUpper() {
        return singleLoanLimitUpper;
    }

    /**
     *单笔贷款上限
     */
    public void setSingleLoanLimitUpper(Long singleLoanLimitUpper) {
        this.singleLoanLimitUpper = singleLoanLimitUpper;
    }

    /**
     *利率单位，see InterestRateUnit
     */
    public String getInterestRateUnit() {
        return interestRateUnit;
    }

    /**
     *利率单位，see InterestRateUnit
     */
    public void setInterestRateUnit(String interestRateUnit) {
        this.interestRateUnit = interestRateUnit == null ? null : interestRateUnit.trim();
    }

    /**
     *利率区间下限
     */
    public BigDecimal getInterestRateLower() {
        return interestRateLower;
    }

    /**
     *利率区间下限
     */
    public void setInterestRateLower(BigDecimal interestRateLower) {
        this.interestRateLower = interestRateLower;
    }

    /**
     *利率区间上限
     */
    public BigDecimal getInterestRateUpper() {
        return interestRateUpper;
    }

    /**
     *利率区间上限
     */
    public void setInterestRateUpper(BigDecimal interestRateUpper) {
        this.interestRateUpper = interestRateUpper;
    }

    /**
     *期限单位，see DateUnit
     */
    public String getLoanPeriodUnit() {
        return loanPeriodUnit;
    }

    /**
     *期限单位，see DateUnit
     */
    public void setLoanPeriodUnit(String loanPeriodUnit) {
        this.loanPeriodUnit = loanPeriodUnit == null ? null : loanPeriodUnit.trim();
    }

    /**
     *期限下限
     */
    public Integer getLoanPeriodLower() {
        return loanPeriodLower;
    }

    /**
     *期限下限
     */
    public void setLoanPeriodLower(Integer loanPeriodLower) {
        this.loanPeriodLower = loanPeriodLower;
    }

    /**
     *期限上限
     */
    public Integer getLoanPeriodUpper() {
        return loanPeriodUpper;
    }

    /**
     *期限上限
     */
    public void setLoanPeriodUpper(Integer loanPeriodUpper) {
        this.loanPeriodUpper = loanPeriodUpper;
    }

    /**
     *see RepaymentType，允许的还本付息方式，多个值之间使用英文逗号分隔
     */
    public String getRepaymentMethods() {
        return repaymentMethods;
    }

    /**
     *see RepaymentType，允许的还本付息方式，多个值之间使用英文逗号分隔
     */
    public void setRepaymentMethods(String repaymentMethods) {
        this.repaymentMethods = repaymentMethods == null ? null : repaymentMethods.trim();
    }

    /**
     *提前还款手续费率
     */
    public BigDecimal getEarlyRepaymentChargeRatio() {
        return earlyRepaymentChargeRatio;
    }

    /**
     *提前还款手续费率
     */
    public void setEarlyRepaymentChargeRatio(BigDecimal earlyRepaymentChargeRatio) {
        this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
    }

    /**
     *展期天数
     */
    public Integer getExtensionDays() {
        return extensionDays;
    }

    /**
     *展期天数
     */
    public void setExtensionDays(Integer extensionDays) {
        this.extensionDays = extensionDays;
    }

    /**
     *see ChargeMethod，展期收息方式，加固定值、按比例上浮
     */
    public String getExtensionRatioMethod() {
        return extensionRatioMethod;
    }

    /**
     *see ChargeMethod，展期收息方式，加固定值、按比例上浮
     */
    public void setExtensionRatioMethod(String extensionRatioMethod) {
        this.extensionRatioMethod = extensionRatioMethod == null ? null : extensionRatioMethod.trim();
    }

    /**
     *展期上浮利率值 或者是 展期上浮比例
     */
    public BigDecimal getExtensionRatio() {
        return extensionRatio;
    }

    /**
     *展期上浮利率值 或者是 展期上浮比例
     */
    public void setExtensionRatio(BigDecimal extensionRatio) {
        this.extensionRatio = extensionRatio;
    }

    /**
     *是否使用节假日政策，即还款计划遇节假日时，自动计算展期
     */
    public Boolean getUseHolidayPolicy() {
        return useHolidayPolicy;
    }

    /**
     *是否使用节假日政策，即还款计划遇节假日时，自动计算展期
     */
    public void setUseHolidayPolicy(Boolean useHolidayPolicy) {
        this.useHolidayPolicy = useHolidayPolicy;
    }

    /**
     *see ChargeMethod，罚息收息方式，加固定值、按比例上浮
     */
    public String getPenaltyRateMethod() {
        return penaltyRateMethod;
    }

    /**
     *see ChargeMethod，罚息收息方式，加固定值、按比例上浮
     */
    public void setPenaltyRateMethod(String penaltyRateMethod) {
        this.penaltyRateMethod = penaltyRateMethod == null ? null : penaltyRateMethod.trim();
    }

    /**
     *逾期上浮利率值 或者是 逾期上浮比例
     */
    public BigDecimal getPenaltyRate() {
        return penaltyRate;
    }

    /**
     *逾期上浮利率值 或者是 逾期上浮比例
     */
    public void setPenaltyRate(BigDecimal penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    /**
     *扣款时点，格式为HH:mm
     */
    public String getDeductionTime() {
        return deductionTime;
    }

    /**
     *扣款时点，格式为HH:mm
     */
    public void setDeductionTime(String deductionTime) {
        this.deductionTime = deductionTime == null ? null : deductionTime.trim();
    }

    /**
     *扣款规则，see DeductionRule
     */
    public String getDeductionRule() {
        return deductionRule;
    }

    /**
     *扣款规则，see DeductionRule
     */
    public void setDeductionRule(String deductionRule) {
        this.deductionRule = deductionRule == null ? null : deductionRule.trim();
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
     *创建用户ID
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     *创建用户ID
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     *创建客户ID
     */
    public String getCreateCustomerId() {
        return createCustomerId;
    }

    /**
     *创建客户ID
     */
    public void setCreateCustomerId(String createCustomerId) {
        this.createCustomerId = createCustomerId == null ? null : createCustomerId.trim();
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
        Project other = (Project) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCoreCompanyId() == null ? other.getCoreCompanyId() == null : this.getCoreCompanyId().equals(other.getCoreCompanyId()))
            && (this.getCoreCompanyName() == null ? other.getCoreCompanyName() == null : this.getCoreCompanyName().equals(other.getCoreCompanyName()))
            && (this.getCoreOrganizationCode() == null ? other.getCoreOrganizationCode() == null : this.getCoreOrganizationCode().equals(other.getCoreOrganizationCode()))
            && (this.getCoreBusinessLicenceCode() == null ? other.getCoreBusinessLicenceCode() == null : this.getCoreBusinessLicenceCode().equals(other.getCoreBusinessLicenceCode()))
            && (this.getCoreTaxRegistrationCertificateCode() == null ? other.getCoreTaxRegistrationCertificateCode() == null : this.getCoreTaxRegistrationCertificateCode().equals(other.getCoreTaxRegistrationCertificateCode()))
            && (this.getCoreIndustry() == null ? other.getCoreIndustry() == null : this.getCoreIndustry().equals(other.getCoreIndustry()))
            && (this.getCoreEconomicCategory() == null ? other.getCoreEconomicCategory() == null : this.getCoreEconomicCategory().equals(other.getCoreEconomicCategory()))
            && (this.getCoreAccountNo() == null ? other.getCoreAccountNo() == null : this.getCoreAccountNo().equals(other.getCoreAccountNo()))
            && (this.getFinanceApplicant() == null ? other.getFinanceApplicant() == null : this.getFinanceApplicant().equals(other.getFinanceApplicant()))
            && (this.getSingleLoanLimitLower() == null ? other.getSingleLoanLimitLower() == null : this.getSingleLoanLimitLower().equals(other.getSingleLoanLimitLower()))
            && (this.getSingleLoanLimitUpper() == null ? other.getSingleLoanLimitUpper() == null : this.getSingleLoanLimitUpper().equals(other.getSingleLoanLimitUpper()))
            && (this.getInterestRateUnit() == null ? other.getInterestRateUnit() == null : this.getInterestRateUnit().equals(other.getInterestRateUnit()))
            && (this.getInterestRateLower() == null ? other.getInterestRateLower() == null : this.getInterestRateLower().equals(other.getInterestRateLower()))
            && (this.getInterestRateUpper() == null ? other.getInterestRateUpper() == null : this.getInterestRateUpper().equals(other.getInterestRateUpper()))
            && (this.getLoanPeriodUnit() == null ? other.getLoanPeriodUnit() == null : this.getLoanPeriodUnit().equals(other.getLoanPeriodUnit()))
            && (this.getLoanPeriodLower() == null ? other.getLoanPeriodLower() == null : this.getLoanPeriodLower().equals(other.getLoanPeriodLower()))
            && (this.getLoanPeriodUpper() == null ? other.getLoanPeriodUpper() == null : this.getLoanPeriodUpper().equals(other.getLoanPeriodUpper()))
            && (this.getRepaymentMethods() == null ? other.getRepaymentMethods() == null : this.getRepaymentMethods().equals(other.getRepaymentMethods()))
            && (this.getEarlyRepaymentChargeRatio() == null ? other.getEarlyRepaymentChargeRatio() == null : this.getEarlyRepaymentChargeRatio().equals(other.getEarlyRepaymentChargeRatio()))
            && (this.getExtensionDays() == null ? other.getExtensionDays() == null : this.getExtensionDays().equals(other.getExtensionDays()))
            && (this.getExtensionRatioMethod() == null ? other.getExtensionRatioMethod() == null : this.getExtensionRatioMethod().equals(other.getExtensionRatioMethod()))
            && (this.getExtensionRatio() == null ? other.getExtensionRatio() == null : this.getExtensionRatio().equals(other.getExtensionRatio()))
            && (this.getUseHolidayPolicy() == null ? other.getUseHolidayPolicy() == null : this.getUseHolidayPolicy().equals(other.getUseHolidayPolicy()))
            && (this.getPenaltyRateMethod() == null ? other.getPenaltyRateMethod() == null : this.getPenaltyRateMethod().equals(other.getPenaltyRateMethod()))
            && (this.getPenaltyRate() == null ? other.getPenaltyRate() == null : this.getPenaltyRate().equals(other.getPenaltyRate()))
            && (this.getDeductionTime() == null ? other.getDeductionTime() == null : this.getDeductionTime().equals(other.getDeductionTime()))
            && (this.getDeductionRule() == null ? other.getDeductionRule() == null : this.getDeductionRule().equals(other.getDeductionRule()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateCustomerId() == null ? other.getCreateCustomerId() == null : this.getCreateCustomerId().equals(other.getCreateCustomerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCoreCompanyId() == null) ? 0 : getCoreCompanyId().hashCode());
        result = prime * result + ((getCoreCompanyName() == null) ? 0 : getCoreCompanyName().hashCode());
        result = prime * result + ((getCoreOrganizationCode() == null) ? 0 : getCoreOrganizationCode().hashCode());
        result = prime * result + ((getCoreBusinessLicenceCode() == null) ? 0 : getCoreBusinessLicenceCode().hashCode());
        result = prime * result + ((getCoreTaxRegistrationCertificateCode() == null) ? 0 : getCoreTaxRegistrationCertificateCode().hashCode());
        result = prime * result + ((getCoreIndustry() == null) ? 0 : getCoreIndustry().hashCode());
        result = prime * result + ((getCoreEconomicCategory() == null) ? 0 : getCoreEconomicCategory().hashCode());
        result = prime * result + ((getCoreAccountNo() == null) ? 0 : getCoreAccountNo().hashCode());
        result = prime * result + ((getFinanceApplicant() == null) ? 0 : getFinanceApplicant().hashCode());
        result = prime * result + ((getSingleLoanLimitLower() == null) ? 0 : getSingleLoanLimitLower().hashCode());
        result = prime * result + ((getSingleLoanLimitUpper() == null) ? 0 : getSingleLoanLimitUpper().hashCode());
        result = prime * result + ((getInterestRateUnit() == null) ? 0 : getInterestRateUnit().hashCode());
        result = prime * result + ((getInterestRateLower() == null) ? 0 : getInterestRateLower().hashCode());
        result = prime * result + ((getInterestRateUpper() == null) ? 0 : getInterestRateUpper().hashCode());
        result = prime * result + ((getLoanPeriodUnit() == null) ? 0 : getLoanPeriodUnit().hashCode());
        result = prime * result + ((getLoanPeriodLower() == null) ? 0 : getLoanPeriodLower().hashCode());
        result = prime * result + ((getLoanPeriodUpper() == null) ? 0 : getLoanPeriodUpper().hashCode());
        result = prime * result + ((getRepaymentMethods() == null) ? 0 : getRepaymentMethods().hashCode());
        result = prime * result + ((getEarlyRepaymentChargeRatio() == null) ? 0 : getEarlyRepaymentChargeRatio().hashCode());
        result = prime * result + ((getExtensionDays() == null) ? 0 : getExtensionDays().hashCode());
        result = prime * result + ((getExtensionRatioMethod() == null) ? 0 : getExtensionRatioMethod().hashCode());
        result = prime * result + ((getExtensionRatio() == null) ? 0 : getExtensionRatio().hashCode());
        result = prime * result + ((getUseHolidayPolicy() == null) ? 0 : getUseHolidayPolicy().hashCode());
        result = prime * result + ((getPenaltyRateMethod() == null) ? 0 : getPenaltyRateMethod().hashCode());
        result = prime * result + ((getPenaltyRate() == null) ? 0 : getPenaltyRate().hashCode());
        result = prime * result + ((getDeductionTime() == null) ? 0 : getDeductionTime().hashCode());
        result = prime * result + ((getDeductionRule() == null) ? 0 : getDeductionRule().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateCustomerId() == null) ? 0 : getCreateCustomerId().hashCode());
        return result;
    }
}