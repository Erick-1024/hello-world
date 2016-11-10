package com.cana.early.warning.service.transaction;

import java.util.List;

import com.cana.vbam.common.early.warning.dto.CanelEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.report.dto.MonitorMetricData;

public interface IEarlyWarningEventTransactionService {
	
	/**
	 * 计算系统预警事件
	 * @param overduePlans 累计逾期次数
	 * @param continueOverduePlans 连续逾期次数
	 * @param monitorMetricDatas 监控指标数据
	 * @param memberId 用户ID
	 * @param outCustomerId 外部客户ID
	 * @param productId 产品ID
	 * @param companyName 公司名称
	 */
	public void reckonEarlyWarningSystemEvent(int overduePlans, int continueOverduePlans, List<MonitorMetricData> monitorMetricDatas, String memberId, String outCustomerId, String productId, String companyName);
	
	/**
	 * 提交人工生成的预警事件
	 * @param earlyWarningManualEventDTO
	 */
	public void submitEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO);
	
	/**
	 * 取消预警事件
	 * @param canelEarlyWarningEventRequest
	 */
	public void cancelEarlyWarningEvent(CanelEarlyWarningEventRequest canelEarlyWarningEventRequest);
	
}
