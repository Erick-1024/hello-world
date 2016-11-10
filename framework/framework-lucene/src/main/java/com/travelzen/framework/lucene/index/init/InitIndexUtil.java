package com.travelzen.framework.lucene.index.init;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;
import com.travelzen.framework.lucene.index.config.IndexInitConfig;

/**
 * @author qifeifei
 */
public class InitIndexUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(InitIndexUtil.class);

	private static Map<String, IndexInitConfig> indexConfigMap = new ConcurrentHashMap<>();

	/** 初始化配置 */
	public static <IB extends AbstractIndexBean> void initIndex(String indexPath, boolean isFileDirectory, Analyzer analyzer, boolean isCreate, Class<IB> ib) {
		if (!checkInitConfig(indexPath, isFileDirectory, analyzer, ib)) {
			LOGGER.debug("===检出初始化配置失败===");
			return;
		}
		IndexInitConfig config = new IndexInitConfig();
		String indexBeanClassName = ib.getSimpleName();
		config.setIndexClassBeanName(indexBeanClassName);
		if (StringUtils.isNotBlank(indexPath)) {
			config.setDEFAULT_INDEX_PATH(indexPath + "/" + indexBeanClassName);
		} else {
			config.setDEFAULT_INDEX_PATH(config.getDEFAULT_INDEX_PATH() + "/" + indexBeanClassName);
		}
		config.setFileDirectoryIndex(isFileDirectory);
		config.setCreate(isCreate);
		indexConfigMap.put(indexBeanClassName, config);
		//indexBuilder.checkInitIndexSystem(indexBeanClassName);
	}

	/** 根据类名类获取配置 */
	public static IndexInitConfig getIndexConfigByBeanName(String ibeanName) {
		if (StringUtils.isBlank(ibeanName)) {
			LOGGER.debug("===根据类名获取配置的参数不能为空===");
			return null;
		}
		return indexConfigMap.get(ibeanName);
	}

	/** 检查初始化配置 */
	private static <IB extends AbstractIndexBean> boolean checkInitConfig(String indexPath, boolean isFileDir, Analyzer analyzer, Class<IB> ib) {
		if (null == ib) {
			LOGGER.debug("===初始化配置class不能为空===");
			return false;
		}
		if (StringUtils.isBlank(indexPath) && isFileDir == true) {
			LOGGER.debug("===文件目录的地址不能为空===");
			return false;
		}
		if (null == analyzer) {
			LOGGER.debug("===分词为空将使用默认IK分词===");
		}
		return true;
	}

}
