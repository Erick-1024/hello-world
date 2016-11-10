package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

public class AddUserPrivilegeRequest implements Serializable{
	
	private static final long serialVersionUID = 6843700219289467638L;

	// 平台用户的主账号id
	private String masterId;
	// 客户名称查询
	private String customerNameQuery;
	// 是否选中了当前全部
	private boolean currentAll;
	// 是否选择了当前+未来 全部
	private boolean all;
	// 选中的客户id
	private List<String> customerIdList;
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getCustomerNameQuery() {
		return customerNameQuery;
	}
	public void setCustomerNameQuery(String customerNameQuery) {
		this.customerNameQuery = customerNameQuery;
	}
	public boolean isCurrentAll() {
		return currentAll;
	}
	public void setCurrentAll(boolean currentAll) {
		this.currentAll = currentAll;
	}
	public boolean isAll() {
		return all;
	}
	public void setAll(boolean all) {
		this.all = all;
	}
	public List<String> getCustomerIdList() {
		return customerIdList;
	}
	public void setCustomerIdList(List<String> customerIdList) {
		this.customerIdList = customerIdList;
	}

}
