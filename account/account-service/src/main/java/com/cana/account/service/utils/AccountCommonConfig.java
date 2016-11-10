//package com.cana.account.service.utils;
//
//import java.util.Properties;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
//import com.travelzen.framework.config.tops.TopsConfReader;
//
///**
// *
// * @since Nov 11, 20159:51:58 AM
// * @author dev1
// *
// */
//public class AccountCommonConfig {
//
//	private static final Logger LOG = LoggerFactory.getLogger(AccountCommonConfig.class);
//
//	private static Properties pptConf = null;
//	
//	static {
//		String path = null;
//		try {
//			path = "properties/account-server-common.properties";
//			Properties ppts = TopsConfReader.getConfProperties(path, ConfScope.G);
//			for (Object oKey : ppts.keySet()) {
//				String key = oKey.toString();
//				pptConf.setProperty(key, ppts.getProperty(key));
//			}
//		} catch (Exception e) {
//			LOG.warn("load account-server-common.properties failed, all default env will be kept.");
//		}
//		
//	}
//
//	public static String get(String key) {
//		return pptConf.getProperty(key);
//	}
//
//	public static String get(String key, String defaultValue) {
//		return pptConf.getProperty(key, defaultValue);
//	}
//	
//	public static String getDataSaveMoths(){
//		return get("server.path.vbamWeb");
//	}
//
//}