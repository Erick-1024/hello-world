package com.cana.yundaex.common.dto.personalinfo;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class PersonalInfoQueryCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7083253939302621422L;
	
	private String customerName;
	
	private String auditStartTime;
	
	private String auditEndTime;
	
	private String auditStatus;
	
	private int page;
	
	private int pageSize;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAuditStartTime() {
		return auditStartTime;
	}

	public void setAuditStartTime(String auditStartTime) {
		this.auditStartTime = auditStartTime;
	}

	public String getAuditEndTime() {
		return auditEndTime;
	}

	public void setAuditEndTime(String auditEndTime) {
		this.auditEndTime = auditEndTime;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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
