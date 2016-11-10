package com.travelzen.framework.lucene.index.search;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;

/**
 * @author qifeifei
 */
public interface ISearchBuilder {

	public <IB extends AbstractIndexBean> IndexSearcher generateIndexSearch(Class<IB> iBean) throws Exception;

	/** 增加内存目录 */
	public void addRAMDirectory2Map(Directory ramDir, String iBeanName) throws Exception;

	/** 获取对应的内存目录 */
	public Directory getRAMDirectory(String indexBeanName) throws Exception;

	/** 判断并更新indexSearcher */
	public void check2UpdateIndexSearcher(String indexBeanName) throws Exception;

}
