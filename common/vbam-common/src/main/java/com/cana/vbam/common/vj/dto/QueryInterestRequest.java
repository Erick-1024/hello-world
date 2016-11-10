package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;

public class QueryInterestRequest implements Serializable {

	private static final long serialVersionUID = 2682114364049846981L;

	private long financeAmount;
	
	private DateUnit loanPeriodUnit;
	
	private int loanPeriod;
	
	private RepaymentType repaymentType;
	
	private InterestRateUnit interestRateUnit;
	
	private String interestRate;
	
	private String loanDate;

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

	public RepaymentType getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(RepaymentType repaymentType) {
		this.repaymentType = repaymentType;
	}

	public InterestRateUnit getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(InterestRateUnit interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	
}
