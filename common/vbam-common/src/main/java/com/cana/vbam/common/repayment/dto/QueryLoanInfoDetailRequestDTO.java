package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

/**
 * 查询放款信息详情请求
 * @author renshui
 *
 */
public class QueryLoanInfoDetailRequestDTO implements Serializable{

	private static final long serialVersionUID = -8534217386645717542L;
	// 放款信息id
	private String loanInfoId;

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

}
