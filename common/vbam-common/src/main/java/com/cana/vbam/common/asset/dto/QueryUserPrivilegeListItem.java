package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class QueryUserPrivilegeListItem implements Serializable{
	private static final long serialVersionUID = 6238403960973335288L;
	// 权限id
	private String id;
	// 客户类型
	private String userTypeDesc;
	// 平台主账号id
	private String masterId;
	// 企业名称
	private String companyName;
	// 保理商id
	private String factorId;
	// 保理商的企业名称
	private String factorName;
	// 客户id
	private String customerId;
	// 客户名称
	private String customerName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserTypeDesc() {
		return userTypeDesc;
	}

	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

}
