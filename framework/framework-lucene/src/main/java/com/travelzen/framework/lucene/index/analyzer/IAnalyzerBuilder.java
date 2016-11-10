package com.travelzen.framework.lucene.index.analyzer;

import org.apache.lucene.analysis.Analyzer;

/**
 * @author qifeifei
 */
public interface IAnalyzerBuilder {

	// 设置分词
	public void setAnalyzer(Analyzer analyzer);

	public Analyzer getAnalyzer();

}