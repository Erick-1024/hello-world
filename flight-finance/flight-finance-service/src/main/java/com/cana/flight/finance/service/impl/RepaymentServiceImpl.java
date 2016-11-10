package com.cana.flight.finance.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.flight.finance.dao.mapper.DailyBillCustomMapper;
import com.cana.flight.finance.dao.mapper.RepaymentCustomMapper;
import com.cana.flight.finance.dao.mapper.gen.RepaymentMapper;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dao.po.DailyBillExample;
import com.cana.flight.finance.dao.po.RepaymentExample;
import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import com.cana.flight.finance.service.IRepaymentService;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class RepaymentServiceImpl implements IRepaymentService {
	
	private static final Logger logger = LoggerFactory.getLogger(RepaymentServiceImpl.class);
	@Resource
	private RepaymentMapper repaymentMapper;
	
	@Resource
	private RepaymentCustomMapper repaymentCustomMapper;

	@Resource
	private DailyBillCustomMapper dailyBillCustomMapper;
	
	@Resource
	private TzCustomerInfoMapper tzCustomerInfoMapper;

	/**
	 * 根据真旅客户Id查询订单采购逾期率
	 * @param customerId
	 * @param period 最近period个月
 	 * @return
	 */
	@Override
	public BigDecimal getOverdueRateBycustomerId(String customerId,int period) {
		String repaymentDateBegin = dateCalculateAndFormat(period,0);
		String repaymentDateEnd = dateCalculateAndFormat(0,1);
		return getOverdueRate(customerId,repaymentDateBegin,repaymentDateEnd);
	}

	/**
	 * 根据真旅客户Id查询订单采购逾期次数
	 * @param customerId
	 * @param period 最近period个月
	 * @return
	 */
	@Override
	public Integer getOverdueTimesBycustomerId(String customerId,int period) {
		String repaymentDateBegin = dateCalculateAndFormat(1,0);
		String repaymentDateEnd = dateCalculateAndFormat(0,1);
		int overdueTimes = repaymentCustomMapper.countOverdue(customerId, repaymentDateBegin, repaymentDateEnd);
		return new Integer(overdueTimes);
	}
	
	/**
	 * 根据真旅客户Id查询订单采购逾期天数总和
	 * @param customerId
	 * @param overdueDays
	 * @param period 最近period个月
	 * @return
	 */
	@Override
	public Integer getOverdueTimesBycustomerIdAndOverdueDays(String customerId,int period) {
		String repaymentDateBegin = dateCalculateAndFormat(period,0);
		String repaymentDateEnd = dateCalculateAndFormat(0,1);
		Integer overdueDays = repaymentCustomMapper.sumOverdueDays(customerId, repaymentDateBegin, repaymentDateEnd);
		return overdueDays;
	}
	
	/**
	 * 根据真旅客户Id查询订单采购增长率（最近18个月至24个月之间）
	 * @param customerId
	 * @return
	 */
	@Override
	public BigDecimal getGrowthRateByCustomerId(String customerId) {
		Integer cooperationPeriod = calculateCooperationPeriodByCustomerId(customerId);
		String farBeginDate = dateCalculateAndFormat(cooperationPeriod>26 ? 24:(cooperationPeriod-2));
		String farEndDate = dateCalculateAndFormat(cooperationPeriod>26 ? 12:(cooperationPeriod-14));
		DailyBillExample farExample = new DailyBillExample();
		farExample.createCriteria().andCustomerIdEqualTo(customerId).andDateGreaterThanOrEqualTo(farBeginDate).andDateLessThan(farEndDate);
		Long farDailyBillTotalPrice = dailyBillCustomMapper.sumPriceByExample(farExample);
		if(farDailyBillTotalPrice == null || farDailyBillTotalPrice==0)
			return null;
		
		String nearBeginDate = dateCalculateAndFormat(12);
		String nearEndDate = dateCalculateAndFormat(0);
		DailyBillExample nearExample = new DailyBillExample();
		nearExample.createCriteria().andCustomerIdEqualTo(customerId).andDateGreaterThanOrEqualTo(nearBeginDate).andDateLessThan(nearEndDate);
		Long nearDailyBillTotalPrice = dailyBillCustomMapper.sumPriceByExample(nearExample);
		if(nearDailyBillTotalPrice == null)
			return null;
		
		logger.info("客户id为：{},近12个月的销售总额为：{}，远12个月的销售总额为：{}",customerId,farDailyBillTotalPrice,nearDailyBillTotalPrice);
		BigDecimal far = new BigDecimal(farDailyBillTotalPrice);
		BigDecimal near = new BigDecimal(nearDailyBillTotalPrice);
		return near.subtract(far).divide(far,5,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 当前时间 - 指定月份,指定天数 并转换成yyyy-MM-dd格式
	 * 2016年1月16日 - 0月，0天 = 2016-01-16
	 * 2016年1月16日 - 1月，0天 = 2015-12-16
	 * 2016年1月16日 - 0月，1天 = 2016-01-15
	 * @param minusMonths
	 * @return
	 */
	private String dateCalculateAndFormat(int minusMonths,int minusDays){
		Date vaildDate = new DateTime().minusMonths(minusMonths).minusDays(minusDays).toDate();
		String vaildDateFormat = DateTimeUtil.format(vaildDate, "yyyy-MM-dd");
		return vaildDateFormat;
	}

	/**
	 * 当前时间 - 指定月份 并转换成当月的第一天，yyyy-MM-dd格式
	 * 2016年1月16日 - 0 = 2016-01-01
	 * 2016年1月16日 - 1 = 2015-12-01
	 * @param minusMonths
	 * @return
	 */
	private String dateCalculateAndFormat(int minusMonths){
		Date vaildDate = new DateTime().minusMonths(minusMonths).toDate();
		Date vaildDatetruncate = DateTimeUtil.truncate(vaildDate, Calendar.MONTH);
		String vaildDateFormat = DateTimeUtil.format(vaildDatetruncate, "yyyy-MM-dd");
		return vaildDateFormat;
	}
	
	/**
	 * 根据真旅客户ID获取指定时间段内的逾期率
	 * @param customerId
	 * @param repaymentDateBegin
	 * @param repaymentDateEnd
	 * @return
	 */
	private BigDecimal getOverdueRate(String customerId,String repaymentDateBegin,String repaymentDateEnd){
		RepaymentExample example = new RepaymentExample();
		example.createCriteria().andCustomerIdEqualTo(customerId).andRepaymentDateGreaterThanOrEqualTo(repaymentDateBegin).andRepaymentDateLessThan(repaymentDateEnd);
		int totalTimes = repaymentMapper.countByExample(example);
		if(totalTimes == 0)
			return new BigDecimal(0);
		int overdueTimes = repaymentCustomMapper.countOverdue(customerId, repaymentDateBegin, repaymentDateEnd);
		BigDecimal total = new BigDecimal(totalTimes);
		BigDecimal overdue = new BigDecimal(overdueTimes);
		return overdue.divide(total,5,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 根据customerId计算合作期限
	 * @param customerId
	 * @return
	 */
	@Override
	public Integer calculateCooperationPeriodByCustomerId(String customerId){
		TzCustomerInfoExample example = new TzCustomerInfoExample();
		example.createCriteria().andTzCustomerIdEqualTo(customerId);
		List<TzCustomerInfo> tzCustomerInfos = tzCustomerInfoMapper.selectByExample(example);
		return calculatePeriodMonth(tzCustomerInfos.get(0).getFirstBusinessTime());
		
	}
	
	/**
	 * 计算当前时间与指定日期相差月份
	 * 2016年1月1日 与 2015年12月31日 算为2个月
	 * @param date
	 * @return
	 */
	private Integer calculatePeriodMonth(String date){
		Date d = DateTimeUtil.truncate(DateTimeUtil.parseDate10(date).toDate(), Calendar.MONTH);
		Date nowDate = DateTimeUtil.truncate(new Date(), Calendar.MONTH);
		int months = Months.monthsBetween(new DateTime(d), new DateTime(nowDate)).getMonths(); 
		return new Integer(months+1);
	}

	/**
	 * 判断客户一段时间内每月的订票是否连续
	 * @param customerId
	 * @param period 包含最初的那个月和当前月 
	 * 例如 period为5，现在是2016-09-01，那么判断的是2016-08月，2016-07月，2016-06月 这三个月是否连续
	 * @return
	 */
	@Override
	public boolean checkBillContinuous(String customerId, Integer period) {
		String beginDate;
		String endDate;
		for(int i=0;i<period-2;i++){
			beginDate = dateCalculateAndFormat(i+1);
			endDate = dateCalculateAndFormat(i);
			DailyBillExample example = new DailyBillExample();
			example.createCriteria().andCustomerIdEqualTo(customerId).andDateGreaterThanOrEqualTo(beginDate).andDateLessThan(endDate);
			Long dailyBillTotalPrice = dailyBillCustomMapper.sumPriceByExample(example);
			if(dailyBillTotalPrice == null || dailyBillTotalPrice <= 0)
				return false;
		}
		return true;
	}
}
