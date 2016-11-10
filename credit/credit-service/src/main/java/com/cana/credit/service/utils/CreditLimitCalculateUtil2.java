package com.cana.credit.service.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.credit.dao.po.CustomerApply;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dao.po.DailyBill;
import com.cana.flight.finance.dao.po.DailyBillExample;
import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import com.cana.vbam.common.credit.dto.limit.CalculateLimitResult;
import com.cana.vbam.common.credit.dto.limit.CreditLimitRuleDTO;
import com.cana.vbam.common.credit.dto.limit.CreditLimitRuleNonWhiteDTO;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;


/**
 * 授信系统计算工具
 * @author ducer
 * @author XuMeng
 * @since 2016-6-12
 * @deprecated plz use {@link CreditLimitCalculateUtil3}
 */
public class CreditLimitCalculateUtil2 {

	private static final Logger logger = LoggerFactory.getLogger(CreditLimitCalculateUtil2.class);
	private static DailyBillMapper dailyBillMapper = SpringApplicationContext.getApplicationContext().getBean(DailyBillMapper.class);
	private static TzCustomerInfoMapper tzCustomerInfoMapper = SpringApplicationContext.getApplicationContext().getBean(TzCustomerInfoMapper.class);
	
	private static final List<CreditLimitRuleDTO> credit_limit_rule;
	private static final List<CreditLimitRuleNonWhiteDTO> credit_limit_rule_non_white;
	static {
		credit_limit_rule=Lists.newArrayList();
		credit_limit_rule.add(new CreditLimitRuleDTO("1","0.2","0.8"));
		credit_limit_rule.add(new CreditLimitRuleDTO("0.5","0.1","0.75"));
		credit_limit_rule.add(new CreditLimitRuleDTO("0.2","0.05","0.65"));
		credit_limit_rule.add(new CreditLimitRuleDTO("0","0","0.6"));
		credit_limit_rule.add(new CreditLimitRuleDTO("-100","-0.05","0.5"));
		
		credit_limit_rule_non_white=Lists.newArrayList();
		credit_limit_rule_non_white.add(new CreditLimitRuleNonWhiteDTO(12,17,"0.6"));
		credit_limit_rule_non_white.add(new CreditLimitRuleNonWhiteDTO(18,23,"0.8"));
		credit_limit_rule_non_white.add(new CreditLimitRuleNonWhiteDTO(24,25,"1.0"));
	}

	private static final String creditConfigFilePath = "properties/credit.properties";
	
	private static int getCreditLimitParamDayN() {
		return TopsConfReader.getConfContentForInt(creditConfigFilePath, "limit_param_dayN", ConfScope.R); // 天数N(实时去读)
	}
	
	private static long getCreditLimitRangeLower() {
		long limit = TopsConfReader.getConfContentForLong(creditConfigFilePath, "limit_range_lower", ConfScope.R);
		if (limit < 0)
			limit = 500000;
		return limit;
	}
	
	private static long getCreditLimitRangeUpper() {
		long limit = TopsConfReader.getConfContentForLong(creditConfigFilePath, "limit_range_upper", ConfScope.R);
		if (limit < 0)
			limit = 10000000;
		return limit;
	}
	
	private static int getCreditLimitUserDayN() {
		return TopsConfReader.getConfContentForInt(creditConfigFilePath, "limit_user_dayN", ConfScope.R); // 资金授信使用天数N(实时去读)
	}
	
	/**
	 * 计算授信额度
	 * @param customerApply
	 */
	public static CalculateLimitResult calculateCreditLimitByCustomerApplyId(CustomerApply customerApply) {
		if (customerApply == null) {
			throw WebException.instance("授信客户申请不存在");
		}
		String customerId = customerApply.getTzCustomerId();
		CalculateLimitResult calculateLimitResult = null;
		Transaction t = Cat.newTransaction("service", "额度计算");
		t.addData("tzCuscomter", customerId);
		try {
			if(customerApply.getInWhitelist()!=null && !customerApply.getInWhitelist()){
				calculateLimitResult = calculateNonWhiteCreditLimit(customerId);
				logger.info("使用额度计算——非白名单");
			}else{
				calculateLimitResult = calculateCreditLimit(customerId);
				logger.info("使用额度计算——白名单");
			}
			t.setStatus(Transaction.SUCCESS);
			Cat.logMetricForCount("额度计算成功");
		} catch (Exception e) {
			logger.error(customerId + "额度计算失败", e);
			t.setStatus(e);
			Cat.logError(e);
			Cat.logMetricForCount("额度计算失败");
			throw e;
		} finally {
			t.complete();
		}
		return calculateLimitResult;
	}

