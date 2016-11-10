package com.travelzen.framework.config.tops.util;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.core.config.PropertiesUtil;

/**
 * properties的配置文件缓存模型
 * 
 * @author jiangningcui
 * 
 */
public class LocalPropertiesUtil {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(LocalPropertiesUtil.class);

	/**
	 * 从资源文件中获得制定的数据 如果是needToCached的话,需要将其cached
	 * 
	 * @param resourcePath
	 * @param key
	 * @param needToCached
	 * @return
	 */
	public static String getPropertyFromLocalFile(String resourcePath, String key, boolean needToCached, String defaultValue) {
		Properties properties = PropertiesUtil.getLocalfileProperties(resourcePath, needToCached);
		return PropertiesUtil.getValue(properties, key, defaultValue);
	}

	/**
	 * 从资源文件中获得制定的数据 如果是needToCached的话,需要将其cached
	 * 
	 * @param resourcePath
	 * @param key
	 * @param needToCached
	 * @return
	 */
	public static Properties getPropertyFromLocalFile(String resourcePath, boolean needToCached) {
		Properties properties = PropertiesUtil.getLocalfileProperties(resourcePath, needToCached);
		return properties;
	}

	/**
	 * 从资源文件中获得制定的数据 如果是needToCached的话,需要将其cached 不走缓存
	 * 
	 * @param resourcePath
	 * @param needToCached
	 * @return
	 */
	public static String getPropertyFromLocalFile(String resourcePath, String key, String defaultValue) {
		return getPropertyFromLocalFile(resourcePath, key, false, defaultValue);
	}

	/**
	 * 从资源文件中获得制定的数据 不走缓存
	 * 
	 * @param resourcePath
	 * @param needToCached
	 * @return
	 */
	public static String getPropertyFromLocalFile(String resourcePath, String key) {
		return getPropertyFromLocalFile(resourcePath, key, false, null);
	}

	/**
	 * 保存某个数据到配置文件中
	 */
	public static void updatePropretiesFile(String timeFile, String key, String value) {
		PropertiesUtil.updatePropretiesFile(timeFile, key, value);
	}

	/**
	 * 获取指定路径的properties文件
	 * 
	 * @param resourcePath
	 * @param needToCached
	 * @return
	 */
	public static Properties getProperties(String resourcePath, boolean needToCached) {
		return PropertiesUtil.getLocalfileProperties(resourcePath, needToCached);
	}

}
