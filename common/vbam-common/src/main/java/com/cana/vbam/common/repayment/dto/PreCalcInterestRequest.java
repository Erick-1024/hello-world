package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;

/**
 * 预算利息请求
 * @author renshui
 *
 */
public class PreCalcInterestRequest implements Serializable{

	private static final long serialVersionUID = -7391904269693911343L;
	
	// 产品id
	private String productId;
	// 融资客户在cana平台的企业id
	private String financeId;
	// 保理商在cana平台的企业id
	private String factorId;
	// 本金
	private long principal;
	// 利率单位
	private InterestRateUnit interestRateUnit;
	// 利率
	private BigDecimal interestRate;
	// 放款日期, 格式: yyyy-MM-dd
	private String loanDate;
	// 放款期限单位
	private DateUnit loanPeriodUnit;
	// 放款期限
	private int loanPeriod;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
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
	public long getPrincipal() {
		return principal;
	}
	public void setPrincipal(long principal) {
		this.principal = principal;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
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
	
}
