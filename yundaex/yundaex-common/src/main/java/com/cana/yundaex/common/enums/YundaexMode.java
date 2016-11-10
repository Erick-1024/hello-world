/**
 * 
 */
package com.cana.yundaex.common.enums;

/**
 * @author guguanggong
 *
 */
public enum YundaexMode {

	SYNTHETICAL("综合授信"),
	SINGLE("单笔授信");
	
	private String desc;
	
	private YundaexMode(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
