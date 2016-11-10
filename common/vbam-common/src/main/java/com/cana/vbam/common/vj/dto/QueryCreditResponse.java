package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class QueryCreditResponse extends BaseResponse{
	
	private static final long serialVersionUID = 319074659927228869L;

	private  QueryCreditResponseData data;

	public QueryCreditResponseData getData() {
		return data;
	}

	public void setData(QueryCreditResponseData data) {
		this.data = data;
	}


}
