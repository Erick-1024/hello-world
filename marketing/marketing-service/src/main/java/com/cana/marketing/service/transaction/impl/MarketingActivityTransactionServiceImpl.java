package com.cana.marketing.service.transaction.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.cana.marketing.dao.mapper.IMarketingActivityMapper;
import com.cana.marketing.dao.po.MarketingActivity;
import com.cana.marketing.service.transaction.IMarketingActivityTransactionService;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscount;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountRequest;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountResponse;
import com.cana.vbam.common.marketing.activity.dto.QueryMarketingActivityCriteria;
import com.cana.vbam.common.marketing.activity.enums.MarketingActivityType;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;

@Service
public class MarketingActivityTransactionServiceImpl implements IMarketingActivityTransactionService{
	
	@Resource
	private IVbamCommonService  commonService;
	
	@Resource
	private IMarketingActivityMapper customizedMarketingActivityMapper;

	@Override
	public InterestRateDiscount getInterestRateDiscountInfo(InterestRateDiscountRequest request) {
		QueryMarketingActivityCriteria criteria = interestRateDiscountRequest2QueryMarketingActivityCriteria(request);
		List<MarketingActivity> activities = customizedMarketingActivityMapper.queryMarketingActivityList(criteria);
		if(CollectionUtils.isEmpty(activities)){
			return null;
		}else{
			return new Gson().fromJson(activities.get(0).getDetail(), InterestRateDiscount.class);
		}
	}

	@Override
	public InterestRateDiscountResponse getInterestRateAfterDiscount(InterestRateDiscountRequest request) {
		checkRequest4getInterestRateAfterDiscount(request);
		QueryMarketingActivityCriteria criteria = interestRateDiscountRequest2QueryMarketingActivityCriteria(request);
		List<MarketingActivity> activities = customizedMarketingActivityMapper.queryMarketingActivityList(criteria);
		InterestRateDiscountResponse response = new InterestRateDiscountResponse();
		response.setInterestRateUnit(request.getOriginInterestRateUnit());
		if(CollectionUtils.isEmpty(activities)){
			response.setInterestRate(request.getOriginInterestRate());
			return response;
		}else{
			InterestRateDiscount discount = new Gson().fromJson(activities.get(0).getDetail(), InterestRateDiscount.class);
			response.setDiscountInfo(discount);
			switch(discount.getType()){
			case RATIO:
				response.setInterestRate(MoneyArithUtil.roundInterestRate(MoneyArithUtil.mul(request.getOriginInterestRate(), discount.getRatio())));
			case AMOUNT:
				BigDecimal interestRate = MoneyArithUtil.roundInterestRate(MoneyArithUtil.sub(request.getOriginInterestRate(), discount.getAmount()));
				if(interestRate.doubleValue() < 0)
					interestRate = new BigDecimal(0);
				response.setInterestRate(interestRate);
			}
			return response;
		}
	}
	
	private void checkRequest4getInterestRateAfterDiscount(InterestRateDiscountRequest request) {
		if(request.getOriginInterestRateUnit() == null)
			throw WebException.instance("originInterestRateUnit不能为null");
		if(request.getOriginInterestRate() == null)
			throw WebException.instance("originInterestRate不能为null");
		if(request.getOriginInterestRate().doubleValue() < 0)
			throw WebException.instance("originInterestRate的值不能小于0");
	}

	private QueryMarketingActivityCriteria interestRateDiscountRequest2QueryMarketingActivityCriteria(InterestRateDiscountRequest request){
		QueryMarketingActivityCriteria criteria = new QueryMarketingActivityCriteria();
		criteria.setType(MarketingActivityType.INTEREST_RATE_DISCOUNT.name());
		criteria.setProductId(request.getProductId());
		criteria.setCurDate(DateTimeFormat.forPattern("yyyy-MM-ddHH:mm").parseDateTime(commonService.getCurrentDate() + commonService.getCurrentTime()).toDate());
		return criteria;
	}

}
