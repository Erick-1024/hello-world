package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.List;

public class ActiveRepaymentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7672155626257866099L;

	private String accountTotalMoney;
	
	private List<String> accounts;

	public String getAccountTotalMoney() {
		return accountTotalMoney;
	}

	public void setAccountTotalMoney(String accountTotalMoney) {
		this.accountTotalMoney = accountTotalMoney;
	}

	public List<String> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}
	
}
