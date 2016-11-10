package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class ReportFactorFinanceCountDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3814696241446485375L;

	/**
     *报表日期
     */
    private String reportDate;

    /**
     * 查询选项
     */
    private String businessProduct;
    
    /**
     *放款笔数
     */
    private String loanItems;

    /**
     *逾期笔数
     */
    private String overdueItems;

    /**
     *展期笔数
     */
    private String extensionItems;

    /**
     *还款笔数
     */
    private String repaymentItems;

    /**
     *调账笔数
     */
    private String adjustItems;
    
    /**
     * 新增逾期客户数
     */
    private String overdueCustomer;

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getLoanItems() {
		return loanItems;
	}

	public void setLoanItems(String loanItems) {
		this.loanItems = loanItems;
	}

	public String getOverdueItems() {
		return overdueItems;
	}

	public void setOverdueItems(String overdueItems) {
		this.overdueItems = overdueItems;
	}

	public String getExtensionItems() {
		return extensionItems;
	}

	public void setExtensionItems(String extensionItems) {
		this.extensionItems = extensionItems;
	}

	public String getRepaymentItems() {
		return repaymentItems;
	}

	public void setRepaymentItems(String repaymentItems) {
		this.repaymentItems = repaymentItems;
	}

	public String getAdjustItems() {
		return adjustItems;
	}

	public void setAdjustItems(String adjustItems) {
		this.adjustItems = adjustItems;
	}

	public String getOverdueCustomer() {
		return overdueCustomer;
	}

	public void setOverdueCustomer(String overdueCustomer) {
		this.overdueCustomer = overdueCustomer;
	}
    
}