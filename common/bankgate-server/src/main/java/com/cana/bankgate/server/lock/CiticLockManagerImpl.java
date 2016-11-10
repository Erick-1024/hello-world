package com.cana.bankgate.server.lock;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.google.gson.Gson;
import com.travelzen.framework.redis.client.SpringRedisClient;

public class CiticLockManagerImpl implements ILockManager{
	
	private String redisQueueName = "citic-query";
	
	/**
	 * 单位: 秒
	 */
	private int opInterval = 12;
	
	private SpringRedisClient redisClient = SpringRedisClient.getInstance();
	
	private RedisTemplate<Serializable, Serializable> redisTemplate = SpringRedisClient.getInstance().getSpringRedisDao().getRedisTemplate();
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean acquire(int timeout) throws InterruptedException{
		if(!enabled()){ // 直接获取到锁
			logger.info("不需要排队, 直接可以获得锁");
			return true;
		}
		
		long estimateWaitingTime = redisTemplate.opsForList().size(getRedisQueueName()) * getOpInterval();
		if(estimateWaitingTime > timeout){
			logger.info("预估等待的时间太长，fail fast");
			return false;
		}
		
		String requestId = RandomStringUtils.randomAlphanumeric(10);
		long startTime = new Date().getTime();
		LockRequest lockRequest = new LockRequest();
		lockRequest.setRequestId(requestId);
		lockRequest.setStartTime(startTime);
		lockRequest.setTimeout(timeout);
		// 将此请求加入队列
		redisTemplate.opsForList().rightPush(getRedisQueueName(), lockRequest);
		while(true){
			Date now = new Date();
			if(now.getTime() > (startTime + timeout * 1000)){ //等待超时
				logger.info("等待超时");
				return false;
			}
			LockRequest headLockRequest = (LockRequest)redisTemplate.opsForList().index(getRedisQueueName(), 0);
			if(headLockRequest == null){
				logger.info("异常状况，应该不会发生");
				return false;
			}
			if(requestId.equals(headLockRequest.getRequestId())){// 自己排在队首，可获取锁
				long lastLockTime = getLastLockTime();
				if(now.getTime() >= (lastLockTime + getOpInterval() * 1000)){ //距上次加锁足够长
					redisTemplate.opsForList().leftPop(getRedisQueueName());
					redisClient.save(getLastLockTimeKey(), now.getTime());
					return true;
				}else{ // 等待1秒钟重试
					Thread.sleep(1000);
				}
			}else{ //自己没排在队首，需等待
				Thread.sleep(1000);
			}
		}
	}

	private long getLastLockTime() {
		Long lastLockTime = (Long)redisClient.get(getLastLockTimeKey());
		if(lastLockTime == null){
			lastLockTime = new Date().getTime();
			redisClient.save(getLastLockTimeKey(), lastLockTime);
		}
		return lastLockTime;
	}
	
	private String getLastLockTimeKey(){
		return getRedisQueueName() + "-lastLockTime";
	}

	@Override
	public void enable() {
		long expireTime = 3600 - new Date().getTime() / 1000 % 3600;
		redisClient.save(getLockNameKey(), "enable", expireTime);
	}

	private boolean enabled(){
		return redisClient.get(getLockNameKey()) != null;
	}
	
	private String getLockNameKey(){
		return getRedisQueueName() + "-lock";
	}

	public String getRedisQueueName() {
		return redisQueueName;
	}

	public void setRedisQueueName(String redisQueueName) {
		this.redisQueueName = redisQueueName;
	}

	public int getOpInterval() {
		return opInterval;
	}

	public void setOpInterval(int opInterval) {
		this.opInterval = opInterval;
	}

	@Override
	public void disable() {
		redisClient.delete(getLockNameKey());
	}

	@Override
	public void cleanTimeoutQueueNode() {
		while(true){
			logger.info("队列中节点个数:" + redisTemplate.opsForList().size(getRedisQueueName()));
			LockRequest headLockRequest = (LockRequest)redisTemplate.opsForList().index(getRedisQueueName(), 0);
			if(headLockRequest == null)
				return;
			Date now = new Date();
			logger.info("当前时间:" + now.getTime());
			logger.info("队列节点:" + new Gson().toJson(headLockRequest));
			if(now.getTime() > (headLockRequest.getStartTime() + headLockRequest.getTimeout() * 1000)){
				logger.info("准备清除过期节点");
				redisTemplate.opsForList().remove(getRedisQueueName(), 1, headLockRequest);
				logger.info("删除超时节点1个");
			} else return;
		}
	}

}
