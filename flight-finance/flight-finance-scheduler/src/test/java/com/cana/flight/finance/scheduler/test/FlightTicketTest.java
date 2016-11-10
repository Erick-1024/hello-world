package com.cana.flight.finance.scheduler.test;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.google.gson.Gson;

@ContextConfiguration("classpath:spring/flight-finance-scheduler-*.xml")
public class FlightTicketTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void test() {
		String json = "{aa:\"true\"}";
		System.out.println(new Gson().fromJson(json, A.class).isAa());
	}
	
	class A {
		boolean aa;

		public boolean isAa() {
			return aa;
		}

		public void setAa(boolean aa) {
			this.aa = aa;
		}
		
	}
	
}