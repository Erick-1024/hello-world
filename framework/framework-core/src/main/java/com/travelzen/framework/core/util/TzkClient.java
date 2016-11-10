package com.travelzen.framework.core.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
simple wrapper a zookeeper client

 must work with a existing  curator or  zkclient,   since the connecting is maintain with the main container 
 */
public enum TzkClient {

	instance;

	private final static Logger logger = LoggerFactory.getLogger(TzkClient.class);

	protected ZooKeeper zk;

	public enum ZkSrc {
		FastConfigReader, TopsCuratorFramework, CuratorFramework
	}

	ZkSrc zkSrc;

	public ZkSrc getZkSrc() {
		return zkSrc;
	}

	static public void setZkSrc(ZkSrc zkSrc) {
		TzkClient.instance.zkSrc = zkSrc;
	}

	static ExecutorService executor = Executors.newFixedThreadPool(1);

	public ReentrantLock zklock = new ReentrantLock();
	public Condition zkcondition = zklock.newCondition();

	static final int AWAIT_SEC = 15;

	public Future<ZooKeeper> getZk() {

		if (zk != null) {
			return ConcurrentUtils.constantFuture(zk);
		}

		if (zk == null && zkSrc == null) {
			logger.warn("must first  start a   FastConfigReader  or TopsConfReader , or new a CuratorFramework and call   TzkClient.setZk()");
			logger.warn("if you have done this, then the zk may be down , just wait the zk to up");
		}

		if (zkSrc == ZkSrc.FastConfigReader || zkSrc == ZkSrc.TopsCuratorFramework) {

			Future<ZooKeeper> future = executor.submit(new Callable<ZooKeeper>() {
				public ZooKeeper call() {

					zklock.lock();
					try {
						if (zkcondition.await(AWAIT_SEC, TimeUnit.SECONDS)) {
							executor.shutdown();
							return zk;
						}
					} catch (Exception e) {
						logger.warn("wait fail:{}", e.getLocalizedMessage(), e);
					} finally {
						zklock.unlock();
					}

					return null;
				}
			});

			return future;

		} else {
			logger.warn("if you use CuratorFramework, you must set the zk  manually ");
			return ConcurrentUtils.constantFuture(null);
		}
	}

	public void setZk(ZooKeeper zk) {
		TzkClient.instance.zk = zk;
	}

	static public void setZkAndSignal(ZooKeeper zk) {

		//may not  be call for zk down on startup
		TzkClient.instance.setZk(zk);
		TzkClient.instance.zklock.lock();
		try {
			TzkClient.instance.zkcondition.signalAll();
		} finally {
			TzkClient.instance.zklock.unlock();
		}
	}

	static public byte[] readData(String path) {
		try {
			return instance.getZk().get().getData(path, null, null);
		} catch (ExecutionException | KeeperException | InterruptedException e) {
			logger.warn("readDate err:{}", e.getLocalizedMessage(), e);
		}
		return null;
	}

	public States getStates() {
		return zk.getState();
	}

}
