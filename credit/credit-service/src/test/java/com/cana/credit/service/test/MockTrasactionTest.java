package com.cana.credit.service.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class MockTrasactionTest {

	private static final Long maxLimit = new Long(10000000);
	private static final Long minLimit = new Long(500000);
	
	@Test
	public void test1() {
		Long a = new Long(9999999);
		if(a<=minLimit)
			System.out.println("A"+minLimit.longValue());
		else if(a>=maxLimit)
			System.out.println("B"+maxLimit.longValue());
		else{
			BigDecimal b = new BigDecimal(a).setScale(-5, RoundingMode.CEILING);
			System.out.println(b.longValue());
		}
		
	}
}