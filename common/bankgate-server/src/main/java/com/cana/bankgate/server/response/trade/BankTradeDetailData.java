/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.trade;

import java.io.Serializable;

import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.BankTradeType;
import com.cana.vbam.common.bankgate.enums.DebitCreditTag;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * 
 * @author XuMeng
 *
 */
@XStreamAlias("row")
public class BankTradeDetailData implements Serializable {

	private static final long serialVersionUID = 846688312750031717L;
	@XStreamAlias("subAccNo")
	private String accountNo; // 附属账号

	@XStreamAlias("tranType")
	@XStreamConverter(BankEnumConverter.class)
	private BankTradeType bankTradeType; // 交易类型varchar(2)，值域参见附录4.8

	@XStreamAlias("tranDate")
	private String tradeDate; // 交易日期char(8)

	@XStreamAlias("tranTime")
	private String tradeTime; // 交易时间char(6)

	@XStreamAlias("tellerNo")
	private String operatorNo; // 柜员交易号varchar(14)

	@XStreamAlias("tranSeqNo")
	private String tradeSeq; // 交易序号varchar(13)

	@XStreamAlias("accountNo")
	private String oppositeAccountNo; // 对方账号varchar(19)

	@XStreamAlias("accountNm")
	private String oppositeAccountName; // 对方账户名称varchar(60)

	@XStreamAlias("accBnkNm")
	private String oppositeBankName; // 对方开户行名称varchar(60)

	@XStreamAlias("loanFlag")
	@XStreamConverter(BankEnumConverter.class)
	private DebitCreditTag debitCreditTag;// 借贷标志varchar (1) D：借，C：贷

	@XStreamAlias("tranAmt")
	@XStreamConverter(BankAmountConverter.class)
	private Long amount; // 交易金额decimal(15,2)

	@XStreamAlias("accBalAmt")
	@XStreamConverter(BankAmountConverter.class)
	private Long accountBalance; // 账户余额decimal(15,2)

	@XStreamAlias("pdgAmt")
	@XStreamConverter(BankAmountConverter.class)
	private Long fee; // 手续费金额decimal(15,2)

	@XStreamAlias("memo")
	private String memo; // 摘要varchar(102)

	@XStreamAlias("verifyCode")
	private String verifyCode; // 打印校验码 varchar(20)

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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getTradeSeq() {
		return tradeSeq;
	}

	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
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
