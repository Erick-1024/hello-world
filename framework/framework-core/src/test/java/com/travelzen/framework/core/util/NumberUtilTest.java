package com.travelzen.framework.core.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class NumberUtilTest {
	@Test
	public void long_equals(){
		assertFalse(NumberUtil.equals(1L, null));
		assertFalse(NumberUtil.equals(null, 1L));
		assertFalse(NumberUtil.equals(1L, 2L));
		assertTrue(NumberUtil.equals((Long)null, (Long)null));
		assertTrue(NumberUtil.equals(1L, 1L));
	}
	@Test
	public void double_equals(){
		assertFalse(NumberUtil.equals(1D, null));
		assertFalse(NumberUtil.equals(null, 1D));
		assertFalse(NumberUtil.equals(2.0, new Double(1.0)));
		assertTrue(NumberUtil.equals((Double)null, (Double)null));
		assertTrue(NumberUtil.equals(1.0, new Double(1.0)));
	}
}
