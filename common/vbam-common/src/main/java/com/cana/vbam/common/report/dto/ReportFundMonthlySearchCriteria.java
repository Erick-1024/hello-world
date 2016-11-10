package com.cana.vbam.common.report.dto;

import java.io.Serializable;

import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.report.enums.FundBalanceGetState;

public class ReportFundMonthlySearchCriteria implements Serializable{

	private static final long serialVersionUID = -4236693574325170012L;

	// 银行账号
	private String accountNo;
	
	// 融资客户
	private String financeName;
	
	// 账户名称
	private String accountName;
	
	// 保理商名称
	private String factorName;
	
	// 账户类型
	private AccountType accountType;
	
	// 监管状态
	private AccountSupervisionStatus supervisionStatus;
	
	// 是否是回款账户
	private Boolean transferInAccount;
	
	// 账户状态
	private AccountStatus accountStatus;
	
	// 余额获取状态
	private FundBalanceGetState fundBalanceGetState;
	
	// 报表年份
	private String reportDate;
	
	private int page = 1;
	
	private int pageSize;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public AccountSupervisionStatus getSupervisionStatus() {
		return supervisionStatus;
	}

	public void setSupervisionStatus(AccountSupervisionStatus supervisionStatus) {
		this.supervisionStatus = supervisionStatus;
	}

	public Boolean getTransferInAccount() {
		return transferInAccount;
	}

	public void setTransferInAccount(Boolean transferInAccount) {
		this.transferInAccount = transferInAccount;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public FundBalanceGetState getFundBalanceGetState() {
		return fundBalanceGetState;
	}

	public void setFundBalanceGetState(FundBalanceGetState fundBalanceGetState) {
		this.fundBalanceGetState = fundBalanceGetState;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
