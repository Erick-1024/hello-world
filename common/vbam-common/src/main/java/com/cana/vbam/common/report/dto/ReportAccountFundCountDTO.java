/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.report.dto;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class ReportAccountFundCountDTO extends CanaReportAccountFundCountDTO implements Serializable {

	private static final long serialVersionUID = -2637397536712006612L;

	/**
	 * 自己的监管状态账户的入金笔数
	 */
	private Long supervisionDepositCount;

	/**
	 * 自己的监管状态账户的转入笔数
	 */
	private Long supervisionTransferInCount;

	/**
	 * 自己的监管状态账户的转出笔数
	 */
	private Long supervisionTransferOutCount;

	/**
	 * 自己的监管状态账户的提现笔数
	 */
	private Long supervisionWithdrawCount;

	/**
	 * 自己的监管状态账户的退款笔数，退款行为由平台交易记录判定
	 */
	private Long supervisionRefundCount;

	public Long getSupervisionDepositCount() {
		return supervisionDepositCount;
	}

	public void setSupervisionDepositCount(Long supervisionDepositCount) {
		this.supervisionDepositCount = supervisionDepositCount;
	}

	public Long getSupervisionTransferInCount() {
		return supervisionTransferInCount;
	}

	public void setSupervisionTransferInCount(Long supervisionTransferInCount) {
		this.supervisionTransferInCount = supervisionTransferInCount;
	}

	public Long getSupervisionTransferOutCount() {
		return supervisionTransferOutCount;
	}

	public void setSupervisionTransferOutCount(Long supervisionTransferOutCount) {
		this.supervisionTransferOutCount = supervisionTransferOutCount;
	}

	public Long getSupervisionWithdrawCount() {
		return supervisionWithdrawCount;
	}

	public void setSupervisionWithdrawCount(Long supervisionWithdrawCount) {
		this.supervisionWithdrawCount = supervisionWithdrawCount;
	}

	public Long getSupervisionRefundCount() {
		return supervisionRefundCount;
	}

	public void setSupervisionRefundCount(Long supervisionRefundCount) {
		this.supervisionRefundCount = supervisionRefundCount;
	}

	@Override
	public int compareTo(CanaReportAccountFundCountDTO o) {
		return super.compareTo(o);
	}
}
