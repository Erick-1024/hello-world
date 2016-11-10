package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class QueryRepaymentSummaryRequestDTO implements Serializable{

	private static final long serialVersionUID = -4591979363426775477L;
	// 融资客户在cana平台的customer id
	private String financeId;
	// 融资客户的外部帐户ID，可以为空
	private String outCustomerId;
	// 保理商在cana平台的customer id
	private String factorId;
	// 业务产品id
	private String businessProductId;

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getBusinessProductId() {
		return businessProductId;
	}

	public void setBusinessProductId(String businessProductId) {
		this.businessProductId = businessProductId;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}
	
}
