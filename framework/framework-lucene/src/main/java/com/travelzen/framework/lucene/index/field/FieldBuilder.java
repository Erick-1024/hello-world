package com.travelzen.framework.lucene.index.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.AbstractField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.NumericField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qifeifei
 */
public class FieldBuilder extends AbstractFieldBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(FieldBuilder.class);
	private static volatile FieldBuilder fieldBuilder = null;

	private static final Float DefaultBoost = 1.0f;

	private FieldBuilder() {
	}
	
	@Override
	public List<AbstractField> generateField(String fieldName, Object value, Store store, Index index, Float boost) {
		if (StringUtils.isBlank(fieldName) || null == value) {
			LOGGER.info("====lucene,FieldBuilder,生成条件不能为空====");
			return null;
		}
		if (null == boost || boost < 0) {
			boost = DefaultBoost;
		}
		List<AbstractField> fieldList = new ArrayList<>();
		if (value instanceof Collection) {
			fieldList.addAll(this.generateCollectionFiled(fieldName, value, store, index, boost));
		} else {
			AbstractField field = this.generateSimpleField(fieldName, value, store, index, boost);
			fieldList.add(field);
		}
		return fieldList;
	}

	// 获得单例
	public static FieldBuilder getFieldBuilder() {
		if (null == fieldBuilder) {
			initFieldBuilder();
		}
		return fieldBuilder;
	}

	// 私有-基本类型field
	private AbstractField generateSimpleField(String fieldName, Object value, Store store, Index index, Float boost) {
		if (StringUtils.isBlank(fieldName) || null == value) {
			return null;
		}
		String fieldType = value.getClass().getSimpleName();
		AbstractField field = null;
		switch (fieldType) {
		case "String":
			field = new Field(fieldName, (String) value, store, index);
			field.setBoost(boost);
			break;
		case "Character":
			field = new Field(fieldName, (String) value, store, index);
			break;
		case "Boolean":
			NumericField fieldBool = new NumericField(fieldName, store, index.isIndexed());
			fieldBool.setIntValue(BooleanUtils.isTrue((Boolean) value) ? 1 : 0);
			field = fieldBool;
			break;
		case "Integer":
			NumericField fieldInt = new NumericField(fieldName, store, index.isIndexed());
			fieldInt.setIntValue(Integer.parseInt(value.toString()));
			field = fieldInt;
			break;
		case "Long":
			NumericField fieldLong = new NumericField(fieldName, store, index.isIndexed());
			fieldLong.setLongValue(Long.parseLong(value.toString()));
			field = fieldLong;
			break;
		case "Float":
			NumericField fieldFloat = new NumericField(fieldName, store, index.isIndexed());
			fieldFloat.setFloatValue(Float.parseFloat(value.toString()));
			field = fieldFloat;
			break;
		case "Double":
			NumericField fieldDouble = new NumericField(fieldName, store, index.isIndexed());
			fieldDouble.setDoubleValue(Double.parseDouble(value.toString()));
			field = fieldDouble;
			break;
		case "Date":
			Date date = (Date) value;
			NumericField fieldDate = new NumericField(fieldName, store, index.isIndexed());
			fieldDate.setLongValue(date.getTime());
			field = fieldDate;
			break;
		}
		return field;
	}

	// 私有-集合类型field
	private List<AbstractField> generateCollectionFiled(String fieldName, Object value, Store store, Index index, Float boost) {
		List<AbstractField> fieldList = new ArrayList<>();
		if (value instanceof Collection) {// List存储为多个fieldName相同的字段
			@SuppressWarnings("rawtypes")
			Iterator iterator = ((Collection) value).iterator();
			while (iterator.hasNext()) {
				AbstractField field = this.generateSimpleField(fieldName, iterator.next(), store, index, boost);
				if (null != field) {
					fieldList.add(field);
				}
			}
		}
		return fieldList;
	}

	// 私有-初始化
	private static synchronized void initFieldBuilder() {
		if (null != fieldBuilder) {
			return;
		}
		fieldBuilder = new FieldBuilder();
	}

}
