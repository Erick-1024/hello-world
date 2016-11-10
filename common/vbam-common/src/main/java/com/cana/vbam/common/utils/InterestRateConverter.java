package com.cana.vbam.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.cana.vbam.common.repayment.enums.InterestRateUnit;

/**
 *
 * 利率转换类
 * 
 * @author XuMeng
 *
 */
public class InterestRateConverter {

	private static final int dayOfMonth = 30; // 日利率、月利率转换时的系数
	private static final int dayOfYear = 360; // 日利率、年利率转换时的系数
	private static final int monthOfYear = 12; // 年利率、月利率转换时的系数
	private static final int scale = 16; // 转换利率时的精度设置
	private static final RoundingMode roundMode = RoundingMode.HALF_UP; // 转换利率时默认使用四舍五入

	/**
	 * 根据传入的利率，返回日利率
	 */
	public static BigDecimal getDailyRate(InterestRateUnit interestRateUnit, BigDecimal interestRate) {
		return getDailyRate(interestRateUnit, interestRate, dayOfYear);
	}

	/**
	 * 根据传入的利率，返回日利率
	 */
	public static BigDecimal getDailyRate(InterestRateUnit interestRateUnit, BigDecimal interestRate, int dayCountConvention) {
		if (interestRateUnit == InterestRateUnit.DAY)
			return interestRate;
		else if (interestRateUnit == InterestRateUnit.MONTH)
			return interestRate.divide(new BigDecimal(dayOfMonth), scale, roundMode);
		else if (interestRateUnit == InterestRateUnit.YEAR)
			return interestRate.divide(new BigDecimal(dayCountConvention), scale, roundMode);
		else
			throw new IllegalArgumentException("不支持的利率转换");
	}

	/**
	 * 获取月利率
	 */
	public static BigDecimal getMonthlyRate(InterestRateUnit interestRateUnit, BigDecimal interestRate) {
		if (InterestRateUnit.MONTH == interestRateUnit)
			return interestRate;
		else if (InterestRateUnit.DAY == interestRateUnit)
			return interestRate.multiply(new BigDecimal(dayOfMonth));
		else if (InterestRateUnit.YEAR == interestRateUnit)
			return interestRate.divide(new BigDecimal(monthOfYear), scale, roundMode);
		else
			throw new IllegalArgumentException("不支持的利率转换");
	}
	
	/**
	 * 获取年利率
	 * @param interestRateUnit
	 * @param interestRate
	 * @param dayCountConvention
	 * @return
	 */
	public static BigDecimal getAnnualRate(InterestRateUnit interestRateUnit, BigDecimal interestRate, int dayCountConvention){
		if (interestRateUnit == InterestRateUnit.YEAR)
			return interestRate;
		else if (interestRateUnit == InterestRateUnit.MONTH)
			return interestRate.multiply(new BigDecimal(monthOfYear));
		else if (interestRateUnit == InterestRateUnit.DAY)
			return interestRate.multiply(new BigDecimal(dayCountConvention));
		else
			throw new IllegalArgumentException("不支持的利率转换");
	}
}
