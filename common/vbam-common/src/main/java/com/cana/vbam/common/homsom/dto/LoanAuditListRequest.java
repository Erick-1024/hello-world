package com.cana.vbam.common.homsom.dto;

import java.util.List;

import com.cana.vbam.common.dto.Pagination;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.LoanState;

public class LoanAuditListRequest extends Pagination {

	private static final long serialVersionUID = -1187380245026260644L;

	private String startDate;
	
	private String endDate;
	
	private List<LoanState> loanStates;
	
	private Channel channel;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<LoanState> getLoanStates() {
		return loanStates;
	}

	public void setLoanStates(List<LoanState> loanStates) {
		this.loanStates = loanStates;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
