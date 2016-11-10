/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.server.enums;

/**
 * @author ducer
 *
 */
public enum TransferFundType {

  transfer_fund("BF","转账"),
  adjust_fund("BF","调账"),
  unfreeze_fund("BG","解冻"),
  unfreeze_pay("BH","解冻支付"),
  freeze_fund("BR","冻结"),
  freeze_pay("BS","支付冻结"),
  ;
  private String code;
  private String desc;
  
  TransferFundType(String code,String desc){
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
  
  public static TransferFundType parseEnum(String code){
    for(TransferFundType type : TransferFundType.values()){
      if(type.code.equals(code)) return type;
    }
    return null;
  }
}
