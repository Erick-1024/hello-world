package com.cana.vbam.common.wechat.account;

import java.io.Serializable;
import java.util.List;

public class AccountWechatDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8481164049851693721L;

	private String accountBalanceTotal;
	
	private String NoSupervisionAccountBalanceTotal;
	
	private int NoSupervisionAccountNumber;
	
	private String HaveSupervisionAccountBalanceTotal;
	
	private int HaveSupervisionAccountNumbe;
	
	private List<AccountWechatDetailDTO> AccountDetailDTOs;

	public String getAccountBalanceTotal() {
		return accountBalanceTotal;
	}

	public void setAccountBalanceTotal(String accountBalanceTotal) {
		this.accountBalanceTotal = accountBalanceTotal;
	}

	public String getNoSupervisionAccountBalanceTotal() {
		return NoSupervisionAccountBalanceTotal;
	}

	public void setNoSupervisionAccountBalanceTotal(String noSupervisionAccountBalanceTotal) {
		NoSupervisionAccountBalanceTotal = noSupervisionAccountBalanceTotal;
	}

	public int getNoSupervisionAccountNumber() {
		return NoSupervisionAccountNumber;
	}

	public void setNoSupervisionAccountNumber(int noSupervisionAccountNumber) {
		NoSupervisionAccountNumber = noSupervisionAccountNumber;
	}

	public String getHaveSupervisionAccountBalanceTotal() {
		return HaveSupervisionAccountBalanceTotal;
	}

	public void setHaveSupervisionAccountBalanceTotal(String haveSupervisionAccountBalanceTotal) {
		HaveSupervisionAccountBalanceTotal = haveSupervisionAccountBalanceTotal;
	}

	public int getHaveSupervisionAccountNumbe() {
		return HaveSupervisionAccountNumbe;
	}

	public void setHaveSupervisionAccountNumbe(int haveSupervisionAccountNumbe) {
		HaveSupervisionAccountNumbe = haveSupervisionAccountNumbe;
	}

	public List<AccountWechatDetailDTO> getAccountDetailDTOs() {
		return AccountDetailDTOs;
	}

	public void setAccountDetailDTOs(List<AccountWechatDetailDTO> accountDetailDTOs) {
		AccountDetailDTOs = accountDetailDTOs;
	}
	
	
	
}
