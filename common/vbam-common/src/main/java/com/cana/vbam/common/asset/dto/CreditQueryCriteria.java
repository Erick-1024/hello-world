/**
 * 
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class CreditQueryCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7050296542796250343L;

	//分页参数
	private int page;
	
	private int pageSize;
	
	private String customerId;

	private String userId;
	
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
