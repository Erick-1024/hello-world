package com.cana.vbam.common.account.dto;

import java.io.Serializable;

import com.cana.vbam.common.account.enums.AccountTradeStatus;

/**
 * 账户交易基本信息
 */
public class AccountTradeRecordBasicInfo implements Serializable {

	private static final long serialVersionUID = 1903747100270261729L;

	private String businessSeq;
	private String accountName;
	private String accountNo;
	private String oppositeAccountName;
	private String oppositeAccountNo;
	private Long amount; // 交易金额
	private AccountTradeStatus status;

	public String getBusinessSeq() {
		return businessSeq;
	}

	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
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

	public String getOppositeAccountName() {
		return oppositeAccountName;
	}

	public void setOppositeAccountName(String oppositeAccountName) {
		this.oppositeAccountName = oppositeAccountName;
	}

	public String getOppositeAccountNo() {
		return oppositeAccountNo;
	}

	public void setOppositeAccountNo(String oppositeAccountNo) {
		this.oppositeAccountNo = oppositeAccountNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public AccountTradeStatus getStatus() {
		return status;
	}

	public void setStatus(AccountTradeStatus status) {
		this.status = status;
	}

}
