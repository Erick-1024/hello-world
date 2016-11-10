package com.travelzen.framwork.config.tops;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.travelzen.framework.config.tops.FastConfigReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.util.TopsConfigReaderProps;
import com.travelzen.framework.config.tops.util.ZkPropertiesUtil;
import com.travelzen.framework.config.tops.zk.ZkData;
import com.travelzen.framework.core.exception.PropertyException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framwork.config.exception.ZkConfReaderException;

public class TopsConfReaderTest {
	private static Logger logger = LoggerFactory.getLogger(TopsConfReaderTest.class);

	@SuppressWarnings("unused")
	@Test
	public void getGlobalValue2() {
		String key = "redis.ip";
		String path = "properties/redis.properties";

		for (int i = 0; i < 1000; i++) {

			//			String value = TopsConfReader.getConfContent(path, key, ConfScope.R);
			try {

				//				String value = ZkConfReader.getPropertyValue(path, key, ConfScope.R);

				ZkData zd = FastConfigReader.getContent(path, ConfScope.R);

				String str = new String(zd.getBytes(), Charsets.UTF_8).substring(0, 20);

				logger.info("key: {},loc:{} ,value: {}", path, zd.getConfLocation(), str);

				//				value = ZkPropertiesUtil.getPropertyFromZookeeper("/tops/dev/global/properties/redis.properties", key, false, null);
				//				logger.info("key: {},value: {}", key, value);
			} catch (ZkConfReaderException e) {
				logger.info(e.getLocalizedMessage(), e);
			}

			DateTimeUtil.SleepSec(2);
		}

	}

	//	@Test
	public void getZookeeperServiceTest() {
		System.out.println(TopsConfigReaderProps.getZookeeperService());
		// System.out.println(reader.get());
	}

	//	@Test
	@SuppressWarnings("unused")
	public void getGlobalValue() {
		//		String key = "gta.mongodb.uri";
		String key = "redis.ip";
		//		String path = "properties/mongo-database.properties";
		String path = "properties/redis.properties";

		for (int i = 0; i < 1000; i++) {

			//			String value = TopsConfReader.getConfContent(path, key, ConfScope.R);
			String value;
			try {
				value = ZkPropertiesUtil.getPropertyFromZookeeper("/tops/dev/global/properties/redis.properties", key, false, null);
				logger.info("key: {},value: {}", key, value);
			} catch (PropertyException e) {
				logger.info(e.getLocalizedMessage(), e);
			}

			//			Connection attempt unsuccessful after 68273 (greater than max timeout of 60000). Resetting connection a

			//			System.out.println(value);
			DateTimeUtil.SleepSec(2);
		}

	}
}
