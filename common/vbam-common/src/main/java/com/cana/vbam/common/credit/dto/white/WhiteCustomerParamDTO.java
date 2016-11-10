/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.vbam.common.credit.dto.white;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class WhiteCustomerParamDTO implements Serializable {

	private static final long serialVersionUID = 357247692305319945L;
	private Integer batchNo;   //批次号
	private Integer canaId; // 采购商ID。唯一
	private String customerName;// 客户名称
	private int page = 1;
	private int pageSize = 10;
	public Integer getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}
	public Integer getCanaId() {
		return canaId;
	}
	public void setCanaId(Integer canaId) {
		this.canaId = canaId;
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
