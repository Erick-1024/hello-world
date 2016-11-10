package com.cana.report.service.transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cana.common.dao.po.Properties;
import com.cana.report.dao.po.ReportMonitorMetric;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;

public interface IMonitorReportTransactionService {

	/**
	 * 生成销售监控数据，同时生成销售回款率和销售变化率。
	 * @param monitorMoneyDTOs 销售总金额
	 * @param dailySales 360天日均销售额
	 * @param yesterdayStr10 昨日:yyyy-MM-dd
	 * @param outCustomerId2MemberIdMap 外部客户ID和Cana平台ID对照
	 * @param properties 配置
	 * @param productId 产品ID
	 * @return 返回数据是否生成成功
	 */
	public boolean generateTotalSalesData(List<MonitorMoneyDTO> monitorMoneyDTOs, List<MonitorMoneyDTO> dailySales,  String yesterdayStr10, Map<String, String> outCustomerId2MemberIdMap, Properties properties, String productId);
	
	/**
	 * 生成合格AR余额监控数据，同时生成某个时间前使用的额度、质押反担保覆盖率
	 * @param monitorMoneyDTOs 合格AR余额
	 * @param creditUsedLimits 已使用的额度
	 * @param yesterdayStr10 昨日:yyyy-MM-dd
	 * @param properties 配置
	 * @param productId 产品ID
	 * @return 返回数据是否生成成功
	 */
	public boolean generateQualifiedARData(List<MonitorMoneyDTO> monitorMoneyDTOs, List<CreditUsedLimit> creditUsedLimits,  String yesterdayStr10, Properties properties, String productId);
	
	/**
	 * 生成真旅当日客票起飞金额
	 * @param monitorMoneyDTOs 当日客票已起飞金额
	 * @param yesterdayStr10 昨日:yyyy-MM-dd
	 * @param properties 配置
	 * @param productId 产品ID
	 * @return 返回数据是否生成成功
	 */
	public boolean generateTicketTakeOffSalesData(List<MonitorMoneyDTO> monitorMoneyDTOs, String yesterdayStr10, Properties properties, String productId);


	/**
	 * 韵达监控 数据保存
	 * @param reportMonitorMetric
	 */
	public void save(BigDecimal param, String memberId, String stationNo, String yundaexAssetProjectId, String type, String currentDate);
	
	
}
