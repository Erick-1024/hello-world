package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class MarketDataProductExcelDTO implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 9129999810189985493L;

	/**
	 * 发行日期
	 */
	private String valueDate;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 基础资产类型
	 */
	private String underlyingAssetType;
	
	/**
     *产品名称
     */
    private String productName;

    /**
     *发行规模(亿元)
     */
    private String issueAmount;

    /**
     *债项评级
     */
    private String debtRating;

    /**
     *利率
     */
    private String interestRate;

    /**
     *评级机构
     */
    private String ratingAgency;
    
    /**
     *AAA平均利率
     */
    private String aaaAverageInterestRate;

    /**
     *优先级平均利率
     */
    private String priorityAverageInterestRate;

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUnderlyingAssetType() {
		return underlyingAssetType;
	}

	public void setUnderlyingAssetType(String underlyingAssetType) {
		this.underlyingAssetType = underlyingAssetType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIssueAmount() {
		return issueAmount;
	}

	public void setIssueAmount(String issueAmount) {
		this.issueAmount = issueAmount;
	}

	public String getDebtRating() {
		return debtRating;
	}

	public void setDebtRating(String debtRating) {
		this.debtRating = debtRating;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getRatingAgency() {
		return ratingAgency;
	}

	public void setRatingAgency(String ratingAgency) {
		this.ratingAgency = ratingAgency;
	}

	public String getAaaAverageInterestRate() {
		return aaaAverageInterestRate;
	}

	public void setAaaAverageInterestRate(String aaaAverageInterestRate) {
		this.aaaAverageInterestRate = aaaAverageInterestRate;
	}

	public String getPriorityAverageInterestRate() {
		return priorityAverageInterestRate;
	}

	public void setPriorityAverageInterestRate(String priorityAverageInterestRate) {
		this.priorityAverageInterestRate = priorityAverageInterestRate;
	}
}