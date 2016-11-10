/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cana.vbam.common.account.enums.AccountTradeType;

/**
 * @author ducer
 *
 */
public class AccountCustomerTradeRecordQueryDTO implements Serializable {

	private static final long serialVersionUID = -2894639112179576397L;

	private String customerId;

	private Date minStartDate; // >=

	private Date maxStartDate; // <

	private Date minEndDate; // >=

	private Date maxEndDate; // <

	private List<AccountTradeType> accountTradeTypes;

	private int page = 1;

	private int pageSize = 10;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getMinEndDate() {
		return minEndDate;
	}

	public Date getMinStartDate() {
		return minStartDate;
	}

	public void setMinStartDate(Date minStartDate) {
		this.minStartDate = minStartDate;
	}

	public Date getMaxStartDate() {
		return maxStartDate;
	}

	public void setMaxStartDate(Date maxStartDate) {
		this.maxStartDate = maxStartDate;
	}

	public void setMinEndDate(Date minEndDate) {
		this.minEndDate = minEndDate;
	}

	public Date getMaxEndDate() {
		return maxEndDate;
	}

	public void setMaxEndDate(Date maxEndDate) {
		this.maxEndDate = maxEndDate;
	}

	public List<AccountTradeType> getAccountTradeTypes() {
		return accountTradeTypes;
	}

	public void setAccountTradeTypes(List<AccountTradeType> accountTradeTypes) {
		this.accountTradeTypes = accountTradeTypes;
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
