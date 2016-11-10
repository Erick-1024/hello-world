package com.cana.vbam.common.asset.dto;

import java.util.List;

import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.LoanState;
import com.cana.vbam.common.dto.Pagination;

public class FactorBusinessSearchDTO extends Pagination {
	
	private static final long serialVersionUID = -5661587027651156307L;

	/**
     *客户名称
     */
    private String customerName;
    
    /**
     *业务产品
     */
    private BusinessProduct businessProduct;
	
    /**
     *放款状态
     */
    private LoanState loanState;
    
    /**
     * 业务合同号
     */
    private String businessContractNo;
    
    private String userId;
    
    private String factorId;
	
	private List<String> customerIds;
    
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BusinessProduct getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(BusinessProduct businessProduct) {
		this.businessProduct = businessProduct;
	}
	
	public LoanState getLoanState() {
		return loanState;
	}

	public void setLoanState(LoanState loanState) {
		this.loanState = loanState;
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

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}
}
