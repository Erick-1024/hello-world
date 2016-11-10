package com.cana.bankgate.server.response.account;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("row")
public class BankAccountSignUpStateData implements Serializable{

	private static final long serialVersionUID = -5245476937591169475L;

	@XStreamAlias("subAccNo")
	private String accountNo; // 附属账号
	
	@XStreamAlias("subAccNm")
	private String accountName; // 附属账号名称
	
	@XStreamAlias("accType")
	private String accountType; // 附属账号类型
	
	@XStreamAlias("status")
	private String accountStatus; // 状态
	
	@XStreamAlias("calInterestFlag")
	private String calInterestFlag; // 是否计算利息标示
	
	@XStreamAlias("interestRate")
	private String interestRate; // 计息利率
	
	@XStreamAlias("feeType")
	private String feeType; // 手续费收取方式
	
	@XStreamAlias("overFlag")
	private String overFlag; //是否允许透支
	
	@XStreamAlias("overAmt")
	private String overAmount; // 透支额度
	
	@XStreamAlias("overRate")
	private String overRate; // 透支利率
	
	@XStreamAlias("autoAssignInterestFlag")
	private String autoAssignInterestFlag; //自动分配利息标示
	
	@XStreamAlias("autoAssignTranFeeFlag")
	private String autoAssignTranFeeFlag; // 自动分配手续费标示
	
	@XStreamAlias("centerNo")
	private String centerNo; // 会员签约中心
	
	@XStreamAlias("centerNm")
	private String centerName; // 会员签约中心名称
	
	@XStreamAlias("realNameParm")
	private String realNameParm; // 实名制更换
	
	@XStreamAlias("subAccPrintParm")
	private String subAccPrintParm; // 附属账户凭证打印更换
	
	@XStreamAlias("statusText")
	private String statusText; //账户状态

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
