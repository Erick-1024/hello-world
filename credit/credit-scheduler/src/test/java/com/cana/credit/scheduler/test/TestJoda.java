package com.cana.credit.scheduler.test;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.travelzen.framework.core.time.DateTimeUtil;

public class TestJoda {

	@Test
	public void test() {
				System.out.println(getVaildDateByPeriod(26));
	}
	
	private Date getVaildDateByPeriod(Integer cooperationPeriod){
		if(cooperationPeriod<2){
			return null;
		}
		DateTime vaildDate = new DateTime().minusMonths(cooperationPeriod);
		return DateTimeUtil.truncate(vaildDate.toDate(), Calendar.MONTH);
	}

}
