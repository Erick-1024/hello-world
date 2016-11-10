package com.cana.flight.finance.service.utils;

import java.util.Date;
import java.util.List;

import com.cana.flight.finance.common.dto.CustomerSaleDataVO;

public interface IFlightFinanceServiceHelper {

	/**
	 * 获取客户的月平均销售数据
	 * 比如 结束年月为2016-09-06,月份数为2 那么获取到的是2016-08,2016-07月份的月平均销售数据
	 * @param tzCustomerId 客户id
	 * @param endMonth 结束年月（不包含）
	 * @param period 月份数（比如）
	 * @return
	 */
	public List<CustomerSaleDataVO> getMonthAverageSales(String tzCustomerId,Date endMonth,int period);
	
}
