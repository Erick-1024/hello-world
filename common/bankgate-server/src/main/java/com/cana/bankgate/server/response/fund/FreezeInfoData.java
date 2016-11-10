/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.server.response.fund;

import java.io.Serializable;
import java.util.Date;

import com.cana.bankgate.server.xstream.BankDateConverter;
import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("row")
public class FreezeInfoData implements Serializable {

  private static final long serialVersionUID = 572566305882604719L;

  @XStreamAlias("subAccNo")
  private String accountNo;//附属帐号varchar(19)
  
  @XStreamAlias("DJTYPE")
  private String freezeType;//冻结类型varchar(2)
  
  @XStreamAlias("DJCODE")
  private String freezeNo;//冻结编号varchar(22)
  
  @XStreamAlias("DJDATE")
  @XStreamConverter(value = BankDateConverter.class,strings={"yyyyMMdd"})
  private Date freezeDate;//冻结日期
  
  @XStreamAlias("DJTIME")
  @XStreamConverter(value = BankDateConverter.class,strings={"HHmmss"})
  private Date freezeTime;//冻结时间
  
  @XStreamAlias("DJOPR")
  private String freezeOperatorNo;//冻结柜员交易号varchar(14)
  
  @XStreamAlias("DJAMT")
  @XStreamConverter(value = BankAmountConverter.class)
  private Long freezeAmount;//冻结金额,分为单位
  
  @XStreamAlias("JDDATE")
  @XStreamConverter(value = BankDateConverter.class,strings={"yyyyMMdd"})
  private Date unfreezeDate;//解冻日期
  
  @XStreamAlias("JDTIME")
  @XStreamConverter(value = BankDateConverter.class,strings={"HHmmss"})
  private Date unfreezeTime;//解冻时间
  
  @XStreamAlias("JDOPR")
  private String unfreezeOperatorNo;//解冻柜员交易号varchar(14)
  
  @XStreamAlias("REASON")
  private String reason;//受理原因varchar(60)
  
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }
  public String getFreezeType() {
    return freezeType;
  }
  public void setFreezeType(String freezeType) {
    this.freezeType = freezeType;
  }
  public String getFreezeNo() {
    return freezeNo;
  }
  public void setFreezeNo(String freezeNo) {
    this.freezeNo = freezeNo;
  }
  public Date getFreezeDate() {
    return freezeDate;
  }
  public void setFreezeDate(Date freezeDate) {
    this.freezeDate = freezeDate;
  }
  public Date getFreezeTime() {
    return freezeTime;
  }
  public void setFreezeTime(Date freezeTime) {
    this.freezeTime = freezeTime;
  }
  public String getFreezeOperatorNo() {
    return freezeOperatorNo;
  }
  public void setFreezeOperatorNo(String freezeOperatorNo) {
    this.freezeOperatorNo = freezeOperatorNo;
  }
  public Long getFreezeAmount() {
    return freezeAmount;
  }
  public void setFreezeAmount(Long freezeAmount) {
    this.freezeAmount = freezeAmount;
  }
  public Date getUnfreezeDate() {
    return unfreezeDate;
  }
  public void setUnfreezeDate(Date unfreezeDate) {
    this.unfreezeDate = unfreezeDate;
  }
  public Date getUnfreezeTime() {
    return unfreezeTime;
  }
  public void setUnfreezeTime(Date unfreezeTime) {
    this.unfreezeTime = unfreezeTime;
  }
  public String getUnfreezeOperatorNo() {
    return unfreezeOperatorNo;
  }
  public void setUnfreezeOperatorNo(String unfreezeOperatorNo) {
    this.unfreezeOperatorNo = unfreezeOperatorNo;
  }
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
}
