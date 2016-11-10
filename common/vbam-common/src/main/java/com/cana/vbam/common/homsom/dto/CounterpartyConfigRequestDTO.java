package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

import com.cana.vbam.common.homsom.enums.Channel;

public class CounterpartyConfigRequestDTO implements Serializable{

	private static final long serialVersionUID = 8250628445540315203L;

	private Channel channel;
	
	private int page;
	
	private int pageSize;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
