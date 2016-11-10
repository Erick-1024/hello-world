package com.cana.vbam.common.asset.enums;

/**
 * CustomerTypeEnum
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:45:55
 */
//	客户类型(融资申请人，交易对手，担保方
public enum CustomerTypeEnum {
	
	FINANCEAPPLICANT("融资申请人"),
	COUNTERPARTY("交易对手"),
	GUARANTEE("担保方");
	
	private String desc;

	CustomerTypeEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
