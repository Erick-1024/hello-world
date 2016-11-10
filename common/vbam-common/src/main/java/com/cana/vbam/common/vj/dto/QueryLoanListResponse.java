package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class QueryLoanListResponse extends BaseResponse {

	private static final long serialVersionUID = 5728826074930789117L;
	
	private QueryLoanListResponseData data;

	public QueryLoanListResponseData getData() {
		return data;
	}

	public void setData(QueryLoanListResponseData data) {
		this.data = data;
	}

}
