package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.repayment.enums.Currency;

public class FactorBusinessInfoDTO implements Serializable{
	
	private static final long serialVersionUID = 5335173601843561688L;

	/**
	 * 交易对手列表
	 */
	private List<BusinessCounterpartyDTO> counterpartyDTOList;
	
	/**
	 * 融资客户Id
	 */
	private String financeCustomerId;

	/**
	 * 融资客户名称
	 */
	private String financeCustomerName;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 业务模式
	 */
	private BusinessProduct businessProduct;
	
	/**
	 * 币种
	 */
	private Currency currency;
	
    /**
     * 保理专用账号
     */
    private String factoringAccount;
    
    /**
     * 结算账户账号
     */
    private String settlementAccount;

	public List<BusinessCounterpartyDTO> getCounterpartyDTOList() {
		return counterpartyDTOList;
	}

	public void setCounterpartyDTOList(
			List<BusinessCounterpartyDTO> counterpartyDTOList) {
		this.counterpartyDTOList = counterpartyDTOList;
	}

	public String getFinanceCustomerId() {
		return financeCustomerId;
	}

	public void setFinanceCustomerId(String financeCustomerId) {
		this.financeCustomerId = financeCustomerId;
	}

	public String getFinanceCustomerName() {
		return financeCustomerName;
	}

	public void setFinanceCustomerName(String financeCustomerName) {
		this.financeCustomerName = financeCustomerName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BusinessProduct getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(BusinessProduct businessProduct) {
		this.businessProduct = businessProduct;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getFactoringAccount() {
		return factoringAccount;
	}

	public void setFactoringAccount(String factoringAccount) {
		this.factoringAccount = factoringAccount;
	}

	public String getSettlementAccount() {
		return settlementAccount;
	}

	public void setSettlementAccount(String settlementAccount) {
		this.settlementAccount = settlementAccount;
	}
    
}
