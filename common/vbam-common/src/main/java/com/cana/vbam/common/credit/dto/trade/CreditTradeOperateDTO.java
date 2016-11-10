package com.cana.vbam.common.credit.dto.trade;

import java.io.Serializable;

public class CreditTradeOperateDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fromAccountNo;
	
	private String fromAccountName;
	
	private String toAccountNo;
	
	private String toAccountName;
	
	private String toFee;

	public String getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public String getFromAccountName() {
		return fromAccountName;
	}

	public void setFromAccountName(String fromAccountName) {
		this.fromAccountName = fromAccountName;
	}

	public String getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(String toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public String getToAccountName() {
		return toAccountName;
	}

	public void setToAccountName(String toAccountName) {
		this.toAccountName = toAccountName;
	}

	public String getToFee() {
		return toFee;
	}

	public void setToFee(String toFee) {
		this.toFee = toFee;
	}
	
	

}
