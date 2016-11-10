package com.cana.vbam.common.auditstatus.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.cana.vbam.common.utils.MoneyArithUtil;

public class MonyUtilsTest {
	@Test
	public void testConvertMoneyToString() {
		System.out.println(MoneyArithUtil.convertMoneyToString(0L));
	}
	
	@Test
	public void testConvertInterestRateToString(){
		System.out.println(MoneyArithUtil.convertInterestRateToString(new BigDecimal(0.003)));
	}
	
	@Test
	public void testRoundUp() {
		System.out.println(MoneyArithUtil.roundUp(12.00001, 0));
	}
	
	@Test
	public void testDive(){
		System.out.println(MoneyArithUtil.divide(new BigDecimal(5), new BigDecimal(28), 3));
		System.out.println(MoneyArithUtil.convertStringToMoney("3.66"));
	}
	
	@Test
	public void testFormat() {
		System.out.println(MoneyArithUtil.doubleFormat(1.0E7));
		System.out.println(MoneyArithUtil.doubleFormat(100000000000.00));
		System.out.println(MoneyArithUtil.doubleFormat(100.00));
	}
}
