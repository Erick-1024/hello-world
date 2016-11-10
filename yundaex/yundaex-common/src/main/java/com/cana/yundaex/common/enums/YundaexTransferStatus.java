/**
 * 
 */
package com.cana.yundaex.common.enums;

/**
 * @author guguanggong
 *
 */
public enum YundaexTransferStatus {

	SUCCESS("成功"), HANDING("交易中"), FAIL("失败");

	private String desc;

	private YundaexTransferStatus(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
