package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;

public class CreditUsedLimitRequest implements Serializable{
	
	private static final long serialVersionUID = -2626816159719804110L;

	private String productId;
	
	private String customerName;
	
	private int page = 1;
	
	private int pageSize = 10;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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
