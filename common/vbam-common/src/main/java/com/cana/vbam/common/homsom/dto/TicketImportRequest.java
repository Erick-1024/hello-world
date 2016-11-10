package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class TicketImportRequest implements Serializable{
	
	private static final long serialVersionUID = -1991919832415603146L;
	private String version;
	private String channel;
	private String sign;
	private String tickets;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTickets() {
		return tickets;
	}
	public void setTickets(String tickets) {
		this.tickets = tickets;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}

}
