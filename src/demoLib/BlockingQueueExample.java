package demoLib;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * BlockingQueue is a inteface that represents a queue is a thread safe
 * 	put() putting items to the queue
 *  take() taking items from the queue
 * 
 * */
class FirstWorker implements  Runnable {
	private BlockingQueue<Integer> blockingQueue;
	
	 public FirstWorker(BlockingQueue<Integer> blockingQueue) {
		// TODO Auto-generated constructor stub
		 this.blockingQueue = blockingQueue;
	}
	@Override
	public void run() {
		int counter = 0;
		while(true) {
			try {
				blockingQueue.put(counter);
				System.err.println("Puting items to the queue ..." + counter);
				counter++;
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}


class SecondWorker implements  Runnable {
	private BlockingQueue<Integer> blockingQueue;
	
	 public SecondWorker(BlockingQueue<Integer> blockingQueue) {
		// TODO Auto-generated constructor stub
		 this.blockingQueue = blockingQueue;
	}
	@Override
	public void run() {
		
		while(true) {
			try {
			int number = blockingQueue.take();
				System.err.println("Taking item from the queue..." + number);
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}



public class BlockingQueueExample {

	public static void main(String[] args) {
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
		
		FirstWorker firstWorker = new FirstWorker(blockingQueue);
		SecondWorker secondWorker= new SecondWorker(blockingQueue);
		new Thread(firstWorker).start();
		new Thread(secondWorker).start();
	}

}
