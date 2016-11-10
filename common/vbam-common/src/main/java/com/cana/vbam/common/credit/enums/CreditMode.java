package com.cana.vbam.common.credit.enums;

/**
 * 授信方式，目前真旅项目仅用到了综合授信方式。
 * 
 * @author XuMeng
 *
 */
public enum CreditMode {

	SYNTHETICAL("综合授信"), SINGLE("单笔授信");

	private String desc;

	private CreditMode(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
