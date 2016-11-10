package com.cana.credit.service;

import java.util.List;

import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitDTO;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitQueryDTO;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;

public interface ICreditLimitAuditService {

	/**
	 * 查询客户某个时间前使用掉的额度
	 * @param date 时间点（不包括）
	 * @param institution 机构
	 * @return
	 */
	public List<CreditUsedLimit> queryUsedLimit(CreditUsedLimitQueryDTO creditUsedLimitQueryDTO);
	
	
	/**
	 * 查询韵达项目使用额度接口
	 * @param creditUsedLimitQueryDTO
	 * @return
	 */
	public List<CreditUsedLimitDTO> queryYundaUsedLimit(CreditUsedLimitQueryDTO creditUsedLimitQueryDTO);
	
	public int queryYundaUsedLimitCount(CreditUsedLimitQueryDTO creditUsedLimitQueryDTO);
}
