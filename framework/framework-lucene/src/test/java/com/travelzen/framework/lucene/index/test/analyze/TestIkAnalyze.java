package com.travelzen.framework.lucene.index.test.analyze;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefualtConfig;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TestIkAnalyze {

	@Test
	public void test() throws IOException {

	}

	public static void main(String[] args) throws IOException {
//		Configuration cfg = DefualtConfig.getInstance(); // 加载词库
//		cfg.setUseSmart(true); // 设置智能分词
//		Dictionary.initial(cfg);
//
//		Dictionary dictionary = Dictionary.getSingleton();
//		List<String> words = new ArrayList<String>();
//		words.add("一日行");
//		dictionary.addWords(words); // 自动添加自定义词

		String testString = "东方明珠很好";
		Analyzer analyzer = new IKAnalyzer(true);
		TokenStream st = analyzer.tokenStream("", new StringReader(testString));
		CharTermAttribute term = st.getAttribute(CharTermAttribute.class);
		StringBuilder sb = new StringBuilder();
		while (st.incrementToken()) {
			sb.append(term.toString()).append("|");
		}
		System.out.println(sb.toString());
	}

}
