package com.cana.vbam.common.yundaex.dto.apply;

import java.io.Serializable;

public class YdCustomerApplyListQueryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String startDate;
	
	private String endDate;
	
	private String customerName;
	
	private String auditState;
	
	private int page = 1;
	
	private int pageSize = 10;
	
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
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
