package com.cana.flight.finance.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cana.flight.finance.common.dto.DailySales;
import com.cana.vbam.common.credit.dto.apply.CustomerSaleDataDTO;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;

public interface IFlightFinanceApi {
	
	/**
	 * 查询客户的在某一天内的销售额
	 * @param customerIds 真旅网的客户ID列表
	 * @param date 日期
	 * @return &lt;outCustomerId, sales>
	 */
	@Deprecated
	public Map<String, Long> getFlightTicketSales(List<String> customerIds, Date date);
	
	/**
	 * 批量计算获得合格AR余额(有价值客票票的（票面价+总税款）/总航班数×未起飞的航班数)
	 * @param customerIds 真旅网的客户ID列表
	 * @param startRecordId 搜索开始位置
	 * @return &lt;outCustomerId, AR>
	 */
	@Deprecated
	public Map<String, Long> getQualifiedAR(List<String> customerIds, String startRecordId);
	
	/**
	 * 获取客户某一天内的起飞客票（不包括退票和被改签了的票）的价格
	 * @param customerIds 真旅网的客户ID列表
	 * @param date10 日期:yyyy-MM-dd
	 * @param startRecordId 搜索开始位置
	 * @return &lt;outCustomerId, sales>
	 * @see	IFlightFinanceApi#getTakeOffSales(Map, String, String)
	 */
	@Deprecated
	public Map<String, Long> getFlightTakeOffSales(List<String> customerIds,  String date10, String startRecordId);
	
	/**
	 * 获取客户某一天内的起飞客票（不包括退票和被改签了的票）的价格(价格=（票面价+总税款）/总航班数×当天起飞的航班数)
	 * @param outCustomerId2MemberIdMap 真旅网的客户ID和cana平台用户ID对照列表
	 * @param date10 日期:yyyy-MM-dd
	 * @param startRecordId 搜索开始位置
	 * @return
	 */
	public List<MonitorMoneyDTO> getTakeOffSales(Map<String, String>  outCustomerId2MemberIdMap, String date10, String startRecordId);

	/**
	 * 获取日均销售额
	 * @param startMonth 开始年月（包含）
	 * @param endMonth 结束年月（不包含）
	 * @param customerIds 客户列表
	 * @param dayNumber 天数
	 * @return
	 */
	public List<DailySales> getDailySales(String startMonth, String endMonth, List<String> customerIds, int dayNumber);
	
	/**
	 * 获取客户近一年的月平均销售数据（12个月）
	 * @param tzCustomerId 客户id
	 * @param endMonth 结束年月（不包含）
	 * @return
	 */
	public List<CustomerSaleDataDTO> getMonthAverageSales(String tzCustomerId,Date endMonth,int period);
	
}
