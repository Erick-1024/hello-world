/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 现金、转帐标志
 * 
 * @author ducer
 *
 */
public enum CashTransferFlag {

  cash("0", "现金"), 
  transfer_fund("1", "转帐"),
  ;

  private String code;

  private String desc;

  CashTransferFlag(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static CashTransferFlag parseEnum(String code) {
    for (CashTransferFlag flag : CashTransferFlag.values()) {
      if (flag.getCode().equals(code))
        return flag;
    }
    return null;
  }
}
