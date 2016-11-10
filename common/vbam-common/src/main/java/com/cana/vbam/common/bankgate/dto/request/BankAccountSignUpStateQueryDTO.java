package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

public class BankAccountSignUpStateQueryDTO implements Serializable {

	private static final long serialVersionUID = -2500987837972979523L;

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
	private String bankUserName;
	
	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;

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

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
