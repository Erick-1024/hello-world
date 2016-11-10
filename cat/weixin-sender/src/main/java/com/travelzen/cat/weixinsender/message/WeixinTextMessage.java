package com.travelzen.cat.weixinsender.message;

public class WeixinTextMessage {
	public class Text{
		private String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
	private String touser;
	private String toparty;
	private String totag;
	private String msgtype = MsgType.TEXT.name();
	private String agentid;
	private Text text;
	private String safe;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getTotag() {
		return totag;
	}
	public void setTotag(String totag) {
		this.totag = totag;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public Text getText() {
		if(null == text)
			return text = new Text();
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}
}
