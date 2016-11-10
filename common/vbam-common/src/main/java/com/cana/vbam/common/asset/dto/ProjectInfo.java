package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.cana.vbam.common.asset.enums.ProjectStatusEnum;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;

public class ProjectInfo implements Serializable{
	
	private static final long serialVersionUID = -5785802005973761090L;
	
	// 项目id
	private String id;
	// 项目名称
	private String projectName;
	// 项目状态
	private ProjectStatusEnum status;
	
	// 资金方列表
	private List<FactorInfo> factors;
	
	// 核心企业ID
	private String coreCompanyId;
	// 核心企业名称
	private String coreCompanyName;
	// 核心企业银行账号
	private String coreAccountNo;
	// 放款期限单位
	private DateUnit loanPeriodUnit;
	// 放款期限-下限
	private int loanPeriodLower;
	// 放款期限-上限
	private int loanPeriodUpper;
	// 利率单位
	private InterestRateUnit interestRateUnit;
	// 利率-下限
	private BigDecimal interestRateLower;
	// 利率-上限
	private BigDecimal interestRateUpper;
	// 还款付息方式
	private List<RepaymentType> repaymentTypes;
	// 提前还款手续费率
	private BigDecimal earlyRepaymentChargeRatio;
	// 逾期上浮利率值 或者是 逾期上浮比例
	private BigDecimal penaltyRatio; 
	// 罚息率计算方式
	private ChargeMethod penaltyChargeMethod; 
	// 保理商指定账扣时间
	private String deductionTime;
	// 扣款规则
	private DeductionRule deductionRule;
	// 展期天数
	private int extensionDays;
	// 展期上浮利率值 或者是 逾期上浮比例
	private BigDecimal extensionRatio; 
	// 展期率计算方式
	private ChargeMethod extensionChargeMethod;
	// 是否使用节假日政策
	private boolean useHolidayPolicy;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public ProjectStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ProjectStatusEnum status) {
		this.status = status;
	}
	public List<FactorInfo> getFactors() {
		return factors;
	}
	public void setFactors(List<FactorInfo> factors) {
		this.factors = factors;
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
	public String getCoreAccountNo() {
		return coreAccountNo;
	}
	public void setCoreAccountNo(String coreAccountNo) {
		this.coreAccountNo = coreAccountNo;
	}
	public DateUnit getLoanPeriodUnit() {
		return loanPeriodUnit;
	}
	public void setLoanPeriodUnit(DateUnit loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}
	public int getLoanPeriodLower() {
		return loanPeriodLower;
	}
	public void setLoanPeriodLower(int loanPeriodLower) {
		this.loanPeriodLower = loanPeriodLower;
	}
	public int getLoanPeriodUpper() {
		return loanPeriodUpper;
	}
	public void setLoanPeriodUpper(int loanPeriodUpper) {
		this.loanPeriodUpper = loanPeriodUpper;
	}
	public InterestRateUnit getInterestRateUnit() {
		return interestRateUnit;
	}
	public void setInterestRateUnit(InterestRateUnit interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}
	public BigDecimal getInterestRateLower() {
		return interestRateLower;
	}
	public void setInterestRateLower(BigDecimal interestRateLower) {
		this.interestRateLower = interestRateLower;
	}
	public BigDecimal getInterestRateUpper() {
		return interestRateUpper;
	}
	public void setInterestRateUpper(BigDecimal interestRateUpper) {
		this.interestRateUpper = interestRateUpper;
	}
	public List<RepaymentType> getRepaymentTypes() {
		return repaymentTypes;
	}
	public void setRepaymentTypes(List<RepaymentType> repaymentTypes) {
		this.repaymentTypes = repaymentTypes;
	}
	public BigDecimal getEarlyRepaymentChargeRatio() {
		return earlyRepaymentChargeRatio;
	}
	public void setEarlyRepaymentChargeRatio(BigDecimal earlyRepaymentChargeRatio) {
		this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
	}
	public BigDecimal getPenaltyRatio() {
		return penaltyRatio;
	}
	public void setPenaltyRatio(BigDecimal penaltyRatio) {
		this.penaltyRatio = penaltyRatio;
	}
	public ChargeMethod getPenaltyChargeMethod() {
		return penaltyChargeMethod;
	}
	public void setPenaltyChargeMethod(ChargeMethod penaltyChargeMethod) {
		this.penaltyChargeMethod = penaltyChargeMethod;
	}
	public String getDeductionTime() {
		return deductionTime;
	}
	public void setDeductionTime(String deductionTime) {
		this.deductionTime = deductionTime;
	}
	public DeductionRule getDeductionRule() {
		return deductionRule;
	}
	public void setDeductionRule(DeductionRule deductionRule) {
		this.deductionRule = deductionRule;
	}
	public int getExtensionDays() {
		return extensionDays;
	}
	public void setExtensionDays(int extensionDays) {
		this.extensionDays = extensionDays;
	}
	public BigDecimal getExtensionRatio() {
		return extensionRatio;
	}
	public void setExtensionRatio(BigDecimal extensionRatio) {
		this.extensionRatio = extensionRatio;
	}
	public ChargeMethod getExtensionChargeMethod() {
		return extensionChargeMethod;
	}
	public void setExtensionChargeMethod(ChargeMethod extensionChargeMethod) {
		this.extensionChargeMethod = extensionChargeMethod;
	}
	public boolean isUseHolidayPolicy() {
		return useHolidayPolicy;
	}
	public void setUseHolidayPolicy(boolean useHolidayPolicy) {
		this.useHolidayPolicy = useHolidayPolicy;
	} 

}
