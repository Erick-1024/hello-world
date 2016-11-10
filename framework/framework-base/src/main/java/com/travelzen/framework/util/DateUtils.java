package com.travelzen.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/**
 * Provide a series of function that relative to convert date information.
 *
 * @author muyuansun
 */
public class DateUtils {

	private DateUtils() {

	}

	/**
	 * @param sDate
	 * @return
	 */
	public static Date getDate(String sDate) {
		return getDate(sDate, "");
	}

	/**
	 * @param sDate
	 * @return
	 */
	public static Date getJustDate(String sDate) {
		return getDate(sDate, "yyyy-MM-dd");
	}

	/**
	 * @param date 日期  format : yyyy-MM-dd
	 * @param time 时间  format : hh:mm
	 * @return date
	 */
	public static Date getFlightDate(String date, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			return sdf.parse(date + " " + time);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param lDate
	 * @return
	 */
	public static Date getDate(long lDate) {
		return getDate(lDate, "");
	}

	/**
	 * @param lDate
	 * @return
	 */
	public static Date getJustDate(long lDate) {
		return getDate(lDate, "yyyy-MM-dd");
	}

	public static Date getDate(long lDate, String sFormat) {
		if (sFormat == null || "".equals(sFormat)) {
			sFormat = "yyyy-MM-dd HH:mm:ss";
		}

		return getDate(formatDate(new Date(lDate), sFormat), sFormat);
	}

	public static Date getDate(String sDate, String sFormat) {
		Date dValue;

		try {
			if (sFormat == null || "".equals(sFormat)) {
				sFormat = "yyyy-MM-dd HH:mm:ss";
			}

			dValue = new SimpleDateFormat(sFormat).parse(sDate);
		} catch (Exception e) {
			dValue = new Date(0);
		}

		return dValue;
	}

	public static String formatDate(long sDate, String sFormat) {
		return formatDate(new Date(sDate), sFormat);
	}

	public static String formatDate(long lDate) {
		return formatDate(new Date(lDate), "");
	}

	public static String formatJustDate(long lDate) {
		return formatDate(new Date(lDate), "yyyy-MM-dd");
	}

	public static String formatMonthAndDay(long lDate) {
		return formatDate(new Date(lDate), "MM-dd");
	}
	public static String formatMonthAndDayCN(long lDate) {
		return formatDate(new Date(lDate), "MM月dd日");
	}

	public static String formatDate(Date date, String sFormat) {
		if (sFormat == null || "".equals(sFormat)) {
			sFormat = "yyyy-MM-dd HH:mm:ss";
		}

		//出现异常时返回空字符串
		try{
			return new SimpleDateFormat(sFormat).format(date);
		}catch(Exception e){

		}
		return "";
	}

	public static String formatDate(Date date) {
		return formatDate(date, TimeZone.getDefault());
	}

	public static String formatDate(Date date, TimeZone timeZone) {
		return formatDate(date, timeZone, "yyyy-MM-dd");
	}

	public static String formatDate(Date date, TimeZone timeZone, String sFormat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat.format(date);
	}

	public static String simplifyDate(String date) {

		Date d = getDate(date, "yyyy-MM-dd HH:mm:ss");
		return formatDate(d, "yy-MM-dd HH:mm");

	}

	public static String getTodayStr() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date todate = new Date(System.currentTimeMillis());
		String today = df.format(todate);
		return today;
	}

	public static String getDateStamp(int intFormat) {
		return findFormat(intFormat).format(new Date());
	}

	public static final int TIMESTAMPTYPE_UNIX = 2;

	/**
	 * 将日期对象转为指定格式日期字符串
	 *
	 * @param date
	 * @param intFormat
	 * @return
	 */
	public static String format(Date date, int intFormat) {
		SimpleDateFormat sdf = findFormat(intFormat);
		return sdf.format(date);
	}

