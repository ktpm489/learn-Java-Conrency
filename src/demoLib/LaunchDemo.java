package demoLib;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronize one or more tasks by forcing theme to wait for the completion a
 * set of operations being performed by others tasks .. others tasks may call
 * countDounw() to finishes its job a CountDownLatch can not be reset // If want
 * to reset use a CyclicBarrie instead the tasks that call countDown() are not
 * blocked when they make that call. Onlye the call to await() is blocked until
 * the count
 *
 * 
 **/
public class LaunchDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
ExecutorService executorService =  Executors.newSingleThreadExecutor();
	CountDownLatch latch = new CountDownLatch(5);
	for (int i = 0; i <5; i++) {
		executorService.execute(new Worker(i, latch));
	}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All the prequisistes are done... ");
	}

}

class Worker implements Runnable {

	private int id;
	private CountDownLatch countDownLatch;
	private Random random;

	public Worker(int id, CountDownLatch countDownLatch) {
		this.id = id;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		doWork();
		this.countDownLatch.countDown();

	}

	private void doWork() {
		System.err.println("Thread with id" + this.id + "start working...");
		try {
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
