package com.cana.vbam.common.homsom.dto;

import com.cana.vbam.common.dto.Pagination;
import com.cana.vbam.common.homsom.enums.Channel;

public class LoanAuditDetailRequest extends Pagination {

	private static final long serialVersionUID = -5742059743988487254L;

	private String loanDate;
	
	private Channel channel;

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
