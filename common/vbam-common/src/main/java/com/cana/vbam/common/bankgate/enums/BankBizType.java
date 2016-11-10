/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 平台对银行业务类型：业务端和网关端共用的枚举，以便对帐和报表,{@link FundBizType }是其资金交易子类
 * 
 * @author ducer
 *
 */
public enum BankBizType {

  deposit_fund("DLFONDIN","入金"), //本业务类型只是做报表的时候用，平台目前不存在入金业务，但报表有用到这个业务类型
	
  create_bank_account("DLBREGST","开户"),
  
  query_account_balance("DLSBALQR","余额查询"),
  
  query_trade_status("DLCIDSTT","交易状态查询"),
  
  platform_withdraw_fund("DLFCSOUT","提现"),//Cana业务平台出金和出金共用一个
  
  online_cancel_account("DLOLCACC","线上销户"),
  
  transfer_fund("DLMDETRN","转帐"),//转账和调账逻辑上都属于转账，但是业务类型上需要做一个区分
  
  adjust_fund("DLMDETRN","调账"), // 逻辑上属于转账
  
  refund_fund("DLMDETRN","退款"),//退款业务类型逻辑上属于转账
  
  unfreeze_fund("DLMDETRN","解冻"), // 逻辑上属于转账
  
  unfreeze_pay("DLMDETRN","解冻支付"), // 逻辑上属于转账
  
  freeze_fund("DLMDETRN","冻结"), // 逻辑上属于转账
  
  freeze_pay("DLMDETRN","支付冻结"), // 逻辑上属于转账
  
  query_freeze_info("DLSFRZQR","账户冻结历史查询"),
  
  query_bank_info("DLBNKCOD","银行名称和支付联行地址查询"),
  
  query_account_trade_flow("DLSTRNDT","查询银行附属账户交易明细"),
 
  query_main_account_trade_flow("DLTRNALL","查询主账户交易明细"),
  
  query_main_account_balance("DLBALQRY","查询主账户余额"),//也是查询实体账户余额的接口
  
  nonlogin_query_trade_detail("DLPTDTQY", "非登录打印明细查询"),

  account_param_mangerment("DLSBAMAN", "附属账户参数管理"),

  account_signup_state("DLSASQRY", "附属账户签约状态查询"),
  ;
  private String code;
  private String desc;
  BankBizType(String code,String desc){
    this.code = code;
    this.desc = desc;
  }
  
  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static BankBizType parseEnum(String code){
    for(BankBizType tag : BankBizType.values()){
      if(tag.code.equals(code)) return tag;
    }
    return null;
  }
  
  public boolean oneOf(BankBizType... types) {
    for (BankBizType type : types) {
      if (this.equals(type)) {
        return true;
      }
    }
    return false;
  }
}
