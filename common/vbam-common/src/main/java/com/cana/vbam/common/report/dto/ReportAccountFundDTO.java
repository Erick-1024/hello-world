/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.report.dto;

import java.io.Serializable;

/**
 * API接口统一用此DTO，调用端再重新转化为父类屏蔽部分字段
 * 
 * @author ducer
 *
 */
public class ReportAccountFundDTO implements Serializable, Comparable<ReportAccountFundDTO> {

	private static final long serialVersionUID = -4547025700859166607L;

	/**
	 * 年报表日期(格式：yyyy，存在当年报表，年报表是由日报表统计生成)
	 */
	private String reportDate;

	/**
	 * 上一年账户余额为上年最后一日的账户余额
	 */
	private String lastBalance;

	/**
	 * 上一年自己的监管状态账户的余额，为上年最后一日自己的监管状态账户余额
	 */
	private String lastOwnSupervisionBalance;

	/**
	 * 上一年监管的别人账户的余额，为上年最后一日监管的别人账户的余额
	 */
	private String lastOtherSupervisionBalance;

	/**
	 * 年入金额为当年日报表入金额累加值
	 */
	private String depositFund;

	/**
	 * 自己的监管状态账户的入金金额，当年日报累加值
	 */
	private String supervisionDepositFund;

	/**
	 * 年转帐额为当年日报表转帐额累加值,负值转出，正值转入
	 */
	private String transferFund;

	/**
	 * 自己的监管状态账户的转帐金额，负值转出，正值转入，当年日报累加值
	 */
	private String supervisionTransferFund;

	/**
	 * 年提现额为当年日报表提现额累加值
	 */
	private String withdrawFund;

	/**
	 * 自己的监管状态账户的提现金额，当年日报累加值
	 */
	private String supervisionWithdrawFund;

	/**
	 * 提现手续费为当年日报表提现手续费累加值
	 */
	private String withdrawFee;

	/**
	 * 自己的监管状态账户的提现手续费，当年日报累加值
	 */
	private String supervisionWithdrawFee;

	/**
	 * 年退款额为当年日报表退款额累加值，退款行为由平台交易记录判定
	 */
	private String refundFund;

	/**
	 * 自己的监管状态账户的退款金额，当年日报累加值
	 */
	private String supervisionRefundFund;

	/**
	 * 当日冻结金额
	 */
	private String freezeFund;

	/**
	 * 自己的监管状态账户的冻结金额
	 */
	private String supervisionFreezeFund;

	/**
	 * 当日解冻金额
	 */
	private String unfreezeFund;

	/**
	 * 自己的监管状态账户的解冻金额
	 */
	private String supervisionUnfreezeFund;

	/**
	 * 本年余额为本年最后一日或当前日的账户余额
	 */
	private String currentBalance;

	/**
	 * 本年自己的监管状态账户的余额，为本年最后一日自己的监管状态账户余额
	 */
	private String currentOwnSupervisionBalance;

	/**
	 * 本年监管的别人账户的余额，为本年最后一日监管的别人账户的余额
	 */
	private String currentOtherSupervisionBalance;

	/**
	 * 年报表日期(格式：yyyy，存在当年报表，年报表是由日报表统计生成)
	 */
	public String getReportDate() {
		return reportDate;
	}

	/**
	 * 年报表日期(格式：yyyy，存在当年报表，年报表是由日报表统计生成)
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate == null ? null : reportDate.trim();
	}

	public String getLastBalance() {
		return lastBalance;
	}

	public void setLastBalance(String lastBalance) {
		this.lastBalance = lastBalance;
	}

	public String getLastOwnSupervisionBalance() {
		return lastOwnSupervisionBalance;
	}

	public void setLastOwnSupervisionBalance(String lastOwnSupervisionBalance) {
		this.lastOwnSupervisionBalance = lastOwnSupervisionBalance;
	}

	public String getLastOtherSupervisionBalance() {
		return lastOtherSupervisionBalance;
	}

	public void setLastOtherSupervisionBalance(String lastOtherSupervisionBalance) {
		this.lastOtherSupervisionBalance = lastOtherSupervisionBalance;
	}

	public String getDepositFund() {
		return depositFund;
	}

	public void setDepositFund(String depositFund) {
		this.depositFund = depositFund;
	}

	public String getSupervisionDepositFund() {
		return supervisionDepositFund;
	}

	public void setSupervisionDepositFund(String supervisionDepositFund) {
		this.supervisionDepositFund = supervisionDepositFund;
	}

	public String getTransferFund() {
		return transferFund;
	}

	public void setTransferFund(String transferFund) {
		this.transferFund = transferFund;
	}

	public String getSupervisionTransferFund() {
		return supervisionTransferFund;
	}

	public void setSupervisionTransferFund(String supervisionTransferFund) {
		this.supervisionTransferFund = supervisionTransferFund;
	}

	public String getWithdrawFund() {
		return withdrawFund;
	}

	public void setWithdrawFund(String withdrawFund) {
		this.withdrawFund = withdrawFund;
	}

	public String getSupervisionWithdrawFund() {
		return supervisionWithdrawFund;
	}

	public void setSupervisionWithdrawFund(String supervisionWithdrawFund) {
		this.supervisionWithdrawFund = supervisionWithdrawFund;
	}

	public String getWithdrawFee() {
		return withdrawFee;
	}

	public void setWithdrawFee(String withdrawFee) {
		this.withdrawFee = withdrawFee;
	}

	public String getSupervisionWithdrawFee() {
		return supervisionWithdrawFee;
	}

	public void setSupervisionWithdrawFee(String supervisionWithdrawFee) {
		this.supervisionWithdrawFee = supervisionWithdrawFee;
	}

	public String getRefundFund() {
		return refundFund;
	}

	public void setRefundFund(String refundFund) {
		this.refundFund = refundFund;
	}

	public String getSupervisionRefundFund() {
		return supervisionRefundFund;
	}

	public void setSupervisionRefundFund(String supervisionRefundFund) {
		this.supervisionRefundFund = supervisionRefundFund;
	}

	public String getFreezeFund() {
		return freezeFund;
	}

	public void setFreezeFund(String freezeFund) {
		this.freezeFund = freezeFund;
	}

	public String getSupervisionFreezeFund() {
		return supervisionFreezeFund;
	}

	public void setSupervisionFreezeFund(String supervisionFreezeFund) {
		this.supervisionFreezeFund = supervisionFreezeFund;
	}

	public String getUnfreezeFund() {
		return unfreezeFund;
	}

	public void setUnfreezeFund(String unfreezeFund) {
		this.unfreezeFund = unfreezeFund;
	}

	public String getSupervisionUnfreezeFund() {
		return supervisionUnfreezeFund;
	}

	public void setSupervisionUnfreezeFund(String supervisionUnfreezeFund) {
		this.supervisionUnfreezeFund = supervisionUnfreezeFund;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getCurrentOwnSupervisionBalance() {
		return currentOwnSupervisionBalance;
	}

	public void setCurrentOwnSupervisionBalance(String currentOwnSupervisionBalance) {
		this.currentOwnSupervisionBalance = currentOwnSupervisionBalance;
	}

	public String getCurrentOtherSupervisionBalance() {
		return currentOtherSupervisionBalance;
	}

	public void setCurrentOtherSupervisionBalance(String currentOtherSupervisionBalance) {
		this.currentOtherSupervisionBalance = currentOtherSupervisionBalance;
	}

	@Override
	public int compareTo(ReportAccountFundDTO o) {
		return -this.reportDate.compareTo(o.getReportDate());
	}
}