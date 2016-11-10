package com.travelzen.framework.lucene.index.query.bean;

import java.util.List;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField;

/**
 * @author qifeifei
 */
public abstract class AbstractQueryExample {

	// 获取已经构建的query
	public abstract Query getBuildedQuery();

	// 获取排序sort
	public abstract List<SortField> getSortFields();
	
	// 获取需要高亮的词语
	public abstract String getNeedHighlighterWord();
	
}
