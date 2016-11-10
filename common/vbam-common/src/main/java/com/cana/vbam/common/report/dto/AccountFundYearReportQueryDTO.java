/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.report.dto;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class AccountFundYearReportQueryDTO implements Serializable {

	private static final long serialVersionUID = 8686730652816585755L;

	private String customerId; // 当前客户的企业ID
	private String year; // format:2016
	private int page = 1;
	private int pageSize = 10;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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
