package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.UserType;

/**
 * 专项计划查询企业名称和客户类型实体
 * @author jiangzhou.Ren
 * @time 2016年9月1日下午1:28:12
 */
public class QueryCompanyListRequest implements Serializable{
	
	private static final long serialVersionUID = 5203100900708321617L;
	
	// 页码
	private int page;
	// 每页的条数
	private int pageSize;
	// 客户类型
	private UserType userType;
	// 企业名称
	private String companyName;
	
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
	
	
}
