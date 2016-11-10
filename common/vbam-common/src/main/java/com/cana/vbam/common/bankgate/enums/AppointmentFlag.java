/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 预约标志
 * 
 * @author ducer
 *
 */
public enum AppointmentFlag {

  unappointment("0","非预约"),
  appointment("1","预约"),
  ;
  
  private String code;
  private String desc;

  AppointmentFlag(String code,String desc){
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
  
  public static AppointmentFlag parseEnum(String code){
    for(AppointmentFlag flag : AppointmentFlag.values()){
      if(flag.code.equals(code)) return flag;
    }
    return null;
  }
}
