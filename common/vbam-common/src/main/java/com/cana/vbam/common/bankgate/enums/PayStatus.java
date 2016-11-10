/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 支付状态
 * 
 * @author ducer
 *
 */
public enum PayStatus {

  handling("00", "主机处理中"),

  success("01", "交易成功"),

  fail("02", "交易失败"),
  
  unknown("03","状态未知"),
  ;

  private String code;

  private String desc;

  PayStatus(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static PayStatus parseEnum(String code) {
    for (PayStatus status : PayStatus.values()) {
      if (status.getCode().equals(code)) return status;
    }
    return null;
  }
}
