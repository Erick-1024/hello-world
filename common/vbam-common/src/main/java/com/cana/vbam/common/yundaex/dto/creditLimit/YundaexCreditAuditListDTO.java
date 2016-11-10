package com.cana.vbam.common.yundaex.dto.creditLimit;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexCreditAuditListDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -644598898518050299L;

    private String id;
    
    private String memberId;
	
	private String stationNo;
	
	private String companyName;
	
	private BigDecimal bailRatio;
	
	private String grade;
	
	private String lastGrade;
	
	private BigDecimal points;
	
	private BigDecimal lastPoints;
	
	private String totalLimit;
	
	private String lastTotalLimit;
	
	private String auditDate;
	
	private String creditLimitAuditState;
	
	private String creditLimitAuditStateDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getBailRatio() {
		return bailRatio;
	}

	public void setBailRatio(BigDecimal bailRatio) {
		this.bailRatio = bailRatio;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLastGrade() {
		return lastGrade;
	}

	public void setLastGrade(String lastGrade) {
		this.lastGrade = lastGrade;
	}

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public BigDecimal getLastPoints() {
		return lastPoints;
	}

	public void setLastPoints(BigDecimal lastPoints) {
		this.lastPoints = lastPoints;
	}

	public String getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(String totalLimit) {
		this.totalLimit = totalLimit;
	}

	public String getLastTotalLimit() {
		return lastTotalLimit;
	}

	public void setLastTotalLimit(String lastTotalLimit) {
		this.lastTotalLimit = lastTotalLimit;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getCreditLimitAuditState() {
		return creditLimitAuditState;
	}

	public void setCreditLimitAuditState(String creditLimitAuditState) {
		this.creditLimitAuditState = creditLimitAuditState;
	}

	public String getCreditLimitAuditStateDesc() {
		return creditLimitAuditStateDesc;
	}

	public void setCreditLimitAuditStateDesc(String creditLimitAuditStateDesc) {
		this.creditLimitAuditStateDesc = creditLimitAuditStateDesc;
	}
	
	
}
