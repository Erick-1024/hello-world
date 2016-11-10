package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class QueryDeductStateRequest implements Serializable{
	
	private static final long serialVersionUID = -5948083360679771700L;
	
	private String canaTranSeq;

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}

}
