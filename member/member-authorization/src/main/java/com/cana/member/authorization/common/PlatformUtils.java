package com.cana.member.authorization.common;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.vbam.common.enums.Platform;


public class PlatformUtils {

	private static final Logger LOG = LoggerFactory.getLogger(PlatformUtils.class);

	private static Platform PLATFORM = null;

	static {
		try {
			Class<?> clz = Class.forName("com.cana.front.platform.PlatformHolder");
			PLATFORM = (Platform) clz.getDeclaredMethod("get").invoke(clz);
		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			LOG.warn("get platform failed. set to OPERATOR");
			PLATFORM = Platform.BIZ;
		}
	}

	public static Platform getPlatform() {
		return PLATFORM;
	}

}
