package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

/**
 * 预算利息还款计划
 * 
 * @author renshui
 *
 */
public class PreCalcRepaymentPlan implements Serializable {

	private static final long serialVersionUID = -7891134051573305414L;
	// 期数
	private int period;
	// 本期开始日
	private String valueDate;
	// 固定还款日
	private String repaymentDate;
	// 本期应还本金
	private long principal;
	// 本期应还利息
	private long interest;

	public long getInterest() {
		return interest;
	}

	public void setInterest(long interest) {
		this.interest = interest;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public long getPrincipal() {
		return principal;
	}

	public void setPrincipal(long principal) {
		this.principal = principal;
	}

}
