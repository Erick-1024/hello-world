/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

import com.cana.vbam.common.annotations.NotBlank;

/**
 * @author ducer
 *
 */
public class BankAccountCreateDTO implements Serializable {

  private static final long serialVersionUID = -3950576212235240922L;

  /**
   * 银行主账号用户名，如果为空，则默认使用平台的用户名
   */
  private String bankUserName;

  /**
   * 银行主账号，如果为空，则默认使用平台的主账号
   */
  private String mainAccountNo;

  /**
   * 开户的账户名称，一个户名可以开多个账户
   */
  @NotBlank
  private String accountName;
  /**
   * 业务流水号，业务端
   */
  @NotBlank
  private String businessSeq;
  /**
   * 交易时间，业务端传，用于业务端对帐用
   */
  @NotBlank
  private String transDate;

  public String getBankUserName() {
    return bankUserName;
  }

  public void setBankUserName(String bankUserName) {
    this.bankUserName = bankUserName;
  }

  public String getMainAccountNo() {
    return mainAccountNo;
  }

  public void setMainAccountNo(String mainAccountNo) {
    this.mainAccountNo = mainAccountNo;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getBusinessSeq() {
    return businessSeq;
  }

  public void setBusinessSeq(String businessSeq) {
    this.businessSeq = businessSeq;
  }

  public String getTransDate() {
    return transDate;
  }

  public void setTransDate(String transDate) {
    this.transDate = transDate;
  }
}
