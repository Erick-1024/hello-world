package com.cana.marketing.dao.mapper;

import java.util.List;

import com.cana.marketing.dao.po.MarketingActivity;
import com.cana.vbam.common.marketing.activity.dto.QueryMarketingActivityCriteria;

public interface IMarketingActivityMapper {

	List<MarketingActivity> queryMarketingActivityList(QueryMarketingActivityCriteria criteria);
}
