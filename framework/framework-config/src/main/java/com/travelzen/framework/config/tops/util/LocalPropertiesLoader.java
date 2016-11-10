package com.travelzen.framework.config.tops.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * properties的配置文件缓存模型
 * 
 * @author jiangningcui
 * 
 */
public class LocalPropertiesLoader {
	private static Logger logger = LoggerFactory.getLogger(LocalPropertiesLoader.class);

	/**
	 * 从资源文件中获取数据
	 * 
	 * @param resourcePath
	 * @param key
	 * @return
	 */
	public static String getPropertyFromResource(String resourcePath, String key) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();

		Resource rc = ctx.getResource(resourcePath);

		InputStream input = null;
		try {
			input = rc.getInputStream();
		} catch (IOException e1) {
			logger.error(e1.getLocalizedMessage());
			e1.printStackTrace();
			ctx.close();
			return null;
		}
		if (input != null) {
			try {
				Properties properties = new Properties();
				properties.load(input);
				return properties.getProperty(key).trim();
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} finally {
				try {
					ctx.close();
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
