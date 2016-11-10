package com.cana.yundaex.service.transaction;

import java.math.BigDecimal;

/**
 * @author hu
 *
 */
public interface IYundaexInterestRateTransactionService {

	public void computeAndSaveInterestRate(String customerId);
	
	/**
	 * 根据客户ID，评级分数生成 利率json字符串
	 * @param customerId 客户ID
	 * @param score 客户评级分数
	 * @return
	 */
	public String getInterestRate(String customerId, BigDecimal score);
	
	/**
	 * 根据客户ID修改利率
	 * @param customerId 用户ID
	 * @param interestRate 利率json字符串
	 */
	public void updateInterestRate(String customerId, String interestRate);
	
}
