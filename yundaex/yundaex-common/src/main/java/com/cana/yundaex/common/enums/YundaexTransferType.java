/**
 * 
 */
package com.cana.yundaex.common.enums;

/**
 * @author guguanggong
 *
 */
public enum YundaexTransferType {
	LOAD("放款");
	
	private String desc;
	
	private YundaexTransferType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
