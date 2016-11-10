package com.cana.asset.server.test;

import java.util.Date;

import com.travelzen.framework.core.time.DateTimeUtil;

public class DateTimeUtilTest {
	public static void main(String[] args) {
		System.out.println(DateTimeUtil.parseDate("yyyy-MM-dd HH:mm:ss.SSSSSS Z", "2016-03-22 15:34:16.941000 +08:00:00"));
	}
}
