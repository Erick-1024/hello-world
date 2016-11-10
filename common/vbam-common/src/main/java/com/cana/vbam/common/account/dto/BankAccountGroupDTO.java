/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author ducer
 *
 */
public class BankAccountGroupDTO implements Serializable {

	private static final long serialVersionUID = -5426833058687833828L;

	private String bankUserName; // 银行主账号登录用户名

	private List<String> mainAccountNos; // 银行主账号集合

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public List<String> getMainAccountNos() {
		return mainAccountNos;
	}

	public void setMainAccountNos(List<String> mainAccountNos) {
		this.mainAccountNos = mainAccountNos;
	}
}
