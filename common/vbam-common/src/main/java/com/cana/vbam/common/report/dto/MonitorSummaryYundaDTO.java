package com.cana.vbam.common.report.dto;

import java.io.Serializable;

/**
 * 韵达监控列表
 * @author jiangzhou.Ren
 * @time 2016年9月27日下午2:43:13
 */
public class MonitorSummaryYundaDTO implements Serializable{

	private static final long serialVersionUID = -547902997367919941L;
	
	/**
	 *  客户名称（企业全称）
	 */
	private String customerName;
	
	/**
	 *  已使用的额度（精度到分）
	 */
	private String usedLimit; 
	
	/**
	 * 韵达评价
	 */
	private String yundaexJudge;
	
	/**
	 * 韵达评级
	 */
	private String grade;
	
	/**
	 * 保证金账户余额/日资金需求
	 */
	private String bailBalance;
	
	/**
	 * 揽派件增长率
	 */
	private String recandsendGrowthRate;
	
	
	/**
	 * 不用于显示
	 */
	private String memberId;
	
	/**
	 * 短期借款
	 */
	private String shortLoan;
	
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(String usedLimit) {
		this.usedLimit = usedLimit;
	}

	public String getYundaexJudge() {
		return yundaexJudge;
	}

	public void setYundaexJudge(String yundaexJudge) {
		this.yundaexJudge = yundaexJudge;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(String bailBalance) {
		this.bailBalance = bailBalance;
	}

	public String getRecandsendGrowthRate() {
		return recandsendGrowthRate;
	}

	public void setRecandsendGrowthRate(String recandsendGrowthRate) {
		this.recandsendGrowthRate = recandsendGrowthRate;
	}

	public String getShortLoan() {
		return shortLoan;
	}

	public void setShortLoan(String shortLoan) {
		this.shortLoan = shortLoan;
	}
	
	
	
	
}
