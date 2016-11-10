package com.travelzen.framework.distributedlock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedLock {
	private static Logger logger = LoggerFactory.getLogger(DistributedLock.class);

	private InterProcessMutex lock;
	private String source;

	public DistributedLock(InterProcessMutex lock, String source) {
		this.lock = lock;
		this.source = source;
	}

	public void release() throws Exception {
		if (lock != null) {
			try {
				lock.release();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		}
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
