package com.travelzen.framework.lucene.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author qifeifei
 */
public class MultiThreadPool {

	private static ExecutorService excutorSerivce = Executors.newCachedThreadPool();

	public static Future<?> addThread(Runnable task) {
		return excutorSerivce.submit(task);
	}

}
