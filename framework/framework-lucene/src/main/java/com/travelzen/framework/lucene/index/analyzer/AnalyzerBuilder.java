package com.travelzen.framework.lucene.index.analyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.logger.core.TZMarkers;

/**
 * @author qifeifei
 */
public class AnalyzerBuilder extends AbstractAnalyzerBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzerBuilder.class);
	private static AnalyzerBuilder analyzerBuilder = null;

	private AnalyzerBuilder() {
	}

	@Override
	public void setAnalyzer(Analyzer analyzer) {
		if (null == analyzer) {
			LOGGER.info(TZMarkers.p3, "====lucene,analyzer,设置分词器不能为空====");
			return;
		}
		this.analyzer = analyzer;
	}

	@Override
	public Analyzer getAnalyzer() {
		return this.analyzer;
	}

	// 获取单例
	public static AnalyzerBuilder getAnalyzerBuilder() {
		if (null == analyzerBuilder) {
			initAnalyzerBuilder();
		}
		return analyzerBuilder;
	}

	// 初始化
	private static synchronized void initAnalyzerBuilder() {
		if (null != analyzerBuilder) {
			return;
		}
		analyzerBuilder = new AnalyzerBuilder();
	}

	/**
	 * @author hu
	 * @param keywords
	 * @return
	 */
	public List<String> ikString(String keywords){
		List<String> ikTitleKeywords = new ArrayList<>();
		StringReader re = new StringReader(keywords);
		IKSegmenter ik = new IKSegmenter(re,true);
		Lexeme lex = null;
		try {
			while((lex=ik.next())!=null){
				if(!ikTitleKeywords.contains(lex.getLexemeText()))
					ikTitleKeywords.add(lex.getLexemeText());
				
			}
		} catch (IOException e) {
			LOGGER.error("分词失败！",e);
			throw WebException.instance("查询失败！");
		}
		return ikTitleKeywords;
		
	}
}
