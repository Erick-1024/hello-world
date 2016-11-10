package com.cana.vbam.common.credit.openapi;

import java.io.Serializable;

public class CreditCustomerFinanceInfoRequest implements Serializable{

	private static final long serialVersionUID = -3816545756267183872L;
	
	private String institution;//机构
	
	private String customerId;//真旅网采购商Id
	
	private String sign;//签名

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
