package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;
import java.util.Date;

public class CreditUsedLimitQueryDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	// 时间节点（不包含）
	private Date date;
	
	// 机构
	private String institution;
	
	// 产品ID
	private String productId;
	
	private String customerName;
	
	
	private int page = 1;
	
	private int pageSize = 10;
	
	
	
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
