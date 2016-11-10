package com.cana.vbam.common.utils.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cana.vbam.common.utils.CanaDateUtils;

public class CanaDateUtilsTest {

	@Test
	public void isMonthLastDay() throws Exception{
		assertTrue(CanaDateUtils.isMonthLastDay("2016-02-29"));
		assertFalse(CanaDateUtils.isMonthLastDay("2016-02-28"));
		assertTrue(CanaDateUtils.isMonthLastDay("2015-02-28"));
		assertTrue(CanaDateUtils.isMonthLastDay("2016-03-31"));
		assertFalse(CanaDateUtils.isMonthLastDay("2016-03-30"));
	}

	@Test
	public void plusMonths() throws Exception{
		assertEquals("2016-02-29", CanaDateUtils.plusMonths("2016-01-31", 1));
		assertEquals("2016-03-29", CanaDateUtils.plusMonths("2016-02-29", 1));
		assertEquals("2016-04-30", CanaDateUtils.plusMonths("2016-03-31", 1));
	}
	
	@Test
	public void durationDays() throws Exception{
		assertEquals(29, CanaDateUtils.durationDays("2016-02-01", "2016-03-01"));
		assertEquals(31, CanaDateUtils.durationDays("2016-03-01", "2016-04-01"));
		assertEquals(15, CanaDateUtils.durationDays("2016-03-01", "2016-03-16"));
	}
	
	@Test
	public void plusMonthsReturnLastDay() throws Exception{
		assertEquals("2016-03-31", CanaDateUtils.plusMonthsReturnLastDay("2016-02-29", 1));
		assertEquals("2016-04-30", CanaDateUtils.plusMonthsReturnLastDay("2016-03-31", 1));
		assertEquals("2016-02-29", CanaDateUtils.plusMonthsReturnLastDay("2016-01-31", 1));
		assertEquals("2015-02-28", CanaDateUtils.plusMonthsReturnLastDay("2015-01-31", 1));
	}
}
