package com.travelzen.framework.lucene.index.document;

import java.util.List;

import org.apache.lucene.document.Document;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;

/**
 * @author qifeifei
 */
public interface IDocumentBuilder {

	/** 单个 */
	public <B extends AbstractIndexBean> Document generateDocument(B bean) throws Exception;

	/** 批量 */
	public <B extends AbstractIndexBean> List<Document> generateDocument(List<B> bList) throws Exception;
	
}
