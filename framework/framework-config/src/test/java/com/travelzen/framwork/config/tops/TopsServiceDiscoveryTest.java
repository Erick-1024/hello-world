package com.travelzen.framwork.config.tops;

import org.junit.Test;

import com.travelzen.framework.config.tops.zk.TopsServiceDiscovery;

public class TopsServiceDiscoveryTest {

	@Test
	public void registerRpc() throws Exception{
		TopsServiceDiscovery.registerRpc("/OP3/TZ", "test-discovery", "abc:123");
		Thread.sleep(1000 * 5);
		System.out.println(TopsServiceDiscovery.getRpcAddress("/OP3/TZ", "test-discovery"));
		Thread.sleep(1000 * 500);
		
	}

}
