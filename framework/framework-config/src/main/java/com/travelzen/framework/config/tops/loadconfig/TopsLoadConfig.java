package com.travelzen.framework.config.tops.loadconfig;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.zookeeper.CreateMode;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.util.TopsConfigReaderProps;
import com.travelzen.framework.config.tops.util.TopsConfigPathResolver;
import com.travelzen.framework.config.tops.zk.TopsCuratorFramework;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.SystemUtil;
import com.travelzen.framework.core.util.TZUtil;

public class TopsLoadConfig {
	private static Logger logger = LoggerFactory.getLogger(TopsLoadConfig.class);
	public static final String TIME_FILE_PATH = TopsConfigPathResolver.getBaseLocalHostPath() + File.separator + TopsConfigPathResolver.getGlobalName() + File.separator
			+ "properties/configupdatetime.properties";
	public static final String LODING_CONFIG_TIME_KEY = "updateTime";
	public final static String DATESTR = "yyyyMMddHHmmss";

	/**
	 * 需要上传到zookeeper的文件: 1) 必须是文件 2) 更新时间需要大于dateTime, 如果dateTime为空,则就是需要上传 3)
	 * 排除time_file_path文件, 不是隐藏的文件
	 *
	 * @param file
	 * @param dateTime
	 * @return
	 */
	public boolean isNeedToLoad(File file, DateTime dateTime) {
		if (TZUtil.isEmpty(file)) {
			return false;
		}
		if (file.isFile() && !file.isHidden()) {
			String filePath = file.getAbsolutePath();
			if (TIME_FILE_PATH.equalsIgnoreCase(filePath)) {
				return false;
			}
			if (TZUtil.isEmpty(dateTime))
				return true;// 1399794511000 //1399797668000
			long modifiedTime = file.lastModified();
			if (dateTime.getMillis() < modifiedTime) {
				// Date date = new Date(modifiedTime);
				// logger.info("fileName: {}, date:{},dateTimes:{}  dateTime: {}, fileTime: {}",
				// file.getName(), date, dateTime, dateTime.getMillis(),
				// file.lastModified());
				return true;
			}
		}
		return false;
	}

	/**
	 * 导入所有config的数据到zookeeper中,这些数据时永久的
	 *
	 */
	public void loadALLConfigFile() {
		clear();
		loadUpdateConfigFile(null);
	}

	public void clear() {
		try {
			TopsCuratorFramework.getInstance().deleteNodesFromPath(TopsConfigReaderProps.getZookeeperConfigPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 增量数据导入 true:表示有返回 遍历所有文件
	 */
	public boolean loadUpdateConfigFile(DateTime dateTime) {
		File baseFilePath = new File(TopsConfigReaderProps.getBaseLocalHostPath());
		if (!TZUtil.isEmpty(baseFilePath)) {
			int count = updateAllFile(baseFilePath, dateTime);
			if (count > 0)
				return true;
		}
		return false;
	}

	/**
	 * 遍历所有文件,并进行更新
	 */
	public int updateAllFile(File file, DateTime dateTime) {
		if (TZUtil.isEmpty(file))
			return 0;
		if (file.isHidden()) // 如果是隐藏文件
			return 0;
		if (file.isFile()) {
			if (isNeedToLoad(file, dateTime)) {
				updateToZookeeper(file);
				return 1;
			}
		} else {
			File[] subfiles = file.listFiles();
			if (subfiles == null) {
				logger.error("Directory error! {}", file.getAbsolutePath());
				return 0;
			}
			int count = 0;
			for (File subfile : subfiles) {
				count += updateAllFile(subfile, dateTime);
			}
			return count;
		}
		return 0;
	}

	public void updateToZookeeper(File file, byte[] bytes) {

		if (TZUtil.isEmpty(file))
			return;

		String monitorNodePath = file.getAbsolutePath();
		monitorNodePath = TopsConfigReaderProps.getZookeeperConfigPath() + monitorNodePath.replace(TopsConfigPathResolver.getBaseLocalHostPath(), "");
		logger.info("update to zookeeper:  {}", monitorNodePath);

		if (SystemUtil.isWindows()) {
			monitorNodePath = monitorNodePath.replace("\\", "/");
		}

		try {
			TopsCuratorFramework.getInstance().deleteNode(monitorNodePath);
			TopsCuratorFramework.getInstance().createNode(monitorNodePath, bytes, CreateMode.PERSISTENT);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public void updateToZookeeper(File file) {
		try {
			byte[] bytes = FileUtils.readFileToByteArray(file);
			updateToZookeeper(file, bytes);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 根据时间决定是否导入数据
	 */
	public void loadingConfigFile() {
		/**
		 * 获得数据
		 */
		DateTime lastUpdateTime = getLastUpdateTime();
		if (TZUtil.isEmpty(lastUpdateTime)) {
			loadALLConfigFile();
			saveIndexTime(new DateTime());
			logger.info("loadingConfigFile : loadALLConfigFile");
		} else {
			if (loadUpdateConfigFile(lastUpdateTime)) {
				saveIndexTime(new DateTime());
				logger.info("loadingConfigFile : loadUpdateConfigFile");
			} else {
				logger.info("no loadingConfigFile");
			}
		}
	}

	public DateTime getLastUpdateTime() {
		String zooKeeperUpdateTime = TopsConfigReaderProps.getZooKeeperUpdateTime(TIME_FILE_PATH, LODING_CONFIG_TIME_KEY);
		if (TZUtil.isEmpty(zooKeeperUpdateTime))
			return null;
		DateTime time = DateTimeUtil.getDate(zooKeeperUpdateTime, DATESTR);
		return time;
	}

	public void saveIndexTime(DateTime dateTime) {
		if (TZUtil.isEmpty(dateTime))
			return;
		logger.info("dateTime is saving {}", dateTime);
		TopsConfigReaderProps.saveZooKeeperUpdateTime(TIME_FILE_PATH, LODING_CONFIG_TIME_KEY, DateTimeUtil.format(dateTime, DATESTR));
	}

}
