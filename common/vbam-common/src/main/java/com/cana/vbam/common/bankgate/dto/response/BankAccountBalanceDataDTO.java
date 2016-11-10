/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;

/**
 * 所有的金额都用Long型，分为单位
 * 
 * @author ducer
 *
 */
public class BankAccountBalanceDataDTO implements Serializable {

  private static final long serialVersionUID = -7085296781040414832L;
  // 附属账号varchar(19)
  private String accountNo;
  // 附属账户名称varchar(60)
  private String accountName;
  // 透支额度decimal(15,2)
  private Long overdraft;
  // 实体账户可用资金decimal(15,2)
  private Long availableFund;
  // 可用余额 decimal(15,2)
  private Long availableBalance;
  // 实际余额decimal(15,2)
  private Long actualFund;
  // 冻结金额decimal(15,2)
  private Long freezeFund;

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

  public Long getActualFund() {
    return actualFund;
  }

  public void setActualFund(Long actualFund) {
    this.actualFund = actualFund;
  }

  public Long getFreezeFund() {
    return freezeFund;
  }

  public void setFreezeFund(Long freezeFund) {
    this.freezeFund = freezeFund;
  }
}
