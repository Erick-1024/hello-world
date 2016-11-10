package com.cana.message.mongo.entity;

import org.joda.time.DateTime;

import com.cana.vbam.common.message.enums.MessageType;
import com.travelzen.framework.vo.AbstractVO;
import com.travelzen.framework.web.util.MemberReflectionUtils;

public class SmsMessage extends AbstractVO {

	private static final long serialVersionUID = -4385793034091271173L;

	private String receivePhoneNum;

	private String content;

	private MessageType msgType;

	private boolean sendState;

	private String failReason;

	private DateTime sendDateTime;

	public SmsMessage() {

	}

	public SmsMessage(SmsMessage history) {
		MemberReflectionUtils.cloneModel(history, this);
	}

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

	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	public boolean isSendState() {
		return sendState;
	}

	public void setSendState(boolean sendState) {
		this.sendState = sendState;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public DateTime getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(DateTime sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

}
