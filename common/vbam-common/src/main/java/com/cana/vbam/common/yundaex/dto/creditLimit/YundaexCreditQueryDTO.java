/**
 * 
 */
package com.cana.vbam.common.yundaex.dto.creditLimit;

import java.io.Serializable;

/**
 * 韵达项目-额度列表查询DTO
 * 
 * @author guguanggong
 *
 */
public class YundaexCreditQueryDTO implements Serializable {

	private static final long serialVersionUID = -5012438610501363531L;

	private String projectId;
	
	private String companyName;
	
	private String limitStart;
	
	private String limitEnd;
	
	private String effectiveDateStart;
	
	private String effectiveDateEnd;
	
	private String limitStatus;
	
	private String memberId;

	private int page = 1;

	private int pageSize = 10;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(String limitStart) {
		this.limitStart = limitStart;
	}

	public String getLimitEnd() {
		return limitEnd;
	}

	public void setLimitEnd(String limitEnd) {
		this.limitEnd = limitEnd;
	}

	public String getEffectiveDateStart() {
		return effectiveDateStart;
	}

	public void setEffectiveDateStart(String effectiveDateStart) {
		this.effectiveDateStart = effectiveDateStart;
	}

	public String getEffectiveDateEnd() {
		return effectiveDateEnd;
	}

	public void setEffectiveDateEnd(String effectiveDateEnd) {
		this.effectiveDateEnd = effectiveDateEnd;
	}

	public String getLimitStatus() {
		return limitStatus;
	}

	public void setLimitStatus(String limitStatus) {
		this.limitStatus = limitStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
