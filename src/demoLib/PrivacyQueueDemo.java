package demoLib;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Priority Blocking Queue
 * 
 * 
 * **/

class FirstWorkers implements Runnable {
	
private BlockingQueue<String> blockingQueue;

	public FirstWorkers(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	@Override
	public void run() {
		try {
			blockingQueue.put("B");
			blockingQueue.put("H");
			Thread.sleep(100);
			blockingQueue.put("A");
			blockingQueue.put("D");
			Thread.sleep(100);
			blockingQueue.put("E");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}


class SecondWorkers implements Runnable {
	
private BlockingQueue<String> blockingQueue;

	public SecondWorkers(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}

public class PrivacyQueueDemo {

	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>();
		new Thread(new FirstWorkers(blockingQueue)).start();
		new Thread(new SecondWorkers(blockingQueue)).start();
	}

}
