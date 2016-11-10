package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorMetricAndDataQueryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String memberId;
	
	private String outCustomerId;
	
	private String productId;
	
	// 开始日期(包括)
	private String startDate;
	
	// 结束日期(包括)
	private String endDate;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
