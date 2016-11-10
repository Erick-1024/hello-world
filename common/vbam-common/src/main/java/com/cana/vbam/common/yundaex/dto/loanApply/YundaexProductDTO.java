package com.cana.vbam.common.yundaex.dto.loanApply;

import java.io.Serializable;

public class YundaexProductDTO implements Serializable {
	private static final long serialVersionUID = 8294743759347407050L;

	private String id;
	/**
	 * 放款期限
	 */
	private String loanPeriod;
	/**
	 * 放款期限单位
	 */
	private String loanPeriodUnit;
	/**
	 * 利率
	 */
	private String interestRate;
	/**
	 * 利率单位
	 */
	private String interestRateUnit;
	/**
	 * 还款方式
	 */
	private String repaymentType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(String loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

}
