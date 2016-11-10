/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.fund;

import com.cana.bankgate.server.enums.TransferFundType;
import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * BF：转账；BG：解冻；BH：解冻支付；BR：冻结；BS：支付冻结<br>
 * 公用一个OXM对象
 * 
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class TransferFundForce extends BankBaseRequest{

  private static final long serialVersionUID = -4100982031831669343L;

  @XStreamAlias("userName")
  private String bankUserName; // 登录名 varchar(30)

  @XStreamAlias("clientID")
  private String gateSeq; // 客户流水号varchar (20)

  @XStreamAlias("accountNo")
  private String mainAccountNo;// 主体账号varchar(19)

  @XStreamAlias("payAccNo")
  private String accountNo; // 付款账号varchar(19)

  @XStreamAlias("tranType")
  @XStreamConverter(BankEnumConverter.class)
  private TransferFundType tranType;// 转账类型varchar(2) ，BF：转账；BG：解冻；BH：解冻支付；BR：冻结；BS：支付冻结

  @XStreamAlias("recvAccNo")
  private String receiveAccountNo;// 收款账号varchar(19)，当转账类型为“冻结”时可空，其他类型必输

  @XStreamAlias("recvAccNm")
  private String receiveAccountName;// 收款账户名称varchar(60) ，当转账类型为“冻结”时可空，其他类型必输

  @XStreamAlias("tranAmt")
  @XStreamConverter(value = BankAmountConverter.class)
  private Long amount;// 交易金额decimal(15,2)

  @XStreamAlias("freezeNo")
  private String freezeNo;// 冻结编号varchar(22)，转账类型为“解冻”或“解冻支付”时，必输

  @XStreamAlias("memo")
  private String memo;// 摘要varchar(22) 可空

  @XStreamAlias("tranFlag")
  private String tranFlag; // 转账时效标识char (1)，0：异步交易；1：同步交易
  
  public TransferFundForce(BankBizType bankBizType) {
	super(bankBizType);
  }
  
  public String getBankUserName() {
    return bankUserName;
  }
  public void setBankUserName(String bankUserName) {
    this.bankUserName = bankUserName;
  }
  public String getGateSeq() {
    return gateSeq;
  }
  public void setGateSeq(String gateSeq) {
    this.gateSeq = gateSeq;
  }
  public String getMainAccountNo() {
    return mainAccountNo;
  }
  public void setMainAccountNo(String mainAccountNo) {
    this.mainAccountNo = mainAccountNo;
  }
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }
  public TransferFundType getTranType() {
    return tranType;
  }
  public void setTranType(TransferFundType tranType) {
    this.tranType = tranType;
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
  public String getTranFlag() {
    return tranFlag;
  }
  public void setTranFlag(String tranFlag) {
    this.tranFlag = tranFlag;
  }
}
