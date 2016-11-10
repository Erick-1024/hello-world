package com.cana.vbam.common.signature.enums;

/**
 * @author hu
 *
 */
public enum CertStatus {

	NODOWNLOAD("3", "未下载"),
	ACTIVATION("4", "激活"),
	FREEZE("5", "冻结"),
	REVOKED("6", "吊销");
	
	private String number;
	private String desc;
	
	private CertStatus(String number, String desc){
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
