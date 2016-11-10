package com.cana.credit.server.test;

import org.junit.Test;

import com.travelzen.framework.core.util.MoneyUtil;

public class TestMonety {

	@Test
	public void test() {
		String m = "333300";
		System.out.println(MoneyUtil.formatMoney(MoneyUtil.cent2Yuan(m)));
	}

}
