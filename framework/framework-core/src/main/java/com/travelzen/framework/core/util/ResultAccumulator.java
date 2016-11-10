package com.travelzen.framework.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.util.CollectionUtils;

public class ResultAccumulator<T> {
	private CountDownLatch latch;
	private volatile List<T> resultList = new ArrayList<>();
	public ResultAccumulator(int count){
		latch = new CountDownLatch(count);
	}
	/**
	 * 收集返回值
	 * @param response
	 */
	public void accumulate(T response){
		resultList.add(response);
		latch.countDown();
	}
	/**
	 * 等待收集完成或者超时
	 * @param timeout
	 * @param unit
	 * @throws InterruptedException
	 */
	public void await(long timeout, TimeUnit unit) throws InterruptedException{
		latch.await(timeout, unit);
	}
	
	/**
	 * 返回收集的所有结果
	 * @return
	 */
	public List<T> getResultList() {
		return resultList;
	}
	
	/**
	 * 返回收集到的第一个结果， 若无结果则返回null
	 * @return
	 */
	public T get(){
		if(CollectionUtils.isEmpty(resultList))
			return null;
		return resultList.get(0);
	}
}
