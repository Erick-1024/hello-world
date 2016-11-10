package com.cana.vbam.common.yundaex.dto.apply;

import java.io.Serializable;
import java.util.Date;

//审核列表页　页面显示
public class YdCustomerApplyMinDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//private Integer canaId;
	
	// customer_apply的ID
	private String id;
	
	private String companyName;
	
	private Date applyDate;
	
	private String auditState;
	
	private String auditor;
	
	
	private String auditStateDesc;

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
	
}
