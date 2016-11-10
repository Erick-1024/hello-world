/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service.bo;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class BankFundReportBO implements Serializable {

	private static final long serialVersionUID = -8290633409760038026L;

	private BalanceReportBO balanceReportBO;
	
	private DepositReportBO depositReportBO;
	
	private OtherSupervisionBalanceReportBO otherSupervisionBalanceBO;

	public BalanceReportBO getBalanceReportBO() {
		return balanceReportBO;
	}

	public void setBalanceReportBO(BalanceReportBO balanceReportBO) {
		this.balanceReportBO = balanceReportBO;
	}

	public DepositReportBO getDepositReportBO() {
		return depositReportBO;
	}

	public void setDepositReportBO(DepositReportBO depositReportBO) {
		this.depositReportBO = depositReportBO;
	}

	public OtherSupervisionBalanceReportBO getOtherSupervisionBalanceBO() {
		return otherSupervisionBalanceBO;
	}

	public void setOtherSupervisionBalanceBO(OtherSupervisionBalanceReportBO otherSupervisionBalanceBO) {
		this.otherSupervisionBalanceBO = otherSupervisionBalanceBO;
	}
}
