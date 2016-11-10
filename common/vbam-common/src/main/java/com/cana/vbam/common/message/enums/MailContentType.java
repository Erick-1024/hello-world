package com.cana.vbam.common.message.enums;

public enum MailContentType {

	PLAIN("text/plain;charset=utf-8"),
	HTML("text/html;charset=utf-8");

	private String desc;

	private MailContentType(String desc) {
		this.setDesc(desc);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
