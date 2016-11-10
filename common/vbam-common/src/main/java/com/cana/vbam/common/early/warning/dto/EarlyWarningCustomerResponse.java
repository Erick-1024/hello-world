package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

/**
 * 贷后预警（显示）
 * @author cap
 *
 */
public class EarlyWarningCustomerResponse implements Serializable {

	private static final long serialVersionUID = -7654122579802680429L;

	private String memberId;
	
	private String companyName;
	
	private String outCustomerId;
	
	private String outCustomerName;
	
	private String earlywarningLevel;
	
	private String earlywarningLevelDesc;
	
	private Long limit;
	
	private Long residualLimit;
	
	private String action;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getEarlywarningLevel() {
		return earlywarningLevel;
	}

	public void setEarlywarningLevel(String earlywarningLevel) {
		this.earlywarningLevel = earlywarningLevel;
	}

	public String getEarlywarningLevelDesc() {
		return earlywarningLevelDesc;
	}

	public void setEarlywarningLevelDesc(String earlywarningLevelDesc) {
		this.earlywarningLevelDesc = earlywarningLevelDesc;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getResidualLimit() {
		return residualLimit;
	}

	public void setResidualLimit(Long residualLimit) {
		this.residualLimit = residualLimit;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
