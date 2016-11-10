package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class QueryPayStateRequest implements Serializable {

	private static final long serialVersionUID = -7217221302071877734L;

	private String vjTranSeq;

	public String getVjTranSeq() {
		return vjTranSeq;
	}

	public void setVjTranSeq(String vjTranSeq) {
		this.vjTranSeq = vjTranSeq;
	}
	
}
