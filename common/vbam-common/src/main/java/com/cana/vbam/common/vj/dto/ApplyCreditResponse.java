package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class ApplyCreditResponse extends BaseResponse{
	
	private static final long serialVersionUID = 2043499287892348248L;

	private  ApplyCreditResponseData data;

	public ApplyCreditResponseData getData() {
		return data;
	}

	public void setData(ApplyCreditResponseData data) {
		this.data = data;
	}

}
