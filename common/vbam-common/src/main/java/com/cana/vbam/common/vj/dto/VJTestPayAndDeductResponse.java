package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJTestPayAndDeductResponse implements Serializable{
	
	private static final long serialVersionUID = 2868156500467683951L;

	private Boolean switchState;
	
	private VJDeductResponse response;

	public VJDeductResponse getResponse() {
		return response;
	}

	public void setResponse(VJDeductResponse response) {
		this.response = response;
	}

	public Boolean getSwitchState() {
		return switchState;
	}

	public void setSwitchState(Boolean switchState) {
		this.switchState = switchState;
	}


}
