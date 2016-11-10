/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.enums;

/**
 * 商品状态
 * 
 * @author ducer
 *
 */
public enum CommodityStatus {

  effective("01", "有效"),

  uneffective("02", "无效"),
  ;

  private String statusCode;

  private String desc;

  CommodityStatus(String statusCode, String desc) {
    this.statusCode = statusCode;
    this.desc = desc;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getDesc() {
    return desc;
  }

  public static CommodityStatus parseEnum(String statusCode) {
    for (CommodityStatus status : CommodityStatus.values()) {
      if (status.getStatusCode().equals(statusCode)) return status;
    }
    return null;
  }
}
