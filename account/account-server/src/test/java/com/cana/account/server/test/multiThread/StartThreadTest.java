package com.cana.account.server.test.multiThread;

public class StartThreadTest {

	public static void main(String[] args) {
		int[] a = {3,2,5,6,3,8,1};
		for(int i=0;i<a.length;i++){
			SleepTime s = new SleepTime(a[i]);
			Thread t = new Thread(s);
			t.start();
		}
		
	}
	
	static class SleepTime implements Runnable{
		
		private int time; 
		
		public SleepTime(int time){
			this.time = time;
		}
		
		public void run(){
			try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(time+" ");
		}
	}

}
