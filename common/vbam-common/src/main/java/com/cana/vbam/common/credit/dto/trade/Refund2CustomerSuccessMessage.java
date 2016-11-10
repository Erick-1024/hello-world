package com.cana.vbam.common.credit.dto.trade;

import java.io.Serializable;

public class Refund2CustomerSuccessMessage implements Serializable{
	
	private static final long serialVersionUID = -945874450386538428L;
	// 转入账号
	private String toAccountNo;
	// 转入账户名
	private String toAccountName;
	// 退款金额
	private long amount;
	
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
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	

}
