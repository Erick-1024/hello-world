package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;
import java.util.Date;

public class CustomerApplyMinDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String canaId;
	
	private String companyName;

	private String outCustomerName;
	
	private Date applyDate;
	
	private String auditState;
	
	private String automaticState;
	
	private String auditor;
	
	private String approver;
	
	// customer_apply的ID
	private String id;
	
	private Boolean inWhitelist;
	
	private String auditStateDesc;
	
	private String automaticStateDesc;
	
	//申请人类型
	private String applicantType;
	private String applicantTypeDesc;

	public String getCanaId() {
		return canaId;
	}

	public void setCanaId(String canaId) {
		this.canaId = canaId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuditStateDesc() {
		return auditStateDesc;
	}

	public void setAuditStateDesc(String auditStateDesc) {
		this.auditStateDesc = auditStateDesc;
	}

	public Boolean getInWhitelist() {
		return inWhitelist;
	}

	public void setInWhitelist(Boolean inWhitelist) {
		this.inWhitelist = inWhitelist;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}

	public String getApplicantTypeDesc() {
		return applicantTypeDesc;
	}

	public void setApplicantTypeDesc(String applicantTypeDesc) {
		this.applicantTypeDesc = applicantTypeDesc;
	}

	public String getAutomaticState() {
		return automaticState;
	}

	public void setAutomaticState(String automaticState) {
		this.automaticState = automaticState;
	}

	public String getAutomaticStateDesc() {
		return automaticStateDesc;
	}

	public void setAutomaticStateDesc(String automaticStateDesc) {
		this.automaticStateDesc = automaticStateDesc;
	}
}
