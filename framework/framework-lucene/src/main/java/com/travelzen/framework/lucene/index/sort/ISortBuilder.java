package com.travelzen.framework.lucene.index.sort;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

/**
 * @author qifeifei
 */
public interface ISortBuilder {
	
	public Sort generateSort(SortField[] fields);
	
	public SortField generateSortField(String fieldName,String type,boolean reverse);
	
	/**
	 * 根据评分或者文档排序
	 * @author hu
	 * @param type
	 * @param reverse
	 * @return
	 */
	public SortField generateScoreOrDocSortField(int type, boolean reverse);
}
