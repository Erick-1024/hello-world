package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;

public class MatchLenderRequest implements Serializable{
	
	private static final long serialVersionUID = 6679549371126239545L;

	private String canaCustomerId;
	
	private long financeAmount;
	
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

}
