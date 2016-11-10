package com.travelzen.framework.lucene.index.test.ram.search;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;

public class RamSearch {

	@Test
	public void testSearch() throws Exception {
		InitIndexUtil.initIndex("/data/tzjn", false, null, false, StudentIndexBean.class);
		
		BooleanQueryExample exa = new BooleanQueryExample();
		exa.multiFieldSearch(Arrays.asList("address","name"),"我");
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
		IndexBuilder ib = IndexBuilder.getIndexBuilder();
		List<StudentIndexBean> result = ib.searchAll(exa,StudentIndexBean.class);
		
		for(StudentIndexBean b:result){
			System.out.println("=========:"+new Gson().toJson(b));
		}
		System.out.println("个数:"+result.size());
	}
	
}
