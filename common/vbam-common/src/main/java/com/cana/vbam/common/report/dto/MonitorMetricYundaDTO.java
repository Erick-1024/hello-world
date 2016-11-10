package com.cana.vbam.common.report.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 韵达监控数据供预警调用原始数据
 * @author jiangzhou.Ren
 * @time 2016年10月10日上午10:09:43
 */
public class MonitorMetricYundaDTO implements Serializable{
	
	private static final long serialVersionUID = -6613076322106137448L;

	/**
	 * 韵达评级
	 */
	private BigDecimal yundaexGrade;
	
	/**
	 * 揽派件增长率
	 */
	private BigDecimal recandsendGrowthrate;
	
	/**
	 * 保证金余额
	 */
	private BigDecimal bailBalance;
	
	/**
	 * 短期借款
	 */
	private BigDecimal shortLoan;
	
	/**
	 * 日资金需求
	 */
	private BigDecimal dayRequirements;
	
	
	/**
	 * 净现金流增长量
	 */
	private BigDecimal netCashflow;
	
	/**
	 * 最大授信金额
	 */
	private BigDecimal creditLimit;
	
	/**
	 * 逾期次数
	 */
	private BigDecimal overdues;
	
	/**
	 * 日期
	 */
	private String date;

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getRecandsendGrowthrate() {
		return recandsendGrowthrate;
	}

	public void setRecandsendGrowthrate(BigDecimal recandsendGrowthrate) {
		this.recandsendGrowthrate = recandsendGrowthrate;
	}

	public BigDecimal getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(BigDecimal bailBalance) {
		this.bailBalance = bailBalance;
	}

	public BigDecimal getDayRequirements() {
		return dayRequirements;
	}

	public void setDayRequirements(BigDecimal dayRequirements) {
		this.dayRequirements = dayRequirements;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getOverdues() {
		return overdues;
	}

	public void setOverdues(BigDecimal overdues) {
		this.overdues = overdues;
	}

	public BigDecimal getYundaexGrade() {
		return yundaexGrade;
	}

	public void setYundaexGrade(BigDecimal yundaexGrade) {
		this.yundaexGrade = yundaexGrade;
	}

	public BigDecimal getNetCashflow() {
		return netCashflow;
	}

	public void setNetCashflow(BigDecimal netCashflow) {
		this.netCashflow = netCashflow;
	}

	public BigDecimal getShortLoan() {
		return shortLoan;
	}

	public void setShortLoan(BigDecimal shortLoan) {
		this.shortLoan = shortLoan;
	}
	
	

}
