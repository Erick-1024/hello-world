package com.cana.marketing.server.api.impl;

import javax.annotation.Resource;

import com.cana.marketing.api.IInterestRateApi;
import com.cana.marketing.service.transaction.IMarketingActivityTransactionService;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscount;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountRequest;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountResponse;

public class InterestRateApiImpl implements IInterestRateApi{
	
	@Resource
	private IMarketingActivityTransactionService marketingActivityTransactionService;

	@Override
	public InterestRateDiscount getInterestRateDiscountInfo(InterestRateDiscountRequest request) {
		return marketingActivityTransactionService.getInterestRateDiscountInfo(request);
	}

	@Override
	public InterestRateDiscountResponse getInterestRateAfterDiscount(InterestRateDiscountRequest request) {
		return marketingActivityTransactionService.getInterestRateAfterDiscount(request);
	}

}
