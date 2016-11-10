package com.travelzen.framwork.config.tops;

import org.junit.Test;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.util.TopsConfigReaderUtil;

public class TopsUtilTest {
	@Test
	public void testUtil() {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(TopsConfigReaderUtil.class.getClassLoader().getResource(""));
		String projectName = TopsConfigReaderUtil.getApplicationName();
		System.out.println("projectName: " + projectName);
		String ip = TopsConfigReaderUtil.getLocalIP();
		System.out.println("localIp: " + ip);

		System.out.println(ConfScope.valueOf("R"));

	}

}
