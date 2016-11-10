/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.enums;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.bankgate.enums.BaseTranStatus;

/**
 * 网关对银行处理结果封装,如果只关心失败、成功、未知三种状态，可用{@link BankBizStatus#toBaseStatus}<br>
 * Note: 如果要判断两个状态是否一致，请用<Code>equal</Code>，而不要用<Code>==</Code>
 * 
 * @author ducer
 *
 */
public final class BankBizStatus implements Serializable {
  private static final Logger logger = LoggerFactory.getLogger(BankBizStatus.class);
  private static final long serialVersionUID = -4097331486139739302L;
  private String name;
  private String[] code;
  private String[] desc;

  private BankBizStatus(String name, String[] code, String[] desc) {
    this.name = name;
    this.code = code;
    this.desc = desc;
  }

  public String[] getCode() {
    return this.code;
  }

  public String name() {
    return this.name;
  }

  @Override
  public String toString() {
    return name();
  }

  public String[] description() {
    return this.desc;
  }

  private static BankBizStatus init(String name, String[] code, String[] desc) {
    return new BankBizStatus(name, code, desc);
  }

  /**
   * 交易成功
   */
  public static final BankBizStatus trade_success = init("trade_success", 
      new String[] {"AAAAAAA"}, 
      new String[] {"交易成功"});
  /**
   * 经办成功待审核
   */
  public static final BankBizStatus handle_audit = init("handle_audit",
      new String[] {"AAAAAAB"}, 
      new String[] {"经办成功待受理"});
  /**
   * 预约支付成功
   */
  public static final BankBizStatus appoint_pay = init("appoint_pay", 
      new String[] {"AAAAAAC"}, 
      new String[] {"预约支付成功"});
  /**
   * 现金管理代理收款信息查询交易专用，表示扣款方审核通过，扣款成功
   */
  public static final BankBizStatus deduct_success = init("deduct_success", 
      new String[] {"AAAAAAD"}, 
      new String[] {"扣款成功"});
  /**
   * 已提交银行处理，需稍后使用“交易状态查询”进行查询.
   */
  public static final BankBizStatus handling = init("bank_handling",
      new String[] {"AAAAAAE", "CCCCCCC", "EEEEEEE", "UNKNOWN", "ED02091"},
      new String[] {"已提交银行处理，请稍后使用交易状态查询确定结果", "交易处理中", "交易未产生，请务必使用该业务的汇总查询交易确认交易真实状态", "交易状态未知", "该笔单子现处于主机处理中状态"});

  /**
   * 两位字母+数字的组合为网银错误代码，其他为后台错误代码。错误信息见statusText字段，以中文表示。交易未产生也视为银行内部错误
   */
  public static final BankBizStatus fail = init("fail", 
      new String[] {"fail"}, 
      new String[] {"fail"});
  /**
   * 网关错误
   */
  public static final BankBizStatus gate_error = init("gate_error", 
      new String[] {"gate_error"}, 
      new String[] {"gate_error"});
  /**
   * 连接超时,超时时间在配置文件里面设置
   */
  public static final BankBizStatus conn_timeout = init("conn_timeout", 
      new String[] {"conn_timeout"}, 
      new String[] {"conn_timeout"});
  /**
   * 请求超时，超时时间在配置文件里面设置
   */
  public static final BankBizStatus timeout = init("timeout", 
      new String[] {"timeout"}, 
      new String[] {"timeout"});

  /**
   * 查询的时候：没有符合条件的记录，也视为查询成功
   */
  public static final BankBizStatus no_record = init("no_record",
      new String[] {"PBRA001"},
      new String[] {"没有符合条件的记录"});
  
  public static final BankBizStatus login = init("login",
      new String[] {"ED12002"},
      new String[] {"系统登录中，请稍后再试"});
  
  public static final BankBizStatus no_account = init("no_account",
      new String[] {"BLR0024", "ED02010", "BLR0027", "ED03307"},
      new String[] {"付款账号不存在", "输入的付款账号不存在", "收款账号不存在", "输入的收款账号不存在"});
  
