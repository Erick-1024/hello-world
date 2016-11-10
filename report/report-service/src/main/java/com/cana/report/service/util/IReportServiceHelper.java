package com.cana.report.service.util;

import com.cana.report.dao.po.ReportMonitorData;

public interface IReportServiceHelper {

	/**
	 * 根据memberId和date对report_monitor_data表加锁
	 * @param memberId
	 * @param outCustomerId
	 * @param date
	 * @param type
	 * @return
	 */
	public ReportMonitorData lockReportMonitorDataByMemberIdAndDateAndType(String memberId, String outCustomerId, String date, String type);

	/**
	 * 生成监控数据的主键id
	 * @return
	 */
	public String generateReportMonitorDataId();
	
	/**
	 * 生成监控指标的主键id
	 * @return
	 */
	public String generateReportMonitorMetricId();
}
