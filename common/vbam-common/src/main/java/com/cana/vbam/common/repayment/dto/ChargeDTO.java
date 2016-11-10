package com.cana.vbam.common.repayment.dto;

import com.cana.vbam.common.utils.MoneyArithUtil;

/**
 * @author hu
 *
 */
public class ChargeDTO {

	private long charge;

	public long getCharge() {
		return charge;
	}

	public void setCharge(long charge) {
		this.charge = charge;
	}
	
	public void setCharge(String charge){
		this.charge = MoneyArithUtil.convertStringToMoney(charge);
	}
}
