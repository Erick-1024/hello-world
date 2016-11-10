package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class CounterpartySearchDTO implements Serializable{

	private static final long serialVersionUID = 5260734058150498119L;

	/**
	 * 业务合同号
	 */
	private String businessContractNo;
	
	/**
	 * 客户姓名
	 */
	private String customerName;
	
	private int page;
	
	private int pageSize;

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

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
