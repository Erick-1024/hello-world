package com.travelzen.framwork.config.tops;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.TzkClient;
import com.travelzen.framwork.config.exception.ZkConfReaderException;

public class TzkClientTest {

	@Test
	public void zk1() {

		String key = "redis.ip";
		String path = "properties/redis.properties";

		//		try {
		//			String value = FastConfigReader.getPropertyValue(path, key, ConfScope.R);
		//			System.out.println(value);
		//
		//		} catch (ZkConfReaderException e) {
		//			e.printStackTrace();
		//		}

		try {
			String value = TopsConfReader.getConfContent(path, key, ConfScope.R);
			System.out.println(value);

		} catch (ZkConfReaderException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 1000; i++) {

			byte[] data = TzkClient.readData("/tops/dev/global/properties/redis.properties");
			System.out.println(new String(data, Charsets.UTF_8));

			DateTimeUtil.SleepSec(2);
		}

	}
}
