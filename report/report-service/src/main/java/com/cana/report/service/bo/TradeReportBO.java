/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service.bo;

import java.io.Serializable;

import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.report.enums.FundReportState;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author ducer
 *
 */
public class TradeReportBO implements Serializable {

	private static final long serialVersionUID = 6404836074638747961L;

	private FundReportState fundReportState = FundReportState.fail; // 资金报表状态
	
	private Long transferFund = 0L; // 转账金额
	private Long withdrawFund = 0L; // 提现金额
	private Long withdrawFee = 0L; // 提现手续费
	private Long freezeFund = 0L; // 冻结金额
	private Long unfreezeFund = 0L; // 解冻金额
	private Long refundFund = 0L; // 退款金额
	// 计数
	private Long transferInCount = 0L; // 转入次数
	private Long transferOutCount = 0L; // 转出次数
	private Long withdrawCount = 0L; // 提现次数
	private Long freezeCount = 0L; // 冻结次数
	private Long unfreezeCount = 0L; // 解冻次数
	private Long refundCount = 0L; // 退款次数

	// ----------------自己的监管户的数据

	private Long supervisionTransferFund = 0L; // 转账金额
	private Long supervisionWithdrawFund = 0L; // 提现金额
	private Long supervisionWithdrawFee = 0L; // 提现手续费
	private Long supervisionFreezeFund = 0L; // 冻结金额
	private Long supervisionUnfreezeFund = 0L; // 解冻金额
	private Long supervisionRefundFund = 0L; // 退款金额
	// 计数
	private Long supervisionTransferInCount = 0L; // 转入次数
	private Long supervisionTransferOutCount = 0L; // 转出次数
	private Long supervisionWithdrawCount = 0L; // 提现次数
	private Long supervisionFreezeCount = 0L; // 冻结次数
	private Long supervisionUnfreezeCount = 0L; // 解冻次数
	private Long supervisionRefundCount = 0L; // 退款次数

	public FundReportState getFundReportState() {
		return fundReportState;
	}

	public void setFundReportState(FundReportState fundReportState) {
		this.fundReportState = fundReportState;
	}

	public Long getTransferFund() {
		return transferFund;
	}

	public void setTransferFund(Long transferFund) {
		this.transferFund = transferFund;
	}

	public Long getWithdrawFund() {
		return withdrawFund;
	}

	public void setWithdrawFund(Long withdrawFund) {
		this.withdrawFund = withdrawFund;
	}

	public Long getWithdrawFee() {
		return withdrawFee;
	}

	public void setWithdrawFee(Long withdrawFee) {
		this.withdrawFee = withdrawFee;
	}

	public Long getFreezeFund() {
		return freezeFund;
	}

	public void setFreezeFund(Long freezeFund) {
		this.freezeFund = freezeFund;
	}

	public Long getUnfreezeFund() {
		return unfreezeFund;
	}

	public void setUnfreezeFund(Long unfreezeFund) {
		this.unfreezeFund = unfreezeFund;
	}

	public Long getRefundFund() {
		return refundFund;
	}

	public void setRefundFund(Long refundFund) {
		this.refundFund = refundFund;
	}

	public Long getTransferInCount() {
		return transferInCount;
	}

	public void setTransferInCount(Long transferInCount) {
		this.transferInCount = transferInCount;
	}

	public Long getTransferOutCount() {
		return transferOutCount;
	}

	public void setTransferOutCount(Long transferOutCount) {
		this.transferOutCount = transferOutCount;
	}

	public Long getWithdrawCount() {
		return withdrawCount;
	}

	public void setWithdrawCount(Long withdrawCount) {
		this.withdrawCount = withdrawCount;
	}

	public Long getFreezeCount() {
		return freezeCount;
	}

	public void setFreezeCount(Long freezeCount) {
		this.freezeCount = freezeCount;
	}

	public Long getUnfreezeCount() {
		return unfreezeCount;
	}

	public void setUnfreezeCount(Long unfreezeCount) {
		this.unfreezeCount = unfreezeCount;
	}

