package com.cana.vbam.common.utils;

import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.utils.MoneyArithUtil;

/**
 *
 * 利率计算
 * 
 * @author XuMeng
 *
 */
public class InterestCalcUtil {

	private static final int dayOfYear = 360; // 日利率、年利率转换时的系数

	/**
	 * 根据本金、利率、期限计算利息
	 * 当计算结果为0时，返回0
	 * 当利息大于0，并小于等于1分钱时，按1分钱记
	 * 当利息大于1分钱时，使用四舍五入的方式保留到分位
	 */
	public static long calcInterest(long principal, InterestRateUnit interestRateUnit, BigDecimal interestRate,
			DateUnit dateUnit, int duration) {
		return calcInterest(principal, interestRateUnit, interestRate, dateUnit, duration, dayOfYear);
	}

	/**
	 * 根据本金、利率、期限计算利息
	 * 当计算结果为0时，返回0
	 * 当利息大于0，并小于等于1分钱时，按1分钱记
	 * 当利息大于1分钱时，使用四舍五入的方式保留到分位
	 * 
	 * @param dayCountConvention 计息基准天数
	 */
	public static long calcInterest(long principal, InterestRateUnit interestRateUnit, BigDecimal interestRate,
			DateUnit dateUnit, int duration, int dayCountConvention) {
		if (dateUnit != DateUnit.DAY)
			throw new IllegalArgumentException("暂不支持的期限单位");

		if (duration < 0 || principal < 0 || interestRate.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("计算利率参数不合法");

		if (principal == 0 || duration == 0 || interestRate.compareTo(BigDecimal.ZERO) == 0)
			return 0;

		BigDecimal interestRateOfDay = InterestRateConverter.getDailyRate(interestRateUnit, interestRate, dayCountConvention);

		BigDecimal interest = new BigDecimal(principal).multiply(new BigDecimal(duration)).multiply(interestRateOfDay);

		if (interest.compareTo(BigDecimal.ZERO) > 0 && interest.compareTo(BigDecimal.ONE) <= 0)
			return 1;
		return MoneyArithUtil.round(interest, 0).longValue();
	}
}
