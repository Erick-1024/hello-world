/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.enums;

/**
 * 制单状态
 * 
 * @author ducer
 *
 */
public enum DocumentStatus {

  audit_not("0", "未审核"),

  audit_reject("1", "审核拒绝"),

  audit_success("2", "审核成功，等待继续审核"),

  audit_complete("3", "审核完成，等待发送主机"),
  
  advance_success("4","预约成功，等待发送主机"),
  
  advance_fail("5","预约被取消"),
  
  handling_host("6","主机处理中"),
  
  trade_success("7","交易成功"),
  
  trade_fail("8","交易失败"),
  
  cancel("9","撤销"),
  
  unknown("10","状态未知"),
  
  handling_thread("11","线程处理中"),
  
  cancel_unknown("12","撤销状态未知"),
  
  success_all("13","全部成功"),
  
  success_part("14","部分成功"),
  
  fail_all("15","全部失败"),
  
  background_sync("16","后台状态同步中（电子票据）"),
  ;

  private String statusCode;

  private String desc;

  DocumentStatus(String statusCode, String desc) {
    this.statusCode = statusCode;
    this.desc = desc;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getDesc() {
    return desc;
  }

  public static DocumentStatus parseEnum(String statusCode) {
    for (DocumentStatus status : DocumentStatus.values()) {
      if (status.getStatusCode().equals(statusCode)) return status;
    }
    return null;
  }

}
