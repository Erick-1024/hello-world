package com.travelzen.framework.lucene.index.search;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;

/**
 * @author qifeifei
 */
public abstract class AbstractSearchBuilder implements ISearchBuilder {

	/** 类-> direcotry */
	protected static volatile Map<String, Directory> directoryMap = new ConcurrentHashMap<>();

	/** 类-> IndexReader */
	protected static volatile Map<String, IndexReader> readerMap = new ConcurrentHashMap<>();

	/** 类-> IndexSearcher , thread safe */
	protected static volatile Map<String, IndexSearcher> searcherMap = new ConcurrentHashMap<>();
	protected static volatile Map<String, AtomicInteger> searcherCount = new ConcurrentHashMap<>();

}
