package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.UserType;

public class LoanInfoSearchCriteriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3600802836951236742L;

	/**
	 * 每页显示行数
	 */
	private int pageSize;
	
	/**
	 * 页码
	 */
	private int page = 1;
	
    /**
    *保理商公司名称（列表显示用）
    */
    private String factorCompany;
    
    /**
    *融资客户公司名称
    */
    private String financeCompany;
    /**
     * 融资客户外部帐户名称
     */
    private String outCustomerName;
    
    /**
     * 核心企业名称
     */
    private String coreCompanyName;
    
    /**
    *业务合同号
    */
    private String businessContractNo;

    /**
    *业务产品
    */
    private String businessProduct;

    /**
    *放款日开始
    */
    private String loanStartDate;
    
    /**
    *放款日结束
    */
    private String loanEndDate;

    /**
    *到期日开始
    */
    private String dueStartDate;
    
    /**
    *到期日结束
    */
    private String dueEndDate;
    
    /**
     * 用户类型
     */
    private UserType userType;
    
    /**
     * 是否拥有还款帐号
     */
    private String accountNoStatus;

    /**
     * 放款信息Id
     */
    private String loanId;
    
    /**
     * 结清状态
     */
    private String settleStatus;

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

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public String getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(String loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public String getDueStartDate() {
		return dueStartDate;
	}

	public void setDueStartDate(String dueStartDate) {
		this.dueStartDate = dueStartDate;
	}

	public String getDueEndDate() {
		return dueEndDate;
	}

	public void setDueEndDate(String dueEndDate) {
		this.dueEndDate = dueEndDate;
	}

	public String getFactorCompany()
	{
		return factorCompany;
	}

	public void setFactorCompany(String factorCompany)
	{
		this.factorCompany = factorCompany;
	}

	public UserType getUserType()
	{
		return userType;
	}

	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}

	public String getAccountNoStatus()
	{
		return accountNoStatus;
	}

	public void setAccountNoStatus(String accountNoStatus)
	{
		this.accountNoStatus = accountNoStatus;
	}

	public String getLoanId()
	{
		return loanId;
	}

	public void setLoanId(String loanId)
	{
		this.loanId = loanId;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}
}
