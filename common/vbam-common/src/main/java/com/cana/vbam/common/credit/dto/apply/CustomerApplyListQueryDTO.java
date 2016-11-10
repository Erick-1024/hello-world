package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;

import com.cana.vbam.common.credit.enums.AccessAutomaticState;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.cana.vbam.common.credit.enums.ApplyApplicantType;

public class CustomerApplyListQueryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String startDate;
	
	private String endDate;
	
	private String customerName;
	
	private AccessManualState auditState;
	
	private AccessAutomaticState automaticState;
	
	private Boolean inWhitelist;
	
	private ApplyApplicantType applicantType;
	
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

	public Boolean getInWhitelist() {
		return inWhitelist;
	}

	public void setInWhitelist(Boolean inWhitelist) {
		this.inWhitelist = inWhitelist;
	}

	public ApplyApplicantType getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(ApplyApplicantType applicantType) {
		this.applicantType = applicantType;
	}

	public AccessManualState getAuditState() {
		return auditState;
	}

	public void setAuditState(AccessManualState auditState) {
		this.auditState = auditState;
	}

	public AccessAutomaticState getAutomaticState() {
		return automaticState;
	}

	public void setAutomaticState(AccessAutomaticState automaticState) {
		this.automaticState = automaticState;
	}
	
}
