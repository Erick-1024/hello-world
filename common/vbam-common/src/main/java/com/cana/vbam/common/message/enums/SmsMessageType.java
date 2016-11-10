package com.cana.vbam.common.message.enums;

public enum SmsMessageType {
	NOTICE("通知类"),
	CODE("验证码类");

	private String desc;

	private SmsMessageType(String desc) {
		this.setDesc(desc);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
