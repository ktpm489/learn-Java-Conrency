package demoLib;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* Latch -> multiple threads can wait for each other
 * A CyclicBarrier is used in situations where you want to create a group of tasks 
 * to perform work in the parallel + wait unitl they are all finished before moving the next step
 * ===? some thing like join()
 *  some thing like CountDownLatch
 *   CountDownLatch : one-shot event
 *   CyclicBarrier : it can be reused over and over again
 *    CyclicBarrier has a barrier action : a runnable that will run automatically 
 *     when a count reaches 0 !!!
 *     new CyclicBarrier(N) -> will wait for each toher
 * 
 * 
 * **/
class Workers implements Runnable{
	private int id;
	private Random random;
	private CyclicBarrier cyclicBarrier;
	
	public Workers(int id ,CyclicBarrier cyclicBarrier) {
		this.id = id;
		this.random = new Random();
		this.cyclicBarrier = cyclicBarrier;
	}
	
	@Override
	public void run() {
		doWork();
	}

	private void doWork() {
		System.out.println("Thread with ID" + id + "starting  the task..");
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread with ID " + id + "finished");
		try {
			cyclicBarrier.await();
			System.out.println("After awaiting to do some thing");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


public class CycleBarrierDemo {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
			
			@Override
			public void run() {
				
				System.out.println("All the task are finished");
				
			}
		});
		for (int i = 0; i <5; i++) {
			executorService.execute(new Workers(i, cyclicBarrier));
		}
		executorService.shutdown();

	}

}
