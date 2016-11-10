package com.travelzen.framework.lucene.index.field;

import java.util.List;

import org.apache.lucene.document.AbstractField;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

/**
 * @author qifeifei
 */
public interface IFieldBuilder {

	// 根据一个字段来生成field
	public List<AbstractField> generateField(String fieldName, Object value, Store store, Index index, Float boost);

}
