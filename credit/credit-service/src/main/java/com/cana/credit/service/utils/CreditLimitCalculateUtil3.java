package com.cana.credit.service.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.credit.dao.po.CustomerApply;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.flight.finance.dao.po.DailyBill;
import com.cana.flight.finance.dao.po.DailyBillExample;
import com.cana.flight.finance.service.IRepaymentService;
import com.cana.vbam.common.credit.dto.limit.CalculateLimitResult;
import com.cana.vbam.common.credit.dto.limit.CreditLimitRuleDTO2;
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
 * @author yihong.tang
 * @since 2016-9-1
 */
public class CreditLimitCalculateUtil3 {

	private static final Logger logger = LoggerFactory.getLogger(CreditLimitCalculateUtil3.class);
	private static DailyBillMapper dailyBillMapper = SpringApplicationContext.getApplicationContext().getBean(DailyBillMapper.class);
	private static IRepaymentService repaymentServiceImpl = SpringApplicationContext.getApplicationContext().getBean(IRepaymentService.class);
	
	private static final List<CreditLimitRuleDTO2> credit_limit_rule;
	
	static {
		credit_limit_rule=Lists.newArrayList();
		credit_limit_rule.add(new CreditLimitRuleDTO2(new BigDecimal(0.43),null,"0.9"));
		credit_limit_rule.add(new CreditLimitRuleDTO2(new BigDecimal(0.23),new BigDecimal(0.43),"0.8"));
		credit_limit_rule.add(new CreditLimitRuleDTO2(new BigDecimal(0.03),new BigDecimal(0.23),"0.7"));
		credit_limit_rule.add(new CreditLimitRuleDTO2(new BigDecimal(-0.17),new BigDecimal(0.03),"0.6"));
		credit_limit_rule.add(new CreditLimitRuleDTO2(null,new BigDecimal(-0.17),"0.5"));
	}

	private static final String creditConfigFilePath = "properties/credit.properties";
	
	private static int getCreditLimitParamDayN() {
		return TopsConfReader.getConfContentForInt(creditConfigFilePath, "limit_param_dayN", ConfScope.R); // 天数N(实时去读)
	}
	
	private static long getCreditLimitRangeLower() {
		long limit = TopsConfReader.getConfContentForLong(creditConfigFilePath, "limit_range_lower", ConfScope.R);
		if (limit < 0)
			limit = 1000000;
		return limit;
	}
	
	private static long getCreditLimitRangeUpper() {
		long limit = TopsConfReader.getConfContentForLong(creditConfigFilePath, "limit_range_upper", ConfScope.R);
		if (limit < 0)
			limit = 10000000;
		return limit;
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
			calculateLimitResult = calculateCreditLimit(customerId);
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

		BigDecimal growthRate = repaymentServiceImpl.getGrowthRateByCustomerId(tzCustomerId);
		CreditLimitRuleDTO2 creditLimitRule = getLimitRuleByGrowthRate(growthRate);
		BigDecimal pledgeRate = new BigDecimal(creditLimitRule.getPledgeRate());
		
		Long average = saleData.getRight() / 12;
		Long limit = calculateLimit(average, pledgeRate);
		Long finalLimit = limit;
		if(limit>0)
			finalLimit = getFinalLimit(tzCustomerId, limit);
		
		CalculateLimitResult result = new CalculateLimitResult(0l);
		result.setFinalLimit(finalLimit);
		return result;
	}
	
	/**
	 * 额度 =（近一年平均月销售额）/ 30 * 天数N * 质押率δ ；
	 */
	private static Long calculateLimit(Long average, BigDecimal pledgeRate) {
		int day = getCreditLimitParamDayN();
		logger.info("天数N的值为:{}",day);

		BigDecimal limit = new BigDecimal(average).divide(new BigDecimal(30), 4, RoundingMode.HALF_UP);
		limit = limit.multiply(new BigDecimal(day)).multiply(pledgeRate);

		long longLimit = limit.longValue();
		return longLimit < 0 ? 0 : longLimit;
	}

	private static CreditLimitRuleDTO2 getLimitRuleByGrowthRate(BigDecimal growthRate) {
		if(growthRate==null)
			return credit_limit_rule.get(credit_limit_rule.size()-1);
		CreditLimitRuleDTO2 creditLimitRule = null;
		for (CreditLimitRuleDTO2 rule : credit_limit_rule) {
			BigDecimal min = rule.getGrowthRateMin();
			BigDecimal max = rule.getGrowthRateMax();
			if ((min == null || min.compareTo(growthRate) < 0) && (max == null || max.compareTo(growthRate) >= 0)) {
				logger.info("增长率为:{},匹配到授信规则:{}", growthRate, new Gson().toJson(rule));// 测试用
				creditLimitRule = rule;
				break;
			}
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
}
