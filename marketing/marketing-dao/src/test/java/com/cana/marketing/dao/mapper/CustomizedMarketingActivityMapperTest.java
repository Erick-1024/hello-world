package com.cana.marketing.dao.mapper;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.vbam.common.marketing.activity.dto.QueryMarketingActivityCriteria;
import com.cana.vbam.common.marketing.activity.enums.MarketingActivityType;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-marketing-dao-datasource.xml")
public class CustomizedMarketingActivityMapperTest {
	
	@Resource
	private IMarketingActivityMapper mapper;

	@Test
	public void queryMarketingActivityList(){
		QueryMarketingActivityCriteria criteria = new QueryMarketingActivityCriteria();
		criteria.setCurDate(DateTimeUtil.parseDate10("2016-04-24").toDate());
		criteria.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		criteria.setType(MarketingActivityType.INTEREST_RATE_DISCOUNT.name());
		System.out.println(new Gson().toJson(mapper.queryMarketingActivityList(criteria)));
	}

}