  /**
   * 请求过于频繁，会被银行服务器直接打回
   */
  public static final BankBizStatus bank_limit = init("bank_limit",
      new String[] {"ED11308", "ED07018"},
      new String[] {"交易行为异常,请联系技术人员"});
  
  /**
   * 余额不足
   */
  public static final BankBizStatus poor_balance = init("no_balance",
	  new String[] {"CPRA004","CPRA005","DAR0004","DCRB010","DCRC030","ABR0061","AGRA200","BMR0083","BMR0302","BMRA535","DDRA279","DPRA003","DPRB001","DPRC141","E01RJ03","EPR1700","ESR6016","FNR3044","FTR0040","FTR1095","FXR0045","GIRA021","IMR0049","IMR0153","IMR1201","IMRA035","IMRA131","IMRA149","IMRA219","IMRA257","LCR0054","LCR1802","LCRA057","PSRA050","SBR1033","SGR0009","SNR1002","SNR2002","SSR0001","SZR5069","T1I2008","T1O2008","V000051"},
	  new String[] {"账户可用余额不足"});
  
  public static final BankBizStatus[] values = new BankBizStatus[] {
      trade_success, handle_audit, appoint_pay, deduct_success, handling, gate_error, conn_timeout, 
      timeout, poor_balance, no_record, login, no_account, bank_limit};

  public static BankBizStatus parseEnum(String code) {
    for (BankBizStatus status : values) {
      if (inArray(code, status.code)) return status;
    }
    logger.info("返回码未匹配，视为银行系统执行失败，返回码:{}", code);
    return new BankBizStatus("fail", new String[] {code}, new String[] {"银行后台执行失败"});
  }

  public boolean equals(BankBizStatus status) {
    if (this.name.equals(status.name()) || isEqualArray(status.getCode(), this.code)) {
      return true;
    }
    return false;
  }

  public boolean oneOf(BankBizStatus... statuses) {
    for (BankBizStatus status : statuses) {
      if (this.equals(status)) return true;
    }
    return false;
  }

  public static BankBizStatus valueOf(String name) {
    for (BankBizStatus status : values) {
      if (status.name.equals(name)) return status;
    }
    throw new RuntimeException("不存在该状态");
  }

  /**
   * 把业务状态转化为：成功、失败、处理中（未知）三种基本状态
   */
  public BaseTranStatus toBaseStatus() {
    if (this.oneOf(trade_success, deduct_success, no_record)) {
      return BaseTranStatus.success;
    } else if (this.oneOf(appoint_pay, handling, handle_audit, timeout, gate_error)) {
      return BaseTranStatus.handling;
    } else if (this.oneOf(fail, conn_timeout, poor_balance, login, no_account, bank_limit)) {
      return BaseTranStatus.fail;
    }
    return BaseTranStatus.handling;
  }

  public BankTranStatus toBankTranStatus() {
    if (this.oneOf(trade_success, deduct_success, no_record)) {
      return BankTranStatus.success;
    } else if (this.oneOf(appoint_pay, handling, handle_audit)) {
      return BankTranStatus.handling;
    } else if (this.oneOf(fail, login, no_account, bank_limit)) {
      return BankTranStatus.fail;
    } else if (this.oneOf(gate_error)) {
      return BankTranStatus.gate_error;
    } else if (this.oneOf(conn_timeout)) {
      return BankTranStatus.conn_timeout;
    } else if (this.oneOf(timeout)) {
      return BankTranStatus.timeout;
    } else if (this.oneOf(poor_balance)) {
      return BankTranStatus.poor_balance;
    }
    return BankTranStatus.handling;
  }
  
  private static boolean inArray(String code, String[] codes) {
    for (String c : codes) {
      if (c.equals(code)) return true;
    }
    return false;
  }

  private static boolean isEqualArray(String[] src, String[] tar) {
    if (src.length != tar.length) return false;
    for (int i = 0; i < src.length; i++) {
      if (!src[i].equals(tar[i])) return false;
    }
    return true;
  }
}
