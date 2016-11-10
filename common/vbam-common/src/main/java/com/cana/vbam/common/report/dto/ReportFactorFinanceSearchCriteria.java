package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class ReportFactorFinanceSearchCriteria implements Serializable{

	private static final long serialVersionUID = 4646431231282709051L;

	private String masterId;
	
	private String startTime;
	
	private String endTime;
	
	private String businessProduct;
	
	private int page = 1;
	
	private int pageSize;

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
