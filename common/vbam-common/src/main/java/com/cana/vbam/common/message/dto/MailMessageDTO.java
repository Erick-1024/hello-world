package com.cana.vbam.common.message.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cana.vbam.common.message.enums.MailContentType;

public class MailMessageDTO implements Serializable{
	private static final long serialVersionUID = 4346931332283756404L;
	private String from;
	private String receiver;
	private String subject;
	private String content;
	private String template;
	private MailContentType contentType;
	private List<String> attachments;
	private Map<String, String> images;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public MailContentType getContentType() {
		return contentType;
	}

	public void setContentType(MailContentType contentType) {
		this.contentType = contentType;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public Map<String, String> getImages() {
		return images;
	}

	public void setImages(Map<String, String> images) {
		this.images = images;
	}

}
