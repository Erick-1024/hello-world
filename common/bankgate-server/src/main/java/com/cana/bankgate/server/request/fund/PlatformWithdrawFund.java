/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.server.request.fund;

import java.util.Date;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.cana.bankgate.server.xstream.BankDateConverter;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.AppointmentFlag;
import com.cana.vbam.common.bankgate.enums.AppointmentTime;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.BankFlag;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class PlatformWithdrawFund extends BankBaseRequest{

private static final long serialVersionUID = 6134030425016994480L;

  @XStreamAlias("userName")
  private String bankUserName;//登录名char(30)
  
  @XStreamAlias("clientID")
  private String gateSeq;//客户流水号 char(20)
  
  @XStreamAlias("accountNo")
  private String accountNo;//付款账号varchar(19)
  
  @XStreamAlias("recvAccNo")
  private String receiveAccountNo;//收款账号varchar(32)
  
  @XStreamAlias("recvAccNm")
  private String receiveAccountName;//收款账户名称varchar(60)
  
  @XStreamAlias("tranAmt")
  @XStreamConverter(value = BankAmountConverter.class)
  private Long amount;//交易金额decimal(15,2)
  
  @XStreamAlias("sameBank")
  @XStreamConverter(value = BankEnumConverter.class)
  private BankFlag bankFlag;//中信标识char(1) 0：本行 1： 他行
  
  //收款账户开户行信息:收款账户若为他行，则收款账户开户行支付联行号与收款账户开户行名至少一项不为空 
  @XStreamAlias("recvTgfi")
  private String receiveBankNo;//收款账户开户行支付联行号varchar(12)
  
  @XStreamAlias("recvBankNm")
  private String receiveBankName;//收款账户开户行名varchar (60)
  
  @XStreamAlias("memo")
  private String memo;//摘要varchar(22) 可空
  
  @XStreamAlias("preFlg")
  @XStreamConverter(value = BankEnumConverter.class)
  private AppointmentFlag appointmentFlag;//预约标志（0：非预约1：预约）char(1)
  
  @XStreamAlias("preDate")
  @XStreamConverter(value = BankDateConverter.class,strings={"yyyyMMdd"})
  private Date appointmentDate;//预约日期（格式：YYYYMMDD 预约时非空）char(8)
  
  @XStreamAlias("preTime")
  @XStreamConverter(value = BankEnumConverter.class)
  private AppointmentTime appointmentTime;//预约时间（格式：hhmmss 预约时非空，只限100000、120000、140000、160000四个时间点）char(6)
  
  public PlatformWithdrawFund(BankBizType bankBizType) {
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
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
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
  public BankFlag getBankFlag() {
    return bankFlag;
  }
  public void setBankFlag(BankFlag bankFlag) {
    this.bankFlag = bankFlag;
  }
  public String getReceiveBankNo() {
    return receiveBankNo;
  }
  public void setReceiveBankNo(String receiveBankNo) {
    this.receiveBankNo = receiveBankNo;
  }
  public String getReceiveBankName() {
    return receiveBankName;
  }
  public void setReceiveBankName(String receiveBankName) {
    this.receiveBankName = receiveBankName;
  }
  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }
  public AppointmentFlag getAppointmentFlag() {
    return appointmentFlag;
  }
  public void setAppointmentFlag(AppointmentFlag appointmentFlag) {
    this.appointmentFlag = appointmentFlag;
  }
  public Date getAppointmentDate() {
    return appointmentDate;
  }
  public void setAppointmentDate(Date appointmentDate) {
    this.appointmentDate = appointmentDate;
  }
  public AppointmentTime getAppointmentTime() {
    return appointmentTime;
  }
  public void setAppointmentTime(AppointmentTime appointmentTime) {
    this.appointmentTime = appointmentTime;
  }
}
