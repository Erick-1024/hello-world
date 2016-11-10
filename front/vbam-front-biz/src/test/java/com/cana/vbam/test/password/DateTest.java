package com.cana.vbam.test.password;

import org.joda.time.DateTime;

import com.travelzen.framework.core.time.DateTimeUtil;

public class DateTest {

	public static void main(String[] args) {
		String today = DateTimeUtil.format(new DateTime(), "yyyy-MM-dd");
		System.out.println(today);
		String oneweek = DateTimeUtil.format(new DateTime().minusDays(6),"yyyy-MM-dd");
		System.out.println(oneweek);
		String onemonth = DateTimeUtil.format(new DateTime().minusMonths(1).plusDays(1), "yyyy-MM-dd");
		System.out.println(onemonth);
		String threemonths = DateTimeUtil.format(new DateTime().minusMonths(3).plusDays(1), "yyyy-MM-dd");
		System.out.println(threemonths);

	}

}
