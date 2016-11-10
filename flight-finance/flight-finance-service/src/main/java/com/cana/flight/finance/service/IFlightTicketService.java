package com.cana.flight.finance.service;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.report.dto.MonitorMoneyDTO;

public interface IFlightTicketService {

	/**
	 * 批量计算获得合格AR余额
	 * @param customerIds 真旅网的客户ID列表
	 * @param startRecordId 搜索开始位置
	 * @return &lt;outCustomerId, AR>
	 */
	public Map<String, Long> getQualifiedAR(List<String> customerIds, String startRecordId);
	
	/**
	 * 获取客户某一天内的起飞客票（不包括退票和被改签了的票）的价格(价格=（票面价+总税款）/总航班数×当天起飞的航班数)
	 * @param customerIds 真旅网的客户ID列表
	 * @param date10 日期:yyyy-MM-dd
	 * @param startRecordId 搜索开始位置
	 * @return &lt;outCustomerId, sales>
	 */
	public Map<String, Long> getFlightTakeOffSales(List<String> customerIds,  String date10, String startRecordId);
	
	/**
	 * 获取客户某一天内的起飞客票（不包括退票和被改签了的票）的价格(价格=（票面价+总税款）/总航班数×当天起飞的航班数)
	 * @param outCustomerId2MemberIdMap 真旅网的客户ID和cana平台用户ID对照列表
	 * @param date10 日期:yyyy-MM-dd
	 * @param startRecordId 搜索开始位置
	 * @return
	 */
	public List<MonitorMoneyDTO> getTakeOffSales(Map<String, String>  outCustomerId2MemberIdMap, String date10, String startRecordId);
	
}
