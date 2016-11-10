package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.vj.enums.TranType;

public class QueryVJTranCriteria {

	private String vjTranSeq;
	
	private String canaTranSeq;
	
	private TranType tranType;
	
	public String getVjTranSeq() {
		return vjTranSeq;
	}

	public void setVjTranSeq(String vjTranSeq) {
		this.vjTranSeq = vjTranSeq;
	}

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}

	public TranType getTranType() {
		return tranType;
	}

	public void setTranType(TranType tranType) {
		this.tranType = tranType;
	}
	
}
