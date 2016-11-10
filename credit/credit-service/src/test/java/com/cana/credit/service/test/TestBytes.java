package com.cana.credit.service.test;


import org.junit.Test;

import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;

public class TestBytes {

	@Test
	public void test() {
		String str = "dffdreh3";
		byte[] by = str.getBytes();
		System.out.println(new String(by));
		ReturnClass returnClass = new ReturnClass(ReturnCode.TP0024,"100000");
		ReturnCode returnCode = returnClass.getRetCode();
		System.out.println(returnClass.getMessage());
	}

}
