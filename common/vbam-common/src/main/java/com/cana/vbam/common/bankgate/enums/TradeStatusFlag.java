/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * @author ducer
 *
 */
public enum TradeStatusFlag {

  success("0", "成功"),

  fail("1", "失败"),

  unknown("2", "未知"),
  
  reject("3","审核拒绝"),
  
  cancel("4","用户撤销"),
  ;

  private String code;

  private String desc;

  TradeStatusFlag(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static TradeStatusFlag parseEnum(String code) {
    for (TradeStatusFlag status : TradeStatusFlag.values()) {
      if (status.getCode().equals(code)) return status;
    }
    return null;
  }
}
