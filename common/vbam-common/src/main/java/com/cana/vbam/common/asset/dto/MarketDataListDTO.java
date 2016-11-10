package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

public class MarketDataListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 318449401716976480L;

	private String id;
	
	private String projectName; 
	
	private String supervisionAgency;
	private String supervisionAgencyDesc;
	
	private String underlyingAssetType;
	
	private String issueTotalAmount;
	
	private String issueDate;
	
	private String AAAAverageInterestRate;
	
	private String priorityAverageInterestRate;
	
	private String originator;
	
	private String issuer;
	
	private List<MarketDataProductDTO> productDTOs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSupervisionAgency() {
		return supervisionAgency;
	}

	public void setSupervisionAgency(String supervisionAgency) {
		this.supervisionAgency = supervisionAgency;
	}

	public String getUnderlyingAssetType() {
		return underlyingAssetType;
	}

	public void setUnderlyingAssetType(String underlyingAssetType) {
		this.underlyingAssetType = underlyingAssetType;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getAAAAverageInterestRate() {
		return AAAAverageInterestRate;
	}

	public void setAAAAverageInterestRate(String aAAAverageInterestRate) {
		AAAAverageInterestRate = aAAAverageInterestRate;
	}

	public String getPriorityAverageInterestRate() {
		return priorityAverageInterestRate;
	}

	public void setPriorityAverageInterestRate(String priorityAverageInterestRate) {
		this.priorityAverageInterestRate = priorityAverageInterestRate;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getSupervisionAgencyDesc() {
		return supervisionAgencyDesc;
	}

	public void setSupervisionAgencyDesc(String supervisionAgencyDesc) {
		this.supervisionAgencyDesc = supervisionAgencyDesc;
	}

	public List<MarketDataProductDTO> getProductDTOs() {
		return productDTOs;
	}

	public void setProductDTOs(List<MarketDataProductDTO> productDTOs) {
		this.productDTOs = productDTOs;
	}

	public String getIssueTotalAmount() {
		return issueTotalAmount;
	}

	public void setIssueTotalAmount(String issueTotalAmount) {
		this.issueTotalAmount = issueTotalAmount;
	}
	
}
