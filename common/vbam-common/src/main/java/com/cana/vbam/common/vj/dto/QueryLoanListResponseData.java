package com.cana.vbam.common.vj.dto;

import java.io.Serializable;
import java.util.List;

public class QueryLoanListResponseData implements Serializable {

	private static final long serialVersionUID = 1190259275980444399L;
	
	private boolean hasNext;
	
	private List<LoanInformation> loanList;//放款信息列表
	
	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<LoanInformation> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<LoanInformation> loanList) {
		this.loanList = loanList;
	}
	
}
