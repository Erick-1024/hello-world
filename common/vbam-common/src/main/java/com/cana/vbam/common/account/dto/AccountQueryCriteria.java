package com.cana.vbam.common.account.dto;

import java.io.Serializable;

import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;

public class AccountQueryCriteria implements Serializable {
	private static final long serialVersionUID = 8952777816236684458L;

	private int page = 1; // 页码
	private int pageSize = 10; // 每页显示行数
	private String accountName; // 账户名称
	private String accountNo; // 银行账号
	private String factorName; // 保理商
	private String finaceName; // 融资商
	private String coreCompanyName; // 核心企业
	private AccountStatus accountStatus; // 账号状态
	private AccountType accountType; // 账户类型
	private AccountAccumulationStatus accumulationStatus; // 归集状态
	private AccountSupervisionStatus supervisoryStatus; // 监管状态
	private Boolean isTransferInAccount; // 是否是保理商的回款账户，为空时表示不限

	private String companyId;// 账户所属者的企业Id,(用于CANA查看“我的账户”,核心企业只能看到自己的账户，而不能看到其他的核心企业)

	// 以下字段是account－dao生成sql时需要的，
	// 前端调account－api接口时不需要传
	private String factorId; // 保理商
	private String finaceId; // 融资商

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

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getFinaceName() {
		return finaceName;
	}

	public void setFinaceName(String finaceName) {
		this.finaceName = finaceName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public AccountAccumulationStatus getAccumulationStatus() {
		return accumulationStatus;
	}

	public void setAccumulationStatus(AccountAccumulationStatus accumulationStatus) {
		this.accumulationStatus = accumulationStatus;
	}

	public AccountSupervisionStatus getSupervisoryStatus() {
		return supervisoryStatus;
	}

	public void setSupervisoryStatus(AccountSupervisionStatus supervisoryStatus) {
		this.supervisoryStatus = supervisoryStatus;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFinaceId() {
		return finaceId;
	}

	public void setFinaceId(String finaceId) {
		this.finaceId = finaceId;
	}

	public Boolean getIsTransferInAccount() {
		return isTransferInAccount;
	}

	public void setIsTransferInAccount(Boolean isTransferInAccount) {
		this.isTransferInAccount = isTransferInAccount;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
