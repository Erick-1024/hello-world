package com.cana.report.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cana.report.dao.po.ReportFactorFinanceCount;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceYear;

public interface IReportFactorFinanceSummarySearchMapper {
	
	/**
	 * 查询日报表(查询用)
	 * @param startTime
	 * @param endTime
	 * @param masterId
	 * @return
	 */
	public List<ReportFactorFinanceDay> getSummaryReportDayByCondition(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("masterId")String masterId, @Param("page")int page, @Param("pageSize")int pageSize);
	
	/**
	 * 查询日报表(统计&导出用)
	 * @param startTime
	 * @param endTime
	 * @param masterId
	 * @return
	 */
	public List<ReportFactorFinanceDay> countSummaryReportDayByCondition(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("masterId")String masterId);

	/**
	 * 查询年报表(查询用)
	 * @param startTime
	 * @param endTime
	 * @param masterId
	 * @return
	 */
	public List<ReportFactorFinanceYear> getSummaryReportYearByCondition(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("masterId")String masterId, @Param("page")int page, @Param("pageSize")int pageSize);

	/**
	 * 查询年报表(统计&导出用)
	 * @param startTime
	 * @param endTime
	 * @param masterId
	 * @return
	 */
	public List<ReportFactorFinanceYear> countSummaryReportYearByCondition(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("masterId")String masterId);
	
	/**
	 * 查询计数报表(查询用)
	 * @param startTime
	 * @param endTime
	 * @param masterId
	 * @return
	 */
	public List<ReportFactorFinanceCount> getSummaryReportCountByCondition(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("masterId")String masterId, @Param("page")int page, @Param("pageSize")int pageSize);
	
	/**
	 * 查询计数报表(统计&导出用)
	 * @param startTime
	 * @param endTime
	 * @param masterId
	 * @return
	 */
	public List<ReportFactorFinanceCount> countSummaryReportCountByCondition(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("masterId")String masterId);
}
