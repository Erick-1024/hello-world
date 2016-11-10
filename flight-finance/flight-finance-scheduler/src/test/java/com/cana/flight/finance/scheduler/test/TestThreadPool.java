package com.cana.flight.finance.scheduler.test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

public class TestThreadPool {

	@Test
	public void test() throws Exception{
		ExecutorService executorService = getThreadPool();
		for(int i = 0; i < 1000; i++)
			executorService.execute(createTask(i));
		executorService.shutdown();
		executorService.awaitTermination(3, TimeUnit.MINUTES);
	}
	
	private ExecutorService getThreadPool(){
		int processorsOfCPU = Runtime.getRuntime().availableProcessors();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2 * processorsOfCPU, 2 * processorsOfCPU, 5L, TimeUnit.MINUTES, new LimitedQueue<Runnable>(1),
				new CustomizableThreadFactory("generate-daily-batch-task-thread"));
		return threadPool;
	}
	
	public Runnable createTask(final int i){
		return new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("task:" + i);
			}
			
		};
	}
	
	public class LimitedQueue<E> extends LinkedBlockingQueue<E>{
		private static final long serialVersionUID = 3593042597313980830L;

		public LimitedQueue(int maxSize)
	    {
	        super(maxSize);
	    }

	    @Override
	    public boolean offer(E e)
	    {
	        // turn offer() and add() into a blocking calls (unless interrupted)
	        try {
	            put(e);
	            return true;
	        } catch(InterruptedException ie) {
	            Thread.currentThread().interrupt();
	        }
	        return false;
	    }

	}

}
