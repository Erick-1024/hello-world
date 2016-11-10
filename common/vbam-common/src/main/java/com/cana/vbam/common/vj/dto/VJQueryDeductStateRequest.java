package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class VJQueryDeductStateRequest implements Serializable{
	
	private static final long serialVersionUID = 5767657910715736726L;

	private String canaTranSeq;

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}
	

}
