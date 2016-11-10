package com.cana.credit.service.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.credit.dao.mapper.gen.CustomerApplyMapper;
import com.cana.credit.dao.po.CustomerApply;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.flight.finance.dao.po.DailyBill;
import com.cana.flight.finance.dao.po.DailyBillExample;
import com.cana.vbam.common.credit.dto.limit.CalculateLimitResult;
import com.cana.vbam.common.credit.dto.limit.CreditLimitRuleDTO;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * 授信系统计算工具
 * 
 * @author ducer
 * @author XuMeng
 * @deprecated plz use {@link CreditLimitCalculateUtil3}
 */
public class CreditLimitCalculateUtil {

	private static final Logger logger = LoggerFactory.getLogger(CreditLimitCalculateUtil.class);
	private static DailyBillMapper dailyBillMapper = SpringApplicationContext.getApplicationContext().getBean(DailyBillMapper.class);
	private static CustomerApplyMapper customerApplyMapper = SpringApplicationContext.getApplicationContext().getBean(CustomerApplyMapper.class);
	private static final List<CreditLimitRuleDTO> credit_limit_rule;
	static {
		credit_limit_rule=Lists.newArrayList();
		credit_limit_rule.add(new CreditLimitRuleDTO("1","0.2","0.8"));
		credit_limit_rule.add(new CreditLimitRuleDTO("0.5","0.1","0.75"));
		credit_limit_rule.add(new CreditLimitRuleDTO("0.2","0.05","0.65"));
		credit_limit_rule.add(new CreditLimitRuleDTO("0","0","0.6"));
		credit_limit_rule.add(new CreditLimitRuleDTO("-100","-0.05","0.5"));
	}
	
	private static final Long maxLimit = new Long(10000000);	//最高10万
	private static final Long minLimit = new Long(500000);		//最低5千

