class Processor {
	
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.err.println("We are in produce");	
			wait();
			System.err.println("Call produce again");
		}
	}
	
	public void consume() throws InterruptedException
	{
	 Thread.sleep(1000);
		synchronized (this) {
			System.err.println("Consumer Method");
			notify();
			// Thread.sleep(3000);
			System.err.println("Consumer Method Finished");
		}
	}
	
}


public class WaitAndNotify {

	public static void main(String[] args) throws InterruptedException {
		
		Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.produce();
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
					processor.consume();
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
