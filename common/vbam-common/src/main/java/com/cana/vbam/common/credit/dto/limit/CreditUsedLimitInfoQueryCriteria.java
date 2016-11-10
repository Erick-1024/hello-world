package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;

public class CreditUsedLimitInfoQueryCriteria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4278752975049366512L;

	// 机构
	private String institution;

	private String productId;
	
	// 公司名字（用于模糊匹配）
	private String companyName;
	
	private String limitUsedStaus;
	
	private int page = 1;
	
	private int pageSize = 10;
	
	private int limitStart = 0;
	
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLimitUsedStaus() {
		return limitUsedStaus;
	}

	public void setLimitUsedStaus(String limitUsedStaus) {
		this.limitUsedStaus = limitUsedStaus;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		limitStart = (page -1) * pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}
	
}
