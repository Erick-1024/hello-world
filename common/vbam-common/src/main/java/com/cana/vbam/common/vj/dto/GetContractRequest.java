package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class GetContractRequest implements Serializable {

	private static final long serialVersionUID = 293929764101642747L;

	private String vjTranSeq;

	public String getVjTranSeq() {
		return vjTranSeq;
	}

	public void setVjTranSeq(String vjTranSeq) {
		this.vjTranSeq = vjTranSeq;
	}
	
}
