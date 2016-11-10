package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

public class EarlywarningLockCustomerRequest implements Serializable{
	
	private static final long serialVersionUID = 4968777676925544163L;

	/**
     *产品id
     */
    private String productId;

    /**
     *融资客户Id
     */
    private String financeId;

    /**
     *融资客户公司名称
     */
    private String financeCompany;

    /**
     * 外部平台客户ID
     */
    private String outCustomerId;
    
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}
    
}
