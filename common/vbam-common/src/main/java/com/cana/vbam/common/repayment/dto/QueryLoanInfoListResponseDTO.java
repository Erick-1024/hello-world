package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.List;

public class QueryLoanInfoListResponseDTO implements Serializable{

	private static final long serialVersionUID = 6625267949443631204L;
	
	// 总的放款信息条数
	private int totalNum;

	private List<LoanInfoDetail> loanInfoDetailList;

	public List<LoanInfoDetail> getLoanInfoDetailList() {
		return loanInfoDetailList;
	}

	public void setLoanInfoDetailList(List<LoanInfoDetail> loanInfoDetailList) {
		this.loanInfoDetailList = loanInfoDetailList;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
}
