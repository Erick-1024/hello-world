/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.enums;

import com.cana.bankgate.server.constants.BankgateConstant;

/**
 * @author ducer
 *
 */
public enum BankgateError {

  conn_refuse("无法连接银行服务，请检查网络连接或者咨询银行运维."), 
  conn_timeout("网关连接银行服务器超时.当前超时阈为" + BankgateConstant.config.getConnectionTimeout()), 
  read_timeout("网关请求银行服务器响应超时.当前超时阈为" + BankgateConstant.config.getTimeout()), 
  gate_error("网关端异常."),
  validate_fail("参数验证失败.");
  private String value;

  BankgateError(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }
}
