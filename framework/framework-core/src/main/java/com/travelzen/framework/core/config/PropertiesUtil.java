package com.travelzen.framework.core.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import com.travelzen.framework.core.exception.PropertyException;
import com.travelzen.framework.core.util.TZUtil;

/**
 * properties的配置文件缓存模型
 * 
 * @author jiangningcui
 * 
 */
public class PropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static Map<String, Properties> mapProperties = new ConcurrentHashMap<String, Properties>();

	/**
	 * 更新缓存
	 * 
	 * @param resourcePath
	 * @param properties
	 */
	private static void saveOrUpdateProperties(String resourcePath, Properties properties) {
		if (!TZUtil.isEmpty(resourcePath) && !TZUtil.isEmpty(properties)) {
			mapProperties.put(resourcePath, properties);
		}
	}

	/**
	 * 从inpustrema中获得数据
	 */

	public static String getProperty(String resourcePath, byte[] data, String key, boolean needToCached, String defaultValue) {
		if (TZUtil.isEmpty(data))
			return null;
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		Properties properties = getProperties(resourcePath, inputStream, needToCached);
		return getValue(properties, key, defaultValue);
	}

	/**
	 * 从inpustrema中获得数据
	 */

	public static Properties getProperty(String resourcePath, byte[] data, boolean needToCached) {
		if (TZUtil.isEmpty(data))
			return null;
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		Properties properties = getProperties(resourcePath, inputStream, needToCached);
		return properties;
	}

	/**
	 * 从inpustrema中获得数据
	 */

	public static Properties getProperty(byte[] data) {
		if (TZUtil.isEmpty(data))
			return null;
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		Properties properties = getProperties(inputStream);
		return properties;
	}

	/**
	 * 保存某个数据到配置文件中
	 */
	public static void updatePropretiesFile(String filePath, String key, String value) {
		if (filePath == null || key == null)
			return;
		try {
			Properties dateProps = getLocalfileProperties(filePath, false);
			if (dateProps != null) {
				FileOutputStream fos = new FileOutputStream(filePath);
				dateProps.setProperty(key, value);
				dateProps.store(fos, "");
				fos.flush();
				fos.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public static Properties getPropertiesFromCache(String resourcePath, boolean needToCached) {
		if (needToCached && !TZUtil.isEmpty(resourcePath) && mapProperties.containsKey(resourcePath)) {
			return mapProperties.get(resourcePath);
		}
		return null;
	}

	/**
	 * loadProperties 注意,获得的不是Java的资源文件,而是外部的文件系统的文件
	 */
	public static Properties getLocalfileProperties(String resourcePath, boolean needToCached) {
		if (needToCached && !TZUtil.isEmpty(resourcePath) && mapProperties.containsKey(resourcePath)) {
			return mapProperties.get(resourcePath);
		}
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(resourcePath), "UTF-8"));
			Properties properties = new Properties();
			properties.load(input);
			if (needToCached) {
				saveOrUpdateProperties(resourcePath, properties);
			}
			return properties;
		} catch (IOException e1) {
			logger.error(e1.getLocalizedMessage() + " ->" + resourcePath);
			throw PropertyException.instanceConfNotFoundException("getLocalProperties上没有数据, resourcePath = " + resourcePath, e1);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * loadProperties 注意,获得的不是Java的资源文件,而是外部的文件系统的文件
	 */
	public static Properties getProperties(InputStream input) {
		if (input != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));

				Properties properties = new Properties();
				properties.load(reader);

				return properties;
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
				throw PropertyException.instanceConfNotFoundException("getLocalProperties上没有数据", e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * loadProperties 注意,获得的不是Java的资源文件,而是外部的文件系统的文件
	 */
	public static Properties getProperties(String resourcePath, InputStream input, boolean needToCached) {
		if (input != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));

				Properties properties = new Properties();
				properties.load(reader);
				if (needToCached) {
					saveOrUpdateProperties(resourcePath, properties);
				}
				return properties;
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
				throw PropertyException.instanceConfNotFoundException("getLocalProperties上没有数据", e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 从properties获得制定的值
	 * 
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getValue(Properties properties, String key, String defaultValue) {
		if (!TZUtil.isEmpty(properties)) {
			String value = properties.getProperty(key);
			if (TZUtil.isEmpty(value))
				value = defaultValue;
			//			value = StringUtils.trimToNull(value);
			return value;
		}
		return defaultValue;
	}

	public static InputStream getInputStreamFromResource(String resourcePath) {
		DefaultResourceLoader loader = new DefaultResourceLoader();

		Resource rc = loader.getResource(resourcePath);

		InputStream input = null;
		try {
			input = rc.getInputStream();
			return input;
		} catch (IOException e1) {
			logger.error(e1.getLocalizedMessage());
			e1.printStackTrace();
			return null;
		}
	}

	public static String getPropertyFromResource(String resourcePath, String key) {

		InputStream input = getInputStreamFromResource(resourcePath);

		if (input != null)
			try {
				Properties properties = new Properties();
				properties.load(input);
				return properties.getProperty(key).trim();
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		return null;
	}

}
