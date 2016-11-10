package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class ConfirmLoanResponse extends BaseResponse {

	private static final long serialVersionUID = 5368741429947626922L;

	private ConfirmLoanResponseData data;

	public ConfirmLoanResponseData getData() {
		return data;
	}

	public void setData(ConfirmLoanResponseData data) {
		this.data = data;
	}
	
}
