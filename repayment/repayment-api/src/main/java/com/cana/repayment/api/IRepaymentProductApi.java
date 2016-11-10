package com.cana.repayment.api;

/**
 * 获取产品信息
 * @author XuMeng
 *
 */
public interface IRepaymentProductApi {
	
	/**
	 * 是否存在该产品
	 * @param productId 产品ID
	 * @return
	 */
	public boolean isExistProduct(String productId);
	
}
