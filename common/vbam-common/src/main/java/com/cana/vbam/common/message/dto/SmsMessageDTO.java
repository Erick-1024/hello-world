package com.cana.vbam.common.message.dto;

import java.io.Serializable;

import com.cana.vbam.common.message.enums.SmsMessageType;

public class SmsMessageDTO implements Serializable{

	private static final long serialVersionUID = 2962084960369096345L;

	private String receivePhoneNum;
	
	private String content;
	
	private SmsMessageType smsMessageType;
	
	private String prefix;
	
	private String templateId;
	
	public String getReceivePhoneNum() {
		return receivePhoneNum;
	}

	public void setReceivePhoneNum(String receivePhoneNum) {
		this.receivePhoneNum = receivePhoneNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SmsMessageType getSmsMessageType() {
		return smsMessageType;
	}

	public void setSmsMessageType(SmsMessageType smsMessageType) {
		this.smsMessageType = smsMessageType;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}
