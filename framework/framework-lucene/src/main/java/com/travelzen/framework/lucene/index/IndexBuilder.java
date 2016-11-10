package com.travelzen.framework.lucene.index;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.travelzen.framework.logger.core.TZMarkers;
import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;
import com.travelzen.framework.lucene.index.config.IndexInitConfig;
import com.travelzen.framework.lucene.index.document.DocumentBuilder;
import com.travelzen.framework.lucene.index.highlighter.HighlighterBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.query.QueryBuilder;
import com.travelzen.framework.lucene.index.query.bean.AbstractQueryExample;
import com.travelzen.framework.lucene.index.search.SearchBuilder;
import com.travelzen.framework.lucene.index.sort.SortBuilder;
import com.travelzen.framework.lucene.index.util.SearchUtil;
import com.travelzen.framework.util.FileUtils;

/**
 * @author qifeifei
 */
public class IndexBuilder extends AbstractIndexBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexBuilder.class);
	private static IndexBuilder indexBuilder = null;

	private static DocumentBuilder documentBuilder = DocumentBuilder.getDocumentBuilder();
	private static SearchBuilder searchBuilder = SearchBuilder.getSearchBuilder();
	private static QueryBuilder queryBuilder = QueryBuilder.getQueryBuilder();
	private static SortBuilder sortBuilder = SortBuilder.getSortBuilder();
	private static HighlighterBuilder highlightBuilder = HighlighterBuilder.getHighlighterBuilder();
	
	private static final int PAGE_DEFAULT = 1;
	private static final int PAGESIZE_DEFAULT = 10;
	private static final float RESULT_RATE = 0.7f;
	private static final float LONG_RESULT_RATE = 0.6f;

	private IndexBuilder() {
	}
	
	@Override
	public <IB extends AbstractIndexBean> void addIndex(IB indexBean) throws Exception {
		LOGGER.info("====lucene,addIndex,[{}]", new Gson().toJson(indexBean));
		if (null == indexBean) {
			LOGGER.info(TZMarkers.p3, "====lucene,IndexBuilder,增加索引的数据为空====");
			return;
		}
		String indexBeanName = indexBean.getClass().getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===addIndex,参数类名不存在===");
			return;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		writer.addDocument(documentBuilder.generateDocument(indexBean));
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
	}

	
	@Override
	public <IB extends AbstractIndexBean> void addIndexs(List<IB> indexBeans) throws Exception {
		LOGGER.info("====lucene,addIndex,[{}]", new Gson().toJson(indexBeans));
		if (CollectionUtils.isEmpty(indexBeans)) {
			LOGGER.info(TZMarkers.p3, "====lucene,IndexBuilder,增加索引的数据为空====");
			return;
		}
		if (null == indexBeans.get(0)) {
			LOGGER.debug("===createIndex，参数不能为空===");
			return;
		}
		String indexBeanName = indexBeans.get(0).getClass().getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===createIndex,indexBeanName不能为空===");
			return;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		writer.addDocuments(documentBuilder.generateDocument(indexBeans));
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
	}

	@Override
	public <IB extends AbstractIndexBean> void deleteIndex(IB ibean) throws Exception {
		LOGGER.info("====lucene,deleteIndex,[{}]", new Gson().toJson(ibean));
		if (null == ibean) {
			LOGGER.info(TZMarkers.p3, "====lucene,IndexBuilder,删除索引的数据为空====");
			return;
		}
		String indexBeanName = ibean.getClass().getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===deleteIndex,indexBeanName不能为空===");
			return;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		Query query = queryBuilder.generateQueryByIndexBean(ibean);
		if (null == query) {
			LOGGER.info(TZMarkers.p3, "====lucene,deleteIndexs,条件indexBean不能为空====");
			return;
		}
		writer.deleteDocuments(query);
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
	}
	
	@Override
	public void deleteIndex(Query query, Class<?> className) throws Exception{
		LOGGER.info("====lucene,deleteIndex,[{}]", new Gson().toJson(query));
		if (null == query) {
			LOGGER.info(TZMarkers.p3, "====lucene,IndexBuilder,删除索引的数据为空====");
			return;
		}
		String indexBeanName = className.getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===deleteIndex,indexBeanName不能为空===");
			return;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		writer.deleteDocuments(query);
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
	}

	@Override
	public <IB extends AbstractIndexBean> void deleteIndexs(List<IB> ibeans) throws Exception {
		LOGGER.info("====lucene,deleteIndexs,[{}]", new Gson().toJson(ibeans));
		if (CollectionUtils.isEmpty(ibeans) || null == ibeans.get(0)) {
			return;
		}
		String indexBeanName = ibeans.get(0).getClass().getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===deleteIndexs,indexBeanName不能为空===");
			return;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		for (IB ib : ibeans) {
			Query query = queryBuilder.generateQueryByIndexBean(ib);
			if (null == query) {
				LOGGER.info(TZMarkers.p3, "====lucene,deleteIndexs,条件indexBean不能为空====");
				continue;
			}
			writer.deleteDocuments(query);
		}
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
	}

	@Override
	public <IB extends AbstractIndexBean> boolean deleteAllIndexs(Class<IB> iBeanName) throws Exception {
		LOGGER.info("====lucene,deleteAllIndexs====");
		if (null == iBeanName) {
			LOGGER.debug("===deleteAllIndexs,参数不能为空===");
			return false;
		}
		String indexBeanName = iBeanName.getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===deleteAllIndexs,indexBeanName不能为空===");
			return false;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		writer.deleteAll();
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
		return true;
	}

	
	@Override
	public <IB extends AbstractIndexBean> boolean deleteAllAndWrite(List<IB> indexBeans) throws Exception {
		LOGGER.debug("===lucene,deleteAllAndWrite===");
		if (CollectionUtils.isEmpty(indexBeans)) {
			return false;
		}
		String indexBeanName = indexBeans.get(0).getClass().getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===lucene,deleteAllAndWrite, indexBeanName 不能为空===");
			return false;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		writer.deleteAll();
		writer.addDocuments(documentBuilder.generateDocument(indexBeans));
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
		return true;
	}
	
	
	@Override
	public <IB extends AbstractIndexBean> void updateIndexs(IB exampleBean, List<IB> newBean) throws Exception {
		LOGGER.info("====lucene,updateInexs====");
		if (null == exampleBean || CollectionUtils.isEmpty(newBean)) {
			LOGGER.info(TZMarkers.p3, "====lucene,updateInexs,条件不正确====");
			return;
		}
		String indexBeanName = exampleBean.getClass().getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===deleteIndex,indexBeanName不能为空===");
			return;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		Query query = queryBuilder.generateQueryByIndexBean(exampleBean);
		if (null == query) {
			LOGGER.info(TZMarkers.p3, "====lucene,deleteIndexs,条件indexBean不能为空====");
			return;
		}
		writer.deleteDocuments(query);
		// after delete,then add
		writer.addDocuments(documentBuilder.generateDocument(newBean));
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
	}

	@Override
	public <IB extends AbstractIndexBean> void updateIndexs(Query query, List<IB> newBean, Class<IB> clazz) throws Exception {
		LOGGER.info("====lucene,updateInexs====");
		if (null == clazz || CollectionUtils.isEmpty(newBean)) {
			LOGGER.info(TZMarkers.p3, "====lucene,updateInexs,条件不正确====");
			return;
		}
		String indexBeanName = clazz.getSimpleName();
		if (StringUtils.isBlank(indexBeanName)) {
			LOGGER.debug("===deleteIndex,indexBeanName不能为空===");
			return;
		}
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		if (null == query) {
			LOGGER.info(TZMarkers.p3, "====lucene,deleteIndexs,条件indexBean不能为空====");
			return;
		}
		writer.deleteDocuments(query);
		// after delete,then add
		writer.addDocuments(documentBuilder.generateDocument(newBean));
		writer.commit();
		searchBuilder.check2UpdateIndexSearcher(indexBeanName);
	}
	
	@Override
	public <QE extends AbstractQueryExample, IB extends AbstractIndexBean> List<IB> doSearch(QE qExample, int page, int pageSize, Class<IB> indexBeanClass) throws Exception {
		Query query = qExample.getBuildedQuery();
		IndexSearcher indexSearcher = searchBuilder.generateIndexSearch(indexBeanClass);
		if(null == indexSearcher){
			LOGGER.debug("===indexSearcher获取为空，不能进行查询===");
			return null;
		}
		Sort sort = null;
		if(CollectionUtils.isNotEmpty(qExample.getSortFields())){
			SortField[] sortField = new SortField[]{};
			sort = sortBuilder.generateSort(qExample.getSortFields().toArray(sortField));
		}
		TopDocs hits = null;
		if (page <= 0) {
			page = PAGE_DEFAULT;
		}
		if (pageSize <= 0) {
			pageSize = PAGESIZE_DEFAULT;
		}
		int maxCount = page * pageSize;
		if(StringUtils.isBlank(query.toString())){		
			query = new MatchAllDocsQuery();
		}
		if(null != sort){
			hits = indexSearcher.search(query, maxCount, sort);
		}else{
			hits = indexSearcher.search(query, maxCount);
		}
		ScoreDoc[] scoreDocs = hits.scoreDocs;
		int begin = (page - 1) * pageSize;
		int end = begin + pageSize;
		List<IB> result = new ArrayList<>();
		// get all field name
		Field[] fields = indexBeanClass.getDeclaredFields();
		List<String> fieldNameList = new ArrayList<>();
		Map<String, Class<?>> fieldName2Type = new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			fieldNameList.add(fields[i].getName());
			fieldName2Type.put(fields[i].getName(), fields[i].getType());
		}
		// set value, highlight
		for (int i = begin; i < end && i < scoreDocs.length; i++) {
			int docID = scoreDocs[i].doc;
			Document docu = indexSearcher.doc(docID);
			IB newBean = (IB) indexBeanClass.newInstance();
			for (String fieldName : fieldNameList) {
				String valueType = fieldName2Type.get(fieldName) == null ? null : fieldName2Type.get(fieldName).getSimpleName();
				if (StringUtils.isBlank(valueType)) {
					continue;
				}
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, newBean.getClass());
				Method writeMethod = pd.getWriteMethod();
				if (StringUtils.equals(valueType, "Collection")) {
					String[] values = docu.getValues(fieldName);
					this.setCollectionValue2IndexBean(newBean, writeMethod, values, valueType, fieldName);
				} else {
					String value = docu.get(fieldName);
					this.setObjectValue2IndexBean(newBean, writeMethod, value, valueType);
				}
			}
			result.add(newBean);
		}
		indexSearcher.close();
		this.check2Highlighter(result, qExample, indexBeanClass);
		return result;
	}

	@Override
	public <QE extends AbstractQueryExample, IB extends AbstractIndexBean> long count(QE qExample, Class<IB> indexBean) throws Exception {
		Query query = qExample.getBuildedQuery();
		IndexSearcher indexSearcher = searchBuilder.generateIndexSearch(indexBean);
		if (null == indexSearcher) {
			LOGGER.debug("===indexSearcher获取为空，不能进行总数查询===");
			return 0;
		}
		if (StringUtils.isBlank(query.toString())) {
			query = new MatchAllDocsQuery();
		}
		TopDocs docs = indexSearcher.search(query, Integer.MAX_VALUE);
		long result = null == docs ? 0 : docs.totalHits;
		indexSearcher.close();
		return result;
	}
	
	@Override
	public <QE extends AbstractQueryExample, IB extends AbstractIndexBean> List<IB> searchAll(QE qExample, Class<IB> indexBeanClass) throws Exception {
		Query query = qExample.getBuildedQuery();
		Sort sort = null;
		if(CollectionUtils.isNotEmpty(qExample.getSortFields())){
			SortField[] sortField = new SortField[]{};
			sort = sortBuilder.generateSort(qExample.getSortFields().toArray(sortField));
		}
		IndexSearcher indexSearcher = searchBuilder.generateIndexSearch(indexBeanClass);
		if(null == indexSearcher){
			LOGGER.debug("===indexSearcher获取为空，不能进行查询===");
			return null;
		}
		TopDocs hits = null;
		if(StringUtils.isBlank(query.toString())){
			query = new MatchAllDocsQuery();
		}
		if(null != sort){
			hits = indexSearcher.search(query, Integer.MAX_VALUE, sort);			// search
		}else{
			hits = indexSearcher.search(query, Integer.MAX_VALUE);
		}
		ScoreDoc[] scoreDocs = hits.scoreDocs;
		List<IB> result = new ArrayList<>();
		if (scoreDocs.length <= 0) {
			return result;
		}
		// get all field name
		Field[] fields = indexBeanClass.getDeclaredFields();
		List<String> fieldNameList = new ArrayList<>();
		Map<String, Class<?>> fieldName2Type = new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			fieldNameList.add(fields[i].getName());
			fieldName2Type.put(fields[i].getName(), fields[i].getType());
		}
		// set value to field, highlight
		for (int i = 0; i < scoreDocs.length; i++) {
			int docID = scoreDocs[i].doc;
			Document docu = indexSearcher.doc(docID);
			IB newBean = indexBeanClass.newInstance();
			for (String fieldName : fieldNameList) {
				String valueType = fieldName2Type.get(fieldName) == null ? null : fieldName2Type.get(fieldName).getSimpleName();
				if (StringUtils.isBlank(valueType)) {
					continue;
				}
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, newBean.getClass());
				Method writeMethod = pd.getWriteMethod();
				if (StringUtils.equals(valueType, "Collection")) {
					String[] values = docu.getValues(fieldName);
					this.setCollectionValue2IndexBean(newBean, writeMethod, values, valueType, fieldName);
				} else {
					String value = docu.get(fieldName);
					this.setObjectValue2IndexBean(newBean, writeMethod, value, valueType);
				}
			}
			result.add(newBean);
		}
		indexSearcher.close();
		this.check2Highlighter(result, qExample, indexBeanClass);
		return result;
	}

	
	@Override
	public <IB extends AbstractIndexBean> void closeIndexWriter(Class<IB> iBeanName) throws Exception {
		if (null == iBeanName || null == iBeanName.getSimpleName()) {
			return;
		}
		String indexBeanName = iBeanName.getSimpleName();
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		try {
			writer.close();
		} catch (Exception e) {
			throw e;
		} finally {
			IndexInitConfig config = InitIndexUtil.getIndexConfigByBeanName(indexBeanName);
			if (null == config || StringUtils.isBlank(config.getDEFAULT_INDEX_PATH())) {
				return;
			}
			if (IndexWriter.isLocked(FSDirectory.open(FileUtils.getFile(config.getDEFAULT_INDEX_PATH())))) {
				IndexWriter.unlock(FSDirectory.open(FileUtils.getFile(config.getDEFAULT_INDEX_PATH())));
			}
		}
	}
	
	
	@Override
	public void closeAllIndexWriter() throws Exception {
		if (writerMap.keySet().size() <= 0) {
			return;
		}
		for (String iBeanName : writerMap.keySet()) {
			IndexWriter writer = writerMap.get(iBeanName);
			if (null == writer) {
				this.checkInitIndexSystem(iBeanName);
				writer = writerMap.get(iBeanName);
			}
			try {
				writer.close();
			} catch (Exception e) {
				throw e;
			} finally {
				IndexInitConfig config = InitIndexUtil.getIndexConfigByBeanName(iBeanName);
				if (null == config || StringUtils.isBlank(config.getDEFAULT_INDEX_PATH())) {
					return;
				}
				if (IndexWriter.isLocked(FSDirectory.open(FileUtils.getFile(config.getDEFAULT_INDEX_PATH())))) {
					IndexWriter.unlock(FSDirectory.open(FileUtils.getFile(config.getDEFAULT_INDEX_PATH())));
				}
			}
		}
	}

	public <IB extends AbstractIndexBean>  List<IB> autoComplate(String query, int limit, Class<IB> indexBeanClass) throws Exception{
		if (query == null || query.length() == 0)
			return null;
		if (indexBeanClass == null)
			return null;

		query = SearchUtil.traditionalToSimple(query);

		List<IB> list = null;
		List<String> words = new ArrayList<>();
		words = AutoCompleteConstants.analyzerWords(query);
		boolean isEnglish = SearchUtil.isEnglish(query);
		if (isEnglish) {
			list = fuzzyEnglishSearch(query, words, indexBeanClass);
		} else if (SearchUtil.containsEnglisht(query)) {
			list = prefixSearch(query, indexBeanClass);// 如果英文一块
		} else {
			list = fuzzySearch(query, words, indexBeanClass);// 模糊搜索
		}
		if(list == null || list.size()<=0){
			return new ArrayList<>();
		}
		if(list.size() < limit){
			return list;
		}
		return list.subList(0, limit);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	// 获取单例
	public static IndexBuilder getIndexBuilder() {
		if (null == indexBuilder) {
			initIndexBuilder();
		}
		return indexBuilder;
	}

	// 私有 - 初始化
	private synchronized static void initIndexBuilder() {
		if (null != indexBuilder) {
			return;
		}
		indexBuilder = new IndexBuilder();
	}

	// 初始化writer
	public void checkInitIndexSystem(String indexBeanName) {
		if(StringUtils.isBlank(indexBeanName) || null == InitIndexUtil.getIndexConfigByBeanName(indexBeanName)){
			LOGGER.debug("==初始化indexSystem的bean名称不能为空===");
			return;
		}
		IndexInitConfig config = InitIndexUtil.getIndexConfigByBeanName(indexBeanName);
		// 初始化
		if (BooleanUtils.isTrue(config.isFileDirectoryIndex())) {
			// 检查文件读写权限
			File dirFile = new File(config.getDEFAULT_INDEX_PATH());
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			if (!dirFile.canWrite() || !dirFile.canRead()) {
				System.out.println("indexPath没有读写权限，路径是：" + config.getDEFAULT_INDEX_PATH());
				return;
			}
			this.initFs(config.getDEFAULT_INDEX_PATH(), config.isCreate(),indexBeanName);
		} else if (BooleanUtils.isFalse(config.isFileDirectoryIndex())) {
			this.initRam(config.getDEFAULT_INDEX_PATH(), config.isCreate(),indexBeanName);
		}
	}
	
	// 初始化writer
	/**
	 * @author hu 获取wirter
	 * @param clazz
	 * @return
	 */
	public  <T> IndexWriter initIndexSystem(Class<T> clazz) {
		if(StringUtils.isBlank(clazz.getSimpleName()) || null == InitIndexUtil.getIndexConfigByBeanName(clazz.getSimpleName())){
			LOGGER.debug("==初始化indexSystem的bean名称不能为空===");
			return  null;
		}
		String indexBeanName = clazz.getSimpleName();
		IndexWriter writer = writerMap.get(indexBeanName);
		if (null == writer) {
			this.checkInitIndexSystem(indexBeanName);
			writer = writerMap.get(indexBeanName);
		}
		return writer;
	}
	
	/**
	 * @author hu 单独触发更新reader
	 * @param clazz
	 * @throws Exception
	 */
	public <T> void updateIndexSearch(Class<T> clazz) throws Exception{
		
		searchBuilder.check2UpdateIndexSearcher(clazz.getSimpleName());
	}

	// 私有 - 把value 设置到类中
	private <IB extends AbstractIndexBean> void setObjectValue2IndexBean(IB newBean, Method writeMethod, String value, String type) throws Exception {
		if (null == writeMethod || null == value || StringUtils.isBlank(type)) {
			return;
		}
		switch (type) {
		case "String":
			writeMethod.invoke(newBean, value);
			break;
		case "Character":
			writeMethod.invoke(newBean, value.charAt(0));
			break;
		case "Boolean":
			writeMethod.invoke(newBean, new Boolean(BooleanUtils.toBooleanObject(Integer.parseInt(value))));
			break;
		case "Integer":
			writeMethod.invoke(newBean, Integer.parseInt(value));
			break;
		case "Long":
			writeMethod.invoke(newBean, Long.parseLong(value));
			break;
		case "Float":
			writeMethod.invoke(newBean, Float.parseFloat(value));
			break;
		case "Double":
			writeMethod.invoke(newBean, Double.parseDouble(value));
			break;
		case "Date":
			Date date = new Date(Long.parseLong(value));
			writeMethod.invoke(newBean, date);
			break;
		default:
			break;
		}
		return;
	}

	// 私有 - 把collection设置到类中
	private <IB extends AbstractIndexBean> void setCollectionValue2IndexBean(IB newBean, Method writeMethod, String[] values, String type, String fieldName)
			throws Exception {
		if (null == writeMethod || null == values || values.length == 0 || !StringUtils.equals(type, "Collection") || StringUtils.isBlank(fieldName)) {
			return;
		}
		String ptype = null;
		try {
			ParameterizedType paramType = (ParameterizedType) newBean.getClass().getField(fieldName).getGenericType();
			ptype = paramType.getActualTypeArguments()[0].toString();
			ptype = ptype.substring(ptype.lastIndexOf(".") + 1);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			Collection<String> col = new ArrayList<String>(Arrays.asList(values));
			writeMethod.invoke(newBean, col);
			return;
		}
		switch (ptype) {
		case "String":
			Collection<String> strResult = new ArrayList<String>(Arrays.asList(values));
			writeMethod.invoke(newBean, strResult);
			return;
		case "Character":
			break;
		case "Boolean":
			Collection<Boolean> boolResult = new ArrayList<>();
			for (int i = 0; i < values.length; i++) {
				boolResult.add(new Boolean(BooleanUtils.toBooleanObject(Integer.parseInt(values[i]))));
			}
			writeMethod.invoke(newBean, boolResult);
			return;
		case "Integer":
			Collection<Integer> intResult = new ArrayList<>();
			for (int i = 0; i < values.length; i++) {
				intResult.add(Integer.parseInt(values[i]));
			}
			writeMethod.invoke(newBean, intResult);
			return;
		case "Long":
			Collection<Long> longResult = new ArrayList<>();
			for (int i = 0; i < values.length; i++) {
				longResult.add(Long.parseLong(values[i]));
			}
			writeMethod.invoke(newBean, longResult);
			return;
		case "Float":
			Collection<Float> floatResult = new ArrayList<>();
			for (int i = 0; i < values.length; i++) {
				floatResult.add(Float.parseFloat(values[i]));
			}
			writeMethod.invoke(newBean, floatResult);
			return;
		case "Double":
			Collection<Double> doubleFloat = new ArrayList<>();
			for (int i = 0; i < values.length; i++) {
				doubleFloat.add(Double.parseDouble(values[i]));
			}
			writeMethod.invoke(newBean, doubleFloat);
			return;
		case "Date":
			Collection<Date> dateResult = new ArrayList<>();
			for (int i = 0; i < values.length; i++) {
				dateResult.add(new Date(Long.parseLong(values[i])));
			}
			writeMethod.invoke(newBean, dateResult);
			return;
		default:
			return;
		}
	}
	
	
	// 将查找的结果在返回前进行高亮
	private <QE extends AbstractQueryExample, IB extends AbstractIndexBean> void check2Highlighter(List<IB> ibList, QE qExample, Class<IB> indexBeanClass) throws Exception {
		if (CollectionUtils.isEmpty(ibList) || null == indexBeanClass || null == qExample) {
			return;
		}
		IB ib = indexBeanClass.newInstance();
		Map<String, Pair<String, String>> highlighterMap = ib.getHighlighter();
		if (null == highlighterMap || StringUtils.isBlank(qExample.getNeedHighlighterWord())) {
			return;
		}
		for (IB b : ibList) {
			for (String fieldName : highlighterMap.keySet()) {
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, b.getClass());
				Method writerM = pd.getWriteMethod();
				Method readM = pd.getReadMethod();
				String text = (String) readM.invoke(b);
				String highlighterResult = highlightBuilder.getHighlighterResult(fieldName, text, qExample.getNeedHighlighterWord(), highlighterMap);
				if (StringUtils.isNotBlank(highlighterResult)) {
					writerM.invoke(b, highlighterResult);
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private <IB extends AbstractIndexBean> List<IB> fuzzyEnglishSearch(String searchName, List<String> words, Class<IB> indexBeanClass) {
		try {
			// 标准搜索
			BooleanQuery wordsBooleanQuery = new BooleanQuery();
			BooleanQuery normalBooleanQuery = new BooleanQuery();
			normalBooleanQuery.setMinimumNumberShouldMatch(getFuzzyEnglishMinMatchToken(words));
			
			for (int i = 0; i < words.size(); i++) {
				normalBooleanQuery.add(new TermQuery(new Term(AutoCompleteConstants.AUTO_COMPLETE_PINYIN_NAME, words.get(i))), BooleanClause.Occur.SHOULD);
			}
			wordsBooleanQuery.add(normalBooleanQuery, BooleanClause.Occur.MUST);

			// 模糊
			StringBuilder bufferCharacterString = new StringBuilder();
			for (int i = 0; i < searchName.length(); i++) {
				if (searchName.charAt(i) != ' ')
					bufferCharacterString.append(searchName.charAt(i));
			}
//			Term t = new Term(AutoCompleteConstants.AUTO_COMPLETE_FULL_PINYIN_NAME, bufferCharacterString.toString().toLowerCase());
			Term t2 = new Term(AutoCompleteConstants.AUTO_COMPLETE_PINYIN_NAME, bufferCharacterString.toString().toLowerCase());
			
			// 字符数<4不能错
			// 字符数==4 :1
			// 字符数5,6,7 :2
			// 字符数8,9,...13:3
			// >13 5/length
			// 设置最小相似度依据
			float minimumSimilarity = (float) 0.6;
			int minLength = 0, maxLength = 0;
			if (bufferCharacterString.length() < 4) {
				minimumSimilarity = (float) 0.66;
				minLength = maxLength = searchName.length();
			} else if (bufferCharacterString.length() == 4) {
				minimumSimilarity = (float) (1.0 - 1.0 / bufferCharacterString.length() - 0.15);
				minLength = searchName.length() - 1;
				maxLength = searchName.length() + 1;
			} else if (bufferCharacterString.length() > 4 && bufferCharacterString.length() < 8) {
				minimumSimilarity = (float) (1.0 - 2.0 / bufferCharacterString.length() - 0.2);
				minLength = searchName.length() - 2;
				maxLength = searchName.length() + 2;
			} else if (bufferCharacterString.length() > 7 && bufferCharacterString.length() < 14) {
				minimumSimilarity = (float) (1.0 - 3.0 / bufferCharacterString.length() - 0.1);
				minLength = searchName.length() - 3;
				maxLength = searchName.length() + 3;
			} else if (bufferCharacterString.length() > 13) {
				minimumSimilarity = (float) ((bufferCharacterString.length() - 3) / (bufferCharacterString.length() * 3.0));
				minLength = bufferCharacterString.length() - 3;
				maxLength = bufferCharacterString.length() * 3;
			}
			int prefixLength = 1;// 首字母必须匹配
			// 模糊query
//			FuzzyQuery fuzzyQuery = new FuzzyQuery(t, minimumSimilarity, prefixLength);
			FuzzyQuery fuzzyQuery2 = new FuzzyQuery(t2, minimumSimilarity, prefixLength);
			BooleanQuery allBooleanQuery = new BooleanQuery();// 我们的业务逻辑查询
			BooleanQuery tmpBooleanQuery = new BooleanQuery();
			
			Term t3 = new Term(AutoCompleteConstants.AUTO_COMPLETE_FULL_PINYIN_NAME, "*"+bufferCharacterString.toString().toLowerCase()+"*");
			Term t4 = new Term(AutoCompleteConstants.AUTO_COMPLETE_SHORT_PINYIN_NAME,"*"+bufferCharacterString.toString().toLowerCase()+"*");
			WildcardQuery wildBooleanQuery = new WildcardQuery(t3);
			WildcardQuery wildBooleanQuery2 = new WildcardQuery(t4);
			
//			fuzzyQuery.setBoost((float) 0.2);
			fuzzyQuery2.setBoost((float) 0.2);
//			tmpBooleanQuery.add(fuzzyQuery, BooleanClause.Occur.SHOULD);
			tmpBooleanQuery.add(fuzzyQuery2, BooleanClause.Occur.SHOULD);
			tmpBooleanQuery.add(wordsBooleanQuery, BooleanClause.Occur.SHOULD);
			tmpBooleanQuery.add(wildBooleanQuery, BooleanClause.Occur.SHOULD);
			tmpBooleanQuery.add(wildBooleanQuery2, BooleanClause.Occur.SHOULD);
			allBooleanQuery.add(tmpBooleanQuery, BooleanClause.Occur.MUST);
			return getSearchResult(allBooleanQuery, indexBeanClass);
		} catch (Exception e) {

		}
		return null;
	}
	
	public <IB extends AbstractIndexBean> List<IB> prefixSearch(String query, Class<IB> indexBeanClass) throws Exception {
		if (query == null || query.length() == 0)
			return null;
		if (indexBeanClass == null)
			return null;
		query = SearchUtil.traditionalToSimple(query);
		char[] charArray = query.toCharArray();
		if(charArray == null || charArray.length <= 0){
			return null;
		}
		query = new Character(charArray[0]).toString();
		String[] pinyins = AutoCompleteConstants.getPinyin(query);
		StringBuffer sb = new StringBuffer();
		for(String py:pinyins){
			sb.append(py);
		}
		Term t2 = new Term(AutoCompleteConstants.AUTO_COMPLETE_PINYIN_NAME, sb.toString());
		PrefixQuery pQuery2 = new PrefixQuery(t2);
		BooleanQuery tmpBooleanQuery = new BooleanQuery();
		tmpBooleanQuery.add(pQuery2, BooleanClause.Occur.SHOULD);

		BooleanQuery allBooleanQuery = new BooleanQuery();
		allBooleanQuery.add(tmpBooleanQuery, BooleanClause.Occur.MUST);
		allBooleanQuery.add(tmpBooleanQuery, BooleanClause.Occur.MUST);
		
		return getSearchResult(allBooleanQuery, indexBeanClass);
	}
	
	@SuppressWarnings("unused")
	private <IB extends AbstractIndexBean> List<IB> fuzzySearch(String searchKeyword, List<String> words, Class<IB> indexBeanClass) {

		String[] allPinyinWords = null; // 模糊拼音
		BooleanQuery chineseBooleanQuery = new BooleanQuery();
		// BooleanQuery ourOperationBooleanQuery = new BooleanQuery();//
		// 我们的业务逻辑查询
		BooleanQuery ourOperationBooleanQuery = new BooleanQuery(true);
		BooleanQuery allBooleanQuery = new BooleanQuery();// 最后组合查询query
		int countQuery = 2;
		try {
			String[] temp = AutoCompleteConstants.getPinyin(searchKeyword);
			String allPinyin = temp[0];
			allPinyinWords = allPinyin.trim().split(";");
			// 汉字单字匹配
			for (int i = 0; i < words.size(); i++) {
				chineseBooleanQuery.add(new TermQuery(new Term(AutoCompleteConstants.AUTO_COMPLETE_CHINESE_NAME, words.get(i))), BooleanClause.Occur.SHOULD);
				countQuery++;
			}
			// 最小匹配限制
			if (words.size() > 9) {
				chineseBooleanQuery.setMinimumNumberShouldMatch(Math.round(words.size() * LONG_RESULT_RATE));
			} else if (words.size() > 2) {
				chineseBooleanQuery.setMinimumNumberShouldMatch(Math.round(words.size() * RESULT_RATE));
			}
			// 拼音单字匹配
			for (int i = 0; i < allPinyinWords.length; i++) {
				String[] arrayTmp = allPinyinWords[i].split(" |,");
				BooleanQuery tmpBooleanQuery = new BooleanQuery();
				countQuery++;
				for (int j = 0; j < arrayTmp.length; j++) {
					tmpBooleanQuery.add(new TermQuery(new Term(AutoCompleteConstants.AUTO_COMPLETE_SIMPLEPINYIN_NAME, arrayTmp[j])), BooleanClause.Occur.SHOULD);
					countQuery++;
				}
				if (words.size() > 9) {
					tmpBooleanQuery.setMinimumNumberShouldMatch(Math.round(words.size() * LONG_RESULT_RATE));
				} else if (words.size() > 2) {
					tmpBooleanQuery.setMinimumNumberShouldMatch(Math.round(words.size() * RESULT_RATE));
				}
				ourOperationBooleanQuery.add(tmpBooleanQuery, BooleanClause.Occur.SHOULD);

			}
			// 汉字单字的 查询 权重
			chineseBooleanQuery.setBoost(2.2f);
			ourOperationBooleanQuery.add(chineseBooleanQuery, BooleanClause.Occur.SHOULD);
			allBooleanQuery.add(ourOperationBooleanQuery, BooleanClause.Occur.MUST);
			countQuery++;
			return getSearchResult(allBooleanQuery, indexBeanClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 英文最小匹配单词数
	 * 
	 * @param lst
	 * @return
	 */
	private int getFuzzyEnglishMinMatchToken(List<String> lst) {
		int iRet = lst.size() - 1;
		if (lst.size() > 8)
			iRet = lst.size() - 3;
		if (lst.size() > 5)
			iRet = lst.size() - 2;
		return iRet;
	}

	private <IB extends AbstractIndexBean> List<IB> getSearchResult(BooleanQuery query, Class<IB> indexBeanClass) throws Exception {
		if (query == null) {
			return null;
		}
		IndexSearcher searcher = searchBuilder.generateIndexSearch(indexBeanClass);
		TopDocs hits = searcher.search(query, Integer.MAX_VALUE);
		ScoreDoc[] scoreDocs = hits.scoreDocs;
		List<IB> result = new ArrayList<>();
		if (scoreDocs.length <= 0) {
			return result;
		}
		// get all field name
		Field[] fields = indexBeanClass.getDeclaredFields();
		List<String> fieldNameList = new ArrayList<>();
		Map<String, Class<?>> fieldName2Type = new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			fieldNameList.add(fields[i].getName());
			fieldName2Type.put(fields[i].getName(), fields[i].getType());
		}
		// set value to field, highlight
		for (int i = 0; i < scoreDocs.length; i++) {
			int docID = scoreDocs[i].doc;
			Document docu = searcher.doc(docID);
			IB newBean = indexBeanClass.newInstance();
			for (String fieldName : fieldNameList) {
				String valueType = fieldName2Type.get(fieldName) == null ? null : fieldName2Type.get(fieldName).getSimpleName();
				if (StringUtils.isBlank(valueType)) {
					continue;
				}
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, newBean.getClass());
				Method writeMethod = pd.getWriteMethod();
				if (StringUtils.equals(valueType, "Collection")) {
					String[] values = docu.getValues(fieldName);
					this.setCollectionValue2IndexBean(newBean, writeMethod, values, valueType, fieldName);
				} else {
					String value = docu.get(fieldName);
					this.setObjectValue2IndexBean(newBean, writeMethod, value, valueType);
				}
			}
			result.add(newBean);
		}
		searcher.close();
		return result;
	}
}

