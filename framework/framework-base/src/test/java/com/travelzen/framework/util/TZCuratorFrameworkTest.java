package com.travelzen.framework.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.zk.TopsCuratorFramework;

public class TZCuratorFrameworkTest {
	private static Logger logger = LoggerFactory.getLogger(TZCuratorFrameworkTest.class);
	@Test
	public void test() throws Exception{
		
		TopsCuratorFramework.getInstance().addConnectionStateListener(
				new ConnectionStateListener(){

					@Override
					public void stateChanged(CuratorFramework client,
							ConnectionState newState) {
						if(newState == ConnectionState.CONNECTED){
							logger.info("connected");
						}
						if(newState == ConnectionState.LOST){
							logger.info("lost");
						}
						if(newState == ConnectionState.RECONNECTED){
							logger.info("reconnected");
						}
						if(newState == ConnectionState.SUSPENDED){
							logger.info("suspended");
						}
						
					}
					
				}
		);
		Thread.currentThread().join();
	}
//	@Test
//	public void test_getRpcAdress() throws InterruptedException{
//		for(int i = 0;i < 1; i++){
//			new Thread(){
//				
//				public void run(){
//					while(true){
//						logger.error(TopsZookeeperBalancer.getRpcAdress("hotel").toString());
//						try {
//							Thread.sleep(10);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//				
//			}.start();
//		}
//		Thread.currentThread().join();
//	}
//	
	@Test
	public void test_registerRpc() throws Exception{
//		TopsZookeeperBalancer.registerRpc("localhost:1005");
		Thread.currentThread().join();
	}
	
}
