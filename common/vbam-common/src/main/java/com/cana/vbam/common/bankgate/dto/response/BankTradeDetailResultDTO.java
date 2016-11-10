/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * 所有的附属帐号都叫accountNo，主帐号叫mainAccountNo
 */
public class BankTradeDetailResultDTO extends BankBaseResultDTO implements Serializable {

	private static final long serialVersionUID = -2081792502092693459L;

	private List<BankTradeDetailDataDTO> bankTradeDetailDatas;

	public List<BankTradeDetailDataDTO> getBankTradeDetailDatas() {
		return bankTradeDetailDatas;
	}

	public void setBankTradeDetailDatas(List<BankTradeDetailDataDTO> bankTradeDetailDatas) {
		this.bankTradeDetailDatas = bankTradeDetailDatas;
	}

}
