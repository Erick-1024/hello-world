/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 银行标志
 * 
 * @author ducer
 *
 */
public enum BankFlag {

  citic_bank("0","中信银行"),
  
  other_bank("1","他行"),
  ;
  private String code;
  private String desc;
  BankFlag(String code,String desc){
    this.code = code;
    this.desc = desc;
  }
  
  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static BankFlag parseEnum(String code){
    for(BankFlag tag : BankFlag.values()){
      if(tag.code.equals(code)) return tag;
    }
    return null;
  }
}
