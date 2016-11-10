package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorSummaryQueryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// 客户名称（注册CANA平台的公司名称）
	private String customerName;
	
	private String creditLimitUsedStatus;
	
	private String productId;
	
	private int page = 1;
	
	private int pageSize = 10;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCreditLimitUsedStatus() {
		return creditLimitUsedStatus;
	}

	public void setCreditLimitUsedStatus(String creditLimitUsedStatus) {
		this.creditLimitUsedStatus = creditLimitUsedStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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
