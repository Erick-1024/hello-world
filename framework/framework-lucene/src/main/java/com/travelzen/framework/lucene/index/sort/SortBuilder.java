package com.travelzen.framework.lucene.index.sort;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.logger.core.TZMarkers;

/**
 * @author qifeifei
 */
public class SortBuilder extends AbstractSortBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(SortBuilder.class);
	private static volatile SortBuilder sortBuilder = null;

	private SortBuilder() {
	}
	
	@Override
	public Sort generateSort(SortField[] fields) {
		if (null == fields || fields.length <= 0) {
			return null;
		}
		Sort sort = new Sort(fields);
		return sort;
	}

	@Override
	public SortField generateSortField(String fieldName, String type, boolean reverse) {
		if (StringUtils.isBlank(fieldName) || StringUtils.isBlank(type)) {
			LOGGER.info(TZMarkers.p3, "====lucene,SortBuilder,条件不能为空====");
			return null;
		}
		SortField sortField = null;
		switch (type) {
		case "String":
			sortField = new SortField(fieldName, SortField.STRING, reverse);
			break;
		case "Character":
			sortField = new SortField(fieldName, SortField.BYTE, reverse);
			break;
		case "Boolean":
			sortField = new SortField(fieldName, SortField.INT, reverse);
			break;
		case "Integer":
			sortField = new SortField(fieldName, SortField.INT, reverse);
			break;
		case "Long":
			sortField = new SortField(fieldName, SortField.LONG, reverse);
			break;
		case "Float":
			sortField = new SortField(fieldName, SortField.FLOAT, reverse);
			break;
		case "Double":
			sortField = new SortField(fieldName, SortField.DOUBLE, reverse);
			break;
		case "Date":
			sortField = new SortField(fieldName, SortField.LONG, reverse);
			break;
		default:
			LOGGER.info(TZMarkers.p3, "====lucene,SortBuilder,【{}】,不是基本类型====", type);
			break;
		}
		return sortField;
	}

	@Override
	public SortField generateScoreOrDocSortField(int type, boolean reverse) {
		SortField sortField = null;
		switch(type){
			case SortField.SCORE:
				sortField = new SortField(null, SortField.SCORE, reverse);
				break;
			case SortField.DOC:
				sortField = new SortField(null, SortField.DOC, reverse);
				break;
		}
		return sortField;
	}
	
	// 获得单例
	public static SortBuilder getSortBuilder() {
		if (null == sortBuilder) {
			initSortBuilder();
		}
		return sortBuilder;
	}

	// 私有-初始化
	private static synchronized void initSortBuilder() {
		if (null != sortBuilder) {
			return;
		}
		sortBuilder = new SortBuilder();
	}
}
