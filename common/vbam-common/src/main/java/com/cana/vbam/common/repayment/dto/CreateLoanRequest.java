package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;

/**
 * 创建放款请求参数类
 * 
 * @author XuMeng
 *
 */
public class CreateLoanRequest implements Serializable {

	// 放款日期，格式:yyyy-MM-dd。
	private String loanDate;
	// 融资客户在平台的用户id
	private String financeId;
	// 融资客户在cana平台的企业名称
	private String financeCompany;
	// 融资客户在外部系统中的ID
	private String outCustomerId;
	// 融资客户在外部系统中的名称
	private String outCustomerName;
	// 核心企业ID
	private String coreCompanyId;
	// 核心企业名称
	private String coreCompanyName;
	// 保理商id
	private String factorId;
	// 保理商公司名称
	private String factorCompany;
	// 融资客户的还款账号
	private String accountNo;
	// 保理商和融资客户的监管关系id
	private String accountSupervisionId;
	// 放款金额，单位分
	private long financeAmount;
	// 放款期限单位
	private DateUnit loanPeriodUnit;
	// 放款期限
	private int loanPeriod;
	// 利率单位
	private InterestRateUnit interestRateUnit;
	// 利率
	private BigDecimal interestRate;
	// 还本付息方式方式，不支持 到期一次性还本及支付收益 方式
	private RepaymentType repaymentMethod;

	// 产品名称，比如信旅宝等
	private String productName;
	// 产品ID，真旅项目对应的是产品表的ID，韵达使用项目管理中的项目ID
	private String productId;
	// 外部平台的名称，比如真旅网等
	private String institutionName;

	// 下面两个字段要么同时为空，要么同时有值，最后计算出来的罚息率不能小于正常利率
	private BigDecimal penaltyRatio; // 逾期上浮利率值 或者是 逾期上浮比例
	private ChargeMethod penaltyChargeMethod; // 罚息率计算方式
	
	// 保理商回款账号
	private String factorTransferInAccountNo;
	// 保理商指定账扣时间
	private String deductionTime;
	// 扣款规则
	private DeductionRule deductionRule;
	// 展期天数
	private int extensionDays;

	// 下面两个字段要么同时为空，要么同时有值，最后计算出来的利率不能小于正常利率
	// 于在2016年9月的节假日展期需求后，展期利率直接取正常日利率作为展期利率
	@Deprecated private BigDecimal extensionRatio; // 展期上浮利率值 或者是 展期上浮比例
	@Deprecated private ChargeMethod extensionChargeMethod; // 展期率计算方式
	// 提前还款手续费率
    private BigDecimal earlyRepaymentChargeRatio;
    // 是否使用节假日政策
    private boolean useHolidayPolicy;

	private static final long serialVersionUID = -7751118733994545980L;

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
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

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFactorCompany() {
		return factorCompany;
	}

	public void setFactorCompany(String factorCompany) {
		this.factorCompany = factorCompany;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountSupervisionId() {
		return accountSupervisionId;
	}

	public void setAccountSupervisionId(String accountSupervisionId) {
		this.accountSupervisionId = accountSupervisionId;
	}

	public long getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(long financeAmount) {
		this.financeAmount = financeAmount;
	}

	public DateUnit getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(DateUnit loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public InterestRateUnit getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(InterestRateUnit interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public RepaymentType getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(RepaymentType repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public BigDecimal getPenaltyRatio() {
		return penaltyRatio;
	}

	public void setPenaltyRatio(BigDecimal penaltyRatio) {
		this.penaltyRatio = penaltyRatio;
	}

	public String getFactorTransferInAccountNo() {
		return factorTransferInAccountNo;
	}

	public void setFactorTransferInAccountNo(String factorTransferInAccountNo) {
		this.factorTransferInAccountNo = factorTransferInAccountNo;
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
	@Deprecated 
	public BigDecimal getExtensionRatio() {
		return extensionRatio;
	}
	@Deprecated 
	public void setExtensionRatio(BigDecimal extensionRatio) {
		this.extensionRatio = extensionRatio;
	}
	@Deprecated 
	public ChargeMethod getExtensionChargeMethod() {
		return extensionChargeMethod;
	}
	@Deprecated 
	public void setExtensionChargeMethod(ChargeMethod extensionChargeMethod) {
		this.extensionChargeMethod = extensionChargeMethod;
	}

	public BigDecimal getEarlyRepaymentChargeRatio() {
		return earlyRepaymentChargeRatio;
	}

	public void setEarlyRepaymentChargeRatio(BigDecimal earlyRepaymentChargeRatio) {
		this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
	}

	public ChargeMethod getPenaltyChargeMethod() {
		return penaltyChargeMethod;
	}

	public void setPenaltyChargeMethod(ChargeMethod penaltyChargeMethod) {
		this.penaltyChargeMethod = penaltyChargeMethod;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public boolean isUseHolidayPolicy() {
		return useHolidayPolicy;
	}

	public void setUseHolidayPolicy(boolean useHolidayPolicy) {
		this.useHolidayPolicy = useHolidayPolicy;
	}

}
