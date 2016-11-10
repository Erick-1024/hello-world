/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service.bo;

import java.io.Serializable;

import com.cana.vbam.common.report.enums.FundReportState;

/**
 * @author ducer
 *
 */
public class OtherSupervisionBalanceReportBO implements Serializable {

	private static final long serialVersionUID = -3054864955933455681L;
	
	private FundReportState fundReportState = FundReportState.fail; // 资金报表状态
	private Long currentOherSupervisionBalance = 0L; // 今日/年账自有监管帐户余额
	
	public FundReportState getFundReportState() {
		return fundReportState;
	}
	public void setFundReportState(FundReportState fundReportState) {
		this.fundReportState = fundReportState;
	}
	public Long getCurrentOherSupervisionBalance() {
		return currentOherSupervisionBalance;
	}
	public void setCurrentOherSupervisionBalance(Long currentOherSupervisionBalance) {
		this.currentOherSupervisionBalance = currentOherSupervisionBalance;
	}
}
