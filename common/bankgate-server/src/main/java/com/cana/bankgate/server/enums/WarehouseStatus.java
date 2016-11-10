/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.enums;

/**
 * 仓库状态
 * 
 * @author ducer
 *
 */
public enum WarehouseStatus {

  effective("1", "生效"),

  uneffective("2", "失效"),

  close("3", "关闭"),
  ;

  private String statusCode;

  private String desc;

  WarehouseStatus(String statusCode, String desc) {
    this.statusCode = statusCode;
    this.desc = desc;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getDesc() {
    return desc;
  }

  public static WarehouseStatus parseEnum(String statusCode) {
    for (WarehouseStatus status : WarehouseStatus.values()) {
      if (status.getStatusCode().equals(statusCode)) return status;
    }
    return null;
  }

}
