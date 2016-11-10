package com.cana.vbam.common.credit.openapi;

import java.util.List;

public class LoanListResponse extends TravelzenBaseResponse {
 
	private static final long serialVersionUID = 1L;

	private Long total;//数据总数
	
	private List<LoanInfoDetailVo> loanInfos;//数据

	public LoanListResponse(){}
	
	public LoanListResponse(Long total,List<LoanInfoDetailVo> loanInfos){
		this.total = total;
		this.loanInfos = loanInfos;
	}
	
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<LoanInfoDetailVo> getLoanInfos() {
		return loanInfos;
	}

	public void setLoanInfos(List<LoanInfoDetailVo> loanInfos) {
		this.loanInfos = loanInfos;
	}

	
}
