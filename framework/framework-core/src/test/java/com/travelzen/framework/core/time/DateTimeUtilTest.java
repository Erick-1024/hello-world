package com.travelzen.framework.core.time;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class DateTimeUtilTest {

	@Test
	public void test_parseDate8() {
		System.out.println(DateTimeUtil.parseDate8("20121201").toDate());
		System.out.println(DateTimeUtil.addDay(DateTimeUtil.parseDate8("20121201"), 1).toDate());
	}
	@Test
	public void test_getBeginDateTime() {
		System.out.println(DateTimeUtil.getBeginDateTime(DateTimeUtil.parseDate8("20121201").toDate()));
		System.out.println(DateTimeUtil.getBeginDateTime(new Date()));
		System.out.println(new Date());
	}
	@Test
	public void diffInDay() {
		Date a = DateTimeUtil.parseDate8("20121201").toDate();
		Date b = DateTimeUtil.parseDate8("20130101").toDate();
		org.junit.Assert.assertEquals(31, DateTimeUtil.diffInDay(a, b));
	}

	@Test
	public void dayOfWeek(){
		assertEquals(4, DateTimeUtil.parseDate8("20150226").getDayOfWeek());
	}
	@Test
	public void parse(){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMMyyyy");
		DateTime dateTime = formatter.parseDateTime("27FEB2015");
		assertEquals("20150227", DateTimeUtil.date8(dateTime));
		dateTime = formatter.parseDateTime("06APR2015");
		assertEquals("20150406", DateTimeUtil.date8(dateTime));
	}
	@Test
	public void parseDate10(){
		// DateTimeUtil.parseDate10("1987-04-12");
//		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
//		LocalDate localDate = format.parseLocalDate("1987-04-12");
//		System.out.println(localDate.getYear());
//		System.out.println(localDate.getMonthOfYear());
//		System.out.println(localDate.getDayOfMonth());
//		Date date = new Date(87, 3, 12);
//		System.out.println(date);
		
//		DateTime dateTime = new DateTime(localDate.getYear(), localDate.getMonthOfYear(),localDate.getDayOfMonth(), 0, 0);
//		System.out.println(dateTime);
		System.out.println(DateTimeUtil.parseDate10("1987-04-12"));
		System.out.println(DateTimeUtil.parseDate10("2014-04-12"));
//		System.out.println(format.parseLocalDate("2014-04-13").toDateTimeAtStartOfDay());
	}
	@Test
	public void parseDate8(){
		System.out.println(DateTimeUtil.parseDate8("19870412"));
		System.out.println(DateTimeUtil.parseDate8("20140412"));
	}
	@Test
	public void testValidMethod() {
		assertEquals(true, DateTimeUtil.isValdateDate("2016-3-22 14:3:32", 8));
		assertEquals(true, DateTimeUtil.isValdateDate("2016-3-22 14:32:32", 8));
		assertEquals(true, DateTimeUtil.isValdateDate("2016-03-22 4:32:32", 8));
	}
	
	@Test
	public void validateMonth(){
		assertEquals(true, DateTimeUtil.validateMonth7("2016-03"));
		assertEquals(false, DateTimeUtil.validateMonth7("16-03"));
		assertEquals(false, DateTimeUtil.validateMonth7("2016-13"));
	}
	
	@Test
	public void month7(){
		System.out.println(DateTimeUtil.month7());
	}
}
