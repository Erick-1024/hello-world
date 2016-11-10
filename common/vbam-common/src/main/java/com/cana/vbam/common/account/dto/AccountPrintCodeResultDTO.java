package com.cana.vbam.common.account.dto;

import java.io.Serializable;

import com.cana.vbam.common.bankgate.enums.BankTradeType;
import com.cana.vbam.common.bankgate.enums.DebitCreditTag;

public class AccountPrintCodeResultDTO implements Serializable {
	private static final long serialVersionUID = 3555245518127288773L;

	private String accountNo; // 附属账号varchar(19)
	private BankTradeType bankTradeType; // 交易类型varchar(2)，值域参见附录4.8;
	private String bankTradeTypeDesc;
	private String tranDate; // 交易日期 char(8) 格式YYYY-MM-DD HH:MM:SS
	private String oppositeAccountNo; // 对方账号 varchar(19)
	private String oppositeAccountName; // 对方账户名称 varchar(122)
	private String oppositeBankName; // 对方开户行名称 varchar(122)
	private DebitCreditTag debitCreditTag; // 借贷标识 "D":借,"C":贷 varchar(1)
	private String debitCreditTagDesc;
	private Long amount; // 交易金额 decimal(15,2)
	private Long accountBalance; // 账户余额 decimal(15,2)
	private Long fee; // 手续费金额 decimal(15,2)
	private String memo; // 摘要 varchar(102)
	private String verifyCode; // 打印校验码

	public String getBankTradeTypeDesc() {
		return bankTradeTypeDesc;
	}

	public void setBankTradeTypeDesc(String bankTradeTypeDesc) {
		this.bankTradeTypeDesc = bankTradeTypeDesc;
	}

	public String getDebitCreditTagDesc() {
		return debitCreditTagDesc;
	}

	public void setDebitCreditTagDesc(String debitCreditTagDesc) {
		this.debitCreditTagDesc = debitCreditTagDesc;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BankTradeType getBankTradeType() {
		return bankTradeType;
	}

	public void setBankTradeType(BankTradeType bankTradeType) {
		this.bankTradeType = bankTradeType;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getOppositeAccountNo() {
		return oppositeAccountNo;
	}

	public void setOppositeAccountNo(String oppositeAccountNo) {
		this.oppositeAccountNo = oppositeAccountNo;
	}

	public String getOppositeAccountName() {
		return oppositeAccountName;
	}

	public void setOppositeAccountName(String oppositeAccountName) {
		this.oppositeAccountName = oppositeAccountName;
	}

	public String getOppositeBankName() {
		return oppositeBankName;
	}

	public void setOppositeBankName(String oppositeBankName) {
		this.oppositeBankName = oppositeBankName;
	}

	public DebitCreditTag getDebitCreditTag() {
		return debitCreditTag;
	}

	public void setDebitCreditTag(DebitCreditTag debitCreditTag) {
		this.debitCreditTag = debitCreditTag;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
