package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.UserType;

public class QueryCompany4AddUserPrivilegeRequest implements Serializable {

	private static final long serialVersionUID = 7584999865090452642L;

	// 页码
	private int page;
	// 每页的条数
	private int pageSize;
	// 客户类型
	private UserType userType;
	// 企业名称
	private String companyName;

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
