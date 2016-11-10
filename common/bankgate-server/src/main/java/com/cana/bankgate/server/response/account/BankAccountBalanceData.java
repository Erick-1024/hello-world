/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.account;

import java.io.Serializable;

import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("row")
public class BankAccountBalanceData implements Serializable {
  private static final long serialVersionUID = -5077092526325504233L;
 
  @XStreamAlias("subAccNo")
  private String accountNo;  // 附属账号varchar(19)
  
  @XStreamAlias("SUBACCNM")
  private String accountName; // 附属账户名称varchar(60)
  
  @XStreamAlias("TZAMT")
  @XStreamConverter(value = BankAmountConverter.class)
  private Long overdraft; // 透支额度decimal(15,2)
  
  @XStreamAlias("XSACVL")
  @XStreamConverter(value = BankAmountConverter.class)
  private Long availableFund; // 实体账户可用资金decimal(15,2)
 
  @XStreamAlias("KYAMT")
  @XStreamConverter(value = BankAmountConverter.class)
  private Long availableBalance;  // 可用余额 decimal(15,2)
  
  @XStreamAlias("SJAMT")
  @XStreamConverter(value = BankAmountConverter.class)
  private Long actualBalance; // 实际余额decimal(15,2)
  
  @XStreamAlias("DJAMT")
  @XStreamConverter(value = BankAmountConverter.class)
  private Long freezeFund; // 冻结金额decimal(15,2)

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
  public Long getOverdraft() {
    return overdraft;
  }
  public void setOverdraft(Long overdraft) {
    this.overdraft = overdraft;
  }
  public Long getAvailableFund() {
    return availableFund;
  }
  public void setAvailableFund(Long availableFund) {
    this.availableFund = availableFund;
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
  public Long getFreezeFund() {
    return freezeFund;
  }
  public void setFreezeFund(Long freezeFund) {
    this.freezeFund = freezeFund;
  }
}
