package com.cana.early.warning.service.transaction;

import com.cana.vbam.common.report.dto.MonitorMetricYundaDTO;

public interface IYdEarlyWarningEventTransactionService {
	
	/**
	 * 计算韵达系统预警事件
	 * @param overduePlans 累计逾期次数
	 * @param monitorMetricData 监控指标数据
	 * @param memberId 用户ID
	 * @param outCustomerId 外部客户ID
	 * @param productId 产品ID
	 * @param companyName 公司名称
	 */
	public void reckonYdEarlyWarningSystemEvent(int overduePlans, MonitorMetricYundaDTO monitorMetricData, MonitorMetricYundaDTO lastMonitorMetricData, String memberId, String outCustomerId, String productId, String companyName, boolean isByMonth);
	
}
