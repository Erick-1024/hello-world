/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.credit.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.cana.credit.dao.po.CreditTrade;
import com.cana.credit.dao.po.CreditTransfer;
import com.cana.credit.dao.po.CustomerApply;

/**
 * @author ducer
 *
 */
public interface CreditTableLockMapper {

	
	CustomerApply lockCustomerApplyById(String id);
	
	CreditTrade lockCreditTradeByTradeNoAndInstitution(@Param("tradeNo") String tradeNo, @Param("institution") String institution);
	
	CreditTransfer lockCreditTransferById(String id);
}
