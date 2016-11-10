package com.travelzen.framework.lucene.index;

import java.util.List;

import org.apache.lucene.search.Query;

import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;
import com.travelzen.framework.lucene.index.query.bean.AbstractQueryExample;

/**
 * @author qifeifei 
 * 初始化索引配置(启动时调用一次) see at InitIndexUtil
 */
public interface IIndexBuilder {

	/** 增加索引 */
	public <IB extends AbstractIndexBean> void addIndex(IB indexBean) throws Exception;

	/** 增加索引(增加多个) */
	public <IB extends AbstractIndexBean> void addIndexs(List<IB> indexBeans) throws Exception;

	/** 删除索引 */
	public <IB extends AbstractIndexBean> void deleteIndex(IB ibean) throws Exception;

	public void deleteIndex(Query query, Class<?> className) throws Exception;
	
	/** 删除索引(删除多个) */
	public <IB extends AbstractIndexBean> void deleteIndexs(List<IB> ibeans) throws Exception;

	/** 删除所有索引 */
	public <IB extends AbstractIndexBean> boolean deleteAllIndexs(Class<IB> iBeanName) throws Exception;

	/** 重新写，覆盖之前的数据 */
	public <IB extends AbstractIndexBean> boolean deleteAllAndWrite(List<IB> indexBeans) throws Exception;

	/** 批量更新索引 */
	public <IB extends AbstractIndexBean> void updateIndexs(IB exampleBean, List<IB> newBean) throws Exception;

	public <IB extends AbstractIndexBean> void updateIndexs(Query query, List<IB> newBean, Class<IB> clazz) throws Exception;
	
	/** 查找索引 */
	public <QE extends AbstractQueryExample, IB extends AbstractIndexBean> List<IB> doSearch(QE qExample, int page, int pageSize, Class<IB> indexBean) throws Exception;

	/** 查询总数 */
	public <QE extends AbstractQueryExample, IB extends AbstractIndexBean> long count(QE qExample, Class<IB> indexBean) throws Exception;
	
	/** 查找 */
	public <QE extends AbstractQueryExample, IB extends AbstractIndexBean> List<IB> searchAll(QE qExample, Class<IB> indexBean) throws Exception;

	/** 关闭索引 */
	public <IB extends AbstractIndexBean> void closeIndexWriter(Class<IB> iBeanName) throws Exception;

	/** 关闭所有索引 */
	public void closeAllIndexWriter() throws Exception;
	
	/** 自动补全 */
	public <IB extends AbstractIndexBean>  List<IB> autoComplate(String query,int limit, Class<IB> indexPath) throws Exception;

}
