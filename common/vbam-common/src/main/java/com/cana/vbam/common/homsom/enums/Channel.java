package com.cana.vbam.common.homsom.enums;

/**
 * @author hu
 *
 */
public enum Channel {

	HOMSOM("恒顺"),
	SHFH("上海泛华");
	
	
	private String desc;
	
	private Channel(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
