package com.travelzen.framwork.config.tops;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;

import com.travelzen.framework.config.tops.TopsConfEnum;
import com.travelzen.framework.config.tops.TopsConfReader;

public class SessionTimeoutTest {
	private static Logger logger = LoggerFactory.getLogger(SessionTimeoutTest.class);

	@Test
	public void getZookeeperServiceTest() {

		try {
			String activeProfile = TopsConfReader.getConfContent("properties/web-env-default.properties", AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,
					TopsConfEnum.ConfScope.R);
			if (StringUtils.isNotBlank(activeProfile)) {
				System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, activeProfile);
			}
		} catch (Exception e) {
			logger.warn("加载spring.profiles.active出现异常.", e);
		}

		try {

//			TopsCuratorFramework.getInstance().getCuratorFramework().delete().guaranteed().deletingChildrenIfNeeded().forPath("/tops");

		} catch (Exception e) {
			e.printStackTrace();
		}

		//		TopsCuratorFramework.getInstance().

		//		System.out.println(TopsCommonResourceUtil.getZookeeperService());
		// System.out.println(reader.get());
	}

}
