/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 银行交易类型,目前只用到了：强制转帐.
 * Note:本枚举的业务概念是对于银行业务的，与平台的业务关联不大.<br/>
 * 
 * @author ducer
 *
 */
public enum BankTradeType {


  ordinary_transfer("11", "普通转账"),

  fund_initialize("12", "资金初始化"),

  interest_allocation("13", "利息分配"),

  fee_allocation("14", "手续费分配"),

  force_transfer("15", "强制转账"),//包含平台业务：转账、冻结、解冻、支付冻结、解冻支付、调账

  adjust_fund("16","调账"),
  
  public_interest_account_external_transfer("21","公共利息收费账户转账"),
  
  public_adjust_account_external_transfer("22","公共调账账户外部转账"),
  
  ordinary_external_transfer("23","普通外部转账"),
  ;

  private String code;

  private String desc;
  
  BankTradeType(String code,String desc){
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static BankTradeType parseEnum(String code) {
    for (BankTradeType type : BankTradeType.values()) {
      if (type.getCode().equals(code)) return type;
    }
    return null;
  }


}
