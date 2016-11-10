package com.cana.account.server.test.multiThread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.util.StopWatch;

public class MultiThreadTest {

    @Test
    public void test() {
        StopWatch stopWatch = new StopWatch("tradeRecordStatusUpdateTask");
        stopWatch.start("query");
        Queue<Integer> queue1 = new ConcurrentLinkedQueue<Integer>();
        for (int i = 0; i < 10000; i++) {
            queue1.offer(i);
        }
        stopWatch.stop();
        stopWatch.start("update");
        ExecutorService executor = Executors.newCachedThreadPool();
        
        int threadNumber = 10;
        CountDownLatch doneSignal = new CountDownLatch(threadNumber);
        for (int i = 0; i < threadNumber; i++) {
            executor.execute(new MyThread(queue1, doneSignal));
        }
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
        }
        stopWatch.stop();
        System.out.println(stopWatch.toString());
    }
    
    public static class MyThread implements Runnable {

        private final Queue<Integer> queue1;
        private final CountDownLatch doneSignal;
        public MyThread(Queue<Integer> queue1, CountDownLatch doneSignal) {
            this.queue1 = queue1;
            this.doneSignal = doneSignal;
        }
        @Override
        public void run() {
            for (Integer i = queue1.poll(); i != null; i = queue1.poll()) {
                System.out.println(i);
            }
            doneSignal.countDown();
        }
    }
}
