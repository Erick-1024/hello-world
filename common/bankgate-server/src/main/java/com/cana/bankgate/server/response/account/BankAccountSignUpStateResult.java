package com.cana.bankgate.server.response.account;

import java.io.Serializable;
import java.util.List;

import com.cana.bankgate.server.response.BankBaseResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("stream")
public class BankAccountSignUpStateResult extends BankBaseResult implements Serializable{

	private static final long serialVersionUID = -2271856630536052355L;

	@XStreamAlias("list")
	private List<BankAccountSignUpStateData> bankAccountSingUpStateDatas; // 账户签约状态数据

	public List<BankAccountSignUpStateData> getBankAccountSingUpStateDatas() {
		return bankAccountSingUpStateDatas;
	}

	public void setBankAccountSingUpStateDatas(
			List<BankAccountSignUpStateData> bankAccountSingUpStateDatas) {
		this.bankAccountSingUpStateDatas = bankAccountSingUpStateDatas;
	}
}
