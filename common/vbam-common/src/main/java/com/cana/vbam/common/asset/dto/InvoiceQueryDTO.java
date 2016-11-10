package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * 应收账款查询DTO
 * 
 * @author guguanggong
 *
 */
public class InvoiceQueryDTO implements Serializable {

	private static final long serialVersionUID = -8420505135760658657L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 客户名称
	 */
	private String customerName;

	/**
	 * 币别
	 */
	private String currencyType;

	/**
	 * 业务产品
	 * 
	 */
	private String businessProduct;

	/**
	 * 业务合同号
	 */
	private String businessContractNo;

	/**
	 * 交易对方手ID
	 */
	private String counterpartyId;

	/**
	 * 操作类型
	 * 
	 */
	private String actionMode;

	/**
	 * 当前页数
	 */
	private int page = 1;

	/**
	 * 页面显示条数
	 */
	private int pageSize = 10;

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

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

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
