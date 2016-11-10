package com.cana.vbam.common.vj.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;

public class LoanInformation implements Serializable {

	private static final long serialVersionUID = -9125729673976652144L;

	private String loanId;
	
	private String canaCustomerId;
	
	private long financeAmount;
	
	private long maximumRepaymentAmount;
	
	private SettleStatus settleStatus;

	private String loanDate;
	
	private DateUnit loanPeriodUnit;
	
	private int loanPeriod;
	
	private RepaymentType repaymentType;
	
	private InterestRateUnit interestRateUnit;
	
	private String interestRate;
	
	private List<RepaymentPlan> plans;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	
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

	public long getMaximumRepaymentAmount() {
		return maximumRepaymentAmount;
	}

	public void setMaximumRepaymentAmount(long maximumRepaymentAmount) {
		this.maximumRepaymentAmount = maximumRepaymentAmount;
	}

	public SettleStatus getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(SettleStatus settleStatus) {
		this.settleStatus = settleStatus;
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

	public List<RepaymentPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<RepaymentPlan> plans) {
		this.plans = plans;
	}
	
}
