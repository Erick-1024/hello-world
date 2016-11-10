package com.travelzen.framework.core.util;

import org.apache.commons.lang.RandomStringUtils;

public class PostgresUtil {
	
	/**
	 * 转义postgres的字符串值
	 * @param value
	 * @return
	 */
	public static String escapeStringValue(String value){
		String randomDollarTag = "$" + RandomStringUtils.randomAlphabetic(3) + RandomStringUtils.randomNumeric(3) + "$";
		return randomDollarTag + value + randomDollarTag;
	}

}
