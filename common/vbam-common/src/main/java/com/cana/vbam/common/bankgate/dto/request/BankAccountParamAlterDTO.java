package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

public class BankAccountParamAlterDTO implements Serializable {

	private static final long serialVersionUID = -7423789525708097377L;

	/**
	 * 银行主账号，如果为空，则默认使用平台的主账号
	 */
	private String mainAccountNo;

	/**
	 * 账号
	 */
	private String accountNo;

	/**
	 * 银行主账号用户名，如果为空，则默认使用平台的用户名
	 */
	private String accountName;

	/**
	 * 自动分配利息标示
	 */
	private String autoAssignInterestFlag;

	/**
	 * 计算利息标志
	 */
	private String calculateInterestFlag;

	/**
	 * 自动分配转账手续费标
	 */
	private String autoAssignTranFeeFlag;

	/**
	 * 手续费收取方式，0：不收取；1：实时收取；2：月末累计；appFlag为3时，必须为0
	 */
	private String feeType;
	
	/**
	 * 默认计息率
	 */
	private String interestRate;
	

	public String getMainAccountNo() {
		return mainAccountNo;
	}

	public void setMainAccountNo(String mainAccountNo) {
		this.mainAccountNo = mainAccountNo;
	}

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

	public String getAutoAssignInterestFlag() {
		return autoAssignInterestFlag;
	}

	public void setAutoAssignInterestFlag(String autoAssignInterestFlag) {
		this.autoAssignInterestFlag = autoAssignInterestFlag;
	}

	public String getCalculateInterestFlag() {
		return calculateInterestFlag;
	}

	public void setCalculateInterestFlag(String calculateInterestFlag) {
		this.calculateInterestFlag = calculateInterestFlag;
	}

	public String getAutoAssignTranFeeFlag() {
		return autoAssignTranFeeFlag;
	}

	public void setAutoAssignTranFeeFlag(String autoAssignTranFeeFlag) {
		this.autoAssignTranFeeFlag = autoAssignTranFeeFlag;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
}
