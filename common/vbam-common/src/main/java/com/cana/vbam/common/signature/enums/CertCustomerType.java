package com.cana.vbam.common.signature.enums;

/**
 * @author hu
 *
 */
public enum CertCustomerType {

	PERSONAL("1", "个人"),
	ENTERPRISE("2", "企业"),
	SERVER("3", "服务器");
	
	private String number;
	
	private String desc;
	
	private CertCustomerType(String number, String desc){
		this.number = number;
		this.desc = desc;
	}
	
	public String number(){
		return number;
	}
	
	public String desc(){
		return desc;
	}
}
