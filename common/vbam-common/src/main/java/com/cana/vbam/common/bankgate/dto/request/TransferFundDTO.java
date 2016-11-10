/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import com.cana.vbam.common.annotations.NotBlank;

/**
 * 转账、调账和冻结支付，通用一个DTO
 * 
 * @author ducer
 *
 */
public class TransferFundDTO extends BankAccountBaseDTO {

  private static final long serialVersionUID = -298266655743102408L;
  
  // 收款账号varchar(19)
  @NotBlank
  private String receiveAccountNo;
  // 收款账户名称varchar(60)
  @NotBlank
  private String receiveAccountName;
  // 交易金额decimal(15,2)
  @NotBlank
  private Long amount;
  // 摘要varchar(22) 可空
  private String memo;

  public String getReceiveAccountNo() {
    return receiveAccountNo;
  }

  public void setReceiveAccountNo(String receiveAccountNo) {
    this.receiveAccountNo = receiveAccountNo;
  }

  public String getReceiveAccountName() {
    return receiveAccountName;
  }

  public void setReceiveAccountName(String receiveAccountName) {
    this.receiveAccountName = receiveAccountName;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
