/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.enums;

/**
 * 银行接口XmlHttp所有的流水类型，code是交易代码
 * 
 * @author ducer
 *
 */
public enum TransType {

  /**
   * 资金初始化
   * <p>
   * 商户初始建立附属账户体系时， 交易资金汇总账户中的资金会自动记录至资金初始化附属账户中。 
   * 商户根据自身台账记录将台账账户期初余额调至附属账户。
   */
  fund_initialize("DLFNDINI"),

  /**
   * 调账入款
   * <p>
   * 商户将公共调账账户款项转至会员交易资金账户。
   * <p>
   * 1)商户须查询公共调账账户交易明细，并记录其中的“交易日期”、“柜员交易号”、“交易序号”三个字段数据；
   * <p>
   * 2)将上述三个字段数据作为调账入款请求报文的参数（即：“被调账日期”、“被调账柜员交易号”、“被调账交易序号”）， 
   *   从而将公共调账账户中的不明来账调入会员交易资金账户。
   */
  adjust_fund_income("DLTRSFIN"),

  /**
   * 错账调回
   * <p>
   * 商户将公共调账账户款项调账至会员交易资金账户后发现调账错误，将错账调回至公共调账账户。本交易是调账入款的逆操作。
   * <p>
   * 1)商户须查询会员交易资金账户交易明细，并记录其中的“交易日期”、“柜员交易号”、“交易序号”三个字段数据；
   * <p>
   * 2)将上述三个字段数据作为错账调回请求报文的参数（即：“被调账日期”、“被调账柜员交易号”、“被调账交易序号”），
   *   从而将错账调回至公共调账账户
   */
  bad_fund_back("DLWFDRTN"),
  
  /**
   * 入金
   * <p>
   * 商户将实体结算账户中的资金转入至自有附属账户
   */
  inject_fund("DLFONDIN"),
  
  /**
   * 出金
   * <p>
   * 商户将自有附属账户中的资金转出至实体结算账户，只有他行账户才需要上送收款信息。
   * <p>
   * 若附属账户已绑定出入金结算账户，则收款账户必须在绑定结算账户范围内；
   * <p>
   * 若附属账户未绑定出入金结算账户，则可以向任意收款账户出金
   */
  withdraw_fund("DLFNDOUT"),
  
  /**
   * 保证金退还
   * <p>
   * 商户将会员缴纳的保证金从保证金附属账户退还至交易资金附属账户。
   * 保证金账户与交易资金账户必须为同一会员在同一附属账户体系内的附属账户。
   */
  deposit_back("DLGTYRTN"),
  
  /**
   * 转账
   * <p>
   * 附属账户之间的普通转账
   */
  transfer_fund_ordinary("DLSUBTRN"),
  
  /**
   * 强制转账
   * <p>
   * 由于一方会员违约不能完成订单业务处理流程时，商户发起强制转账交易来帮助会员完成订单业务处理。
   * 强制转账交易类型分为五类：转账、解冻、解冻支付、冻结、支付冻结。
   * <p>
   * 注意：在“解冻”交易中，收方账号对应 “支付冻结”交易中的付方账号；付方账号对应“支付冻结”交易中的收方账号。
   */
  transfer_fund_force("DLMDETRN"),
  
  /**
   * 充值退回
   * <p>
   * 根据会员的充值退回请求，第三方支付商户可将资金从自有结算账户退回至会员原订单充值支付时的付款结算账户。
   */
  inject_fund_back("DLWDLSUB"),
  
  /**
   * 充值退回结果查询
   * <p>
   * 第三方支付商户查询充值退回交易的处理结果。
   */
  inject_fund_back_query("DLWDLDTL"),
  
  /**
   * 退款
   * <p>
   * 根据会员的退款请求，直联商户可将资金从自有结算账户退回至会员原订单支付时的付款结算账户。
   */
  fund_back("DLRFDSUB"),
  
  /**
   * 退款结果查询
   * <p>
   * 直联商户查询退款交易的处理结果。
   */
  fund_back_query("DLRFDDTL"),
  
  /**
   * 对账文件下载
   * <p>
   * 第三方支付商户或直联商户可按清算日期下载交易对账文件，其中包括支付与退款交易明细信息。
   */
  audit_file_download("DLSESMDN"),
  
  /**
   * 交易状态查询
   * <p>
   * 商户查询金融类交易（3.1～3.8）流水状态。
   */
  trade_status_query("DLCIDSTT"),
  
  /**
   * 商户附属账户余额查询
   * <p>
   * 商户查询附属账户体系内附属账户的余额信息。
   */
  business_attach_account_balance_query("DLSBALQR"),
  
  /**
   * 商户附属账户冻结信息查询
   * <p>
   * 商户查询附属账户体系内附属账户的冻结信息。
   */
  business_attach_account_frozen_query("DLSFRZQR"),
  
  /**
   * 商户附属账户交易明细查询
   * <p>
   * 商户查询附属账户体系内附属账户的交易明细，支持分页查询。当输入分页查询条件
   * （起始记录号、查询记录条数）时，每页最多返回10条记录。当分页查询条件为空时，
   * 查询符合查询条件的全部交易明细。
   */
  business_attach_account_trade_query("DLSTRNDT"),
  
  /**
   * 订单交易结果查询
   * <p>
   * 商户查询会员通过支付网关所做的订单类交易结果。当商户没有收到银行订单交易结果通知时，
   * 可使用本交易查询单笔订单类交易的结果信息。
   */
  order_trade_result_query("DLPGOSTT"),
  
