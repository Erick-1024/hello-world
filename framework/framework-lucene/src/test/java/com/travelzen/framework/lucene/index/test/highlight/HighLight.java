package com.travelzen.framework.lucene.index.test.highlight;

import java.util.Arrays;
import java.util.List;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;

public class HighLight {

	
	@Test
	public void searchWithHighlighter() throws Exception{
		InitIndexUtil.initIndex("/data/tzjn", true, null, false, StudentIndexBean.class);
		
		BooleanQueryExample exa = new BooleanQueryExample();
		exa.multiFieldSearch(Arrays.asList("address","name"),"我");
		exa.setHightlightWork("发达");
		
		IndexBuilder ib = IndexBuilder.getIndexBuilder();
		List<StudentIndexBean> result = ib.searchAll(exa,StudentIndexBean.class);
		
		// print result
		for(StudentIndexBean b:result){
			System.out.println(b.getRemark());
		}
		System.out.println("个数:"+result.size());
		
	}
	
	
	@Test
	public void testHighlighter() throws Exception {
		String txt = "我爱北京天安门，天安门上彩旗飞,伟大领袖毛主席，指引我们向前进，向前进！！！想起身离开东京法律思考的机会那个上的讲话那伟大的个圣诞sadfsadnfl.sajdfl;aksjdf;lsadfsadfm.asd那是肯定激发了深刻的机会拉萨宽带计费了那个傻大姐华纳公司的机会节贺卡就是对话框那是国天安门际  北京电话卡开始觉啊 北京得人们大会堂  北京！！！！";
		QueryParser parser = new QueryParser(Version.LUCENE_36, "content1", new IKAnalyzer());
		Query query = parser.parse("北京 伟大");

		QueryScorer queryScorer = new QueryScorer(query);// 如果有需要，可以传入评分
		// 设置高亮标签
		Formatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");
		// 高亮分析器
		Highlighter hl = new Highlighter(formatter, queryScorer);

		Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
		hl.setTextFragmenter(fragmenter);
		// 获取返回结果
		String str = hl.getBestFragment(new IKAnalyzer(), "content1", txt);
		System.out.println(str);
	}
	
}
