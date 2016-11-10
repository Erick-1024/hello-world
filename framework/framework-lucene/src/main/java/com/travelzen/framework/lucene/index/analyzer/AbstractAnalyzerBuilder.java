package com.travelzen.framework.lucene.index.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * @author qifeifei
 */
public abstract class AbstractAnalyzerBuilder implements IAnalyzerBuilder{
	
	/** 默认是IK分词 */
	protected Analyzer analyzer = new IKAnalyzer(true);
	
}
