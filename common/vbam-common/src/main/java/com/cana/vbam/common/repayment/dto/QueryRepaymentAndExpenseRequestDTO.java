package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class QueryRepaymentAndExpenseRequestDTO implements Serializable{

	private static final long serialVersionUID = -1644294499632021240L;
	/**
	 * 页面选择开始时间
	 */
	private String beginRepaymentDate;
	/**
	 * 页面选择结束时间
	 */
	private String endRepaymentDate;
	/**
	 * 业务产品
	 */
	private String businessProduct;
	/**
	 * 放款编号
	 */
	private String loanNo;
	/**
	 * 平台流水号
	 */
	private String loanId;
	/**
	 * 保理商Id
	 */
	private String factorId;
	/**
	 * 保理商名称
	 */
	private String factorCompany;
	/**
	 * 融资客户Id
	 */
	private String financeId; 
	/**
	 * 融资客户公司名称
	 */
	private String financeCompany;
	/**
	 * 核心企业Id
	 */
	private String coreCompanyId; 
	/**
	 * 核心企业名称	
	 */
	private String coreCompanyName;
	/**
	 * 是否逾期
	 */
	private String overdue;
	/**
	 * 结清状态
	 */
	private String settleStatus;
	/**
	 * 每页显示行数
	 */
	private int pageSize;
	
	/**
	 * 页码
	 */
	private int page = 1;
	public String getBeginRepaymentDate() {
		return beginRepaymentDate;
	}
	public void setBeginRepaymentDate(String beginRepaymentDate) {
		this.beginRepaymentDate = beginRepaymentDate;
	}
	public String getEndRepaymentDate() {
		return endRepaymentDate;
	}
	public void setEndRepaymentDate(String endRepaymentDate) {
		this.endRepaymentDate = endRepaymentDate;
	}
	public String getBusinessProduct() {
		return businessProduct;
	}
	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	public String getFactorCompany() {
		return factorCompany;
	}
	public void setFactorCompany(String factorCompany) {
		this.factorCompany = factorCompany;
	}
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public String getFinanceCompany() {
		return financeCompany;
	}
	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}
	public String getCoreCompanyId() {
		return coreCompanyId;
	}
	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId;
	}
	public String getCoreCompanyName() {
		return coreCompanyName;
	}
	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}
	public String getOverdue() {
		return overdue;
	}
	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}
	public String getSettleStatus() {
		return settleStatus;
	}
	public void setSettleStatus(String settleStatus) {
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
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	} 
}
