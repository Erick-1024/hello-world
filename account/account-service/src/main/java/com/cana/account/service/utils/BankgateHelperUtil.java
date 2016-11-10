/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.account.service.utils;

import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.bankgate.enums.BaseTranStatus;

/**
 * @author ducer
 *
 */
public class BankgateHelperUtil {

	public static AccountTradeStatus parseStatus(BankTranStatus status) {
		BaseTranStatus base = status.toBaseStatus();
		if (BaseTranStatus.success.equals(base)) {
			return AccountTradeStatus.TRADE_SUCCESS;
		} else if (BaseTranStatus.fail.equals(base)) {
			return AccountTradeStatus.TRADE_FAIL;
		} else if (BaseTranStatus.handling.equals(base)) {
			return AccountTradeStatus.TRADE_HANDLING;
		} else {
			return AccountTradeStatus.TRADE_HANDLING;
		}
	}
}
