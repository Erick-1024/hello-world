/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.enums;

/**
 * 赎货状态
 * 
 * @author ducer
 *
 */
public enum RedeemStatus {

  unhandle("00", "未处理"),

  success("01", "成功"),

  fail("02", "失败"),

  unknown("03", "未知"),

  handling("04", "处理中"),;

  private String statusCode;

  private String desc;

  RedeemStatus(String statusCode, String desc) {
    this.statusCode = statusCode;
    this.desc = desc;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getDesc() {
    return desc;
  }

  public static RedeemStatus parseEnum(String statusCode) {
    for (RedeemStatus status : RedeemStatus.values()) {
      if (status.getStatusCode().equals(statusCode)) return status;
    }
    return null;
  }

}
