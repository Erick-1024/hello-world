package com.cana.report.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cana.report.dao.po.ReportMonitorMetric;
import com.cana.vbam.common.report.dto.MonitorDataData;
import com.cana.vbam.common.report.dto.MonitorMetricData;
import com.cana.vbam.common.report.dto.MonitorMetricDataYunda;
import com.cana.vbam.common.report.dto.MonitorMetricYundaDTO;
import com.cana.vbam.common.report.enums.ReportMonitorDataType;
import com.cana.vbam.common.report.enums.ReportMonitorMetricType;

public interface IReportMonitorService {

	public void saveMonitorData(String memberId, String outCustomerId, Long price, ReportMonitorDataType reportMonitorDataType, String date10, String productId);
	
	public void saveMonitorMetric(String memberId, String outCustomerId, BigDecimal proportion, ReportMonitorMetricType reportMonitorMetricType, String date10, String productId);
	
	/**
	 * 根据条件获取监控数据
	 * @param outCustomerIds 用户列表
	 * @param reportMonitorDataType 数据类型
	 * @param date 数据统计日期，yyyy-MM-dd
	 * @return &lt;outCustomerId, amount>
	 */
	public Map<String, Long> getMonitorData(List<String> outCustomerIds, ReportMonitorDataType reportMonitorDataType, String date, String productId);
	
	/**
	 * 根据条件查询各个指标的值(按监控生成时间升序排序)(某个日期不存在任何一个指标记录时返回的列表中缺少改天记录)
	 * @param memberId 用户ID
	 * @param outCustomerId 外部客户ID
	 * @param productId 产品ID
	 * @param startDate 起始统计时间（包括）yyyy-MM-dd
	 * @param endDate 结束统计时间（包括）yyyy-MM-dd
	 * @return
	 */
	public List<MonitorMetricData> queryMonitorMetric(String memberId, String outCustomerId, String productId, String startDate, String endDate);
	
	/**
	 * 根据条件查询各个数据的值(按监控生成时间升序排序)(某个日期不存在任何一个指标记录时返回的列表中缺少改天记录)
	 * @param memberId 用户ID
	 * @param outCustomerId 外部客户ID
	 * @param productId 产品ID
	 * @param startDate 起始统计时间（包括）yyyy-MM-dd
	 * @param endDate 结束统计时间（包括）yyyy-MM-dd
	 * @return
	 */
	public List<MonitorDataData> queryMonitorData(String memberId, String outCustomerId, String productId, String startDate, String endDate);

	/**
	 * 韵达监控 
	 * @param reportMonitorMetric
	 */
	public void saveMonitorMetric(ReportMonitorMetric reportMonitorMetric);
	
	/**
	 * 查询监控表信息
	 * @param productId
	 * @return
	 */
	public List<MonitorMetricDataYunda> queryMonitorMetric(String productId);

	/**
	 * 根据条件查询各个指标的值(按监控生成时间升序排序)(某个日期不存在任何一个指标记录时返回的列表中缺少改天记录)
	 * @param memberId 用户ID
	 * @param outCustomerId 外部客户ID
	 * @param productId 产品ID
	 * @param startDate 起始统计时间（包括）yyyy-MM
	 * @param endDate 结束统计时间（包括）yyyy-MM
	 * @return
	 */
	public List<MonitorMetricDataYunda> queryYundaMonitorMetric(String memberId, String productId, String startDate, String endDate);
	
	
	
	/**
	 * 根据条件查询当月监控列表数据（如果当月没有监控数据则查询上个月监控数据）
	 * @param memberId
	 * @param productId
	 * @param date
	 * @return
	 */
	public List<MonitorMetricDataYunda> queryYundaexMonitorMetric(String memberId, String productId, String date);
	
	/**
	 * 给预警提供查询监控数据接口
	 * @param productId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<MonitorMetricYundaDTO>queryYundaMonitorMetricEarlyWarning(String memberId,String productId,String startDate, String endDate);
	
}
