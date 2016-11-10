package com.travelzen.framework.lucene.index.query;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.logger.core.TZMarkers;
import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;

/**
 * @author qifeifei
 */
public class QueryBuilder extends AbstractQueryBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilder.class);
	private static volatile QueryBuilder queryBuilder = null;

	private QueryBuilder() {
	}
	
	// 生成查询最基础的query
	@Override
	public Query generateQueryByType(String fieldName, Object value) {
		String typeName = value.getClass().getSimpleName();
		Query query = null;
		Term term = null;
		switch (typeName) {
		case "String":
			term = new Term(fieldName, value.toString());
			query = new TermQuery(term);
			break;
		case "Character":
			term = new Term(fieldName, value.toString());
			query = new TermQuery(term);
			break;
		case "Long":
			query = NumericRangeQuery.newLongRange(fieldName, Long.parseLong(value.toString()), Long.parseLong(value.toString()), true, true);
			break;
		case "Integer":
			query = NumericRangeQuery.newIntRange(fieldName, Integer.parseInt(value.toString()), Integer.parseInt(value.toString()), true, true);
			break;
		case "Float":
			query = NumericRangeQuery.newFloatRange(fieldName, Float.parseFloat(value.toString()), Float.parseFloat(value.toString()), true, true);
			break;
		case "Double":
			query = NumericRangeQuery.newDoubleRange(fieldName, Double.parseDouble(value.toString()), Double.parseDouble(value.toString()), true, true);
			break;
		case "Boolean":
			int booleanInt = Boolean.parseBoolean(value.toString()) == true ? 1 : 0;
			query = NumericRangeQuery.newIntRange(fieldName, booleanInt, booleanInt, true, true);
			break;
		}
		return query;
	}

	// 生成查询最基础的query(数字)
	@Override
	public Query generateNumericQuery(String fieldName, Object min, Object max, boolean minInclude, boolean maxInclude) {
		String typeName = null;
		if (null != min) {
			typeName = min.getClass().getSimpleName();
		}
		if (typeName == null && null != max) {
			typeName = max.getClass().getSimpleName();
		}
		if (StringUtils.isBlank(typeName)) {
			LOGGER.info(TZMarkers.p3, "====lucene,生成查询的条件都为空！====");
			return null;
		}
		Query query = null;
		switch (typeName) {
		case "String":
			if (null == min) {
				min = "";
			}
			if (null == max) {
				max = "";
			}
			query = new TermRangeQuery(fieldName, StringUtils.isNotBlank(min.toString())?min.toString():null, StringUtils.isNotBlank(max.toString())?max.toString():null, minInclude, maxInclude);
			break;
		case "Character":
			if (null == min) {
				min = "";
			}
			if (null == max) {
				max = "";
			}
			query = new TermRangeQuery(fieldName, min.toString(), max.toString(), minInclude, maxInclude);
			break;
		case "Long":
			if (null == min) {
				min = Long.MIN_VALUE;
			}
			if (null == max) {
				max = Long.MAX_VALUE;
			}
			query = NumericRangeQuery.newLongRange(fieldName, Long.parseLong(min.toString()), Long.parseLong(max.toString()), minInclude, maxInclude);
			break;
		case "Integer":
			if (null == min) {
				min = Integer.MIN_VALUE;
			}
			if (null == max) {
				max = Integer.MAX_VALUE;
			}
			query = NumericRangeQuery.newIntRange(fieldName, Integer.parseInt(min.toString()), Integer.parseInt(max.toString()), minInclude, maxInclude);
			break;
		case "Float":
			if (null == min) {
				min = Float.MIN_VALUE;
			}
			if (null == max) {
				max = Float.MAX_VALUE;
			}
			query = NumericRangeQuery.newFloatRange(fieldName, Float.parseFloat(min.toString()), Float.parseFloat(max.toString()), minInclude, maxInclude);
			break;
		case "Double":
			if (null == min) {
				min = Double.MIN_VALUE;
			}
			if (null == max) {
				max = Double.MAX_VALUE;
			}
			query = NumericRangeQuery.newDoubleRange(fieldName, Double.parseDouble(min.toString()), Double.parseDouble(max.toString()), minInclude, maxInclude);
			break;
		case "Date":
			long minLong = 0;
			long maxLong = 0;
			if (null == min && null == max) {
				return null;
			} else if (null == min) {
				minLong = 0;
				maxLong = ((Date) max).getTime();
			} else if (null == max) {
				minLong = ((Date) min).getTime();
				maxLong = Long.MAX_VALUE;
			}
			query = NumericRangeQuery.newLongRange(fieldName, new Long(minLong), new Long(maxLong), minInclude, maxInclude);
			break;
		}
		return query;
	}

	
	@Override
	public <IB extends AbstractIndexBean> Query generateQueryByIndexBean(IB ibean) throws Exception {
		if (null == ibean) {
			LOGGER.info(TZMarkers.p3, "====lucene,根据bean生成查询对象，条件不能为空");
			return null;
		}
		BooleanQueryExample exa = new BooleanQueryExample();
		Field[] fields = ibean.getClass().getFields();
		List<String> analyzedField = ibean.getAnalyzedField();
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			Object value = fields[i].get(ibean);
			// 分词后的字段不参加查询条件
			if (null == value || analyzedField.contains(fieldName)) {
				continue;
			}
			exa.and(fieldName, value);
		}
		return exa.getBuildedQuery();
	}
	
	// 获得单例
	public static QueryBuilder getQueryBuilder() {
		if (null == queryBuilder) {
			initQueryBuilder();
		}
		return queryBuilder;
	}

	// 私有-初始化
	private static synchronized void initQueryBuilder() {
		if (null != queryBuilder) {
			return;
		}
		queryBuilder = new QueryBuilder();
	}

	

}
