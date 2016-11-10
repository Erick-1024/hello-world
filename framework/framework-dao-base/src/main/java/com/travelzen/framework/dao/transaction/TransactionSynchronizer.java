package com.travelzen.framework.dao.transaction;

import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: xian.zeng
 * Date: 6/17/2014
 * Time: 2:24 PM
 */

public class TransactionSynchronizer {
	private final static Logger logger = LoggerFactory.getLogger(TransactionSynchronizer.class);

	public static void afterCommit(final Runnable runnable) {
		if (TransactionSynchronizationManager.isActualTransactionActive()) {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				public void afterCommit() {
					runnable.run();
				}
			});
		} else {
			//directly call
			runnable.run();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Future<T> afterCommit(final Callable<T> callable) {
		final TransactionFuture<T> future = new TransactionFuture<>();
		if (TransactionSynchronizationManager.isActualTransactionActive()) {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				public void afterCommit() {
					try {
						future.put(callable.call());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else {
			//directly call
			try {
				future.put(callable.call());
			} catch (Exception e) {
				future.cancel(true);
			}
		}
		return future;
	}

	/**
	 * after commit or rollback
	 */
	public static void afterCompletion(final Runnable runnable) {
		if (TransactionSynchronizationManager.isActualTransactionActive()) {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				public void afterCompletion(int status) {
					runnable.run();
				}
			});
		} else {
			//directly call
			runnable.run();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Future<T> afterCompletion(final Callable<T> callable) {
		final TransactionFuture<T> future = new TransactionFuture<>();
		if (TransactionSynchronizationManager.isActualTransactionActive()) {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				public void afterCompletion(int status) {
					try {
						future.put(callable.call());
					} catch (Exception e) {
						logger.error("error calling callable.", e);
						future.cancel(true);
					}
				}
			});
		} else {
			//directly call
			try {
				future.put(callable.call());
			} catch (Exception e) {
				logger.error("error calling callable.", e);
				future.cancel(true);
			}
		}
		return future;
	}

	public static class TransactionFuture<T> implements Future {
		private volatile T res;
		private volatile boolean active = true;
		private volatile boolean done = false;
		private volatile Duration timeout = new Duration(TimeUnit.SECONDS.toMillis(300));

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			active = false;
			notifyAll();
			return false;
		}

		@Override
		public boolean isCancelled() {
			return !active;
		}

		@Override
		public boolean isDone() {
			return done;
		}

		@Override
		public T get() throws InterruptedException, ExecutionException {
			synchronized (this) {
				if (active && res == null) {
					wait(timeout.getMillis());
				}
				return res;
			}
		}

		@Override
		public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
			synchronized (this) {
				if (active && res == null) {
					wait(unit.toMillis(timeout));
				}
				return res;
			}
		}

		public void put(T res) {
			synchronized (this) {
				this.res = res;
				done = true;
				notifyAll();
			}
		}
	}
}
