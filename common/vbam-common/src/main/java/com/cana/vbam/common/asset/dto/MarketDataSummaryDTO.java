package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

public class MarketDataSummaryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 394863784884317660L;

	private String minDate;
	
	private String maxDate;
	
	private int issueTotalNum;					//发行产品总数
	
	private String issueTotalAmount;			//发行产品总额
	
	private int recentThirtyNum;				//最近30天发行产品总数
	
	private String recentThirtyAmount;			//最近30天发行产品总额
	
	private List<MarketDataListDTO> recentIssueProducts;			//最新发行企业产品

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public int getIssueTotalNum() {
		return issueTotalNum;
	}

	public void setIssueTotalNum(int issueTotalNum) {
		this.issueTotalNum = issueTotalNum;
	}

	public String getIssueTotalAmount() {
		return issueTotalAmount;
	}

	public void setIssueTotalAmount(String issueTotalAmount) {
		this.issueTotalAmount = issueTotalAmount;
	}

	public int getRecentThirtyNum() {
		return recentThirtyNum;
	}

	public void setRecentThirtyNum(int recentThirtyNum) {
		this.recentThirtyNum = recentThirtyNum;
	}

	public String getRecentThirtyAmount() {
		return recentThirtyAmount;
	}

	public void setRecentThirtyAmount(String recentThirtyAmount) {
		this.recentThirtyAmount = recentThirtyAmount;
	}

	public List<MarketDataListDTO> getRecentIssueProducts() {
		return recentIssueProducts;
	}

	public void setRecentIssueProducts(List<MarketDataListDTO> recentIssueProducts) {
		this.recentIssueProducts = recentIssueProducts;
	}
	
	
}
