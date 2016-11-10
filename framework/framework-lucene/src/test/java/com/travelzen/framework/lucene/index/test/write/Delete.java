package com.travelzen.framework.lucene.index.test.write;

import org.junit.Test;

import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;

public class Delete {

	@Test
	public void testDeleteAllDocument() throws Exception {
		System.out.println("准备删除所有");

		InitIndexUtil.initIndex("/data/tzjn//", true, null, false, StudentIndexBean.class);
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		indexBuilder.deleteAllIndexs(StudentIndexBean.class);
	}
	
	@Test
	public void testDeleteOneDocument() throws Exception  {
		System.out.println("准备删除指定条件记录");
		
		InitIndexUtil.initIndex("/data/tzjn//", true, null, false, StudentIndexBean.class);
		StudentIndexBean stu = new StudentIndexBean();
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		stu.setRemark("发达f");
		indexBuilder.deleteIndex(stu);
	}
	
}
