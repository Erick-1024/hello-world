/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.account.dto;

import java.io.Serializable;

import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;

/**
 * 传给前端
 * 
 * @author ducer
 *
 */
public class AccountFundInfoDTO implements Serializable {
	
	private static final long serialVersionUID = -226069405699062263L;

	private String accountNo;//转出账号
	private String accountName;//转出户名
	private AccountType accountType;//账户类型
	private AccountSupervisionStatus accountSupervisionStatus;//账户监管状态
	private AccountAccumulationStatus accountAccumulationStatus;//账户归集状态
	private String accountSupervisionId;//当前监管关系Id，对应account_supervision表
	private String accountBalance;//账户余额，Note:需要转化为元为单位
	private String financeBalance;//融资余额，Note:需要转化为元为单位
	private String fundCoverRatio;//资金覆盖率，Note:百分比为单位，没有小数
	private String commissionCharge;//手续费，Note:需要转化为元为单位
	
	//页面专用 
	private String accountTypeDesc;//账户类型Desc
	private String accountSupervisionStatusDesc;//账户监管状态Desc
	private String accountAccumulationStatusDesc;//账户归集状态Desc
	
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
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getFinanceBalance() {
		return financeBalance;
	}
	public void setFinanceBalance(String financeBalance) {
		this.financeBalance = financeBalance;
	}
	public String getFundCoverRatio() {
		return fundCoverRatio;
	}
	public void setFundCoverRatio(String fundCoverRatio) {
		this.fundCoverRatio = fundCoverRatio;
	}
	public String getCommissionCharge() {
		return commissionCharge;
	}
	public void setCommissionCharge(String commissionCharge) {
		this.commissionCharge = commissionCharge;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public AccountSupervisionStatus getAccountSupervisionStatus() {
		return accountSupervisionStatus;
	}
	public void setAccountSupervisionStatus(AccountSupervisionStatus accountSupervisionStatus) {
		this.accountSupervisionStatus = accountSupervisionStatus;
	}
	public AccountAccumulationStatus getAccountAccumulationStatus() {
		return accountAccumulationStatus;
	}
	public void setAccountAccumulationStatus(AccountAccumulationStatus accountAccumulationStatus) {
		this.accountAccumulationStatus = accountAccumulationStatus;
	}
	public String getAccountTypeDesc() {
		return accountTypeDesc;
	}
	public void setAccountTypeDesc(String accountTypeDesc) {
		this.accountTypeDesc = accountTypeDesc;
	}
	public String getAccountSupervisionStatusDesc() {
		return accountSupervisionStatusDesc;
	}
	public void setAccountSupervisionStatusDesc(String accountSupervisionStatusDesc) {
		this.accountSupervisionStatusDesc = accountSupervisionStatusDesc;
	}
	public String getAccountAccumulationStatusDesc() {
		return accountAccumulationStatusDesc;
	}
	public void setAccountAccumulationStatusDesc(String accountAccumulationStatusDesc) {
		this.accountAccumulationStatusDesc = accountAccumulationStatusDesc;
	}
	public String getAccountSupervisionId() {
		return accountSupervisionId;
	}
	public void setAccountSupervisionId(String accountSupervisionId) {
		this.accountSupervisionId = accountSupervisionId;
	}
}
