import java.util.ArrayList;
import java.util.List;

class Processors {
	
	private List<Integer> list = new ArrayList<>();
	private final int LIMIT = 5;
	private final int BOTTOM = 0;
	private int value = 0;
	private final Object lock = new Object();
	public void producer() throws InterruptedException {
		synchronized (lock) {
			while(true) {
				if (list.size() == LIMIT) {
					System.err.println("Waiting for removing items from the list ....");
					lock.wait();
				} else {
					System.err.println("Adding" + value);
					list.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(10000);
				
			}
		}
	}
	
	public void consumer() throws InterruptedException {
		synchronized (lock) {
			while( true) {
				if (list.size() == BOTTOM) {
					System.err.println("Waiting for adding item to list....");
					lock.wait();
					System.err.println("Waiting adding ");
					
				} else {
					System.err.println("Removing item from list" + list.remove(--value));
					lock.notify();
				}
				Thread.sleep(10000);
				
			}
			
		}
		
	}
	
}


public class ProcuderAndConsumer {

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
