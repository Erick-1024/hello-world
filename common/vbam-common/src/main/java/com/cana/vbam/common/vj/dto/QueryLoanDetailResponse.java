package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class QueryLoanDetailResponse extends BaseResponse {

	private static final long serialVersionUID = -1828257607403646982L;

	private QueryLoanDetailResponseData data;

	public QueryLoanDetailResponseData getData() {
		return data;
	}

	public void setData(QueryLoanDetailResponseData data) {
		this.data = data;
	}
	
}
