package com.cana.vbam.common.report.dto;

import java.io.Serializable;

/**
 * 韵达监控列表查询条件
 * @author jiangzhou.Ren
 * @time 2016年9月27日下午2:57:07
 */
public class MonitorSummaryQueryYundaDTO implements Serializable{

	private static final long serialVersionUID = -364541339511882751L;
	
	//客户名称
	private String customerName;
	
	//private String creditLimitUsedStatus;
	
	//项目ｉｄ
	private String productId;
	
	private int page = 1;
	
	private int pageSize = 10;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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
