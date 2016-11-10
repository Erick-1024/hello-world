/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.flow;

import java.io.Serializable;

import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.CashTransferFlag;
import com.cana.vbam.common.bankgate.enums.DebitCreditTag;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("row")
public class BankMainAccountTradeFlowData implements Serializable {

  private static final long serialVersionUID = 4164513815284496529L;

  @XStreamAlias("tranDate")
  private String tradeDate;//交易日期 char(8) 格式YYYYMMDD
  
  @XStreamAlias("tranTime")
  private String tradeTime;//交易时间 char(6) 格式hhmmss
  
  @XStreamAlias("tranNo")
  private String tradeNo;//柜员交易号 char(14)
  
  @XStreamAlias("sumTranNo")
  private String sumTradeNo;//总交易流水号 char(13)
  
  @XStreamAlias("tranAmount")
  @XStreamConverter(BankAmountConverter.class)
  private Long tradeAmount;//交易金额 decimal(15,2)
  
  @XStreamAlias("creditDebitFlag")
  @XStreamConverter(BankEnumConverter.class)
  private DebitCreditTag debitCreditTag;//借贷标识 借：D，贷：C char(1)
  
  @XStreamAlias("e3rtDate")
  private String reexchangeDate;//退汇日期 char(8)，格式YYYYMMDD
  
  @XStreamAlias("e3rtFlag")
  private String reexchangeFlag;//退汇标志 char(1)，0：退汇；1：非退汇
  
  @XStreamAlias("oppAccountNo")
  private String oppositeAccountNo;//对方账号 varchar(32)
  
  @XStreamAlias("oppAccountName")
  private String oppositeAccountName;//对方账户名称 varchar(60)
  
  @XStreamAlias("oppOpenBankName")
  private String oppositeBankName;//对方开户行名 varchar(62)
  
  @XStreamAlias("abstract")
  private String memo;//摘要 varchar(22)
  
  @XStreamAlias("cashTransferFlag")
  @XStreamConverter(BankEnumConverter.class)
  private CashTransferFlag cashTransferFlag;//现转标识 0：现金；1：转帐 char(1)
  
  @XStreamAlias("opId")
  private String operatorNo;//网银制单员 char(20)
  
  @XStreamAlias("opName")
  private String operatorName;//制单员姓名 varchar(20)
  
  @XStreamAlias("ckId")
  private String auditorNo;//网银审核员char(20)
  
  @XStreamAlias("ckName")
  private String auditorName;//审核员姓名varchar(20)
  
  @XStreamAlias("balance")
  @XStreamConverter(BankAmountConverter.class)
  private Long AccountBalance;//账户余额 decimal(15,2)
  
  @XStreamAlias("valueDate")
  private String calculateInterestDate;//起息日期 char(8)
  
  @XStreamAlias("hostTranCode")
  private String hostTradeCode;//主机交易码 char(4)

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
  public String getTradeNo() {
    return tradeNo;
  }
  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }
  public String getSumTradeNo() {
    return sumTradeNo;
  }
  public void setSumTradeNo(String sumTradeNo) {
    this.sumTradeNo = sumTradeNo;
  }
  public Long getTradeAmount() {
    return tradeAmount;
  }
  public void setTradeAmount(Long tradeAmount) {
    this.tradeAmount = tradeAmount;
  }
  public DebitCreditTag getDebitCreditTag() {
    return debitCreditTag;
  }
  public void setDebitCreditTag(DebitCreditTag debitCreditTag) {
    this.debitCreditTag = debitCreditTag;
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
  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }
  public CashTransferFlag getCashTransferFlag() {
    return cashTransferFlag;
  }
  public void setCashTransferFlag(CashTransferFlag cashTransferFlag) {
    this.cashTransferFlag = cashTransferFlag;
  }
  public String getOperatorNo() {
    return operatorNo;
  }
  public void setOperatorNo(String operatorNo) {
    this.operatorNo = operatorNo;
  }
  public String getOperatorName() {
    return operatorName;
  }
  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }
  public String getAuditorNo() {
    return auditorNo;
  }
  public void setAuditorNo(String auditorNo) {
    this.auditorNo = auditorNo;
  }
  public String getAuditorName() {
    return auditorName;
  }
  public void setAuditorName(String auditorName) {
    this.auditorName = auditorName;
  }
  public Long getAccountBalance() {
    return AccountBalance;
  }
  public void setAccountBalance(Long accountBalance) {
    AccountBalance = accountBalance;
  }
  public String getCalculateInterestDate() {
    return calculateInterestDate;
  }
  public void setCalculateInterestDate(String calculateInterestDate) {
    this.calculateInterestDate = calculateInterestDate;
  }
  public String getHostTradeCode() {
    return hostTradeCode;
  }
  public void setHostTradeCode(String hostTradeCode) {
    this.hostTradeCode = hostTradeCode;
  }
}
