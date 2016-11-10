/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.report.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ducer
 *
 */
public enum FundReportState {

	fail(0),
	trade(2),
	deposit(4),
	balance(8),
	supervision_balance(16),
	success(30),
	;
	
	private int code;
	
	public int getCode() {
		return code;
	}

	FundReportState(int code){
		this.code = code;
	}
	
	public static List<FundReportState> parseFailReport(int code){
		List<FundReportState> states = new ArrayList<FundReportState>();
		if((code & trade.code) == 0)states.add(FundReportState.trade);
		if((code & deposit.code) == 0)states.add(FundReportState.deposit);
		if((code & balance.code) == 0)states.add(FundReportState.balance);
		if((code & supervision_balance.code) == 0)states.add(FundReportState.supervision_balance);
		return states;
	}
	
	public static boolean balancePass(int code){
		return (code & FundReportState.balance.getCode()) > 0;
	}
	
	public static boolean tradePass(int code){
		return (code & FundReportState.trade.getCode()) > 0;
	}
	
	public static boolean depositPass(int code){
		return (code & FundReportState.deposit.getCode()) > 0;
	}
	
	public static boolean supervisionBalancePass(int code){
		return (code & FundReportState.supervision_balance.getCode()) > 0;
	}
}
