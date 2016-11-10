package com.cana.vbam.common.report.dto;

import java.io.Serializable;


/**
 * 监控详情指标数据
 * @author jiangzhou.Ren
 * @time 2016年9月27日下午2:35:50
 */
public class MonitorMetricDataYundaDTO extends MonitorBaseDataYunda implements Serializable{

	private static final long serialVersionUID = 4323645119487964204L;
	
	/**
	 * 韵达评级
	 */
	private String yundaexGrade;
	
	/**
	 * 揽派件增长率
	 */
	private String recandsendGrowthrate;
	
	/**
	 * 保证金余额
	 */
	private String bailBalance;
	
	
	/**
	 * 日资金需求
	 */
	private String dayRequirements;
	
	
	/**
	 * 净现金流增长量
	 */
	private String netCashflowGrowth;
	
	/**
	 * 最大授信金额
	 */
	private String creditLimit;
	
	/**
	 * 逾期次数
	 */
	private String overdues;
	
	/**
	 * 保证金余额除以日资金需求
	 */
	private String bailBalanceDivideDayRequirements;

	

	public String getBailBalanceDivideDayRequirements() {
		return bailBalanceDivideDayRequirements;
	}

	public void setBailBalanceDivideDayRequirements(String bailBalanceDivideDayRequirements) {
		this.bailBalanceDivideDayRequirements = bailBalanceDivideDayRequirements;
	}

	public String getYundaexGrade() {
		return yundaexGrade;
	}

	public void setYundaexGrade(String yundaexGrade) {
		this.yundaexGrade = yundaexGrade;
	}

	public String getRecandsendGrowthrate() {
		return recandsendGrowthrate;
	}

	public void setRecandsendGrowthrate(String recandsendGrowthrate) {
		this.recandsendGrowthrate = recandsendGrowthrate;
	}

	public String getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(String bailBalance) {
		this.bailBalance = bailBalance;
	}

	public String getDayRequirements() {
		return dayRequirements;
	}

	public void setDayRequirements(String dayRequirements) {
		this.dayRequirements = dayRequirements;
	}

	public String getNetCashflowGrowth() {
		return netCashflowGrowth;
	}

	public void setNetCashflowGrowth(String netCashflowGrowth) {
		this.netCashflowGrowth = netCashflowGrowth;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getOverdues() {
		return overdues;
	}

	public void setOverdues(String overdues) {
		this.overdues = overdues;
	}
	
	
	
}

