package com.cana.flight.finance.service.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.flight.finance.common.dto.CustomerSaleDataVO;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.flight.finance.dao.po.DailyBill;
import com.cana.flight.finance.dao.po.DailyBillExample;
import com.travelzen.framework.core.time.DateTimeUtil;

@Component
public class FlightFinanceServiceHelper implements IFlightFinanceServiceHelper {

	@Resource
	private DailyBillMapper dailyBillMapper;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<CustomerSaleDataVO> getMonthAverageSales(String tzCustomerId, Date endMonth,int period) {
		List<CustomerSaleDataVO> saleDatas = new ArrayList<>();
		logger.info("获取近一年的销售数据,tz_customer_id为{},申请时间为{}",tzCustomerId,DateTimeUtil.formatDate(endMonth));
		DateTime applyDateTime = new DateTime(endMonth);
		
		DateTime startDateTime = null; 
		DateTime endDateTime = applyDateTime.minusMonths(period).withDayOfMonth(1);
		long saleMoney = 0l;
		
		for(int i = period-1; i >= 0; i--){
			startDateTime = endDateTime;
			endDateTime = applyDateTime.minusMonths(i).withDayOfMonth(1);
			logger.info("开始时间为{},结束时间为{}",DateTimeUtil.date10(startDateTime),DateTimeUtil.date10(endDateTime.minusDays(1)));
			saleMoney = getTotalSaleData(tzCustomerId,DateTimeUtil.date10(startDateTime),DateTimeUtil.date10(endDateTime.minusDays(1)));
			CustomerSaleDataVO saleData = new CustomerSaleDataVO();
			saleData.setYear(startDateTime.getYear());
			saleData.setMonth(startDateTime.getMonthOfYear());
			saleData.setSaleMoney(saleMoney);
			saleDatas.add(saleData);
			logger.info("销售额为{}分",saleData.getSaleMoney());
		}
		return saleDatas;
	}
	
	/**
	 * 获取客户指定时间内总销售额
	 * @param customerId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private long getTotalSaleData(String tzCustomerId,String startTime,String endTime){
		DailyBillExample ex = new DailyBillExample();
		ex.createCriteria().andCustomerIdEqualTo(tzCustomerId).andDateBetween(startTime, endTime);
		List<DailyBill> dailyBills = dailyBillMapper.selectByExample(ex);
		
		long totalSaleData = 0l;
		for (DailyBill bill : dailyBills) {
			totalSaleData += bill.getPrice();
		}
		return totalSaleData;
	}

}
