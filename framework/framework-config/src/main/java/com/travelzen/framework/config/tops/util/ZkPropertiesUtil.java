package com.travelzen.framework.config.tops.util;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.zk.TopsCuratorFramework;
import com.travelzen.framework.core.config.PropertiesUtil;
import com.travelzen.framework.core.exception.PropertyException;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.core.util.TZUtil;

public class ZkPropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(ZkPropertiesUtil.class);
	private static Map<String, byte[]> zkMap = new ConcurrentHashMap<String, byte[]>();

	public static byte[] getContentFromZookeeper(String resourcePath, boolean needToCache) {
		byte[] data = ZkPropertiesUtil.getValueFromZk(resourcePath, needToCache);
		return data;
	}

	public static String getPropertyFromZookeeper(String resourcePath, String key, boolean needToCached, String defaultValue) {
		Properties properties = PropertiesUtil.getPropertiesFromCache(resourcePath, needToCached);
		if (!TZUtil.isEmpty(properties)) {
			return PropertiesUtil.getValue(properties, key, defaultValue);
		}
		/**
		 * 导入
		 */
		byte[] data = ZkPropertiesUtil.getValueFromZk(resourcePath, needToCached);
		return PropertiesUtil.getProperty(resourcePath, data, key, needToCached, null);
	}

	/**
	 * 从zookeeper获取指定路径的properties文件
	 * 
	 * @param resourcePath
	 * @param needToCached
	 * @return
	 */
	public static Properties getPropertiesFromZookeeper(String resourcePath, boolean needToCached) {

		Properties properties = PropertiesUtil.getPropertiesFromCache(resourcePath, needToCached);
		if (null == properties) {
			byte[] data = ZkPropertiesUtil.getValueFromZk(resourcePath, needToCached);
			properties = PropertiesUtil.getProperty(resourcePath, data, needToCached);
		}

		return properties;
	}

	private static byte[] getValueFromZk(String zkPath, boolean isNeedCached) {
		if (StringUtil.isEmpty(zkPath))
			return null;
		zkPath = zkPath.trim();
		if (isNeedCached && zkMap.containsKey(zkPath)) {
			byte[] value = zkMap.get(zkPath);
			return value;
		} else {
			try {
				DataWatcher dataWatcher = null;
				if (isNeedCached) {
					dataWatcher = new DataWatcher();
				}
				Stat stat = TopsCuratorFramework.getInstance().checkNodePath(zkPath, dataWatcher);
				if (stat != null) {// 节点存在，去获取数据
					byte[] data = TopsCuratorFramework.getInstance().getData(zkPath);
					if (isNeedCached && !TZUtil.isEmpty(data)) {
						zkMap.put(zkPath, data);
					}
					return data;
				} else {
					zkMap.remove(zkPath);
					return null;
				}
			} catch (Throwable thr) {
				logger.error("获取zookeeper节点数据异常," + zkPath, thr);
				throw PropertyException.instanceConfNotFoundException("zookeeper上没有数据", thr);
			}
		}
	}

	public static byte[] getValueFromZKNoCache(String zkPath) {
		return getValueFromZk(zkPath, false);
	}

	private static class DataWatcher implements CuratorWatcher {

		@Override
		public void process(WatchedEvent event) throws Exception {
			try {
				String path = StringUtil.trim(event.getPath());
				if (event.getType() == EventType.NodeDataChanged || event.getType() == EventType.NodeCreated) {
					try {
						byte[] data = TopsCuratorFramework.getInstance().getData(path);
						if (TZUtil.isEmpty(data))
							zkMap.put(path, data);
					} catch (Throwable thr) {
						logger.error("", thr);
					}
				} else if (event.getType() == EventType.NodeDeleted) { // 配置不存在了
					zkMap.remove(path);
				}
			} catch (Throwable thr) {
				logger.error("", thr);
			}

		}
	}
}
