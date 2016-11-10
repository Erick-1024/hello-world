package com.travelzen.framwork.config.tops;

import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import com.travelzen.framework.config.tops.zk.TopsCuratorFramework;

public class TopsCuratorFrameworkTest {

	@Test
	public void exists() throws Exception{
		String zkPath = "/crawler/checkprice/global_switch";
		TopsCuratorFramework.getInstance().deleteNode(zkPath);
		TopsCuratorFramework.getInstance().createNode(zkPath, null, CreateMode.PERSISTENT);
		System.out.println(TopsCuratorFramework.getInstance().exists(zkPath));
		TopsCuratorFramework.getInstance().deleteNode(zkPath);
		System.out.println(TopsCuratorFramework.getInstance().exists(zkPath));
	}

}
