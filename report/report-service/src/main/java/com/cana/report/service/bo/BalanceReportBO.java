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
public class BalanceReportBO implements Serializable {

	private static final long serialVersionUID = -1283904293910399175L;

	private FundReportState fundReportState = FundReportState.fail; // 资金报表状态
	private Long currentBalance = 0L; // 今日账户余额

	private Long currentOwnSupervisionBalance = 0L; // 今日/年账自有监管帐户余额

	public FundReportState getFundReportState() {
		return fundReportState;
	}

	public void setFundReportState(FundReportState fundReportState) {
		this.fundReportState = fundReportState;
	}

	public Long getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Long currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Long getCurrentOwnSupervisionBalance() {
		return currentOwnSupervisionBalance;
	}

	public void setCurrentOwnSupervisionBalance(Long currentOwnSupervisionBalance) {
		this.currentOwnSupervisionBalance = currentOwnSupervisionBalance;
	}
}
