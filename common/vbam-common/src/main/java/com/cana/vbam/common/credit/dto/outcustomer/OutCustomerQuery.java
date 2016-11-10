package com.cana.vbam.common.credit.dto.outcustomer;

import java.io.Serializable;

public class OutCustomerQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     *Cana用户的ID
     */
    private String memberId;

    /**
     *机构
     */
    private String institutionId;
    
    /**
     * 外部机构的客户ID
     */
    private String customerId;
    
    /**
     * 公司名称
     */
    private String companyName;

    public OutCustomerQuery() {
    	
    }
    
    public OutCustomerQuery(String memberId) {
    	this.memberId = memberId;
    }
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
