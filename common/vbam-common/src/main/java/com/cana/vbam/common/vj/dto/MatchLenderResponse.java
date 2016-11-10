package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

public class MatchLenderResponse extends BaseResponse{
	

	private static final long serialVersionUID = -2845521271411323743L;
	
	private  MatchLenderResponseData data;

	public MatchLenderResponseData getData() {
		return data;
	}

	public void setData(MatchLenderResponseData data) {
		this.data = data;
	}


}
