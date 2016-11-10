package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class RepaymentPlanSearchCriteriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4203334901321212576L;

	/**
	 * 每页显示行数
	 */
	private int pageSize;
	
	/**
	 * 页码
	 */
	private int page = 1;

	/**
	 * 用户类型
	 */
	private String userType;
	
    /**
     *融资客户Id
     */
    private String financeId;
    
    /**
    *融资客户公司名称
    */
    private String financeCompany;
    
    /**
     *保理商Id
     */
    private String factorId;
    
    /**
     *保理商公司名称
     */
    private String factorCompany;
    
	/**
     * 放款编号
     */
    private String loanNo;

    /**
     * 平台流水号
     */
    private String loanId;
     
    /**
     *业务产品名称
     */
    private String businessProduct;
    
    /**
     * 起始还款日
     */
    private String beginRepaymentDate;
    
    /**
     * 截止还款日
     */
    private String endRepaymentDate;
    
    /**
    *结清状态（已结清、未结清、全部、逾期）
    */
    private String settleStatus;
    
    /**
     * 是否为逾期列表
     */
    private boolean overdue;
    
    /**
     * 核心企业Id
     */
    private String coreCompanyId;
    
    /**
     * 核心企业名称
     */
    private String coreCompanyName;
    
    /**
     * 微信分页专用
     */
    private String currentLastId;
    
    /**
     * 微信分页专用
     */
    private int offset = 0;
    
	/**
	 * @return the factorId
	 */
	public String getFactorId() {
		return factorId;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the financeCompany
	 */
	public String getFinanceCompany() {
		return financeCompany;
	}

	/**
	 * @param financeCompany the financeCompany to set
	 */
	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

	/**
	 * @return the factorCompany
	 */
	public String getFactorCompany() {
		return factorCompany;
	}

	/**
	 * @param factorCompany the factorCompany to set
	 */
	public void setFactorCompany(String factorCompany) {
		this.factorCompany = factorCompany;
	}

	/**
	 * @return the loanNo
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * @param loanNo the loanNo to set
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	/**
	 * @return the businessProduct
	 */
	public String getBusinessProduct() {
		return businessProduct;
	}

	/**
	 * @param businessProduct the businessProduct to set
	 */
	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getBeginRepaymentDate() {
		return beginRepaymentDate;
	}

	public void setBeginRepaymentDate(String beginRepaymentDate) {
		this.beginRepaymentDate = beginRepaymentDate;
	}

	/**
	 * @return the endRepaymentDate
	 */
	public String getEndRepaymentDate() {
		return endRepaymentDate;
	}

	/**
	 * @param endRepaymentDate the endRepaymentDate to set
	 */
	public void setEndRepaymentDate(String endRepaymentDate) {
		this.endRepaymentDate = endRepaymentDate;
	}

	/**
	 * @return the settleStatus
	 */
	public String getSettleStatus() {
		return settleStatus;
	}

	/**
	 * @param settleStatus the settleStatus to set
	 */
	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	/**
	 * @param factorId the factorId to set
	 */
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	/**
	 * @return the financeId
	 */
	public String getFinanceId() {
		return financeId;
	}

	/**
	 * @param financeId the financeId to set
	 */
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	public boolean isOverdue() {
		return overdue;
	}

	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}

	public String getCoreCompanyId() {
		return coreCompanyId;
	}

	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getCurrentLastId() {
		return currentLastId;
	}

	public void setCurrentLastId(String currentLastId) {
		this.currentLastId = currentLastId;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
