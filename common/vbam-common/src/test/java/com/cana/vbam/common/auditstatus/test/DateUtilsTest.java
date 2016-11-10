package com.cana.vbam.common.auditstatus.test;

import org.joda.time.DateTime;
import org.junit.Test;

import com.cana.vbam.common.utils.CanaDateUtils;

public class DateUtilsTest {

//	@Test
	public void test() {
		DateTime date = new DateTime("2015-03-31");
		System.out.println(date.toDate().toString());
		DateTime date2 = date.plusMonths(1);
		System.out.println(date2.toDate().toString());
		
		System.out.println(date.getDayOfMonth());
		System.out.println(date.dayOfMonth().getMaximumValue());
		DateTime dt4 = new DateTime("2016-02-29");
        org.joda.time.DateTime.Property month = dt4.monthOfYear();
        
        System.out.println("是否闰月:" + month.isLeap());
        
	}
	
	@Test
	public void testDateDealUtils() throws Exception{
		System.out.println(CanaDateUtils.isMonthLastDay("2015-12-30"));
//		System.out.println(CanaDateUtils.plusMonthsIncludeLastDay("2015-12-31", 2));
		System.out.println(CanaDateUtils.plusDays("2015-03-31", 1));
	}

	@Test
	public void test1() throws Exception{
		System.out.println(("2015-08-19".compareTo("2015-08-18")));
	}
}
