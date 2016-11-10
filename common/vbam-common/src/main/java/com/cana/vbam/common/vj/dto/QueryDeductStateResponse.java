package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;
import com.cana.vbam.common.vj.enums.TranState;

public class QueryDeductStateResponse extends BaseResponse{
	
	private static final long serialVersionUID = -2994962780484790859L;

	private TranState state;
	
	public TranState getState() {
		return state;
	}

	public void setState(TranState state) {
		this.state = state;
	}

}
