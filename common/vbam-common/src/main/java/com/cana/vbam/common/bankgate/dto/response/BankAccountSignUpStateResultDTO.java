package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.List;

public class BankAccountSignUpStateResultDTO extends BankBaseResultDTO implements Serializable{

	private static final long serialVersionUID = 269935415776706060L;
	
	private List<BankAccountSignUpStateDataDTO> bankAccountSingUpStateDatas;

	public List<BankAccountSignUpStateDataDTO> getBankAccountSingUpStateDatas() {
		return bankAccountSingUpStateDatas;
	}

	public void setBankAccountSingUpStateDatas(List<BankAccountSignUpStateDataDTO> bankAccountSingUpStateDatas) {
		this.bankAccountSingUpStateDatas = bankAccountSingUpStateDatas;
	}
}
