package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class QueryInterestResponse extends BaseResponse {

	private static final long serialVersionUID = 7868525140511326003L;

	private QueryInterestResponseData data;

	public QueryInterestResponseData getData() {
		return data;
	}

	public void setData(QueryInterestResponseData data) {
		this.data = data;
	}
	
}
