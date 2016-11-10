package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

public class CounterpartyListSearchDTO implements Serializable{
	
	private static final long serialVersionUID = 2256537982323380678L;

	//分页参数
	private int page;
	
	private int pageSize;
	
	//客户名称			
    private String customerName;
    
    //保理商ｉｄ
    private String  factorId;
    
    //排除列表
    private List<String> exceptList;

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

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public List<String> getExceptList() {
		return exceptList;
	}

	public void setExceptList(List<String> exceptList) {
		this.exceptList = exceptList;
	}
}
