package com.cana.vbam.common.repayment.rule.dto;

import java.io.Serializable;

import com.cana.vbam.common.repayment.rule.enums.ScopeOfApplication;

public class RepaymentRuleSearchCriteriaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3447582847684955995L;

	/**
	 * 每页显示行数
	 */
	private int pageSize;
	
	/**
	 * 页码
	 */
	private int page = 1;
	
	/**
	 * 规则编号
	 */
	private String id;
	
	/**
	 * 适用范围：DEFAULT，PART
	 */
	private ScopeOfApplication scopeOfApplication;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ScopeOfApplication getScopeOfApplication() {
		return scopeOfApplication;
	}

	public void setScopeOfApplication(ScopeOfApplication scopeOfApplication) {
		this.scopeOfApplication = scopeOfApplication;
	}
	
}
