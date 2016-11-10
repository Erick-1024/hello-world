package com.travelzen.framework.lucene.index.query.bean;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.lucene.index.analyzer.AnalyzerBuilder;
import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;
import com.travelzen.framework.lucene.index.query.QueryBuilder;
import com.travelzen.framework.lucene.index.sort.SortBuilder;

/**
 * @author qifeifei
 */
public class BooleanQueryExample extends AbstractQueryExample {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static QueryBuilder queryBuilder = QueryBuilder.getQueryBuilder();
	private static AnalyzerBuilder analyzerBuilder = AnalyzerBuilder.getAnalyzerBuilder();
	private static SortBuilder sortBuilder = SortBuilder.getSortBuilder();

	private BooleanQuery bQuery = new BooleanQuery();
	private List<SortField> sortField = new ArrayList<>();
	private String highlighterWord = null;
	
	private static final float RESULT_RATE = 0.7f;
	private static final float LONG_RESULT_RATE = 0.6f;

	
	/** 与 */
	public void and(String fieldName, Object value) {
		Query query = queryBuilder.generateQueryByType(fieldName, value);
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/** 或 */
	public void or(String fieldName, Object value) {
		Query query = queryBuilder.generateQueryByType(fieldName, value);
		bQuery.add(query, BooleanClause.Occur.SHOULD);
	}
	
	/** 非 */
	public void not(String fieldName, Object value) {
		Query query = queryBuilder.generateQueryByType(fieldName, value);
		bQuery.add(query, BooleanClause.Occur.MUST_NOT);
	}

	/** 字符串包含 */
	public void contains(String fieldName, Object value) {
		StringBuffer sb = new StringBuffer("*");
		sb.append(value).append("*");
		Query query = new WildcardQuery(new Term(fieldName, sb.toString()));
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/** 小于 */
	public void lessThan(String fieldName, Object value) {
		Query query = queryBuilder.generateNumericQuery(fieldName, null, value, false, false);
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/** 小于等于 */
	public void lessThanAndEqual(String fieldName, Object value) {
		Query query = queryBuilder.generateNumericQuery(fieldName, null, value, true, true);
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/** 大于 */
	public void greaterThan(String fieldName, Object value) {
		Query query = queryBuilder.generateNumericQuery(fieldName, value, null, false, false);
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/** 大于等于 */
	public void greaterThanAndEqual(String fieldName, Object value) {
		Query query = queryBuilder.generateNumericQuery(fieldName, value, null, true, true);
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/** 之间 */
	public void between(String fieldName, Object min, Object max) {
		Query query = queryBuilder.generateNumericQuery(fieldName, min, max, false, false);
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/** 之间包含等于 */
	public void betweenAndEqual(String fieldName, Object min, Object max) {
		Query query = queryBuilder.generateNumericQuery(fieldName, min, max, true, true);
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/** 在集合中 */
	public <T> void inCollection(String fieldName, List<T> collectionValue) {
		if (StringUtils.isBlank(fieldName) || CollectionUtils.isEmpty(collectionValue)) {
			return;
		}
		BooleanQuery bq = new BooleanQuery();
		for (T obj : collectionValue) {
			Query query = queryBuilder.generateQueryByType(fieldName, obj);
			bq.add(query, BooleanClause.Occur.SHOULD);
		}
		bQuery.add(bq, BooleanClause.Occur.MUST);
	}

	/** 不在集合中 */
	public <T> void notInCollection(String fieldName, List<T> collectionValue) {
		if (StringUtils.isBlank(fieldName) || CollectionUtils.isEmpty(collectionValue)) {
			return;
		}
		BooleanQuery bq = new BooleanQuery();
		for (T obj : collectionValue) {
			Query query = queryBuilder.generateQueryByType(fieldName, obj);
			bq.add(query, BooleanClause.Occur.SHOULD);
		}
		bQuery.add(bq, BooleanClause.Occur.MUST_NOT);
	}

	/** 排序 */
	public <IB extends AbstractIndexBean> void sort(String fieldName, boolean isASC, Class<IB> ibean) throws Exception {
		if (StringUtils.isBlank(fieldName) || null == ibean) {
			return;
		}
		if (isASC) {
			this.sortByASC(fieldName, ibean);
		} else {
			this.sortByDESC(fieldName, ibean);
		}
	}

	/** 升序 */
	public <IB extends AbstractIndexBean> void sortByASC(String fieldName, Class<IB> ibean) throws Exception {
		if (StringUtils.isBlank(fieldName) || null == ibean) {
			return;
		}
		Field field = ibean.getField(fieldName);
		if (null == field) {
			return;
		}
		String type = field.getType().getSimpleName();
		SortField sortField = sortBuilder.generateSortField(fieldName, type, false);
		if (null != sortField) {
			this.sortField.add(sortField);
		}
	}

	/** 将序 */
	public <IB extends AbstractIndexBean> void sortByDESC(String fieldName, Class<IB> ibean) throws Exception {
		if (StringUtils.isBlank(fieldName) || null == ibean) {
			return;
		}
		Field field = ibean.getField(fieldName);
		if (null == field) {
			return;
		}
		String type = field.getType().getSimpleName();
		SortField sortField = sortBuilder.generateSortField(fieldName, type, true);
		if (null != sortField) {
			this.sortField.add(sortField);
		}
	}

	
	/**
	 * 评分排序
	 * @author hu
	 * @param reverse
	 */
	public void sortByScore(boolean reverse){
		SortField sortField = sortBuilder.generateScoreOrDocSortField(SortField.SCORE, reverse);
		this.sortField.add(sortField);
	}
	
	/**
	 * 文档排序
	 * @author hu
	 * @param reverse
	 */
	public void sortByDoc(boolean reverse){
		SortField sortField = sortBuilder.generateScoreOrDocSortField(SortField.DOC, reverse);
		this.sortField.add(sortField);
	}
	
	/** 多个已经分词的字段的查询 */
	@SuppressWarnings("deprecation")
	public void multiFieldSearch(List<String> fieldNames, String queryStr) throws ParseException {
		if (StringUtils.isBlank(queryStr) || CollectionUtils.isEmpty(fieldNames)) {
			return;
		}
		String[] fields = new String[] {};
		fields = fieldNames.toArray(fields);
		List<BooleanClause.Occur> clauseList = new ArrayList<>();
		for (int i = 0; i < fieldNames.size(); i++) {
			clauseList.add(BooleanClause.Occur.SHOULD);
		}
		BooleanClause.Occur[] clause = new BooleanClause.Occur[] {};
		clause = clauseList.toArray(clause);
		// + - && || ! ( ) { } [ ] ^ " ~ * ? : 
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_CURRENT, QueryParser.escape(queryStr), fields, clause, analyzerBuilder.getAnalyzer());
		bQuery.add(query, BooleanClause.Occur.MUST);
	}

	/**
	 * @author hu
	 * @param fieldNames
	 * @param queryStr
	 */
	public void fuzzyMultiFieldSearch(List<String> fieldNames, String queryStr){
		if (StringUtils.isBlank(queryStr) || CollectionUtils.isEmpty(fieldNames)) {
			return;
		}
		BooleanQuery wordsBooleanQuery = new BooleanQuery();
		BooleanQuery booleanQuery = new BooleanQuery();
		List<String> words = analyzerBuilder.ikString(queryStr);
		// 分词匹配
		for (int i = 0; i < words.size(); i++) {
			for(String fieldName : fieldNames){

				booleanQuery.add(new WildcardQuery(new Term(fieldName, "*"+words.get(i)+"*")), BooleanClause.Occur.SHOULD);
			}
		}
		// 最小匹配限制
		if (words.size() > 9) {
			booleanQuery.setMinimumNumberShouldMatch(Math.round(words.size() * LONG_RESULT_RATE));
		} else if (words.size() > 2) {
			booleanQuery.setMinimumNumberShouldMatch(Math.round(words.size() * RESULT_RATE));
		}
		
		wordsBooleanQuery.add(booleanQuery, BooleanClause.Occur.MUST);

		bQuery.add(booleanQuery, BooleanClause.Occur.MUST);
	}
	
	/** 需要把结果高亮的词语 */
	public void setHightlightWork(String hightlighterWord) {
		if (StringUtils.isBlank(hightlighterWord)) {
			return;
		}
		this.highlighterWord = hightlighterWord;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Query getBuildedQuery() {
		//System.out.println("===lucene,query:" + new Gson().toJson(this.bQuery));
		return this.bQuery;
	}

	@Override
	public List<SortField> getSortFields() {
		//System.out.println("====lucence,sortField:" + new Gson().toJson(this.sortField));
		return this.sortField;
	}

	@Override
	public String getNeedHighlighterWord() {
		return this.highlighterWord;
	}
	
	public BooleanQuery getBuildedBooleanQuery() {
		return this.bQuery;
	}
}
