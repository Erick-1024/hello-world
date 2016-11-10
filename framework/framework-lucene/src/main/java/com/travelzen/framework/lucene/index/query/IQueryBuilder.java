package com.travelzen.framework.lucene.index.query;

import org.apache.lucene.search.Query;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;

/**
 * @author qifeifei
 */
public interface IQueryBuilder {

	public Query generateQueryByType(String fieldName, Object value);

	public Query generateNumericQuery(String fieldName, Object minValue, Object maxValue, boolean minInclude, boolean maxInclude);

	// 根据类来自动生成查询对象
	public <IB extends AbstractIndexBean> Query generateQueryByIndexBean(IB ibean) throws Exception;

}
