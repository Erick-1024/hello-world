package com.cana.vbam.common.credit.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;

/**
 * 授信要素
 * @author renshui
 *
 */
public class CreditFactor implements Serializable{
	
	private static final long serialVersionUID = -2505005563749844310L;

	//授信额度
	private long creditLimit;
	//融资期限单位
	private DateUnit loanPeriodUnit;
	//融资期限
	private int loanPeriod;
	//还款方式
	private RepaymentType repaymentType;
	//利率单位
	private InterestRateUnit interestRateUnit;
	//利率
	private BigDecimal interestRate;
	public long getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(long creditLimit) {
		this.creditLimit = creditLimit;
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
	public RepaymentType getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(RepaymentType repaymentType) {
		this.repaymentType = repaymentType;
	}

}
