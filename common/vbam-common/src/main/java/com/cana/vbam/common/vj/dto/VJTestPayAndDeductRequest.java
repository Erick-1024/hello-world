package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJTestPayAndDeductRequest implements Serializable{
	
	private static final long serialVersionUID = 2868156500467683951L;

	private Boolean switchState;
	
	private String retCode;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public Boolean getSwitchState() {
		return switchState;
	}

	public void setSwitchState(Boolean switchState) {
		this.switchState = switchState;
	}


}
