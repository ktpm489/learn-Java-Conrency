import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	private List<Integer> list = new ArrayList<>();
	private final int LIMIT = 5;
	private final int BOTTOM = 0;
	private int value = 0;
	public void producer() throws InterruptedException {
		while(true) {
			lock.lock();
			if (list.size() == LIMIT) {
				System.err.println("Waiting for removing items from the list ....");
				condition.await();
			} else {
				System.err.println("Adding" + value);
				list.add(value);
				value++;
				condition.notify();
			}
			Thread.sleep(10000);
			lock.unlock();
		}
	}
	public void consumer() throws InterruptedException {
		while( true) {
			lock.lock();
			if (list.size() == BOTTOM) {
				System.err.println("Waiting for adding item to list....");
				condition.await();
				System.err.println("Waiting adding ");
				
			} else {
				System.err.println("Removing item from list" + list.remove(--value));
				condition.notify();
			}
			Thread.sleep(10000);
			lock.unlock();
			
		}
	}
}


public class ProducerWithLock {

	static Processors processor = new Processors();
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
			Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
			t1.start();
			t2.start();
			t1.join();
			t2.join();

	}

}
