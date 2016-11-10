package com.travelzen.framwork.config.tops;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ZKPaths;
import org.junit.Test;

public class CuratorTest {
	private static final String PATH = "/treeCache";
	@Test
	public void treeCache() throws Exception {
		CuratorFramework client = null;
		TreeCache cache = null;
		try {
			client = CuratorFrameworkFactory.newClient("192.168.161.61:2181", new ExponentialBackoffRetry(1000, 3));
			client.start();
			cache = new TreeCache(client, PATH);
			addListener(cache);
			cache.start();
			Thread.sleep(1000 * 1000);
		} finally {
			CloseableUtils.closeQuietly(cache);
			CloseableUtils.closeQuietly(client);
		}
	}

	private static void addListener(final TreeCache cache) {
		TreeCacheListener listener = new TreeCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				switch (event.getType()) {
				case NODE_ADDED: {
					System.out.println("TreeNode added: " + event.getData().getPath() + ", value: "
							+ new String(event.getData().getData()));
					break;
				}
				case NODE_UPDATED: {
					System.out.println("TreeNode changed: " + ZKPaths.getNodeFromPath(event.getData().getPath()) + ", value: "
							+ new String(event.getData().getData()));
					break;
				}
				case NODE_REMOVED: {
					System.out.println("TreeNode removed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
					break;
				}
				default:
					System.out.println("Other event: " + event.getType().name());
				}
			}
		};
		cache.getListenable().addListener(listener);
	}

}
