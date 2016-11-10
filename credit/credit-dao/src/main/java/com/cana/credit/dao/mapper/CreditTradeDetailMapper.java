package com.cana.credit.dao.mapper;

import java.util.List;

import com.cana.credit.dao.po.CreditTradeFlow;
import com.cana.credit.dao.po.CreditTradeRequest;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimitQueryCriteria;

public interface CreditTradeDetailMapper {
	/**
	 * 获取放款流水
	 * @return
	 */
	public List<CreditTradeFlow> getCreditLoanFlowList(CreditTradeRequest cRequest);
	/**
	 * 统计数目
	 * @param cRequest
	 * @return
	 */
	public int getCreditTradeFlowCount(CreditTradeRequest cRequest);

	/**
	 * 查询所有的客户在某段时间内内所使用的额度值
	 * @param creditUsedLimitQueryCriteria 查询条件
	 * @return
	 */
	public List<CreditUsedLimit> getCreditUsedLimit(CreditUsedLimitQueryCriteria creditUsedLimitQueryCriteria);
	
	/**
	 * 获取对账单（没有分页）
	 * @param cRequest
	 * @return
	 */
	public List<CreditTradeFlow> getCreditStatementNoPaging(CreditTradeRequest cRequest);
	
}
