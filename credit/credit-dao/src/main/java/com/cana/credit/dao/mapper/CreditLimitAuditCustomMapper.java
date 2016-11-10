package com.cana.credit.dao.mapper;

import java.util.List;

import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitQueryDTO;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;

public interface CreditLimitAuditCustomMapper {

	/**
	 * 查询客户某个时间前使用掉的额度
	 * @param date 时间点（不包括）
	 * @param institution 机构
	 * @return
	 */
	public List<CreditUsedLimit> queryUsedLimit(CreditUsedLimitQueryDTO creditUsedLimitQueryDTO);
	
}
