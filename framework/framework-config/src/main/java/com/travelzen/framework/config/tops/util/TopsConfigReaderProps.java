package com.travelzen.framework.config.tops.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  
 * @author liang.wang
 *  读取本地配置文件中的属性
	比如  zk连接串， isLocal属性
 *
 */
public class TopsConfigReaderProps {

	private static final Logger logger = LoggerFactory.getLogger(TopsConfigReaderProps.class);

	/**
	 * 获得zookeeper地址
	 * 
	 */
	public static boolean isOnlyLocal() {

		//if the ip address in  "/data/etc/local/ip"  is "local"
		if (TopsAppRegistry.isLocal()) {
			return true;
		}

		//read data from  "/opt/conf/tz-data/global/properties/zkService.properties", key?isOnlyLocal=true"
		logger.debug("reading 'isOnlyLocal' from path:{}", TopsConfigPathResolver.getZKservicePath());
		String value = LocalPropertiesUtil.getPropertyFromLocalFile(TopsConfigPathResolver.getZKservicePath(), "isOnlyLocal");
		logger.debug("'isOnlyLocal':{}", value);

		if ("true".equalsIgnoreCase(value)) {
			return true;
		}
		return false;
	}

	/**
	 * 获得zookeeper的地址
	 */
	public static String getZookeeperService() {

		logger.debug("reading zk connectionString from:{}", TopsConfigPathResolver.getZKservicePath());
		String zkConnectionString = LocalPropertiesUtil.getPropertyFromLocalFile(TopsConfigPathResolver.getZKservicePath(), "connectionString");
		logger.debug("zkConnectionString:{}", zkConnectionString);
		return zkConnectionString;
	}

	/**
	 * 获得zookeeper目录中config的基本路径
	 */
	public static String getZookeeperConfigPath() {
		return LocalPropertiesUtil.getPropertyFromLocalFile(TopsConfigPathResolver.getZKservicePath(), "zkBasePath", TopsConfigPathResolver.getBaseZookeeperPath());
	}

	/**
	 * 获得zookeeper同步的时间
	 */
	public static String getZooKeeperUpdateTime(String timeFile, String key) {
		return LocalPropertiesUtil.getPropertyFromLocalFile(timeFile, key, "19700101000000");
	}

	/**
	 * 保存zookeeper同步时间
	 */
	public static void saveZooKeeperUpdateTime(String timeFile, String key, String value) {
		LocalPropertiesUtil.updatePropretiesFile(timeFile, key, value);
	}

	public static String getBaseLocalHostPath() {
		return TopsConfigPathResolver.getBaseLocalHostPath();
	}
}
