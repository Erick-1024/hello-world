/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;

import com.cana.vbam.common.bankgate.enums.BankTradeType;
import com.cana.vbam.common.bankgate.enums.DebitCreditTag;

/**
 * @author ducer
 *
 */
public class BankAccountTradeFlowDataDTO implements Serializable, Comparable<BankAccountTradeFlowDataDTO> {

  private static final long serialVersionUID = 2269005104485629039L;

  private String account; // 附属账号
  private BankTradeType bankTradeType; // 交易类型varchar(2)，值域参见附录4.8
  private String tradeDate; // 交易日期char(8)
  private String tradeTime; // 交易时间char(6)
  private String operatorNo; // 柜员交易号varchar(14)
  private String tradeSeq; // 交易序号varchar(13)
  private String oppositeAccountNo; // 对方账号varchar(19)
  private String oppositeAccountName; // 对方账户名称varchar(60)
  private String oppositeBankName; // 对方开户行名称varchar(60)
  private String oppositeBankNo; // 对方支付联行号char(14)
  private DebitCreditTag debitCreditTag;// 借贷标志varchar (1) D：借，C：贷
  private Long amount; // 交易金额decimal(15,2)
  private Long accountBalance; // 账户余额decimal(15,2)
  private Long fee; // 手续费金额decimal(15,2)
  private String memo; // 摘要varchar(22)
  
  public String getAccount() {
    return account;
  }
  public void setAccount(String account) {
    this.account = account;
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
  public String getOppositeBankNo() {
    return oppositeBankNo;
  }
  public void setOppositeBankNo(String oppositeBankNo) {
    this.oppositeBankNo = oppositeBankNo;
  }
  @Override
  public int compareTo(BankAccountTradeFlowDataDTO o) {
	int date = this.tradeDate.compareTo(o.tradeDate);
	int time = this.tradeTime.compareTo(o.tradeTime);
	return date == 0? time : date;
  }
}
