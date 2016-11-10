package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class QueryCustomer4AddUserPrivilegeListItem implements Serializable {

	private static final long serialVersionUID = 303083006443151347L;

	// 客户id
	private String customerId;
	// 客户名称
	private String customerName;
	
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

}
