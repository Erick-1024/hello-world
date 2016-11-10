package com.cana.asset.service.transaction.util;

import com.cana.asset.dao.po.Credit;
import com.cana.vbam.common.asset.enums.CreditStatus;

/**
 * @author hu
 *
 */
public class CreditUtil {

	
	public static long getAvailableLimit(Credit credit){
		if(CreditStatus.NORMAL.name().equals(credit.getStatus()) && credit.getTotalLimit() > credit.getUsedLimit())
			return credit.getTotalLimit() - credit.getUsedLimit();
		return 0;
	}
}
