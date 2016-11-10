package com.travelzen.framework.pool;

import java.util.NoSuchElementException;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.poi.ss.formula.functions.T;

@SuppressWarnings("hiding")
public abstract class BasicObjectPool<T> implements ObjectPool<T>{
	private int maxActive = 1;
	private long maxWait = -1;
	private int maxIdle;
	private boolean testOnBorrow;
	private boolean testWhileIdle;
	private ObjectPool<T> delegate;
	public void init(){
		GenericObjectPool.Config config = new GenericObjectPool.Config();
		config.lifo = true;
		config.maxActive = maxActive;
		config.maxWait = maxWait * DateUtils.MILLIS_PER_SECOND;
		config.maxIdle = maxIdle;
		config.testOnBorrow = testOnBorrow;
		config.testWhileIdle = testWhileIdle;
		config.timeBetweenEvictionRunsMillis = 3 * 1000;
		delegate = new GenericObjectPool<T>(createObjectFactory(), config);
	}
	protected abstract PoolableObjectFactory<T> createObjectFactory();
	@Override
	public T borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
		return delegate.borrowObject();
	}
	@Override
	public void returnObject(T obj) throws Exception {
		if(obj == null)
			return;
		delegate.returnObject(obj);
	}
	@Override
	public void invalidateObject(T obj) throws Exception {
		delegate.invalidateObject(obj);
	}
	@Override
	public void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
		delegate.addObject();
	}
	@Override
	public int getNumIdle() throws UnsupportedOperationException {
		return delegate.getNumIdle();
	}
	@Override
	public int getNumActive() throws UnsupportedOperationException {
		return delegate.getNumActive();
	}
	@Override
	public void clear() throws Exception, UnsupportedOperationException {
		delegate.clear();
	}
	@Override
	public void close() throws Exception {
		delegate.close();
	}
	@Override
	public void setFactory(PoolableObjectFactory<T> factory) throws IllegalStateException, UnsupportedOperationException {
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public long getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
}