	public static CalculateLimitResult calculateCreditLimit(String tzCustomerId) {
		Pair<Long, Long> saleData = getTwoYearsSaleData(tzCustomerId);

		BigDecimal last = new BigDecimal(saleData.getRight());
		BigDecimal beforeLast = new BigDecimal(saleData.getLeft());
		BigDecimal growthRate = new BigDecimal(0);
		if (saleData.getLeft() != 0) {
			growthRate = last.subtract(beforeLast).divide(beforeLast, 4, RoundingMode.HALF_UP);
		}
		CreditLimitRuleDTO creditLimitRule = getLimitRuleByGrowthRate(growthRate);
		BigDecimal growthFinalRate = new BigDecimal(creditLimitRule.getSaleGrowthRateFinalValue());
		BigDecimal pledgeRate = new BigDecimal(creditLimitRule.getPledgeRate());
		
		Long average = saleData.getRight() / 12;
		Long limit = calculateLimit(average, growthFinalRate, pledgeRate);
		
		Long finalLimit = getFinalLimit(tzCustomerId, limit);
		
		CalculateLimitResult result = new CalculateLimitResult();
		result.setFinalLimit(finalLimit);
		result.setGrowthFinalRate(growthFinalRate);
		result.setPledgeRate(pledgeRate);
		return result;
	}
	
	/**
	 * 额度 =（近一年平均月销售额）/ 30 * 天数N * (1 + 销售增长率定值θ）* 质押率δ ；
	 */
	private static Long calculateLimit(Long average, BigDecimal growthFinalRate, BigDecimal pledgeRate) {
		int day = getCreditLimitParamDayN();
		logger.info("天数N的值为:{}",day);

		BigDecimal limit = new BigDecimal(average).divide(new BigDecimal(30), 4, RoundingMode.HALF_UP);
		limit = limit.multiply(new BigDecimal(day))
				.multiply( new BigDecimal(1).add(growthFinalRate) )
				.multiply(pledgeRate);

		long longLimit = limit.longValue();
		return longLimit < 0 ? 0 : longLimit;
	}

	private static CreditLimitRuleDTO getLimitRuleByGrowthRate(BigDecimal growthRate) {
		CreditLimitRuleDTO creditLimitRule = null;
		for (CreditLimitRuleDTO rule : credit_limit_rule) {
			if (growthRate.compareTo(new BigDecimal(rule.getSaleGrowthRateOfMonth())) > 0) {
				logger.info("增长率为:{},匹配到授信规则:{}", growthRate, new Gson().toJson(rule));// 测试用
				creditLimitRule = rule;
				break;
			}
		}
		if (creditLimitRule == null) {
			logger.info("客户同期销售额增长率低于零，为:{}，使用最低额度规则", growthRate);// 测试用
			creditLimitRule = credit_limit_rule.get(credit_limit_rule.size() - 1);
		}
		return creditLimitRule;
	}
	
	/**
	 * 返回客户近两年的销售数据
	 * @param customerId
	 * @return Pair类型，假如当前时间为2016-6-12日，则左值为2014年6月至2015年5月之间的销售额，右值为2015年6月至2016年5月之间的销售额
	 */
	private static Pair<Long, Long> getTwoYearsSaleData(String customerId) {
		DailyBillExample ex = new DailyBillExample();
		DailyBillExample.Criteria criteria = ex.createCriteria().andCustomerIdEqualTo(customerId);
		DateTime now = DateTime.now();
		String startTime = DateTimeUtil.date10(now.minusYears(2).withDayOfMonth(1));
		String endTime = DateTimeUtil.date10(now.withDayOfMonth(1).minusDays(1));
		criteria.andDateBetween(startTime, endTime);
		List<DailyBill> dailyBills = dailyBillMapper.selectByExample(ex);

		String midTime = DateTimeUtil.date10(now.minusYears(1).withDayOfMonth(1));
		Long left = 0l;
		Long right = 0l;
		
		for (DailyBill bill : dailyBills) {
			if (bill.getDate().compareTo(midTime) < 0) {
				left += bill.getPrice();
			} else {
				right += bill.getPrice();
			}
		}
		return Pair.of(left, right);
	}
	
