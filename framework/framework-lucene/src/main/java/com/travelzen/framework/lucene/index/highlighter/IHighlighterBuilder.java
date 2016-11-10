package com.travelzen.framework.lucene.index.highlighter;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author qifeifei
 */
public interface IHighlighterBuilder {

	/** 根据已给条件生成高亮结果 */
	public String getHighlighterResult(String fieldName, String text, String highlighterWord, Map<String, Pair<String, String>> highlighterMap) throws Exception;

}
