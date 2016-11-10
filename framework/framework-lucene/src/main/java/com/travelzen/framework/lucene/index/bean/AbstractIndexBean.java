package com.travelzen.framework.lucene.index.bean;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author qifeifei
 */
public abstract class AbstractIndexBean {

	/** 需要分词的字段 */
	public abstract List<String> getAnalyzedField();

	/** index字段 */
	public abstract List<String> getIndexField();

	/** 存储字段 */
	public abstract List<String> getStoreField();

	/** 字段的评分 */
	public abstract Map<String, Float> getFieldBoost();

	/** 需要高亮的字段(为文本类型) */
	public abstract Map<String, Pair<String, String>> getHighlighter();
	
}
