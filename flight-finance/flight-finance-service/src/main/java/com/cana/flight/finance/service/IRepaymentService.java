package com.cana.flight.finance.service;

import java.math.BigDecimal;

public interface IRepaymentService {
	/**
	 * 根据真旅客户Id查询订单采购逾期率
	 * @param customerId
	 * @param period 最近period个月
 	 * @return
	 */
	public BigDecimal getOverdueRateBycustomerId(String customerId,int period);
	
	/**
	 * 根据真旅客户Id查询订单采购逾期次数
	 * @param customerId
	 * @param period 最近period个月
	 * @return
	 */
	public Integer getOverdueTimesBycustomerId(String customerId,int period);

	/**
	 * 根据真旅客户Id查询订单采购增长率（最近18个月至24个月之间）
	 * @param customerId
	 * @return
	 */
	public BigDecimal getGrowthRateByCustomerId(String customerId);

	/**
	 * 根据真旅客户Id查询订单采购逾期天数总和
	 * @param customerId
	 * @param overdueDays
	 * @param period 最近period个月
	 * @return
	 */
	public Integer getOverdueTimesBycustomerIdAndOverdueDays(String customerId,int period);
	
	/**
	 * 根据customerId计算合作期限
	 * @param customerId
	 * @return
	 */
	public Integer calculateCooperationPeriodByCustomerId(String customerId);
	
	/**
	 * 判断客户一段时间内每月的订票是否连续
	 * @param customerId
	 * @param period
	 * @return
	 */
	public boolean checkBillContinuous(String customerId,Integer period);
}

