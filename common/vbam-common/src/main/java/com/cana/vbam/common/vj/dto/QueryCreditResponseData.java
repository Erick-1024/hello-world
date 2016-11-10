package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

import com.cana.vbam.common.credit.enums.CreditLimitStatus;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;

public class QueryCreditResponseData implements Serializable{
	
	private static final long serialVersionUID = -7631087520800755170L;

	private String canaCustomerId;
	
	private CreditLimitStatus creditState;
	
	private long creditLimit;
	
	private long availableCreditLimit;
	
	private long usedCreditLimit;
	
	private DateUnit loanPeriodUnit; 
	
	private int loanPeriod;
	
	private RepaymentType repaymentType;
	
	private InterestRateUnit interestRateUnit;
	
	private String interestRate;

	public String getCanaCustomerId() {
		return canaCustomerId;
	}

	public void setCanaCustomerId(String canaCustomerId) {
		this.canaCustomerId = canaCustomerId;
	}

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

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public RepaymentType getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(RepaymentType repaymentType) {
		this.repaymentType = repaymentType;
	}

	public long getAvailableCreditLimit() {
		return availableCreditLimit;
	}

	public void setAvailableCreditLimit(long availableCreditLimit) {
		this.availableCreditLimit = availableCreditLimit;
	}

	public long getUsedCreditLimit() {
		return usedCreditLimit;
	}

	public void setUsedCreditLimit(long usedCreditLimit) {
		this.usedCreditLimit = usedCreditLimit;
	}

	public CreditLimitStatus getCreditState() {
		return creditState;
	}

	public void setCreditState(CreditLimitStatus creditState) {
		this.creditState = creditState;
	}

}
