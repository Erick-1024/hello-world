/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.report.dto;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class CanaReportAccountFundCountDTO implements Serializable, Comparable<CanaReportAccountFundCountDTO> {

	private static final long serialVersionUID = -467897688524408761L;

	/**
	 * 日报表日期（格式：yyyy-MM-dd，不存在当天报表，当天报表在0点查询统计生成）
	 */
	private String reportDate;

	/**
	 * 入金笔数
	 */
	private Long depositCount;

	/**
	 * 转入笔数
	 */
	private Long transferInCount;

	/**
	 * 转出笔数
	 */
	private Long transferOutCount;

	/**
	 * 提现笔数
	 */
	private Long withdrawCount;

	/**
	 * 退款笔数，退款行为由平台交易记录判定
	 */
	private Long refundCount;

	/**
	 * 日报表日期（格式：yyyy-MM-dd，不存在当天报表，当天报表在0点查询统计生成）
	 */
	public String getReportDate() {
		return reportDate;
	}

	/**
	 * 日报表日期（格式：yyyy-MM-dd，不存在当天报表，当天报表在0点查询统计生成）
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate == null ? null : reportDate.trim();
	}

	public Long getDepositCount() {
		return depositCount;
	}

	public void setDepositCount(Long depositCount) {
		this.depositCount = depositCount;
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

	public Long getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Long refundCount) {
		this.refundCount = refundCount;
	}

	@Override
	public int compareTo(CanaReportAccountFundCountDTO o) {
		return -this.reportDate.compareTo(o.getReportDate());
	}
}
