package com.cana.vbam.common.message.enums;

public enum SmsMessageChannelType {
	
	YUNPIAN("云片"),
	DINGDONG("叮咚");

	private String desc;

	private SmsMessageChannelType(String desc) {
		this.setDesc(desc);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
