package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class ReportFundMonthlyResultDTO implements Serializable{

	private static final long serialVersionUID = 1030787511044527160L;

	// 余额月份
    private String month;

    // 保理商名称
    private String factorName;

    // 融资客户名称
    private String financeName;

    // 账户名称
    private String accountName;

    // 银行账号
    private String accountNo;

    // 账户类型
    private String accountType;

    // 监管类型
    private String supervisionStatus;

    // 回款账户
    private String isTransferInAccount;

    // 账户状态
    private String accountStatus;

    // 当月账户余额
    private String balance;

    // 获取结果
    private String result;

    // 更新时间
    private String updateTime;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getFinanceName() {
		return financeName;
	}

	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getSupervisionStatus() {
		return supervisionStatus;
	}

	public void setSupervisionStatus(String supervisionStatus) {
		this.supervisionStatus = supervisionStatus;
	}

	public String getIsTransferInAccount() {
		return isTransferInAccount;
	}

	public void setIsTransferInAccount(String isTransferInAccount) {
		this.isTransferInAccount = isTransferInAccount;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
