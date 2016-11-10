package com.cana.vbam.common.credit.dto.marketing;

import java.io.Serializable;

public class CurrentActivityRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String institution;
	
	private String customerId;
	
	private String sign;

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
