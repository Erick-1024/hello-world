package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class QueryPlanListRequest implements Serializable{
	
	private static final long serialVersionUID = -9083460370378024295L;
	
	// 产品id
	private String businessProductId;
	// 融资客户在cana平台的customer id
	private String financeId;
	// 外部客户id
	private String outCustomerId;
	// 开始日期（包含）, 格式: yyyy-MM-dd
	private String startDate;
	// 结束时间(不包含), 格式: yyyy-MM-dd
	private String endDate;
	
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	
	public String getBusinessProductId() {
		return businessProductId;
	}
	public void setBusinessProductId(String businessProductId) {
		this.businessProductId = businessProductId;
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
	public String getOutCustomerId() {
		return outCustomerId;
	}
	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}
	
	
	
}
