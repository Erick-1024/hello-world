package com.cana.flight.finance.dao.mapper;

import java.util.List;

import com.cana.flight.finance.common.dto.DailySales;
import com.cana.flight.finance.common.dto.DailySalesQueryCriteria;
import com.cana.flight.finance.dao.po.DailyBillExample;

public interface DailyBillCustomMapper {
	
	Long sumPriceByExample(DailyBillExample example);
	
	List<DailySales> getDailySales(DailySalesQueryCriteria dailySalesQueryCriteria);
	
}