	/**
	 * 计算最终额度
	 * 最终额度范围的上限和下限在配置文件中配置
	 * 额度进行向上舍入保留至千位
	 */
	private static Long getFinalLimit(String tzCustomerId, Long limit) {
		Long manualLimit = getManualConfigLimitByTzCustomerId(tzCustomerId);
		if (manualLimit != null) {
			logger.info("计算客户[{}]的额度为{}，优先配置文件中的额度为{}", tzCustomerId, limit, manualLimit);
			return manualLimit;
		}

		Long maxLimit = getCreditLimitRangeUpper();
		Long minLimit = getCreditLimitRangeLower();

		Long finalLimit = limit;
		if (finalLimit <= minLimit)
			finalLimit = minLimit;
		else if (finalLimit >= maxLimit)
			finalLimit = maxLimit;
		else
			finalLimit = new BigDecimal(finalLimit).setScale(-5, RoundingMode.CEILING).longValue();

		logger.info("计算客户[{}]的额度为{}，配置的最小额度为{}，最大额度为{}，最终额度为{}", tzCustomerId, limit, minLimit, maxLimit, finalLimit);
		return finalLimit;
	}
	
	/**
	 * 从配置文件中检查有没有对该客户设置业务给定的额度，如果有，则使用，负责返回null
	 */
	private static Long getManualConfigLimitByTzCustomerId(String customerId) {
		String content = TopsConfReader.getConfContent(creditConfigFilePath, "manual_config_limit", ConfScope.R);
		if (StringUtils.isBlank(content))
			return null;

		String[] items = content.split(",");
		for (String item : items) {
			if (StringUtils.isBlank(item))
				continue;
			String[] pair = item.split(":");
			if (pair.length != 2
					|| !StringUtils.trimToEmpty(pair[0]).equals(customerId)
					|| StringUtils.isBlank(pair[1]))
				continue;
			String limitStr = StringUtils.trim(pair[1]);
			try {
				return Long.valueOf(limitStr);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 计算非白名单的额度
	 * @param tzCustomerId
	 * @return
	 */
	public static CalculateLimitResult calculateNonWhiteCreditLimit(String tzCustomerId) {
		//12个月平均月销售额最小值Ft
		Long averageMin = getMonthAverageSaleDataMin(tzCustomerId);
		//资金授信使用天数N
		int dayN = getCreditLimitUserDayN();
		logger.info("授信资金使用天数N的值为:{}",dayN);
		//折扣率δ
		Integer cooperationPeriod = calculateCooperationPeriodByCustomerId(tzCustomerId);
		CreditLimitRuleNonWhiteDTO rule = getLimitRuleNonWhiteByCooperationPeriod(cooperationPeriod); 
		BigDecimal depositRate = new BigDecimal(rule.getDepositRate());
		logger.info("tz_customer_id为{},合作月份为{},折扣率为{}",tzCustomerId,cooperationPeriod,depositRate);
		
		//额度 =（12个月平均月销售额最小值Ft）/ 30 * 资金授信使用天数N * 折扣率δ ；
		BigDecimal limit = new BigDecimal(averageMin)
				.divide(new BigDecimal(30), 4, RoundingMode.HALF_UP)
				.multiply(new BigDecimal(dayN))
				.multiply(depositRate);

		long longLimit = limit.longValue() < 0 ? 0 : limit.longValue();
		logger.info("额度公式计算后的额度:{}",longLimit);
		
		Long finalLimit = getFinalLimit(tzCustomerId, longLimit);
		logger.info("最终额度:{}",finalLimit);
		
		CalculateLimitResult result = new CalculateLimitResult();
		result.setFinalLimit(finalLimit);
		return result;
	}
	
	/**
	 * 过去12个月去除最高值，最低值后的平均值A,过去12个月平均值B,两者取低---
	 * 假如当前时间为2016-6-12,
	 * A为2015年6月至2016年5月之间的销售额【月】平均值(10个月，去掉最高值和最低值),
	 * B为2015年6月至2016年5月之间的销售额【月】平均值（12个月）,
	 * 最终取A,B的最小值
	 * @param customerId
	 * @return Pair类型，
	 */
	private static long getMonthAverageSaleDataMin(String customerId) {
		logger.info("非白名单额度计算——计算月平均销售数据最低值,tz_customer_id为{}",customerId);
		DateTime now = DateTime.now();
		DateTime startDateTime = now.minusYears(1).withDayOfMonth(1); 
		DateTime endDateTime = now.minusMonths(11).withDayOfMonth(1);
		long temp = getTotalSaleData(customerId,DateTimeUtil.date10(startDateTime),DateTimeUtil.date10(endDateTime.minusDays(1)));
		logger.info("开始时间为{},结束时间为{},销售额为{}分",startDateTime,endDateTime,temp);
		long rightSum = temp;
		long saleDataMin = temp;
		long saleDataMax = temp;
		for(int i = 10;i >= 0 ;i--){
			startDateTime = endDateTime;
			endDateTime = now.minusMonths(i).withDayOfMonth(1);
			temp = getTotalSaleData(customerId,DateTimeUtil.date10(startDateTime),DateTimeUtil.date10(endDateTime.minusDays(1)));
			logger.info("开始时间为{},结束时间为{},销售额为{}分",startDateTime,endDateTime,temp);
			rightSum += temp;
			if(temp < saleDataMin)
				saleDataMin = temp;
			if(temp > saleDataMax)
				saleDataMax = temp;
		}
		
		long leftSum = rightSum - saleDataMin - saleDataMax;
		long leftAverage = leftSum / 10;
		long rightAverage = rightSum / 12;
		
		logger.info("【十个月总销售额为{}分,月平均销售额为{}分】,【十二个月总销售额为{}分,月平均销售额为{}分】",leftSum,leftAverage,rightSum,rightAverage);
		return leftAverage < rightAverage ? leftAverage : rightAverage;
	}
	
	/**
	 * 获取客户指定时间内总销售额
	 * @param customerId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private static long getTotalSaleData(String customerId,String startTime,String endTime){
		DailyBillExample ex = new DailyBillExample();
		ex.createCriteria().andCustomerIdEqualTo(customerId).andDateBetween(startTime, endTime);
		List<DailyBill> dailyBills = dailyBillMapper.selectByExample(ex);
		
		long totalSaleData = 0l;
		for (DailyBill bill : dailyBills) {
			totalSaleData += bill.getPrice();
		}
		return totalSaleData;
	}
	
	/**
	 * 根据customerId计算合作期限
	 * @param customerId
	 * 2016年1月1日 与 2015年12月31日 算为1个月
	 * @return
	 */
	private static Integer calculateCooperationPeriodByCustomerId(String customerId){
		TzCustomerInfoExample example = new TzCustomerInfoExample();
		example.createCriteria().andTzCustomerIdEqualTo(customerId);
		List<TzCustomerInfo> tzCustomerInfos = tzCustomerInfoMapper.selectByExample(example);
		Date d = DateTimeUtil.truncate(DateTimeUtil.parseDate10(tzCustomerInfos.get(0).getFirstBusinessTime()).toDate(), Calendar.MONTH);
		Date nowDate = DateTimeUtil.truncate(new Date(), Calendar.MONTH);
		int months = Months.monthsBetween(new DateTime(d), new DateTime(nowDate)).getMonths(); 
		return new Integer(months+1);
	}
	
	/**
	 * 根据合作期限获取对应的（合作期限---折扣率）规则
	 * @param cooperationPeriod
	 * @return
	 */
	private static CreditLimitRuleNonWhiteDTO getLimitRuleNonWhiteByCooperationPeriod(Integer cooperationPeriod) {
		CreditLimitRuleNonWhiteDTO creditLimitRuleNonWhite = null;
		for (CreditLimitRuleNonWhiteDTO rule : credit_limit_rule_non_white) {
			if (cooperationPeriod >= rule.getCooperationPeriodMin() && cooperationPeriod <= rule.getCooperationPeriodMax()) {
				logger.info("合作期限为:{},匹配到授信规则:{}", cooperationPeriod, new Gson().toJson(rule));
				creditLimitRuleNonWhite = rule;
				break;
			}
		}
		if (creditLimitRuleNonWhite == null) {
			logger.info("合作期限大于最高值，为:{}，使用最高额度规则", cooperationPeriod);
			creditLimitRuleNonWhite = credit_limit_rule_non_white.get(credit_limit_rule_non_white.size() - 1);
		}
		return creditLimitRuleNonWhite;
	}
}
