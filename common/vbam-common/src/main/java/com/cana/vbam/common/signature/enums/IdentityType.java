package com.cana.vbam.common.signature.enums;

/**
 * @author hu
 * 共24种,只列出可能会用到的
 */
public enum IdentityType {

	RESIDENT_IDENTITY_CARD("0", "居民身份证"),
	PASSPORT("1", "护照"),
	MILITARY_ID_CARD("2", "军人身份证"),
	INDUSTRIAL_AND_COMMERCIAL_REGISTRATION_CERTIFICATE("3", "工商登记证"),
	TAX_REGISTRATION_CERTIFICATE("4", "税务等级证"),
	SOCIAL_SECURITY_CARD("6", "社会保障卡"),
	ORGANIZATION_CODE_CERTIFICATE("7", "组织机构代码证"),
	BUSINESS_LICENSE("8", "企业营业执照"),
	CORPORATE_CODE_CERTIFICATE("9", "法人代码证"),
	RESIDENCE_BOOKLET("E", "户口簿"),
	TEMPORARY_RESIDENT_IDENTITY_CARD("F", "临时居民身份证"),
	OTHER("Z", "其他");
	
	private String number;
	
	private String desc;
	
	private IdentityType(String number, String desc){
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
