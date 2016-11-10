package com.cana.vbam.common.utils.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import com.cana.vbam.common.utils.MoneyArithUtil;

public class MoneyArithUtilTest {

	@Test
	public void roundInterestRate() {
		assertEquals("0.00050", MoneyArithUtil.roundInterestRate(new BigDecimal("0.000501")).toString());
		assertEquals("0.00050", MoneyArithUtil.roundInterestRate(new BigDecimal("0.000504")).toString());
		assertEquals("0.00051", MoneyArithUtil.roundInterestRate(new BigDecimal("0.000505")).toString());
	}
	
	@Test
	public void divide(){
		BigDecimal ratio = new BigDecimal("43.6").divide(new BigDecimal(100), 160, RoundingMode.HALF_UP);
		System.out.println(ratio);
	}

}
