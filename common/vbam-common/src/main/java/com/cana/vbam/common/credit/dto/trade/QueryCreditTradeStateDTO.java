package com.cana.vbam.common.credit.dto.trade;

import java.io.Serializable;

public class QueryCreditTradeStateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String institution;

	private String tradeType;

	private String tradeNo;

	private String sign;

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
