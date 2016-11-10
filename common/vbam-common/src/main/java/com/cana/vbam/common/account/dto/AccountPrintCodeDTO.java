package com.cana.vbam.common.account.dto;

import java.io.Serializable;

public class AccountPrintCodeDTO implements Serializable {

	private static final long serialVersionUID = -821180338770515828L;
	private String accountNo;// 账号varchar(19)
	private String startDate;// 起始日期char(8) 格式YYYYMMDD
	private String endDate;// 终止日期char(8) 格式YYYYMMDD
	private int startIndex = 1; // 起始记录号
	private int pageSize = 10; // 请求记录条数，最大为10

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
