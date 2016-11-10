package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;
import com.cana.vbam.common.vj.enums.TranState;

public class DeductResponse extends BaseResponse{
	
	private static final long serialVersionUID = 108898239586488439L;

	private TranState state;
	
	public TranState getState() {
		return state;
	}

	public void setState(TranState state) {
		this.state = state;
	}

}
