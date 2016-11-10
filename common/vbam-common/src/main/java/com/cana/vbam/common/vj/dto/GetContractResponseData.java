package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class GetContractResponseData implements Serializable {

	private static final long serialVersionUID = 7473621803164934232L;

	private String contract;

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}
	
}
