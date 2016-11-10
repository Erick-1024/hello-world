package com.cana.vbam.common.credit.dto.outcustomer;

import java.io.Serializable;

public class OutCustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     *Cana用户的ID
     */
    private String memberId;
    
    /**
     *外部的客户ID
     */
    private String outCustomerId;

    /**
     *机构
     */
    private String institutionId;

    /**
     *客户企业名称
     */
    private String companyName;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
