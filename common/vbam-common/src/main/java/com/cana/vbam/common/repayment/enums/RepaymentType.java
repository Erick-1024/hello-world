package com.cana.vbam.common.repayment.enums;

import java.util.Arrays;
import java.util.List;

public enum RepaymentType {
	ORDER("订单款回款还款"),
	MATURITY("到期一次性还本及支付收益"),
	MONTHLY("按月支付收益到期还本"),
	EQUALALL("等额本息"),
	EQUALPRINCIPAL("等额本金");
	
	private String desc;
	
	private RepaymentType(String desc) {
		this.desc = desc;
	}
	
	public static RepaymentType getValue(String desc){
		if(ORDER.desc.equals(desc))
			return ORDER;
		if(MATURITY.desc.equals(desc))
			return MATURITY;
		if(MONTHLY.desc.equals(desc))
			return MONTHLY;
		if(EQUALALL.desc.equals(desc))
			return EQUALALL;
		if(EQUALPRINCIPAL.desc.equals(desc))
			return EQUALPRINCIPAL;
		return null;
	}
	
	public String desc() {
		return desc;
	}
	
	public static List<RepaymentType> factorBusinessInterestType(){
		return Arrays.asList(ORDER, MATURITY, MONTHLY);
	}
}
