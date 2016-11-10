package com.cana.vbam.common.signature.enums;

/**
 * @author hu
 *
 */
public enum SignType {

	ATTACH("带原文"),
	DETACH("分离式");
	
	private String desc;
	
	private SignType(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
