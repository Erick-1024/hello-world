package com.travelzen.framework.lucene.index.test.old.write;

import java.io.File;

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

import com.google.gson.Gson;

public class MyOriginT {

	@Test
	public void testA() throws Exception {
		IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LUCENE_36, new StandardAnalyzer(Version.LUCENE_36));
		IndexWriter writer = new IndexWriter(FSDirectory.open(new File("/data/test")), indexConfig);
		Document doc = new Document();
		doc.add(new Field("title", "lucene introduction", Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("content", "lucene works well", Field.Store.YES, Field.Index.NOT_ANALYZED));
		writer.addDocument(doc);
		writer.commit();
		writer.close();
	}

	@Test
	public void testB() throws Exception {
		IndexSearcher searcher = new IndexSearcher(FSDirectory.open(new File("/data/test")));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		Term term = new Term("content", "well");
		Query query = new TermQuery(term);
		TopDocs hits = searcher.search(query, 10);
		int docNum = hits.scoreDocs[0].doc;
		Document doc1 = searcher.doc(docNum);

		System.out.println(hits.totalHits);
		System.out.println(new Gson().toJson(doc1));
	}
}
