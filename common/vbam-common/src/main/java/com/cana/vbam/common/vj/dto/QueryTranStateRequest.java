package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

import com.cana.vbam.common.vj.enums.TranType;

public class QueryTranStateRequest implements Serializable {

	private static final long serialVersionUID = 1190259275980444399L;

	private String vjTranSeq;
	
	private TranType tranType;

	public String getVjTranSeq() {
		return vjTranSeq;
	}

	public void setVjTranSeq(String vjTranSeq) {
		this.vjTranSeq = vjTranSeq;
	}

	public TranType getTranType() {
		return tranType;
	}

	public void setTranType(TranType tranType) {
		this.tranType = tranType;
	}
	
}
