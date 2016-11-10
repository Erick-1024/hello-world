package com.cana.member.authorization.common;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

public class MemberCommonConfig {

	private static final Logger LGR = LoggerFactory.getLogger(MemberCommonConfig.class);

	private static Properties config = null;

	static {
		String path = null;
		try {
			path = "properties/member-common-global.properties";
			config = TopsConfReader.getConfProperties(path, ConfScope.R);
		} catch (Exception e) {
			LGR.error("load properties(" + path + ") failed." ,e);
		}
		
		try {
			path = "properties/member-common.properties";
			Properties ppts = TopsConfReader.getConfProperties(path, ConfScope.R);
			for (Object oKey : ppts.keySet()) {
				String key = oKey.toString();
				config.setProperty(key, ppts.getProperty(key));
			}
		} catch (Exception e) {
			LGR.warn("load member-common.properties failed, all global properties will be kept.");
		}
	}

	public static String get(String key) {
		return config.getProperty(key);
	}

	public static List<String> getList(String key) {
		String property = config.getProperty(key);
		if (StringUtils.isBlank(property)) {
			return null;
		}
		return Arrays.asList(property.split("\\|"));
	}

	public static int getInt(String key) {
		return Integer.parseInt(get(key));
	}

}
