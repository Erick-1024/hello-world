package com.cana.flight.finance.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.flight.finance.common.dto.DailySales;
import com.cana.flight.finance.common.dto.DailySalesQueryCriteria;
import com.cana.flight.finance.dao.mapper.DailyBillCustomMapper;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.flight.finance.dao.po.DailyBill;
import com.cana.flight.finance.dao.po.DailyBillExample;
import com.cana.flight.finance.service.IDailyBillService;

@Service
public class DailyBillServiceImpl implements IDailyBillService {

	@Resource
	private DailyBillMapper dailyBillMapper;
	
	@Resource
	private DailyBillCustomMapper dailyCustomMapper;
	
	@Override
	public Map<String, Long> getFlightTicketSales(List<String> customerIds, String date10) {
		DailyBillExample dailyBillExample = new DailyBillExample();
		dailyBillExample.createCriteria().andCustomerIdIn(customerIds).andDateEqualTo(date10);
		List<DailyBill> dailyBills = dailyBillMapper.selectByExample(dailyBillExample);
		Map<String, Long> returnValue = new HashMap<>();
		for (DailyBill dailyBill : dailyBills)
			returnValue.put(dailyBill.getCustomerId(), dailyBill.getPrice());
		return returnValue;
	}

	@Override
	public List<DailySales> getDailySales(String startMonth, String endMonth, List<String> customerIds, int dayNumber) {
		DailySalesQueryCriteria dailySalesQueryCriteria = new DailySalesQueryCriteria();
		dailySalesQueryCriteria.setStartMonth(startMonth);
		dailySalesQueryCriteria.setEndMonth(endMonth);
		dailySalesQueryCriteria.setCustomerIds(customerIds);
		dailySalesQueryCriteria.setDayNumber(dayNumber);
		return dailyCustomMapper.getDailySales(dailySalesQueryCriteria);
	}
	
}
