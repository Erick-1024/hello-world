package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class QueryTranStateResponse extends BaseResponse {

	private static final long serialVersionUID = 5728826074930789117L;
	
	private QueryTranStateResponseData data;

	public QueryTranStateResponseData getData() {
		return data;
	}

	public void setData(QueryTranStateResponseData data) {
		this.data = data;
	}

}
