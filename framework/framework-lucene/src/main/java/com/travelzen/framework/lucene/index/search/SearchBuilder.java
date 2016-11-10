package com.travelzen.framework.lucene.index.search;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;
import com.travelzen.framework.lucene.index.config.IndexInitConfig;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.thread.pool.MultiThreadPool;
import com.travelzen.framework.util.FileUtils;

/**
 * @author qifeifei
 */
public class SearchBuilder extends AbstractSearchBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchBuilder.class);
	private static volatile SearchBuilder searchBuilder = null;

	private SearchBuilder() {
	}
	
	@Override
	public <IB extends AbstractIndexBean> IndexSearcher generateIndexSearch(Class<IB> iBean) throws Exception {
		if (null == iBean) {
			return null;
		}
		String indexBeanName = iBean.getSimpleName();
		if (null == searcherMap.get(indexBeanName)) {
			initIndexSearch(indexBeanName);
		}
		return searcherMap.get(indexBeanName);
	}

	@Override
	public void addRAMDirectory2Map(Directory ramDir, String iBeanName) throws Exception {
		if (null == ramDir || null == iBeanName) {
			return;
		}
		directoryMap.put(iBeanName, ramDir);
	}

	@Override
	public Directory getRAMDirectory(String iBeanName) throws Exception {
		if (null == iBeanName) {
			return null;
		}
		return directoryMap.get(iBeanName);
	}

	
	@Override
	public void check2UpdateIndexSearcher(String indexBeanName) throws Exception {
		if (StringUtils.isBlank(indexBeanName) || null == readerMap.get(indexBeanName)) {
			return;
		}
		// thread pool 
		final String iBN = indexBeanName;
		MultiThreadPool.addThread(new Runnable() {
			@Override
			public void run() {
				IndexReader newReader = null;
				try {
					newReader = IndexReader.openIfChanged(readerMap.get(iBN));
					if (null != newReader) {
						updateIndexSearch(newReader, iBN);
					}
				} catch (IOException e) {
					LOGGER.debug(e.getMessage(), e);
				}
			}
		});
	}

	
	/** 初始化目录文件 */
	private static synchronized void initDirectory(String indexBeanName) throws IOException {
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===initDirectory,beanName不能为空===");
			return;
		}
		if (null == directoryMap.get(indexBeanName)) {
			IndexInitConfig config = InitIndexUtil.getIndexConfigByBeanName(indexBeanName);
			if (null == config || StringUtils.isBlank(config.getDEFAULT_INDEX_PATH())) {
				LOGGER.debug("===initDirectory,读取初始化的配置文件为空==");
				return;
			}
			Directory dir = null;
			if (config.isFileDirectoryIndex()) {
				dir = FSDirectory.open(FileUtils.getFile(config.getDEFAULT_INDEX_PATH()));
			} else {
				dir = new RAMDirectory();
			}
			directoryMap.put(indexBeanName, dir);
		}
	}

	/** 初始化indexReader */
	@SuppressWarnings("deprecation")
	private static synchronized void initIndexReader(String indexBeanName) throws IOException {
		if (StringUtils.isBlank(indexBeanName)) {
			return;
		}
		if (null == readerMap.get(indexBeanName)) {
			if (null == directoryMap.get(indexBeanName)) {
				initDirectory(indexBeanName);
			}
			IndexReader reader = IndexReader.open(directoryMap.get(indexBeanName), true);
			readerMap.put(indexBeanName, reader);
		}
	}

	/** 初始化indexSearch */
	private static synchronized void initIndexSearch(String indexBeanName) {
		if (StringUtils.isBlank(indexBeanName)) {
			return;
		}
		IndexSearcher searcher = searcherMap.get(indexBeanName);
		if (null == searcher) {
			if (null == readerMap.get(indexBeanName)) {
				try {
					initIndexReader(indexBeanName);
				} catch (IOException e) {
					LOGGER.debug(e.getMessage(), e);
					System.out.println(e);
					return;
				}
			}
			searcherMap.put(indexBeanName, new IndexSearcher(readerMap.get(indexBeanName)));
		}
	}

	/** 更新indexSearcher */
	private static synchronized void updateIndexSearch(IndexReader newReader, String indexBeanName) throws IOException {
		if (null == newReader || StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===更新indexSearch的reader不能为空");
			return;
		}
		if (readerMap.get(indexBeanName) != newReader) {
			/** release old, update new into map */
			readerMap.get(indexBeanName).close();
			readerMap.put(indexBeanName, newReader);
			searcherMap.put(indexBeanName, new IndexSearcher(newReader));
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/** 获得单例 */
	public static SearchBuilder getSearchBuilder() {
		if (null == searchBuilder) {
			initSearchBuilder();
		}
		return searchBuilder;
	}

	/** 私有-初始化 */
	private static synchronized void initSearchBuilder() {
		if (null != searchBuilder) {
			return;
		}
		searchBuilder = new SearchBuilder();
	}

}
