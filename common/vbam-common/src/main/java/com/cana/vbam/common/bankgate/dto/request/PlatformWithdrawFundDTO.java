/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.util.Date;

import com.cana.vbam.common.annotations.NotBlank;
import com.cana.vbam.common.bankgate.enums.AppointmentFlag;
import com.cana.vbam.common.bankgate.enums.AppointmentTime;
import com.cana.vbam.common.bankgate.enums.BankFlag;

/**
 * 平台出金
 * 
 * @author ducer
 *
 */
public class PlatformWithdrawFundDTO extends BankAccountBaseDTO {

  private static final long serialVersionUID = -7851860854738730039L;
  @NotBlank
  private String receiveAccountNo;// 收款帐号
  @NotBlank
  private String receiveAccountName;// 收款账户名
  @NotBlank
  private Long amount;// 交易金额
  @NotBlank
  private BankFlag bankFlag = BankFlag.citic_bank;// 中信标识char(1) 0：本行 1： 他行 第一期需求暂时全部默认在中信体系内交易
  //收款账户开户行信息:收款账户若为他行，则收款账户开户行支付联行号与收款账户开户行名至少一项不为空
  private String receiveBankNo;// 收款账户开户行支付联行号varchar(12)
  private String receiveBankName;// 收款账户开户行名varchar (60)
  // 摘要varchar(22) 可空
  private String memo;
  
  private AppointmentFlag appointmentFlag = AppointmentFlag.unappointment;// 预约标志（0：非预约1：预约）char(1)
  private Date appointmentDate = new Date();// 预约日期（格式：YYYYMMDD 预约时非空）char(8)
  private AppointmentTime appointmentTime = AppointmentTime.am_10;// 预约时间（格式：hhmmss 预约时非空，只限100000、120000、140000、160000四个时间点）char(6)
  
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
  public Date getAppointmentDate() {
    return appointmentDate;
  }
  public AppointmentTime getAppointmentTime() {
    return appointmentTime;
  }
}