	/**
	 * 根据参数获得简单日期格式对象
	 *
	 * @param intFormat
	 * @return
	 */
	public static SimpleDateFormat findFormat(int intFormat) {
		String strFormat = "yyyy'??'MM'??'dd'??' H:mm:ss.S";
		switch (intFormat) {
			case 0: // '\0'
				strFormat = "yyyy'??'MM'??'dd'??' H:mm:ss.S";
				break;

			case 1: // '\001'
				strFormat = "yyyy'-'MM'-'dd H:mm:ss.S";
				break;

			case 2: // '\002'
				strFormat = "yyyy'??'MM'??'dd'??'";
				break;

			case 3: // '\003'
				strFormat = "yyyy'-'MM'-'dd";
				break;

			case 4: // '\004'
				strFormat = "H:mm:ss";
				break;

			case 5: // '\005'
				strFormat = "K:mm:ss a";
				break;

			case 6: // '\006'
				strFormat = "yyyy'??'MM'??'dd'??' H:mm:ss";
				break;

			case 7: // '\007'
				strFormat = "yyyy'??'MM'??'dd'??' K:mm:ss a";
				break;

			case 8: // '\b'
				strFormat = "yyyy-MM-dd H:mm:ss";
				break;

			case 9: // '\t'
				strFormat = "yyyy-MM-dd K:mm:ss a";
				break;

			case 10: // '\n'
				strFormat = "H:mm:ss.S";
				break;

			case 11: // '\013'
				strFormat = "K:mm:ss.S a";
				break;

			case 12: // '\f'
				strFormat = "H:mm";
				break;

			case 13: // '\r'
				strFormat = "K:mm a";
				break;

			case 14: // '\r'
				strFormat = "yyyy-MM-dd H:mm";
				break;

			case 15: // '\r'
				strFormat = "yyyyMMddHHmmssS";
				break;
			case 16: // '\r'
				strFormat = "yyyyMMdd";
				break;

			case 17: // '\r'
				strFormat = "yyyy/MM/dd";
				break;

			case 18: // '\r'
				strFormat = "yyyy/MM/dd H:mm:ss";
				break;

			case 19: // '\r'
				strFormat = "yyyy-MM-dd";
				break;

			case 20:
				strFormat= "yyyy-MM-dd HH:mm:ss";
				break;

			case 21:
				strFormat="HH:mm";
				break;
				
			case 22:
				strFormat= "yyyy-MM-dd HH:mm";
				break;
				
			case 23:
				strFormat= "yyyy-MM";
				break;
				
			default:
				strFormat = "yyyy'??'MM'??'dd'??' H:mm:ss.S";
				break;
		}
		return new SimpleDateFormat(strFormat);
	}

	public static String formatDateStr(String dateStr, int newtimestampType,
	                                   int oldtimestampType) throws ParseException {
		Date oldDate = findFormat(oldtimestampType).parse(dateStr);
		return format(oldDate, newtimestampType);
	}

	/**
	 * 判断字符日期是否合法，根据给定时间戳格式
	 *
	 * @param strDate
	 * @param timestampType
	 * @return
	 */
	public static boolean isValdateDate(String strDate, int timestampType) {
		boolean isPassed = false;

		SimpleDateFormat sdf = findFormat(timestampType);
		sdf.setLenient(false);
		if (strDate != null && strDate.length() > 0) {
			try {
				Date dtCheck = (sdf.parse(strDate));
				String strCheck = sdf.format(dtCheck);
				if (strDate.equals(strCheck)) {
					isPassed = true;
				} else {
					isPassed = false;
				}
			} catch (Exception e) {
				isPassed = false;
			}
		}
		return isPassed;
	}

	public static List<String> getDateList(String startTime, String endTime)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> dateInfos = new ArrayList<String>();
		Date start = null;
		Date end = null;
		startTime = startTime + " 00:00:00";
		endTime = endTime + " 23:59:59";
		try {
			start = sdf.parse(startTime);
			end = sdf.parse(endTime);
		} catch (Exception e) {
			throw new Exception("Parse date exception !");
		}

