package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

import com.cana.vbam.common.repayment.enums.SettleStatus;

public class QueryLoanInfoListCriteria implements Serializable{

	private static final long serialVersionUID = -3933549232631988346L;
	// 融资客户在cana平台的customer id
	private String financeId;
	// 融资客户在cana平台的企业名称, 支持模糊匹配
	private String financeCompany;
	// 融资客户的外部客户ID
	private String outCustomerId;
	// 保理商在cana平台的customer id
	private String factorId;
	// 业务产品id
	private String businessProductId;
	// 放款id
	private String loanInfoId;
	// 开始日期, 格式： yyyy-MM-dd
	private String loanDateStart;
	// 结束日期, 格式： yyyy-MM-dd
	private String loanDateEnd;
	// 开始日期, 格式： yyyy-MM-dd
	private String dueDateStart;
	// 结束日期, 格式： yyyy-MM-dd
	private String dueDateEnd;
	// 结清状态，当前
	private SettleStatus settleStatus;
	// offset, 从0开始
	private int offset = -1; 
	// limit, 返回的最大行数
	private int limit; 
	// orderByClause
	private String orderByClause;
	

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public SettleStatus getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(SettleStatus settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getBusinessProductId() {
		return businessProductId;
	}

	public void setBusinessProductId(String businessProductId) {
		this.businessProductId = businessProductId;
	}

	public String getLoanDateStart() {
		return loanDateStart;
	}

	public void setLoanDateStart(String loanDateStart) {
		this.loanDateStart = loanDateStart;
	}

	public String getLoanDateEnd() {
		return loanDateEnd;
	}

	public void setLoanDateEnd(String loanDateEnd) {
		this.loanDateEnd = loanDateEnd;
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

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getDueDateStart() {
		return dueDateStart;
	}

	public void setDueDateStart(String dueDateStart) {
		this.dueDateStart = dueDateStart;
	}

	public String getDueDateEnd() {
		return dueDateEnd;
	}

	public void setDueDateEnd(String dueDateEnd) {
		this.dueDateEnd = dueDateEnd;
	}

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
	
}
