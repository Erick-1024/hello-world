/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.account;

import java.io.Serializable;

import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.CurrencyType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("row")
public class BankMainAccountBalanceData implements Serializable {

  private static final long serialVersionUID = 1247516957447734095L;

  @XStreamAlias("status")
  private String accountStatus; // 账户状态 char(7)

  @XStreamAlias("statusText")
  private String accountStatusText; // 账户状态信息 varchar(254)

  @XStreamAlias("accountNo")
  private String mainAccountNo; // 账号 char(19)

  @XStreamAlias("accountName")
  private String mainAccountName; // 账户名称 varchar(60)

  @XStreamAlias("currencyID")
  @XStreamConverter(BankEnumConverter.class)
  private CurrencyType currencyType; // 币种 char(2)

  @XStreamAlias("openBankName")
  private String bankName; // 开户行名称 varchar(62)

  @XStreamAlias("lastTranDate")
  private String lastTradeDate; // 最近交易日 char(8)

  @XStreamAlias("usableBalance")
  @XStreamConverter(BankAmountConverter.class)
  private Long availableBalance; // 账户可用余额 decimal(15,2)

  @XStreamAlias("balance")
  @XStreamConverter(BankAmountConverter.class)
  private Long actualBalance; // 账号实际余额 decimal(15,2)

  @XStreamAlias("forzenAmt")
  @XStreamConverter(BankAmountConverter.class)
  private Long freezeAmount; // 冻结（或看管）金额decimal(15,2)

  public String getAccountStatus() {
    return accountStatus;
  }
  public void setAccountStatus(String accountStatus) {
    this.accountStatus = accountStatus;
  }
  public String getAccountStatusText() {
    return accountStatusText;
  }
  public void setAccountStatusText(String accountStatusText) {
    this.accountStatusText = accountStatusText;
  }
  public String getMainAccountNo() {
    return mainAccountNo;
  }
  public void setMainAccountNo(String mainAccountNo) {
    this.mainAccountNo = mainAccountNo;
  }
  public String getMainAccountName() {
    return mainAccountName;
  }
  public void setMainAccountName(String mainAccountName) {
    this.mainAccountName = mainAccountName;
  }
  public CurrencyType getCurrencyType() {
    return currencyType;
  }
  public void setCurrencyType(CurrencyType currencyType) {
    this.currencyType = currencyType;
  }
  public String getBankName() {
    return bankName;
  }
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
  public String getLastTradeDate() {
    return lastTradeDate;
  }
  public void setLastTradeDate(String lastTradeDate) {
    this.lastTradeDate = lastTradeDate;
  }
  public Long getAvailableBalance() {
    return availableBalance;
  }
  public void setAvailableBalance(Long availableBalance) {
    this.availableBalance = availableBalance;
  }
  public Long getActualBalance() {
    return actualBalance;
  }
  public void setActualBalance(Long actualBalance) {
    this.actualBalance = actualBalance;
  }
  public Long getFreezeAmount() {
    return freezeAmount;
  }
  public void setFreezeAmount(Long freezeAmount) {
    this.freezeAmount = freezeAmount;
  }
}
