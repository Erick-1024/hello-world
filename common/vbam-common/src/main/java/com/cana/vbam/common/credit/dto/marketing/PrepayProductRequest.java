package com.cana.vbam.common.credit.dto.marketing;

public class PrepayProductRequest extends CurrentActivityRequest {

	private static final long serialVersionUID = 1L;

	private long prepayAmount;

	public long getPrepayAmount() {
		return prepayAmount;
	}

	public void setPrepayAmount(long prepayAmount) {
		this.prepayAmount = prepayAmount;
	}
	
}
