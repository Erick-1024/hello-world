package com.travelzen.framework.config.tops.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfLocation;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfSeqType;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.PropertyException;
import com.travelzen.framework.core.util.SystemUtil;
import com.travelzen.framework.core.util.TZUtil;

/**
 *  把相对路径解析到实际路径
 * @author liang.wang
 *
 */

public class TopsConfigPathResolver {
	private static final Logger logger = LoggerFactory.getLogger(TopsConfigPathResolver.class);
	public static String BASE_LOCALHOST_PATH;
	private static String BASE_IP_PATH;
	private static final String ZKSERVICE_FILENAME = "properties/zkService.properties";

	static {

		try {
			BASE_LOCALHOST_PATH = LocalPropertiesLoader.getPropertyFromResource("properties/confbase.properties", "conf_base_path");// "/opt/conf/tz-data";
			BASE_IP_PATH = LocalPropertiesLoader.getPropertyFromResource("properties/confbase.properties", "ip_path");// "/opt/conf/tz-data";

			if (SystemUtil.isWindows()) {
				BASE_LOCALHOST_PATH = "D:" + BASE_LOCALHOST_PATH.replace("/", "\\");
				BASE_IP_PATH = "D:" + BASE_IP_PATH.replace("/", "\\");

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static String getBaseLocalHostPath() {
		return BASE_LOCALHOST_PATH;
	}

	public static String getBaseZookeeperPath() {
		return LocalPropertiesUtil.getPropertyFromLocalFile(TopsConfigPathResolver.getZKservicePath(), "zkBasePath");
	}

	public static String getTopsIpPath() {
		return BASE_IP_PATH;
	}

	public static String getGlobalName() {
		return "global";
	}

	public static String getSeqPath(ConfSeqType type) {
		if (TZUtil.isEmpty(type))
			throw PropertyException.instanceConfNotFoundException("ConfSeqType is null");
		if (type == ConfSeqType.IP) {
			return TopsConfigPathResolver.getBaseZookeeperPath() + File.separator + TopsConfigReaderUtil.getApplicationName() + File.separator + TopsConfigReaderUtil.getLocalIP();
		} else {
			return TopsConfigPathResolver.getBaseZookeeperPath() + File.separator + TopsConfigReaderUtil.getApplicationName();
		}
	}

	/**
	 * 获得主机基本路径
	 * 
	 * @param location
	 * @return
	 */
	public static String getBaseHostPath(ConfLocation location) {
		if (TZUtil.isEmpty(location)) {
			throw new RuntimeException();
		}
		if (location == ConfLocation.ZK)
			return getBaseZookeeperPath();
		if (location == ConfLocation.LOCALHOST)
			return getBaseLocalHostPath();
		return getBaseLocalHostPath();
	}

	/**
	 * 必须在文件系统中
	 * 
	 * @return
	 */
	public static String getZKservicePath() {
		return getBaseLocalHostPath() + File.separator + getGlobalName() + File.separator + ZKSERVICE_FILENAME;
	}

	public static String getAppConfigPath(String fileName, ConfScope confScope, ConfLocation location) {

		String sp = File.separator;
		if (location == ConfLocation.ZK) {
			sp = "/";
		}

		if (TZUtil.isEmpty(fileName) || TZUtil.isEmpty(confScope)) {
			throw new RuntimeException();
		}
		String path = null;
		String appName = null;
		switch (confScope) {
		case G: // 全局
				// /opt/conf/tz-data/${appname}/sampleconf.xml
			appName = TopsConfigReaderUtil.getApplicationName();
			path = getBaseHostPath(location) + sp + appName + sp + fileName;
			break;
		case M: // /opt/conf/tz-data/${appname}/${ip}/sampleconf.xml
			appName = TopsConfigReaderUtil.getApplicationName();
			path = getBaseHostPath(location) + sp + appName + sp + TopsConfigReaderUtil.getLocalIP() + sp + fileName;
			break;
		case GA:
			// ConfScope.GA
			// ${appname}/3/sampleconf.xml
			appName = TopsConfigReaderUtil.getApplicationName();
			path = getBaseHostPath(location) + sp + appName + sp + TopsConfReader.getAppSeq() + sp + fileName;
			break;
		case MA:
			// ConfScope.MA
			// ${appname}/{$ip}/3/sampleconf.xml
			appName = TopsConfigReaderUtil.getApplicationName();
			path = getBaseHostPath(location) + sp + appName + sp + TopsConfigReaderUtil.getLocalIP() + sp + TopsConfReader.getIPSeq() + sp + fileName;
			break;
		case U:
			// ConfScope.U
			// ${appname}/${ip}/${appid}/sampleconf.xml
			appName = TopsConfigReaderUtil.getApplicationName();
			path = getBaseHostPath(location) + sp + appName + sp + TopsConfigReaderUtil.getLocalIP() + sp + TopsConfReader.getAppid() + sp + fileName;
			break;
		case R:
			path = getBaseHostPath(location) + sp + getGlobalName() + sp + fileName;
			break;
		default: // 默认为R
			path = getBaseHostPath(location) + sp + getGlobalName() + sp + fileName;
			break;
		}

		logger.debug("path:{}, scope:{}", path, confScope);
		return path;
	}
}
