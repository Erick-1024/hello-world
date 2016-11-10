package com.cana.marketing.dao.mapper.gen;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.marketing.dao.mapper.gen.MarketingActivityMapper;
import com.cana.marketing.dao.po.MarketingActivity;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscount;
import com.cana.vbam.common.marketing.activity.enums.InterestRateDiscountType;
import com.cana.vbam.common.marketing.activity.enums.MarketingActivityType;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-marketing-dao-datasource.xml")
public class MarketingActivityMapperTest {
	
	@Resource
	private MarketingActivityMapper mapper;

	@Test
	public void upInsert(){
		
		final String ID = "travelzen_finance_promotion";
		MarketingActivity activity = new MarketingActivity();
		activity.setId(ID);
		activity.setType(MarketingActivityType.INTEREST_RATE_DISCOUNT.name());
		activity.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		activity.setStartDate(DateTimeUtil.parseDate10("2016-04-15").toDate());
		activity.setEndDate(DateTimeUtil.parseDatetime14("20160424235959").toDate());
		InterestRateDiscount discount = new InterestRateDiscount();
		discount.setType(InterestRateDiscountType.RATIO);
		discount.setRatio(new BigDecimal("0.8"));
		discount.setStartDate(activity.getStartDate());
		discount.setEndDate(activity.getEndDate());
		discount.setActivityId(ID);
		activity.setDetail(new Gson().toJson(discount));
		activity.setUsed(true);
		if (mapper.selectByPrimaryKey(ID) == null) {
			mapper.insert(activity);
		} else {
			mapper.updateByPrimaryKey(activity);
		}
	}

}
