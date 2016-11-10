/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ducer
 *
 */
public class FreezeInfoDataDTO implements Serializable {

  private static final long serialVersionUID = -117210516300526945L;

  private String accountNo;//附属帐号varchar(19)
  private String freezeType;//冻结类型varchar(2)
  private String freezeNo;//冻结编号varchar(22)
  private Date freezeDate;//冻结日期yyyyMMdd,因为返回的结果日期可能为空，所以并没有对时间和日期进行合并
  private Date freezeTime;//冻结时间HHmmss
  private String freezeOperatorNo;//冻结柜员交易号varchar(14)
  private Long freezeAmount;//冻结金额,分为单位
  private Date unfreezeDate;//解冻日期
  private Date unfreezeTime;//解冻时间
  private String unfreezeOperatorNo;//解冻柜员交易号varchar(14)
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
  public Date getFreezeTime() {
    return freezeTime;
  }
  public void setFreezeTime(Date freezeTime) {
    this.freezeTime = freezeTime;
  }
  public Date getUnfreezeTime() {
    return unfreezeTime;
  }
  public void setUnfreezeTime(Date unfreezeTime) {
    this.unfreezeTime = unfreezeTime;
  }
}
