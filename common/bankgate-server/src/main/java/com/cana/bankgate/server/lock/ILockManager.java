package com.cana.bankgate.server.lock;

public interface ILockManager {
	
	/**
	 * 获取锁, 返回true代表锁已获得，返回false标识未获取到锁
	 * @param timeout 单位：秒
	 * @return
	 */
	public boolean acquire(int timeout) throws InterruptedException;
	
	/**
	 * 设置成需要等待锁
	 */
	public void enable();
	
	/**
	 * 设置成不需要等待锁
	 */
	public void disable();
	
	/**
	 * 清楚超时的队列节点
	 */
	public void cleanTimeoutQueueNode();
	
	
}
