package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class QueryCompany4AddUserPrivilegeListItem implements Serializable {

	private static final long serialVersionUID = -2410396057428273946L;
	// 平台主账号id
	private String masterId;
	// 客户类型
	private String userTypeDesc;
	// 企业名称
	private String companyName;
	
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserTypeDesc() {
		return userTypeDesc;
	}
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

}
