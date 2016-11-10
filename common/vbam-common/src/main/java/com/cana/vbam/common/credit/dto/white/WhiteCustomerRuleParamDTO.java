/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.vbam.common.credit.dto.white;

import java.io.Serializable;
import java.util.Date;

import com.travelzen.framework.spring.web.format.annotation.DateFormat;

/**
 * @author ducer
 *
 */
public class WhiteCustomerRuleParamDTO implements Serializable{

	private static final long serialVersionUID = 2393886751122899843L;
	private Integer batchNo;     //批次号
	@DateFormat
	private Date minCreateTime;  //最早创建时间
	@DateFormat
	private Date maxCreateTime;  //最晚创建时间
	private int page = 1;
	private int pageSize = 10;
	public Integer getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}
	public Date getMinCreateTime() {
		return minCreateTime;
	}
	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}
	public Date getMaxCreateTime() {
		return maxCreateTime;
	}
	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
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
