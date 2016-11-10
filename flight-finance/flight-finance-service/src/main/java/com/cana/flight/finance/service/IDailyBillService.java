package com.cana.flight.finance.service;

import java.util.List;
import java.util.Map;

import com.cana.flight.finance.common.dto.DailySales;

public interface IDailyBillService {

	/**
	 * 查询客户的在某一天内的销售额
	 * @param customerIds 真旅网的客户ID列表
	 * @param date10 日期
	 * @return &lt;outCustomerId, sales>
	 */
	public Map<String, Long> getFlightTicketSales(List<String> customerIds, String date10);
	
	/**
	 * 获取日均销售额
	 * @param startMonth 开始年月（包含）
	 * @param endMonth 结束年月（不包含）
	 * @param customerIds 客户列表
	 * @param dayNumber 天数
	 * @return
	 */
	public List<DailySales> getDailySales(String startMonth, String endMonth, List<String> customerIds, int dayNumber);
	
}
