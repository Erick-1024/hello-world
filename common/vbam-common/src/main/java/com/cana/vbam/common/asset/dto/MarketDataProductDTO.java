package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

public class MarketDataProductDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6564033748142732558L;

	/**
     * 主键
     */
    private String id;

    /**
     *项目名称
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
     * 项目id
     */
    private String projectId;

    /**
     * AAA平均利率
     */
    private String aaaAverageInterestRate;

    /**
     * 优先级平均利率
     */
    private String priorityAverageInterestRate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
