package com.cana.vbam.common.homsom.enums;

/**
 * @author renshui
 *
 */
public enum LoanState {

	UNFINISHED("未完成"),
	WAITING_FOR_LOAN("待放款"),
	NO_LOAN("无放款"),
	LOANED("已放款"),

	TRANSFERING("转账中"),
	WITHDRAWING("提现中"),
	TRANSFER_FAIL("转账失败"),
	WITHDRAW_FAIL("提现失败"),
	;
	
	
	private String desc;
	
	private LoanState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}

	public static LoanState[] valuesByChannel(Channel channel) {
		if (channel == Channel.HOMSOM)
			return new LoanState[]{UNFINISHED, WAITING_FOR_LOAN, NO_LOAN, LOANED};
		else
			return LoanState.values();
	}
}
