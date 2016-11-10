/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 网关对银行处理结果封装,如果只关心失败、成功、未知三种状态，可用{@link BankTranStatus#toBaseStatus}<br>
 * 
 * @author ducer
 *
 */
public enum BankTranStatus {

  success, // 成功
  handling, // 处理中
  fail, // 失败
  conn_timeout, // 银行服务连接超时，链接拒绝
  timeout, // 银行服务请求超时
  poor_balance, // 余额不足
  gate_error, // 网关服务异常
  ;


  public boolean oneOf(BankTranStatus... statuses) {
    for (BankTranStatus status : statuses) {
      if (this.equals(status))
        return true;
    }
    return false;
  }

  /**
   * 把业务状态转化为：成功、失败、处理中（未知）三种基本状态
   */
  public BaseTranStatus toBaseStatus() {
    if (this.oneOf(BankTranStatus.success)) {
      return BaseTranStatus.success;
    } else if (this.oneOf(BankTranStatus.handling, BankTranStatus.timeout, BankTranStatus.gate_error)) {
      return BaseTranStatus.handling;
    } else if (this.oneOf(BankTranStatus.conn_timeout, BankTranStatus.poor_balance, BankTranStatus.fail)) {
      return BaseTranStatus.fail;
    }
    return BaseTranStatus.handling;
  }
}