	/**
	 * 计算授信额度
	 * @param customerApplyId
	 */
	public static CalculateLimitResult calculateCreditLimitByCustomerApplyId(String customerApplyId) {
		CustomerApply customerApply = customerApplyMapper.selectByPrimaryKey(customerApplyId);
		if (customerApply == null) {
			throw WebException.instance("授信客户申请不存在");
		}
		String customerId = customerApply.getTzCustomerId();
//		Long limitTravelzenAdvise = customerApply.getProposalCreditLimit();
		CalculateLimitResult calculateLimitResult = null;
		Transaction t = Cat.newTransaction("service", "额度计算");
		t.addData("tzCuscomter", customerId);
		try {
//			calculateLimitResult = calculateCreditLimit(customerId, limitTravelzenAdvise);
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

//	public static CalculateLimitResult calculateCreditLimit(String tzCustomerId, long limitTravelzenAdvise) {
	public static CalculateLimitResult calculateCreditLimit(String tzCustomerId) {
		Map<String, Long> saleData = getTwoYearsSaleData(tzCustomerId);
		Long lastYearSale = calculateSaleOfMonth(saleData, 1, 0);
		Long yearBeforeLastSale = calculateSaleOfMonth(saleData, 2, 0);
		BigDecimal last = new BigDecimal(lastYearSale);
		BigDecimal beforeLast = new BigDecimal(yearBeforeLastSale);
		BigDecimal growthRate = new BigDecimal(0);
		if (yearBeforeLastSale != 0) {
			growthRate = last.subtract(beforeLast).divide(beforeLast, 4, RoundingMode.HALF_UP);
		}
		CreditLimitRuleDTO creditLimitRule = getLimitRuleByGrowthRate(growthRate);
		BigDecimal growthFinalRate = new BigDecimal(creditLimitRule.getSaleGrowthRateFinalValue());
		BigDecimal pledgeRate = new BigDecimal(creditLimitRule.getPledgeRate());
		Long limitA = calculateLimit(lastYearSale, growthFinalRate, pledgeRate);
		
		Long limitB = 0l;
		for (int monthOffset = 1; monthOffset < 12; monthOffset++) {
			Long saleOfMonth = calculateSaleOfMonth(saleData, 0, monthOffset);
			limitB += calculateLimit(saleOfMonth, growthFinalRate, pledgeRate);
		}
		limitB += limitA;
		limitB /= 12;
		
//		Long finalLimit = getFinalLimit(limitA, limitB, limitTravelzenAdvise);
		Long finalLimit = getFinalLimit(limitA, limitB);
		
		CalculateLimitResult result = new CalculateLimitResult();
		result.setFinalLimit(finalLimit);
		result.setGrowthFinalRate(growthFinalRate);
		result.setPledgeRate(pledgeRate);
		return result;
	}

	/**
	 * 额度 =（上年同期当月订单金额 - 上年同期当月退改签金额）/ 30 * 天数N * (1+销售增长率定值θ）* 质押率δ ；
	 */
	private static Long calculateLimit(Long lastYearSale, BigDecimal growthFinalRate, BigDecimal pledgeRate) {
		int day = TopsConfReader.getConfContentForInt("properties/credit.properties", "limit_param_dayN", ConfScope.R); // 天数N(实时去读)
		logger.info("天数N的值为:{}",day);
		BigDecimal limit = new BigDecimal(lastYearSale).divide(new BigDecimal(30),4,RoundingMode.HALF_UP);
		limit = limit.multiply(new BigDecimal(day)).multiply(new BigDecimal(1).add(growthFinalRate)).multiply(pledgeRate);
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
	 * 计算月销售额
	 * 
	 * @param customerId
	 * @param yearOffset 年偏移量 0:今年 1：去年 2：前年
	 * @param monthOffset 月偏移量0:这个月 1：上个月 2：上上个月
	 * @return
	 */
	private static Long calculateSaleOfMonth(Map<String, Long> saleData, int yearOffset, int monthOffset) {
		DateTime date = DateTime.now().minusYears(yearOffset).minusMonths(monthOffset);
		String month = DateTimeUtil.format(date, "yyyy-MM");
		Long sale = saleData.get(month);
		return sale == null ? 0l : sale;
	}

	private static Map<String, Long> getTwoYearsSaleData(String customerId) {
		DailyBillExample ex = new DailyBillExample();
		DailyBillExample.Criteria criteria = ex.createCriteria().andCustomerIdEqualTo(customerId);
		DateTime now = DateTime.now();
		String startTime = DateTimeUtil.date10(now.minusYears(2).withDayOfMonth(1));
		String endTime = DateTimeUtil.date10(now.withDayOfMonth(1).minusDays(1));
		criteria.andDateBetween(startTime, endTime);
		List<DailyBill> dailyBills = dailyBillMapper.selectByExample(ex);

		Map<String, Long> saleData = Maps.newHashMap();
		for (DailyBill bill : dailyBills) {
			String month = bill.getDate().substring(0, 7);
			Long sale = saleData.get(month);
			sale = sale == null ? 0l : sale;
			sale += bill.getPrice();
			saleData.put(month, sale);
		}
		return saleData;
	}
	
	/**
	 * 计算最终额度
	 */
//	private static Long getFinalLimit(Long limitAverage12Month,Long limitCurrentMonth,Long limitTravelzenAdvise){
	private static Long getFinalLimit(Long limitAverage12Month,Long limitCurrentMonth){
//		Long temporaryMinLimit = Math.min(Math.min(limitAverage12Month, limitCurrentMonth), limitTravelzenAdvise);
		Long temporaryMinLimit = Math.min(limitAverage12Month, limitCurrentMonth);
		if(temporaryMinLimit<=minLimit)
			return minLimit;
		else if(temporaryMinLimit>=maxLimit)
			return maxLimit;
		else
			return new BigDecimal(temporaryMinLimit).setScale(-5, RoundingMode.CEILING).longValue();
	}
	
}
