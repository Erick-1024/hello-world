package com.travelzen.cat.weixinsender;

public class WeixinRequest {
	private StringBuilder connection_url = new StringBuilder("https://qyapi.weixin.qq.com/cgi-bin/gettoken");//建立连接的url
	private StringBuilder send_url = new StringBuilder("https://qyapi.weixin.qq.com/cgi-bin/message/send");//发送消息的url
	private String touser;//收件人
	private String corpid;//企业号id
	private String corpsecret;//企业号管理组密钥
	private String access_token;//全局唯一票据
	private String agentid;//应用id
	private String content;//微信文本
	private String safe;//消息是否保密
	private String successAck;//发送成功后的返回的ACK
	
	
	public String getConnection_url() {
		return new String(connection_url.append("?corpid=" ).append(getCorpid()).append("&corpsecret=").append(getCorpsecret()));
	}
	public String getSend_url() {
		return new String(send_url.append("?access_token=").append(getAccess_token()));
	}
	public String getTouser() {
		return touser.replace(",", "|");
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getCorpid() {
		return corpid;
	}
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public String getCorpsecret() {
		return corpsecret;
	}
	public void setCorpsecret(String corpsecret) {
		this.corpsecret = corpsecret;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}
	public String getSuccessAck() {
		return successAck;
	}
	public void setSuccessAck(String successAck) {
		this.successAck = successAck;
	}
}
