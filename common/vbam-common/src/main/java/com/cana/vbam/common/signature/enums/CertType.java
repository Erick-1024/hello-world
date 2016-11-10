package com.cana.vbam.common.signature.enums;

/**
 * @author hu
 * 复合证书为两类型证书以"-"相连,例如:1-1
 */
public enum CertType {

	SINGLE_CERT("1", "单证"),
	DOUBLE_CERT("2", "双证"),
	ENCRYPT_CERT("3", "加密单证");
	
	private String number;
	
	private String desc;
	
	private CertType(String number, String desc){
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
