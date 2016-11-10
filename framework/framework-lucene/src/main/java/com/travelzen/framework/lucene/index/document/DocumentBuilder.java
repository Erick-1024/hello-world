package com.travelzen.framework.lucene.index.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.AbstractField;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;
import com.travelzen.framework.lucene.index.field.FieldBuilder;

/**
 * @author qifeifei
 */
public class DocumentBuilder extends AbstractDocumentBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentBuilder.class);
	private static volatile DocumentBuilder documentBuilder = null;
	private static FieldBuilder fieldBuilder = FieldBuilder.getFieldBuilder();

	private DocumentBuilder() {
	}

	@Override
	public <B extends AbstractIndexBean> Document generateDocument(B bean) throws Exception {
		if (null == bean) {
			LOGGER.info("====lucene,DocumentBuilder,生成条件不能为空====");
			return null;
		}
		Document document = new Document();
		List<AbstractField> fieldList = new ArrayList<>();
		Class<?> bClass = bean.getClass();
		java.lang.reflect.Field[] bFieldList = bClass.getDeclaredFields();
		if (null != bFieldList && bFieldList.length > 0) {
			for (java.lang.reflect.Field f : bFieldList) {
				String fieldName = f.getName();
				Float boost = bean.getFieldBoost().get(fieldName) == null ? 1.0f : bean.getFieldBoost().get(fieldName);
				Store store = bean.getStoreField().contains(fieldName) ? Store.YES : Store.NO;
				Index index = Index.NO; // Default
				if (bean.getIndexField().contains(fieldName)) {
					if (bean.getAnalyzedField().contains(fieldName)) {
						index = Index.ANALYZED;
					}else{
						index = Index.NOT_ANALYZED;
					}
				}
				List<AbstractField> fields = fieldBuilder.generateField(fieldName, f.get(bean), store, index, boost);
				if (!CollectionUtils.isEmpty(fields)) {
					fieldList.addAll(fields);
				}
			}
		}
		for (AbstractField field : fieldList) {
			document.add(field);
		}
		return document;
	}

	@Override
	public <B extends AbstractIndexBean> List<Document> generateDocument(List<B> bList) throws Exception {
		if (CollectionUtils.isEmpty(bList)) {
			LOGGER.info("====lucene,DocumentBuilder,生成条件list不能为空====");
			return null;
		}
		List<Document> result = new ArrayList<>();
		Class<?> bClass = bList.get(0).getClass();
		java.lang.reflect.Field[] bFieldList = bClass.getDeclaredFields();
		if (null == bFieldList || bFieldList.length <= 0) {
			return null;
		}
		Map<String, Float> fieldBoost = null;
		List<String> storeField = null;
		List<String> analyzedField = null;
		List<String> indexField = null;
		for (B b : bList) {
			if (null == fieldBoost) {
				fieldBoost = b.getFieldBoost();
			}
			if (null == storeField) {
				storeField = b.getStoreField();
			}
			if (null == analyzedField) {
				analyzedField = b.getAnalyzedField();
			}
			if (null == indexField) {
				indexField = b.getIndexField();
			}
			Document doc = new Document();
			List<AbstractField> fieldList = new ArrayList<>();
			for (java.lang.reflect.Field f : bFieldList) {
				String fieldName = f.getName();
				Float boost = (null == fieldBoost || fieldBoost.get(fieldName) == null) ? 1.0f : fieldBoost.get(fieldName);
				Store store = (null != storeField && storeField.contains(fieldName)) ? Store.YES : Store.NO;
				Index index = Index.NO; // Default
				if (null != indexField && indexField.contains(fieldName)) {
					if(null != analyzedField && analyzedField.contains(fieldName)) {
						index = Index.ANALYZED;
					}else{
						index = Index.NOT_ANALYZED;
					}
				}
				if (StringUtils.isNotBlank(fieldName) && null != f.get(b)) {
					List<AbstractField> fields = fieldBuilder.generateField(fieldName, f.get(b), store, index, boost);
					if (!CollectionUtils.isEmpty(fields)) {
						fieldList.addAll(fields);
					}
				}
			}
			for (AbstractField field : fieldList) {
				doc.add(field);
			}
			result.add(doc);
		}
		return result;
	}

	// 获得单例
	public static DocumentBuilder getDocumentBuilder() {
		if (null == documentBuilder) {
			initDocumentBuilder();
		}
		return documentBuilder;
	}

	// 私有-初始化
	private synchronized static void initDocumentBuilder() {
		if (null != documentBuilder) {
			return;
		}
		documentBuilder = new DocumentBuilder();
	}

}
