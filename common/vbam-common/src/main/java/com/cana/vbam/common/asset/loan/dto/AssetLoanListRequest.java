package com.cana.vbam.common.asset.loan.dto;

import java.util.List;

import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.dto.Pagination;
import com.cana.vbam.common.repayment.enums.SettleStatus;

public class AssetLoanListRequest extends Pagination {

	private static final long serialVersionUID = 1554129652724441950L;

	private String customerName;
	
	private String businessContractNo;
	
	private BusinessProduct businessProduct;
	
	private SettleStatus settleStatus;
	
	private String loanStartDate;
	
	private String loanEndDate;
	
	private String dueStartDate;
	
	private String dueEndDate;
	
	private String userId;
	
	private String factorId;
	
	private List<String> customerIds;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public BusinessProduct getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(BusinessProduct businessProduct) {
		this.businessProduct = businessProduct;
	}

	public SettleStatus getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(SettleStatus settleStatus) {
		this.settleStatus = settleStatus;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public List<String> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}
	
}
