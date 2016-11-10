/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.server.enums;

/**
 * 仓单状态
 * 
 * @author ducer
 *
 */
public enum WarehouseOrderStatus {

  not_pledge("00","未出质"),
  
  apply_pledge("01","申请出质"),
  
  pledged("02","已出质"),
  
  redeemed("03","已赎货"),
  ;
  
  private String statusCode;
  
  private String desc;
  
  WarehouseOrderStatus(String statusCode, String desc){
    this.statusCode = statusCode;
    this.desc = desc;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getDesc() {
    return desc;
  }

  public static WarehouseOrderStatus parseEnum(String statusCode){
    for(WarehouseOrderStatus status : WarehouseOrderStatus.values()){
      if(status.getStatusCode().equals(statusCode)) return status;
    }
    return null;
  }
  
}