	public Long getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Long refundCount) {
		this.refundCount = refundCount;
	}

	public Long getSupervisionTransferFund() {
		return supervisionTransferFund;
	}

	public void setSupervisionTransferFund(Long supervisionTransferFund) {
		this.supervisionTransferFund = supervisionTransferFund;
	}

	public Long getSupervisionWithdrawFund() {
		return supervisionWithdrawFund;
	}

	public void setSupervisionWithdrawFund(Long supervisionWithdrawFund) {
		this.supervisionWithdrawFund = supervisionWithdrawFund;
	}

	public Long getSupervisionWithdrawFee() {
		return supervisionWithdrawFee;
	}

	public void setSupervisionWithdrawFee(Long supervisionWithdrawFee) {
		this.supervisionWithdrawFee = supervisionWithdrawFee;
	}

	public Long getSupervisionFreezeFund() {
		return supervisionFreezeFund;
	}

	public void setSupervisionFreezeFund(Long supervisionFreezeFund) {
		this.supervisionFreezeFund = supervisionFreezeFund;
	}

	public Long getSupervisionUnfreezeFund() {
		return supervisionUnfreezeFund;
	}

	public void setSupervisionUnfreezeFund(Long supervisionUnfreezeFund) {
		this.supervisionUnfreezeFund = supervisionUnfreezeFund;
	}

	public Long getSupervisionRefundFund() {
		return supervisionRefundFund;
	}

	public void setSupervisionRefundFund(Long supervisionRefundFund) {
		this.supervisionRefundFund = supervisionRefundFund;
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

	public Long getSupervisionFreezeCount() {
		return supervisionFreezeCount;
	}

	public void setSupervisionFreezeCount(Long supervisionFreezeCount) {
		this.supervisionFreezeCount = supervisionFreezeCount;
	}

	public Long getSupervisionUnfreezeCount() {
		return supervisionUnfreezeCount;
	}

	public void setSupervisionUnfreezeCount(Long supervisionUnfreezeCount) {
		this.supervisionUnfreezeCount = supervisionUnfreezeCount;
	}

	public Long getSupervisionRefundCount() {
		return supervisionRefundCount;
	}

	public void setSupervisionRefundCount(Long supervisionRefundCount) {
		this.supervisionRefundCount = supervisionRefundCount;
	}

	public void update(AccountTradeType tradeType, AccountSupervisionStatus superStatus, String amount,
			String operateType) {
		switch (tradeType) {
		case FREEZE:
			freeze(superStatus, amount);
			break;
		case TRANSFER_FUND:
			transfer(superStatus, amount);
			break;
		case UNFREEZE:
			unfreeze(superStatus, amount);
			break;
		case WITHDRAW_FUND:
			withdraw(tradeType, superStatus, amount, operateType);
			break;
		default:
			break;
		}

	}

	private void freeze(AccountSupervisionStatus superStatus, String amount) {
		this.freezeFund += MoneyUtil.yuan2Cent(amount, 0L);
		this.freezeCount++;
		if (AccountSupervisionStatus.HAVE_SUPERVISION.equals(superStatus)) {
			this.supervisionFreezeFund += MoneyUtil.yuan2Cent(amount, 0L);
			this.supervisionFreezeCount++;
		}
	}

	private void transfer(AccountSupervisionStatus superStatus, String amount) {
		Long tFund = MoneyUtil.yuan2Cent(amount, 0L);
		this.transferFund += tFund;
		if (tFund < 0) {
			this.transferOutCount++;
		} else {
			this.transferInCount++;// 0视为转入，平台是不允许0转账的
		}
		if (AccountSupervisionStatus.HAVE_SUPERVISION.equals(superStatus)) {
			this.supervisionTransferFund += tFund;
			if (tFund < 0) {
				this.supervisionTransferOutCount++;
			} else {
				this.supervisionTransferInCount++;// 0视为转入，平台是不允许0转账的
			}
		}
	}

	private void unfreeze(AccountSupervisionStatus superStatus, String amount) {
		this.unfreezeFund += MoneyUtil.yuan2Cent(amount, 0L);
		this.unfreezeCount++;
		if (AccountSupervisionStatus.HAVE_SUPERVISION.equals(superStatus)) {
			this.supervisionUnfreezeFund += MoneyUtil.yuan2Cent(amount, 0L);
			this.supervisionUnfreezeCount++;
		}
	}

	private void withdraw(AccountTradeType tradeType, AccountSupervisionStatus superStatus, String amount,
			String operateType) {
		Long wFund = MoneyUtil.yuan2Cent(amount, 0L);
		if (!"手续费".equals(operateType)) {// TODO 账户这里需要优化以下,好方便区分各种手续费
			this.withdrawCount++;
			this.withdrawFund += wFund;
		} else if ("手续费".equals(operateType)) {
			this.withdrawFee += wFund;
		}
		if (AccountSupervisionStatus.HAVE_SUPERVISION.equals(superStatus)) {
			if (!"手续费".equals(operateType)) {// TODO 账户这里需要优化以下,好方便区分各种手续费
				this.supervisionWithdrawCount++;
				this.supervisionWithdrawFund += wFund;
			} else if ("手续费".equals(operateType)) {
				this.supervisionWithdrawFee += wFund;
			}
		}
	}
}
