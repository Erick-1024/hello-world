package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * @author sugar
 *
 */
public class BusinessAndCreditDTO implements Serializable{
	
	private static final long serialVersionUID = -4970238465106845597L;

	/**
	 * 放款编号
	 */
	private String loanInfoId;
	
	/**
	 * 业务基本信息
	 */
	FactorBusinessInfoDTO factorBusinessInfoDTO;
    
    /**
     * 信用额度
     */
    private CreditDTO creditDTO;

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public FactorBusinessInfoDTO getFactorBusinessInfoDTO() {
		return factorBusinessInfoDTO;
	}

	public void setFactorBusinessInfoDTO(FactorBusinessInfoDTO factorBusinessInfoDTO) {
		this.factorBusinessInfoDTO = factorBusinessInfoDTO;
	}

	public CreditDTO getCreditDTO() {
		return creditDTO;
	}

	public void setCreditDTO(CreditDTO creditDTO) {
		this.creditDTO = creditDTO;
	}
}
