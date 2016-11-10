package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

import com.cana.vbam.common.repayment.enums.SettleStatus;

public class QueryLoanInfoListRequestDTO implements Serializable{

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
	// 排序条件，推荐使用 id loan_date create_time update_time 进行排序
	private String orderByClause = "upate_time desc";
	// 每页显示行数, 不能大于30
	private int pageSize; 
	 //页码, 从1开始
	private int page = 1; 
	
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
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
