package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class GetContractResponse extends BaseResponse {

	private static final long serialVersionUID = -5973649323870318361L;

	private GetContractResponseData data;

	public GetContractResponseData getData() {
		return data;
	}

	public void setData(GetContractResponseData data) {
		this.data = data;
	}
	
}
