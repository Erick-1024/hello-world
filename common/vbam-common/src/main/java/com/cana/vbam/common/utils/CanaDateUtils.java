package com.cana.vbam.common.utils;

import org.joda.time.DateTime;

import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author hu
 *
 */
public class CanaDateUtils {

	/**
	 * 增加天
	 * @param dateStr
	 * @param days
	 * @return
	 * @throws Exception
	 */
	public static String plusDays(String date10, int days) {
		DateTime dateTime = new DateTime(date10);
		dateTime = dateTime.plusDays(days);
		return dateTime.toString("yyyy-MM-dd");
	}
	
	/**
	 * 普通增加月不分月末,对DateTime.plusMonths的一个简单封装
	 * @param dateStr
	 * @param months
	 * @return
	 */
	public static String plusMonths(String date10, int months) {
		DateTime dateTime = new DateTime(date10);
		dateTime = dateTime.plusMonths(months);
		return dateTime.toString("yyyy-MM-dd");
	}
	
	/**
	 * 增加月，返回增加后日期所在月的最后一天
	 * @param dateStr
	 * @param months
	 * @return
	 * @throws Exception
	 */
	public static String plusMonthsReturnLastDay(String date10, int months) {
		DateTime dateTime = new DateTime(date10);
		dateTime = dateTime.plusMonths(months);
		dateTime = dateTime.dayOfMonth().withMaximumValue();
		return dateTime.toString("yyyy-MM-dd");
	}
	
	/**
	 * 判断日期是否是月末
	 * @param date10, 格式yyyy-MM-dd
	 */
	public static boolean isMonthLastDay(String date10) {
		DateTime dateTime = new DateTime(date10);
		return isMonthLastDay(dateTime);
	}
	
	/**
	 * 判断日期是否是月末
	 */
	public static boolean isMonthLastDay(DateTime dateTime) {
		int nowDay = dateTime.getDayOfMonth();
		int totalDay = dateTime.dayOfMonth().getMaximumValue();
		if(nowDay == totalDay){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取当前日期,格式yyyy-MM-dd
	 * @return
	 */
	public static String date10(){
		DateTime dateTime = new DateTime();
		return dateTime.toString("yyyy-MM-dd");
	}
	
	/**
	 * 获取当前年份，格式yyyy
	 * @return
	 */
	public static String getCurrentYear(){
		DateTime dateTime = new DateTime();
		return dateTime.toString("yyyy");
	}
	
	/**
	 * 计算两个日期之间的时间间隔，以天计。 比如： 2016-02-01 与 2016-03-01相差29天
	 * @param startDate10 格式：yyyy-MM-dd
	 * @param endDate10, 格式：yyyy-MM-dd
	 * @return
	 */
	public static int durationDays(String startDate10, String endDate10){
		return DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(startDate10), DateTimeUtil.parseDate10(endDate10));
	}
}