		if (start.after(end)) {
			throw new Exception("Query time error.Start time after end time.");
		}

		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);

		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);

		sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (calStart.after(calEnd)) {
			dateInfos.add(startTime);
		} else {
			while (!calStart.after(calEnd)) {
				dateInfos.add(sdf.format(calStart.getTime()));
				calStart.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return dateInfos;
	}

	/**
	 * 通过起始时间和结束时间获得期间有多少分钟
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static long findDateSpaceInMinuties(String startTime, String endTime)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");

		Date startDate = sdf.parse(startTime + " 00:00:00");

		Date endDate = sdf.parse(endTime + " 23:59:59");

		long minutes = (endDate.getTime() - startDate.getTime()) / (1000 * 60);

		return minutes + 1;
	}

	/**
	 * 通过起始时间和结束时间，将当前时间范围分割为参数（divisor）代表的份数，
	 * 并将每一份转化为对应的日期封装到集合中返回。注：divisor必须为偶数， 返回的日期格式为标准格式，yyyy-MM-dd H:mm:ss
	 *
	 * @param startTime
	 * @param endTime
	 * @param divisor
	 * @return
	 * @throws ParseException
	 */
	public static List<String> findDateScopeStandardFormat(String startTime,
	                                                       String endTime, int divisor, String strFormat)
			throws ParseException {
		List<String> returnValue = null;
		if (startTime == null || startTime.length() <= 0) {
			return returnValue;
		}
		if (endTime == null || endTime.length() <= 0) {
			return returnValue;
		}
		if (divisor == 0 || divisor % 2 != 0) {
			return returnValue;
		}
		returnValue = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);

		Date startDate = sdf.parse(startTime + " 00:00:00");
		Date endDate = sdf.parse(endTime + " 23:59:59");

		Calendar calStartTime = Calendar.getInstance();
		calStartTime.setTime(startDate);

		Calendar calEndTime = Calendar.getInstance();
		calEndTime.setTime(endDate);

		int minuties = (int) findDateSpaceInMinuties(startTime, endTime)
				/ divisor;

		while (!calStartTime.after(calEndTime)) {
			returnValue.add(sdf.format(calStartTime.getTime()));
			calStartTime.add(Calendar.MINUTE, minuties);
		}
		return returnValue;
	}

	public static long getDateTime(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date).getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 从日期对象得到该日期零点的时间戳
	 *
	 * @param date
	 * @param timestampType
	 * @return
	 */
	public static long getZeroTimeStampOfDay(Date date, int timestampType) {
		long returnValue = -1;
		Calendar calendar = null;
		try {

			calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);

			returnValue = calendar.getTimeInMillis();

			if (timestampType == TIMESTAMPTYPE_UNIX)
				returnValue = returnValue / 1000;
		} catch (Exception e) {
			returnValue = -1;
		}
		return returnValue;
	}

	/**
	 * 获取日期对应的时间
	 *
	 * @param date
	 * @param timestampType
	 * @return
	 */
	public static long getDateTimestamp(Date date, int timestampType) {
		long returnValue = -1;
		try {
			if (date == null) {
				return -1;
			}
			returnValue = date.getTime();

			if (timestampType == TIMESTAMPTYPE_UNIX) {
				returnValue = returnValue / 1000;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public static String findYestarday(int timestampType) {
		Calendar calendar = null;
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.add(Calendar.DATE, -1);

		return DateUtils.format(calendar.getTime(), timestampType);
	}

	public static Date formatDate(String date, int intFormat) throws Exception {
		Calendar calendar = null;
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(findFormat(intFormat).parse(date));
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	public static String addDate(String dateStr, int intFormat, int addNum)
			throws Exception {
		Calendar calendar = null;
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(findFormat(intFormat).parse(dateStr));
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.add(Calendar.DATE, addNum);
		return findFormat(intFormat).format(calendar.getTime());
	}

	public static Date toDate(String param, String format) throws ParseException {
		if (StringUtils.isEmpty(param)) {
			return null;
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		date = sdf.parse(param);
		return date;
	}

	public static Date computeDate(Date date, int day) {
		GregorianCalendar nowDate = new GregorianCalendar();
		nowDate.setTime(date);
		nowDate.add(GregorianCalendar.DATE, day);
		Date computeAfter = nowDate.getTime();
		return computeAfter;
	}

	public static Date todayTrim(Date date) {
		Calendar calendar = null;
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	/**
	 * 计算年龄
	 */
	public static int getAge(Date birthDay) {
		Calendar noewCal = Calendar.getInstance();

		if (noewCal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is after Now.It's unbelievable!");
		}

		int yearNow = noewCal.get(Calendar.YEAR);
		int monthNow = noewCal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = noewCal.get(Calendar.DAY_OF_MONTH);

		noewCal.setTime(birthDay);
		int yearBirth = noewCal.get(Calendar.YEAR);
		int monthBirth = noewCal.get(Calendar.MONTH);
		int dayOfMonthBirth = noewCal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}

		return age;
	}

	/**
	 * 根据日期计算星期
	 * 1->周日, 2->周一
	 *
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		int weekIndex = -1;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
		return weekIndex;
	}

	/**
	 * 获取中文大写星期  DAY OF WEEK
	 * @param date
	 * @return
	 */
	public static String getDayOfWeek(Date date){
		Calendar  cal = Calendar.getInstance();
		cal.setTime(date);
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		return weekDays[cal.get(Calendar.DAY_OF_WEEK)];
	}

	/**
	 * 获取中文大写星期  DAY OF WEEK
	 * @param cal
	 * @return
	 */
	public static String getDayOfWeek(Calendar cal){
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		return weekDays[cal.get(Calendar.DAY_OF_WEEK)-1];
	}

	/**
	 * 两个日期相隔月份 大于某个月份
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 * @throws Exception
	 */
	public static int getMonthCompare(String dateStr1,String dateStr2, int month) throws Exception {
		Date date1 = formatDate(dateStr1, 3);
		Calendar c1 = Calendar.getInstance();

		c1.setTime(date1);
		
		c1.add(Calendar.MONTH, month);
		
		int returnDate = compareDate(format(c1.getTime(), 3), dateStr2);
		
		return returnDate;
	}

	/**
	 * 比较两个日期大小
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;  // dt1在dt2前
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;  // dt1在dt2后
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 拿到月份第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(Date time) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.setTime(time);
		// 设置日历中月份的第1天
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfMonth = sdf.format(cal.getTime());
		return firstDayOfMonth;
	}
	
	/**
	 * 判断当前时间是否在某个时间段，如果 9：00-19：00
	 * hour1 起始时间-时
	 * minute1 起始时间-分
	 * hour2 终止时间-时
	 * minute2 终止时间-分
	 */
	public static Boolean checkTimeInterval(int hour1,int minute1, int hour2, int minute2) {
		Calendar date = Calendar.getInstance();
		Calendar date1 = (Calendar) date.clone();
		Calendar date2 = (Calendar) date.clone();
		
		date1.set(Calendar.HOUR_OF_DAY, hour1);
		date1.set(Calendar.MINUTE, minute1);
		date1.set(Calendar.SECOND, 0);
		
		date2.set(Calendar.HOUR_OF_DAY, hour2);
		date2.set(Calendar.MINUTE, minute2);
		date2.set(Calendar.SECOND, 0);
		
		return date.after(date1) && date.before(date2);
	}
	
	/**
	 * 判断当前时间是否是工作日
	 * @return
	 */
	public static Boolean checkWeekDay() {
		Calendar date = Calendar.getInstance();
		int week = date.get(Calendar.DAY_OF_WEEK);
		if (week == 1 || week == 7) {
			return false;
		}
		return true;
	}
	
	/**
	 * 计算两个日期之间相差的月数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonths(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
				flag = 1;

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
						+ objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth;
	}
}