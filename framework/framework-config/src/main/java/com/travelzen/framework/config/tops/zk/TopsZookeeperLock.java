package com.travelzen.framework.config.tops.zk;

import java.io.File;
import java.util.List;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import com.travelzen.framework.core.util.TZUtil;

public class TopsZookeeperLock {
	private InterProcessMutex mutext;
	private String seq;

	public TopsZookeeperLock(InterProcessMutex mutext, String seq) {
		super();
		this.mutext = mutext;
		this.seq = seq;
	}

	public InterProcessMutex getMutext() {
		return mutext;
	}

	public void setMutext(InterProcessMutex mutext) {
		this.mutext = mutext;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * 从0,1,2,3进行lock 当进程不在的时候,进行解锁
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static TopsZookeeperLock wantToLock(String zkPath) throws Exception {
		if (TZUtil.isEmpty(zkPath))
			throw new IllegalStateException("zkPath is null");
		if (zkPath.length() > 2 && zkPath.endsWith("/")) {
			zkPath = zkPath.substring(0, zkPath.length() - 1);
		}
		List<String> zNodes = TopsCuratorFramework.getInstance().listPaths(zkPath);
		for (String zNode : zNodes) {
			if (zNode.startsWith("s")) {
				String path = zkPath + File.separator + zNode;
				InterProcessMutex interProcessMutex = TopsCuratorFramework.getInstance().getInterProcessMutex(path);
				if (!TZUtil.isEmpty(interProcessMutex)) {
					return new TopsZookeeperLock(interProcessMutex, zNode);
				}
			}
		}
		return null;
	}
}
