/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

import com.cana.vbam.common.annotations.NotBlank;

/**
 * 基础信息，只封装了附属帐号和业务端的流水信息
 * 
 * @author ducer
 *
 */
public class BankAccountBaseDTO implements Serializable {

  private static final long serialVersionUID = 3830523509582621201L;
  /**
   * 发起交易附属帐号，最多19位
   */
  @NotBlank
  protected String accountNo;
  /**
   * 业务流水号，业务端
   */
  @NotBlank
  protected String businessSeq;
  /**
   * 交易时间，业务端传，用于业务端对帐用
   */
  @NotBlank
  protected String transDate;

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
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
