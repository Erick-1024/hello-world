package com.travelzen.framework.lucene.index.highlighter;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.lucene.index.analyzer.AnalyzerBuilder;

/**
 * @author qifeifei
 */
public class HighlighterBuilder extends AbstractHighlighterBuilder {

	private static volatile HighlighterBuilder highlighterBuilder = null;
	private static AnalyzerBuilder analyzerBuilder = AnalyzerBuilder.getAnalyzerBuilder();

	private HighlighterBuilder() {
	}
	
	/** 获得高亮后的结果 */
	/* (non-Javadoc)
	 * @author hu 增加通配匹配
	 * @see com.travelzen.framework.lucene.index.highlighter.IHighlighterBuilder#getHighlighterResult(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public String getHighlighterResult(String fieldName, String text, String highlighterWord, Map<String, Pair<String, String>> highlighterMap)
			throws IOException, InvalidTokenOffsetsException, ParseException {
		if (StringUtils.isBlank(fieldName) || StringUtils.isBlank(text) || null == highlighterMap || null == highlighterMap.get(fieldName)) {
			return null;
		}
		Analyzer analyzer = analyzerBuilder.getAnalyzer();
//		@SuppressWarnings("deprecation")
//		QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, fieldName, analyzer);
//		Query query = parser.parse(highlighterWord);
		BooleanQuery booleanQuery = new BooleanQuery();
		List<String> words = analyzerBuilder.ikString(highlighterWord);
		// 分词匹配
		for (int i = 0; i < words.size(); i++) {
			booleanQuery.add(new WildcardQuery(new Term(fieldName, "*"+words.get(i)+"*")), BooleanClause.Occur.SHOULD);
		}
		QueryScorer queryScorer = new QueryScorer(booleanQuery);
		Pair<String, String> highlighterStyle = highlighterMap.get(fieldName);
		Formatter formatter = new SimpleHTMLFormatter(highlighterStyle.getLeft(), highlighterStyle.getRight());
		Highlighter hl = new Highlighter(formatter, queryScorer);
		Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
		hl.setTextFragmenter(fragmenter);
		String str = hl.getBestFragment(analyzer, fieldName, text);
		return str;
	}

	// 获得单例
	public static HighlighterBuilder getHighlighterBuilder() {
		if (null == highlighterBuilder) {
			initHighlighterBuilder();
		}
		return highlighterBuilder;
	}

	// 私有-初始化
	private static synchronized void initHighlighterBuilder() {
		if (null != highlighterBuilder) {
			return;
		}
		highlighterBuilder = new HighlighterBuilder();
	}
}
