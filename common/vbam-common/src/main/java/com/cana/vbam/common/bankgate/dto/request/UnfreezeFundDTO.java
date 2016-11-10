/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import com.cana.vbam.common.annotations.NotBlank;

/**
 * 解冻和解冻支付
 * 
 * @author ducer
 *
 */
public class UnfreezeFundDTO extends BankAccountBaseDTO {

  private static final long serialVersionUID = -3083858329688363338L;
  // 原业务流水号，解冻和解冻支付之前的支付冻结和冻结操作的业务流水号
  @NotBlank
  private String originBusinessSeq;
  // 收款账号varchar(19)，当转账类型为“冻结”时可空，其他类型必输
  @NotBlank
  private String receiveAccountNo;
  // 收款账户名称varchar(60) ，当转账类型为“冻结”时可空，其他类型必输
  @NotBlank
  private String receiveAccountName;
  // 交易金额decimal(15,2)
  @NotBlank
  private Long amount;
  // 冻结编号varchar(22)，转账类型为“解冻”或“解冻支付”时，必输
  @NotBlank
  private String freezeNo;
  // 摘要varchar(22) 可空
  private String memo;

  public String getOriginBusinessSeq() {
    return originBusinessSeq;
  }

  public void setOriginBusinessSeq(String originBusinessSeq) {
    this.originBusinessSeq = originBusinessSeq;
  }

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

  public String getFreezeNo() {
    return freezeNo;
  }

  public void setFreezeNo(String freezeNo) {
    this.freezeNo = freezeNo;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
