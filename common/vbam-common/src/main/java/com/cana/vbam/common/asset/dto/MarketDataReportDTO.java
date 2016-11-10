package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MarketDataReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9172450391222202085L;

	private String underlyingAssetType;
	
	private BigDecimal issueAmount;
	
	private int issueNum;
	
	private String month;

	public String getUnderlyingAssetType() {
		return underlyingAssetType;
	}

	public void setUnderlyingAssetType(String underlyingAssetType) {
		this.underlyingAssetType = underlyingAssetType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getIssueAmount() {
		return issueAmount;
	}

	public void setIssueAmount(BigDecimal issueAmount) {
		this.issueAmount = issueAmount;
	}

	public int getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(int issueNum) {
		this.issueNum = issueNum;
	}
	
	
}
