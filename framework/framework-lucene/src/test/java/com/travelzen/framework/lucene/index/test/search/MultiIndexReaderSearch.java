package com.travelzen.framework.lucene.index.test.search;

import java.io.File;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;
import com.travelzen.framework.lucene.index.test.student.generateStudentUtil;

public class MultiIndexReaderSearch {

	@Test
	public void testMultiAdd() throws Exception {
		System.out.println("准备增加两个不同数据表");
		//InitIndexUtil.initIndex("/data/programdata/jnroute1", true, null, false, StudentIndexBean.class);
		InitIndexUtil.initIndex("/data/programdata/jnroute2", true, null, false, StudentIndexBean.class);
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		List<StudentIndexBean> stuList = generateStudentUtil.getStudents2();
		indexBuilder.addIndexs(stuList);
		Thread.currentThread().sleep(4000l);
	}

	
	@Test
	public void testMultiSearch() throws Exception {
		IndexReader reader1 = IndexReader.open(FSDirectory.open(new File("/data/programdata/jnroute1/StudentIndexBean")), true);
		IndexReader reader2 = IndexReader.open(FSDirectory.open(new File("/data/programdata/jnroute2/StudentIndexBean")), true);
//		Term term = new Term("name", "我");
//		Query query = new TermQuery(term);
//		IndexSearcher search1 = new IndexSearcher(reader1);
//		IndexSearcher search2 = new IndexSearcher(reader2);
//		TopDocs docs1 = search1.search(query, 10);
//		System.out.println(docs1.totalHits);
//		TopDocs docs2 = search2.search(query, 10);
//		System.out.println(docs2.totalHits);
		MultiReader multiR = new MultiReader(new IndexReader[]{reader2,reader1});
		IndexSearcher multiS = new IndexSearcher(multiR);
		Term termMulti = new Term("name", "我");
		Query queryMulti = new TermQuery(termMulti);
		TopDocs docMulti = multiS.search(queryMulti, 10);
		System.out.println(docMulti.totalHits);
	}
	

}
