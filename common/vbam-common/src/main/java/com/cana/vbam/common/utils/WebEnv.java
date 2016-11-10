package com.cana.vbam.common.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

/**
 *
 * @since Nov 11, 20159:51:58 AM
 * @author dev1
 *
 */
public class WebEnv {

	private static final Logger LOG = LoggerFactory.getLogger(WebEnv.class);

	private static Properties pptConf = null;
	
	static {
		String path = null;
		try {
			path = "properties/web-env-default.properties";
			pptConf = TopsConfReader.getConfProperties(path, ConfScope.R);
		} catch (Exception e) {
			LOG.error("load properties(" + path + ") failed." ,e);
		}

		try {
			path = "properties/web-env.properties";
			Properties ppts = TopsConfReader.getConfProperties(path, ConfScope.G);
			for (Object oKey : ppts.keySet()) {
				String key = oKey.toString();
				pptConf.setProperty(key, ppts.getProperty(key));
			}
		} catch (Exception e) {
			LOG.warn("load web-env.properties failed, all default env will be kept.");
		}
		
	}

	public static String get(String key) {
		return pptConf.getProperty(key);
	}

	public static String get(String key, String defaultValue) {
		return pptConf.getProperty(key, defaultValue);
	}
	
	public static String getVBAMPlatformPath(){
		return get("server.path.vbamWeb");
	}

	public static String getVBAMPlatformLoginUrl() {
	    return getVBAMPlatformPath() + "facade/signin";
	}
	
	public static String getMediaPath(){
		return get("server.path.media");
	}

}