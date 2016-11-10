package com.cana.early.warning.scheduler.test;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.travelzen.framework.core.util.MoneyUtil;

public class TestJoda {

	@Test
	public void test() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
		DateTime now = new DateTime();
		System.out.println(fmt.print(now.plusHours(8)));
		System.out.println(fmt.print(now.plusHours(-8)));
		System.out.println(fmt.print(now.plusHours(-20)));
	}

	@Test
	public void testDecimal() {
		BigDecimal bigDecimal = new BigDecimal("36.1");
		BigDecimal b = new BigDecimal("100");
		System.out.println(bigDecimal.compareTo(b));
	}
	
	@Test
	public void testMoneyUtil() {
		System.out.println(MoneyUtil.formatMoney(10000L));
	}
	
	@Test
	public void testNumberFormat() {
		NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        BigDecimal bigDecimal = new BigDecimal("0.04551");
        System.out.println(percent.format(bigDecimal.doubleValue()));
	}
	
	@Test
	public void calender() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtils.addDays(new Date(), -1));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date lowerDate = calendar.getTime();
		calendar.setTime(DateUtils.addDays(new Date(), -1));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date upperDate = calendar.getTime();
		System.out.println(lowerDate);
		System.out.println(upperDate);
		System.out.println(lowerDate);
	}
	
}
