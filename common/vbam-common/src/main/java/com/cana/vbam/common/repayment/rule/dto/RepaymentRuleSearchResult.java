package com.cana.vbam.common.repayment.rule.dto;

import java.io.Serializable;

public class RepaymentRuleSearchResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5504952321654243852L;

	/**
	 * 规则编号
	 */
	private String id;
	
    /**
    *融资客户公司名称，以，号隔开（部分融资客户有用，列表显示用）
    */
	private String fianceCustomerCompanys;
	
    /**
    *保理商回款账户账号
    */
    private String factorTransferInAccountNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFianceCustomerCompanys() {
		return fianceCustomerCompanys;
	}

	public void setFianceCustomerCompanys(String fianceCustomerCompanys) {
		this.fianceCustomerCompanys = fianceCustomerCompanys;
	}

	public String getFactorTransferInAccountNo() {
		return factorTransferInAccountNo;
	}

	public void setFactorTransferInAccountNo(String factorTransferInAccountNo) {
		this.factorTransferInAccountNo = factorTransferInAccountNo;
	}

}
