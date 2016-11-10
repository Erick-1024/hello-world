package com.cana.report.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.cana.vbam.common.report.dto.ReportFundMonthlySearchCriteria;

public interface ReportFundMonthlySumMapper{

	/**
	 * 计算当月所有账户余额
	 * @param reportDate
	 * @return
	 */
	public Long getCurrentMonthAccountBalanceSum(@Param("searchCriteria") ReportFundMonthlySearchCriteria searchCriteria);
}
