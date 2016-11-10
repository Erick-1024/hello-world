package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class PrepareRepaymentResponse extends BaseResponse{
	
	private static final long serialVersionUID = -6905900986785153042L;

	private PrepareRepaymentResponseData data;

	public PrepareRepaymentResponseData getData() {
		return data;
	}

	public void setData(PrepareRepaymentResponseData data) {
		this.data = data;
	}
	

}
