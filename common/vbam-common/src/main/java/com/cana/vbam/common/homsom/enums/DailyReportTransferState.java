package com.cana.vbam.common.homsom.enums;

/**
 * 日报表资金转移明细表状态
 * @author XuMeng
 *
 */
public enum DailyReportTransferState {

	TRANSFERING("转账中"),
	TRANSFERED("已转账"), // 目前实现中，该状态与提现中状态等价，实际数据库中并不存在该状态的记录
	WITHDRAWING("提现中"),
	WITHDRAWED("已提现"), // 目前实现中的最终状态

	TRANSFER_FAIL("转账失败"), // 转账失败可重新发起转账
	WITHDRAW_FAIL("提现失败"), // 提现失败可重新发起提现
	;
	
	
	private String desc;
	
	private DailyReportTransferState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
