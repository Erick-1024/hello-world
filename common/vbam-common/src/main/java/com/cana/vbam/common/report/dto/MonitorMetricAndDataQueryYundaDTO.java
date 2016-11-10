package com.cana.vbam.common.report.dto;

import java.io.Serializable;

/**
 * 监控详情数据查询条件
 * @author jiangzhou.Ren
 * @time 2016年9月27日下午2:24:51
 */
public class MonitorMetricAndDataQueryYundaDTO implements Serializable{

	private static final long serialVersionUID = 3535831931681841581L;
	
	//用户id
	private String memberId;
	
	
	private String outCustomerId;
	
	//项目id在controller中set韵达项目
	private String productId;
	
	
	// 开始日期(包括)
	private String startDate;
	
	// 结束日期(包括)
	private String endDate;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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
	
	
	
}
