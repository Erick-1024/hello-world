package com.travelzen.framework.lucene.index;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.logger.core.TZMarkers;
import com.travelzen.framework.lucene.index.analyzer.AnalyzerBuilder;
import com.travelzen.framework.lucene.index.search.SearchBuilder;
import com.travelzen.framework.util.FileUtils;

/**
 * @author qifeifei
 */
@SuppressWarnings("deprecation")
public abstract class AbstractIndexBuilder implements IIndexBuilder {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractIndexBuilder.class);
	protected static AnalyzerBuilder analyzerBuilder = AnalyzerBuilder.getAnalyzerBuilder();
	private static SearchBuilder searchBuilder = SearchBuilder.getSearchBuilder();
	
	protected Map<String, IndexWriter> writerMap = new ConcurrentHashMap<>();

	// 文件
	protected void initFs(String fileName, boolean isCreate, String indexBeanName) {
		if (writerMap.containsKey(indexBeanName)) {
			LOGGER.debug("==indexBeanName:[{}],已经初始化过了===", indexBeanName);
			return;
		}
		try {
			IndexWriter writer = init(FSDirectory.open(FileUtils.getFile(fileName)), analyzerBuilder.getAnalyzer(), isCreate);
			writerMap.put(indexBeanName, writer);
		} catch (Exception e) {
			LOGGER.warn(TZMarkers.p2, "init error {}", e.getMessage(), e);
		}
	}

	// 內存
	protected void initRam(String fileName, boolean isCreate, String indexBeanName) {
		if (writerMap.containsKey(indexBeanName)) {
			LOGGER.debug("==indexBeanName:[{}],已经初始化过了..===", indexBeanName);
			return;
		}
		try {
			RAMDirectory ramDir = new RAMDirectory();
			/** remember this ram directory */
			searchBuilder.addRAMDirectory2Map(ramDir, indexBeanName);
			IndexWriter writer = init(ramDir, analyzerBuilder.getAnalyzer(), isCreate);
			writerMap.put(indexBeanName, writer);
		} catch (Exception e) {
			LOGGER.warn(TZMarkers.p2, "init error {}", e.getMessage(), e);
		}
	}

	// 私有-初始化
	protected IndexWriter init(Directory directory, Analyzer analyzer, boolean isCreate) {
		IndexWriter writer = null;
		if (null == directory) {
			LOGGER.warn(TZMarkers.p4, "directory not exist");
			return null;
		}
		// check if locked
		try {
			if (IndexWriter.isLocked(directory)) {
				LOGGER.debug("===directory is locked, now try to unlock it ===");
				IndexWriter.unlock(directory);
			}
		} catch (IOException e1) {
			LOGGER.debug("directory 解锁失败", e1.getMessage(), e1);
		}
		try {
			if (null == analyzerBuilder.getAnalyzer()) {
				analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
			}
			if (isCreate) {
				writer = new IndexWriter(directory, analyzer, isCreate, MaxFieldLength.LIMITED);
			} else {
				writer = new IndexWriter(directory, analyzer, MaxFieldLength.LIMITED);
			}
			writer.setInfoStream(System.out);
		} catch (Exception e) {
			LOGGER.warn(TZMarkers.p2, "writer init error{}", e.getMessage(), e);
		}
		return writer;
	}

}
