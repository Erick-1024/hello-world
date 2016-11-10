package com.cana.credit.service.utils;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Months;

import com.travelzen.framework.core.time.DateTimeUtil;

public class CreditDateUtil {

	/**
	 * 计算当前时间与指定日期相差月份
	 * 2016年1月1日 与 2015年12月31日 算为2个月
	 * @param date
	 * @return
	 */
	public static Integer calculatePeriodMonth(String date){
		Date d = DateTimeUtil.truncate(DateTimeUtil.parseDate10(date).toDate(), Calendar.MONTH);
		Date nowDate = DateTimeUtil.truncate(new Date(), Calendar.MONTH);
		int months = Months.monthsBetween(new DateTime(d), new DateTime(nowDate)).getMonths(); 
		return new Integer(months+1);
	}
}
