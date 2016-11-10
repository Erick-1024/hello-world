/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 是{@link BankBizType}的子类，只包含资金业务操作
 * 
 * @author ducer
 *
 */
public enum FundBizType {

  deposit_fund("DLFONDIN","入金"), //本业务类型只是做报表的时候用，平台目前不存在入金业务
  
  platform_withdraw_fund("DLFCSOUT","平台出金"),//Cana业务平台出金和出金共用一个
  
  transfer_fund("DLMDETRN","转帐"),//转账业务类型逻辑上属于强制转账
  
  adjust_fund("DLMDETRN","调账"),//调账业务类型逻辑上属于强制转账
  
  refund_fund("DLMDETRN","退款"),//退款业务类型逻辑上属于强制转账
  
  unfreeze_fund("DLMDETRN","解冻"),//解冻业务类型逻辑上属于强制转账
  
  unfreeze_pay("DLMDETRN","解冻支付"),//解冻支付业务类型逻辑上属于强制转账
  
  freeze_fund("DLMDETRN","冻结"),//冻结业务类型逻辑上属于强制转账
  
  freeze_pay("DLMDETRN","支付冻结"),//支付冻结业务类型逻辑上属于强制转账
  ;

  private String code;

  private String desc;

  FundBizType(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static FundBizType parseEnum(String code) {
    for (FundBizType status : FundBizType.values()) {
      if (status.getCode().equals(code)) return status;
    }
    return null;
  }


}
