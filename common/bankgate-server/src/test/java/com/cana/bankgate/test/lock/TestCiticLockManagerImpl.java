package com.cana.bankgate.test.lock;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.bankgate.server.lock.CiticLockManagerImpl;
import com.travelzen.framework.redis.client.SpringRedisClient;

public class TestCiticLockManagerImpl {
	
	private CiticLockManagerImpl lockManager = new CiticLockManagerImpl();
	private Logger logger = LoggerFactory.getLogger(getClass());
	private SpringRedisClient redisClient = SpringRedisClient.getInstance();

	@Test
	public void enable() throws Exception{
		for(int i = 0; i < 50; i++){
			lockManager.enable();
			logger.info("设置成需要加锁");
			Thread.sleep(10 * 1000);
		}
		while(true){
			logger.info("锁的值:" + redisClient.get("citic-query-lock"));
			Thread.sleep(10 * 1000);
		}
	}
	
	@Test
	public void acquire_nolock() throws Exception{
		lockManager.disable();
		assertTrue(lockManager.acquire(0));
	}
	
	@Test
	public void acquire_waiting_too_long() throws Exception{
		lockManager.enable();
		lockManager.cleanTimeoutQueueNode();
		for(int i = 0; i < 6; i++){
			new Thread(){
				public void run(){
					try {
						logger.info("加锁结果:" + lockManager.acquire(50));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();;
		}
		Thread.sleep(1000);
		assertFalse(lockManager.acquire(50));
		Thread.sleep(1000 * 60);
	}
	
	@Test
	public void cleanTimeoutQueueNode(){
		lockManager.cleanTimeoutQueueNode();
	}
	
	@Test
	public void deleteQueue(){
		redisClient.getSpringRedisDao().getRedisTemplate().delete("citic-query");
		redisClient.delete("citic-query-lock");
		System.out.println(redisClient.getSpringRedisDao().getRedisTemplate().opsForList().size("citic-query"));
	}

}
