package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class QueryCustomer4AddUserPrivilegeRequest implements Serializable {

	private static final long serialVersionUID = -8206821907169183749L;

	// 页码
	private int page;
	// 每页的条数
	private int pageSize;
	// 客户名称
	private String customerName;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
