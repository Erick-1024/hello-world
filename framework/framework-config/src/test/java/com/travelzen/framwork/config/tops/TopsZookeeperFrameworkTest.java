package com.travelzen.framwork.config.tops;

import org.junit.Test;

import com.travelzen.framework.config.tops.util.TopsConfigPathResolver;
import com.travelzen.framework.config.tops.zk.TopsZookeeperLock;
import com.travelzen.framework.core.util.TZUtil;

public class TopsZookeeperFrameworkTest {
	public TopsZookeeperLock lock() {
		String path = TopsConfigPathResolver.getBaseZookeeperPath() + "autocomplete/127.0.1.1";
		try {
			TopsZookeeperLock lock = TopsZookeeperLock.wantToLock(path);
			if (TZUtil.isEmpty(lock)) {
				System.out.println("资源用完了");
			} else {
				System.out.println("lock is " + lock.getSeq());
				return lock;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Test
	public void wantTolock() throws Exception {
		TopsZookeeperLock lock = lock();// lock 1;
		while (lock != null) {
			Thread.sleep(600000);
		}
	}
}
