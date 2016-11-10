package com.cana.vbam.common.homsom.dto;

import com.cana.vbam.common.dto.Pagination;
import com.cana.vbam.common.homsom.enums.Channel;

public class TicketListRequest extends Pagination {

	private static final long serialVersionUID = -3628284453528753373L;

	/**
     *代理商名称
     */
    private String agentName;
    
    /**
     *出票日期，格式:yyyy-MM-dd
     */
    private String issueDateStart;
	
    /**
     *出票日期，格式:yyyy-MM-dd
     */
    private String issueDateEnd;

    private Channel channel;
    
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getIssueDateStart() {
		return issueDateStart;
	}

	public void setIssueDateStart(String issueDateStart) {
		this.issueDateStart = issueDateStart;
	}

	public String getIssueDateEnd() {
		return issueDateEnd;
	}

	public void setIssueDateEnd(String issueDateEnd) {
		this.issueDateEnd = issueDateEnd;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
    
}
