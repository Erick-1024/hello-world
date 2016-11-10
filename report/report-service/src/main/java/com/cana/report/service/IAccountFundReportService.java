/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service;

import java.util.List;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.report.dto.ReportAccountFundDTO;
import com.cana.vbam.common.report.dto.AccountFundYearReportQueryDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundCountDTO;
import com.cana.vbam.common.report.dto.AccountFundDailyReportQueryDTO;

/**
 * @author ducer
 *
 */
public interface IAccountFundReportService {

	/**
	 * 查询账户资金日报表
	 * @param query
	 * @return
	 */
	ListResult<ReportAccountFundDTO> queryAccountFundDailyReport(AccountFundDailyReportQueryDTO query);
	
	/**
	 * 查询账户资金年报表
	 * @param query
	 * @return
	 */
	ListResult<ReportAccountFundDTO> queryAccountFundYearReport(AccountFundYearReportQueryDTO query);
	
	/**
	 * 查询账户资金统计日报表
	 * @param query
	 * @return
	 */
	ListResult<ReportAccountFundCountDTO> queryAccountFundDailyCountReport(AccountFundDailyReportQueryDTO query);
	
	/**
	 * 导出资金日报表
	 * @param query
	 * @return
	 */
	List<ReportAccountFundDTO> exportAcountFundDailyReport(AccountFundDailyReportQueryDTO query);
	
	/**
	 * 导出资金汇总表
	 * @param query
	 * @return
	 */
	List<ReportAccountFundDTO> exportAcountFundYearReport(AccountFundYearReportQueryDTO query);
	
	/**
	 * 导出资金计数报
	 * @param query
	 * @return
	 */
	List<ReportAccountFundCountDTO> exportAcountFundDailyCountReport(AccountFundDailyReportQueryDTO query);
}
