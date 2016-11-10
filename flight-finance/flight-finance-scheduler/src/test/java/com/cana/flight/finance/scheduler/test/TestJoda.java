package com.cana.flight.finance.scheduler.test;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class TestJoda {

	@Test
	public void test() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
		DateTime now = new DateTime();
		System.out.println(fmt.print(now.plusHours(8)));
		System.out.println(fmt.print(now.plusHours(-8)));
		System.out.println(fmt.print(now.plusHours(-20)));
	}

}
