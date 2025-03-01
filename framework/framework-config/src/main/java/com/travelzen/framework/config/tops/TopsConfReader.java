package com.travelzen.framework.config.tops;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfSeqType;
import com.travelzen.framework.config.tops.util.TopsConfigPathResolver;
import com.travelzen.framework.config.tops.zk.TopsZookeeperLock;
import com.travelzen.framework.core.config.PropertiesUtil;
import com.travelzen.framework.core.exception.PropertyException;
import com.travelzen.framework.core.util.TZUtil;

public class TopsConfReader {
	private static Logger logger = LoggerFactory.getLogger(TopsConfReader.class);
	private static String containerPort;
	private static String seq;
	private static String appSeq;
	private static final String NIL = "nil";

	/**
	 * 其他应用专用
	 * 
	 * @param ars
	 */
	public static void setAppid(String appid) {
		if (TZUtil.isEmpty(appid) || NIL.equalsIgnoreCase(appid))
			throw PropertyException.instanceConfNotFoundException("appid is null");
		containerPort = appid;
	}

	/**
	 * 其他应用专用
	 * 
	 * @param ars
	 */
	public static void setAppSeq(String pid) {
		if (TZUtil.isEmpty(pid) || NIL.equalsIgnoreCase(pid))
			throw PropertyException.instanceConfNotFoundException("appSeq is null");
		appSeq = pid;
	}

	/**
	 * tomcat专用
	 * 
	 * @param port
	 */
	public static void setContainerPort(String port) {
		if (TZUtil.isEmpty(port) || NIL.equalsIgnoreCase(port))
			throw PropertyException.instanceConfNotFoundException("port is null");
		containerPort = port;
	}

	/**
	 * 获得这个主机appid
	 * 
	 * @param args
	 */
	public static String getAppid() {
		if (TZUtil.isEmpty(containerPort))
			throw PropertyException.instanceConfNotFoundException("containerPort is null");
		return containerPort;
	}

	/**
	 * 获得这个主机的seq
	 * 
	 * @param args
	 */
	public static String getIPSeq() {
		if (TZUtil.isEmpty(seq)) {// 还没有获得过的
			synchronized (TopsConfReader.class) {
				if (TZUtil.isEmpty(seq)) { // double checked
					if (!TZUtil.isEmpty(containerPort)) {
						seq = containerPort;
					} else {
						try {
							TopsZookeeperLock lock = TopsZookeeperLock.wantToLock(TopsConfigPathResolver.getSeqPath(ConfSeqType.IP));
							if (lock != null) {
								logger.info("--->lock is {}", lock.getSeq());
								seq = lock.getSeq();
							} else {
								seq = NIL;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							seq = NIL;
						}

					}
				}
			}
		}
		if (seq.equalsIgnoreCase(NIL))
			throw new RuntimeException();
		return seq;
	}

	/**
	 * 获得这个主机的seq
	 * 
	 * @param args
	 */
	public static String getAppSeq() {
		if (TZUtil.isEmpty(appSeq)) {// 还没有获得过的
			synchronized (TopsConfReader.class) {
				if (TZUtil.isEmpty(appSeq)) { // double checked
					try {
						TopsZookeeperLock lock = TopsZookeeperLock.wantToLock(TopsConfigPathResolver.getSeqPath(ConfSeqType.APP));
						if (lock != null) {
							logger.info("--->lock is {}", lock.getSeq());
							appSeq = lock.getSeq();
						} else {
							appSeq = NIL;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						appSeq = NIL;
					}
				}
			}
		}
		if (appSeq.equalsIgnoreCase(NIL))
			throw new RuntimeException();
		return appSeq;
	}

	/*
	 * if the zkService.properties is not exist, we just load file from local
	 * filesystem,
	 * 
	 * but before the calling, we'd better call
	 * "TopsConfUtil.setApplicationName("xxx-applicationName")"
	 * 
	 * testxxx
	 */

	public static byte[] getConfContent(String fileName, ConfScope scope) throws Exception {
		return FastConfigReader.getContent(fileName, scope).data;
	}

	/**
	 * 
	 * 获得文件的变量
	 * 
	 * @param path
	 * @param scope
	 * @return
	 */
	public static String getConfContent(String fileName, String key, ConfScope scope) {
		return FastConfigReader.getPropertyValue(fileName, key, scope);
	}

	/**
	 * 获得Int
	 */
	public static int getConfContentForInt(String fileName, String key, ConfScope scope) {
		String value = getConfContent(fileName, key, scope);

		if (!TZUtil.isEmpty(value)) {
			return Integer.parseInt(value);
		}
		return -1;
	}

	/**
	 * 获得Long
	 */
	public static long getConfContentForLong(String fileName, String key, ConfScope scope) {
		String value = getConfContent(fileName, key, scope);

		if (!TZUtil.isEmpty(value)) {
			return Long.parseLong(value);
		}
		return -1;
	}

	/**
	 * return true or false
	 * 
	 * @param fileName
	 * @param key
	 * @param scope
	 * @return
	 */
	public static boolean getConfContentForBoolean(String fileName, String key, ConfScope scope) {
		return "true".equalsIgnoreCase(getConfContent(fileName, key, scope));
	}

	/**
	 * 获取全局配置文件的变量
	 * 
	 * @param path
	 */
	public static String getConfContent(String fileName, String key) {
		return getConfContent(fileName, key, ConfScope.G);
	}

	/**
	 * 从zookeeper获取指定作用域下的指定名称的properties文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param scope
	 *            文件作用域
	 * @return
	 */
	public static Properties getConfProperties(String fileName, ConfScope scope) {

		byte[] data = FastConfigReader.getContent(fileName, scope).data;

		return PropertiesUtil.getProperty(data);

	}

}
