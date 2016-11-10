/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * @author ducer
 *
 */
public enum AppointmentTime {

	  am_10("100000","10 a.m"),
	  
	  am_12("120000","12 a.m"),
	  
	  pm_14("140000","14 p.m"),
	  
	  pm_16("160000","16 p.m"),
	  ;
	  private String code;
	  private String desc;
	  AppointmentTime(String code,String desc){
	    this.code = code;
	    this.desc = desc;
	  }
	  
	  public String getCode() {
	    return code;
	  }

	  public String getDesc() {
	    return desc;
	  }

	  public static AppointmentTime parseEnum(String code){
	    for(AppointmentTime tag : AppointmentTime.values()){
	      if(tag.code.equals(code)) return tag;
	    }
	    return null;
	  }
}
