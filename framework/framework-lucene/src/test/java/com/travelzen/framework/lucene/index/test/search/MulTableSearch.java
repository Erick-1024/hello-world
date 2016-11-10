package com.travelzen.framework.lucene.index.test.search;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;

public class MulTableSearch {

	@Test
	public void testSearchIndex() throws Exception{
		InitIndexUtil.initIndex("/data/tzjn", true, null, false, StudentIndexBean.class);
		BooleanQueryExample exa = new BooleanQueryExample();
		exa.multiFieldSearch(Arrays.asList("address","name"),"放得开地方开工积分抵扣看地方15665！@#￥%……\u0026*——fgdsg方地方开关机");
		//exa.greaterThan("markDate", new Date("2001/12/29"));
		//exa.and("name", "我");
		//exa.betweenAndEqual("age", 12l, 87l);
		//exa.or("age", 24l);
		//exa.lessThan("age", stu.getAge());
		//exa.greaterThan("age", 34L);
		//exa.lessThan("age", 80L);
		//exa.and("address", "使");
		//exa.contains("remark", "F");
		//exa.and("remark", "fFDFdaf");
		//exa.and("address", 2);
		//exa.and("age", 24L);
		//exa.and("address", "hellofdsa");
		//exa.and("address", "浙江1");
		//exa.not("address", "浙江1");
		//exa.contains("address", "浙江");
		exa.sort(StudentIndexBean.StudentFieldEnum.age.name(), true, StudentIndexBean.class);
		IndexBuilder ib = IndexBuilder.getIndexBuilder();
		List<StudentIndexBean> result = ib.doSearch(exa, 1, 1000, StudentIndexBean.class);
		// print result
		for(StudentIndexBean b:result){
			System.out.println("=========:"+new Gson().toJson(b));
		}
		System.out.println("个数:"+result.size());
		System.out.println("test:"+exa.getBuildedQuery().toString());
	}
	
	
	@Test
	public void testDocALL() throws Exception{
		InitIndexUtil.initIndex("/data/tzjn", true, null, false, StudentIndexBean.class);
		BooleanQueryExample exa = new BooleanQueryExample();
		IndexBuilder ib = IndexBuilder.getIndexBuilder();
		List<StudentIndexBean> result = ib.doSearch(exa, 1, 1000, StudentIndexBean.class);
		for(StudentIndexBean b : result){
			System.out.println(new Gson().toJson(b));
		}
	}
	
	
}
