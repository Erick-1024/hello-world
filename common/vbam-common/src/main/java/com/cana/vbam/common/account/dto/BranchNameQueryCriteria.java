package com.cana.vbam.common.account.dto;

import java.io.Serializable;

public class BranchNameQueryCriteria implements Serializable{

	private static final long serialVersionUID = -5927064760958851928L;

	private String bankName;
	
	private String province;
	
	private String city;
	
	private String keyWord;
	
	private int page;
	
	private int pageSize;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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
