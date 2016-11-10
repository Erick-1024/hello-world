/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 借贷标志 流水中的credit表示入金，debit表示出金
 * 
 * @author ducer
 *
 */
public enum DebitCreditTag {

  debit("D", "借款方"), credit("C", "贷款方"),;

  private String code;
  private String desc;

  DebitCreditTag(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static DebitCreditTag parseEnum(String code) {
    for (DebitCreditTag flag : DebitCreditTag.values()) {
      if (flag.code.equals(code))
        return flag;
    }
    return null;
  }

}
