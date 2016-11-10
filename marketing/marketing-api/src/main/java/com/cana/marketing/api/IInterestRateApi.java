package com.cana.marketing.api;

import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscount;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountRequest;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountResponse;

public interface IInterestRateApi {
	/**
	 * 获取利率折扣信息
	 * @param request
	 * @return
	 */
	public InterestRateDiscount getInterestRateDiscountInfo(InterestRateDiscountRequest request);
	
	/**
	 * 获取打完折后的利率
	 * @param request
	 * @return
	 */
	public InterestRateDiscountResponse getInterestRateAfterDiscount(InterestRateDiscountRequest request);
}