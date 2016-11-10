package com.cana.vbam.common.credit.enums;

public enum NoticeScene {

	TZ_CUSTOMER_APPLY("客户申请"),
	ACCESS_AUTOMATIC("自动准入"),
	GENERATE_LIMIT("生成额度"),
	LIMIT_ACTIVE("额度激活");
	
	private String desc;
	
	private NoticeScene(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
