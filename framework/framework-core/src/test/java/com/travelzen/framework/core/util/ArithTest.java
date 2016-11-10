package com.travelzen.framework.core.util;

import org.junit.Assert;
import org.junit.Test;

public class ArithTest {

	@Test
	public void testCent2yuan() {
		
		Long num = 1L;
		
		Assert.assertTrue(num == 1);
		
		String expected1 = "12.34";
		String actual1 = Arith.cent2yuan(1234);
		Assert.assertEquals(expected1, actual1);

		String expected2 = "0.10";
		String actual2 = Arith.cent2yuan(10);
		Assert.assertEquals(expected2, actual2);

		String expected3 = "0.01";
		String actual3 = Arith.cent2yuan(1);
		Assert.assertEquals(expected3, actual3);

		String expected4 = "0.00";
		String actual4 = Arith.cent2yuan(0);
		Assert.assertEquals(expected4, actual4);
	}
}
