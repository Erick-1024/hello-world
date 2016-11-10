package com.travelzen.framework.config.tops;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.zookeeper.CreateMode;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfLocation;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.util.TopsConfigPathResolver;
import com.travelzen.framework.config.tops.zk.TopsCuratorFramework;

public class TopsConfWriter {

	/**
	 * 将params中的key-value写入文件
	 * @param fileName
	 * @param params
	 * @param scope
	 */
	public static void writeConfContent(String fileName, Map<String, String> params, ConfScope scope) throws Exception{
		Properties properties = new Properties();
		for(Map.Entry<String, String> entry : params.entrySet()){
			properties.setProperty(entry.getKey(), entry.getValue());
		}
		writeConfContent(fileName, properties, scope);
	}
	
	/**
	 * 将params中的properties写入文件
	 * @param fileName
	 * @param properties
	 * @param scope
	 */
	public static void writeConfContent(String fileName, Properties properties, ConfScope scope) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		properties.store(baos, "");
		writeConfContent(fileName, baos.toByteArray(), scope);
	}
	
	/**
	 * 将data的内容写入文件
	 * @param fileName
	 * @param data
	 * @param scope
	 */
	public static void writeConfContent(String fileName, byte[] data, ConfScope scope) throws Exception{
		String fullZkPath = TopsConfigPathResolver.getAppConfigPath(fileName, scope, ConfLocation.ZK);
		TopsCuratorFramework.getInstance().deleteNode(fullZkPath);
		TopsCuratorFramework.getInstance().createNode(fullZkPath, data, CreateMode.PERSISTENT);
	}
	
}
