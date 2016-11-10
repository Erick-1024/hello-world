package com.cana.message.mongo.entity;

import org.joda.time.DateTime;

import com.cana.vbam.common.message.enums.MessageType;
import com.travelzen.framework.vo.AbstractVO;
import com.travelzen.framework.web.util.MemberReflectionUtils;

public class MailMessageHistory extends AbstractVO {

	private static final long serialVersionUID = -4450537887517374369L;

	private DateTime sendDateTime;

	private String sendTo;

	private String title;

	private String content;

	private MessageType msgType;

	private boolean sendStatus;

	public MailMessageHistory() {

	}

	public MailMessageHistory(MailMessageHistory history) {
		MemberReflectionUtils.cloneModel(history, this);
	}

	public DateTime getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(DateTime sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(boolean sendStatus) {
		this.sendStatus = sendStatus;
	}

}
