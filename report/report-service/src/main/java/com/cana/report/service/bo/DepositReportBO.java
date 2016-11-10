/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service.bo;

import java.io.Serializable;

import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.report.enums.FundReportState;

/**
 * @author ducer
 *
 */
public class DepositReportBO implements Serializable {

	private static final long serialVersionUID = -5313804577913722623L;

	private FundReportState fundReportState = FundReportState.fail; // 资金报表状态
	private Long depositFund = 0L; // 入金金额
	private Long depositCount = 0L; // 入金次数

	private Long supervisionDepositFund = 0L; // 入金金额
	private Long supervisionDepositCount = 0L; // 入金次数

	public FundReportState getFundReportState() {
		return fundReportState;
	}

	public void setFundReportState(FundReportState fundReportState) {
		this.fundReportState = fundReportState;
	}

	public Long getDepositFund() {
		return depositFund;
	}

	public void setDepositFund(Long depositFund) {
		this.depositFund = depositFund;
	}

	public Long getDepositCount() {
		return depositCount;
	}

	public void setDepositCount(Long depositCount) {
		this.depositCount = depositCount;
	}

	public Long getSupervisionDepositFund() {
		return supervisionDepositFund;
	}

	public void setSupervisionDepositFund(Long supervisionDepositFund) {
		this.supervisionDepositFund = supervisionDepositFund;
	}

	public Long getSupervisionDepositCount() {
		return supervisionDepositCount;
	}

	public void setSupervisionDepositCount(Long supervisionDepositCount) {
		this.supervisionDepositCount = supervisionDepositCount;
	}

	public void update(String superStatus, Long amount) {
		this.depositFund += amount;
		this.depositCount++;
		if (AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(superStatus)) {
			this.supervisionDepositFund += amount;
			this.supervisionDepositCount++;
		}
	}
}
