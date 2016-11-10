package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class InvoiceInfoQueryDTO implements Serializable {

	private static final long serialVersionUID = -3461357242972705068L;

	/**
	 * 业务合同号
	 */
	private String businessContractNo;

	/**
	 * 交易对方手ID
	 */
	private String counterpartyId;
	
	private String invoiceBaseId;
	
	private String userId;

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getInvoiceBaseId() {
		return invoiceBaseId;
	}

	public void setInvoiceBaseId(String invoiceBaseId) {
		this.invoiceBaseId = invoiceBaseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
