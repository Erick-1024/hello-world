package com.travelzen.framework.lucene.index.test.write;

import java.io.File;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;
import com.travelzen.framework.lucene.index.test.student.TeacherIndexBean;
import com.travelzen.framework.lucene.index.test.student.generateStudentUtil;
import com.travelzen.framework.lucene.index.test.student.generateTeacherUtil;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean.StudentFieldEnum;

public class Add {

	@Test
	public void testAddIndex() throws Exception {
		System.out.println("准备增加所有");

		InitIndexUtil.initIndex("/data/tzjn//", true, null, false, StudentIndexBean.class);
		List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		indexBuilder.addIndexs(stuList);
	}

	@Test
	public void testAddOneIndex() throws Exception {
		System.out.println("准备增加一个记录");

		InitIndexUtil.initIndex("/data/tzjn//", true, null, false, StudentIndexBean.class);
		List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		indexBuilder.addIndex(stuList.get(0));
	}

	/** 同时增加不同索引 */
	@Test
	public void testMulDifferentAdd() throws Exception {
		System.out.println("准备增加两个不同数据表");
		InitIndexUtil.initIndex("/tmp/tzjn/", true, null, false, StudentIndexBean.class);
		InitIndexUtil.initIndex("/tmp/tzjn/", true, null, false, TeacherIndexBean.class);
		List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
		List<TeacherIndexBean> tList = generateTeacherUtil.getTeachers();
		// add
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		indexBuilder.addIndexs(stuList);
		indexBuilder.addIndexs(tList);
		Thread.currentThread().sleep(4000l);
		// search
		BooleanQueryExample stuexa = new BooleanQueryExample();
		List<String> felds = Lists.newArrayList();
		felds.add(StudentFieldEnum.address.name());
		felds.add(StudentFieldEnum.name.name());
		stuexa.multiFieldSearch(felds, "诺基亚n97");
		stuexa.sortByScore(false);
		stuexa.sortByDESC(StudentFieldEnum.birthDay.name(), StudentIndexBean.class);
		List<StudentIndexBean> result = indexBuilder.searchAll(stuexa, StudentIndexBean.class);
		System.out.println(result.size());
		BooleanQueryExample teaExa = new BooleanQueryExample();
		List<TeacherIndexBean> result2 = indexBuilder.searchAll(teaExa, TeacherIndexBean.class);
		System.out.println(result2.size());
	}

	/** 测试长时间性能 */
	@Test
	public void testLongtimeAdd() throws Exception {
		System.out.println("准备增加两个不同数据表");
		for (int i = 0; i < 10000; i++) {
			long startTime = System.currentTimeMillis();
			InitIndexUtil.initIndex("/data/tzjn/", true, null, false, StudentIndexBean.class);
			List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
			IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
			indexBuilder.addIndexs(stuList);
			long endTime = System.currentTimeMillis();
			System.out.println("第" + i + "次增加索引文件，消耗时间:" + (endTime - startTime)+"毫秒");
			Thread.currentThread().sleep(4000l);
		}
	}
	
}
