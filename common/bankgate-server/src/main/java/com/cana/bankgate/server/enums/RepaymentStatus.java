/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.enums;

/**
 * 还款状态
 * 
 * @author ducer
 *
 */
public enum RepaymentStatus {

  unhandle("00", "未处理"),

  success("01", "成功"),

  fail("02", "失败"),

  unknown("03", "未知"),
  
  handling("04", "处理中"),
  
  day_fail("05", "日终失败，线下处理"),
  ;

  private String statusCode;

  private String desc;

  RepaymentStatus(String statusCode, String desc) {
    this.statusCode = statusCode;
    this.desc = desc;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getDesc() {
    return desc;
  }

  public static RepaymentStatus parseEnum(String statusCode) {
    for (RepaymentStatus status : RepaymentStatus.values()) {
      if (status.getStatusCode().equals(statusCode)) return status;
    }
    return null;
  }

}
