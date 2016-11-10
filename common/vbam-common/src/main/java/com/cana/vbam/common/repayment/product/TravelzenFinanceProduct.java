package com.cana.vbam.common.repayment.product;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;

/**
 * @deprecated 废弃使用，替换为从资产管理系统取项目与合同，来确定客户的产品信息
 */
public class TravelzenFinanceProduct implements Serializable{

	
	private static final long serialVersionUID = 7929740249043281014L;
	// 产品id
	private String productId;
	// 产品名称
	private String productName;
	// 真旅在cana平台中的账号
	private String accountNo;
	// 真旅在cana平台中的账户名
	private String accountName;
	// 放款期限单位
	private DateUnit loanPeriodUnit;
	// 放款期限
	private int loanPeriod;
	// 利率单位
	private InterestRateUnit interestRateUnit;
	// 利率
	private BigDecimal interestRate;
	
	@Deprecated // 真旅融资商角色ID，已经移至配置文件中
	private String finaceRoleId;		//真旅的采购商在凯拿平台的角色ID
	private String factorId;			//真旅的采购商对应的保理商ID
	private String factorName;			//真旅的采购商对应的保理商企业名称
	private String factorAccountNo;		//真旅产品支付、退款使用的保理商的银行账号
	private String institutionName;     //外部机构名称，“真旅网”
	private String coreCompanyId;       //核心企业id
    private String coreCompanyName;     // 核心企业名称
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	@Deprecated
	public String getFinaceRoleId() {
		return finaceRoleId;
	}
	@Deprecated
	public void setFinaceRoleId(String finaceRoleId) {
		this.finaceRoleId = finaceRoleId;
	}
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	public String getFactorAccountNo() {
		return factorAccountNo;
	}
	public void setFactorAccountNo(String factorAccountNo) {
		this.factorAccountNo = factorAccountNo;
	}
	public String getFactorName() {
		return factorName;
	}
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
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
	
}
