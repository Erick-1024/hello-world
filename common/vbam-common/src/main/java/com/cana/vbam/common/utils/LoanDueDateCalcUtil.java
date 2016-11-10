package com.cana.vbam.common.utils;

import org.joda.time.DateTime;

import com.cana.vbam.common.repayment.enums.DateUnit;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * 到期日计算
 * @author XuMeng
 *
 */
public class LoanDueDateCalcUtil {

	public static String calcLoanDueDate(String loanDate, DateUnit loanPeriodUnit, int loanPeriod) {
		if (loanPeriod <= 0)
			throw new IllegalArgumentException("放款期限必须大于0");
		if (!DateTimeUtil.validateDate10(loanDate))
			throw new IllegalArgumentException("放款日期不合法");

		DateTime loanDateTime = new DateTime(loanDate);

		int periodMonths;
		if (loanPeriodUnit == DateUnit.MONTH) {
			periodMonths = loanPeriod;
		} else if (loanPeriodUnit == DateUnit.YEAR) {
			periodMonths = loanPeriod * 12;
		} else {
			return DateTimeUtil.date10(loanDateTime.plusDays(loanPeriod));
		}

		if (isMonthLastDay(loanDateTime))
			return plusMonthsReturnLastDay(loanDateTime, periodMonths);
		else
			return plusMonths(loanDateTime, periodMonths);
	}

	/**
	 * 判断日期是否是月末
	 */
	private static boolean isMonthLastDay(DateTime dateTime) {
		int nowDay = dateTime.getDayOfMonth();
		int totalDay = dateTime.dayOfMonth().getMaximumValue();
		if(nowDay == totalDay){
			return true;
		}
		return false;
	}

	private static String plusMonthsReturnLastDay(DateTime dateTime, int months) {
		dateTime = dateTime.plusMonths(months);
		dateTime = dateTime.dayOfMonth().withMaximumValue();
		return dateTime.toString("yyyy-MM-dd");
	}

	private static String plusMonths(DateTime dateTime, int months) {
		dateTime = dateTime.plusMonths(months);
		return dateTime.toString("yyyy-MM-dd");
	}
}
