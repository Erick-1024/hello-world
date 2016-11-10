package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.Date;

public class QueryPlanListCriteria implements Serializable{
	
	private static final long serialVersionUID = -7790323104498042083L;
	// 产品id
	private String businessProductId;
	// 融资客户在cana平台的customer id
	private String financeId;
	// 外部客户id
	private String outCustomerId;
	// 开始时间（包含）
	private Date startTime;
	// 结束时间(不包含)
	private Date endTime;
	// orderByClause
	private String orderByClause;
	// offset, 从0开始
	private int offset = -1; 
	// limit, 返回的最大行数
	private int limit; 
	// 固定还款日期-开始日期(包含), 格式： yyyy-MM-dd
	private String repaymentDateStart;
	// 固定还款日期-结束日期(包含), 格式： yyyy-MM-dd
	private String repaymentDateEnd;
	
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getBusinessProductId() {
		return businessProductId;
	}
	public void setBusinessProductId(String businessProductId) {
		this.businessProductId = businessProductId;
	}
	public String getOrderByClause() {
		return orderByClause;
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getRepaymentDateStart() {
		return repaymentDateStart;
	}
	public void setRepaymentDateStart(String repaymentDateStart) {
		this.repaymentDateStart = repaymentDateStart;
	}
	public String getRepaymentDateEnd() {
		return repaymentDateEnd;
	}
	public void setRepaymentDateEnd(String repaymentDateEnd) {
		this.repaymentDateEnd = repaymentDateEnd;
	}
	public String getOutCustomerId() {
		return outCustomerId;
	}
	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}
	
}
