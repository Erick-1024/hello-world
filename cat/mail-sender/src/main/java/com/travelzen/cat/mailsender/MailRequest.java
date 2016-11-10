package com.travelzen.cat.mailsender;

public class MailRequest {
	private String to; // 收件人
	private String from; // 发件人
	private String host; // smtp主机
	private String username = ""; // 发件人的账号
	private String password = ""; // 发件人密码
	private String subject; // 邮件主题
	private String content; // 邮件正文
	private String successAck; // 发送成功后的返回的ACK
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getSuccessAck() {
		return successAck;
	}
	public void setSuccessAck(String successAck) {
		this.successAck = successAck;
	}
}
