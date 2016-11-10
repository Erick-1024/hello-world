/**
 * 
 */
package com.cana.yundaex.common.enums;

/**
 * @author guguanggong
 *
 */
public enum YundaexLimitStatus {

	NORMAL("正常"),
	FREEZE("冻结"),
	DISABLED("停用"),
	INACTIVE("未激活");

	private String desc;

	private YundaexLimitStatus(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
