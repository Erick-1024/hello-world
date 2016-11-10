/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * @author ducer
 *
 */
public enum CurrencyType {

  RMB("DLMDETRN","人民币"),
  DOLLAR("","美元"),
  ;

  private String code;

  private String desc;

  CurrencyType(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static CurrencyType parseEnum(String code) {
    for (CurrencyType type : CurrencyType.values()) {
      if (type.getCode().equals(code)) return type;
    }
    return null;
  }



}
