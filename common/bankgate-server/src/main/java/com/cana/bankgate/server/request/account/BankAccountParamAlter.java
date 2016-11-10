package com.cana.bankgate.server.request.account;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("stream")
public class BankAccountParamAlter extends BankBaseRequest{
	
	private static final long serialVersionUID = 1272503837319708145L;
	
	@XStreamAlias("userName")
	private String bankUserName;

	@XStreamAlias("mainAccNo")
	private String mainAccountNo;

	@XStreamAlias("subAccNo")
	private String accountNo;

	@XStreamAlias("subAccNm")
	private String accountName;
	
	@XStreamAlias("autoAssignInterestFlag")
	private String autoAssignInterestFlag;

	@XStreamAlias("calInterestFlag")
	private String calculateInterestFlag;

	@XStreamAlias("autoAssignTranFeeFlag")
	private String autoAssignTranFeeFlag;

	@XStreamAlias("feeType")
	private String feeType;
	
	@XStreamAlias("interestRate")
	private String interestRate;
	
	public BankAccountParamAlter(BankBizType bankBizType) {
		super(bankBizType);
	}

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

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}
}
