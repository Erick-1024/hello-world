package com.cana.vbam.common.homsom.enums;

/**
 * @author renshui
 *
 */
public enum TicketDetailState {

	INIT("初始"),
	NO_COUNTERPARTY("无交易对手"),
	NO_COUNTERPARTY_CONFIG("无交易对手配置信息"),
	INSUFFICIENT_LIMIT("额度不足"),
	LOAN_GENERATED("放款生成");
	
	
	private String desc;
	
	private TicketDetailState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
