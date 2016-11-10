package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;

public class BankAccountSignUpStateDataDTO implements Serializable{

	private static final long serialVersionUID = 269935415776706060L;
	
	/**
	 * 附属账号
	 */
	private String accountNo;
	
	/**
	 * 附属账号名称
	 */
	private String accountName;
	
	/**
	 * 附属账号类型
	 */
	private String accountType;
	
	/**
	 * 状态
	 */
	private String accountStatus;
	
	/**
	 * 是否计算利息标示
	 */
	private String calInterestFlag;
	
	/**
	 * 计息利率
	 */
	private String interestRate;
	
	/**
	 * 手续费收取方式
	 */
	private String feeType;
	
	/**
	 * 是否允许透支
	 */
	private String overFlag;
	
	/**
	 * 透支额度
	 */
	private String overAmount;
	
	/**
	 * 透支利率
	 */
	private String overRate;
	
	/**
	 * 自动分配利息标示
	 */
	private String autoAssignInterestFlag;
	
	/**
	 * 自动分配手续费标示
	 */
	private String autoAssignTranFeeFlag;
	
	/**
	 * 会员签约中心
	 */
	private String centerNo;
	
	/**
	 * 会员签约中心名称
	 */
	private String centerName;
	
	/**
	 * 实名制更换
	 */
	private String realNameParm;
	
	/**
	 * 附属账户凭证打印更换
	 */
	private String subAccPrintParm;
	
	/**
	 * 账户状态
	 */
	private String statusText;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCalInterestFlag() {
		return calInterestFlag;
	}

	public void setCalInterestFlag(String calInterestFlag) {
		this.calInterestFlag = calInterestFlag;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getOverFlag() {
		return overFlag;
	}

	public void setOverFlag(String overFlag) {
		this.overFlag = overFlag;
	}

	public String getOverAmount() {
		return overAmount;
	}

	public void setOverAmount(String overAmount) {
		this.overAmount = overAmount;
	}

	public String getOverRate() {
		return overRate;
	}

	public void setOverRate(String overRate) {
		this.overRate = overRate;
	}

	public String getAutoAssignInterestFlag() {
		return autoAssignInterestFlag;
	}

	public void setAutoAssignInterestFlag(String autoAssignInterestFlag) {
		this.autoAssignInterestFlag = autoAssignInterestFlag;
	}

	public String getAutoAssignTranFeeFlag() {
		return autoAssignTranFeeFlag;
	}

	public void setAutoAssignTranFeeFlag(String autoAssignTranFeeFlag) {
		this.autoAssignTranFeeFlag = autoAssignTranFeeFlag;
	}

	public String getCenterNo() {
		return centerNo;
	}

	public void setCenterNo(String centerNo) {
		this.centerNo = centerNo;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getRealNameParm() {
		return realNameParm;
	}

	public void setRealNameParm(String realNameParm) {
		this.realNameParm = realNameParm;
	}

	public String getSubAccPrintParm() {
		return subAccPrintParm;
	}

	public void setSubAccPrintParm(String subAccPrintParm) {
		this.subAccPrintParm = subAccPrintParm;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
}
