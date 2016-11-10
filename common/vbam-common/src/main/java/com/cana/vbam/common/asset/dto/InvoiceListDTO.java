package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.asset.enums.ActionMode;

/**
 * 应收账款DTO
 * 
 * @author guguanggong
 *
 */
public class InvoiceListDTO implements Serializable {

	private static final long serialVersionUID = 1058603139429341468L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 客户id
	 */
	private String customerId;
	
	/**
	 * 客户名称
	 */
	private String customerName;

	/**
	 * 币别
	 */
	private String currency;

	private String currencyDesc;
	
	/**
	 * 业务产品
	 * 
	 */
	private String businessProduct;

	private String businessProductDesc;
	
	/**
	 * 业务合同号
	 */
	private String businessContractNo;

	/**
	 * 单证笔数
	 */
	private int countInvoice;

	/**
	 * 账款余额（列表页面）
	 */
	private String sumInvoiceAmt;

	/**
	 * 放款状态
	 */
	private String loanState;
	
	private String loanStateDesc;
	
	/**
	 * 应收账款信息
	 */
	private List<InvoiceInfoDTO> invoiceInfoDTOs;

	/**
	 * 费用列表
	 */
	 private List<ExpenseDTO> expenseDTOs;

	/**
	 * 操作类型
	 * 
	 * @return
	 */
	private String actionMode;

	public String getActionMode() {
		return actionMode;
	}

	public void setActionMode(String actionMode) {
		this.actionMode = actionMode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public int getCountInvoice() {
		return countInvoice;
	}

	public void setCountInvoice(int countInvoice) {
		this.countInvoice = countInvoice;
	}

	public String getSumInvoiceAmt() {
		return sumInvoiceAmt;
	}

	public void setSumInvoiceAmt(String sumInvoiceAmt) {
		this.sumInvoiceAmt = sumInvoiceAmt;
	}

	public List<InvoiceInfoDTO> getInvoiceInfoDTOs() {
		return invoiceInfoDTOs;
	}

	public void setInvoiceInfoDTOs(List<InvoiceInfoDTO> invoiceInfoDTOs) {
		this.invoiceInfoDTOs = invoiceInfoDTOs;
	}

	public String getLoanState() {
		return loanState;
	}

	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}

	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}

	public String getBusinessProductDesc() {
		return businessProductDesc;
	}

	public void setBusinessProductDesc(String businessProductDesc) {
		this.businessProductDesc = businessProductDesc;
	}

	public String getLoanStateDesc() {
		return loanStateDesc;
	}

	public void setLoanStateDesc(String loanStateDesc) {
		this.loanStateDesc = loanStateDesc;
	}

	public List<ExpenseDTO> getExpenseDTOs() {
		return expenseDTOs;
	}

	public void setExpenseDTOs(List<ExpenseDTO> expenseDTOs) {
		this.expenseDTOs = expenseDTOs;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	

}
