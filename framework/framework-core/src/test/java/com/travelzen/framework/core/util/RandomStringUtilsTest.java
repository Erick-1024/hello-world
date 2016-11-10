package com.travelzen.framework.core.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class RandomStringUtilsTest {
	@Test
	public void getRandomString(){
		for(int i = 0; i < 100; i++)
			System.out.println(RandomStringUtils.randomAlphanumeric(10));
	}

}
