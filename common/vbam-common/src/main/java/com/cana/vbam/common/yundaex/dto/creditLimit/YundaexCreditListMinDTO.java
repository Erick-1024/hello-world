package com.cana.vbam.common.yundaex.dto.creditLimit;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexCreditListMinDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5706286050458160656L;

	// customer_apply的ID
	private String id;
	
	private String stationNo;
	
	private String companyName;
	
	private BigDecimal bailRatio;
	
	private String grade;
	
	private BigDecimal points;
	
	private String totalLimit;
	
	private String auditTime;
	
	private String creditLimitGenerateState;
	
	private String creditLimitGenerateStateDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(String totalLimit) {
		this.totalLimit = totalLimit;
	}

	public String getCreditLimitGenerateStateDesc() {
		return creditLimitGenerateStateDesc;
	}

	public void setCreditLimitGenerateStateDesc(String creditLimitGenerateStateDesc) {
		this.creditLimitGenerateStateDesc = creditLimitGenerateStateDesc;
	}

	public String getCreditLimitGenerateState() {
		return creditLimitGenerateState;
	}

	public void setCreditLimitGenerateState(String creditLimitGenerateState) {
		this.creditLimitGenerateState = creditLimitGenerateState;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
	
}
