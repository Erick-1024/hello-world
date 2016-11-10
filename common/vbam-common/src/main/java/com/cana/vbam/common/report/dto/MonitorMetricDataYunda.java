package com.cana.vbam.common.report.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class MonitorMetricDataYunda extends MonitorBaseDataYunda implements Serializable{

	private static final long serialVersionUID = -7632859213594512025L;
	
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
	 * 韵达评价
	 */
	private BigDecimal yundaexJudge;
	
	/**
	 * 项目id 
	 */
	private String projectId;
	
		
	/**
	 * 净现金流增长量
	 */
	private BigDecimal netCashflowGrowth;
	
	/**
	 * 最大授信金额
	 */
	private BigDecimal creditLimit;
	
	/**
	 * 逾期次数
	 */
	private BigDecimal overdues;
	
	/**
	 * 短期借款
	 */
	private BigDecimal shortLoan;
	
	/**
	 * 日资金需求
	 */
	private BigDecimal dayRequirements;
	
	
	/**
	 * 保证金余额除以日资金需求
	 */
	private String bailBalanceDivideDayRequirements; 
	
	
	
	/**
	 * 外部客户ｉｄ
	 */
	private String outCustomerId;
	
	
	
	
	public String getBailBalanceDivideDayRequirements() {
		return bailBalanceDivideDayRequirements;
	}

	public void setBailBalanceDivideDayRequirements(String bailBalanceDivideDayRequirements) {
		this.bailBalanceDivideDayRequirements = bailBalanceDivideDayRequirements;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public BigDecimal getYundaexJudge() {
		return yundaexJudge;
	}

	public void setYundaexJudge(BigDecimal yundaexJudge) {
		this.yundaexJudge = yundaexJudge;
	}


	public BigDecimal getYundaexGrade() {
		return yundaexGrade;
	}

	public void setYundaexGrade(BigDecimal yundaexGrade) {
		this.yundaexGrade = yundaexGrade;
	}

	public BigDecimal getNetCashflowGrowth() {
		return netCashflowGrowth;
	}

	public void setNetCashflowGrowth(BigDecimal netCashflowGrowth) {
		this.netCashflowGrowth = netCashflowGrowth;
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

	public BigDecimal getDayRequirements() {
		return dayRequirements;
	}

	public void setDayRequirements(BigDecimal dayRequirements) {
		this.dayRequirements = dayRequirements;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public BigDecimal getShortLoan() {
		return shortLoan;
	}

	public void setShortLoan(BigDecimal shortLoan) {
		this.shortLoan = shortLoan;
	}

	
	
	
}
