package com.cana.credit.limit.dao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.vbam.common.credit.enums.CreditLimitStatus;

public class CreditLimitUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CreditLimitUtil.class);

	/**
	 * 获取可用的授信余额
	 * @param creditLimit
	 * @return
	 */
	public static long getAvailableCreditLimit(CreditLimit creditLimit){
		
		CreditLimitStatus limitStatus = CreditLimitStatus.valueOf(creditLimit.getStatus());
		
		if(limitStatus != CreditLimitStatus.NORMAL){
			logger.info("额度状态非正常:{}", limitStatus);
			return 0;
		}
		
		long availableCreditLimit = creditLimit.getTotalLimit() - creditLimit.getUsedLimit();
		return availableCreditLimit > 0 ? availableCreditLimit : 0;
	}
	
}
