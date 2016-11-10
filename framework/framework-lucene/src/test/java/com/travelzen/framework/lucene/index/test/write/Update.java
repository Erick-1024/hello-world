package com.travelzen.framework.lucene.index.test.write;

import java.util.Arrays;

import org.junit.Test;

import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;

public class Update {

	
	@Test
	public void testDeleteAllDocument() throws Exception {
		System.out.println("准备删除所有");

		InitIndexUtil.initIndex("/data/tzjn//", true, null, false, StudentIndexBean.class);
		
		StudentIndexBean exa = new StudentIndexBean();
		
		StudentIndexBean stu = new StudentIndexBean();
		stu.setS_Id("XXXtest");
		stu.setAddress("test_address");
		stu.setAge(22l);
		
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		indexBuilder.addIndex(exa);
		//indexBuilder.updateIndexs(exa,Arrays.asList(stu));
	}
	
}
