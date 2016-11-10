package com.travelzen.framework.lucene.index.test.ram.write;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;
import com.travelzen.framework.lucene.index.test.student.generateStudentUtil;

public class RamWriter {

	@Test
	public void testAddIndex() throws Exception{
		
		InitIndexUtil.initIndex("/data/tzjn//", false, null, false, StudentIndexBean.class);
		List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		indexBuilder.addIndexs(stuList);
		
		
		BooleanQueryExample exa = new BooleanQueryExample();
		exa.multiFieldSearch(Arrays.asList("address","name"),"我");
		IndexBuilder ib = IndexBuilder.getIndexBuilder();
		List<StudentIndexBean> result = ib.searchAll(exa,StudentIndexBean.class);
		
		for(StudentIndexBean b:result){
			System.out.println("=========:"+new Gson().toJson(b));
		}
		System.out.println("个数:"+result.size());
		
	}
	
	public static void main(String[] args) throws Exception{
		InitIndexUtil.initIndex("/data/tzjn//", false, null, false, StudentIndexBean.class);
		List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		indexBuilder.addIndexs(stuList);
		
		
		BooleanQueryExample exa = new BooleanQueryExample();
		exa.multiFieldSearch(Arrays.asList("address","name"),"我");
		IndexBuilder ib = IndexBuilder.getIndexBuilder();
		List<StudentIndexBean> result = ib.searchAll(exa,StudentIndexBean.class);
		
		for(StudentIndexBean b:result){
			System.out.println("=========:"+new Gson().toJson(b));
		}
		System.out.println("个数:"+result.size());
	}
	
}
