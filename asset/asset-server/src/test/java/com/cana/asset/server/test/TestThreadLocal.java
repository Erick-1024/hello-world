package com.cana.asset.server.test;

import java.util.List;

public class TestThreadLocal {
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		public Integer initialValue() {
			
			return 0;
		}

	};

	public int getNextNum() {

		seqNum.set(seqNum.get() + 1);

		return seqNum.get();

	}
	//main方法创建对象
	public static void main(String[] args) {
		TestThreadLocal tl = new TestThreadLocal();

		TestClient t1 = new TestClient(tl);

		TestClient t2 = new TestClient(tl);

		TestClient t3 = new TestClient(tl);

		t1.start();

		t2.start();

		t3.start();

	}
	private static class TestClient extends Thread
	{
		private TestThreadLocal tl;

		public TestClient(TestThreadLocal tl) {
			this.tl = tl;
		}
		public void run()
		{
			for (int i = 0; i < 3; i++) {// ④每个线程打出3个序列值
				System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + tl.getNextNum() + "]");
			}
		}

	}

}