  /**
   * 平台额度查询
   * <p>
   * 该功能用于查询市场平台额度的相关信息。
   */
  platform_fund_query("DLPTFAQY"),
  
  /**
   * 仓库信息查询
   * <p>
   * 该功能用于查询银行仓库的相关信息。
   */
  warehouse_info_query("DLWHIQRY"),
  
  /**
   * 商品信息查询
   * <p>
   * 该功能用于查询市场商品的相关信息。
   */
  commodity_info_query("DLPDIQRY"),
  
  /**
   * 仓单状态查询
   * <p>
   * 该功能用于实时查询仓单状态信息。
   */
  warehouse_order_status_query("DLSKSQRY"),
  
  /**
   * 在线赎货试算并冻结
   * <p>
   * 该功能用于买家通过交易市场下单后，由交易市场通过银企直联渠道向银行发起冻结，
   * 同时进行试算并返回买家剩余应回款余额。 
   */
  online_redeem_count_frozen("DLNRMFRZ"),
  
  /**
   * 仓单解冻
   * <p>
   * 该功能用于对指定已冻结的仓单进行解冻。  
   */
  warehouse_order_unfreeze("DLNRMRLS"),
  
  /**
   * 附属账户预签约
   */
  attach_account_presign("DLBREGST"),
  
  /**
   * 附属账户签约状态查询
   */
  attach_account_sign_query("DLSASQRY"),
  
  /**
   * 预置订单
   */
  prehandle_order("DLGSVODR"),
  
  /**
   * 投标明细查询
   */
  bid_info_query("DLDPDQRY"),
  
  /**
   * 保证金退还经办
   */
  deposit_back_handle("DLDRTSUB"),
  
  /**
   * 保证金退还查询
   */
  deposit_back_query("DLDPBQRY"),
  
  /**
   * 附属账户手续费查询
   */
  attach_account_fee_query("DLSAFQRY"),
  
  /**
   * 赎货还款试算
   * <p>
   * 该功能用于买家通过交易市场下单后，由交易市场通过银企直联渠道向银行发试算交易，
   * 银行返回给买家应还款额度信息。 
   */
  redeem_repayment_count("DLNRMCAL"),
  
  /**
   * 仓单状态变更通知
   * <p>
   * 该功能用于交易平台通过银企直联渠道，提交仓单质押状态变更通知给银行。 
   */
  warehouse_order_change_notice("DLPTSTAN"),
  
  /**
   * 借据余额查询
   * <p>
   * 交易平台可使用此功能，根据融资订单的商户交易流水号查询对应借据余额信息。 
   */
  iou_balance_query("DLFBALQR"),
  
  /**
   * 挂单赎货
   * <p>
   * 交易平台可使用此功能，根据会员上送挂单赎货申请执行客户的赎货请求
   */
  pend_order_redeem("DLSORWTT"),
  
  /**
   * 挂单赎货交易概要信息查询
   * <p>
   * 交易平台可使用此功能，查询该商户一定时间段内“挂单赎货”交易的概要信息。
   * 该交易要求分页查询，每页最多显示20条记录。 
   */
  pend_order_redeem_profiles_info("DLSORWLQ"),
  
  /**
   * 挂单赎货交易明细信息查询
   * <p>
   * 交易平台可使用此功能，根据“挂单赎货”交易上送的“客户流水号”字段，
   * 查询挂单赎货交易的交易状态。
   */
  pend_order_redeem_info("DLSORWDQ"),
  
  /**
   * 融资后续解冻
   * <p>
   * 会员申请融资部分资金解冻后，交易平台可使用此功能，解冻会员申请融资资金的剩余资金。
   */
  financing_frozen("DLSECUFZ"),
  
  /**
   * 商品信息当日增量查询
   * <p>
   * 会员通过直联客户端发起的交易，经电商平台发送SCF同步当日商品信息，
   * 入库操作并组织响应报文返回客户端显示。
   */
  commodity_info_day_inc("DLPDINAQ"),
  
  /**
   * 非登录打印明细查询
   * <p>
   * 会员通过直联客户端发起的交易，经平台发送主机同步查询明细，
   * 返回的明细和打印校验码入库，供公司网银BS非登录打印明细。
   */
  nologin_print_info_query("DLPTDTQY"),
  
  /**
   * 分行保证金账户明细查询
   * <p>
   * 商户可使用此接口查询分行保证金账户明细交易信息
   */
  branch_deposit_account_info_query("DLBSAQRY"),
  
  /**
   * 平台出金
   * <p>
   * 商户可使用此接口完成会员交易资金附属账户出金功能。
   */
  platform_withdraw_fund("DLFCSOUT"),
  
  /**
   * 在线销户
   * <p>
   * 商户可使用此功能注销名下的电子商务类型的附属账号。
   */
  account_cancel_online("DLOLCACC"),
  
  /**
   * 利息试算
   * <p>
   * 商户可使用此功能试算名下的电子商务类型的附属账户待结算的利息。
   */
  interest_count("DLIRTCAL"),
  
  /**
   * 手工结息
   * <p>
   * 商户可使用此功能为名下的电子商务类型的附属账户提前结算利息。
   */
  interest_bear_manual("DLIRTSTL"),
  
  /**
   * 指定状态仓单查询
   * <p>
   * 商户可使用此接口查询SCF系统记录的冻结和质押状态的仓单。
   */
  warehouse_order_query_by_status("DLFSWHSQ"),
  ;
  private String code;

  TransType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public static TransType parseEnum(String code) {
    for (TransType type : TransType.values()) {
      if (type.getCode().equals(code)) return type;
    }
    return null;
  }

  public String formatEnum(){
    return this.getCode();
  }
}
